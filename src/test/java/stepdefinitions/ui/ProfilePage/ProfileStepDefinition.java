package stepdefinitions.ui.ProfilePage;


import Hooks.Hooks;
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
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.urlContains("dashboard")
        );
    }

    @And("User navigates to the Profile page")
    public void user_navigates_to_the_profile_page() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("Navigated to profile URL: " + this.config.getProp("profileUrl"));
        this.driver.get(this.config.getProp("profileUrl"));
        Thread.sleep(100);
    }


    // Profile Page Load
    @Then("User profile page should load successfully")
    public void user_profile_page_should_load_successfully() {
        ProfilePage profileInstance = new ProfilePage(this.driver);
        profileInstance.WaitTillProfileVisibility();
    }


    // =========================
    // Expired Session
    // =========================

    @When("User clicks on Logout button")
    public void user_clicks_on_logout_button() {
        CommonDashBoard DashBoard = new CommonDashBoard(this.driver);
        DashBoard.ClickLogout();
    }

    @And("User manually navigates to Profile page URL")
    public void user_manually_navigates_to_profile_page_url() {
        this.driver.get(profileUrl);
    }

    @Then("User should get redirected to Home page")
    public void user_should_get_redirected_to_home_page() {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.urlToBe("http://localhost:3000/")
        );
    }



    // =========================
    // Update Profile
    // =========================

    @When("User updates profile details")
    public void user_updates_profile_details() throws IOException, InterruptedException {
        ExcelReader reader = new ExcelReader("C:\\Users\\abhiram.x1\\Desktop\\Testing-Bank-scripts\\Banking_Scripts\\src\\test\\resources\\Data\\Profile_TestData.xlsx" , "ValidProfileData");
        this.page = new ProfilePage(this.driver);
        page.ClickEditProfileButton();
        page.ClearNameField();
        page.SetNameField(reader.GetCellData(1,0));
        Thread.sleep(1000);
        page.ClearOccupationField();
        page.setOccupationField(reader.GetCellData(1,2));
        Thread.sleep(1000);
        page.ClearPhoneField();
        page.setPhoneField(reader.GetCellData(1,1));
        Thread.sleep(1000);
        page.ClearAddressField();
        page.SetAddressField(reader.GetCellData(1,3));
        Thread.sleep(1000);
        page.ClearAnnualIncomeField();
        page.SetAnnualIncomeField(reader.GetCellData(1,4));
        Thread.sleep(1000);
        page.ClickSaveProfileButton();
    }

    @Then("User should see message {string}")
    public void user_should_see_message(String expectedMessage) {
        Assert.assertEquals(page.getToastMessage() , expectedMessage);
    }
    @And("Details to be Displayed Correctly After updation")
    public void Correct_details() throws IOException {
        ExcelReader reader = new ExcelReader("C:\\Users\\abhiram.x1\\Desktop\\Testing-Bank-scripts\\Banking_Scripts\\src\\test\\resources\\Data\\Profile_TestData.xlsx" , "ValidProfileData");
        Assert.assertEquals(this.page.getNameFieldText() ,reader.GetCellData(1,0) );
        Assert.assertEquals(this.page.getOccupationFieldText(),reader.GetCellData(1,2));
        Assert.assertEquals(this.page.getAnnualIncomeFieldText(),"50000.00");
        Assert.assertEquals(this.page.getAddressFieldText(),reader.GetCellData(1,3));
        Assert.assertEquals(this.page.getPhoneFieldText() ,reader.GetCellData(1,1));
    }

    // =========================
    // Invalid Name Validation
    // =========================



    @When("User enters invalid full name {string}")
    public void user_enters_invalid_full_name(String fullName) throws InterruptedException {
        ProfilePage page = new ProfilePage(this.driver);
        this.page = page;
        this.page = new ProfilePage(this.driver);
        this.page.ClickEditProfileButton();
        this.page.ClearNameField();
        this.page.SetNameField(fullName);
    }

    @And("User clicks on Save Profile button")
    public void user_clicks_on_save_profile_button() {
        this.page.ClickSaveProfileButton();
    }

    @Then("Proper validation message {string} should be displayed")
    public void proper_validation_message_should_be_displayed(
            String expectedMessage) {

        Assert.assertEquals(
                page.getToastMessage(),
                expectedMessage
        );

        page.ClickCancelProfileButton();
    }

    @When("User clicks Edit Profile button")
    public void user_clicks_edit_profile_button() {

        this.page.ClickEditProfileButton();
    }


    @When("User clicks Save Profile button")
    public void user_clicks_save_profile_button() {
        this.page.ClickSaveProfileButton();
    }


    // ---------------- CLEAR FIELD ACTIONS ----------------

    @And("User clears Phone number field")
    public void user_clears_phone_number_field() {
        this.page.ClearPhoneField();
    }


    // ---------------- INPUT ACTIONS ----------------

    @And("User enters Phone number {string}")
    public void user_enters_phone_number(String phoneNumber) {
        this.page.setPhoneField(phoneNumber);
    }


    // ---------------- VALIDATIONS ----------------

    @Then("User should not be able to edit Email field")
    public void user_should_not_be_able_to_edit_email_field() {
        Boolean status = this.page.CheckEmailVisibility();
        Assert.assertFalse(status);
    }


    @Then("User should see toast message {string}")
    public void user_should_see_toast_message(String expectedMessage) {
        String actualMessage = this.page.getToastMessage();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    // ---------------- ANNUAL INCOME ACTIONS ----------------

    @And("User clears Annual Income field")
    public void user_clears_annual_income_field() {
        this.page.ClearAnnualIncomeField();
    }


    @And("User enters Annual Income {string}")
    public void user_enters_annual_income(String annualIncome) {
        this.page.SetAnnualIncomeField(annualIncome);
    }


    // ---------------- ANNUAL INCOME VALIDATIONS ----------------

    @Then("User profile should be updated successfully")
    public void user_profile_should_be_updated_successfully() {
        Assert.assertEquals(this.page.getToastMessage() , "Profile updated successfully");
    }


    @Then("User should not be able to enter Annual Income {string}")
    public void user_should_not_be_able_to_enter_annual_income(String annualIncome) {
        this.page.SetAnnualIncomeField(annualIncome);
//        this.page.ClickSaveProfileButton();
        Assert.assertEquals(this.page.getAnnualIncomeFieldText(), "");
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
        this.page.ClearNameField();
        this.page.SetNameField(NewName);
        this.page.ClearPhoneField();
        this.page.setPhoneField(NewPhone);
        this.page.ClearAnnualIncomeField();
        this.page.SetAnnualIncomeField(this.newIncome);
        this.page.ClearOccupationField();
        this.page.setOccupationField(newOccupation);
//        Thread.sleep(10000);
        this.page.ClearAddressField();
        this.page.SetAddressField(newAddress);
    }


    @And("User clicks Cancel Profile button")
    public void user_clicks_cancel_profile_button() {
        this.page.ClickCancelProfileButton();
    }


    @And("User logs out from application")
    public void user_logs_out_from_application() {
        CommonDashBoard logout = new CommonDashBoard(this.driver);
        logout.ClickLogout();
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


