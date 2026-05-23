package stepdefinitions.api.Profile;


import api.Common.AuthHelper;

import api.Profile.RequestHelper;

import api.Profile.Routes;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import utils.LoggerUtility;

import java.io.IOException;
import java.util.HashMap;

public class KYCAPISteps {


    RequestHelper requestHelper =
            new RequestHelper();

    HashMap<String, Object> payload;

    AuthHelper auth =
            new AuthHelper();



    public KYCAPISteps()
            throws IOException {

    }



    @Given("User has valid unverified KYC session cookie")
    public void user_has_valid_unverified_kyc_session_cookie()
            throws IOException {

        LoggerUtility.info(
                "Generating unverified KYC session cookie"
        );

        auth.getValidUnverifiedKycSessionCookie();
    }


    @Given("User has valid verified KYC session cookie")
    public void user_has_valid_verified_kyc_session_cookie()
            throws IOException {

        LoggerUtility.info(
                "Generating verified KYC session cookie"
        );

        auth.getValidSessionCookie();
    }

    // =========================
    // KYC REQUEST
    // =========================

    @When("User sends PATCH request with Aadhaar {string} and PAN {string}")
    public void user_sends_patch_request_with_aadhaar_and_pan(
            String aadhaar,
            String pan
    ) {

        LoggerUtility.info(
                "Sending PATCH request with Aadhaar and PAN"
        );


        payload =
                new HashMap<>();


        payload.put(
                "aadhaar_number",
                aadhaar
        );



        payload.put(
                "pan_number",
                pan
        );



        APIContext.response =
                requestHelper.sendPatchRequest(
                        Routes.UPDATE_KYC,
                        payload,
                        auth.GetUnverifiedToken()
                );


        LoggerUtility.info(
                "KYC PATCH request executed"
        );

        APIContext.response.prettyPrint();
    }

    @When("Verified KYC user sends PATCH request with Aadhaar {string} and PAN {string}")
    public void verified_kyc_user_sends_patch_request_with_aadhaar_and_pan(
            String aadhaar,
            String pan
    ) {

        LoggerUtility.info(
                "Verified KYC user sending PATCH request"
        );


        payload =
                new HashMap<>();


        payload.put(
                "aadhaar_number",
                aadhaar
        );



        payload.put(
                "pan_number",
                pan
        );



        APIContext.response =
                requestHelper.sendPatchRequest(
                        Routes.UPDATE_KYC,
                        payload,
                        auth.GetToken()
                );


        LoggerUtility.info(
                "Verified KYC PATCH request executed"
        );

        APIContext.response.prettyPrint();
    }
}