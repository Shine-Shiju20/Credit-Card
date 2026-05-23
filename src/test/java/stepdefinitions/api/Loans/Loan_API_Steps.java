package stepdefinitions.api.Loans;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import utils.Loan.ApiSessionManager;
import utils.Loan.ExcelReader;
import utils.Loan.LoanTestContext;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Loan_API_Steps {

    ExcelReader excel;
    Response response;

    String loanId;

    int excelRow = 1;

    String excelPath = "src/test/java/resources/data/complete_Loan_test_cases.xlsx";
    String sheetName = "Automation";

    @Given("User logs into the banking application")
    public void user_logs_into_the_banking_application() throws IOException {
        ApiSessionManager.loginIfNeeded();
        System.out.println("Session ready for loan API testing.");
    }

    @When("User fetches all loans")
    public void user_fetches_all_loans() throws IOException {

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .when()
                        .get("/loans/user/me")
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Fetch Loans Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("Fetch loans response should be validated")
    public void fetch_loans_response_should_be_validated() {

        Assert.assertEquals(response.getStatusCode(), 200, "Fetch loans failed");
        Assert.assertEquals(response.jsonPath().getBoolean("success"), true, "Fetch loans success mismatch");
        Assert.assertNotNull(response.jsonPath().get("data"), "Loans data is missing");
    }

    @When("User fetches active loans summary")
    public void user_fetches_active_loans_summary() throws IOException {

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .when()
                        .get("/loans/active-summary")
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Active Loans Summary Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("Active loans summary response should be validated")
    public void active_loans_summary_response_should_be_validated() {

        Assert.assertEquals(response.getStatusCode(), 200, "Active loans summary failed");
        Assert.assertEquals(response.jsonPath().getBoolean("success"), true, "Active loans summary success mismatch");
        Assert.assertNotNull(response.asString(), "Active loans summary response is empty");
    }

    @When("User applies for loan using Excel test data")
    public void user_applies_for_loan_using_excel_test_data() throws IOException {

        excel = new ExcelReader(excelPath, sheetName);

        JSONObject loanBody = new JSONObject();

        loanBody.put("loan_type", excel.getCellData(excelRow, "loan_type"));
        loanBody.put("requested_amount", Double.parseDouble(excel.getCellData(excelRow, "requested_amount")));
        loanBody.put("tenure_months", Integer.parseInt(excel.getCellData(excelRow, "tenure_months")));
        loanBody.put("annual_income", Double.parseDouble(excel.getCellData(excelRow, "annual_income")));
        loanBody.put("occupation", excel.getCellData(excelRow, "Occupation"));
        loanBody.put("existing_liabilities", Double.parseDouble(excel.getCellData(excelRow, "existing_liabilities")));

        String accountId = excel.getCellData(excelRow, "Account_ID");

        if (accountId == null || accountId.trim().isEmpty()) {
            throw new RuntimeException("Account_ID is empty in Excel. Add valid account_id in Excel.");
        }

        loanBody.put("linked_account_id", accountId);

        System.out.println("Apply Loan Request:");
        System.out.println(loanBody.toString());

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

        System.out.println("Apply Loan Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("Loan application response should be validated")
    public void loan_application_response_should_be_validated() {

        int expectedStatus = Integer.parseInt(
                excel.getCellData(excelRow, "Expected_Status_Code")
        );

        String expectedSuccess = excel.getCellData(excelRow, "Expected_Success")
                .trim()
                .toLowerCase();

        String expectedMessage = excel.getCellData(excelRow, "Expected_Message")
                .trim();

        Assert.assertEquals(response.getStatusCode(), expectedStatus, "Status code mismatch");

        Boolean actualSuccess = response.jsonPath().getBoolean("success");

        Assert.assertEquals(
                String.valueOf(actualSuccess).toLowerCase(),
                expectedSuccess,
                "Success mismatch"
        );

        if (!expectedMessage.isEmpty()) {

            String actualMessage = response.jsonPath().getString("message");

            Assert.assertNotNull(actualMessage, "Message field not found in response");

            Assert.assertTrue(
                    actualMessage.toLowerCase().contains(expectedMessage.toLowerCase())
                            || expectedMessage.toLowerCase().contains(actualMessage.toLowerCase()),
                    "Expected message mismatch. Expected: " + expectedMessage + " | Actual: " + actualMessage
            );
        }

        if (actualSuccess != null && actualSuccess) {
            loanId = response.jsonPath().getString("data.loan_id");
            Assert.assertNotNull(loanId, "Loan ID not found after apply loan");

            LoanTestContext.addLoanId(loanId);

            System.out.println("Saved Loan ID: " + loanId);
        }
    }

    @When("User fetches loan by loan id")
    public void user_fetches_loan_by_loan_id() throws IOException {

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new RuntimeException("Loan ID is missing. Apply loan first.");
        }

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .when()
                        .get("/loans/" + loanId)
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Fetch Loan By ID Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("Fetch loan by id response should be validated")
    public void fetch_loan_by_id_response_should_be_validated() {

        Assert.assertEquals(response.getStatusCode(), 200, "Fetch loan by ID failed");
        Assert.assertEquals(response.jsonPath().getBoolean("success"), true, "Fetch loan by ID success mismatch");

        Assert.assertTrue(
                response.asString().contains(loanId),
                "Loan ID not found in fetch loan by ID response"
        );
    }

    @When("User fetches loan EMI schedule")
    public void user_fetches_loan_emi_schedule() throws IOException {

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new RuntimeException("Loan ID is missing. Apply loan first.");
        }

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .when()
                        .get("/loans/schedule/" + loanId)
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Loan EMI Schedule Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("Loan EMI schedule response should be validated")
    public void loan_emi_schedule_response_should_be_validated() {

        Assert.assertEquals(response.getStatusCode(), 200, "Fetch EMI schedule failed");
        Assert.assertEquals(response.jsonPath().getBoolean("success"), true, "EMI schedule success mismatch");
        Assert.assertNotNull(response.asString(), "EMI schedule response is empty");
    }

    @When("User pays loan EMI")
    public void user_pays_loan_emi() throws IOException {

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new RuntimeException("Loan ID is missing. Apply loan first.");
        }

        ExcelReader paymentExcel = new ExcelReader(excelPath, sheetName);

        String accountId = paymentExcel.getCellData(excelRow, "Account_ID");

        if (accountId == null || accountId.trim().isEmpty()) {
            throw new RuntimeException("Account_ID is empty in Excel.");
        }

        Response loanDetailsResponse = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .when()
                        .get("/loans/" + loanId)
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Loan Details Before EMI Payment:");
        System.out.println(loanDetailsResponse.asPrettyString());

        String emiValue = loanDetailsResponse.jsonPath().getString("data.monthly_emi");

        if (emiValue == null) {
            emiValue = loanDetailsResponse.jsonPath().getString("data.loan.monthly_emi");
        }

        Assert.assertNotNull(emiValue, "Monthly EMI not found in loan details response");

        double monthlyEmi = Double.parseDouble(emiValue);

        JSONObject paymentBody = new JSONObject();

        paymentBody.put("loan_id", loanId);
        paymentBody.put("payment_amount", monthlyEmi);
        paymentBody.put("source_account_id", accountId);
        paymentBody.put("installments_to_pay", 1);

        System.out.println("Loan EMI Payment Request:");
        System.out.println(paymentBody.toString());

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .body(paymentBody.toString())
                        .when()
                        .post("/loans/payment")
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Loan EMI Payment Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("Loan EMI payment response should be validated")
    public void loan_emi_payment_response_should_be_validated() {

        Assert.assertTrue(
                response.getStatusCode() == 200 || response.getStatusCode() == 201,
                "EMI payment status code mismatch"
        );

        Assert.assertEquals(response.jsonPath().getBoolean("success"), true, "EMI payment success mismatch");
    }

    @When("User fetches foreclosure preview")
    public void user_fetches_foreclosure_preview() throws IOException {

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new RuntimeException("Loan ID is missing. Apply loan first.");
        }

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .when()
                        .get("/loans/foreclose-preview/" + loanId)
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Foreclosure Preview Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("Foreclosure preview response should be validated")
    public void foreclosure_preview_response_should_be_validated() {

        Assert.assertEquals(response.getStatusCode(), 200, "Foreclosure preview failed");
        Assert.assertEquals(response.jsonPath().getBoolean("success"), true, "Foreclosure preview success mismatch");
        Assert.assertNotNull(response.asString(), "Foreclosure preview response is empty");
    }

    @When("User forecloses loan")
    public void user_forecloses_loan() throws IOException {

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new RuntimeException("Loan ID is missing. Apply loan first.");
        }

        ExcelReader forecloseExcel = new ExcelReader(excelPath, sheetName);

        String accountId = forecloseExcel.getCellData(excelRow, "Account_ID");

        if (accountId == null || accountId.trim().isEmpty()) {
            throw new RuntimeException("Account_ID is empty in Excel.");
        }

        JSONObject forecloseBody = new JSONObject();
        forecloseBody.put("source_account_id", accountId);

        System.out.println("Foreclose Loan Request:");
        System.out.println(forecloseBody.toString());

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .body(forecloseBody.toString())
                        .when()
                        .post("/loans/foreclose/" + loanId)
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Foreclose Loan Response:");
        System.out.println(response.asPrettyString());
    }

    @Then("Foreclose loan response should be validated")
    public void foreclose_loan_response_should_be_validated() {

        Assert.assertTrue(
                response.getStatusCode() == 200 || response.getStatusCode() == 201,
                "Foreclose loan status code mismatch"
        );

        Assert.assertEquals(response.jsonPath().getBoolean("success"), true, "Foreclose loan success mismatch");
        Assert.assertNotNull(response.asString(), "Foreclose loan response is empty");
    }

    private Map<String, String> getSafeCookies() {
        try {
            return ApiSessionManager.getCookies();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}