//package stepdefinitions;
//
//import io.cucumber.java.en.When;
//import pages.AdvancedTransactionsPage;
//import utils.DriverFactory;
//import utils.ExcelReader;
//
//public class AdvancedTransactionsSteps {
//
//    private AdvancedTransactionsPage advancedTransactionsPage;
//
//    public AdvancedTransactionsSteps() {
//        advancedTransactionsPage = new AdvancedTransactionsPage(
//                new DriverFactory().getDriver()
//        );
//    }
//
//    @When("the user performs advanced transaction using Excel test data row {int}")
//    public void the_user_performs_advanced_transaction_using_excel_test_data_row(
//            int rowNum) {
//
//        String filePath =
//                "src/test/resources/testdata/Advanced_Transaction_TestData.xlsx";
//        String sheetName = "AdvancedTransactions";
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
//        String transactionPin = ExcelReader.getCellData(
//                filePath, sheetName, rowNum, 5);
//
//        String expectedMessage = ExcelReader.getCellData(
//                filePath, sheetName, rowNum, 6);
//
//        String expectedBalanceChange = ExcelReader.getCellData(
//                filePath, sheetName, rowNum, 7);
//
//        System.out.println("Scenario Row: " + rowNum);
//        System.out.println("Source Account: " + sourceAccount);
//        System.out.println("Recipient Account: " + recipientAccount);
//        System.out.println("Transfer Type: " + transferType);
//        System.out.println("Amount: " + amount);
//        System.out.println("Transaction PIN: " + transactionPin);
//        System.out.println("Expected Message: " + expectedMessage);
//        System.out.println("Expected Balance Change: "
//                + expectedBalanceChange);
//
//        advancedTransactionsPage.performAdvancedTransaction(
//                sourceAccount,
//                recipientAccount,
//                transferType,
//                amount,
//                transactionPin
//        );
//    }
//}

package stepdefinitions.ui.Transaction;

import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.When;
import pages.AdvancedTransactionsPage;
import utils.Transactions.DriverFactory;
import utils.Transactions.ExcelReader;
import utils.Log;

public class AdvancedTransactionsSteps {

    private AdvancedTransactionsPage advancedTransactionsPage;

    // Logger
    private static final Logger logger =
            Log.getLogger(AdvancedTransactionsSteps.class);

    public AdvancedTransactionsSteps() {
        advancedTransactionsPage = new AdvancedTransactionsPage(
                new DriverFactory().getDriver()
        );
        logger.info("AdvancedTransactionsSteps initialized.");
    }

    @When("the user performs advanced transaction using Excel test data row {int}")
    public void the_user_performs_advanced_transaction_using_excel_test_data_row(
            int rowNum) {

        logger.info(
                "Reading advanced transaction test data from Excel row: {}",
                rowNum
        );

        String filePath =
                "src/test/resources/testdata/Advanced_Transaction_TestData.xlsx";
        String sheetName = "AdvancedTransactions";

        String sourceAccount = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 1);

        String recipientAccount = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 2);

        String transferType = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 3);

        String amount = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 4);

        String transactionPin = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 5);

        String expectedMessage = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 6);

        String expectedBalanceChange = ExcelReader.getCellData(
                filePath, sheetName, rowNum, 7);

        // Log all test data
        logger.info("Source Account: {}", sourceAccount);
        logger.info("Recipient Account: {}", recipientAccount);
        logger.info("Transfer Type: {}", transferType);
        logger.info("Amount: {}", amount);
        logger.info("Transaction PIN: {}", transactionPin);
        logger.info("Expected Message: {}", expectedMessage);
        logger.info(
                "Expected Balance Change: {}",
                expectedBalanceChange
        );

        // Perform transaction
        advancedTransactionsPage.performAdvancedTransaction(
                sourceAccount,
                recipientAccount,
                transferType,
                amount,
                transactionPin
        );

        logger.info(
                "Advanced transaction execution completed for row: {}",
                rowNum
        );
    }
}