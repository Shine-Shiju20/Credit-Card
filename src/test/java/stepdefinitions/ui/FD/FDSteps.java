package stepdefinitions.ui.FD;

import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import Hooks.FD.Hooks;
import utils.LoggerUtility;

import pages.FDPage;
import utils.DriverFactory;

public class FDSteps {

    DriverFactory driverFactory;
    WebDriver driver;
    FDPage fdPage;


    @Given("user launches banking application")
    public void user_launches_banking_application() {
        LoggerUtility.info("Initializing WebDriver and launching banking application");
        driverFactory = Hooks.factory;
        driver = driverFactory.getDriver();

        fdPage = new FDPage(driver);
        fdPage.openApplication();
        LoggerUtility.pass("Banking application launched successfully");
    }

    @Given("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        LoggerUtility.info("Logging in with valid user credentials");
        fdPage.login(
                "sk.shreya651@gmail.com",
                "Shreya@26"
        );
        LoggerUtility.pass("User logged in successfully");
    }

    @Given("user navigates to fixed deposit page")
    public void user_navigates_to_fixed_deposit_page() {
        LoggerUtility.info("Navigating to Fixed Deposit page");
        fdPage.navigateToFDPage();
        LoggerUtility.pass("Successfully navigated to Fixed Deposit page");
    }

    // Scenario Steps
    //1
    @When("user creates FD with amount {string} and tenure {string}")
    public void user_creates_fd_with_amount_and_tenure(String amount, String tenure) {
        LoggerUtility.info("Starting FD creation process");

        LoggerUtility.info("Clicking Create New FD button");
        fdPage.clickCreateFD();
        LoggerUtility.info("Selecting linked account");
        fdPage.selectAccount();
        LoggerUtility.info("Entering FD amount: " + amount);
        fdPage.enterDepositAmount(amount);
        LoggerUtility.info("Selecting FD tenure: " + tenure);
        fdPage.selectTenure(tenure);
        LoggerUtility.info("Submitting FD creation request");
        fdPage.createFixedDeposit();

        LoggerUtility.pass("FD creation request submitted successfully");
    }

    @Then("FD should be created successfully")
    public void fd_should_be_created_successfully() {
        LoggerUtility.info("Verifying FD creation success message");
        Assert.assertTrue(
                fdPage.isSuccessMessageDisplayed(),
                "FD creation failed"
        );
        LoggerUtility.pass("FD created successfully");
    }

    @And("user selects account")
    public void user_selects_account() {
        LoggerUtility.info("Selecting linked account for FD creation");
        fdPage.selectAccount();
        LoggerUtility.pass("Account selected successfully");
    }

    @And("user enters FD amount {string}")
    public void user_enters_fd_amount(String amount) {

        if (amount == null || amount.trim().isEmpty()) {
            fdPage.leaveAmountBlank();
            LoggerUtility.warn("FD amount left blank");
        } else {
            LoggerUtility.info("Entering FD amount: " + amount);
            fdPage.enterDepositAmount(amount);
            LoggerUtility.pass("FD amount entered successfully");
        }
    }

    @And("user selects tenure as {string}")
    public void user_selects_tenure_as(String tenure) {
        LoggerUtility.info("Selecting FD tenure: " + tenure);
        fdPage.selectTenure(tenure);
        LoggerUtility.pass("FD tenure selected successfully");
    }


    @And("user clicks Create FD submit button")
    public void user_clicks_create_fd_submit_button() {
        LoggerUtility.info("Clicking Create FD submit button");
        fdPage.createFixedDeposit();
        LoggerUtility.pass("Create FD submit button clicked");
    }

    @Then("success message should be displayed")
    public void success_message_should_be_displayed() {
        LoggerUtility.info("Verifying success message is displayed");
        Assert.assertTrue(fdPage.isSuccessMessageDisplayed());
        LoggerUtility.pass("Success message displayed successfully");
    }

    @Then("new FD should appear in FD list")
    public void new_fd_should_appear_in_fd_list() {
        LoggerUtility.info("Verifying newly created FD appears in FD list");
        Assert.assertTrue(fdPage.isFDCreated());
        LoggerUtility.pass("New FD is visible in FD list");
    }

    @When("user clicks {string}")
    public void user_clicks(String action) {
        LoggerUtility.info("User clicks action: " + action);

        if (action.equalsIgnoreCase("Create New FD")) {
            fdPage.clickCreateFD();
            LoggerUtility.pass("Create New FD button clicked");
        } else if (action.equalsIgnoreCase("Cancel")) {
            fdPage.clickCancelButton();
            LoggerUtility.pass("Cancel button clicked");
        } else if (action.equalsIgnoreCase("Close Icon")) {
            fdPage.clickCloseIcon();
            LoggerUtility.pass("Close icon clicked");
        } else if (action.equalsIgnoreCase("Create FD Submit")) {
            fdPage.createFixedDeposit();
            LoggerUtility.pass("Create FD submit button clicked");
        } else {
            LoggerUtility.warn("Unknown action provided: " + action);
        }
    }

    @And("user enters FD amount as {string}")
    public void user_enters_fd_amount_as(String amount) {
        LoggerUtility.info("Entering FD amount: " + amount);
        fdPage.enterDepositAmount(amount);
        LoggerUtility.pass("FD amount entered successfully");
    }

