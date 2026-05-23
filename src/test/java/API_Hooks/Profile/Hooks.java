package API_Hooks.Profile;

import api.Common.AuthHelper;

import api.Profile.RequestHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.restassured.response.Response;

import utils.LoggerUtility;

import java.io.IOException;

import java.util.HashMap;

public class Hooks {


    RequestHelper requestHelper =
            new RequestHelper();

    AuthHelper authHelper =
            new AuthHelper();
    public static HashMap<String, Object> originalKYCData;

    public static String token;

    public static HashMap<String, Object>
            originalProfileData;




    public Hooks() throws IOException {

    }




    //capture data of all fields which can be restored to original State Later
    @Before("@ProfileUpdateAPI")
    public void captureOriginalProfileData() throws IOException {





        token = authHelper.getValidSessionCookie();



        Response getResponse =
                requestHelper.sendGetRequest(
                        "/user/me",
                        token
                );



        originalProfileData = getResponse.jsonPath().getJsonObject("data");


        getResponse.prettyPrint();
    }

    //capture only the KYC field to Restore Later
    @Before("@KYCAPI")
    public void captureOriginalKYCData()
            throws IOException {


        LoggerUtility.info(
                "Capturing original KYC data"
        );


        token = authHelper.getValidUnverifiedKycSessionCookie();



        Response response =
                requestHelper.sendGetRequest(
                        "/user/me",
                        token
                );



        originalKYCData =
                response.jsonPath()
                        .getJsonObject("data");



        response.prettyPrint();
    }

    //restoring the original Data After Each test case
    @After("@KYCAPI")
    public void restoreOriginalKYCData(){


        if(originalKYCData != null){

            HashMap<String, Object>
                    restorePayload =
                    new HashMap<>();



            restorePayload.put(
                    "aadhaar_number",
                    originalKYCData.get(
                            "aadhaar_number"
                    )
            );



            restorePayload.put(
                    "pan_number",
                    originalKYCData.get(
                            "pan_number"
                    )
            );



            Response restoreResponse =
                    requestHelper.sendPatchRequest(
                            "/user/kyc",
                            restorePayload,
                            token
                    );



            LoggerUtility.info(
                    "Original KYC data restored successfully"
            );

            restoreResponse.prettyPrint();
        }
    }


    //Restoring The data After Each test case
    @After("@ProfileUpdateAPI")
    public void restoreOriginalProfileData(){

        if(originalProfileData != null){

            HashMap<String, Object>
                    restorePayload =
                    new HashMap<>();



            restorePayload.put(
                    "full_name",
                    originalProfileData.get(
                            "full_name"
                    )
            );



            restorePayload.put(
                    "phone",
                    originalProfileData.get(
                            "phone"
                    )
            );



            restorePayload.put(
                    "address",
                    originalProfileData.get(
                            "address"
                    )
            );



            restorePayload.put(
                    "occupation",
                    originalProfileData.get(
                            "occupation"
                    )
            );



            restorePayload.put(
                    "annual_income",
                    originalProfileData.get(
                            "annual_income"
                    )
            );



            Response restoreResponse =
                    requestHelper.sendPutRequest(
                            "/user/me",
                            restorePayload,
                            token
                    );



            LoggerUtility.info(
                    "Original profile data restored successfully"
            );

            restoreResponse.prettyPrint();
        }
    }
}