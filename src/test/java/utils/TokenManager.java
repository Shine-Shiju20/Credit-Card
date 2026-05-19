package utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

public class TokenManager {
    private static final Logger logger = LoggerUtility.getLogger(TokenManager.class);

    private static final ThreadLocal<String>  cachedToken     = new ThreadLocal<>();
    private static final ThreadLocal<Long>    tokenTimestamp  = new ThreadLocal<>();
    private static final long                 TOKEN_TTL_MS    = 55 * 60 * 1000; // 55 min

    private TokenManager() {}

    public static String getToken(String email, String password) {
        if (isTokenValid()) {
            logger.info("Using cached token");
            return cachedToken.get();
        }
        return login(email, password);
    }

    public static void login() {
        // Default login for hooks
        getToken("yilap59703@deapad.com", "Kuttichatan@123");
    }

    public static String getToken() {
        ScenarioContext ctx = ScenarioContext.get();
        if (ctx.getAccessToken() != null && isTokenValid()) {
            return ctx.getAccessToken();
        }
        throw new IllegalStateException("No token in context. Call getToken(email, password) first.");
    }

    public static void invalidate() {
        cachedToken.remove();
        tokenTimestamp.remove();
    }

    private static boolean isTokenValid() {
        Long ts = tokenTimestamp.get();
        return cachedToken.get() != null
                && ts != null
                && (System.currentTimeMillis() - ts) < TOKEN_TTL_MS;
    }

    private static String login(String email, String password) {
        try {
            utils.ConfigReader config = new utils.ConfigReader();
            io.restassured.RestAssured.baseURI = config.getProp("APIUrl");
        } catch (java.io.IOException e) {
            logger.error("Failed to load base URI from config", e);
        }

        Response response = given()
                .contentType("application/json")
                .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                .post("/auth/login");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Login failed for " + email
                    + " | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        String token = response.getCookie("access_token");
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Login succeeded but no access_token cookie received for " + email);
        }
        String userId = response.jsonPath().getString("user.user_id");

        cachedToken.set(token);
        tokenTimestamp.set(System.currentTimeMillis());

        ScenarioContext ctx = ScenarioContext.get();
        ctx.setAccessToken(token);
        ctx.setLoggedInUserId(userId);
        ctx.setLoggedInEmail(email);

        return token;
    }
}