package utils;

import api.clients.AccountApiClient;
import api.clients.AuthApiClient;
import api.clients.TransactionApiClient;
import api.models.requests.LoginRequest;
import api.models.requests.WithdrawRequest;
import context.ApiContext;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import api.models.requests.UpdateAccountRequest;

public class UIApiCleanupUtility {

    public static void cleanUp(){

        try{

            if(
                    TestDataStore.createdAccounts.isEmpty()
                            &&
                            TestDataStore.updatedAccounts.isEmpty()
                            &&
                            TestDataStore.closedAccounts.isEmpty()
            ){

                LoggerUtility.info(
                        "No Cleanup Required"
                );

                TestDataStore.clear();

                return;
            }

            LoginRequest request=new LoginRequest();

            request.setEmail(
                    TestDataStore.currentEmail
            );

            request.setPassword(
                    TestDataStore.currentPassword
            );

            Response loginResponse=
                    new AuthApiClient()
                            .login(request);

            ApiContext.set(
                    "cookies",
                    loginResponse.getDetailedCookies()
            );

            AccountApiClient accountApi=
                    new AccountApiClient();

            TransactionApiClient transactionApi=
                    new TransactionApiClient();

            Response accountsResponse=
                    accountApi.getAllAccounts();

            JsonPath json=
                    accountsResponse.jsonPath();

            int size=
                    json.getList("data").size();

            // CREATE CLEANUP

            for(String accountNo :
                    TestDataStore.createdAccounts.keySet()){

                for(int i=0;i<size;i++){

                    String number=
                            json.getString(
                                    "data["+i+"].account_number"
                            );

                    if(number.trim().equals(
                            accountNo.trim()
                    )){

                        String accountId=
                                json.getString(
                                        "data["+i+"].account_id"
                                );

                        Double balance=
                                Double.parseDouble(
                                        json.getString(
                                                "data["+i+"].balance"
                                        )
                                );

                        ConfigReader config=
                                new ConfigReader();

                        String transactionPin=
                                config.getProp(
                                        "transactionPin"
                                );

                        LoggerUtility.info(
                                "Found Account : "
                                        + accountNo
                        );

                        if(balance>0){

                            WithdrawRequest withdraw=
                                    new WithdrawRequest();

                            withdraw.setAccount_number(
                                    accountNo
                            );

                            withdraw.setAmount(
                                    balance
                            );

                            withdraw.setTransaction_pin(
                                    transactionPin
                            );

                            transactionApi.withdraw(
                                    withdraw
                            );

                            LoggerUtility.info(
                                    "Withdraw Success : "
                                            + balance
                            );
                        }
                        Double updatedBalance=balance;

                        for(int retry=0;retry<5;retry++){

                            Response refreshResponse=
                                    accountApi.getAccount(
                                            accountId
                                    );

                            updatedBalance=
                                    Double.parseDouble(
                                            refreshResponse
                                                    .jsonPath()
                                                    .getString(
                                                            "data.balance"
                                                    )
                                    );

                            LoggerUtility.info(
                                    "Updated Balance : "
                                            + updatedBalance
                            );

                            if(updatedBalance<=0){
                                break;
                            }

                            Thread.sleep(1000);
                        }

                        if(updatedBalance<=0){

                            Response closeResponse=
                                    accountApi.closeAccount(
                                            accountId
                                    );

                            LoggerUtility.info(
                                    "Close Status : "
                                            + closeResponse.getStatusCode()
                            );

                            LoggerUtility.info(
                                    "Closed : "
                                            + accountNo
                            );
                        }
                        break;
                    }
                }
            }

            // UPDATE CLEANUP

            for(String accountNo :
                    TestDataStore.updatedAccounts.keySet()){

                String oldType=
                        TestDataStore.updatedAccounts.get(accountNo);

                for(int i=0;i<size;i++){

                    String number=
                            json.getString(
                                    "data["+i+"].account_number"
                            );

                    if(number.trim().equals(accountNo.trim())){

                        String accountId=
                                json.getString(
                                        "data["+i+"].account_id"
                                );

                        UpdateAccountRequest updateRequest=
                                new UpdateAccountRequest();

                        updateRequest.setAccount_type(
                                oldType
                        );

                        Response updateResponse=
                                accountApi.updateAccount(
                                        accountId,
                                        updateRequest
                                );

                        LoggerUtility.info(
                                "Restored : "
                                        + accountNo
                                        + " -> "
                                        + oldType
                        );

                        LoggerUtility.info(
                                "Update Status : "
                                        + updateResponse.statusCode()
                        );

                        break;
                    }
                }
            }

            for(String accountNo :
                    TestDataStore.closedAccounts){

                DBCleanupUtility
                        .reopenAccount(
                                accountNo
                        );
            }

        }
        catch(Exception e){

            LoggerUtility.info(
                    "Cleanup Failed : "
                            + e.getMessage()
            );
        }

        TestDataStore.clear();

        LoggerUtility.info(
                "TestDataStore Cleared"
        );
    }
}