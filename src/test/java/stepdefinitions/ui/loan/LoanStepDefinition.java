package stepdefinitions.ui.loan;
import Hooks.Loan.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.loan.LoanPage;
import utils.ConfigReader;
import utils.ExcelReader;
import io.cucumber.datatable.DataTable;
import pages.loan.EmiPage;
import pages.loan.ForeclosurePage;
import utils.LoggerUtility;

import java.util.Map;

public class LoanStepDefinition {
    WebDriver driver;

    LoanPage loanPage;

    ExcelReader excel;

    ConfigReader config;

    String filePath;

    EmiPage emiPage;

    ForeclosurePage foreclosurePage;

    @Given("User launches banking application")
    public void user_launches_banking_application()
            throws Exception {
        LoggerUtility.info("User opens Bank application");

        config =
                new ConfigReader();

        driver =
                Hooks.factory.getDriver();

        Hooks.factory.FetchPage(
                config.getProp("baseUrl")
        );
        LoggerUtility.pass("User opened Bank Application");
    }

    @And("User navigates to Loan page")
    public void user_navigates_to_loan_page() {
        LoggerUtility.info("User Taps Loan Section");

        loanPage =
                new LoanPage(driver);

        loanPage.NavigateToLoanPage();

    }

    @Then("Loan page should display successfully")
    public void loan_page_should_display_successfully() {

        Assert.assertTrue(
                loanPage.IsLoanPageDisplayed()
        );
        LoggerUtility.pass("Loan Section Opened Successfully");
    }

    @Then("User should see no active loans message")
    public void user_should_see_no_active_loans_message() {

        Assert.assertTrue(
                loanPage.IsNoActiveLoanMessageDisplayed()
        );
    }

    @Then("Apply Loan button should be enabled")
    public void apply_loan_button_should_be_enabled() {

        Assert.assertTrue(
                loanPage.IsApplyLoanButtonEnabled()
        );
    }

    @When("User clicks Apply Loan button")
    public void user_clicks_apply_loan_button() {
        LoggerUtility.info("User Clicked apply loan Button");

        loanPage.ClickApplyLoanButton();
    }

    @And("User enters valid loan details")
    public void user_enters_valid_loan_details(
            DataTable dataTable
    ) {
        LoggerUtility.info("User Entering Loan Details");

        Map<String, String> loanData =
                dataTable.asMaps(
                        String.class,
                        String.class
                ).get(0);

        loanPage.EnterLoanDetails(
                loanData.get("LoanType"),
                loanData.get("Amount"),
                loanData.get("Tenure"),
                loanData.get("Income"),
                loanData.get("Liabilities")
        );
    }

    @And("User clicks Submit Loan button")
    public void user_clicks_submit_loan_button() throws Exception{

        loanPage.ClickSubmitLoanButton();
        Thread.sleep(5000);
        LoggerUtility.pass("Loan Section Opened");
        LoggerUtility.pass("User Submitted Application ");
//        Thread.sleep(10000);
    }

    @Then("Loan application submitted successfully")
    public void loan_application_submitted_successfully() {

        Assert.assertTrue(
                loanPage.IsSuccessToastDisplayed()
        );
    }

    @And("User enters low annual income loan details for {string}")
    public void user_enters_low_annual_income_loan_details_for(String tcId) throws Exception {

        filePath =
                System.getProperty("user.dir")
                        + "/src/test/resources/Data/Loan_TestData.xlsx";

        excel =
                new ExcelReader(
                        filePath,
                        "TS_LOAN_002"
                );

        int rowNumber = 0;

        for(int i = 1; i < excel.GetNumberOfRows(); i++) {

            String excelTcId =
                    excel.GetCellData(i,0);

            if(excelTcId.equals(tcId)) {

                rowNumber = i;
                break;
            }
        }

        String loanType =
                excel.GetCellData(rowNumber,3);

        String amount =
                excel.GetCellData(rowNumber,4);

        String tenure =
                excel.GetCellData(rowNumber,5);

        String income =
                excel.GetCellData(rowNumber,6);

        String liabilities =
                excel.GetCellData(rowNumber,7);

        loanPage.EnterLoanDetails(
                loanType,
                amount,
                tenure,
                income,
                liabilities
        );
    }

