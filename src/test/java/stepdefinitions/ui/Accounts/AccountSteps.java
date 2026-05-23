package stepdefinitions.ui.Accounts;

import Hooks.Accounts.Hooks;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.Account.AccountsPage;
import pages.Account.LoginPage;

import pages.Account.TransactionsPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.LoggerUtility;
import utils.TestDataStore;

import java.time.Duration;
import java.util.List;

public class AccountSteps {

    WebDriver driver;
    DriverFactory driverFactory;
    ConfigReader config;
    LoginPage loginPage;
    AccountsPage accountsPage;
    List<String> existingAccounts;
    TransactionsPage transactionsPage;

    @Given("user launches banking application")
    public void user_launches_banking_application() throws Exception {
        driverFactory = Hooks.factory;
        driver = driverFactory.getDriver();
        driver.manage().deleteAllCookies();
        config = new ConfigReader();
        driverFactory.FetchPage(config.getProp("baseUrl"));
        loginPage = new LoginPage(driver);
        accountsPage = new AccountsPage(driver);
        transactionsPage = new TransactionsPage(driver);
        LoggerUtility.info("Application Launched");
    }

    @Given("user logs into the application")
    public void user_logs_into_the_application(){

        loginPage.login(
                config.getProp("email"),
                config.getProp("password")
        );

        TestDataStore.currentEmail=
                config.getProp("email");

        TestDataStore.currentPassword=
                config.getProp("password");

        LoggerUtility.info(
                "Login Completed"
        );
    }

    @Given("user navigates to accounts route")
    public void user_navigates_to_accounts_route(){

        driver.get(
                config.getProp("baseUrl")
                        + config.getProp("accountsRoute")
        );

        LoggerUtility.info(
                "Accounts Route Opened Successfully"
        );
    }

    @When("user creates {string} account with deposit {string}")
    public void user_creates_account_with_deposit(
            String accountType,
            String amount){

        TestDataStore.existingAccounts.clear();

        TestDataStore.existingAccounts.addAll(
                accountsPage
                        .getAllAccountNumbers()
        );

        accountsPage.createAccount(
                accountType,
                amount
        );

        try{

            String toast=
                    accountsPage.getToastMessage();

            if(
                    toast.equals(
                            "Account created successfully!"
                    )
            ){

                String accountNo=
                        accountsPage.getLatestAccountNumber();

                TestDataStore.createdAccounts.put(
                        accountNo,
                        accountType
                );

                LoggerUtility.info(
                        "Storing Created Account : "
                                + accountNo
                );
            }

        }
        catch(Exception e){

            LoggerUtility.info(
                    "No account created : "
                            + e.getMessage()
            );
        }
    }

    //validAccount

