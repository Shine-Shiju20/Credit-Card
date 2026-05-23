package utils.Loan;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import utils.Loan.ConfigReader;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

import static io.restassured.RestAssured.given;

public class ApiSessionManager {

    private static Map<String, String> cookies;
    private static boolean loginPrinted = false;

    public static void loginIfNeeded() throws IOException {

        ConfigReader config = new ConfigReader();
        RestAssured.baseURI = config.getProperty("baseUrl");

        if (cookies != null && !cookies.isEmpty()) {
            return;
        }

        JSONObject loginBody = new JSONObject();
        loginBody.put("email", config.getProperty("email"));
        loginBody.put("password", config.getProperty("password"));

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(loginBody.toString())
                        .when()
                        .post(config.getProperty("loginEndpoint"))
                        .then()
                        .extract()
                        .response();

        if (!loginPrinted) {
            System.out.println("Login Response:");
            System.out.println(response.asPrettyString());
            loginPrinted = true;
        }

        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
        Assert.assertEquals(response.jsonPath().getBoolean("success"), true, "Login success mismatch");

        cookies = response.getCookies();
        Assert.assertFalse(cookies.isEmpty(), "Cookies not found after login");
    }

    public static Map<String, String> getCookies() throws IOException {
        loginIfNeeded();
        return cookies;
    }

    public static Response executeWithRefresh(Supplier<Response> requestSupplier) throws IOException {

        loginIfNeeded();

        Response response = requestSupplier.get();

        if (response.getStatusCode() == 401 ||
                response.asString().toLowerCase().contains("token") ||
                response.asString().toLowerCase().contains("session")) {

            System.out.println("Session expired. Re-login started...");

            cookies = null;
            loginIfNeeded();

            response = requestSupplier.get();
        }

        return response;
    }

    public static void resetSession() {
        cookies = null;
        loginPrinted = false;
    }
}