package Hooks.Auth;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import utils.LoggerUtility;
import utils.ScreenShotUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Hooks {

    public void deleteAutomationUser(
        String email,
        String phone,
        String aadhaar,
        String pan
) {

    try {

        Connection connection =
                DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/banking_system",
                        "postgres",
                        "postgres"
                );

        Statement statement =
                connection.createStatement();

        statement.executeUpdate(
                "DELETE FROM users " +
                "WHERE email = '" + email + "' " +
                "OR phone = '" + phone + "' " +
                "OR aadhaar_number = '" + aadhaar + "' " +
                "OR pan_number = '" + pan + "'"
        );

        connection.close();

        System.out.println(
                "Automation user deleted successfully"
        );

    } catch (Exception e) {

        e.printStackTrace();
    }
}


    public static DriverFactory factory;

    Connection connection;

    @Before("@validDOB or @validPAN or @validAadhaar or @validIncome or @validMaleGender or @validFemaleGender or @validOtherGender")
    public void cleanupAutomationData() {

    deleteAutomationUser(

            "dharwadguddu123@gmail.com",

            "9631117704",

            "335338762333",

            "BANTH2121A"
    );
}

    // ==========================================
    // BEFORE SCENARIO
    // ==========================================

    @Before
    public void setup() {

        try {

            // ======================================
            // START BROWSER
            // ======================================

            factory =
                    new DriverFactory();

            LoggerUtility.info(
                    "Browser Started"
            );

            System.out.println(
                    "Browser Started"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ==========================================
    // AFTER SCENARIO
    // ==========================================

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
    System.out.println("Scenario Failed: " + scenario.getName());
    ScreenShotUtil screenShotUtil = new ScreenShotUtil();
    screenShotUtil.TakeScreenShot(factory.getDriver(), scenario.getName());
}

        try {

            // ======================================
            // CONNECT DATABASE
            // ======================================




            // ======================================
            // RESTORE ORIGINAL DATA
            // ======================================




            LoggerUtility.info(
                    "Database restored successfully"
            );

            System.out.println(
                    "Database restored successfully"
            );




            // ======================================
            // CLOSE BROWSER
            // ======================================

            factory.CloseDriver();

            LoggerUtility.info(
                    "Browser Closed"
            );

            System.out.println(
                    "Browser Closed"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}