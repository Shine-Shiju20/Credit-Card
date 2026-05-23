package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DBCleanupUtility {

    public static void reopenAccount(
            String accountNumber){

        try{

            Connection connection=
                    DBUtility.getConnection();

            PreparedStatement stmt=
                    connection.prepareStatement(

                            "UPDATE accounts " +
                                    "SET status='active'::enum_accounts_status, " +
                                    "balance=?, " +
                                    "available_balance=? " +
                                    "WHERE account_number=?"
                    );

            Double balance=
                    TestDataStore.accountBalances.get(
                            accountNumber
                    );

            stmt.setDouble(
                    1,
                    balance
            );

            stmt.setDouble(
                    2,
                    balance
            );

            stmt.setString(
                    3,
                    accountNumber
            );

            int rows=
                    stmt.executeUpdate();

            LoggerUtility.info(
                    "Reopened Rows : "
                            + rows
            );

            LoggerUtility.info(
                    "Account Reopened : "
                            + accountNumber
            );

            stmt.close();
        }
        catch(Exception e){

            LoggerUtility.fail(
                    e.getMessage());
        }
    }

    public static void reopenApiAccount(
            String accountNumber){

        try{

            if(accountNumber==null){

                LoggerUtility.warn(
                        "Account number null");

                return;
            }

            if(!TestDataStore
                    .apiClosedAccounts
                    .contains(
                            accountNumber)){

                LoggerUtility.info(
                        "Account not closed in current scenario");

                return;
            }

            Double balance =
                    TestDataStore
                            .apiAccountBalances
                            .get(
                                    accountNumber);

            if(balance==null){

                LoggerUtility.warn(
                        "Balance missing");

                return;
            }

            Connection connection =
                    DBUtility
                            .getConnection();

            PreparedStatement stmt =
                    connection.prepareStatement(

                            "UPDATE accounts "
                                    + "SET status='active'::enum_accounts_status, "
                                    + "balance=?, "
                                    + "available_balance=? "
                                    + "WHERE account_number=?"

                    );

            stmt.setDouble(
                    1,
                    balance);

            stmt.setDouble(
                    2,
                    balance);

            stmt.setString(
                    3,
                    accountNumber);

            int rows =
                    stmt.executeUpdate();

            if(rows>0){

                LoggerUtility.pass(
                        "API Account Reopened : "
                                + accountNumber);

            }

            else{

                LoggerUtility.warn(
                        "No account updated : "
                                + accountNumber);

            }

            stmt.close();

        }

        catch(Exception e){

            LoggerUtility.fail(
                    "API Account Reopen Failed : "
                            + e.getMessage());

        }

    }

}