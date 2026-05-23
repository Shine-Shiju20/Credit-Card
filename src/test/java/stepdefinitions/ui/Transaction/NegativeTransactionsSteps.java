//package stepdefinitions;
//
//import org.testng.Assert;
//
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import pages.NegativeTransactionsPage;
//import utils.DriverFactory;
//import utils.ExcelReader;
//
//public class NegativeTransactionsSteps {
//
//    private NegativeTransactionsPage negativeTransactionsPage =
//            new NegativeTransactionsPage(
//                    new DriverFactory().getDriver()
//            );
//
//    // =========================================================
//    // Excel-Driven Negative Transaction Scenarios
//    // =========================================================
//
//    @When("the user performs negative transaction using Excel test data row {int}")
//    public void the_user_performs_negative_transaction_using_excel_test_data_row(
//            int rowNum) {
//
//        String filePath =
//                "src/test/resources/testdata/Pending_Transaction_TestData.xlsx";
//        String sheetName = "Transactions";
//
//        String sourceAccount = ExcelReader.getCellData(
//                filePath, sheetName, rowNum, 1);
//
//        String recipientAccount = ExcelReader.getCellData(
//                filePath, sheetName, rowNum, 2);
//
//        String transferType = ExcelReader.getCellData(
//                filePath, sheetName, rowNum, 3);
//
//        String amount = ExcelReader.getCellData(
//                filePath, sheetName, rowNum, 4);
//
//        String pin = ExcelReader.getCellData(
//                filePath, sheetName, rowNum, 5);
//
//        System.out.println("Executing Negative Transaction Row: " + rowNum);
//        System.out.println("Source Account: " + sourceAccount);
//        System.out.println("Recipient Account: " + recipientAccount);
//        System.out.println("Transfer Type: " + transferType);
//        System.out.println("Amount: " + amount);
//        System.out.println("PIN: " + pin);
//
//        negativeTransactionsPage.performTransaction(
//                sourceAccount,
//                recipientAccount,
//                transferType,
//                amount,
//                pin
//        );
//    }
//
//    // =========================================================
//    // Error Message Validation
//    // =========================================================
//
//    @Then("the error message should be displayed")
//    public void the_error_message_should_be_displayed() {
//        Assert.assertTrue(
//                negativeTransactionsPage.isErrorMessageDisplayed(),
//                "Expected error message was not displayed."
//        );
//    }
//
//    // =========================================================
//    // Simulated Session Expiration
//    // =========================================================
//
//    @When("the session expires")
//    public void the_session_expires() {
//        negativeTransactionsPage.expireSession();
//    }
//
//    // =========================================================
//    // Actual Wait for Timeout Validation
//    // =========================================================
//
//    @When("the user waits for {int} minutes")
//    public void the_user_waits_for_minutes(Integer minutes)
//            throws InterruptedException {
//
//        System.out.println(
//                "Waiting for " + minutes + " minute(s)..."
//        );
//
//        Thread.sleep(minutes * 60 * 1000L);
//    }
//
//    // =========================================================
//    // Login Redirect Validation
//    // =========================================================
//
//    @Then("the user should be redirected to the login page")
//    public void the_user_should_be_redirected_to_the_login_page() {
//        Assert.assertTrue(
//                negativeTransactionsPage.isLoginPageDisplayed(),
//                "User was not redirected to the login page."
//        );
//    }
//}
package stepdefinitions.ui.Transaction;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NegativeTransactionsPage;
import utils.Transactions.DriverFactory;
import utils.Transactions.ExcelReader;
import utils.Log;

public class NegativeTransactionsSteps {

    private NegativeTransactionsPage negativeTransactionsPage =
            new NegativeTransactionsPage(
                    DriverFactory.getDriver()
            );

    // Logger
    private static final Logger logger =
            Log.getLogger(NegativeTransactionsSteps.class);

    // =========================================================
    // Excel-Driven Negative Transaction Scenarios
    // =========================================================

    @When("the user performs negative transaction using Excel test data row {int}")
    public void the_user_performs_negative_transaction_using_excel_test_data_row(
            int rowNum) {

        logger.info(
                "Reading negative transaction test data from Excel row: {}",
                rowNum
        );

        String filePath =
                "src/test/resources/testdata/Pending_Transaction_TestData.xlsx";
        String sheetName = "Transactions";

        String sourceAccount = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 1);

        String recipientAccount = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 2);

        String transferType = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 3);

        String amount = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 4);

        String pin = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 5);

        logger.info("Source Account: {}", sourceAccount);
        logger.info("Recipient Account: {}", recipientAccount);
        logger.info("Transfer Type: {}", transferType);
        logger.info("Amount: {}", amount);
        logger.info("PIN: {}", pin);

        negativeTransactionsPage.performTransaction(
                sourceAccount,
                recipientAccount,
                transferType,
                amount,
                pin
        );

        logger.info(
                "Negative transaction executed successfully for row: {}",
                rowNum
        );
    }

    // =========================================================
    // Error Message Validation
    // =========================================================

    @Then("the error message should be displayed")
    public void the_error_message_should_be_displayed() {

        logger.info("Validating error message display.");

        Assert.assertTrue(
                negativeTransactionsPage.isErrorMessageDisplayed(),
                "Expected error message was not displayed."
        );

        logger.info("Error message displayed successfully.");
    }

    // =========================================================
    // Simulated Session Expiration
    // =========================================================

    @When("the session expires")
    public void the_session_expires() {

        logger.info("Expiring the session immediately.");

        negativeTransactionsPage.expireSession();

        logger.info("Session expired successfully.");
    }

    // =========================================================
    // Actual Wait for Timeout Validation
    // =========================================================

    @When("the user waits for {int} minutes")
    public void the_user_waits_for_minutes(Integer minutes)
            throws InterruptedException {

        logger.info("Waiting for {} minute(s).", minutes);

        // Wait for actual session timeout
        Thread.sleep(minutes * 60 * 1000L);

        logger.info(
                "Refreshing page after waiting to trigger session validation."
        );

        // Refresh page so frontend detects expired session
        DriverFactory.getDriver().navigate().refresh();

        logger.info("Page refreshed after timeout wait.");
    }

    // =========================================================
    // Login Redirect Validation
    // =========================================================

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {

        logger.info("Validating redirection to login page.");

        Assert.assertTrue(
                negativeTransactionsPage.isLoginPageDisplayed(),
                "User was not redirected to the login page."
        );

        logger.info("User successfully redirected to login page.");
    }
}