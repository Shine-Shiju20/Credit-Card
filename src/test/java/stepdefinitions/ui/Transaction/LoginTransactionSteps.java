//package stepdefinitions;
//
//import org.testng.Assert;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import pages.LoginPage;
//import utils.DriverFactory;
//import utils.ExcelReader;
//
//public class LoginSteps {
//
//    private LoginPage loginPage;
//
//    private final String excelPath =
//            "src/test/resources/testdata/LoginData.xlsx";
//    @Given("the user opens the banking application")
//    public void theUserOpensTheBankingApplication() {
//        loginPage = new LoginPage(DriverFactory.getDriver());
//        loginPage.openApplication();
//    }
//
//    @When("the user logs in using Excel test data row {int}")
//    public void theUserLogsInUsingExcelTestDataRow(int rowNumber) {
//
//        String email = ExcelReader.getCellData(
//                excelPath,
//                "Login",
//                rowNumber,
//                0
//        );
//        String password = ExcelReader.getCellData(
//                excelPath,
//                "Login",
//                rowNumber,
//                1
//        );
//
//        loginPage.login(email, password);
//    }
//
//    @Then("the dashboard should be displayed")
//    public void theDashboardShouldBeDisplayed() {
//        Assert.assertTrue(
//                loginPage.isDashboardDisplayed(),
//                "Dashboard was not displayed after login."
//        );
//    }
//    // Reusable step for all scenarios
//    @Given("the user is logged into the banking application")
//    public void theUserIsLoggedIntoTheBankingApplication() {
//        loginPage = new LoginPage(DriverFactory.getDriver());
//        loginPage.openApplication();
//
//        String email = ExcelReader.getCellData(
//                excelPath,
//                "Login",
//                1,
//                0
//        );
//
//        String password = ExcelReader.getCellData(
//                excelPath,
//                "Login",
//                1,
//                1
//        );
//        
//        loginPage.login(email, password);
//
//        Assert.assertTrue(
//                loginPage.isDashboardDisplayed(),
//                "Login failed. Dashboard not displayed."
//        );
//    }
//}

package stepdefinitions.ui.Transaction;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Transaction.LoginPage;
import utils.Transactions.DriverFactory;
import utils.Transactions.ExcelReader;
import utils.Log;

public class LoginTransactionSteps {

    private LoginPage loginPage;

    // Logger
    private static final Logger logger =
            Log.getLogger(LoginTransactionSteps.class);

    // Excel file path
    private final String excelPath =
            "src/test/resources/testdata/LoginData.xlsx";

    // =========================================================
    // Open Application
    // =========================================================

    @Given("the user opens the banking application")
    public void theUserOpensTheBankingApplication() {

        logger.info("Opening banking application.");

        loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.openApplication();

        logger.info("Application opened successfully.");
    }

    // =========================================================
    // Login Using Excel Row
    // =========================================================

    @When("the user logs in using Excel test data row {int}")
    public void theUserLogsInUsingExcelTestDataRow(int rowNumber) {

        logger.info(
                "Reading login data from Excel row: {}",
                rowNumber
        );

        String email = ExcelReader.getCellData(
                excelPath,
                "Login",
                rowNumber,
                0
        );

        String password = ExcelReader.getCellData(
                excelPath,
                "Login",
                rowNumber,
                1
        );

        logger.info("Email: {}", email);

        loginPage.login(email, password);

        logger.info("Login submitted.");
    }

    // =========================================================
    // Dashboard Validation
    // =========================================================

    @Then("the dashboard should be displayed")
    public void theDashboardShouldBeDisplayed() {

        logger.info("Validating dashboard display.");

        Assert.assertTrue(
                loginPage.isDashboardDisplayed(),
                "Dashboard was not displayed after login."
        );

        logger.info("Dashboard displayed successfully.");
    }

    // =========================================================
    // Reusable Login Step
    // =========================================================

    @Given("the user is logged into the banking application")
    public void theUserIsLoggedIntoTheBankingApplication() {

        logger.info("Performing reusable login step.");

        loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.openApplication();

        // Read default credentials from row 1
        String email = ExcelReader.getCellData(
                excelPath,
                "Login",
                1,
                0
        );

        String password = ExcelReader.getCellData(
                excelPath,
                "Login",
                1,
                1
        );

        logger.info("Using login email: {}", email);

        // Perform login
        loginPage.login(email, password);

        // Validate dashboard
        Assert.assertTrue(
                loginPage.isDashboardDisplayed(),
                "Login failed. Dashboard not displayed."
        );

        logger.info("Reusable login completed successfully.");
    }
}