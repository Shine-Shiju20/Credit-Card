package stepdefinitions.api.Accounts;

import api.clients.AuthApiClient;
import api.models.requests.LoginRequest;
import context.ApiContext;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import utils.LoggerUtility;
import utils.TestDataStore;

public class AuthSteps {

    private final AuthApiClient authApiClient;

    public AuthSteps(){

        authApiClient =
                new AuthApiClient();

    }

    @Given("User logs in successfully")
    public void userLogsInSuccessfully(){

        LoginRequest request =
                new LoginRequest();

        request.setEmail(
                "fansrcb881@gmail.com");

        request.setPassword(
                "Prajwal@2026");

        Response response =
                authApiClient
                        .login(request);

        LoggerUtility.debug(
                response.asPrettyString());

        ApiContext.set(
                "transactionPin",
                "2026");

        // Store complete cookies object
        ApiContext.set(
                "cookies",
                response.getDetailedCookies());

        TestDataStore.currentEmail=
                "user_email_here";

        TestDataStore.currentPassword=
                "user_password_here";

        LoggerUtility.info(
                "Authentication cookies stored in ApiContext");

    }

    @Given("User logs in with account having no accounts")
    public void loginWithNoAccountsUser(){

        AuthApiClient authApiClient =
                new AuthApiClient();

        LoginRequest request =
                new LoginRequest();

        request.setEmail(
                "prajwal20221cse0544@gmail.com");

        request.setPassword(
                "Akash@123");

        Response response =
                authApiClient
                        .login(
                                request);

        ApiContext.set(
                "response",
                response);

        ApiContext.set(
                "cookies",
                response.getDetailedCookies());

    }
    @Given("User removes authentication cookies")
    public void removeAuthenticationCookies(){

        ApiContext.remove(
                "cookies");

        ApiContext.remove(
                "response");

    }
    @Given("User logs in with limit test account")
    public void loginLimitUser(){

        LoginRequest request =
                new LoginRequest();

        request.setEmail("nv327392@gmail.com");

        request.setPassword("Navi@123");

        Response response =
                authApiClient
                        .login(
                                request);

        LoggerUtility.debug(
                response.asPrettyString());

        ApiContext.set(
                "cookies",
                response.getDetailedCookies());

    }

}