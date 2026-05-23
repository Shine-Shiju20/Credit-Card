package stepdefinitions.api.Accounts;

import api.clients.AccountApiClient;
import context.ApiContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountNegativeSteps {

    private AccountApiClient
            accountApiClient;

    @When(
            "User creates account with invalid deposit {string}")
    public void createInvalidDeposit(
            String value){

        accountApiClient =
                new AccountApiClient();


        Map<String,Object> request =
                new HashMap<>();

        request.put(
                "account_type",
                "savings");

        request.put(
                "initial_deposit",
                value);

        Response response =
                accountApiClient
                        .createAccount(
                                request);

        ApiContext.set(
                "response",
                response);

    }


    @When(
            "User creates account without account type")
    public void withoutAccountType(){

        accountApiClient =
                new AccountApiClient();


        Map<String,Object> request =
                new HashMap<>();

        request.put(
                "initial_deposit",
                5000);

        Response response =
                accountApiClient
                        .createAccount(
                                request);

        ApiContext.set(
                "response",
                response);

    }


    @When(
            "User creates account without deposit")
    public void withoutDeposit(){

        accountApiClient =
                new AccountApiClient();


        Map<String,Object> request =
                new HashMap<>();

        request.put(
                "account_type",
                "savings");

        Response response =
                accountApiClient
                        .createAccount(
                                request);

        ApiContext.set(
                "response",
                response);

    }

}