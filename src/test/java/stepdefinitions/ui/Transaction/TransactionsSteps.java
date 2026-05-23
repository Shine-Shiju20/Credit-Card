package stepdefinitions.ui.Transaction;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.TransactionsHelper;
import pages.TransactionsPage;
import utils.Transactions.DriverFactory;
import utils.Transactions.ExcelReader;
import utils.Log;

public class TransactionsSteps {

    private TransactionsHelper transactionsPage;

    // Logger
    private static final Logger logger =
            Log.getLogger(TransactionsSteps.class);

    // =========================================================
    // Navigation
    // =========================================================

    @When("the user navigates to the Transactions page")
    public void navigateToTransactionsPage() {

        logger.info("Navigating to Transactions page.");

        transactionsPage = new TransactionsHelper(
                DriverFactory.getDriver()
        );

        transactionsPage.open();

        logger.info("Transactions page navigation completed.");
    }

    @Then("the Transactions page should load successfully")
    public void the_transactions_page_should_load_successfully() {

        logger.info("Validating Transactions page load.");

        Assert.assertTrue(
                transactionsPage.isPageLoaded(),
                "Transactions page did not load successfully."
        );

        logger.info("Transactions page loaded successfully.");
    }

    // =========================================================
    // Transfer Tab
    // =========================================================

    @When("the user clicks the Transfer tab")
    public void the_user_clicks_the_transfer_tab() {
        logger.info("Clicking Transfer tab.");
        transactionsPage.clickTransferTab();
    }

    @Then("the Transfer tab should be displayed")
    public void the_transfer_tab_should_be_displayed() {

        logger.info("Validating Transfer tab display.");

        Assert.assertTrue(
                transactionsPage.isTransferTabDisplayed(),
                "Transfer tab is not displayed."
        );
    }

    // =========================================================
    // Deposit Tab
    // =========================================================

    @When("the user clicks the Deposit tab")
    public void the_user_clicks_the_deposit_tab() {
        logger.info("Clicking Deposit tab.");
        transactionsPage.clickDepositTab();
    }

    @Then("the Deposit tab should be displayed")
    public void the_deposit_tab_should_be_displayed() {

        logger.info("Validating Deposit tab display.");

        Assert.assertTrue(
                transactionsPage.isDepositTabDisplayed(),
                "Deposit tab is not displayed."
        );
    }

    // =========================================================
    // Withdraw Tab
    // =========================================================

    @When("the user clicks the Withdraw tab")
    public void the_user_clicks_the_withdraw_tab() {
        logger.info("Clicking Withdraw tab.");
        transactionsPage.clickWithdrawTab();
    }

    @Then("the Withdraw tab should be displayed")
    public void the_withdraw_tab_should_be_displayed() {

        logger.info("Validating Withdraw tab display.");

        Assert.assertTrue(
                transactionsPage.isWithdrawTabDisplayed(),
                "Withdraw tab is not displayed."
        );
    }

    // =========================================================
    // Account Dropdown
    // =========================================================

    @Then("the account dropdown should be displayed")
    public void the_account_dropdown_should_be_displayed() {

        logger.info("Validating account dropdown display.");

        Assert.assertTrue(
                transactionsPage.isAccountDropdownDisplayed(),
                "Account dropdown is not displayed."
        );
    }

    @Then("the account dropdown should contain at least one account")
    public void the_account_dropdown_should_contain_at_least_one_account() {

        logger.info("Validating account dropdown contains accounts.");

        Assert.assertTrue(
                transactionsPage.hasAccountsInDropdown(),
                "No accounts are available in the account dropdown."
        );
    }

    // =========================================================
    // Recipient Account Number
    // =========================================================

    @Then("the recipient account number field should be displayed")
    public void the_recipient_account_number_field_should_be_displayed() {

        logger.info("Validating recipient account field display.");

        Assert.assertTrue(
                transactionsPage.isRecipientAccountFieldDisplayed(),
                "Recipient account number field is not displayed."
        );
    }

    @When("the user enters a valid recipient account number {string}")
    public void the_user_enters_a_valid_recipient_account_number(
            String accountNumber) {

        logger.info(
                "Entering recipient account number: {}",
                accountNumber
        );

        transactionsPage.enterRecipientAccountNumber(accountNumber);
    }

