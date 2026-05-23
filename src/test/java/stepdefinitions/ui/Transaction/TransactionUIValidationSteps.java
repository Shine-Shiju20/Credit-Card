//package stepdefinitions;
//
//import org.testng.Assert;
//
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import pages.TransactionUIValidationPage;
//import utils.DriverFactory;
//
//public class TransactionUIValidationSteps {
//
//    private TransactionUIValidationPage uiPage;
//
//    public TransactionUIValidationSteps() {
//        uiPage = new TransactionUIValidationPage(
//                new DriverFactory().getDriver()
//        );
//    }
//
//    // =========================================================
//    // Transfer Type Dropdown Validation
//    // =========================================================
//
//    @Then("the transfer type dropdown should contain at least {int} options")
//    public void the_transfer_type_dropdown_should_contain_at_least_options(
//            Integer expectedCount) {
//
//        Assert.assertTrue(
//                uiPage.isTransferTypeDropdownDisplayed(),
//                "Transfer type dropdown is not displayed."
//        );
//
//        Assert.assertTrue(
//                uiPage.getTransferTypeOptionCount() >= expectedCount,
//                "Transfer type dropdown contains fewer options than expected."
//        );
//    }
//
//    // =========================================================
//    // Amount Field Validation
//    // =========================================================
//
//    @When("the user enters a valid amount {string}")
//    public void the_user_enters_a_valid_amount(String amount) {
//        uiPage.enterAmount(amount);
//    }
//
//    @Then("the amount field should display {string}")
//    public void the_amount_field_should_display(String expectedValue) {
//        Assert.assertEquals(
//                uiPage.getAmountValue(),
//                expectedValue,
//                "Amount field value is incorrect."
//        );
//    }
//
//    @When("the user enters an invalid amount {string}")
//    public void the_user_enters_an_invalid_amount(String amount) {
//        uiPage.enterAmount(amount);
//    }
//
//    @Then("the amount field should not display {string}")
//    public void the_amount_field_should_not_display(String invalidValue) {
//        Assert.assertNotEquals(
//                uiPage.getAmountValue(),
//                invalidValue,
//                "Invalid amount was accepted."
//        );
//    }
//
//    // =========================================================
//    // Confirm Button Validation
//    // =========================================================
//
//    @When("the user clicks the Confirm button")
//    public void the_user_clicks_the_confirm_button() {
//        uiPage.clickConfirmButton();
//    }
//
//    // NOTE:
//    // Do NOT add:
//    // @Then("the error message should be displayed")
//    // because this step already exists in NegativeTransactionsSteps.java
//
//    // =========================================================
//    // Left Navigation Validation
//    // =========================================================
//
//    @Then("the left navigation should be displayed")
//    public void the_left_navigation_should_be_displayed() {
//        Assert.assertTrue(
//                uiPage.isSidebarDisplayed(),
//                "Left navigation is not displayed."
//        );
//    }
//
//    // =========================================================
//    // Form Alignment Validation
//    // =========================================================
//
//    @Then("the transaction form should be displayed properly")
//    public void the_transaction_form_should_be_displayed_properly() {
//        Assert.assertTrue(
//                uiPage.isTransactionFormDisplayed(),
//                "Transaction form is not displayed properly."
//        );
//    }
//
//    // =========================================================
//    // Labels and Placeholders Validation
//    // =========================================================
//
//    @Then("all labels should be displayed")
//    public void all_labels_should_be_displayed() {
//        Assert.assertTrue(
//                uiPage.allLabelsDisplayed(),
//                "Some labels are missing."
//        );
//    }
//
//    @Then("all input fields should have placeholders")
//    public void all_input_fields_should_have_placeholders() {
//        Assert.assertTrue(
//                uiPage.allFieldsHavePlaceholders(),
//                "Some input fields do not have placeholders."
//        );
//    }
//
//    // =========================================================
//    // Responsive Validation
//    // =========================================================
//
//    @Then("the transactions page should be responsive on mobile")
//    public void the_transactions_page_should_be_responsive_on_mobile() {
//        Assert.assertTrue(
//                uiPage.isResponsiveOnMobile(),
//                "Transactions page is not responsive on mobile."
//        );
//    }
//
//    // =========================================================
//    // Transaction History / Success Toast Validation
//    // =========================================================
//
//    @Then("the success toast message should be displayed")
//    public void the_success_toast_message_should_be_displayed() {
//        Assert.assertTrue(
//                uiPage.isSuccessToastDisplayed(),
//                "Success toast message was not displayed."
//        );
//    }
//
//    // =========================================================
//    // Unauthorized Access Restriction
//    // =========================================================
//
//    @When("the user logs out by clearing the session")
//    public void the_user_logs_out_by_clearing_the_session() {
//        uiPage.logoutSession();
//    }
//
//    @Then("the login page should be displayed")
//    public void the_login_page_should_be_displayed() {
//        Assert.assertTrue(
//                uiPage.isLoginPageDisplayed(),
//                "Login page was not displayed."
//        );
//    }
//}


package stepdefinitions.ui.Transaction;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.TransactionUIValidationPage;
import utils.Transactions.DriverFactory;
import utils.Log;

public class TransactionUIValidationSteps {

