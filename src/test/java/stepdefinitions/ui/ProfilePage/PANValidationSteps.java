package stepdefinitions.ui.ProfilePage;

import Hooks.Profile.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ProfilePage;
import utils.LoggerUtility;

public class PANValidationSteps {

    WebDriver driver;

    ProfilePage page;


    public PANValidationSteps(){

        this.driver = Hooks.factory.getDriver();

        this.page = new ProfilePage(this.driver);
    }


    // ---------------- PAN ACTIONS ----------------

    @And("User clears PAN field")
    public void user_clears_pan_field() {
        this.page.ClearPanField();
        LoggerUtility.info("Cleared Pan field");
    }


    @And("User enters PAN number {string}")
    public void user_enters_pan_number(String pan) {
        this.page.SetPanField(pan);
        LoggerUtility.info("Set pan field : " + pan);
    }
    @Then("PAN number is automatically converted to uppercase {string}")
    public void PAN_number_is_automatically_converted_to_uppercase(String pan){
        LoggerUtility.info("Validating PAN Field Text");

        String actualPan = this.page.getPanFieldText();
        String expectedPan = pan.toUpperCase();

        LoggerUtility.info("Expected PAN Value: " + expectedPan);
        LoggerUtility.info("Actual PAN Value: " + actualPan);

        Assert.assertEquals(
                actualPan,
                expectedPan,
                "PAN Field validation failed.\n" +
                        "Expected: " + expectedPan + "\n" +
                        "Actual: " + actualPan
        );

        LoggerUtility.pass("PAN Field validation passed successfully");
    }
}