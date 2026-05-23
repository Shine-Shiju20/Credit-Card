package stepdefinitions.api.Transaction;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TransactionApiSteps {

    private Response response;
    private Map<String, String> cookies;

    // =========================================================
    // Base URI
    // =========================================================
    @Given("the API base URI is {string}")
    public void the_api_base_uri_is(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    // =========================================================
    // Login
    // =========================================================
    @Given("the user authenticates with email {string} and password {string}")
    public void the_user_authenticates_with_email_and_password(
            String email,
            String password) {

        String loginPayload =
                "{"
                + "\"email\":\"" + email + "\","
                + "\"password\":\"" + password + "\""
                + "}";

        response =
                given()
                    .contentType("application/json")
                    .body(loginPayload)
                    .log().all()
                .when()
                    .post("/auth/login")
                .then()
                    .log().all()
                    .extract()
                    .response();

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Login API failed."
        );

        Assert.assertTrue(
                response.jsonPath().getBoolean("success"),
                "Login was not successful."
        );

        // Capture all cookies from login response
        cookies = response.getCookies();

        System.out.println("Captured Cookies: " + cookies);
    }

    // =========================================================
    // Clear Authentication
    // =========================================================
    @Given("the authorization token is cleared")
    public void the_authorization_token_is_cleared() {
        cookies = null;
    }

    // =========================================================
    // POST Request
    // =========================================================
    @When("the user sends a POST request to {string} with JSON:")
    public void the_user_sends_a_post_request_to_with_json(
            String endpoint,
            String jsonBody) {

        if (cookies != null && !cookies.isEmpty()) {
            response =
                    given()
                        .cookies(cookies)
                        .contentType("application/json")
                        .body(jsonBody)
                        .log().all()
                    .when()
                        .post(endpoint)
                    .then()
                        .log().all()
                        .extract()
                        .response();
        } else {
            response =
                    given()
                        .contentType("application/json")
                        .body(jsonBody)
                        .log().all()
                    .when()
                        .post(endpoint)
                    .then()
                        .log().all()
                        .extract()
                        .response();
        }
    }

    // =========================================================
    // Status Code Validation
    // =========================================================
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(
            Integer expectedStatusCode) {

        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatusCode.intValue(),
                "Unexpected response status code."
        );
    }

    // =========================================================
    // JSON Boolean Validation
    // =========================================================
    @Then("the response JSON path {string} should be true")
    public void the_response_json_path_should_be_true(
            String jsonPath) {

        Assert.assertTrue(
                response.jsonPath().getBoolean(jsonPath)
        );
    }

    @Then("the response JSON path {string} should be false")
    public void the_response_json_path_should_be_false(
            String jsonPath) {

        Assert.assertFalse(
                response.jsonPath().getBoolean(jsonPath)
        );
    }
}