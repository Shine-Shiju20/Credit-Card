package stepdefinitions.ui.ProfilePage;



import Hooks.Hooks;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pages.ProfilePage;

public class OccupationSteps {

    WebDriver driver;

    ProfilePage page;


    public OccupationSteps(){

        this.driver = Hooks.factory.getDriver();

        this.page = new ProfilePage(this.driver);
    }


    // ---------------- OCCUPATION ACTIONS ----------------

    @And("User clears Occupation field")
    public void user_clears_occupation_field() {
        this.page.ClearOccupationField();
    }

    @And("User enters Occupation {string}")
    public void user_enters_occupation(String occupation) {
        this.page.setOccupationField(occupation);
    }

}
