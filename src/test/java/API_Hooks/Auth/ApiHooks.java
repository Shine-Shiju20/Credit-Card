package API_Hooks.Auth;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import utils.LoggerUtility;
import utils.ScreenShotUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static Hooks.Auth.Hooks.factory;

public class ApiHooks {

    // ==========================================
    // DELETE AUTOMATION USER
    // ==========================================

    public void deleteAutomationUser(

            String email,
            String phone,
            String aadhaar,
            String pan

    ) {

        Connection connection = null;

        Statement statement = null;

        try {

            // ======================================
            // DATABASE CONNECTION
            // ======================================

            connection = DriverManager.getConnection(

                    "jdbc:postgresql://localhost:5432/banking_system",

                    "postgres",

                    "postgres"
            );

            statement = connection.createStatement();

            // ======================================
            // DELETE EMAIL OTP
            // ======================================

            int deletedOtpRows = statement.executeUpdate(

                    "DELETE FROM email_otps " +

                    "WHERE email = '" + email + "'"
            );

            System.out.println(
                    "Deleted OTP Rows : " + deletedOtpRows
            );


            // ======================================
            // DELETE USER AUDIT LOGS
            // ======================================

            int deletedAuditRows = statement.executeUpdate(

                    "DELETE FROM audit_logs " +

                    "WHERE user_id IN (" +

                    "SELECT user_id FROM users " +

                    "WHERE email = '" + email + "'" +

                    ")"
            );

            System.out.println(
                    "Deleted Audit Log Rows : " + deletedAuditRows
            );


            // ======================================
            // DELETE USER
            // ======================================

            int deletedUserRows = statement.executeUpdate(

                    "DELETE FROM users " +

                    "WHERE email = '" + email + "' " +

                    "OR phone = '" + phone + "' " +

                    "OR aadhaar_number = '" + aadhaar + "' " +

                    "OR pan_number = '" + pan + "'"
            );

            System.out.println(
                    "Deleted User Rows : " + deletedUserRows
            );


            // ======================================
            // SUCCESS MESSAGE
            // ======================================

            LoggerUtility.info(
                    "API automation user deleted successfully"
            );

            System.out.println(
                    "API automation user deleted successfully"
            );

        } catch (Exception e) {

            LoggerUtility.info(
                    "API cleanup failed"
            );

            e.printStackTrace();

        } finally {

            try {

                // ==================================
                // CLOSE STATEMENT
                // ==================================

                if (statement != null) {

                    statement.close();
                }

                // ==================================
                // CLOSE CONNECTION
                // ==================================

                if (connection != null) {

                    connection.close();
                }

            } catch (Exception e) {

                LoggerUtility.info(
                        "Failed to close DB connection"
                );

                e.printStackTrace();
            }
        }
    }


    // ==========================================
    // BEFORE API TESTCASE
    // ==========================================

    @Before(

        "@TC_API_001 " +

        "or @TC_API_002 " +

        "or @TC_API_003 " +

        "or @TC_API_004 " +

        "or @TC_API_005 " +

        "or @TC_API_006 " +

        "or @TC_API_007 " +

        "or @TC_API_008 " +

        "or @TC_API_009 " +

        "or @TC_API_010 " +

        "or @TC_API_011 " +

        "or @TC_API_012 " +

        "or @TC_API_013 " +

        "or @TC_API_014 " +

        "or @TC_API_015 " +

        "or @TC_API_016 "


)

    public void cleanupBeforeApiTestcase() {

        deleteAutomationUser(

                "prajwalprajwal222@gmail.com",

                "9632222204",

                "333337862333",

                "BANFE2121A"
        );

        System.out.println(
                "Before API cleanup completed"
        );
    }


    // ==========================================
    // AFTER API TESTCASE
    // ==========================================

    @After(

        "@TC_API_001 " +

        "or @TC_API_002 " +

        "or @TC_API_003 " +

        "or @TC_API_004 " +

        "or @TC_API_005 " +

        "or @TC_API_006 " +

        "or @TC_API_007 " +

        "or @TC_API_008 " +

        "or @TC_API_009 " +

        "or @TC_API_010 " +

        "or @TC_API_011 " +

        "or @TC_API_012 " +

        "or @TC_API_013 " +

        "or @TC_API_014 " +

        "or @TC_API_015 " +

        "or @TC_API_016 "


)

    public void cleanupAfterApiTestcase(io.cucumber.java.Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
    System.out.println("Scenario Failed: " + scenario.getName());
    ScreenShotUtil screenShotUtil = new ScreenShotUtil();
    screenShotUtil.TakeScreenShot(factory.getDriver(), scenario.getName());
}

        deleteAutomationUser(

                "prajwalprajwal222@gmail.com",

                "9632222204",

                "333337862333",

                "BANFE2121A"
        );

        System.out.println(
                "After API cleanup completed"
        );
    }
}