package stepdefinitions.api.Loans;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import utils.Loan.ApiSessionManager;
import utils.Loan.ExcelReader;
import utils.Loan.LoanTestContext;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class amount_check {

    ExcelReader excel;
    Response response;

    String excelPath = "src/test/java/resources/Data/complete_Loan_test_cases.xlsx";
    String sheetName = "Automation";

    String currentTcId;

    @Given("User is logged in for applying multiple loans")
    public void user_is_logged_in_for_applying_multiple_loans() throws IOException {
        ApiSessionManager.loginIfNeeded();
        System.out.println("Session ready. Reusing login from ApiSessionManager.");
    }

    @When("User applies loan for {string}")
    public void user_applies_loan_for(String tcId) throws IOException {

        currentTcId = tcId;
        excel = new ExcelReader(excelPath, sheetName);

        JSONObject loanBody = new JSONObject();

        String endpoint = excel.getCellDataByTestCaseID(tcId, "Endpoint");

        loanBody.put("loan_type", excel.getCellDataByTestCaseID(tcId, "loan_type"));
        loanBody.put("requested_amount", Double.parseDouble(excel.getCellDataByTestCaseID(tcId, "requested_amount")));
        loanBody.put("tenure_months", Integer.parseInt(excel.getCellDataByTestCaseID(tcId, "tenure_months")));
        loanBody.put("annual_income", Double.parseDouble(excel.getCellDataByTestCaseID(tcId, "annual_income")));
        loanBody.put("occupation", excel.getCellDataByTestCaseID(tcId, "Occupation"));
        loanBody.put("existing_liabilities", Double.parseDouble(excel.getCellDataByTestCaseID(tcId, "existing_liabilities")));
        loanBody.put("linked_account_id", excel.getCellDataByTestCaseID(tcId, "Account_ID"));

        System.out.println("\nApplying Loan For TC_ID: " + tcId);
        System.out.println("Endpoint: " + endpoint);
        System.out.println("Request Body:");
        System.out.println(loanBody.toString());

        response = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .body(loanBody.toString())
                        .when()
                        .post(endpoint)
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("Response For " + tcId + ":");
        System.out.println(response.asPrettyString());
    }

    @Then("Loan application for {string} should be validated")
    public void loan_application_for_should_be_validated(String tcId) throws IOException {

        int expectedStatus = Integer.parseInt(
                excel.getCellDataByTestCaseID(tcId, "Expected_Status_Code")
        );

        String expectedSuccess = excel.getCellDataByTestCaseID(tcId, "Expected_Success")
                .trim()
                .toLowerCase();

        String expectedMessage = excel.getCellDataByTestCaseID(tcId, "Expected_Message")
                .trim();

        int actualStatus = response.getStatusCode();
        Boolean actualSuccess = response.jsonPath().getBoolean("success");
        String responseBody = response.asString();

        Assert.assertEquals(actualStatus, expectedStatus, "Status code mismatch for " + tcId);

        Assert.assertEquals(
                String.valueOf(actualSuccess).toLowerCase(),
                expectedSuccess,
                "Success mismatch for " + tcId
        );

        if (!expectedMessage.isEmpty()) {
            Assert.assertTrue(
                    responseBody.toLowerCase().contains(expectedMessage.toLowerCase()),
                    "Expected message not found for " + tcId
            );
        }

        if (actualSuccess != null && actualSuccess) {
            String loanId = response.jsonPath().getString("data.loan_id");
            System.out.println("Loan ID for " + tcId + ": " + loanId);
            LoanTestContext.addLoanId(loanId);
        }
    }

    private Map<String, String> getSafeCookies() {
        try {
            return ApiSessionManager.getCookies();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}