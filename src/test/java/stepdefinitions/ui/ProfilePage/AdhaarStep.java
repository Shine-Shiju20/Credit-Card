package stepdefinitions.ui.ProfilePage;

import Hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.ProfilePage;

import java.time.Duration;

public class AdhaarStep {

    WebDriver driver;

    ProfilePage page;


    public AdhaarStep(){
        this.driver = Hooks.factory.getDriver();
        this.page = new ProfilePage(this.driver);
    }

    @And("User logs in using unverified KYC account")
    public void user_logs_in_using_unverified_kyc_account() {

        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.clickFirstLogin();

        loginPage.enterEmail("prajwalp.123p@gmail.com");

        loginPage.enterPassword("Strong@123");

        loginPage.clickLogin();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.urlContains("dashboard")
        );
    }

    // ---------------- KYC ACTIONS ----------------

    @When("User clicks Update KYC button")
    public void user_clicks_update_kyc_button() {
        this.page.ClickEditKYCButton();
    }


    @And("User clicks Save KYC button")
    public void user_clicks_save_kyc_button() {
        this.page.ClickSaveKYCButton();
    }


    // ---------------- AADHAAR ACTIONS ----------------

    @And("User clears Aadhaar field")
    public void user_clears_aadhaar_field() {
        this.page.ClearAadhaarField();
    }


    @And("User enters Aadhaar number {string}")
    public void user_enters_aadhaar_number(String aadhaar) {
        this.page.ClearAadhaarField();
        this.page.SetAadhaarField(aadhaar);
    }

}