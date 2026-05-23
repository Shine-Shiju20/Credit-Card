package api.creditcard;

import io.restassured.response.Response;
import utils.ScenarioContext;
import utils.LoggerUtility;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

public class RuntimeCardManager {
    private static final Logger logger = LoggerUtility.getLogger(RuntimeCardManager.class);


    private static final String CARDS_BASE = "/credit-cards";

    public static void applyCard(String cardTier, double requestedLimit, double existingLiabilities) {
        ScenarioContext ctx = ScenarioContext.get();

        String body = "{"
                + "\"source_account_id\":\"" + ctx.getRuntimeAccountId() + "\","
                + "\"requested_limit\":" + requestedLimit + ","
                + "\"card_tier\":\"" + cardTier + "\","
                + "\"existing_liabilities\":" + existingLiabilities
                + "}";

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .body(body)
                .post(CARDS_BASE + "/apply");

        if (response.statusCode() != 201) {
            throw new RuntimeException("Card apply failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        ctx.setRuntimeCardId(response.jsonPath().getString("data.card_id"));
        ctx.setRuntimeCardNumber(response.jsonPath().getString("data.card_number"));
        ctx.setRuntimeCreditLimit(response.jsonPath().getDouble("data.credit_limit"));
        ctx.setRuntimeAvailableLimit(response.jsonPath().getDouble("data.available_limit"));
        ctx.setRuntimeOutstandingBalance(0);

        logger.info("Runtime card created: " + ctx.getRuntimeCardId());
    }

    public static void makePurchase(double amount, String merchant, String category) {
        ScenarioContext ctx;
        ctx = ScenarioContext.get();

        String body = "{"
                + "\"card_id\":\"" + ctx.getRuntimeCardId() + "\","
                + "\"amount\":" + amount + ","
                + "\"merchant\":\"" + merchant + "\","
                + "\"category\":\"" + category + "\""
                + "}";

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .body(body)
                .post(CARDS_BASE + "/purchase");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Purchase failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        ctx.setRuntimeAvailableLimit(response.jsonPath().getDouble("data.remaining_limit"));
        ctx.setRuntimeOutstandingBalance(response.jsonPath().getDouble("data.outstanding_balance"));
    }

    public static void repayFull() {
        ScenarioContext ctx = ScenarioContext.get();

        Response cardResponse = given()
                .cookie("access_token", ctx.getAccessToken())
                .get(CARDS_BASE + "/" + ctx.getRuntimeCardId());

        if (cardResponse.statusCode() != 200) {
            logger.info("Skipping repayment. Card not found or unauthorized.");
            return;
        }

        double outstanding =
                cardResponse.jsonPath().getDouble("data.outstandingBalance");

        if (outstanding <= 0) {
            logger.info("No outstanding balance to repay.");
            return;
        }

        String body = "{"
                + "\"card_id\":\"" + ctx.getRuntimeCardId() + "\","
                + "\"payment_amount\":" + outstanding + ","
                + "\"amount\":" + outstanding
                + "}";

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .body(body)
                .post(CARDS_BASE + "/payment");

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Repayment failed | status="
                            + response.statusCode()
                            + " | body="
                            + response.getBody().asString()
            );
        }

        ctx.setRuntimeOutstandingBalance(0);

        logger.info("Full repayment done for card: "
                + ctx.getRuntimeCardId());
    }

    public static void closeCard() {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .patch(CARDS_BASE + "/close/" + ctx.getRuntimeCardId());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Card close failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        logger.info("Card closed: " + ctx.getRuntimeCardId());
    }

    public static void deleteCard() {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .cookie("access_token", ctx.getAccessToken())
                .delete(CARDS_BASE + "/" + ctx.getRuntimeCardId());

        if (response.statusCode() != 200 && response.statusCode() != 204) {
            throw new RuntimeException("Card delete failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        logger.info("Card deleted: " + ctx.getRuntimeCardId());
    }
}