    @Then("Annual income field should be disabled")
    public void annual_income_field_should_be_disabled() {

        boolean isDisabled =
                loanPage.IsAnnualIncomeFieldDisabled();
        LoggerUtility.info(
                "Annual Income Field Disabled : "
                        + isDisabled
        );

        Assert.assertTrue(
                isDisabled,
                "Annual Income Field is ENABLED for low income user"
        );
    }
    @Then("Annual income field should display entered income")
    public void annual_income_field_should_display_entered_income() {

        String actualIncome =
                loanPage.GetDisplayedAnnualIncome();
        System.out.println(
                "Displayed Income : "
                        + actualIncome
        );

        Assert.assertEquals(
                actualIncome,
                "50000"
        );

    }
    @Then("User should see 1 loan slot available")
    public void user_should_see_1_loan_slot_available() {

        Assert.assertTrue(
                loanPage.IsLoanSlotAvailableDisplayed()
        );
    }


    @Then("Loan limit reached message should display")
    public void loan_limit_reached_message_should_display() {

        String actualMessage =
                loanPage.GetLoanLimitMessage();

        System.out.println(
                "Loan Limit Message : "
                        + actualMessage
        );

        Assert.assertEquals(
                actualMessage,
                "Loan limit reached (3/3)"
        );
    }
    @Then("No active accounts found message should display")
    public void no_active_accounts_found_message_should_display() {

        Assert.assertTrue(
                loanPage.IsNoActiveAccountsMessageDisplayed()
        );
    }
    @Then("Linked account dropdown should display")
    public void linked_account_dropdown_should_display() {

        Assert.assertTrue(
                loanPage.IsLinkedAccountDropdownDisplayed()
        );
    }
    @Then("Submit loan button should be disabled")
    public void submit_loan_button_should_be_disabled() {

        Assert.assertTrue(
                loanPage.IsSubmitLoanButtonDisabled()
        );
    }
    @And("User enters liability validation details with liabilities {string}")
    public void user_enters_liability_validation_details_with_liabilities(String liabilities) {

        loanPage.EnterLiabilities(liabilities);
    }
    @Then("Liabilities field should be auto-filled with {string}")
    public void liabilities_field_should_be_auto_filled_with(
            String expectedValue
    ) {

        String actualValue =
                loanPage.GetLiabilitiesValue();

        System.out.println("Actual : " + actualValue);
        System.out.println("Expected : " + expectedValue);

        Assert.assertEquals(
                actualValue,
                expectedValue
        );
    }
    @Then("Liability validation should display")
    public void liability_validation_should_display() {

        Assert.assertTrue(
                loanPage.IsLiabilityValidationDisplayed()
        );
    }

    @Then("Liabilities field should be auto populated")
    public void liabilities_field_should_be_auto_populated() {

        String liabilities =
                loanPage.GetAutoPopulatedLiabilities();

        System.out.println(
                "Fetched Liabilities : "
                        + liabilities
        );

        Assert.assertFalse(
                liabilities.isEmpty(),
                "Liabilities field is empty"
        );

        double liabilityValue =
                Double.parseDouble(liabilities);

        Assert.assertTrue(
                liabilityValue > 0,
                "Liabilities not auto populated"
        );
    }
    @And("User selects {string} loan type")
    public void user_selects_loan_type(String loanType) {
        LoggerUtility.info("User Selects Loan Type");

        loanPage.SelectLoanType(loanType);
    }
    @And("User enters amount {string}")
    public void user_enters_amount(String amount) {
        LoggerUtility.info("User Enters Amount");

        loanPage.EnterLoanAmount(amount);
    }
    @And("User enters tenure {string}")
    public void user_enters_tenure(String tenure) {
        LoggerUtility.info("User Enters Tenure in Months");

        loanPage.EnterLoanTenure(tenure);
    }
    @Then("{string} validation should display")
    public void validation_should_display(
            String expectedMessage
    ) {

        if(expectedMessage.equalsIgnoreCase(
                "Loan application submitted successfully"
        )) {

            String actualToast =
                    loanPage.getToastMessage();

            System.out.println(
                    "Actual Toast Message : "
                            + actualToast
            );

            Assert.assertTrue(

                    actualToast.toLowerCase().contains(
                            expectedMessage.toLowerCase()
                    )
            );
        }

        else {

            String actualMessage =
                    loanPage.GetAmountValidationMessage();

            System.out.println(
                    "Actual Validation Message : "
                            + actualMessage
            );

            Assert.assertTrue(

                    actualMessage.toLowerCase()
                            .contains(
                                    expectedMessage.toLowerCase()
                            )
            );
        }
    }

    @Then("Non numeric amount should not be accepted")
    public void non_numeric_amount_should_not_be_accepted() {


        String actualValue =
                loanPage.GetLoanAmountFieldValue();

        Assert.assertEquals(
                actualValue,
                ""
        );
    }

