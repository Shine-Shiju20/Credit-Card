package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.Map;

public class OrphanCleanup {
    public static void main(String[] args) throws Exception {
        ConfigReader config = new ConfigReader();
        RestAssured.baseURI = config.getProp("APIUrl");
        String pin = config.getProp("transactionPin");

        Response loginResp = given()
                .contentType("application/json")
                .body("{\"email\":\"yilap59703@deapad.com\",\"password\":\"Kuttichatan@123\"}")
                .post("/auth/login");

        String token = loginResp.getCookie("access_token");
        System.out.println("Logged in.");

        Response accountsResp = given()
                .cookie("access_token", token)
                .get("/accounts/user/me");

        List<Map<String, Object>> accounts = accountsResp.jsonPath().getList("data");
        System.out.println("Total accounts: " + accounts.size());

        int savings = 0;
        for (Map<String, Object> acc : accounts) {
            if ("savings".equals(acc.get("account_type"))) savings++;
        }
        System.out.println("Savings accounts: " + savings);

        int deleted = 0;
        int target = savings - 1;
        for (int i = 0; i < accounts.size() && deleted < target; i++) {
            Map<String, Object> acc = accounts.get(i);
            if (!"savings".equals(acc.get("account_type"))) continue;

            String id = (String) acc.get("account_id");
            String accountNumber = (String) acc.get("account_number");
            String balance = String.valueOf(acc.get("balance"));
            double bal = Double.parseDouble(balance);

            if (bal > 0) {
                Response withdraw = given()
                        .contentType("application/json")
                        .cookie("access_token", token)
                        .body("{\"account_number\":\"" + accountNumber
                                + "\",\"amount\":" + bal
                                + ",\"transaction_pin\":\"" + pin + "\"}")
                        .post("/transactions/withdraw");
                System.out.println("WITHDRAW " + id + " amount=" + bal
                        + " -> " + withdraw.statusCode()
                        + " | " + withdraw.getBody().asString());
            }

            Response del = given()
                    .cookie("access_token", token)
                    .delete("/accounts/" + id);
            System.out.println("DELETE " + id + " -> " + del.statusCode()
                    + " | " + del.getBody().asString());
            if (del.statusCode() == 200 || del.statusCode() == 204) deleted++;
        }

        System.out.println("Deleted " + deleted + " savings accounts. Done.");
    }
}