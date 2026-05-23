package stepdefinitions.ui.ProfilePage;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pages.ProfilePage;
import Hooks.Profile.Hooks;
import utils.LoggerUtility;

public class AddressStep {
    WebDriver driver;

    ProfilePage page;

    public AddressStep(){
        this.driver = Hooks.factory.getDriver();
        this.page = new ProfilePage(this.driver);
    }

    @And("User clears Address field")
    public void user_clears_address_field() {
        this.page.ClearAddressField();
        LoggerUtility.info("cleared Address field");
    }

    @And("User enters Address {string}")
    public void user_enters_address(String address) {
        this.page.SetAddressField(address);
        LoggerUtility.info("Entered Address : " + address);
    }

}
