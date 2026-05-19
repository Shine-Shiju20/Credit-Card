package api.Account;

import io.restassured.response.Response;
import utils.ScenarioContext;
import utils.LoggerUtility;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

public class RuntimeAccountManager {
    private static final Logger logger = LoggerUtility.getLogger(RuntimeAccountManager.class);

    private static final String ACCOUNTS_BASE = "/accounts";

    public static void createAccount() {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .body("{\"account_type\":\"savings\",\"initial_deposit\":100000}")
                .post(ACCOUNTS_BASE);

        if (response.statusCode() != 201) {
            throw new RuntimeException("Account creation failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        ctx.setRuntimeAccountId(response.jsonPath().getString("data.account_id"));
        ctx.setRuntimeAccountNumber(response.jsonPath().getString("data.account_number"));
        ctx.setRuntimeAccountBalance(response.jsonPath().getDouble("data.balance"));

        logger.info("Runtime account created: " + ctx.getRuntimeAccountId());
    }

    public static void createAccount(double initialDeposit) {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .body(String.format("{\"account_type\":\"savings\",\"initial_deposit\":%.2f}", initialDeposit))
                .post(ACCOUNTS_BASE);

        if (response.statusCode() != 201) {
            throw new RuntimeException("Account creation failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        ctx.setRuntimeAccountId(response.jsonPath().getString("data.account_id"));
        ctx.setRuntimeAccountNumber(response.jsonPath().getString("data.account_number"));
        ctx.setRuntimeAccountBalance(response.jsonPath().getDouble("data.balance"));

        logger.info("Runtime account created: " + ctx.getRuntimeAccountId() + " with custom initial deposit: " + initialDeposit);
    }

    public static double fetchBalance() {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .cookie("access_token", ctx.getAccessToken())
                .get(ACCOUNTS_BASE + "/" + ctx.getRuntimeAccountId());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Fetch balance failed | status=" + response.statusCode());
        }

        double balance = response.jsonPath().getDouble("data.balance");
        ctx.setRuntimeAccountBalance(balance);
        return balance;
    }

    public static void withdrawBalance(double amount) {
        ScenarioContext ctx = ScenarioContext.get();

        String pin;
        try {
            pin = new utils.ConfigReader().getProp("transactionPin");
        } catch (Exception e) {
            throw new RuntimeException("transactionPin missing from config.properties", e);
        }

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                // Only this method — change account_id → account_number in body only
                .body(String.format(
                        "{\"account_number\":\"%s\",\"amount\":%s,\"transaction_pin\":\"%s\"}",
                        ctx.getRuntimeAccountNumber(), amount, pin))
                .post("/transactions/withdraw");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Withdraw failed | amount=" + amount
                    + " | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        logger.info("Withdrawn " + amount + " from account " + ctx.getRuntimeAccountId());
    }

    public static void deleteAccount() {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .cookie("access_token", ctx.getAccessToken())
                .delete(ACCOUNTS_BASE + "/" + ctx.getRuntimeAccountId());

        if (response.statusCode() != 200 && response.statusCode() != 204) {
            throw new RuntimeException("Account deletion failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        logger.info("Runtime account deleted: " + ctx.getRuntimeAccountId());
    }
}