package stepdefinitions.ui.Auth;

import Hooks.Auth.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.ExcelReader;
import org.testng.Assert;

public class LoginSteps {

    WebDriver driver;

    LoginPage login ;

    String expectedResult;



    // Launch Banking Application

    @Given("User launches the banking application")
    public void user_launches_the_banking_application() {
        this.driver = Hooks.factory.getDriver();
        this.login = new LoginPage(driver);
        driver.get("http://localhost:3000");
    }

    // Click Home Login Button

    @And("User clicks on Home Login button")
    public void user_clicks_on_home_login_button() {

        login.clickHomeLoginButton();
    }

    // Fetch Login Data From Excel


    @When("User enters login credentials from excel for {string}")
    public void user_enters_login_credentials_from_excel_for(
            String testDataId) {

        try {

            ExcelReader reader = new ExcelReader("src/test/java/resources/testdata/LoginExcel2075.xlsx", "Sheet1");

            int row = -1;


            // Find Matching TestDataID

            for (int i = 1; i < 20; i++) {

                String currentId = reader.GetCellData(i, 0);

                if (currentId.equalsIgnoreCase(testDataId)) {

                    row = i;

                    break;
                }
            }


            // Fetch Email And Password

            String email = reader.GetCellData(row, 1);

            String password = reader.GetCellData(row, 2);

            expectedResult = reader.GetCellData(row, 3);


            // Enter Login Credentials

            login.enterEmail(email);

            login.enterPassword(password);

        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }


    // Click Secure Login Button

    @And("User clicks on Secure Login button")
    public void user_clicks_on_secure_login_button() {

        login.clickLogin();
    }

    // Successful Login Validation

    @Then("User should login successfully")
    public void user_should_login_successfully() {
        System.out.println(driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedResult));
        //System.out.println(driver.getCurrentUrl());
    }

    // Email Validation Message

    @Then("Proper email validation message should be displayed")
public void proper_email_validation_message_should_be_displayed() {

    String actualMessage =
            login.getEmailFieldValidationMessage();

    System.out.println(
            "Actual Validation Message : "
                    + actualMessage
    );

    Assert.assertTrue(

            actualMessage.contains(
                    expectedResult
            )
    );
}
    // Trimmed Email Validation

    @Then("Spaces should be removed from email input")
    public void spaces_should_be_removed_from_email_input() {
         String actualEmail = login.getEmailFieldValue();

    System.out.println("Actual Email : " + actualEmail);

    Assert.assertFalse(actualEmail.contains(" "));
    }
    @Then("Invalid credentials message should be displayed")
    public void invalid_credentials_message_should_be_displayed() {
        String actualMessage = login.getValidationMessage();
        System.out.println("Actual Validation Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedResult);


}

}