    @Then("Create FD modal should be {string}")
    public void create_fd_modal_should_be(String state) {
        LoggerUtility.info("Validating Create FD modal state: " + state);

        if (state.equalsIgnoreCase("displayed")) {
            Assert.assertTrue(fdPage.isModalDisplayed());
            LoggerUtility.pass("Create FD modal is displayed successfully");
        } else if (state.equalsIgnoreCase("closed")) {
            Assert.assertTrue(fdPage.isModalClosed());
            LoggerUtility.pass("Create FD modal is closed successfully");
        } else {
            LoggerUtility.warn("Invalid modal state provided: " + state);
        }
    }

    @Then("user should remain on {string} page")
    public void user_should_remain_on_page(String pageName) {
        LoggerUtility.info("Validating user remains on page: " + pageName);

        if (pageName.equalsIgnoreCase("fixed deposit")) {
            Assert.assertTrue(fdPage.isOnFDPage());
            LoggerUtility.pass("User remained on Fixed Deposit page");
        } else {
            LoggerUtility.warn("Unknown page validation requested: " + pageName);
        }
    }

    @When("user clicks close icon")
    public void user_clicks_close_icon() {
        LoggerUtility.info("User clicks close icon on Create FD modal");
        fdPage.clickCloseIcon();
        LoggerUtility.pass("Close icon clicked successfully");
    }

    @Then("{string} account should be auto selected")
    public void account_should_be_auto_selected(String selectionType) {
        LoggerUtility.info("Validating auto-selected account type: " + selectionType);

        if (selectionType.equalsIgnoreCase("first")) {
            Assert.assertTrue(fdPage.isFirstAccountAutoSelected());
            LoggerUtility.pass("First account is auto-selected successfully");
        } else {
            LoggerUtility.warn("Unknown account selection validation: " + selectionType);
        }
    }

    @Then("deposit amount should be {string}")
    public void deposit_amount_should_be(String status) {
        LoggerUtility.info("Validating deposit amount field status: " + status);

        if (status.equalsIgnoreCase("accepted")) {
            Assert.assertTrue(fdPage.isDepositAmountAccepted("5000"));
            LoggerUtility.pass("Deposit amount accepted successfully");
        } else {
            LoggerUtility.warn("Unknown deposit amount validation: " + status);
        }
    }

    @Then("FD submission should be {string}")
    public void fd_submission_should_be(String status) {
        LoggerUtility.info("Validating FD submission state: " + status);

        if (status.equalsIgnoreCase("prevented")) {
            Assert.assertTrue(fdPage.isFDSubmissionPrevented());
            LoggerUtility.pass("FD submission was prevented as expected");
        } else {
            LoggerUtility.warn("Unknown FD submission validation: " + status);
        }
    }

    @Then("FD submission for zero amount should be {string}")
    public void fd_submission_for_zero_amount_should_be(String status) {
        LoggerUtility.info("Validating zero amount FD submission state: " + status);

        if (status.equalsIgnoreCase("prevented")) {
            Assert.assertTrue(fdPage.isZeroDepositRejected());
            LoggerUtility.pass("Zero amount FD submission prevented successfully");
        } else {
            LoggerUtility.warn("Unknown zero amount validation: " + status);
        }
    }

    @Then("FD submission for negative amount should be {string}")
    public void fd_submission_for_negative_amount_should_be(String status) {
        LoggerUtility.info("Validating negative amount FD submission state: " + status);

        if (status.equalsIgnoreCase("prevented")) {
            Assert.assertTrue(fdPage.isNegativeDepositRejected());
            LoggerUtility.pass("Negative amount FD submission prevented successfully");
        } else {
            LoggerUtility.warn("Unknown negative amount validation: " + status);
        }
    }

    @And("user selects {string} account")
    public void user_selects_account_type(String accountType) {
        LoggerUtility.info("User selecting account type: " + accountType);


        if (accountType.equalsIgnoreCase("low balance")) {
            fdPage.selectLowBalanceAccount();
            LoggerUtility.pass("Low balance account selected successfully");
        } else {
            fdPage.selectAccount();
            LoggerUtility.pass("Valid linked account selected successfully");
        }
    }

    @Then("{string} validation should be displayed")
    public void validation_should_be_displayed(String validationType) {

        if (validationType.equalsIgnoreCase("insufficient balance")) {
            Assert.assertTrue(fdPage.isInsufficientBalanceDisplayed());
        }
    }

    @Then("interest rate field should be {string}")
    public void interest_rate_field_should_be(String fieldState) {
        LoggerUtility.info("Validating interest rate field state: " + fieldState);

        if (fieldState.equalsIgnoreCase("read only")) {
            Assert.assertTrue(fdPage.isInterestRateFieldReadOnly());
            LoggerUtility.pass("Interest rate field is read-only as expected");
        } else {
            LoggerUtility.warn("Unknown interest rate field validation: " + fieldState);
        }
    }

    @And("user enters valid FD details with amount {string} and tenure {string}")
    public void user_enters_valid_fd_details_with_amount_and_tenure(String amount, String tenure) {

        if (fdPage.verifyLinkedAccountsDisplayed()) {
            fdPage.selectAccount();
        } else {
            throw new RuntimeException("Test cannot proceed: No linked active account available");
        }

        fdPage.enterDepositAmount(amount);
        fdPage.selectTenureByVisibleText(tenure);
    }

