package stepdefinitions.ui.ProfilePage;


import Hooks.Profile.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProfilePage;
import pages.common.CommonDashBoard;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.LoggerUtility;

import java.io.IOException;
import java.time.Duration;

public class ProfileStepDefinition {
    WebDriver driver;
    ConfigReader config;
    String profileUrl;
    private ProfilePage page;
    String sucessMessage;

    @Given("User launches the banking application")
    public void user_launches_the_banking_application() throws IOException {
//        DriverFactory factory = new DriverFactory();
        this.config = new ConfigReader();
//        System.out.println(this.config.getProp("baseUrl"));

        this.driver = Hooks.factory.getDriver();
        Hooks.factory.FetchPage(this.config.getProp("baseUrl"));
        this.profileUrl = config.getProp("profileUrl");
        this.page = new ProfilePage(this.driver);
    }

    @And("User logs in using valid credentials")
    public void user_logs_in_using_valid_credentials() {
        LoginPage logingPage = new LoginPage(this.driver);
        logingPage.clickFirstLogin();
        logingPage.enterEmail("abhirampb9@gmail.com");
        logingPage.enterPassword("Strong@123");
        logingPage.clickLogin();
        LoggerUtility.info("Logs in Using KYC verified Account");
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.urlContains("dashboard")
        );
    }

    @And("User navigates to the Profile page")
    public void user_navigates_to_the_profile_page() throws InterruptedException {
        Thread.sleep(100);
        LoggerUtility.info("Navigated to profile URL: " + this.config.getProp("profileUrl"));
        this.driver.get(this.config.getProp("profileUrl"));
        Thread.sleep(100);
    }


    // Profile Page Load
    @Then("User profile page should load successfully")
    public void user_profile_page_should_load_successfully() {
        ProfilePage profileInstance = new ProfilePage(this.driver);
        LoggerUtility.info("profile Page Loaded SuccessFully");
        profileInstance.WaitTillProfileVisibility();
    }


    // =========================
    // Expired Session
    // =========================

    @When("User clicks on Logout button")
    public void user_clicks_on_logout_button() {
        CommonDashBoard DashBoard = new CommonDashBoard(this.driver);
        LoggerUtility.info("Clicks Logout button");
        DashBoard.ClickLogout();
    }

    @And("User manually navigates to Profile page URL")
    public void user_manually_navigates_to_profile_page_url() {
        this.driver.get(profileUrl);
        LoggerUtility.info("Navigates to profile Page");
    }

    @Then("User should get redirected to Home page")
    public void user_should_get_redirected_to_home_page() {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.urlToBe("http://localhost:3000/")
        );
        LoggerUtility.info("redirected to Home Page ");
    }



    // =========================
    // Update Profile
    // =========================

    @When("User updates profile details")
    public void user_updates_profile_details() throws IOException, InterruptedException {
        ExcelReader reader = new ExcelReader("C:\\Users\\abhiram.x1\\Desktop\\Testing-Bank-scripts\\Banking_Scripts\\src\\test\\resources\\Data\\Profile_TestData.xlsx" , "ValidProfileData");
        LoggerUtility.info("Excel test data loaded successfully");
        this.page = new ProfilePage(this.driver);
        LoggerUtility.info("Clicking Edit Profile button");
        page.ClickEditProfileButton();
        LoggerUtility.info("Clearing Name field");
        page.ClearNameField();

        LoggerUtility.info("Entering Full Name: " + reader.GetCellData(1,0));
        page.SetNameField(reader.GetCellData(1,0));
//        Thread.sleep(1000);

        LoggerUtility.info("Clearing Occupation field");
        page.ClearOccupationField();
        String occupation = reader.GetCellData(1,2);
        LoggerUtility.info("Entering Occupation: " + occupation);
        page.setOccupationField(reader.GetCellData(1,2));
//        Thread.sleep(1000);
        LoggerUtility.info("Clearing Phone field");
        page.ClearPhoneField();
        String phone = reader.GetCellData(1,1);
        LoggerUtility.info("Entering Phone Number: " + phone);
        page.setPhoneField(reader.GetCellData(1,1));
//        Thread.sleep(1000);
        LoggerUtility.info("Clearing Address field");
        page.ClearAddressField();
        String address = reader.GetCellData(1,3);
        LoggerUtility.info("Entering Address: " + address);
        page.SetAddressField(reader.GetCellData(1,3));
//        Thread.sleep(1000);

        LoggerUtility.info("Clearing Annual Income field");
        page.ClearAnnualIncomeField();
        String income = reader.GetCellData(1,4);
        LoggerUtility.info("Entering Annual Income: " + income);
        page.SetAnnualIncomeField(reader.GetCellData(1,4));

//        Thread.sleep(1000);
        LoggerUtility.info("Clicking Save Profile button");
        page.ClickSaveProfileButton();
        LoggerUtility.pass("Profile form submitted successfully");
    }

    @Then("User should see message {string}")
    public void user_should_see_message(String expectedMessage) {
        String actualToastMessage = page.getToastMessage();

        LoggerUtility.info("Actual Toast Message: " + actualToastMessage);
        LoggerUtility.info("Expected Toast Message: " + expectedMessage);
        Assert.assertEquals(actualToastMessage , expectedMessage , "Incorrect toast message displayed after profile update Toast Message: " + actualToastMessage +" expected : " + expectedMessage);
    }

    @And("Details to be Displayed Correctly After updation")
    public void Correct_details() throws IOException {
        ExcelReader reader = new ExcelReader(
                "C:\\Users\\abhiram.x1\\Desktop\\Testing-Bank-scripts\\Banking_Scripts\\src\\test\\resources\\Data\\Profile_TestData.xlsx",
                "ValidProfileData"
        );

        LoggerUtility.info("Loaded Profile Test Data from Excel");




        String expectedName = reader.GetCellData(1,0);
        String actualName = this.page.getNameFieldText();

        LoggerUtility.info("Validating Name Field");
        LoggerUtility.info("Expected Name: " + expectedName);
        LoggerUtility.info("Actual Name: " + actualName);

        Assert.assertEquals(
                actualName,
                expectedName,
                "Name field validation failed.\n" +
                        "Expected: " + expectedName + "\n" +
                        "Actual: " + actualName
        );

        LoggerUtility.pass("Name field validation passed");




        String expectedOccupation = reader.GetCellData(1,2);
        String actualOccupation = this.page.getOccupationFieldText();

        LoggerUtility.info("Validating Occupation Field");
        LoggerUtility.info("Expected Occupation: " + expectedOccupation);
        LoggerUtility.info("Actual Occupation: " + actualOccupation);

        Assert.assertEquals(
                actualOccupation,
                expectedOccupation,
                "Occupation field validation failed.\n" +
                        "Expected: " + expectedOccupation + "\n" +
                        "Actual: " + actualOccupation
        );

        LoggerUtility.pass("Occupation field validation passed");




        String expectedIncome = "50000.00";
        String actualIncome = this.page.getAnnualIncomeFieldText();

        LoggerUtility.info("Validating Annual Income Field");
        LoggerUtility.info("Expected Annual Income: " + expectedIncome);
        LoggerUtility.info("Actual Annual Income: " + actualIncome);

        Assert.assertEquals(
                actualIncome,
                expectedIncome,
                "Annual Income field validation failed.\n" +
                        "Expected: " + expectedIncome + "\n" +
                        "Actual: " + actualIncome
        );

        LoggerUtility.pass("Annual Income field validation passed");




        String expectedAddress = reader.GetCellData(1,3);
        String actualAddress = this.page.getAddressFieldText();

        LoggerUtility.info("Validating Address Field");
        LoggerUtility.info("Expected Address: " + expectedAddress);
        LoggerUtility.info("Actual Address: " + actualAddress);

        Assert.assertEquals(
                actualAddress,
                expectedAddress,
                "Address field validation failed.\n" +
                        "Expected: " + expectedAddress + "\n" +
                        "Actual: " + actualAddress
        );

        LoggerUtility.pass("Address field validation passed");




        String expectedPhone = reader.GetCellData(1,1);
        String actualPhone = this.page.getPhoneFieldText();

        LoggerUtility.info("Validating Phone Number Field");
        LoggerUtility.info("Expected Phone Number: " + expectedPhone);
        LoggerUtility.info("Actual Phone Number: " + actualPhone);

        Assert.assertEquals(
                actualPhone,
                expectedPhone,
                "Phone Number field validation failed.\n" +
                        "Expected: " + expectedPhone + "\n" +
                        "Actual: " + actualPhone
        );
        LoggerUtility.pass("Phone Number field validation passed");
        LoggerUtility.pass("All Profile Field Validations Completed Successfully");
    }





    @When("User enters invalid full name {string}")
    public void user_enters_invalid_full_name(String fullName) throws InterruptedException {
        ProfilePage page = new ProfilePage(this.driver);
        this.page = page;
        this.page = new ProfilePage(this.driver);
        this.page.ClickEditProfileButton();
        LoggerUtility.info("click Edit profile Button");
        this.page.ClearNameField();
        LoggerUtility.info("cleared Name field");
        this.page.SetNameField(fullName);
        LoggerUtility.info("Set Name field to " + fullName);
    }

    @And("User clicks on Save Profile button")
    public void user_clicks_on_save_profile_button() {
        LoggerUtility.info("Clicking Save profile Button");
        this.page.ClickSaveProfileButton();
        LoggerUtility.info("clicked Save profile Button");

    }

    @Then("Proper validation message {string} should be displayed")
    public void proper_validation_message_should_be_displayed(
            String expectedMessage) {
        String actualToastMessage = page.getToastMessage();
        LoggerUtility.info("Expected Toast Message: " + expectedMessage);
        LoggerUtility.info("Actual Toast Message: " + actualToastMessage);
        Assert.assertEquals(
                actualToastMessage,
                expectedMessage,
                "Profile Update Toast Message validation failed.\n" +
                        "Expected: " + expectedMessage + "\n" +
                        "Actual: " + actualToastMessage
        );
        LoggerUtility.pass("Profile Update Toast Message validation passed");
        LoggerUtility.info("Clicking Cancel Profile Button");
        page.ClickCancelProfileButton();
        LoggerUtility.pass("Cancel Profile Button clicked successfully");
    }

    @When("User clicks Edit Profile button")
    public void user_clicks_edit_profile_button() {
        LoggerUtility.info("clicking Edit profile button");
        this.page.ClickEditProfileButton();
        LoggerUtility.info("clicked Edit profile button");
    }


    @When("User clicks Save Profile button")
    public void user_clicks_save_profile_button() {
        LoggerUtility.info("Clicking Save Profile button");
        this.page.ClickSaveProfileButton();
        LoggerUtility.info("Clicked Save Profile Button");
    }


    // ---------------- CLEAR FIELD ACTIONS ----------------

    @And("User clears Phone number field")
    public void user_clears_phone_number_field() {

        this.page.ClearPhoneField();
        LoggerUtility.info("Cleared Phone Field");
    }


    // ---------------- INPUT ACTIONS ----------------

    @And("User enters Phone number {string}")
    public void user_enters_phone_number(String phoneNumber) {

        this.page.setPhoneField(phoneNumber);
        LoggerUtility.info("Phone Field Set to : " +phoneNumber);
    }


    // ---------------- VALIDATIONS ----------------

    @Then("User should not be able to edit Email field")
    public void user_should_not_be_able_to_edit_email_field() {
        LoggerUtility.info("Validating Email Field Visibility");
        Boolean status = this.page.CheckEmailVisibility();
        LoggerUtility.info("Actual Email Visibility Status: " + status);
        LoggerUtility.info("Expected Email Visibility Status: false");
        Assert.assertFalse(
                status,
                "Email field visibility validation failed.\n" +
                        "Expected: false\n" +
                        "Actual: " + status
        );
        LoggerUtility.pass("Email field visibility validation passed");
    }


    @Then("User should see toast message {string}")
    public void user_should_see_toast_message(String expectedMessage) {
        LoggerUtility.info("Validating Toast Message");
        String actualMessage = this.page.getToastMessage();
        LoggerUtility.info("Expected Toast Message: " + expectedMessage);
        LoggerUtility.info("Actual Toast Message: " + actualMessage);
        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "Toast Message validation failed.\n" +
                        "Expected: " + expectedMessage + "\n" +
                        "Actual: " + actualMessage
        );
        LoggerUtility.pass("Toast Message validation passed");
    }



    @And("User clears Annual Income field")
    public void user_clears_annual_income_field() {
        this.page.ClearAnnualIncomeField();
        LoggerUtility.info("Cleared Annual Income");
    }


    @And("User enters Annual Income {string}")
    public void user_enters_annual_income(String annualIncome) {

        this.page.SetAnnualIncomeField(annualIncome);
        LoggerUtility.info("Set Annual Income to : " + annualIncome);
    }


    // ---------------- ANNUAL INCOME VALIDATIONS ----------------

    @Then("User profile should be updated successfully")
    public void user_profile_should_be_updated_successfully() {
        Assert.assertEquals(this.page.getToastMessage() , "Profile updated successfully", "Profile Did not Update Actual: " + this.page.getToastMessage()+" Expected: Profile updated successfully" );
    }


    @Then("User should not be able to enter Annual Income {string}")
    public void user_should_not_be_able_to_enter_annual_income(String annualIncome) {
        this.page.ClearAnnualIncomeField();
        this.page.SetAnnualIncomeField(annualIncome);
//        this.page.ClickSaveProfileButton();
        Assert.assertEquals(this.page.getAnnualIncomeFieldText(), "","User Able to Enter Annual invalid Annual Income: " +annualIncome);
    }


    // ---------------- PROFILE ACTIONS ----------------
    String oldName;
    String oldPhone;
    String oldOccupation;
    String oldIncome;
    String oldAddress;
    String NewName;
    String NewPhone;
    String newOccupation;
    String newIncome;
    String newAddress;

    @And("User edits profile details")
    public void user_edits_profile_details() throws InterruptedException {
        this.oldName = this.page.getNameFieldText();
        this.oldPhone = this.page.getPhoneFieldText();
        this.oldOccupation = this.page.getOccupationFieldText();
        this.oldIncome = this.page.getAnnualIncomeFieldText();
        this.oldAddress = this.page.getAddressFieldText();
        this.NewName = this.oldName + " AAAAAA";
        long number = 1000000000L + (long)(Math.random() * 9000000000L);
        this.NewPhone = String.valueOf(number);
        this.newOccupation = oldOccupation + " aaa";
        this.newIncome = String.format("%.2f",
                Double.parseDouble(oldIncome) + 100
        );
        this.newAddress = oldAddress + "BBBBBBB";
        LoggerUtility.info("Clearing Name field");
        this.page.ClearNameField();
        LoggerUtility.info("Entering New Name: " + NewName);
        this.page.SetNameField(NewName);
        LoggerUtility.info("Clearing Phone Number field");
        this.page.ClearPhoneField();
        LoggerUtility.info("Entering New Phone Number: " + NewPhone);
        this.page.setPhoneField(NewPhone);
        LoggerUtility.info("Clearing Annual Income field");
        this.page.ClearAnnualIncomeField();
        LoggerUtility.info("Entering New Annual Income: " + this.newIncome);
        this.page.SetAnnualIncomeField(this.newIncome);
        LoggerUtility.info("Clearing Occupation field");
        this.page.ClearOccupationField();
        LoggerUtility.info("Entering New Occupation: " + newOccupation);
        this.page.setOccupationField(newOccupation);
// Thread.sleep(10000);
        LoggerUtility.info("Clearing Address field");
        this.page.ClearAddressField();
        LoggerUtility.info("Entering New Address: " + newAddress);
        this.page.SetAddressField(newAddress);
        LoggerUtility.pass("All Profile fields updated successfully");
    }


    @And("User clicks Cancel Profile button")
    public void user_clicks_cancel_profile_button() {

        this.page.ClickCancelProfileButton();
        LoggerUtility.info("clicks Cancel Button");
    }


    @And("User logs out from application")
    public void user_logs_out_from_application() {
        CommonDashBoard logout = new CommonDashBoard(this.driver);
        logout.ClickLogout();
        LoggerUtility.info("clicks logout button");
    }


    @And("User relogins with valid credentials")
    public void user_relogins_with_valid_credentials() {
        LoginPage logingPage = new LoginPage(this.driver);
        logingPage.clickFirstLogin();
        logingPage.enterEmail("abhirampb9@gmail.com");
        logingPage.enterPassword("Strong@123");
        logingPage.clickLogin();
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.urlContains("dashboard")
        );
        LoggerUtility.info("user Logged Back in ");
        this.driver.get(this.config.getProp("profileUrl"));
    }


    @When("User changes browser screen size")
    public void user_changes_browser_screen_size() {
        this.driver.manage().window().setSize(new Dimension(375, 812));
    }


    // ---------------- PROFILE VALIDATIONS ----------------

    @Then("Profile data should revert back to previous data")
    public void profile_data_should_revert_back_to_previous_data() {
        Assert.assertEquals(this.page.getNameFieldText() , this.oldName);
        Assert.assertEquals(this.page.getPhoneFieldText() , this.oldPhone);
        Assert.assertEquals(this.page.getOccupationFieldText() , this.oldOccupation);
        Assert.assertEquals(this.page.getAnnualIncomeFieldText(),this.oldIncome);
        Assert.assertEquals(this.page.getAddressFieldText(), this.oldAddress);
    }


    @Then("Updated profile data should be visible properly")
    public void updated_profile_data_should_be_visible_properly() {
       this.page.WaitTillProfileVisibility();
    }


    @Then("Saved profile data should persist after relogin")
    public void saved_profile_data_should_persist_after_relogin() {
            Assert.assertEquals(this.page.getAnnualIncomeFieldText(), this.newIncome);
            Assert.assertEquals(this.page.getNameFieldText(), this.NewName);
            Assert.assertEquals(this.page.getAddressFieldText() , this.newAddress);
            Assert.assertEquals(this.page.getPhoneFieldText() , this.NewPhone);
            Assert.assertEquals(this.page.getOccupationFieldText(), this.newOccupation);
    }


}


