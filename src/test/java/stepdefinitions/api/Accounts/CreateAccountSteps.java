package stepdefinitions.api.Accounts;

import api.clients.AccountApiClient;
import api.models.requests.CreateAccountRequest;
import context.ApiContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.LoggerUtility;
import utils.TestDataStore;

public class CreateAccountSteps {

    private AccountApiClient accountApiClient;

    private Response response;


    @When("User creates account with {string} and {string}")
    public void createAccount(
            String accountType,
            String deposit){

        accountApiClient =
                new AccountApiClient();

        CreateAccountRequest request =
                new CreateAccountRequest();

        request.setAccount_type(
                accountType);

        try{

            request.setInitial_deposit(
                    Double.parseDouble(
                            deposit));

        }
        catch(Exception e){

            request.setInitial_deposit(null);

        }

        response =
                accountApiClient
                        .createAccount(
                                request);

        LoggerUtility.info(
                "Create Status = "
                        + response.getStatusCode());

        if(response.getStatusCode()!=201){

            LoggerUtility.fail(
                    response.asPrettyString());
        }

        ApiContext.set(
                "response",
                response);

        if(response.getStatusCode()==201){

            LoggerUtility.info(
                    response.asPrettyString());

            String accountId =
                    response.jsonPath()
                            .getString(
                                    "data.account_id");

            String accountNumber =
                    response.jsonPath()
                            .getString(
                                    "data.account_number");

            String balance =
                    response.jsonPath()
                            .getString(
                                    "data.balance");
            ApiContext.set(
                    "transactionPin",
                    "2026");

            ApiContext.set(
                    "accountId",
                    accountId);

            ApiContext.set(
                    "accountNumber",
                    accountNumber);

            TestDataStore
                    .createdAccounts
                    .put(
                            accountNumber,
                            accountId);

            LoggerUtility.info(
                    "Stored API Account : "
                            + accountNumber);

            ApiContext.set(
                    "balance",
                    balance);


            /* API ownership tracking */

            TestDataStore
                    .apiCreatedAccounts
                    .put(
                            accountNumber,
                            accountId);

            TestDataStore
                    .apiAccountBalances
                    .put(
                            accountNumber,
                            Double.parseDouble(
                                    balance));

            LoggerUtility.info(
                    "Stored API Account : "
                            + accountNumber);


            TestDataStore.accountBalances.put(
                    accountNumber,
                    Double.parseDouble(
                            balance));

            LoggerUtility.info(
                    "Account ID = "
                            + accountId);

        }

    }


    @Then("Response status code should be {int}")
    public void verifyStatusCode(
            int expectedStatusCode){

        Response response =
                (Response)
                        ApiContext.get(
                                "response");

        LoggerUtility.info(
                "Expected: " +
                        expectedStatusCode);

        LoggerUtility.info(
                "Actual: " +
                        response.getStatusCode());

        if(response.getStatusCode()
                == expectedStatusCode){

            LoggerUtility.pass(
                    "Status code matched. Expected="
                            + expectedStatusCode
                            + " Actual="
                            + response.getStatusCode());

        }
        else{

            LoggerUtility.fail(
                    "Status code mismatch. Expected="
                            + expectedStatusCode
                            + " Actual="
                            + response.getStatusCode());

        }

        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatusCode,
                "Status code mismatch");
    }

    @When("User creates maximum {string} account")
    public void createMaximumAccount(
            String accountType){

        accountApiClient =
                new AccountApiClient();

        CreateAccountRequest request =
                new CreateAccountRequest();

        request.setAccount_type(
                accountType);

        if(accountType.equalsIgnoreCase(
                "salary")){

            request.setInitial_deposit(
                    0.0);

        }
        else{

            request.setInitial_deposit(
                    5000.0);

        }

        Response response =
                accountApiClient
                        .createAccount(
                                request);

        LoggerUtility.debug(
                response.asPrettyString());

        ApiContext.set(
                "response",
                response);

    }

}