    @Then("{string} in summary should be {string}")
    public void in_summary_should_be(String field, String expectedValue) {
        LoggerUtility.info("Validating summary field: " + field);
        LoggerUtility.info("Expected value: " + expectedValue);

        switch (field.toLowerCase()) {

            case "deposit amount":
                Assert.assertTrue(
                        fdPage.isDepositAmountCorrect(expectedValue),
                        "Deposit amount mismatch"
                );
                LoggerUtility.pass("Deposit amount in summary validated successfully");
                break;

            case "interest rate":
                Assert.assertTrue(
                        fdPage.isSummaryInterestRateCorrect(expectedValue),
                        "Interest rate mismatch"
                );
                LoggerUtility.pass("Interest rate in summary validated successfully");
                break;

            case "tenure":
                Assert.assertTrue(
                        fdPage.isSummaryTenureCorrect(expectedValue),
                        "Tenure mismatch"
                );
                LoggerUtility.pass("Tenure in summary validated successfully");
                break;

            case "interest earned":
                Assert.assertTrue(
                        fdPage.isInterestEarnedCorrect(expectedValue),
                        "Interest earned mismatch"
                );
                LoggerUtility.pass("Interest earned in summary validated successfully");
                break;

            case "maturity amount":
                Assert.assertTrue(
                        fdPage.isMaturityAmountCorrect(expectedValue),
                        "Maturity amount mismatch"
                );
                LoggerUtility.pass("Maturity amount in summary validated successfully");
                break;

            case "roi":
                Assert.assertTrue(
                        fdPage.isROICorrect(expectedValue),
                        "ROI mismatch"
                );
                LoggerUtility.pass("ROI in summary validated successfully");
                break;

            default:
                LoggerUtility.fail("Invalid summary field provided: " + field);
                throw new IllegalArgumentException("Invalid summary field: " + field);
        }
    }

    @And("user enters amount state {string}")
    public void user_enters_amount_state(String amountState) {
        LoggerUtility.info("Entering amount state: " + amountState);

        if (amountState.equalsIgnoreCase("blank")) {
            fdPage.leaveAmountBlank();
            LoggerUtility.pass("Deposit amount left blank");
        } else if (amountState.equalsIgnoreCase("zero")) {
            fdPage.enterZeroDepositAmount();
            LoggerUtility.pass("Entered zero deposit amount");
        } else if (amountState.equalsIgnoreCase("negative")) {
            fdPage.enterNegativeDepositAmount();
            LoggerUtility.pass("Entered negative deposit amount");
        } else {
            LoggerUtility.warn("Unknown amount state provided: " + amountState);
        }
    }

    @Then("Create FD button should be {string}")
    public void create_fd_button_should_be(String buttonState) {
        LoggerUtility.info("Validating Create FD button state: " + buttonState);

        if (buttonState.equalsIgnoreCase("disabled")) {
            Assert.assertTrue(fdPage.isCreateFDButtonDisabled());
            LoggerUtility.pass("Create FD button is disabled as expected");
        } else if (buttonState.equalsIgnoreCase("enabled")) {
            Assert.assertTrue(fdPage.isCreateFDButtonEnabled());
            LoggerUtility.pass("Create FD button is enabled as expected");
        } else {
            LoggerUtility.warn("Unknown button state validation: " + buttonState);
        }
    }

    @And("user enters invalid amount {string}")
    public void user_enters_invalid_amount(String amount) {
        LoggerUtility.info("Entering invalid FD amount: " + amount);

        if (amount == null || amount.trim().isEmpty()) {
            fdPage.leaveAmountBlank();
            LoggerUtility.pass("Deposit amount left blank");
        } else {
            fdPage.enterDepositAmount(amount);
            LoggerUtility.pass("Invalid deposit amount entered: " + amount);
        }
    }

    @And("user selects account type {string}")
    public void user_select_account_type(String accountType) {
        LoggerUtility.info("Selecting account type: " + accountType);

        if (accountType.equalsIgnoreCase("current")) {
            fdPage.selectCurrentAccount();
            LoggerUtility.pass("Current account selected successfully");
        } else {
            fdPage.selectAccount();
            LoggerUtility.pass("Default linked account selected successfully");
        }
    }


    @Then("{string} should be displayed")
    public void should_be_displayed(String validation) {
        LoggerUtility.info("Validating display of: " + validation);

        if (validation.equalsIgnoreCase("success message")) {
            Assert.assertTrue(fdPage.isSuccessMessageDisplayed());
            LoggerUtility.pass("Success message displayed successfully");
        } else if (validation.equalsIgnoreCase("new fd")) {
            Assert.assertTrue(fdPage.isFDCreated());
            LoggerUtility.pass("New FD displayed successfully");
        } else if (validation.equalsIgnoreCase("investment summary")) {
            Assert.assertTrue(fdPage.isInvestmentSummaryDisplayed());
            LoggerUtility.pass("Investment summary displayed successfully");
        } else if (validation.equalsIgnoreCase("tenure options")) {
            Assert.assertTrue(fdPage.areTenureOptionsDisplayed());
            LoggerUtility.pass("Tenure options displayed successfully");
        }
    }

