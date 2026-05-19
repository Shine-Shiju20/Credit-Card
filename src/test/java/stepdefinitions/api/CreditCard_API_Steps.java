package stepdefinitions.api;

import api.creditcard.Credit_API;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.ExcelReader;
import utils.LoggerUtility;

import utils.ScenarioContext;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreditCard_API_Steps {
    private static final Logger logger = LoggerUtility.getLogger(CreditCard_API_Steps.class);

    private final Credit_API creditAPI = new Credit_API();

    private Map<String, String> testData;
    private Response response;

    @Given("test data is loaded for {string}")
    public void test_data_is_loaded_for(String testcaseId) throws IOException {
        logger.info("Loading test data for testcaseId: {}", testcaseId);

        ExcelReader excelReader = new ExcelReader(
                "src/test/resources/Data/Credit_Card_TestData_Final.xlsx",
                "Test_Data"
        );

        testData = excelReader.getData(testcaseId);

        String balanceStr = testData.get("Source_Account_Balance");
        if (balanceStr != null && !balanceStr.trim().isEmpty() && !balanceStr.equalsIgnoreCase("NaN") && !balanceStr.contains("|") && !balanceStr.contains(";")) {
            try {
                double targetBalance = Double.parseDouble(balanceStr.trim());
                double currentBalance = api.Account.RuntimeAccountManager.fetchBalance();
                if (currentBalance > targetBalance) {
                    double withdrawAmount = currentBalance - targetBalance;
                    api.Account.RuntimeAccountManager.withdrawBalance(withdrawAmount);
                    logger.info("Aligned runtime account balance: current={}, target={}, withdrawn={}",
                            currentBalance, targetBalance, withdrawAmount);
                } else if (currentBalance < targetBalance) {
                    api.Account.RuntimeAccountManager.deleteAccount();
                    api.Account.RuntimeAccountManager.createAccount(targetBalance);
                    logger.info("Recreated runtime account to align balance: current={}, target={}",
                            currentBalance, targetBalance);
                }
            } catch (Exception e) {
                logger.error("Failed to align runtime account balance", e);
            }
        }
    }

    private String getAuthToken() {
        String authState = testData.get("Auth_State");

        if ("logged_in".equalsIgnoreCase(authState)) {
            return utils.TokenManager.getToken(
                    testData.get("Login_Email"),
                    testData.get("Login_Password")
            );
        }
        return authState;
    }

    private Map<String, Object> buildApplicationPayload() {
        Map<String, Object> payload = new HashMap<>();

        String seedProfile = testData.get("Seed_Profile");
        if (seedProfile != null && !seedProfile.trim().isEmpty() && !seedProfile.equalsIgnoreCase("NaN")) {
            payload.put("userId", seedProfile.trim());
        } else {
            payload.put("userId", ScenarioContext.get().getLoggedInUserId());
        }
        String sourceAccId = testData.get("Source_Account_ID");
        if (sourceAccId == null || sourceAccId.trim().isEmpty() || sourceAccId.equalsIgnoreCase("NaN")) {
            String sourceAccStatus = testData.get("Source_Account_Status");
            if ("missing".equalsIgnoreCase(sourceAccStatus)) {
                sourceAccId = "";
            } else {
                sourceAccId = ScenarioContext.get().getRuntimeAccountId();
            }
        }
        payload.put("source_account_id", sourceAccId);
        payload.put("requested_limit", parseDouble(testData.get("Requested_Limit")));
        payload.put("card_tier", testData.get("Card_Tier"));
        payload.put("existing_liabilities", parseDouble(testData.get("Monthly_Liabilities")));

        return payload;
    }

    // Add these two helper methods to the class:
    private Double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        return Double.parseDouble(value.trim());
    }

    private Map<String, Object> buildPurchasePayload() {

        Map<String, Object> payload = new HashMap<>();

        payload.put("card_id", getCardId());
        payload.put("amount", parseDouble(testData.get("Amount")));
        payload.put("merchant", testData.get("Merchant"));
        payload.put("category", testData.get("Category"));

        return payload;
    }

    private Map<String, Object> buildRepaymentPayload() {

        Map<String, Object> payload = new HashMap<>();

        payload.put("card_id", getCardId());
        payload.put("amount", parseDouble(testData.get("Amount")));
        payload.put("minimumDue", parseDouble(testData.get("Minimum_Due")));
        payload.put("outstandingBalance", parseDouble(testData.get("Outstanding_Balance")));

        return payload;
    }

    private Map<String, Object> buildBlockPayload() {

        Map<String, Object> payload = new HashMap<>();

        payload.put("card_id", getCardId());
        payload.put("cardStatus", testData.get("Card_Status"));

        return payload;
    }

    private Map<String, Object> buildClosePayload() {

        Map<String, Object> payload = new HashMap<>();

        payload.put("outstandingBalance", parseDouble(testData.get("Outstanding_Balance")));
        payload.put("minimumDue", parseDouble(testData.get("Minimum_Due")));

        return payload;
    }

    private Map<String, Object> buildPaymentCallbackPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("card_id", ScenarioContext.get().getRuntimeCardId());
        payload.put("userId", testData.get("Seed_Profile"));
        payload.put("amount", testData.get("Amount"));
        payload.put("merchant", testData.get("Merchant"));
        return payload;
    }

    private Map<String, Object> buildSchedulerPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("card_id", ScenarioContext.get().getRuntimeCardId());
        payload.put("userId", testData.get("Seed_Profile"));
        payload.put("dueDate", testData.get("Due_Date"));
        return payload;
    }

    @When("user submits credit card application")
    public void user_submits_credit_card_application() {

        response = creditAPI.applyCreditCard(
                getAuthToken(),
                buildApplicationPayload()
        );
        System.out.println("Apply Response: " + response.getBody().asString());
    }

    @When("user fetches linked accounts")
    public void user_fetches_linked_accounts() {

        response = creditAPI.getUserAccounts(
                getAuthToken()
        );
    }

    @When("user fetches credit card details")
    public void user_fetches_credit_card_details() {

        response = creditAPI.getCreditCardById(
                getAuthToken(), getCardId()
        );
    }

    @When("user fetches user credit cards")
    public void user_fetches_user_credit_cards() {
        String userId = ScenarioContext.get().getLoggedInUserId();
        System.out.println("USER ID IN STEP DEF: [" + userId + "]");
        response = creditAPI.getUserCreditCards(
                getAuthToken(),
                userId
        );
    }

    @When("user performs purchase transaction")
    public void user_performs_purchase_transaction() {

        response = creditAPI.purchaseTransaction(
                getAuthToken(),
                buildPurchasePayload()
        );
        System.out.println("Purchase Response: " + response.getBody().asString());
    }

    @When("user performs repayment transaction")
    public void user_performs_repayment_transaction() {

        response = creditAPI.repayCreditCardBalance(
                getAuthToken(),
                buildRepaymentPayload()
        );
    }

    @When("user blocks the credit card")
    public void user_blocks_the_credit_card() {

        response = creditAPI.blockCreditCard(
                getAuthToken(),
                buildBlockPayload()
        );
    }

    @When("user closes the credit card")
    public void user_closes_the_credit_card() {

        response = creditAPI.closeCreditCard(
                getAuthToken(),
                getCardId(),
                buildClosePayload()
        );
    }

    @When("user deletes the credit card")
    public void user_deletes_the_credit_card() {

        response = creditAPI.deleteCreditCard(
                getAuthToken(),
                getCardId()
        );
    }

    @When("user fetches credit card statement")
    public void user_fetches_credit_card_statement() {

        response = creditAPI.getCardStatement(
                getAuthToken(),
                getCardId()
        );
    }

    @When("user downloads statement PDF")
    public void user_downloads_statement_pdf() {

        response = creditAPI.downloadStatementPdf(
                getAuthToken(),
                getCardId()
        );
    }

    @When("user fetches repayment history")
    public void user_fetches_repayment_history() {

        response = creditAPI.getRepaymentHistory(
                getAuthToken(),
                getCardId()
        );
    }

    @When("payment callback is triggered")
    public void payment_callback_is_triggered() {

        response = creditAPI.paymentCallback(
                buildPaymentCallbackPayload()
        );
    }

    @When("billing scheduler is triggered")
    public void billing_scheduler_is_triggered() {

        response = creditAPI.triggerBillingScheduler(
                getAuthToken(),
                buildSchedulerPayload()
        );
    }

    @When("late payment scheduler is triggered")
    public void late_payment_scheduler_is_triggered() {

        response = creditAPI.triggerLatePenaltyScheduler(
                getAuthToken(),
                buildSchedulerPayload()
        );
    }

    @When("payment reconciliation service is triggered")
    public void payment_reconciliation_service_is_triggered() {

        response = creditAPI.triggerPaymentReconciliation(
                getAuthToken(),
                buildSchedulerPayload()
        );
    }

    @When("notification service is triggered")
    public void notification_service_is_triggered() {

        response = creditAPI.triggerNotificationService(
                getAuthToken(),
                buildSchedulerPayload()
        );
    }

    @When("audit logging service is triggered")
    public void audit_logging_service_is_triggered() {

        response = creditAPI.triggerAuditLoggingService(
                getAuthToken(),
                buildSchedulerPayload()
        );
    }

    @Then("API response status code should be {int}")
    public void api_response_status_code_should_be(
            Integer expectedStatusCode
    ) {
        logger.info("Verifying API response status code is {}", expectedStatusCode);

        Assert.assertNotNull(
                response,
                "Response object is null"
        );

        if (response.getStatusCode() != expectedStatusCode) {
            System.out.println("TEST FAILURE DETAILS: Expected " + expectedStatusCode + " but got " + response.getStatusCode());
            System.out.println("RESPONSE BODY: " + response.getBody().asString());
        }

        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatusCode.intValue(),
                "Unexpected status code"
        );
    }

    @Then("response should contain generated card id")
    public void response_should_contain_generated_card_id() {
        Assert.assertNotNull(response, "Response object is null");

        String generatedCardId = response.jsonPath().getString("data.card_id");

        Assert.assertNotNull(generatedCardId, "Generated card id is null");
        Assert.assertFalse(generatedCardId.trim().isEmpty(), "Generated card id is empty");
    }

    @Then("response field {string} should equal {string}")
    public void response_field_should_equal(String fieldPath, String expectedValue) {
        Assert.assertNotNull(response, "Response object is null");

        String actual = response.jsonPath().getString(fieldPath);

        Assert.assertEquals(
                actual,
                expectedValue,
                "Field '" + fieldPath + "' mismatch"
        );
    }

    @Then("response message should contain {string}")
    public void response_message_should_contain(String expectedMessage) {
        Assert.assertNotNull(response, "Response object is null");

        String actual = response.jsonPath().getString("message");

        Assert.assertTrue(
            actual != null && actual.contains(expectedMessage),
            "Expected message to contain: '" + expectedMessage + "' but got: '" + actual + "'"
        );
    }

    private String getCardId() {
        return ScenarioContext.get().getRuntimeCardId();
    }
}
