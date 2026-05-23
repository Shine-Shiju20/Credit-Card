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

public class emi_payment {

    ExcelReader excel;
    Response response;

    String excelPath = "src/test/java/resources/data/complete_Loan_test_cases.xlsx";
    String sheetName = "Automation";

    String loanId;
    String accountId;
    double monthlyEmi;

    @Given("User is logged in for EMI payment testing")
    public void user_is_logged_in_for_emi_payment_testing() throws IOException {
        ApiSessionManager.loginIfNeeded();
        System.out.println("Session ready for EMI payment testing.");
    }

    @When("User creates loan for EMI test using {string}")
    public void user_creates_loan_for_emi_test_using(String tcId) throws IOException {

        excel = new ExcelReader(excelPath, sheetName);

        accountId = excel.getCellDataByTestCaseIDSafe(tcId, "Account_ID");

        JSONObject loanBody = new JSONObject();

        loanBody.put("loan_type", excel.getCellDataByTestCaseIDSafe(tcId, "loan_type"));
        loanBody.put("requested_amount", Double.parseDouble(excel.getCellDataByTestCaseIDSafe(tcId, "requested_amount")));
        loanBody.put("tenure_months", Integer.parseInt(excel.getCellDataByTestCaseIDSafe(tcId, "tenure_months")));
        loanBody.put("annual_income", Double.parseDouble(excel.getCellDataByTestCaseIDSafe(tcId, "annual_income")));
        loanBody.put("occupation", excel.getCellDataByTestCaseIDSafe(tcId, "Occupation"));
        loanBody.put("existing_liabilities", Double.parseDouble(excel.getCellDataByTestCaseIDSafe(tcId, "existing_liabilities")));
        loanBody.put("linked_account_id", accountId);

        System.out.println("Create Loan Request:");
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

        System.out.println("Create Loan Response:");
        System.out.println(response.asPrettyString());

        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 201, "Loan creation failed");
        Assert.assertEquals(response.jsonPath().getBoolean("success"), true);

        loanId = response.jsonPath().getString("data.loan_id");
        Assert.assertNotNull(loanId, "Loan ID not found after loan creation");

        LoanTestContext.addLoanId(loanId);