    @When("user creates FD with account type {string}, amount {string}, and tenure {string}")
    public void user_creates_fd_with_account_type_amount_and_tenure(
            String accountType,
            String amount,
            String tenure) {
        LoggerUtility.info("Creating FD with details:");
        LoggerUtility.info("Account Type: " + accountType);
        LoggerUtility.info("Amount: " + amount);
        LoggerUtility.info("Tenure: " + tenure);

        fdPage.clickCreateFD();
        LoggerUtility.pass("Create FD modal opened");

        if (accountType.equalsIgnoreCase("current")) {
            fdPage.selectCurrentAccount();
            LoggerUtility.pass("Current account selected");
        } else {
            fdPage.selectAccount();
            LoggerUtility.pass("Default linked account selected");
        }

        fdPage.enterDepositAmount(amount);
        LoggerUtility.pass("Deposit amount entered: " + amount);
        fdPage.selectTenureByVisibleText(tenure);
        LoggerUtility.pass("Tenure selected: " + tenure);
        fdPage.createFixedDeposit();
        LoggerUtility.pass("Create FD submitted successfully");
    }

    @Then("FD should appear in active list with amount {string}")
    public void fd_should_appear_in_active_list_with_amount(String expectedAmount) {
        LoggerUtility.info("Validating FD in active list with amount: " + expectedAmount);

        Assert.assertTrue(
                fdPage.isNewFDVisible(),
                "New FD is not visible in active list"
        );
        LoggerUtility.pass("New FD is visible in active list");

        Assert.assertTrue(
                fdPage.isFDCardAmountCorrect(expectedAmount),
                "FD amount mismatch in active list"
        );
        LoggerUtility.pass("FD amount matches expected value");
    }

    @Then("FD card amount should be {string}")
    public void fd_card_amount_should_be(String expectedAmount) {
        LoggerUtility.info("Validating FD card amount: " + expectedAmount);
        Assert.assertTrue(
                fdPage.isFDCardAmountCorrect(expectedAmount),
                "FD card amount mismatch"
        );
        LoggerUtility.pass("FD card amount validated successfully");
    }

    @Then("FD status should be {string}")
    public void fd_status_should_be(String expectedStatus) {
        LoggerUtility.info("Validating FD status: " + expectedStatus);

        if (expectedStatus.equalsIgnoreCase("active")) {
            Assert.assertTrue(
                    fdPage.isFDStatusActive(),
                    "FD status is not active"
            );
            LoggerUtility.pass("FD status is active");
        } else {
            LoggerUtility.warn("Unknown FD status validation: " + expectedStatus);
        }
    }

    @Then("remaining days should be {string}")
    public void remaining_days_should_be(String expectedResult) {
        LoggerUtility.info("Validating remaining days display: " + expectedResult);

        if (expectedResult.equalsIgnoreCase("displayed")) {
            Assert.assertTrue(
                    fdPage.isRemainingDaysDisplayed(),
                    "Remaining days are not displayed"
            );
            LoggerUtility.pass("Remaining days displayed successfully");
        } else {
            LoggerUtility.warn("Unknown remaining days validation: " + expectedResult);
        }
    }

    @When("user stores current active FD count")
    public void user_stores_current_active_fd_count() {
        LoggerUtility.info("Storing current active FD count");
        fdPage.storeActiveFDCount();
        LoggerUtility.pass("Current active FD count stored successfully");
    }

    @Then("active FD count should be {string}")
    public void active_fd_count_should_be(String expectedResult) {

        LoggerUtility.info("Validating active FD count result: " + expectedResult);

        if (expectedResult.equalsIgnoreCase("incremented")) {
            Assert.assertTrue(
                    fdPage.isActiveFDCountIncremented(),
                    "Active FD count did not increment"
            );
            LoggerUtility.pass("Active FD count increment validated successfully");
        } else {
            LoggerUtility.warn("Unknown active FD count validation: " + expectedResult);
        }
    }

    @Then("{string} should be enforced")
    public void should_be_enforced(String validation) {
        LoggerUtility.info("Validating enforcement rule: " + validation);

        if (validation.equalsIgnoreCase("create fd disabled")) {
            Assert.assertTrue(
                    fdPage.isCreateFDButtonDisabled(),
                    "Create FD button should be disabled"
            );
            LoggerUtility.pass("Create FD disabled validation enforced successfully");
        } else {
            LoggerUtility.warn("Unknown enforcement validation: " + validation);
        }
    }

    //FD_ui feature file
    @Then("Create FD form should be {string}")
    public void create_fd_form_should_be(String formState) {
        LoggerUtility.info("Validating Create FD form state: " + formState);

        if (formState.equalsIgnoreCase("reset")) {
            Assert.assertTrue(
                    fdPage.isFDFormReset(),
                    "FD form was not reset after reopening modal"
            );
            LoggerUtility.pass("Create FD form reset validated successfully");
        } else {
            LoggerUtility.warn("Unknown form state validation: " + formState);
        }
    }

    @When("user refreshes browser {string} times")
    public void user_refreshes_browser_times(String refreshCount) {

        LoggerUtility.info("Refreshing browser " + refreshCount + " times");

        fdPage.refreshBrowserMultipleTimes(Integer.parseInt(refreshCount));

        LoggerUtility.pass("Browser refreshed successfully " + refreshCount + " times");
    }

