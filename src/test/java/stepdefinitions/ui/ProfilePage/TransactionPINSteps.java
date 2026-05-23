package stepdefinitions.ui.ProfilePage;

import Hooks.Profile.Hooks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ProfilePage;
import utils.LoggerUtility;


public class TransactionPINSteps {

    WebDriver driver;

    ProfilePage page;


    public TransactionPINSteps(){

        this.driver = Hooks.factory.getDriver();

        this.page = new ProfilePage(this.driver);
    }



    // =========================
    // TRANSACTION PIN ACTIONS
    // =========================

    @When("User clicks Reset Transaction PIN button")
    public void user_clicks_reset_transaction_pin_button() {
        this.page.ClickForgotTransactionPinButton();
        LoggerUtility.info("clicks Forgot transaction button");
    }


    @And("User enters account password {string}")
    public void user_enters_account_password(String password) {
        this.page.SetTransactionPasswordField(password);
        LoggerUtility.info("Enters Account password : " + password);
    }


    @And("User enters new transaction PIN {string}")
    public void user_enters_new_transaction_pin(String pin) {
        this.page.SetTransactionResetField(pin);
        LoggerUtility.info("Enters the new pin " + pin);
    }


    @And("User enters confirm transaction PIN {string}")
    public void user_enters_confirm_transaction_pin(String confirmPin) {
        this.page.SetTransactionResetConfirmField(confirmPin);
        LoggerUtility.info("Enters the confirm pin field : " + confirmPin);
    }

    @Then("User should see required validation message for new transaction PIN field")
    public void user_should_see_required_validation_message_for_new_transaction_pin_field() {

        LoggerUtility.info("Validating Transaction Reset Field Validation Message");
        String actualMessage = this.page.GetTransactionResetFieldValidationMessage();
        String expectedMessage = "Please fill out this field.";
        LoggerUtility.info("Expected Validation Message: " + expectedMessage);
        LoggerUtility.info("Actual Validation Message: " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage, "Transaction Reset Field validation message mismatch.\n" + "Expected: " + expectedMessage + "\n" + "Actual: " + actualMessage);
        LoggerUtility.pass("Transaction Reset Field validation message verified successfully");
    }

    @Then("User should see required validation message for confirm transaction PIN field")
    public void user_should_see_required_validation_message_for_confirm_transaction_pin_field() {

        LoggerUtility.info("Validating Transaction Reset Confirm Field Validation Message");

        String actualMessage = this.page.GetTransactionResetConfirmFieldValidationMessage();

        String expectedMessage = "Please fill out this field.";

        LoggerUtility.info("Expected Validation Message: " + expectedMessage);
        LoggerUtility.info("Actual Validation Message: " + actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage, "Transaction Reset Confirm Field validation message mismatch.\n" + "Expected: " + expectedMessage + "\n" + "Actual: " + actualMessage);

        LoggerUtility.pass("Transaction Reset Confirm Field validation message verified successfully");
    }

    @And("User clicks Confirm Reset PIN button")
    public void user_clicks_confirm_reset_pin_button() {

        this.page.ClickForgotTransactionResetPinButton();
        LoggerUtility.info("Clicks Forgot Transaction Reset button");
    }

    @Then("User should see required field validation message")
    public void user_should_see_required_field_validation_message() {

        LoggerUtility.info("Validating Transaction Password Field Validation Message");

        String actualMessage =
                this.page.GetTransactionPasswordValidationMessage();

        String expectedMessage = "Please fill out this field.";

        LoggerUtility.info("Expected Validation Message: " + expectedMessage);
        LoggerUtility.info("Actual Validation Message: " + actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage, "Transaction Password Field validation message mismatch.\n" + "Expected: " + expectedMessage + "\n" + "Actual: " + actualMessage);

        LoggerUtility.pass("Transaction Password Field validation message verified successfully");
    }

    @Then("Transaction PIN field should restrict input of {string} to 6 digits")
    public void transaction_pin_field_should_restrict_input_to_6_digits(String pin) throws InterruptedException {
        String actual = this.page.GetTransactionResetPinField();
        String expected = pin.substring(0, 6);
        Assert.assertEquals(actual, expected, "Transaction PIN field accepted more than 6 digits");
    }

}