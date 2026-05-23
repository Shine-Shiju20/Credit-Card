package stepdefinitions.api.Loans;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import utils.Loan.ApiSessionManager;
import utils.Loan.ExcelReader;
import utils.Loan.LoanTestContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class foreclose_loan {

    ExcelReader excel;
    Response response;

    String excelPath = "src/test/java/resources/data/complete_Loan_test_cases.xlsx";
    String sheetName = "Automation";

    String loanId;
    List<String> createdLoanIds = new ArrayList<>();

    @Given("User is logged in for foreclosure testing")
    public void user_is_logged_in_for_foreclosure_testing() throws IOException {
        ApiSessionManager.loginIfNeeded();
        System.out.println("Session ready for foreclosure testing.");
    }

    @When("User creates loan for foreclosure using {string}")
    public void user_creates_loan_for_foreclosure_using(String tcId) throws IOException {

        excel = new ExcelReader(excelPath, sheetName);

        JSONObject loanBody = buildLoanBody(tcId);

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .body(loanBody.toString())
                        .when()
                        .post("/loans/apply")
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Create Loan Response:");
        System.out.println(response.asPrettyString());

        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 201, "Loan creation failed");
        Assert.assertEquals(response.jsonPath().getBoolean("success"), true);

        loanId = response.jsonPath().getString("data.loan_id");
        Assert.assertNotNull(loanId, "Loan ID not generated");

        createdLoanIds.add(loanId);
        LoanTestContext.addLoanId(loanId);

        System.out.println("Created Loan ID: " + loanId);
    }

    @When("User forecloses created loan using {string}")
    public void user_forecloses_created_loan_using(String tcId) throws IOException {

        String accountId = excel.getCellDataByTestCaseIDSafe(tcId, "Foreclose_Account_ID");

        if (accountId == null || accountId.trim().isEmpty()) {
            accountId = excel.getCellDataByTestCaseIDSafe(tcId, "Account_ID");
        }

        JSONObject body = new JSONObject();
        body.put("source_account_id", accountId);

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .body(body.toString())
                        .when()
                        .post("/loans/foreclose/" + loanId)
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Foreclosure Account ID Used: " + accountId);
        System.out.println("Foreclosure Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("Foreclosure response for {string} should be validated")
    public void foreclosure_response_for_should_be_validated(String tcId) {

        int expectedStatus = Integer.parseInt(
                excel.getCellDataByTestCaseIDSafe(tcId, "Expected_Status_Code")
        );

        String expectedSuccess = excel.getCellDataByTestCaseIDSafe(tcId, "Expected_Success")
                .trim()
                .toLowerCase();

        String expectedMessage = excel.getCellDataByTestCaseIDSafe(tcId, "Expected_Message")
                .trim()
                .toLowerCase();

        Assert.assertEquals(response.getStatusCode(), expectedStatus, "Foreclosure status code mismatch");

        Boolean actualSuccess = response.jsonPath().getBoolean("success");

        Assert.assertEquals(
                String.valueOf(actualSuccess).toLowerCase(),
                expectedSuccess,
                "Foreclosure success mismatch"
        );

        if (!expectedMessage.isEmpty()) {
            Assert.assertTrue(
                    response.asString().toLowerCase().contains(expectedMessage),
                    "Expected message not found: " + expectedMessage
            );
        }
    }

    @When("User creates three active loans for max limit")
    public void user_creates_three_active_loans_for_max_limit() throws IOException {

        excel = new ExcelReader(excelPath, sheetName);

        createLoanAndSave("TC_FORE_002_HOME");
        createLoanAndSave("TC_FORE_002_VEHICLE");
        createLoanAndSave("TC_FORE_002_EDU");
    }

    @When("User tries to create fourth loan using {string}")
    public void user_tries_to_create_fourth_loan_using(String tcId) throws IOException {

        JSONObject loanBody = buildLoanBody(tcId);

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .body(loanBody.toString())
                        .when()
                        .post("/loans/apply")
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Fourth Loan Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("Maximum loan limit message should be displayed for {string}")
    public void maximum_loan_limit_message_should_be_displayed_for(String tcId) {

        String expectedMessage = excel.getCellDataByTestCaseIDSafe(tcId, "Expected_Message")
                .trim()
                .toLowerCase();

        Boolean success = response.jsonPath().getBoolean("success");

        Assert.assertEquals(success, false, "Fourth loan should fail");

        Assert.assertTrue(
                response.asString().toLowerCase().contains(expectedMessage),
                "Maximum loan limit message not found"
        );
    }

    @When("User forecloses any one active loan using {string}")
    public void user_forecloses_any_one_active_loan_using(String tcId) throws IOException {

        String loanToForeclose = createdLoanIds.get(0);
        String accountId = excel.getCellDataByTestCaseIDSafe(tcId, "Foreclose_Account_ID");

        if (accountId == null || accountId.trim().isEmpty()) {
            accountId = excel.getCellDataByTestCaseIDSafe(tcId, "Account_ID");
        }

        JSONObject body = new JSONObject();
        body.put("source_account_id", accountId);

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .body(body.toString())
                        .when()
                        .post("/loans/foreclose/" + loanToForeclose)
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Foreclose One Active Loan Response:");
        System.out.println(response.asPrettyString());

        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 201, "Foreclosure failed");
        Assert.assertEquals(response.jsonPath().getBoolean("success"), true);

        createdLoanIds.remove(loanToForeclose);
        LoanTestContext.getLoanIds().remove(loanToForeclose);
    }

    @When("User creates another loan after foreclosure using {string}")
    public void user_creates_another_loan_after_foreclosure_using(String tcId) throws IOException {

        JSONObject loanBody = buildLoanBody(tcId);

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .body(loanBody.toString())
                        .when()
                        .post("/loans/apply")
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Create Loan After Foreclosure Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("New loan should be created successfully after foreclosure")
    public void new_loan_should_be_created_successfully_after_foreclosure() {

        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 201, "New loan not created after foreclosure");
        Assert.assertEquals(response.jsonPath().getBoolean("success"), true);

        String newLoanId = response.jsonPath().getString("data.loan_id");
        Assert.assertNotNull(newLoanId, "New loan ID not generated");

        LoanTestContext.addLoanId(newLoanId);

        System.out.println("New Loan ID After Foreclosure: " + newLoanId);
    }

    public void createLoanAndSave(String tcId) throws IOException {

        JSONObject loanBody = buildLoanBody(tcId);

        Response createResponse = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .body(loanBody.toString())
                        .when()
                        .post("/loans/apply")
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Create Loan For " + tcId + ":");
        System.out.println(createResponse.asPrettyString());

        Assert.assertTrue(createResponse.getStatusCode() == 200 || createResponse.getStatusCode() == 201, "Loan creation failed for " + tcId);
        Assert.assertEquals(createResponse.jsonPath().getBoolean("success"), true);

        String id = createResponse.jsonPath().getString("data.loan_id");
        Assert.assertNotNull(id, "Loan ID missing for " + tcId);

        createdLoanIds.add(id);
        LoanTestContext.addLoanId(id);
    }

    public JSONObject buildLoanBody(String tcId) {

        JSONObject loanBody = new JSONObject();

        loanBody.put("loan_type", excel.getCellDataByTestCaseIDSafe(tcId, "loan_type"));
        loanBody.put("requested_amount", Double.parseDouble(excel.getCellDataByTestCaseIDSafe(tcId, "requested_amount")));
        loanBody.put("tenure_months", Integer.parseInt(excel.getCellDataByTestCaseIDSafe(tcId, "tenure_months")));
        loanBody.put("annual_income", Double.parseDouble(excel.getCellDataByTestCaseIDSafe(tcId, "annual_income")));
        loanBody.put("occupation", excel.getCellDataByTestCaseIDSafe(tcId, "Occupation"));
        loanBody.put("existing_liabilities", Double.parseDouble(excel.getCellDataByTestCaseIDSafe(tcId, "existing_liabilities")));
        loanBody.put("linked_account_id", excel.getCellDataByTestCaseIDSafe(tcId, "Account_ID"));

        return loanBody;
    }

    private Map<String, String> getSafeCookies() {
        try {
            return ApiSessionManager.getCookies();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}