    @Then("FD page should be {string}")
    public void fd_page_should_be(String pageState) {

        LoggerUtility.info("Validating FD page state: " + pageState);

        if (pageState.equalsIgnoreCase("loaded")) {
            Assert.assertTrue(
                    fdPage.isFDPageLoadedProperly(),
                    "FD page did not load correctly after refresh"
            );

            LoggerUtility.pass("FD page loaded successfully");
        } else {
            LoggerUtility.warn("Unknown page state validation: " + pageState);
        }
    }

    @Then("account number masking should be {string}")
    public void account_number_masking_should_be(String maskingStatus) {

        LoggerUtility.info("Validating account masking status: " + maskingStatus);

        if (maskingStatus.equalsIgnoreCase("masked")) {
            Assert.assertTrue(
                    fdPage.areAccountNumbersMasked(),
                    "Account numbers are not masked properly"
            );

            LoggerUtility.pass("Account number masking validated successfully");
        } else {
            LoggerUtility.warn("Unknown masking validation: " + maskingStatus);
        }
    }

    @Then("summary currency values should be in {string} format")
    public void summary_currency_values_should_be_in_format(String currencyFormat) {

        LoggerUtility.info("Validating summary currency format: " + currencyFormat);

        if (currencyFormat.equalsIgnoreCase("INR")) {
            Assert.assertTrue(
                    fdPage.areSummaryValuesFormattedInINR(),
                    "Summary values are not formatted in INR"
            );

            LoggerUtility.pass("Summary currency format validated successfully");
        } else {
            LoggerUtility.warn("Unknown currency format validation: " + currencyFormat);
        }
    }

    @Then("FD card currency values should be in {string} format")
    public void fd_card_currency_values_should_be_in_format(String currencyFormat) {

        LoggerUtility.info("Validating FD card currency format: " + currencyFormat);

        if (currencyFormat.equalsIgnoreCase("INR")) {
            Assert.assertTrue(
                    fdPage.areFDCardValuesFormattedInINR(),
                    "FD card values are not formatted in INR"
            );

            LoggerUtility.pass("FD card currency format validated successfully");
        } else {
            LoggerUtility.warn("Unknown FD card currency validation: " + currencyFormat);
        }
    }

    @Then("Create FD keyboard navigation should be {string}")
    public void create_fd_keyboard_navigation_should_be(String navigationStatus) {

        LoggerUtility.info("Validating keyboard navigation status: " + navigationStatus);

        if (navigationStatus.equalsIgnoreCase("working")) {
            Assert.assertTrue(
                    fdPage.isKeyboardNavigationWorking(),
                    "Keyboard tab navigation is not working"
            );

            LoggerUtility.pass("Keyboard navigation validated successfully");
        } else {
            LoggerUtility.warn("Unknown navigation validation: " + navigationStatus);
        }
    }

    @And("user performs session action {string}")
    public void user_performs_session_action(String action) {

        LoggerUtility.info("Performing session action: " + action);

        if (action.equalsIgnoreCase("logout")) {
            fdPage.logout();

            LoggerUtility.pass("User logged out successfully");
        } else if (action.equalsIgnoreCase("login")) {
            fdPage.login(
                    "sk.shreya651@gmail.com",
                    "Shreya@26"
            );

            LoggerUtility.pass("User logged in successfully");
        } else {
            LoggerUtility.warn("Unknown session action: " + action);
        }
    }

    @And("user navigates to {string}")
    public void user_navigates_to(String page) {

        LoggerUtility.info("Navigating to page: " + page);

        if (page.equalsIgnoreCase("fixed deposit")) {
            fdPage.navigateToFDPage();

            LoggerUtility.pass("Navigated to Fixed Deposit page successfully");
        } else {
            LoggerUtility.warn("Unknown navigation page: " + page);
        }
    }

    @Then("FD persistence should be {string}")
    public void fd_persistence_should_be(String expectedResult) {

        LoggerUtility.info("Validating FD persistence after relogin: " + expectedResult);

        if (expectedResult.equalsIgnoreCase("displayed")) {
            Assert.assertTrue(
                    fdPage.isLatestFDPresent(),
                    "Previously created FD is not displayed after relogin"
            );

            LoggerUtility.pass("FD persistence validated successfully");
        } else {
            LoggerUtility.warn("Unknown FD persistence validation: " + expectedResult);
        }
    }

    long fdLoadTime;

    @When("user opens fixed deposit page and measures load time")
    public void user_opens_fixed_deposit_page_and_measures_load_time() {

        LoggerUtility.info("Measuring FD dashboard page load time");

        fdLoadTime = fdPage.measureFDPageLoadTime();

        LoggerUtility.pass("FD dashboard load time recorded: " + fdLoadTime + " ms");
    }

    @Then("FD dashboard performance should be within {string} milliseconds")
    public void fd_dashboard_performance_should_be_within_milliseconds(String maxLoadTime) {

        LoggerUtility.info("Validating FD dashboard performance threshold: " + maxLoadTime + " ms");

        long expectedTime = Long.parseLong(maxLoadTime);

        Assert.assertTrue(
                fdLoadTime <= expectedTime,
                "FD dashboard load time exceeded threshold. Actual: " + fdLoadTime + " ms"
        );

        LoggerUtility.pass("FD dashboard performance validation successful");
    }

