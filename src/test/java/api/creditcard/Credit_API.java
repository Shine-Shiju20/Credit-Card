package api.creditcard;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;
import utils.LoggerUtility;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class Credit_API {
    private static final Logger logger =
            LoggerUtility.getLogger(Credit_API.class);

    private final RequestSpecification requestSpecification;

    public Credit_API() {

        try {

            ConfigReader configReader = new ConfigReader();

            RestAssured.baseURI = configReader.getProp("APIUrl");

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to load config.properties: "
                            + e.getMessage()
            );
        }

        this.requestSpecification = RestAssured.given()
                .header("Content-Type", "application/json");
    }

    private RequestSpecification addAuthHeader(String authToken) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .cookie("access_token", authToken);
    }

    private RequestSpecification addRequestBody(
            RequestSpecification request,
            Map<String, Object> payload
    ) {

        return request.body(payload);
    }

    public Response applyCreditCard(
            String authToken,
            Map<String, Object> payload
    ) {

        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/credit-cards/apply");
    }

    public Response getCreditCardById(
            String authToken,
            String cardId
    ) {

        return addAuthHeader(authToken)
                .get("/credit-cards/" + cardId);
    }

    public Response getUserCreditCards(
            String authToken,
            String userId
    ) {

        return addAuthHeader(authToken)
                .get("/credit-cards/user/me");
    }

    public Response purchaseTransaction(
            String authToken,
            Map<String, Object> payload
    ) {

        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/credit-cards/purchase");
    }

    public Response repayCreditCardBalance(
            String authToken,
            Map<String, Object> payload
    ) {

        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/credit-cards/payment");
    }

    public Response blockCreditCard(
            String authToken,
            Map<String, Object> payload
    ) {

        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/credit-cards/block");
    }

    public Response closeCreditCard(
            String authToken,
            String cardId,
            Map<String, Object> payload
    ) {

        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).patch("/credit-cards/close/" + cardId);
    }

    public Response deleteCreditCard(
            String authToken,
            String cardId
    ) {

        return addAuthHeader(authToken)
                .delete("/credit-cards/" + cardId);
    }

    public Response getCardStatement(
            String authToken,
            String cardId
    ) {

        return addAuthHeader(authToken)
                .get("/credit-cards/statements/" + cardId);
    }

    public Response downloadStatementPdf(
            String authToken,
            String cardId
    ) {

        return addAuthHeader(authToken)
                .get("/credit-cards/statements/" + cardId + "/pdf");
    }

    public Response getRepaymentHistory(
            String authToken,
            String cardId
    ) {

        return addAuthHeader(authToken)
                .get("/credit-cards/repayments/" + cardId);
    }

    public Response getUserAccounts(String authToken) {

        return addAuthHeader(authToken)
                .get("/accounts/user/me");
    }

    public Response getCurrentUserProfile(String authToken) {

        return addAuthHeader(authToken)
                .get("/user/me");
    }

    // TODO: Confirm with backend whether payment callback
    // endpoint requires authorization header
    public Response paymentCallback(
            Map<String, Object> payload
    ) {

        return addRequestBody(
                requestSpecification,
                payload
        ).post("/payment/callback");
    }

    // TODO: Actual scheduler endpoint must be confirmed with backend team
    public Response triggerBillingScheduler(
            String authToken,
            Map<String, Object> payload
    ) {

        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/billing/scheduler/trigger");
    }

    // TODO: Actual penalty scheduler endpoint must be confirmed with backend team
    public Response triggerLatePenaltyScheduler(
            String authToken,
            Map<String, Object> payload
    ) {

        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/billing/penalty/trigger");
    }

    // TODO: Actual reconciliation endpoint must be confirmed with backend team
    public Response triggerPaymentReconciliation(
            String authToken,
            Map<String, Object> payload
    ) {

        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/payment/reconciliation/trigger");
    }

    // TODO: Actual notification service endpoint must be confirmed with backend team
    public Response triggerNotificationService(
            String authToken,
            Map<String, Object> payload
    ) {

        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/notification/trigger");
    }

    // TODO: Actual audit logging endpoint must be confirmed with backend team
    public Response triggerAuditLoggingService(
            String authToken,
            Map<String, Object> payload
    ) {

        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/audit/logs/trigger");
    }

    private String accessToken;
    private String loggedInUserId;

    public String login(String email, String password) {
        logger.info("Attempting login for user: {}", email);
        String body = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";

        Response loginResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/auth/login");

        System.out.println("Login Response: " + loginResponse.getBody().asString());
        if (loginResponse.getStatusCode() == 200 || loginResponse.getStatusCode() == 201) {
            logger.info("Login successful for user: {}", email);
        } else {
            logger.error("Login failed for user: {}", email);
        }
        accessToken = loginResponse.getCookie("access_token");
        loggedInUserId = loginResponse.jsonPath().getString("user.user_id"); // ← extract user_id
        return accessToken;
    }

    public Response createAccount(
            String authToken,
            Map<String, Object> payload
    ) {
        Response response = addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/accounts");
        logger.info("Runtime account created");
        return response;
    }

    public Response withdrawMoney(
            String authToken,
            Map<String, Object> payload
    ) {
        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/transactions/withdraw");
    }

    public Response deleteAccount(
            String authToken,
            String accountId
    ) {
        logger.info("Deleting runtime account");
        return addAuthHeader(authToken)
                .delete("/accounts/" + accountId);
    }

    public Response createRuntimeCard(
            String authToken,
            Map<String, Object> payload
    ) {
        Response response = addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/credit-cards/apply");
        logger.info("Runtime card created successfully");
        return response;
    }

    public Response repayCreditCard(
            String authToken,
            Map<String, Object> payload
    ) {
        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).post("/credit-cards/payment");
    }

    public Response closeRuntimeCard(
            String authToken,
            String cardId,
            Map<String, Object> payload
    ) {
        return addRequestBody(
                addAuthHeader(authToken),
                payload
        ).patch("/credit-cards/close/" + cardId);
    }

    public Response deleteRuntimeCard(
            String authToken,
            String cardId
    ) {
        logger.info("Deleting runtime card");
        return addAuthHeader(authToken)
                .delete("/credit-cards/" + cardId);
    }

    public String getLoggedInUserId() {
        return loggedInUserId;
    }
}