        fetchMonthlyEmiFromLoanDetails();
    }

    public void fetchMonthlyEmiFromLoanDetails() throws IOException {

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

        System.out.println("Loan Details Response:");
        System.out.println(loanDetailsResponse.asPrettyString());

        Assert.assertEquals(loanDetailsResponse.getStatusCode(), 200);

        String emiValue = loanDetailsResponse.jsonPath().getString("data.monthly_emi");

        Assert.assertNotNull(emiValue, "Monthly EMI not found in loan details");

        monthlyEmi = Double.parseDouble(emiValue);

        if (Double.isNaN(monthlyEmi) || Double.isInfinite(monthlyEmi) || monthlyEmi <= 0) {
            throw new RuntimeException("Invalid monthly EMI received from backend: " + monthlyEmi);
        }

        System.out.println("Fetched Monthly EMI: " + monthlyEmi);
    }

    @When("User pays EMI for {string}")
    public void user_pays_emi_for(String tcId) throws IOException {

        int installmentsToPay = Integer.parseInt(
                excel.getCellDataByTestCaseIDSafe(tcId, "installments_to_pay")
        );

        String paymentAmountFromExcel =
                excel.getCellDataByTestCaseIDSafe(tcId, "payment_amount").trim();

        double finalPaymentAmount;

        if (paymentAmountFromExcel.equalsIgnoreCase("BELOW_EMI")) {
            finalPaymentAmount = monthlyEmi - 1;
        } else if (paymentAmountFromExcel.equalsIgnoreCase("LESS_THAN_TOTAL")) {
            finalPaymentAmount = (monthlyEmi * installmentsToPay) - 1;
        } else if (paymentAmountFromExcel.equalsIgnoreCase("NEGATIVE")) {
            finalPaymentAmount = -monthlyEmi;
        } else if (!paymentAmountFromExcel.isEmpty()) {
            finalPaymentAmount = Double.parseDouble(paymentAmountFromExcel);
        } else {
            finalPaymentAmount = monthlyEmi * installmentsToPay;

            if (installmentsToPay > 1) {
                double advancePrincipal = fetchAdvancePrincipalFromSchedule(installmentsToPay);
                double prepaymentPenalty = advancePrincipal * 0.02;

                finalPaymentAmount = finalPaymentAmount + prepaymentPenalty;
                finalPaymentAmount = Math.round(finalPaymentAmount * 100.0) / 100.0;

                System.out.println("Advance Principal: " + advancePrincipal);
                System.out.println("Prepayment Penalty 2%: " + prepaymentPenalty);
                System.out.println("Final Advance EMI Amount: " + finalPaymentAmount);
            }
        }

        if (Double.isNaN(finalPaymentAmount) || Double.isInfinite(finalPaymentAmount)) {
            throw new RuntimeException("Invalid payment amount generated: " + finalPaymentAmount);
        }

        JSONObject paymentBody = new JSONObject();

        paymentBody.put("loan_id", loanId);
        paymentBody.put("source_account_id", accountId);
        paymentBody.put("payment_amount", finalPaymentAmount);
        paymentBody.put("installments_to_pay", installmentsToPay);

        System.out.println("EMI Payment Request:");
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

        System.out.println("EMI Payment Response:");
        System.out.println(response.asPrettyString());
    }

    public double fetchAdvancePrincipalFromSchedule(int installmentsToPay) throws IOException {

        Response scheduleResponse = ApiSessionManager.executeWithRefresh(() ->
                given()
                        .cookies(getSafeCookies())
                        .header("Content-Type", "application/json")
                        .when()
                        .get("/loans/schedule/" + loanId)
                        .then()
                        .extract()
                        .response()
        );

        System.out.println("EMI Schedule Response:");
        System.out.println(scheduleResponse.asPrettyString());

        Assert.assertEquals(scheduleResponse.getStatusCode(), 200);

        double advancePrincipal = 0;

        for (int i = 0; i < installmentsToPay; i++) {
            String principal = scheduleResponse.jsonPath()
                    .getString("data[" + i + "].principal_component");

            Assert.assertNotNull(principal, "Principal component not found for installment: " + (i + 1));

            double principalValue = Double.parseDouble(principal);

            if (Double.isNaN(principalValue) || Double.isInfinite(principalValue)) {
                throw new RuntimeException("Invalid principal component received: " + principalValue);
            }

            advancePrincipal = advancePrincipal + principalValue;
        }

        return Math.round(advancePrincipal * 100.0) / 100.0;
    }

    @Then("EMI payment for {string} should be validated")
    public void emi_payment_for_should_be_validated(String tcId) {

        int expectedStatus = Integer.parseInt(
                excel.getCellDataByTestCaseIDSafe(tcId, "Expected_Status_Code")
        );

        String expectedSuccess = excel.getCellDataByTestCaseIDSafe(tcId, "Expected_Success")
                .trim()
                .toLowerCase();

        String expectedMessage = excel.getCellDataByTestCaseIDSafe(tcId, "Expected_Message")
                .trim()
                .toLowerCase();

        Assert.assertEquals(response.getStatusCode(), expectedStatus, "Status code mismatch for " + tcId);

        Boolean actualSuccess = response.jsonPath().getBoolean("success");

        Assert.assertEquals(
                String.valueOf(actualSuccess).toLowerCase(),
                expectedSuccess,
                "Success mismatch for " + tcId
        );

        if (!expectedMessage.isEmpty()) {
            Assert.assertTrue(
                    response.asString().toLowerCase().contains(expectedMessage),
                    "Expected message not found for " + tcId
            );
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