    @And("user performs FD action {string}")
    public void user_performs_fd_action(String fdAction) {

        LoggerUtility.info("Performing FD action: " + fdAction);

        if (fdAction.equalsIgnoreCase("View Details")) {
            fdPage.clickViewDetails();

            LoggerUtility.pass("View Details action performed successfully");
        } else {
            LoggerUtility.warn("Unknown FD action: " + fdAction);
        }
    }

    @Then("navigation result should be {string}")
    public void navigation_result_should_be(String expectedPage) {

        LoggerUtility.info("Validating navigation result: " + expectedPage);

        if (expectedPage.equalsIgnoreCase("FD details")) {
            Assert.assertTrue(
                    fdPage.isOnFDDetailsPage(),
                    "User was not navigated to FD details page"
            );

            LoggerUtility.pass("Navigation to FD details page successful");
        }
    }

    @When("user attempts FD creation beyond limit with account type {string}, amount {string}, and tenure {string}")
    public void user_attempts_fd_creation_beyond_limit(
            String accountType,
            String amount,
            String tenure) {

        int maxLimit = 10; // business rule

        fdPage.createFDUntilLimit(
                maxLimit,
                accountType,
                amount,
                tenure
        );
    }

    @Then("FD cards display status should be {string}")
    public void fd_cards_display_status_should_be(String expectedResult) {

        LoggerUtility.info("Validating FD cards display status: " + expectedResult);

        if (expectedResult.equalsIgnoreCase("displayed")) {
            Assert.assertTrue(
                    fdPage.areMultipleFDCardsDisplayed(),
                    "Multiple FD cards are not displayed properly"
            );

            LoggerUtility.pass("Multiple FD cards displayed successfully");
        }
    }

    //21
    @When("user creates {string} FDs with account type {string}, amount {string}, and tenure {string}")
    public void user_creates_multiple_fds_with_account_type_amount_and_tenure(
            String fdCount,
            String accountType,
            String amount,
            String tenure) {

        int count = Integer.parseInt(fdCount);

        LoggerUtility.info("Creating multiple FDs");
        LoggerUtility.info("FD Count: " + count);
        LoggerUtility.info("Account Type: " + accountType);
        LoggerUtility.info("Amount: " + amount);
        LoggerUtility.info("Tenure: " + tenure);

        for (int i = 0; i < count; i++) {

            fdPage.clickCreateFD();
            LoggerUtility.pass("Create FD modal opened");

            if (accountType.equalsIgnoreCase("current")) {
                fdPage.selectCurrentAccount();
                LoggerUtility.pass("Current account selected");
            } else {
                fdPage.selectAccount();
                LoggerUtility.pass("Default linked account selected");
            }

            fdPage.enterDepositAmount(amount);
            LoggerUtility.pass("Deposit amount entered: " + amount);

            fdPage.selectTenureByVisibleText(tenure);
            LoggerUtility.pass("Tenure selected: " + tenure);

            fdPage.createFixedDeposit();
            LoggerUtility.pass("FD created successfully: " + (i + 1));
        }
    }

    @Then("FD page scroll behavior should be {string}")
    public void fd_page_scroll_behavior_should_be(String scrollStatus) {

        LoggerUtility.info("Validating FD page scroll behavior: " + scrollStatus);

        if (scrollStatus.equalsIgnoreCase("scrollable")) {
            Assert.assertTrue(
                    fdPage.isPageScrollable(),
                    "FD page is not scrollable"
            );

            LoggerUtility.pass("FD page scroll validation successful");
        }
    }

    //failed test scenarios
    @And("user enters decimal FD amount {string}")
    public void user_enters_decimal_fd_amount(String amount) {

        LoggerUtility.info("Entering decimal FD amount: " + amount);

        fdPage.enterDecimalFDAmount(amount);

        LoggerUtility.pass("Decimal amount entered successfully");
    }

    @Then("decimal amount handling should be {string}")
    public void decimal_amount_handling_should_be(String expectedResult) {

        LoggerUtility.info("Validating decimal amount handling: " + expectedResult);

        if (expectedResult.equalsIgnoreCase("accepted")) {
            Assert.assertTrue(
                    fdPage.isDecimalFDAccepted(),
                    "Decimal FD amount was not accepted"
            );

            LoggerUtility.pass("Decimal amount validation successful");
        }
    }

    @And("user enters amount type {string}")
    public void user_enters_amount_type(String amountType) {

        LoggerUtility.info("Entering amount type: " + amountType);

        if (amountType.equalsIgnoreCase("exact balance")) {
            fdPage.enterExactAvailableBalance();

            LoggerUtility.pass("Exact available balance entered successfully");
        }
    }

    @Then("FD creation result should be {string}")
    public void fd_creation_result_should_be(String expectedResult) {

        LoggerUtility.info("Validating FD creation result: " + expectedResult);

        if (expectedResult.equalsIgnoreCase("successful")) {
            Assert.assertTrue(
                    fdPage.isFDCreatedSuccessfully(),
                    "FD was not created using exact available balance"
            );

            LoggerUtility.pass("FD creation using exact balance successful");
        }
    }