    @Then("account should be created successfully with message {string}")
    public void account_should_be_created_successfully_with_message(String expectedMessage) throws InterruptedException {
        String actualMessage = accountsPage.getToastMessage();
        LoggerUtility.info("Toast Message"+actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @When("user clicks create account button")
    public void user_clicks_create_account_button(){
        accountsPage.clickOpenAccount();
        LoggerUtility.info("Click Create Account Button");
    }
    
    //invalidDeposit

    @Then("browser validation message should be displayed as {string}")
    public void browser_validation_message_should_be_displayed_as(
            String expectedMessage){

        String actualMessage=
                accountsPage.getBrowserValidationMessage();

        LoggerUtility.info(
                "Getting Browser Validation Message : "
                        + actualMessage
        );

        Assert.assertEquals(
                actualMessage,
                expectedMessage
        );

        accountsPage.closeCreateAccountPopup();

        LoggerUtility.info(
                "Create Account Popup Closed"
        );
    }

    //@invalidCharacters

    @When("user enters invalid deposit value {string}")
    public void user_enters_invalid_deposit_value(String amount) throws InterruptedException {
        accountsPage.clickOpenAccount();
        LoggerUtility.info("Click Open Account Button");
        accountsPage.enterDeposit(amount);
        LoggerUtility.info("Enter Deposit Amount"+amount);
    }

    @Then("invalid deposit value should be rejected with value {string}")
    public void invalid_deposit_value_should_be_rejected_with_value(String expectedValue) throws InterruptedException {
        String actualValue = accountsPage.getDepositFieldValue();
        LoggerUtility.info("Getting Deposit Field Value "+actualValue);

        Assert.assertEquals(actualValue, expectedValue);
    }

    //mandatoryFields
    @When("user opens account creation form")
    public void user_opens_account_creation_form(){
        accountsPage.clickOpenAccount();
        LoggerUtility.info("Click Open Account Button");
    }

    @When("user submits account form without entering mandatory fields")
    public void user_submits_account_form_without_entering_mandatory_fields(){
        accountsPage.clickSubmitButton();
        LoggerUtility.info("Submit Empty Account Form");
    }
    //largeDepositValidation
    @Then("compliance validation message should be displayed as {string}")
    public void compliance_validation_message_should_be_displayed_as(String expectedMessage) {
        String actualMessage = accountsPage.getValidationMessage();
        LoggerUtility.info("Getting Validation Message "+actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    //maxSavingsBalance

    @Then("savings validations should be displayed")
    public void savings_validations_should_be_displayed() {
        String actualMessage = accountsPage.getValidationMessage();
        LoggerUtility.info("Getting Validation Message "+actualMessage);

        Assert.assertTrue(actualMessage.contains("savings account cannot exceed ₹500000000"));
        Assert.assertTrue(actualMessage.contains("Large deposits require manual compliance verification"));
    }

    //NonKYC User
    @And("user logs in with non-KYC user credentials")
    public void user_logs_in_with_non_kyc_user_credentials() throws InterruptedException {
        accountsPage.clickLogout();
        LoggerUtility.info("Click LogOut Button");
        loginPage.login(
                config.getProp("nonKycEmail"),
                config.getProp("nonKycPassword")
        );
        LoggerUtility.info("Login Using Non-KYC user");
    }

    @Then("KYC validation message should be displayed as {string}")
    public void kyc_validation_message_should_be_displayed_as(String expectedMessage) {
        String actualMessage = accountsPage.getValidationMessage();
        LoggerUtility.info("Getting Validation Message "+actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage);
    }


    //Unique Account Number Validation
    @Given("user stores existing account numbers")
    public void user_stores_existing_account_numbers() {
        existingAccounts = accountsPage.getAllAccountNumbers();
        LoggerUtility.info("Getting All Existing Accounts: "+existingAccounts);
    }

    @Then("new account number should be unique")
    public void new_account_number_should_be_unique() throws InterruptedException {
        String latestAccount = accountsPage.getLatestAccountNumber();
        LoggerUtility.info("Getting latest Account's Number "+latestAccount);

        Assert.assertFalse(existingAccounts.contains(latestAccount), "Duplicate Account Number Found");
    }

    //duplicateSalaryAccount
    @Then("duplicate salary account validation should be displayed as {string}")
    public void duplicate_salary_validation(String expectedMessage) {
        String actualMessage = accountsPage.getValidationMessage();
        LoggerUtility.info("Getting Validation Message "+actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    //duplicateSalaryUpdate
    @Then("duplicate salary account validations should be displayed as {string}")
    public void duplicate_salary_Toast(String expectedMessage) {
        String actualMessage = accountsPage.getToastMessage();
        LoggerUtility.info("Getting Toast Message "+actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    //updateAccountType
    @Given("user logs in with new user credentials")
    public void user_logs_in_with_new_user_credentials(){

        try{

            accountsPage.clickLogout();

            LoggerUtility.info(
                    "Logout Completed"
            );

            loginPage.waitForLoginPage();

        }catch(Exception e){

            LoggerUtility.info(
                    "Already on Login Page"
            );
        }

        loginPage.login(
                config.getProp("newUserEmail"),
                config.getProp("newUserPassword")
        );

        TestDataStore.currentEmail=
                config.getProp("newUserEmail");

        TestDataStore.currentPassword=
                config.getProp("newUserPassword");

        LoggerUtility.info(
                "New User Login Completed"
        );
    }

    @When("user updates {string} account to {string}")
    public void user_updates_account_to(String existingType, String newType) throws InterruptedException {
        accountsPage.updateAccountType(
                existingType,
                newType
        );

        String accountNo=
                accountsPage.getSelectedAccountNumber();

        String oldType=
                existingType
                        .replace(" Account","")
                        .trim();

        TestDataStore.updatedAccounts.put(
                accountNo,
                oldType
        );
        LoggerUtility.info("Account Update from One type to Other");
    }

    @Then("account update should be successful with message {string}")
    public void account_update_should_be_successful_with_message(String expectedMessage) {
        String actualMessage = accountsPage.getToastMessage();
        LoggerUtility.info("Getting Toast Message"+actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    //updatedDetailsReflection
    @Then("updated account type should be displayed as {string}")
    public void updated_account_type_should_be_displayed_as(String expectedType){

        driver.navigate().refresh();

        new WebDriverWait(
                driver,
                Duration.ofSeconds(5)
        ).until(

                ExpectedConditions
                        .numberOfElementsToBeMoreThan(
                                By.xpath(
                                        "//div[contains(@class,'acc-item ')]"
                                ),
                                0
                        )
        );
        String actualType = accountsPage.getUpdatedAccountType();
        LoggerUtility.info(actualType);

        Assert.assertTrue(actualType.toLowerCase().contains(expectedType.toLowerCase()));
    }

    //salaryToSavingsDefect
    @Then("account update should fail due to insufficient balance")
    public void account_update_should_fail_due_to_insufficient_balance() {
        String actualMessage = accountsPage.getToastMessage();
        LoggerUtility.info(actualMessage);

        Assert.assertEquals(actualMessage, "Insufficient balance for account conversion");
    }

    //salaryToCurrentDefect
    @Given("user logs in with max account user credentials")
    public void user_logs_in_with_max_account_user_credentials(){

        try{

            accountsPage.clickLogout();

            LoggerUtility.info(
                    "Logout Completed"
            );

            loginPage.waitForLoginPage();

        }catch(Exception e){

            LoggerUtility.info(
                    "Already on Login Page"
            );
        }

        loginPage.login(
                config.getProp("maxUserEmail"),
                config.getProp("maxUserPassword")
        );

        TestDataStore.currentEmail=
                config.getProp("maxUserEmail");

        TestDataStore.currentPassword=
                config.getProp("maxUserPassword");

        LoggerUtility.info(
                "Max User Login Completed"
        );
    }

//maxAccountRestriction
    @Then("account restriction message should be displayed as {string}")
    public void account_restriction_message_should_be_displayed_as(String expectedMessage) {
        String actualMessage = accountsPage.getValidationMessage();
        LoggerUtility.info("Getting Toast Message "+actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    //sameAccountUpdate
    @Then("same account update should be restricted with message {string}")
    public void same_account_update_should_be_restricted_with_message(String expectedMessage) {
        String actualMessage = accountsPage.getToastMessage();
        LoggerUtility.info("Getting Toast Message "+actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    //fetchAccounts
    @Then("user accounts should be displayed")
    public void user_accounts_should_be_displayed() {

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(

                d ->
                        accountsPage
                                .getAllAccountNumbers()
                                .size() > 0
        );

        Assert.assertTrue(
                accountsPage
                        .getAllAccountNumbers()
                        .size() > 0
        );

        LoggerUtility.info(
                "Accounts Displayed Successfully"
        );
    }

    //balancePersistence
    @When("user refreshes application")
    public void user_refreshes_application(){
        driver.navigate().refresh();
        LoggerUtility.info("Refreshing The Browser");
    }

    @When("user stores updated balance")
    public void user_stores_updated_balance(){
        String accountNo = transactionsPage.getSelectedAccountNumber();
        LoggerUtility.info("Getting Account Number(Updated "+accountNo);
        accountsPage.storeUpdatedBalance(accountNo);

    }


    @Then("updated balance should persist after refresh")
    public void updated_balance_should_persist_after_refresh(){
        String accountNo = transactionsPage.getSelectedAccountNumber();

        String beforeRefresh = accountsPage.getStoredBalance();
        String afterRefresh = accountsPage.getCurrentBalance(accountNo);

        Assert.assertEquals(beforeRefresh, afterRefresh);
    }

    @When("user withdraws amount {string} from {string} account")
    public void user_withdraws_amount_from_account(String amount, String accountType) throws InterruptedException {
        transactionsPage.clickWithdrawTab();
        LoggerUtility.info("Click Withdraw Tab");
        transactionsPage.selectWithdrawAccount(accountType);
        LoggerUtility.info("Select Withdraw Account Type");
        transactionsPage.enterWithdrawAmount(amount);
        LoggerUtility.info("Enter Amount for Withdraw ");
        transactionsPage.enterTransactionPin(config.getProp("transactionPin"));
        LoggerUtility.info("Enter the Transaction PIN");
        transactionsPage.clickConfirmWithdraw();

        String toast =
                transactionsPage.getToast();

        LoggerUtility.info(
                "Withdraw Toast : "
                        + toast
        );

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(
                ExpectedConditions
                        .invisibilityOfElementLocated(
                                By.xpath(
                                        "//div[contains(@class,'modal')]"
                                )
                        )
        );

        driver.navigate().refresh();

        LoggerUtility.info(
                "Balance Refreshed After Withdraw"
        );
    }

    //closeAccountWithBalance
    @When("user closes {string} account")
    public void user_closes_account(String accountType) throws InterruptedException {
        String accountNo =
                accountsPage
                        .getSelectedAccountNumber();

        TestDataStore
                .closedAccounts
                .add(
                        accountNo
                );
        accountsPage.closeAccount(accountType);
        LoggerUtility.info("Close Account ");
    }

    @When("user closes selected account")
    public void user_closes_selected_account()
            throws InterruptedException {

        String accountNo=
                transactionsPage
                        .getSelectedAccountNumber();

        TestDataStore.closedAccounts.add(
                accountNo
        );

        LoggerUtility.info(
                "Stored Closed Account : "
                        + accountNo
        );

        accountsPage.closeAccountByNumber(
                accountNo
        );

        LoggerUtility.info(
                "Close Account By AccountNo"
        );
    }

    @Then("closure should fail with message {string}")
    public void closure_should_fail_with_message(String expectedMessage) {
        String actualMessage = accountsPage.getToastMessage();
        LoggerUtility.info("Getting Toast Message"+actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    //validAccountClosure

    @Given("user navigates to transactions route")
    public void user_navigates_to_transactions_route() throws InterruptedException {
        driver.get(
                config.getProp("baseUrl")
                        + config.getProp("transactionsRoute")
        );

        transactionsPage.waitForTransactionsPage();
        LoggerUtility.info("Navigate to Transaction Module");
    }

    @Given("user withdraws complete balance from {string} account")
    public void user_withdraws_complete_balance_from_account(
            String accountType)
            throws InterruptedException {

        transactionsPage.clickWithdrawTab();
        LoggerUtility.info("Click Withdraw Tab");

        transactionsPage.selectWithdrawAccount(accountType);
        LoggerUtility.info("Select Withdraw Account Type");

        String amount=
                transactionsPage.getSelectedBalance();

        LoggerUtility.info(
                "Getting Amount for Withdraw "
                        + amount
        );

        transactionsPage.enterWithdrawAmount(
                amount
        );

        transactionsPage.enterTransactionPin(
                config.getProp(
                        "transactionPin"
                )
        );
        String accountNo=
                transactionsPage
                        .getSelectedAccountNumber();

        Double balance=
                Double.parseDouble(
                        transactionsPage
                                .getSelectedBalance()
                );

        TestDataStore.accountBalances.put(
                accountNo,
                balance
        );

        transactionsPage.clickConfirmWithdraw();

        String withdrawMessage=
                transactionsPage.getToast();

        LoggerUtility.info(
                "Withdraw Toast : "
                        + withdrawMessage
        );

        new WebDriverWait(
                driver,
                Duration.ofSeconds(5)
        ).until(

                ExpectedConditions
                        .invisibilityOfElementLocated(
                                By.xpath(
                                        "//div[contains(@class,'modal')]"
                                )
                        )
        );

        LoggerUtility.info(
                "Withdraw Completed"
        );
    }

    @Then("account should be closed successfully with message {string}")
    public void account_should_be_closed_successfully_with_message(String expectedMessage) {
        String actualMessage = accountsPage.getToastMessage();
        LoggerUtility.info("Getting Toast Message"+actualMessage);

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Then("account should be closed successfully")
    public void account_should_be_closed_successfully(){

        String actualMessage=
                accountsPage
                        .getToastMessage();

        Assert.assertEquals(
                actualMessage,
                "Account closed successfully"
        );
    }
}