    private TransactionUIValidationPage uiPage;

    // Logger
    private static final Logger logger =
            Log.getLogger(TransactionUIValidationSteps.class);

    public TransactionUIValidationSteps() {
        uiPage = new TransactionUIValidationPage(
                DriverFactory.getDriver()
        );
        logger.info("TransactionUIValidationSteps initialized.");
    }

    // =========================================================
    // Transfer Type Dropdown Validation
    // =========================================================

    @Then("the transfer type dropdown should contain at least {int} options")
    public void the_transfer_type_dropdown_should_contain_at_least_options(
            Integer expectedCount) {

        logger.info(
                "Validating transfer type dropdown contains at least {} options.",
                expectedCount
        );

        Assert.assertTrue(
                uiPage.isTransferTypeDropdownDisplayed(),
                "Transfer type dropdown is not displayed."
        );

        Assert.assertTrue(
                uiPage.getTransferTypeOptionCount() >= expectedCount,
                "Transfer type dropdown contains fewer options than expected."
        );

        logger.info("Transfer type dropdown validation passed.");
    }

    // =========================================================
    // Amount Field Validation
    // =========================================================

    @When("the user enters a valid amount {string}")
    public void the_user_enters_a_valid_amount(String amount) {
        logger.info("Entering valid amount: {}", amount);
        uiPage.enterAmount(amount);
    }

    @Then("the amount field should display {string}")
    public void the_amount_field_should_display(String expectedValue) {

        logger.info(
                "Validating amount field displays: {}",
                expectedValue
        );

        Assert.assertEquals(
                uiPage.getAmountValue(),
                expectedValue,
                "Amount field value is incorrect."
        );
    }

    @When("the user enters an invalid amount {string}")
    public void the_user_enters_an_invalid_amount(String amount) {
        logger.info("Entering invalid amount: {}", amount);
        uiPage.enterAmount(amount);
    }

    @Then("the amount field should not display {string}")
    public void the_amount_field_should_not_display(String invalidValue) {

        logger.info(
                "Validating amount field does not display: {}",
                invalidValue
        );

        Assert.assertNotEquals(
                uiPage.getAmountValue(),
                invalidValue,
                "Invalid amount was accepted."
        );
    }

    // =========================================================
    // Confirm Button Validation
    // =========================================================

    @When("the user clicks the Confirm button")
    public void the_user_clicks_the_confirm_button() {
        logger.info("Clicking Confirm button.");
        uiPage.clickConfirmButton();
    }

    // NOTE:
    // Do NOT add:
    // @Then("the error message should be displayed")
    // because this step already exists in NegativeTransactionsSteps.java

    // =========================================================
    // Left Navigation Validation
    // =========================================================

    @Then("the left navigation should be displayed")
    public void the_left_navigation_should_be_displayed() {

        logger.info("Validating left navigation display.");

        Assert.assertTrue(
                uiPage.isSidebarDisplayed(),
                "Left navigation is not displayed."
        );
    }

    // =========================================================
    // Form Alignment Validation
    // =========================================================

    @Then("the transaction form should be displayed properly")
    public void the_transaction_form_should_be_displayed_properly() {

        logger.info("Validating transaction form display.");

        Assert.assertTrue(
                uiPage.isTransactionFormDisplayed(),
                "Transaction form is not displayed properly."
        );
    }

    // =========================================================
    // Labels and Placeholders Validation
    // =========================================================

    @Then("all labels should be displayed")
    public void all_labels_should_be_displayed() {

        logger.info("Validating all labels are displayed.");

        Assert.assertTrue(
                uiPage.allLabelsDisplayed(),
                "Some labels are missing."
        );
    }

    @Then("all input fields should have placeholders")
    public void all_input_fields_should_have_placeholders() {

        logger.info("Validating all input fields have placeholders.");

        Assert.assertTrue(
                uiPage.allFieldsHavePlaceholders(),
                "Some input fields do not have placeholders."
        );
    }

    // =========================================================
    // Responsive Validation
    // =========================================================

    @Then("the transactions page should be responsive on mobile")
    public void the_transactions_page_should_be_responsive_on_mobile() {

        logger.info("Validating mobile responsiveness.");

        Assert.assertTrue(
                uiPage.isResponsiveOnMobile(),
                "Transactions page is not responsive on mobile."
        );
    }

    // =========================================================
    // Transaction History / Success Toast Validation
    // =========================================================

    @Then("the success toast message should be displayed")
    public void the_success_toast_message_should_be_displayed() {

        logger.info("Validating success toast message.");

        Assert.assertTrue(
                uiPage.isSuccessToastDisplayed(),
                "Success toast message was not displayed."
        );
    }

    // =========================================================
    // Unauthorized Access Restriction
    // =========================================================

    @When("the user logs out by clearing the session")
    public void the_user_logs_out_by_clearing_the_session() {

        logger.info("Logging out by clearing session.");

        uiPage.logoutSession();
    }

    @Then("the login page should be displayed")
    public void the_login_page_should_be_displayed() {

        logger.info("Validating login page is displayed.");

        Assert.assertTrue(
                uiPage.isLoginPageDisplayed(),
                "Login page was not displayed."
        );

        logger.info("Login page displayed successfully.");
    }
}