    @Then("custom deposit amount handling should be {string}")
    public void custom_deposit_amount_handling_should_be(String expectedResult) {
        LoggerUtility.info("Validating custom deposit amount handling: " + expectedResult);
        if (expectedResult.equalsIgnoreCase("accepted")) {
            Assert.assertTrue(
                    fdPage.isCustomAmountAccepted(),
                    "Custom deposit amount was not accepted"
            );
            LoggerUtility.pass("Custom deposit amount accepted successfully");
        }
    }

    @When("user opens FD details for {string} FD account")
    public void user_opens_fd_details_for_fd_account(String fdStatus) {
        LoggerUtility.info("Opening FD details for FD status: " + fdStatus);

        if (fdStatus.equalsIgnoreCase("Active")) {
            fdPage.openActiveFDDetails();
            LoggerUtility.pass("Active FD details opened successfully");
        }
    }

    @Then("Close FD option visibility should be {string}")
    public void close_fd_option_visibility_should_be(String expectedVisibility) {
        LoggerUtility.info("Validating Close FD option visibility: " + expectedVisibility);

        if (expectedVisibility.equalsIgnoreCase("visible")) {
            Assert.assertTrue(
                    fdPage.isCloseFDOptionVisible(),
                    "BUG: Close FD option is not available for active FD account"
            );
            LoggerUtility.pass("Close FD option is visible");
        }
    }

    @Then("FD creation limit status should be {string}")
    public void fd_creation_limit_status_should_be(String expectedResult) {
        LoggerUtility.info("Validating FD creation limit status: " + expectedResult);

        if (expectedResult.equalsIgnoreCase("enforced")) {
            Assert.assertTrue(
                    fdPage.isFDCreationLimitEnforced(),
                    "BUG: System allows unlimited FD creation for same user"
            );
            LoggerUtility.pass("FD creation limit is enforced");
        }
    }

//

    @Then("deposit amount should be accepted")
    public void deposit_amount_should_be_accepted() {
        LoggerUtility.info("Validating deposit amount acceptance");

        Assert.assertTrue(fdPage.isDepositAmountAccepted("5000"));
        LoggerUtility.pass("Deposit amount accepted successfully");
    }

    @Then("insufficient balance validation should be displayed")
    public void insufficient_balance_validation_should_be_displayed() {
        LoggerUtility.info("Checking insufficient balance validation");
        Assert.assertTrue(fdPage.isInsufficientBalanceDisplayed());
        LoggerUtility.pass("Insufficient balance validation displayed");
    }

    @And("user clicks tenure dropdown")
    public void user_clicks_tenure_dropdown() {
        LoggerUtility.info("Clicking tenure dropdown");
        fdPage.clickTenureDropdown();
        LoggerUtility.pass("Tenure dropdown clicked successfully");
    }

    @And("user enters amount without selecting tenure")
    public void user_enters_amount_without_selecting_tenure() {
        LoggerUtility.info("Entering amount without selecting tenure");
        fdPage.enterDepositAmount("5000");
        LoggerUtility.pass("Amount entered without selecting tenure");
    }

    @And("user selects tenure option {string}")
    public void user_selects_tenure_option(String tenure) {
        LoggerUtility.info("Selecting tenure option: " + tenure);

        if (tenure != null && !tenure.trim().isEmpty()) {
            fdPage.selectTenureByVisibleText(tenure);
            LoggerUtility.pass("Tenure selected successfully: " + tenure);
        } else {
            LoggerUtility.info("No tenure selected");
        }
    }


    @Then("interest rate should be {string}")
    public void interest_rate_should_be(String expectedRate) {
        LoggerUtility.info("Validating interest rate: " + expectedRate);
        Assert.assertTrue(fdPage.isInterestRateCorrect(expectedRate));
        LoggerUtility.pass("Interest rate validated successfully");
    }

    @Then("deposit amount in summary should be {string}")
    public void deposit_amount_in_summary_should_be(String amount) {

        LoggerUtility.info("Validating deposit amount in summary: " + amount);

        Assert.assertTrue(fdPage.isDepositAmountCorrect(amount));

        LoggerUtility.pass("Deposit amount in summary validated successfully");
    }

    @Then("summary interest rate should be {string}")
    public void summary_interest_rate_should_be(String rate) {

        LoggerUtility.info("Validating summary interest rate: " + rate);

        Assert.assertTrue(fdPage.isSummaryInterestRateCorrect(rate));

        LoggerUtility.pass("Summary interest rate validated successfully");
    }

    @Then("summary tenure should be {string}")
    public void summary_tenure_should_be(String tenure) {

        LoggerUtility.info("Validating summary tenure: " + tenure);

        Assert.assertTrue(fdPage.isSummaryTenureCorrect(tenure));

        LoggerUtility.pass("Summary tenure validated successfully");
    }

    @Then("interest earned should be {string}")
    public void interest_earned_should_be(String amount) {

        LoggerUtility.info("Validating interest earned: " + amount);

        Assert.assertTrue(fdPage.isInterestEarnedCorrect(amount));

        LoggerUtility.pass("Interest earned validated successfully");
    }

    @Then("maturity amount should be {string}")
    public void maturity_amount_should_be(String amount) {

        LoggerUtility.info("Validating maturity amount: " + amount);

        Assert.assertTrue(fdPage.isMaturityAmountCorrect(amount));

        LoggerUtility.pass("Maturity amount validated successfully");
    }

