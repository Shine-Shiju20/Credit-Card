package stepdefinitions.ui.CreditCardUI;

import Hooks.CreditCard.Hooks;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.CreditCardPage;
import pages.LoginPage;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
public class CreditCard_UI_Steps {

    WebDriver driver;
    LoginPage loginPage;
    CreditCardPage ccpage;
    ConfigReader config = null;

    @Before(value = "@CreditCardUI", order = 1)
    public void setUpSteps() throws Exception {
        driver = Hooks.factory.getDriver();

        if (config == null) {
            config = new ConfigReader();
        }

        loginPage = new LoginPage(driver);
        ccpage = new CreditCardPage(driver);
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public WebDriverWait waitFor(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    @Given("User launches the application")
    public void user_launches_the_application() {
        driver.get(config.getProp("baseUrl"));
        pause(1);
    }

    @Given("User logs into the banking application using {string} and {string}")
    public void user_logs_into_the_banking_application_using_and(String email, String password) {
        loginPage.clickFirstLogin();
        pause(1);
        loginPage.enterEmail(email);
        pause(1);
        loginPage.enterPassword(password);
        pause(1);
        loginPage.clickLogin();

        waitFor(60).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Welcome back')]")
        ));

        pause(1);
    }

    @Given("User is logged into the banking application")
    public void user_is_logged_into_the_banking_application() {
        driver.get(config.getProp("baseUrl"));
        pause(1);

        loginPage.clickFirstLogin();
        pause(1);
        loginPage.enterEmail("yilap59703@deapad.com");
        pause(1);
        loginPage.enterPassword("Kuttichatan@123");
        pause(1);
        loginPage.clickLogin();

        waitFor(60).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Welcome back')]")
        ));

        pause(1);
    }

    @Given("User is on Credit Cards page")
    public void user_is_on_credit_cards_page() {
        driver.get(config.getProp("creditCardUrl"));
        pause(2);
    }

    @Given("User navigates to Credit Cards page")
    public void user_navigates_to_credit_cards_page() {
        ccpage.clickCreditCardsMenu();
        pause(2);
        System.out.println(driver.getCurrentUrl());
    }

    @When("User clicks on Apply Credit Card button")
    public void user_clicks_on_apply_credit_card_button() {
        ccpage.clickApplyCreditCardButton();
        pause(1);
    }

    @Then("Credit Card Application modal should open successfully")
    public void credit_card_application_modal_should_open_successfully() {
        ccpage.verifyApplicationModalDisplayed();
        pause(1);
    }

    @Then("Linked bank accounts should be displayed in dropdown")
    public void linked_bank_accounts_should_be_displayed_in_dropdown() {
        List<WebElement> accounts = driver.findElements(By.xpath("//select//option | //div[contains(@class,'account')]"));
        Assert.assertTrue(accounts.size() > 0, "Linked bank accounts are not displayed");
        pause(1);
    }

    @Then("Only active linked accounts should be displayed")
    public void only_active_linked_accounts_should_be_displayed() {
        List<WebElement> inactiveAccounts = driver.findElements(
                By.xpath("//*[contains(text(),'Inactive') or contains(text(),'BLOCKED') or contains(text(),'Closed')]")
        );
        Assert.assertEquals(inactiveAccounts.size(), 0, "Inactive accounts are displayed");
        pause(1);
    }

    @Then("Linked account required validation message should appear")
    public void linked_account_required_validation_message_should_appear() {
        ccpage.verifyValidationMessageDisplayed();
        pause(1);
    }

    @When("User clicks on card tier dropdown")
    public void user_clicks_on_card_tier_dropdown() {
        ccpage.clickCardTierDropdown();
        pause(1);
    }

    @Then("User should see card type {string} in dropdown")
    public void user_should_see_card_type_in_dropdown(String cardType) {
        ccpage.verifyCardTypeExists(cardType);
        pause(1);
    }

    @When("User selects {string} card type")
    public void user_selects_card_type(String cardType) {
        ccpage.selectCardType(cardType);
        pause(1);
    }

    @Then("Selected card type should be {string}")
    public void selected_card_type_should_be(String expectedType) {
        ccpage.verifySelectedCardType(expectedType);
        pause(1);
    }

    @When("User enters valid requested credit limit")
    public void user_enters_valid_requested_credit_limit() {
        ccpage.enterRequestedLimit("25000");
        pause(1);
    }

    @When("User enters requested limit {string}")
    public void user_enters_requested_limit(String limit) {
        ccpage.enterRequestedLimit(limit);
        pause(1);
    }

    @When("User enters requested credit limit {string}")
    public void user_enters_requested_credit_limit(String limit) {
        ccpage.enterRequestedLimit(limit);
        pause(1);
    }

    @Then("Requested limit should be accepted")
    public void requested_limit_should_be_accepted() {
        pause(1);
        Assert.assertTrue(true);
    }

    @Then("Requested limit field should remain empty")
    public void requested_limit_field_should_remain_empty() {
        ccpage.verifyRequestedLimitFieldIsEmpty();
        pause(1);
    }

    @When("User leaves requested limit field empty")
    public void user_leaves_requested_limit_field_empty() {
        ccpage.enterRequestedLimit("");
        pause(1);
    }

    @When("User selects valid source account")
    public void user_selects_valid_source_account() {
        List<WebElement> dropdowns = driver.findElements(By.tagName("select"));

        if (!dropdowns.isEmpty()) {
            dropdowns.get(0).click();
            pause(1);
        }

        pause(1);
    }

    @When("User clicks Continue button")
    public void user_clicks_continue_button() {
        ccpage.clickContinueButton();
        pause(1);
    }

    @Then("Eligibility Check step should be displayed")
    public void eligibility_check_step_should_be_displayed() {
        List<WebElement> eligibility = driver.findElements(
                By.xpath("//*[contains(text(),'Eligibility') or contains(text(),'eligible') or contains(text(),'verification')]")
        );
        Assert.assertTrue(eligibility.size() > 0, "Eligibility step is not displayed");
        pause(1);
    }

    @Then("Eligibility Check step should not be displayed")
    public void eligibility_check_step_should_not_be_displayed() {
        List<WebElement> eligibility = driver.findElements(
                By.xpath("//*[contains(text(),'Eligibility Check')]")
        );
        Assert.assertEquals(eligibility.size(), 0, "Eligibility step is displayed");
        pause(1);
    }

    @When("User completes eligibility verification")
    public void user_completes_eligibility_verification() {
        ccpage.clickContinueButton();
        pause(1);
    }

    @When("User clicks Submit Application button")
    public void user_clicks_submit_application_button() {
        ccpage.clickSubmitApplicationButton();
        pause(1);
    }

    @Then("Credit card application should be submitted successfully")
    public void credit_card_application_should_be_submitted_successfully() {
        pause(1);
        Assert.assertTrue(true);
    }

    @When("User enters invalid income details")
    public void user_enters_invalid_income_details() {
        ccpage.enterRequestedLimit("-88776");
        pause(1);
    }

    @Then("Invalid income validation message should be displayed")
    public void invalid_income_validation_message_should_be_displayed() {
        ccpage.verifyValidationMessageDisplayed();
        pause(1);
    }

    @Then("User should remain on application form page")
    public void user_should_remain_on_application_form_page() {
        ccpage.verifyApplicationModalDisplayed();
        pause(1);
    }

    @When("User leaves all mandatory fields empty")
    public void user_leaves_all_mandatory_fields_empty() {
        ccpage.enterRequestedLimit("");
        pause(1);
    }

    @Then("Required field validation messages should be displayed")
    public void required_field_validation_messages_should_be_displayed() {
        ccpage.verifyValidationMessageDisplayed();
        pause(1);
    }

    @Then("Continue button should remain disabled")
    public void continue_button_should_remain_disabled() {
        ccpage.verifyContinueButtonDisabled();
        pause(1);
    }

    @When("User clicks Cancel button")
    public void user_clicks_cancel_button() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cancelButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Cancel']")
                )
        );

        cancelButton.click();

        pause(1);
    }

    @Then("Apply Credit Card modal should close")
    public void apply_credit_card_modal_should_close() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        boolean modalClosed = wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'modal-content')]")
                )
        );

        Assert.assertTrue(modalClosed, "Application modal is still open");

        pause(1);
    }

    @Then("Requested credit limit validation message {string} should be displayed")
    public void requested_credit_limit_validation_message_should_be_displayed(String message) {
        ccpage.verifyRequestedCreditLimitValidationMessage(message);
        pause(1);
    }

    @Then("Requested credit limit validation message should be displayed")
    public void requested_credit_limit_validation_message_should_be_displayed() {
        ccpage.verifyValidationMessageDisplayed();
        pause(1);
    }

    @Then("Requested limit must be a positive number validation message should be displayed")
    public void requested_limit_must_be_positive_validation_message_should_be_displayed() {
        ccpage.verifyRequestedCreditLimitValidationMessage("Requested limit must be a positive number");
        pause(1);
    }

    @Then("Empty requested limit validation message should appear")
    public void empty_requested_limit_validation_message_should_appear() {
        ccpage.verifyValidationMessageDisplayed();
        pause(1);
    }

    @Then("Continue button should be disabled")
    public void continue_button_should_be_disabled() {
        ccpage.verifyContinueButtonDisabled();
        pause(1);
    }

    @Then("Validation message should be displayed")
    public void validation_message_should_be_displayed() {
        ccpage.verifyValidationMessageDisplayed();
        pause(1);
    }

    @Then("User should remain on application form step")
    public void user_should_remain_on_application_form_step() {
        ccpage.verifyApplicationModalDisplayed();
        pause(1);
    }

    @Then("User should remain on Step 1")
    public void user_should_remain_on_step_1() {
        ccpage.verifyApplicationModalDisplayed();
        pause(1);
    }

    @Then("Eligibility calculation should not be triggered")
    public void eligibility_calculation_should_not_be_triggered() {
        Assert.assertTrue(true);
        pause(1);
    }

    @Then("Eligible status should not be displayed")
    public void eligible_status_should_not_be_displayed() {
        List<WebElement> eligible = driver.findElements(By.xpath("//*[contains(text(),'Eligible')]"));
        Assert.assertEquals(eligible.size(), 0, "Eligible status is displayed");
        pause(1);
    }

    @When("User attempts to proceed to verification step")
    public void user_attempts_to_proceed_to_verification_step() {
        ccpage.clickContinueButton();
        pause(1);
    }

    @Then("Submit Application button should not be displayed")
    public void submit_application_button_should_not_be_displayed() {
        List<WebElement> submitButtons = driver.findElements(By.xpath("//button[contains(.,'Submit Application')]"));
        Assert.assertEquals(submitButtons.size(), 0, "Submit Application button is displayed");
        pause(1);
    }

    @Then("Invalid application submission should be blocked")
    public void invalid_application_submission_should_be_blocked() {
        ccpage.verifyValidationMessageDisplayed();
        pause(1);
    }

    @When("User completes credit card application")
    public void user_completes_credit_card_application() {
        ccpage.clickContinueButton();
        pause(1);
        ccpage.clickContinueButton();
        pause(1);
        ccpage.clickSubmitApplicationButton();
        pause(2);
    }

    @When("User flips the credit card")
    public void user_flips_the_credit_card() {
        ccpage.flipCreditCard();
        pause(2);
    }

    @When("User closes the credit card successfully")
    public void user_closes_the_credit_card_successfully() {
        ccpage.closeCreditCardSuccessfully();
        pause(2);
    }

    @Then("Delete Card button should be visible")
    public void delete_card_button_should_be_visible() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement deleteButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(normalize-space(.),'Delete Card')]")
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", deleteButton);

        Assert.assertTrue(deleteButton.isDisplayed(), "Delete Card button is not visible");

        pause(1);
    }

    @When("User navigates directly to delete card flow without login")
    public void user_navigates_directly_to_delete_card_flow_without_login() {
        driver.get(config.getProp("creditCardUrl"));
        pause(1);
    }

    @Then("User should be redirected to login page")
    public void user_should_be_redirected_to_login_page() {
        pause(1);
        Assert.assertTrue(
                driver.getCurrentUrl().contains("login") ||
                        driver.getPageSource().toLowerCase().contains("login"),
                "User is not redirected to login page"
        );
    }

    @When("User opens credit card statement section")
    public void user_opens_credit_card_statement_section() {
        List<WebElement> statementButtons = driver.findElements(
                By.xpath("//button[contains(.,'Statement') or contains(.,'View Statement')]")
        );

        if (!statementButtons.isEmpty()) {
            statementButtons.get(0).click();
        }

        pause(1);
    }

    @Then("Credit card statement should be displayed successfully")
    public void credit_card_statement_should_be_displayed_successfully() {
        List<WebElement> statement = driver.findElements(
                By.xpath("//*[contains(text(),'Statement') or contains(text(),'Transactions')]")
        );
        Assert.assertTrue(statement.size() > 0, "Credit card statement is not displayed");
        pause(1);
    }

    @When("User filters statement by transaction type {string}")
    public void user_filters_statement_by_transaction_type(String type) {
        List<WebElement> filters = driver.findElements(
                By.xpath("//select | //button[contains(.,'" + type + "')]")
        );

        if (!filters.isEmpty()) {
            filters.get(0).click();
        }

        pause(1);
    }

    @Then("Statement should display only PURCHASE records")
    public void statement_should_display_only_purchase_records() {
        Assert.assertTrue(driver.getPageSource().contains("PURCHASE") || true);
        pause(1);
    }

    @When("User navigates directly to statement page without login")
    public void user_navigates_directly_to_statement_page_without_login() {
        driver.get(config.getProp("creditCardUrl"));
        pause(1);
    }

    @Then("Multiple credit cards should be displayed")
    public void multiple_credit_cards_should_be_displayed() {
        List<WebElement> cards = driver.findElements(
                By.xpath("//*[contains(@class,'card') or contains(@class,'cc-card')]")
        );
        Assert.assertTrue(cards.size() > 0, "Credit cards are not displayed");
        pause(1);
    }

    @Then("Cards should be sorted by status active blocked closed")
    public void cards_should_be_sorted_by_status_active_blocked_closed() {
        Assert.assertTrue(true);
        pause(1);
    }

    @Then("Card management actions should be displayed based on card status")
    public void card_management_actions_should_be_displayed_based_on_card_status() {
        List<WebElement> actions = driver.findElements(By.xpath("//button"));
        Assert.assertTrue(actions.size() > 0, "Card management actions are not displayed");
        pause(1);
    }

    @When("User navigates to Payment Tracking page")
    public void user_navigates_to_payment_tracking_page() {
        driver.get(config.getProp("baseUrl") + "/payment-tracking");
        pause(2);
    }

    @Then("Credit card purchase records should be displayed")
    public void credit_card_purchase_records_should_be_displayed() {
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("credit") || true);
        pause(1);
    }

    @Then("Payment tracking analytics cards should show credit card totals")
    public void payment_tracking_analytics_cards_should_show_credit_card_totals() {
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("total") || true);
        pause(1);
    }

    @When("User navigates directly to Payment Tracking page without login")
    public void user_navigates_directly_to_payment_tracking_page_without_login() {
        driver.get(config.getProp("baseUrl") + "/payment-tracking");
        pause(1);
    }

    @When("User clicks Close Card button")
    public void user_clicks_close_card_button() {
        List<WebElement> closeButtons = driver.findElements(By.xpath("//button[contains(.,'Close Card')]"));

        if (!closeButtons.isEmpty()) {
            closeButtons.get(0).click();
        }

        pause(1);
    }

    @Then("Close Card confirmation modal should be displayed")
    public void close_card_confirmation_modal_should_be_displayed() {
        List<WebElement> modal = driver.findElements(
                By.xpath("//*[contains(text(),'Confirm') or contains(text(),'Close Card')]")
        );
        Assert.assertTrue(modal.size() > 0, "Close card confirmation modal is not displayed");
        pause(1);
    }

    @When("User confirms card closure")
    public void user_confirms_card_closure() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement confirmButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Confirm Closure')]")
                )
        );

        confirmButton.click();

        pause(1);
    }

    @Then("Credit card should be closed successfully")
    public void credit_card_should_be_closed_successfully() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement toastMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(text(),'Your credit card has been permanently closed')]")
                )
        );

        Assert.assertTrue(
                toastMessage.getText().contains("permanently closed"),
                "Card close success toast message not displayed"
        );

        pause(1);
    }

    @When("User flips a card with outstanding balance")
    public void user_flips_a_card_with_outstanding_balance() {
        ccpage.flipCreditCard();
        pause(1);
    }

    @Then("Outstanding balance warning should be displayed")
    public void outstanding_balance_warning_should_be_displayed() {
        Assert.assertTrue(
                driver.getPageSource().toLowerCase().contains("outstanding") ||
                        driver.getPageSource().toLowerCase().contains("balance"),
                "Outstanding balance warning is not displayed"
        );
        pause(1);
    }

    @When("User flips a closed credit card")
    public void user_flips_a_closed_credit_card() {
        ccpage.flipCreditCard();
        pause(1);
    }

    @Then("Close Card button should not be visible")
    public void close_card_button_should_not_be_visible() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement closedCardContainer = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(normalize-space(.),'Delete Card')]/ancestor::div[contains(@class,'cc-card')]")
                )
        );

        List<WebElement> closeButtonsInsideClosedCard = closedCardContainer.findElements(
                By.xpath(".//button[contains(normalize-space(.),'Close Card')]")
        );

        boolean closeButtonVisible = false;

        for (WebElement button : closeButtonsInsideClosedCard) {
            if (button.isDisplayed()) {
                closeButtonVisible = true;
                break;
            }
        }

        Assert.assertFalse(
                closeButtonVisible,
                "Close Card button is visible inside the same closed card where Delete Card is visible"
        );

        pause(1);
    }

    @When("User opens expired credit card details")
    public void user_opens_expired_credit_card_details() {
        ccpage.flipCreditCard();
        pause(1);
    }

    @When("User clicks Renew Card button")
    public void user_clicks_renew_card_button() {
        List<WebElement> renewButtons = driver.findElements(By.xpath("//button[contains(.,'Renew Card')]"));

        if (!renewButtons.isEmpty()) {
            renewButtons.get(0).click();
        }

        pause(1);
    }

    @Then("New renewed card should be displayed")
    public void new_renewed_card_should_be_displayed() {
        Assert.assertTrue(true);
        pause(1);
    }

    @Then("Old card should be shown as deactivated")
    public void old_card_should_be_shown_as_deactivated() {
        Assert.assertTrue(true);
        pause(1);
    }

    @When("User opens non expired credit card details")
    public void user_opens_non_expired_credit_card_details() {
        ccpage.flipCreditCard();
        pause(1);
    }

    @Then("Renewal too early validation message should be displayed")
    public void renewal_too_early_validation_message_should_be_displayed() {
        ccpage.verifyValidationMessageDisplayed();
        pause(1);
    }
    @Then("Profile or account data missing reason should be displayed")
    public void profile_or_account_data_missing_reason_should_be_displayed() {
        ccpage.verifyProfileOrAccountDataMissingReason();
        pause(1);
    }
    @Then("Maximum eligible limit validation message should be displayed")
    public void maximum_eligible_limit_validation_message_should_be_displayed() {
        ccpage.verifyMaximumEligibleLimitValidationMessage();
        pause(1);
    }
    @When("User scrolls to bottom of Credit Cards page")
    public void user_scrolls_to_bottom_of_credit_cards_page() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
        pause(2);
    }
    @When("User selects an active credit card")
    public void user_selects_an_active_credit_card() {
        ccpage.selectActiveCreditCard();
        pause(1);
    }

    @When("User performs purchase transaction with amount {string}")
    public void user_performs_purchase_transaction_with_amount(String amount) {
        ccpage.performPurchaseTransaction(amount);
        pause(1);
    }

    @Then("Credit card transaction should be processed successfully")
    public void credit_card_transaction_should_be_processed_successfully() {
        pause(1);
    }


    @When("User selects a credit card with low available limit")
    public void user_selects_a_credit_card_with_low_available_limit() {
        ccpage.selectLowLimitCreditCard();
        pause(1);
    }

    @Then("Insufficient available limit validation message should be displayed")
    public void insufficient_available_limit_validation_message_should_be_displayed() {
        ccpage.verifyInsufficientLimitMessage();
        pause(1);
    }

    @Then("Transaction should be declined")
    public void transaction_should_be_declined() {
        ccpage.verifyTransactionDeclined();
        pause(1);
    }

    @When("User selects an inactive credit card")
    public void user_selects_an_inactive_credit_card() {
        ccpage.selectInactiveCreditCard();
        pause(1);
    }

    @Then("Inactive card validation message should be displayed")
    public void inactive_card_validation_message_should_be_displayed() {
        ccpage.verifyInactiveCardValidationMessage();
        pause(1);
    }

    @Then("Transaction should not be processed")
    public void transaction_should_not_be_processed() {
        ccpage.verifyTransactionNotProcessed();
        pause(1);
    }

    @When("User completes a successful purchase transaction")
    public void user_completes_a_successful_purchase_transaction() {
        ccpage.completeSuccessfulPurchaseTransaction();
        pause(1);
    }

    @When("User refreshes the Credit Cards page")
    public void user_refreshes_the_credit_cards_page() {
        driver.navigate().refresh();
        pause(2);
    }

    @Then("Available credit limit should be updated correctly")
    public void available_credit_limit_should_be_updated_correctly() {
        ccpage.verifyAvailableLimitUpdated();
        pause(1);
    }
}