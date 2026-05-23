package stepdefinitions.api.Profile;


import api.Common.AuthHelper;
import api.Profile.RequestHelper;

import api.Profile.Routes;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import io.restassured.response.Response;

import org.apiguardian.api.API;
import org.openqa.selenium.remote.http.Route;
import utils.LoggerUtility;

import java.io.IOException;
import java.util.HashMap;

public class OccupationAnnualIncomeAPISteps {


    RequestHelper requestHelper =
            new RequestHelper();

    AuthHelper authHelper =
            new AuthHelper();




    HashMap<String, Object> payload;


    public OccupationAnnualIncomeAPISteps()
            throws IOException {

    }




    @When("User sends PUT request with occupation {string}")
    public void user_sends_put_request_with_occupation(
            String occupation
    ) {

        LoggerUtility.info(
                "Sending PUT request with occupation value: "
                        + occupation
        );


        payload =
                new HashMap<>();


//        payload.put(
//                "full_name",
//                "Abhiram PB"
//        );
//
//        payload.put(
//                "phone",
//                "7337695314"
//        );
//
//        payload.put(
//                "address",
//                "Bangalore Karnataka 560064"
//        );
//
        payload.put(
                "occupation",
                occupation
        );
//
//        payload.put(
//                "annual_income",
//                500000
//        );



        APIContext.response =
                requestHelper.sendPutRequest(
                        Routes.GET_USER,
                        payload,
                        authHelper.GetToken()
                );


        LoggerUtility.info(
                "Occupation validation request executed"
        );

    }






    @When("User sends PUT request with annual income {string}")
    public void user_sends_put_request_with_annual_income(
            String annualIncome
    ) {

        LoggerUtility.info(
                "Sending PUT request with annual income value: "
                        + annualIncome
        );


        payload =
                new HashMap<>();


//        payload.put(
//                "full_name",
//                "Abhiram PB"
//        );
//
//        payload.put(
//                "phone",
//                "7337695314"
//        );
//
//        payload.put(
//                "address",
//                "Bangalore Karnataka 560064"
//        );
//
//        payload.put(
//                "occupation",
//                "Software Engineer"
//        );

        payload.put(
                "annual_income",
                annualIncome
        );



        APIContext.response =
                requestHelper.sendPutRequest(
                        Routes.GET_USER,
                        payload,
                        authHelper.GetToken()
                );


        LoggerUtility.info(
                "Annual income validation request executed"
        );


    }
}