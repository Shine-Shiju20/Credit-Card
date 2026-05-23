package stepdefinitions.api.Accounts;

import api.clients.AccountApiClient;
import context.ApiContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.LoggerUtility;

public class GetAccountSteps {

    private AccountApiClient
            accountApiClient;

    private Response response;

    @When("User fetches {string} account")
    public void getAccount(
            String requestType){

        accountApiClient =
                new AccountApiClient();

        String accountId;

        if(requestType.equalsIgnoreCase(
                "valid")){

            Response allAccounts=
                    accountApiClient
                            .getAllAccounts();

            accountId=
                    allAccounts
                            .jsonPath()
                            .getString(
                                    "data[0].account_id");

            if(accountId==null){

                throw new RuntimeException(
                        "No account available");

            }

        }

        else{

            accountId=
                    "11111111-1111-1111-1111-111111111111";

        }

        response=
                accountApiClient
                        .getAccount(
                                accountId);

        LoggerUtility.info(
                "Get Status : "
                        +
                        response
                                .getStatusCode());

        ApiContext.set(
                "response",
                response);

    }


    @When(
            "User fetches all accounts")
    public void getAllAccounts(){

        accountApiClient =
                new AccountApiClient();

        response=
                accountApiClient
                        .getAllAccounts();

        LoggerUtility.info(
                "Fetch All Status : "
                        +
                        response
                                .getStatusCode());

        ApiContext.set(
                "response",
                response);

    }

}