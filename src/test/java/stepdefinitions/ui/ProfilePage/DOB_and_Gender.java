package stepdefinitions.ui.ProfilePage;

import Hooks.Profile.Hooks;

import io.cucumber.java.en.Then;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ProfilePage;


public class DOB_and_Gender {

    WebDriver driver;

    ProfilePage page;


    public DOB_and_Gender(){
        this.driver = Hooks.factory.getDriver();
        this.page = new ProfilePage(this.driver);
    }



    @Then("Date of Birth field should be enabled")
    public void date_of_birth_field_should_be_enabled() {
        this.page.ClickEditKYCButton();
        boolean isEnabled =
                page.IsDateOfBirthFieldEnabled();



        Assert.assertTrue(
                isEnabled,
                "Date of Birth field is disabled"
        );
    }



    @Then("Gender dropdown field should be enabled")
    public void gender_dropdown_field_should_be_enabled() {

        this.page.ClickEditKYCButton();
        boolean isEnabled =
                page.IsGenderFieldEnabled();



        Assert.assertTrue(
                isEnabled,
                "Gender dropdown field is disabled"
        );
    }
}