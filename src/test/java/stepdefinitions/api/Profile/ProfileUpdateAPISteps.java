package stepdefinitions.api.Profile;

import api.Common.AuthHelper;
import api.Profile.RequestHelper;

import api.Profile.Routes;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;

import org.openqa.selenium.remote.http.Route;
import org.testng.Assert;

import utils.LoggerUtility;

import java.io.IOException;
import java.util.HashMap;

public class ProfileUpdateAPISteps {


    RequestHelper requestHelper = new RequestHelper();

    HashMap<String, Object> payload;
    AuthHelper auth = new AuthHelper();

    public ProfileUpdateAPISteps()
            throws IOException {
    }



    @When("User sends PUT request to {string} with valid profile details")
    public void user_sends_put_request_to_with_valid_profile_details(String endpoint) {

        LoggerUtility.info("Preparing valid profile update payload");

        payload =
                new HashMap<>();

        payload.put(
                "full_name",
                "Abhiram PB"
        );
        payload.put(
                "phone",
                "7337695314"
        );
        payload.put(
                "address",
                "Bangalore Karnataka 560064"
        );
        payload.put(
                "occupation",
                "Software Engineer"
        );

        payload.put(
                "annual_income",
                500000
        );

        LoggerUtility.info(
                "Sending PUT request to endpoint: "
                        + endpoint
        );


        APIContext.response =
                requestHelper.sendPutRequest(
                        endpoint,
                        payload,
                        auth.GetToken()
                );

        LoggerUtility.info(
                "PUT request executed successfully"
        );
//        response.prettyPrint();
    }




    // =========================
    // INVALID FULL NAME
    // =========================

    @When("User sends PUT request with full_name {string}")
    public void user_sends_put_request_with_full_name(
            String fullName
    ) {
        LoggerUtility.info(
                "Testing invalid full_name value: "
                        + fullName
        );
        payload =
                new HashMap<>();


        payload.put(
                "full_name",
                fullName
        );



        APIContext.response =
                requestHelper.sendPutRequest(
                        "/user/me",
                        payload,
                        auth.GetToken()
                );


        LoggerUtility.info(
                "PUT request with invalid full_name executed"
        );

//        response.prettyPrint();
    }




    @When("User sends PUT request with phone {string}")
    public void user_sends_put_request_with_phone(
            String phone
    ) {

        LoggerUtility.info(
                "Testing invalid phone value: "
                        + phone
        );


        payload =
                new HashMap<>();


        payload.put(
                "phone",
                phone
        );



        APIContext.response =
                requestHelper.sendPutRequest(
                        "/user/me",
                        payload,
                        auth.GetToken()
                );


        LoggerUtility.info(
                "PUT request with invalid phone executed"
        );

//        response.prettyPrint();
    }




    // =========================
    // INVALID ADDRESS
    // =========================

    @When("User sends PUT request with address {string}")
    public void user_sends_put_request_with_address(
            String address
    ) {

        LoggerUtility.info(
                "Testing invalid address value: "
                        + address
        );


        payload =
                new HashMap<>();


        payload.put(
                "address",
                address
        );



        APIContext.response =
                requestHelper.sendPutRequest(
                        Routes.GET_USER,
                        payload,
                        auth.GetToken()
                );


        LoggerUtility.info(
                "PUT request with invalid address executed"
        );

//        response.prettyPrint();
    }




    // =========================
    // STATUS CODE VALIDATION
    // =========================

    @Then("Response status code should be {int}")
    public void response_status_code_should_be(
            int expectedStatusCode
    ) {

        int actualStatusCode =
                APIContext.response.statusCode();


        LoggerUtility.info(
                "Validating response status code"
        );


        Assert.assertEquals(
                actualStatusCode,
                expectedStatusCode,
                "Incorrect response status code returned by API"
        );


        LoggerUtility.info(
                "Status code validation passed"
        );
    }




    // =========================
    // RESPONSE MESSAGE VALIDATION
    // =========================

    @And("Response message should be {string}")
    public void response_message_should_be(
            String expectedMessage
    ) {

        String actualMessage =
                APIContext.response.jsonPath()
                        .getString("message");


        LoggerUtility.info(
                "Validating API response message"
        );


        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "API response message validation failed"
        );


        LoggerUtility.info(
                "Response message validation passed"
        );
    }




    // =========================
    // GENERIC VALIDATION MESSAGE
    // =========================

    @Then("Response should match expected validation {string}")
    public void response_should_match_expected_validation(
            String expectedMessage
    ) {

        String actualMessage =
                APIContext.response.jsonPath()
                        .getString("message");


        LoggerUtility.info(
                "Validating expected validation message"
        );


        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Validation message mismatch"
        );


        LoggerUtility.info(
                "Validation message assertion passed"
        );
    }
}