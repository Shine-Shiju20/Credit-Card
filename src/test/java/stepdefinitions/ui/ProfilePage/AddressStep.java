package stepdefinitions.ui.ProfilePage;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pages.ProfilePage;
import Hooks.Hooks;

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
    }

    @And("User enters Address {string}")
    public void user_enters_address(String address) {
        this.page.SetAddressField(address);
    }





}
