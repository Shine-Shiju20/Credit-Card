package API_Hooks.Accounts;

import api.clients.AccountApiClient;
import api.clients.AuthApiClient;
import api.clients.TransactionApiClient;
import api.models.requests.LoginRequest;
import api.models.requests.UpdateAccountRequest;
import api.models.requests.WithdrawRequest;
import context.ApiContext;
import io.cucumber.java.After;
import io.restassured.response.Response;
import utils.DBCleanupUtility;
import utils.LoggerUtility;
import utils.TestDataStore;

public class ApiHooks {

    @After
    public void cleanUp() {

        try{

            if(TestDataStore.currentEmail==null
                    ||
                    TestDataStore.currentPassword==null){

                LoggerUtility.warn(
                        "No current user found for cleanup");

                return;
            }

            LoginRequest loginRequest =
                    new LoginRequest();

            loginRequest.setEmail(
                    TestDataStore.currentEmail);

            loginRequest.setPassword(
                    TestDataStore.currentPassword);

            AuthApiClient
                    authApiClient =
                    new AuthApiClient();

            Response loginResponse =
                    authApiClient
                            .login(
                                    loginRequest);

            ApiContext.set(
                    "cookies",
                    loginResponse
                            .getDetailedCookies());


            /* Cleanup created accounts */

            for(String accountNumber :
                    TestDataStore
                            .apiCreatedAccounts
                            .keySet()){

                try{

                    String accountId =
                            TestDataStore
                                    .apiCreatedAccounts
                                    .get(
                                            accountNumber);

                    if(accountId==null){

                        continue;
                    }

                    AccountApiClient
                            accountApiClient =
                            new AccountApiClient();

                    Response accountResponse =
                            accountApiClient
                                    .getAccount(
                                            accountId);

                    if(accountResponse.getStatusCode()!=200){

                        continue;
                    }

                    Double currentBalance =
                            accountResponse
                                    .jsonPath()
                                    .getDouble(
                                            "data.balance");

                    if(currentBalance!=null
                            &&
                            currentBalance>0){

                        TransactionApiClient
                                transactionApiClient =
                                new TransactionApiClient();

                        WithdrawRequest request =
                                new WithdrawRequest();

                        request.setAccount_number(
                                accountNumber);

                        request.setAmount(
                                currentBalance);

                        request.setTransaction_pin(
                                "2026");

                        Response withdrawResponse =
                                transactionApiClient
                                        .withdraw(
                                                request);

                        LoggerUtility.info(
                                "Withdraw Status : "
                                        +
                                        withdrawResponse
                                                .getStatusCode());

                    }

                    Response closeResponse =
                            accountApiClient
                                    .closeAccount(
                                            accountId);

                    LoggerUtility.info(
                            "Close Status : "
                                    +
                                    closeResponse
                                            .getStatusCode());

                }

                catch(Exception e){

                    LoggerUtility.fail(
                            "Created Account Cleanup Failed : "
                                    +
                                    e.getMessage());

                }

            }

            /* Restore updated accounts */

            for(String accountNumber :
                    TestDataStore
                            .apiUpdatedAccounts
                            .keySet()){

                try{

                    String oldType =
                            TestDataStore
                                    .apiUpdatedAccounts
                                    .get(
                                            accountNumber);

                    Response allAccounts =
                            new AccountApiClient()
                                    .getAllAccounts();

                    int size =
                            allAccounts
                                    .jsonPath()
                                    .getList(
                                            "data")
                                    .size();

                    for(int i=0;i<size;i++){

                        String accNo =
                                allAccounts
                                        .jsonPath()
                                        .getString(
                                                "data["+i+"].account_number");

                        if(accountNumber.equals(
                                accNo)){

                            String accountId =
                                    allAccounts
                                            .jsonPath()
                                            .getString(
                                                    "data["+i+"].account_id");

                            UpdateAccountRequest request =
                                    new UpdateAccountRequest();

                            request.setAccount_type(
                                    oldType);

                            Response updateResponse =
                                    new AccountApiClient()
                                            .updateAccount(
                                                    accountId,
                                                    request);

                            LoggerUtility.info(
                                    "Restore Status : "
                                            +
                                            updateResponse
                                                    .getStatusCode());

                            break;

                        }

                    }

                }
                catch(Exception e){

                    LoggerUtility.fail(
                            "Update restore failed : "
                                    + e.getMessage());

                }

            }


            /* Reopen explicitly closed accounts only */

            for(String accountNumber :
                    TestDataStore
                            .apiClosedAccounts){

                try{

                    DBCleanupUtility
                            .reopenApiAccount(
                                    accountNumber);

                }

                catch(Exception e){

                    LoggerUtility.fail(
                            "Reopen Failed : "
                                    +
                                    e.getMessage());

                }

            }


            LoggerUtility.pass(
                    "API Cleanup Completed");


        }

        catch(Exception e){

            LoggerUtility.fail(
                    "Cleanup failed : "
                            + e.getMessage());

        }

        finally{

            TestDataStore
                    .apiCreatedAccounts
                    .clear();

            TestDataStore
                    .apiUpdatedAccounts
                    .clear();

            TestDataStore
                    .apiClosedAccounts
                    .clear();

            TestDataStore
                    .apiAccountBalances
                    .clear();

            ApiContext.clear();

        }

    }

}