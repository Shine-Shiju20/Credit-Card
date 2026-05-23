package stepdefinitions.api.Profile;


import api.Common.AuthHelper;

import api.Profile.RequestHelper;

import api.Profile.Routes;
import io.cucumber.java.en.When;

import stepdefinitions.api.Profile.APIContext;
import utils.LoggerUtility;

import java.io.IOException;
import java.util.HashMap;

public class TransactionPinAPISteps {


    RequestHelper requestHelper =
            new RequestHelper();


    HashMap<String, Object> payload;

    AuthHelper auth =
            new AuthHelper();



    public TransactionPinAPISteps()
            throws IOException {

    }



    // =========================
    // VALID PIN RESET
    // =========================

    @When("User sends POST request to {string} with valid PIN details")
    public void user_sends_post_request_to_with_valid_pin_details(
            String endpoint
    ) {

        LoggerUtility.info(
                "Sending valid transaction PIN reset request"
        );


        payload =
                new HashMap<>();


        payload.put(
                "password",
                "Strong@123"
        );



        payload.put(
                "newPin",
                "1290"
        );



        APIContext.response =
                requestHelper.sendPostRequest(
                        endpoint,
                        payload,
                        auth.GetToken()
                );


        LoggerUtility.info(
                "Valid transaction PIN reset request executed"
        );

        APIContext.response.prettyPrint();
    }




    // =========================
    // INVALID PASSWORD
    // =========================

    @When("User sends POST request with invalid account password")
    public void user_sends_post_request_with_invalid_account_password() {

        LoggerUtility.info(
                "Sending transaction PIN reset request with invalid password"
        );


        payload =
                new HashMap<>();


        payload.put(
                "password",
                "Wrong@123"
        );



        payload.put(
                "newPin",
                "1290"
        );



        APIContext.response =
                requestHelper.sendPostRequest(
                        Routes.RESET_TRANSACTION_PIN,
                        payload,
                        auth.GetToken()
                );


        LoggerUtility.info(
                "Invalid password transaction PIN request executed"
        );

        APIContext.response.prettyPrint();
    }




    // =========================
    // WEAK PIN
    // =========================

    @When("User sends POST request with weak transaction PIN")
    public void user_sends_post_request_with_weak_transaction_pin() {

        LoggerUtility.info(
                "Sending transaction PIN reset request with weak PIN"
        );


        payload =
                new HashMap<>();


        payload.put(
                "password",
                "Strong@123"
        );



        payload.put(
                "newPin",
                "1111"
        );



        APIContext.response =
                requestHelper.sendPostRequest(
                        Routes.RESET_TRANSACTION_PIN,
                        payload,
                        auth.GetToken()
                );


        LoggerUtility.info(
                "Weak transaction PIN request executed"
        );

        APIContext.response.prettyPrint();
    }




    // =========================
    // INVALID PIN VALIDATION
    // =========================

    @When("User sends POST request with transaction PIN {string}")
    public void user_sends_post_request_with_transaction_pin(
            String pin
    ) {

        LoggerUtility.info(
                "Sending transaction PIN reset request with PIN: "
                        + pin
        );


        payload =
                new HashMap<>();


        payload.put(
                "password",
                "Strong@123"
        );



        payload.put(
                "newPin",
                pin
        );



        APIContext.response =
                requestHelper.sendPostRequest(
                        Routes.RESET_TRANSACTION_PIN,
                        payload,
                        auth.GetToken()
                );


        LoggerUtility.info(
                "Invalid transaction PIN validation request executed"
        );

//        APIContext.response.prettyPrint();
    }
}