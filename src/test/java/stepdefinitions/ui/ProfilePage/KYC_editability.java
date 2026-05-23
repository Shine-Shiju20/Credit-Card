package stepdefinitions.ui.ProfilePage;

import Hooks.Profile.Hooks;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.ProfilePage;

public class KYC_editability {
    WebDriver driver;
    ProfilePage profilePage;
    public KYC_editability(){
        this.driver = Hooks.factory.getDriver();
        profilePage = new ProfilePage(Hooks.factory.getDriver());
    }

    @Then("Aadhaar field should be editable")
    public void aadhaar_field_should_be_editable() {

        boolean isEnabled =
                profilePage.IsAadhaarFieldEnabled();


        Assert.assertTrue(
                isEnabled,
                "Aadhaar field is not editable for unverified KYC user"
        );
    }

    @Then("Update KYC button should be disabled")
    public void update_kyc_button_should_be_disabled() {

        boolean isEnabled =
                profilePage.IsUpdateKYCButtonEnabled();


        Assert.assertFalse(
                isEnabled,
                "Update KYC button is enabled for verified KYC user"
        );
    }




    @Then("PAN field should be editable")
    public void pan_field_should_be_editable() {

        boolean isEnabled =
                profilePage.IsPanFieldEnabled();


        Assert.assertTrue(
                isEnabled,
                "PAN field is not editable for unverified KYC user"
        );
    }



    @Then("Aadhaar field should be disabled")
    public void aadhaar_field_should_be_disabled() {

        boolean isEnabled =
                profilePage.IsAadhaarFieldEnabled();


        Assert.assertFalse(
                isEnabled,
                "Aadhaar field is editable for verified KYC user"
        );
    }



    @Then("PAN field should be disabled")
    public void pan_field_should_be_disabled() {

        boolean isEnabled =
                profilePage.IsPanFieldEnabled();


        Assert.assertFalse(
                isEnabled,
                "PAN field is editable for verified KYC user"
        );
    }
}