    @Then("the recipient account number should be accepted")
    public void the_recipient_account_number_should_be_accepted() {

        logger.info("Validating recipient account acceptance.");

        Assert.assertTrue(
                transactionsPage.isRecipientAccountNumberAccepted(),
                "Recipient account number was not accepted."
        );
    }

    @Then("the invalid recipient account number should be retained")
    public void the_invalid_recipient_account_number_should_be_retained() {

        logger.info("Validating invalid recipient account retention.");

        Assert.assertTrue(
                transactionsPage.isRecipientAccountNumberAccepted(),
                "Invalid recipient account number was not entered."
        );
    }

    // =========================================================
    // Transfer Type Dropdown
    // =========================================================

    @Then("the transfer type dropdown should be displayed")
    public void the_transfer_type_dropdown_should_be_displayed() {

        logger.info("Validating transfer type dropdown display.");

        Assert.assertTrue(
                transactionsPage.isTransferTypeDropdownDisplayed(),
                "Transfer type dropdown is not displayed."
        );
    }

    @Then("the transfer type dropdown should contain the following options:")
    public void the_transfer_type_dropdown_should_contain_the_following_options(
            DataTable dataTable) {

        logger.info("Validating transfer type dropdown options.");

        List<String> expectedOptions = dataTable.asList();

        logger.info("Expected options: {}", expectedOptions);

        Assert.assertTrue(
                transactionsPage.verifyTransferTypeOptions(expectedOptions),
                "Transfer type dropdown options do not match expected values."
        );
    }

    // =========================================================
    // Amount Field
    // =========================================================

    @When("the user enters the amount {string}")
    public void the_user_enters_the_amount(String amount) {

        logger.info("Entering amount: {}", amount);

        transactionsPage.enterAmount(amount);
    }

    @Then("the amount field should contain {string}")
    public void the_amount_field_should_contain(String expectedValue) {

        logger.info(
                "Validating amount field contains: {}",
                expectedValue
        );

        Assert.assertEquals(
                transactionsPage.getAmountValue(),
                expectedValue,
                "Amount field value is incorrect."
        );
    }

    @Then("the amount field should not contain {string}")
    public void the_amount_field_should_not_contain(
            String invalidValue) {

        logger.info(
                "Validating amount field does not contain: {}",
                invalidValue
        );

        Assert.assertNotEquals(
                transactionsPage.getAmountValue(),
                invalidValue,
                "Invalid value was accepted in the amount field."
        );
    }

    // =========================================================
    // Transaction PIN
    // =========================================================

    @When("the user enters the transaction PIN {string}")
    public void the_user_enters_the_transaction_pin(String pin) {

        logger.info("Entering transaction PIN.");

        transactionsPage.enterTransactionPin(pin);
    }

    @Then("the transaction PIN field should contain {string}")
    public void the_transaction_pin_field_should_contain(
            String expectedPin) {

        logger.info("Validating transaction PIN value.");

        Assert.assertEquals(
                transactionsPage.getTransactionPinValue(),
                expectedPin,
                "Transaction PIN value is incorrect."
        );
    }

    @Then("the transaction PIN field should not contain {string}")
    public void the_transaction_pin_field_should_not_contain(
            String invalidPin) {

        logger.info("Validating invalid transaction PIN rejection.");

        Assert.assertNotEquals(
                transactionsPage.getTransactionPinValue(),
                invalidPin,
                "Invalid transaction PIN was accepted."
        );
    }

    // =========================================================
    // Excel-Driven Transaction Execution
    // =========================================================

    @When("the user performs transaction using Excel test data row {int}")
    public void the_user_performs_transaction_using_excel_test_data_row(
            int rowNum) {

        logger.info(
                "Reading transaction data from Excel row: {}",
                rowNum
        );

        String filePath =
                "src/test/resources/testdata/TransactionData.xlsx";
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

        transactionsPage.performTransaction(
                sourceAccount,
                recipientAccount,
                transferType,
                amount,
                pin
        );

        logger.info(
                "Transaction execution completed for row: {}",
                rowNum
        );
    }

    @Then("the transaction should be completed successfully")
    public void the_transaction_should_be_completed_successfully() {

        logger.info("Validating transaction success.");

        Assert.assertTrue(
                transactionsPage.isTransactionSuccessful(),
                "Transaction was not completed successfully."
        );

        logger.info("Transaction completed successfully.");
    }
}