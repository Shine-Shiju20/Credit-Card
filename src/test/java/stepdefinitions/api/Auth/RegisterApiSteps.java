package stepdefinitions.api.Auth;

import api.Auth.RequestHelper;
import api.Auth.Routes;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import stepdefinitions.api.Auth.APIContext;

public class RegisterApiSteps {

    RequestHelper requestHelper =
            new RequestHelper();

    HashMap<String, Object> payload;

    public RegisterApiSteps()
            throws IOException {
    }

    // ==========================================
    // SET REGISTER ENDPOINT
    // ==========================================

    @Given("User has registration API endpoint")
    public void user_has_registration_api_endpoint() {

    }

    // ==========================================
    // SET CONTENT TYPE
    // ==========================================

    @And("Request content type is JSON")
    public void request_content_type_is_json() {

    }

    // ==========================================
    // SEND REGISTER REQUEST
    // ==========================================

    @When("User sends registration request with following data")
    public void user_sends_registration_request_with_following_data(
            DataTable dataTable
    ) {

        Map<String, String> data =
                dataTable.asMap(
                        String.class,
                        String.class
                );

        payload =
                new HashMap<>();


        // ==========================================
        // AUTH OBJECT
        // ==========================================

        HashMap<String, Object> auth =
                new HashMap<>();

        auth.put(
                "email",
                data.get("email")
        );

        auth.put(
                "phone",
                data.get("phone")
        );

        auth.put(
                "password",
                data.get("password")
        );

        auth.put(
                "confirm_password",
                data.get("confirm_password")
        );

        auth.put(
                "transaction_pin",
                data.get("transaction_pin")
        );


        // ==========================================
        // USER OBJECT
        // ==========================================

        HashMap<String, Object> user =
                new HashMap<>();

        user.put(
                "full_name",
                data.get("full_name")
        );

        user.put(
                "dob",
                data.get("dob")
        );

        user.put(
                "gender",
                data.get("gender")
        );

        user.put(
                "address",
                data.get("address")
        );

        user.put(
                "aadhaar_number",
                data.get("aadhaar_number")
        );

        user.put(
                "pan_number",
                data.get("pan_number")
        );

        user.put(
                "occupation",
                data.get("occupation")
        );

        user.put(
                "annual_income",
                Integer.parseInt(
                        data.get("annual_income")
                )
        );


        // ==========================================
        // FINAL PAYLOAD
        // ==========================================

        payload.put(
                "auth",
                auth
        );

        payload.put(
                "user",
                user
        );


        APIContext.response =
                requestHelper.sendPostRequest(
                        Routes.REGISTER,
                        payload
                );

        APIContext.response.prettyPrint();
    }

    // ==========================================
    // STATUS CODE VALIDATION
    // ==========================================

    @Then("API should return status code {int}")
    public void api_should_return_status_code(
            int expectedStatusCode
    ) {

        int actualStatusCode =
                APIContext.response.statusCode();

        Assert.assertEquals(
                actualStatusCode,
                expectedStatusCode,
                "Incorrect response status code returned by API"
        );
    }

    // ==========================================
    // RESPONSE MESSAGE VALIDATION
    // ==========================================

    @And("response message should be {string}")
public void response_message_should_be(
        String expectedMessage
) {

    String actualMessage;

    // ==========================================
    // CHECK NORMAL MESSAGE
    // ==========================================

    actualMessage =
            APIContext.response
                    .jsonPath()
                    .getString("message");

    // ==========================================
    // IF MESSAGE IS NULL
    // CHECK ERRORS ARRAY
    // ==========================================

    if (actualMessage == null) {

        actualMessage =
                APIContext.response
                        .jsonPath()
                        .getString("errors[0]");
    }

    System.out.println("Actual Response Message : " + actualMessage
    );

    Assert.assertEquals(actualMessage, expectedMessage, "Response message validation failed");
}
}