    @Then("ROI should be {string}")
    public void roi_should_be(String roi) {

        LoggerUtility.info("Validating ROI: " + roi);

        Assert.assertTrue(fdPage.isROICorrect(roi));

        LoggerUtility.pass("ROI validated successfully");
    }

    @And("user leaves amount blank")
    public void user_leaves_amount_blank() {

        LoggerUtility.info("Leaving deposit amount blank");

        fdPage.leaveAmountBlank();

        LoggerUtility.pass("Deposit amount field left blank");
    }

    @And("user selects current account")
    public void user_selects_current_account() {

        LoggerUtility.info("Selecting current account");

        fdPage.selectCurrentAccount();

        LoggerUtility.pass("Current account selected successfully");
    }

    @And("user creates a valid FD")
    public void user_creates_a_valid_fd() {

        LoggerUtility.info("Creating a valid FD");

        fdPage.createValidFD();

        LoggerUtility.pass("Valid FD created successfully");
    }

    @Then("newly created FD should appear in active FD list")
    public void newly_created_fd_should_appear_in_active_fd_list() {

        LoggerUtility.info("Validating newly created FD in active list");

        Assert.assertTrue(fdPage.isNewFDVisible());

        LoggerUtility.pass("New FD is visible in active FD list");
    }

    @And("user notes expected returns")
    public void user_notes_expected_returns() {

        LoggerUtility.info("Storing expected returns value");

        fdPage.storeExpectedReturns();

        LoggerUtility.pass("Expected returns stored successfully");
    }

    @Then("expected returns should increase")
    public void expected_returns_should_increase() {

        LoggerUtility.info("Validating expected returns increase after FD creation");

        Assert.assertTrue(fdPage.isExpectedReturnsUpdated());

        LoggerUtility.pass("Expected returns increased successfully");
    }

    // profile1.feature
    @Then("Create FD modal should close automatically")
    public void create_fd_modal_should_close_automatically() {

        LoggerUtility.info("Validating Create FD modal closes automatically after successful creation");

        Assert.assertTrue(fdPage.isModalClosedAfterSuccess());

        LoggerUtility.pass("Create FD modal closed automatically");
    }

    @Then("Create FD form should be reset")
    public void create_fd_form_should_be_reset() {

        LoggerUtility.info("Validating Create FD form reset after reopening");

        Assert.assertTrue(fdPage.isFDFormReset());

        LoggerUtility.pass("Create FD form reset successfully");
    }

    @Then("user should not be able to create fixed deposit")
    public void user_should_not_be_able_to_create_fixed_deposit() {

        LoggerUtility.info("Validating unauthorized user cannot create fixed deposit");

        Assert.assertTrue(fdPage.isFDCreationBlocked());

        LoggerUtility.pass("Unauthorized FD creation blocked successfully");
    }

    @And("user tries to access fixed deposit page directly")
    public void user_tries_to_access_fixed_deposit_page_directly() {

        LoggerUtility.info("User attempting direct access to Fixed Deposit page");

        fdPage.openFDPageDirectly();

        LoggerUtility.info("Direct navigation to FD page attempted");
    }

    @Then("previously created FD should be displayed")
    public void previously_created_fd_should_be_displayed() {

        LoggerUtility.info("Validating previously created FD persistence after relogin");

        Assert.assertTrue(fdPage.isLatestFDPresent());

        LoggerUtility.pass("Previously created FD displayed successfully");
    }

    @Then("FD dashboard should load within acceptable time")
    public void fd_dashboard_should_load_within_acceptable_time() {

        LoggerUtility.info("Validating FD dashboard performance within 5000 ms");

        Assert.assertTrue(fdLoadTime < 5000);

        LoggerUtility.pass("FD dashboard loaded within acceptable time");
    }

    // failed testcases
// 16
    @Then("system should accept decimal amount correctly")
    public void system_should_accept_decimal_amount_correctly() {

        LoggerUtility.info("BUG VALIDATION: Checking decimal FD amount acceptance");

        Assert.assertTrue(fdPage.isDecimalFDAccepted());

        LoggerUtility.pass("Decimal FD amount accepted successfully");
    }

    @And("user enters exact available balance")
    public void user_enters_exact_available_balance() {

        LoggerUtility.info("Entering exact available account balance as FD amount");

        fdPage.enterExactAvailableBalance();

        LoggerUtility.pass("Exact available balance entered successfully");
    }

    @Then("FD should be created successfully for exact balance amount")
    public void fd_should_be_created_successfully_for_exact_balance_amount() {

        LoggerUtility.info("Validating FD creation using exact available balance");

        Assert.assertTrue(fdPage.isFDCreatedSuccessfully());

        LoggerUtility.pass("FD created successfully using exact available balance");
    }

    @And("user enters custom deposit amount {string}")
    public void user_enters_custom_deposit_amount(String amount) {

        LoggerUtility.info("Entering custom deposit amount: " + amount);

        fdPage.enterCustomDepositAmount(amount);

        LoggerUtility.pass("Custom deposit amount entered successfully");
    }

    @Then("system should accept custom deposit amount")
    public void system_should_accept_custom_deposit_amount() {

        LoggerUtility.info("BUG VALIDATION: Checking custom deposit amount acceptance");

        Assert.assertTrue(fdPage.isCustomAmountAccepted());

        LoggerUtility.pass("Custom deposit amount accepted successfully");
    }
}