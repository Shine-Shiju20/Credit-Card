package stepdefinitions.api.Accounts;

import api.clients.AccountApiClient;
import api.clients.AuthApiClient;
import api.models.requests.LoginRequest;
import api.models.requests.UpdateAccountRequest;
import context.ApiContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.LoggerUtility;
import utils.TestDataStore;

public class UpdateAccountSteps {

    private AuthApiClient
            authApiClient;

    private AccountApiClient
            accountApiClient;

    private Response response;


    @When("User updates account to {string}")
    public void updateAccount(
            String accountType){

        authApiClient =
                new AuthApiClient();

        accountApiClient =
                new AccountApiClient();

        Object accountObj=
                ApiContext.get(
                        "accountId");

        if(accountObj==null){

            throw new RuntimeException(
                    "No account found for update");
        }

        String accountId=
                accountObj
                        .toString();

        LoggerUtility.info(
                "Stored Account ID : "
                        + accountId);


        /* Save old values for revert */

        Response accountResponse=
                accountApiClient
                        .getAccount(
                                accountId);

        if(accountResponse.getStatusCode()==200){

            String oldType=
                    accountResponse
                            .jsonPath()
                            .getString(
                                    "data.account_type");

            String accountNumber=
                    accountResponse
                            .jsonPath()
                            .getString(
                                    "data.account_number");

            TestDataStore
                    .apiUpdatedAccounts
                    .put(
                            accountNumber,
                            oldType);

        }


        UpdateAccountRequest request=
                new UpdateAccountRequest();

        request.setAccount_type(
                accountType);

        response=
                accountApiClient
                        .updateAccount(
                                accountId,
                                request);

        LoggerUtility.info(
                "Update Status : "
                        +
                        response
                                .getStatusCode());

        LoggerUtility.debug(
                response.asPrettyString());

        ApiContext.set(
                "response",
                response);

    }


    @When("User updates invalid account")
    public void updateInvalidAccount(){
        accountApiClient =
                new AccountApiClient();

        UpdateAccountRequest request=
                new UpdateAccountRequest();

        request.setAccount_type(
                "savings");

        response=
                accountApiClient
                        .updateAccount(

                                "11111111-1111-1111-1111-111111111111",
                                request
                        );

        ApiContext.set(
                "response",
                response);
    }


    @Given(
            "User logs in with new user account")
    public void userLogsInWithNewUserAccount(){

        authApiClient =
                new AuthApiClient();

        LoginRequest request=
                new LoginRequest();

        request.setEmail(
                "prajwalvithale10@gmail.com");

        request.setPassword(
                "Prajwal@2026");

        Response response=
                authApiClient
                        .login(
                                request);

        if(response.getStatusCode()!=200){

            throw new RuntimeException(
                    "New user login failed");
        }

        ApiContext.set(
                "cookies",
                response.getDetailedCookies());

        ApiContext.set(
                "transactionPin",
                "2026");


        TestDataStore.currentEmail=
                "prajwalvithale10@gmail.com";

        TestDataStore.currentPassword=
                "Prajwal@2026";

    }

    @Given("User uses existing salary account")
    public void useExistingSalaryAccount(){

        accountApiClient =
                new AccountApiClient();

        Response response =
                accountApiClient
                        .getAllAccounts();

        int size =
                response.jsonPath()
                        .getList("data")
                        .size();

        for(int i=0;i<size;i++){

            String type =
                    response.jsonPath()
                            .getString(
                                    "data[" + i + "].account_type");

            if(type.equalsIgnoreCase(
                    "salary")){

                ApiContext.set(
                        "accountId",

                        response.jsonPath()
                                .getString(
                                        "data[" + i + "].account_id"));

                ApiContext.set(
                        "accountNumber",

                        response.jsonPath()
                                .getString(
                                        "data[" + i + "].account_number"));

                ApiContext.set(
                        "balance",

                        response.jsonPath()
                                .getString(
                                        "data[" + i + "].balance"));

                return;
            }

        }

        throw new RuntimeException(
                "No salary account found");

    }

}