    @Then("{string} tenure validation should display")
    public void tenure_validation_should_display(
            String expectedMessage
    ) {

        String actualMessage =
                loanPage.GetTenureValidationMessage();

        System.out.println(
                "Actual Tenure Validation : "
                        + actualMessage
        );

        Assert.assertTrue(
                actualMessage
                        .toLowerCase()
                        .contains(
                                expectedMessage.toLowerCase()
                        )
        );
    }

    // ================= EMI PAYMENT STEPS =================
    // These steps take loan creation and EMI payment data directly
    // from the feature file DataTable. No Excel is used here.

    @When("User creates loan with following data")
    public void user_creates_loan_with_following_data(
            DataTable dataTable
    ) {
        LoggerUtility.info("User Creates Loan");

        loanPage =
                new LoanPage(driver);

        emiPage =
                new EmiPage(driver);

        if(emiPage.IsPayEmiButtonAvailable()) {

            System.out.println(
                    "Active loan found, using existing loan for EMI payment"
            );

            return;
        }

        Map<String, String> loanData =
                dataTable.asMaps(
                        String.class,
                        String.class
                ).get(0);

        String loanType =
                loanData.get("LoanType");

        String amount =
                loanData.get("Amount");

        String tenure =
                loanData.get("Tenure");

        String income =
                loanData.get("Income");

        String liabilities =
                loanData.get("Liabilities");

        loanPage.ClickApplyLoanButton();

        loanPage.EnterLoanDetails(
                loanType,
                amount,
                tenure,
                income,
                liabilities
        );

        loanPage.ClickSubmitLoanButton();

        Assert.assertTrue(
                loanPage.IsSuccessToastDisplayed()
        );
    }


    @Then("Loan should be approved")
    public void loan_should_be_approved() {

        String actualStatus =
                loanPage.GetLoanApprovalStatus();

        Assert.assertEquals(
                actualStatus,
                "APPROVED"
        );
    }

    @Then("Loan should be rejected")
    public void loan_should_be_rejected() {

        String actualStatus =
                loanPage.GetLoanApprovalStatus();

        Assert.assertEquals(
                actualStatus,
                "REJECTED"
        );
    }

    @And("User pays EMI with following data")
    public void user_pays_emi_with_following_data(
            DataTable dataTable
    ) {
        LoggerUtility.info("User is paying EMI Amount");

        emiPage =
                new EmiPage(driver);

        Map<String, String> emiData =
                dataTable.asMaps(
                        String.class,
                        String.class
                ).get(0);

        String paymentType =
                emiData.get("PaymentType");

        String installmentsToPay =
                emiData.get("InstallmentsToPay");

        emiPage.PayEmi(
                paymentType,
                installmentsToPay
        );
        LoggerUtility.pass("User Paid Installment Amount");
    }

    @Then("{string} payment message should display")
    public void payment_message_should_display(String expectedMessage) {

        emiPage = new EmiPage(driver);

        String actualMessage =
                emiPage.GetPaymentMessage();

        System.out.println("Actual Payment Message : " + actualMessage);

        Assert.assertTrue(
                actualMessage.toLowerCase()
                        .contains(expectedMessage.toLowerCase()),
                "Expected: " + expectedMessage + " but found: " + actualMessage
        );
    }

    // ================= END EMI PAYMENT STEPS =================

    // ================= FORECLOSURE STEPS =================

    @And("User forecloses the created loan")
    public void user_forecloses_the_created_loan() {
        LoggerUtility.info("User is Foreclosing the loan");

        foreclosurePage =
                new ForeclosurePage(driver);

        foreclosurePage.ForecloseLoan();
        LoggerUtility.pass("User Foreclosed the loan with paying full amount");
    }

    @And("User forecloses the created loan using insufficient balance account")
    public void user_forecloses_the_created_loan_using_insufficient_balance_account() {

        foreclosurePage =
                new ForeclosurePage(driver);

        foreclosurePage.ForecloseLoanUsingInsufficientBalanceAccount();
    }

    @Then("{string} foreclosure message should display")
    public void foreclosure_message_should_display(
            String expectedMessage
    ) {

        if(foreclosurePage == null) {

            foreclosurePage =
                    new ForeclosurePage(driver);
        }

        String actualMessage =
                foreclosurePage.GetForeclosureMessage();

        System.out.println(
                "Actual Foreclosure Message : "
                        + actualMessage
        );

        Assert.assertTrue(
                actualMessage.toLowerCase()
                        .contains(
                                expectedMessage.toLowerCase()
                        )
        );
    }

    // ================= END FORECLOSURE STEPS =================
}

