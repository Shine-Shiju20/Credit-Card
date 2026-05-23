package stepdefinitions.api.Accounts;

import api.clients.AccountApiClient;
import api.clients.TransactionApiClient;
import api.models.requests.WithdrawRequest;
import context.ApiContext;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.LoggerUtility;
import utils.TestDataStore;

public class CloseAccountSteps {

    private Response response;


    @When(
            "User withdraws complete account balance")
    public void withdrawCompleteBalance(){

        Object accountObj=
                ApiContext.get(
                        "accountNumber");

        Object balanceObj=
                ApiContext.get(
                        "balance");

        Object pinObj=
                ApiContext.get(
                        "transactionPin");

        if(accountObj==null
                ||
                balanceObj==null
                ||
                pinObj==null){

            throw new RuntimeException(
                    "Withdraw data missing");
        }

            TransactionApiClient
                    transactionApiClient=
                    new TransactionApiClient();

        WithdrawRequest request=
                new WithdrawRequest();

        request.setAccount_number(
                accountObj
                        .toString());

        request.setAmount(

                Double.parseDouble(
                        balanceObj
                                .toString())

        );

        request.setTransaction_pin(

                pinObj
                        .toString()

        );

        response=
                transactionApiClient
                        .withdraw(
                                request);

        LoggerUtility.info(
                "Withdraw Status : "
                        +
                        response
                                .getStatusCode());

    }


    @When(
            "User closes account")
    public void closeAccount(){

        Object accountObj=
                ApiContext.get(
                        "accountId");

        if(accountObj==null){

            throw new RuntimeException(
                    "No account found for close");
        }

        String accountId=
                accountObj
                        .toString();

        AccountApiClient
                accountApiClient=
                new AccountApiClient();

        response=
                accountApiClient
                        .closeAccount(
                                accountId);

        LoggerUtility.info(
                "Close Status : "
                        +
                        response
                                .getStatusCode());

        LoggerUtility.debug(
                response
                        .asPrettyString());

        ApiContext.set(
                "response",
                response);


        if(response!=null
                &&
                response.getStatusCode()==200){

            String accountNumber=
                    ApiContext.get(
                                    "accountNumber")
                            .toString();

            if(!TestDataStore
                    .apiCreatedAccounts
                    .containsKey(
                            accountNumber)){

                TestDataStore
                        .apiClosedAccounts
                        .add(
                                accountNumber);

                LoggerUtility.pass(
                        "Closed Existing Account Stored : "
                                +
                                accountNumber);

            }

            ApiContext.remove(
                    "accountId");

            ApiContext.remove(
                    "accountNumber");

            ApiContext.remove(
                    "balance");

        }
    }

}