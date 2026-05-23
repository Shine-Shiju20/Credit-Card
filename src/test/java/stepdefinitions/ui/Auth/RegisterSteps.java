package stepdefinitions.ui.Auth;

import Hooks.Auth.Hooks;

import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import pages.RegisterPage;

public class RegisterSteps {

    WebDriver driver;

    RegisterPage register;

    // Launch Banking Application

    @Given("User launches banking application")
    public void user_launches_banking_application() {
        this.driver = Hooks.factory.getDriver();
        this.register = new RegisterPage(driver);
        driver.get("http://localhost:3000");
    }

    // Click Home Register Button

    @And("User clicks on Home Register button")
    public void user_clicks_on_home_register_button() {
        register.clickHomeRegisterButton();
    }

    // Enter Registration Details

    @When("User enters registration details with {string} {string} {string} {string} {string}")
    public void user_enters_registration_details_with(
            String email,
            String phone,
            String password,
            String confirmPassword,
            String pin) {

        register.enterEmail(email);

        register.enterPhoneNumber(phone);

        register.enterPassword(password);

        register.enterConfirmPassword(confirmPassword);

        register.enterTransactionPin(pin);
    }

    // Click Next Button

    @And("User clicks on Next button")
    public void user_clicks_on_next_button()
            throws InterruptedException {

        Thread.sleep(2000);
        register.clickNextButton();
        Thread.sleep(2000);
    }

    // Navigate To KYC Page

    @Then("User should navigate to KYC page")
    public void user_should_navigate_to_kyc_page() {

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"input-group\"]//label[text()=\" Full Name (as per Aadhaar)\"]")).isDisplayed());
    }

    // Enter KYC Details

    @And("User enters KYC details with {string} {string} {string} {string} {string} {string} {string} {string}")
    public void user_enters_kyc_details_with(

            String fullName,
            String dob,
            String gender,
            String address,
            String aadhaar,
            String pan,
            String income,
            String occupation) {

        register.enterFullName(fullName);

        register.enterDOB(dob);

        register.selectGender(gender);

        register.enterAddress(address);

        register.enterAadhaar(aadhaar);

        register.enterPAN(pan);

        register.enterAnnualIncome(income);

        register.enterOccupation(occupation);
    }

    // Submit KYC Registration Form

    @And("User submits KYC registration form")
    public void user_submits_kyc_registration_form()
            throws InterruptedException {

        Thread.sleep(2000);

        register.clickSubmitKYCButton();
        Thread.sleep(30000);

    }

    // Registration Success

    @Then("User registration should proceed successfully")
    public void user_registration_should_proceed_successfully() {

        String pageSource = driver.getPageSource();
        Assert.assertTrue(

                pageSource.contains("OTP")
                        ||
                        pageSource.contains("Verification")
                        ||
                        pageSource.contains("Registered")
        );
    }

    // Email Validation Message

    @Then("Proper registration email validation message should be displayed as {string}")
    public void proper_registration_email_validation_message_should_be_displayed_as(

            String expectedResult) {

        String actualMessage = register.getValidationMessage();
        System.out.println("Email Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Duplicate User Validation

    @Then("Duplicate user validation message should be displayed as {string}")
    public void duplicate_user_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Duplicate User Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Required Field Validation

    @Then("Required field validation message should be displayed as {string}")
    public void required_field_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Required Field Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Phone Validation Message

    @Then("invalid phone validation should be displayed as {string}")
    public void invalid_phone_validation_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Phone Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Phone Restriction Validation

    @Then("phone field should restrict value as {string}")
    public void phone_field_should_restrict_value_as(

            String expectedResult) {
        String actualPhone = register.getPhoneFieldValue();
        System.out.println("Actual Phone Value : " + actualPhone);
        Assert.assertEquals(actualPhone, expectedResult);
    }

    // Password Validation Message

    @Then("Registration password validation message should be displayed as {string}")
    public void registration_password_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Password Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Password Mismatch Validation

    @Then("Registration password mismatch validation message should be displayed as {string}")
    public void registration_password_mismatch_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Password Mismatch Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Confirm Password Required Validation

    @Then("Confirm password required validation message should be displayed as {string}")
    public void confirm_password_required_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Confirm Password Required Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Invalid PIN Validation

    @Then("invalid PIN validation should be displayed as {string}")
    public void invalid_pin_validation_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("PIN Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // PIN Restriction Validation

    @Then("PIN field should restrict value as {string}")
    public void pin_field_should_restrict_value_as(

            String expectedResult) {
        String actualPin = register.getPinFieldValue();
        System.out.println("Actual PIN Value : " + actualPin);
        Assert.assertEquals(actualPin, expectedResult);
    }

    // DOB Validation Message

    @Then("DOB validation message should be displayed as {string}")
    public void dob_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("DOB Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Income Validation Message

    @Then("income validation message should be displayed as {string}")
    public void income_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Income Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // PAN Validation Message

    @Then("PAN validation message should be displayed as {string}")
    public void pan_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("PAN Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Aadhaar Validation Message

    @Then("Aadhaar validation message should be displayed as {string}")
    public void aadhaar_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Aadhaar Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Full Name Validation Message

    @Then("Full Name validation message should be displayed as {string}")
    public void full_name_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Full Name Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }
    // Address Validation Message

    @Then("Address validation message should be displayed as {string}")
    public void address_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Address Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // Occupation Validation Message

    @Then("Occupation validation message should be displayed as {string}")
    public void occupation_validation_message_should_be_displayed_as(

            String expectedResult) {
        String actualMessage = register.getValidationMessage();
        System.out.println("Occupation Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);
    }

    // PAN Restriction Validation

    @Then("PAN field should restrict value as {string}")
    public void pan_field_should_restrict_value_as(

            String expectedResult) {
        String actualPan = register.getPanFieldValue();
        System.out.println("Actual PAN Value : " + actualPan);
        Assert.assertEquals(actualPan, expectedResult);
    }

    // Aadhaar Restriction Validation

    @Then("Aadhaar field should restrict value as {string}")
    public void aadhaar_field_should_restrict_value_as(

            String expectedResult) {
        String actualAadhaar = register.getAadhaarFieldValue();
        System.out.println("Actual Aadhaar Value : " + actualAadhaar);
        Assert.assertEquals(actualAadhaar, expectedResult);
    }
}