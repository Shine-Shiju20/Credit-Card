package stepdefinitions.ui.loan;

import Hooks.Loan.Hooks;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class MyLoanStepDefinition {

    WebDriver driver;

    @And("User logs into application with email {string} and password {string}")
    public void user_logs_into_application_with_email_and_password(
            String email,
            String password
    ) throws InterruptedException {

        driver =
                Hooks.factory.getDriver();

        LoginPage loginPage =
                new LoginPage(driver);
        Thread.sleep(2000);
        loginPage.clickFirstLogin();

        loginPage.enterEmail(email);

        loginPage.enterPassword(password);

        loginPage.clickLogin();
    }
}
