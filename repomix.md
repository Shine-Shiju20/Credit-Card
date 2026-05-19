# Repomix - Credit Card & Profile Testing Scripts Repository

This file consolidates the key configuration, feature files, step definitions, page objects, and utilities used in the Banking System test suite.

## Repository Directory Structure

```text
Credit-Card-CreditCardAPI_Testing/
├── pom.xml
└── src/
    └── test/
        ├── java/
        │   ├── Hooks/
        │   │   └── Hooks.java
        │   ├── api/
        │   │   ├── Account/
        │   │   │   ├── Account_API.java
        │   │   │   └── RuntimeAccountManager.java
        │   │   └── creditcard/
        │   │       ├── Credit_API.java
        │   │       └── RuntimeCardManager.java
        │   ├── pages/
        │   │   ├── LoginPage.java
        │   │   ├── ProfilePage.java
        │   │   └── common/
        │   │       └── CommonDashBoard.java
        │   ├── runners/
        │   │   ├── TestRunner.java
        │   │   └── creditCardAPIRunner.java
        │   ├── stepdefinitions/
        │   │   ├── api/
        │   │   │   ├── CreditCard_API_Steps.java
        │   │   │   └── User_API_Steps.java
        │   │   └── ui/
        │   │       └── ProfilePage/
        │   │           ├── AddressStep.java
        │   │           ├── AdhaarStep.java
        │   │           ├── OccupationSteps.java
        │   │           └── ProfileStepDefinition.java
        │   └── utils/
        │       ├── CleanupManager.java
        │       ├── ConfigReader.java
        │       ├── DriverFactory.java
        │       ├── ExcelReader.java
        │       ├── ExecutionModeResolver.java
        │       ├── LoggerUtility.java
        │       ├── OrphanCleanup.java
        │       ├── RuntimeEntityFactory.java
        │       ├── ScenarioContext.java
        │       ├── ScreenShotUtil.java
        │       └── TokenManager.java
        └── resources/
            ├── config.properties
            ├── log4j2.xml
            └── features/
                ├── ProfilePage/
                │   ├── aadhaar_validation.feature
                │   ├── address_validation.feature
                │   ├── occupation_validation.feature
                │   ├── pan_validation.feature
                │   └── profile.feature
                └── creditcard/
                    ├── CC_S_001_Entry_Tier_Application.feature
                    ├── CC_S_002_Premium_Tier_Application.feature
                    ├── CC_S_003_Eligibility_Failures.feature
                    ├── CC_S_004_Credit_Limit_Calculation.feature
                    ├── CC_S_005_Purchase_Transaction.feature
                    ├── CC_S_006_Balance_Repayment.feature
                    ├── CC_S_007_Block_And_Unblock.feature
                    ├── CC_S_008_Card_Close.feature
                    ├── CC_S_009_Card_Delete.feature
                    ├── CC_S_010_Card_Statement.feature
                    ├── CC_S_011_Monthly_Billing_Cycle.feature
                    ├── CC_S_012_Late_Payment_Penalty.feature
                    ├── CC_S_013_Multiple_Card_Management.feature
                    ├── CC_S_014_Payment_Tracking_Integration.feature
                    └── CC_S_015_Security_And_Authorization.feature
```

---

## Configuration & Setup

### File: `pom.xml`
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>Banking_Scripts</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>Banking_Scripts</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
      <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>3.8.1</version>
          <scope>test</scope>
      </dependency>
      <dependency>
          <groupId>org.seleniumhq.selenium</groupId>
          <artifactId>selenium-java</artifactId>
          <version>4.31.0</version>
      </dependency>
      <dependency>
          <groupId>org.testng</groupId>
          <artifactId>testng</artifactId>
          <version>7.11.0</version>
          <scope>test</scope>
      </dependency>
      <dependency>
          <groupId>io.cucumber</groupId>
          <artifactId>cucumber-java</artifactId>
          <version>7.15.0</version>
      </dependency>
      <dependency>
          <groupId>io.cucumber</groupId>
          <artifactId>cucumber-testng</artifactId>
          <version>7.15.0</version>
      </dependency>
      <dependency>
          <groupId>commons-io</groupId>
          <artifactId>commons-io</artifactId>
          <version>2.16.1</version>
      </dependency>
      <dependency>
          <groupId>com.fasterxml.jackson.core</groupId>
          <artifactId>jackson-databind</artifactId>
          <version>2.17.0</version>
      </dependency>
      <dependency>
          <groupId>io.rest-assured</groupId>
          <artifactId>rest-assured</artifactId>
          <version>5.3.0</version>
          <scope>compile</scope>
      </dependency>
      <dependency>
          <groupId>org.apache.poi</groupId>
          <artifactId>poi-ooxml</artifactId>
          <version>5.2.3</version>
      </dependency>
      <dependency>
          <groupId>org.apache.logging.log4j</groupId>
          <artifactId>log4j-api</artifactId>
          <version>2.23.1</version>
      </dependency>
      <dependency>
          <groupId>org.apache.logging.log4j</groupId>
          <artifactId>log4j-core</artifactId>
          <version>2.23.1</version>
      </dependency>
      <dependency>
          <groupId>net.java.dev.jna</groupId>
          <artifactId>jna</artifactId>
          <version>5.13.0</version>
      </dependency>
  </dependencies>
  <build>
      <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-compiler-plugin</artifactId>
              <configuration>
                  <source>11</source>
                  <target>11</target>
              </configuration>
          </plugin>
      </plugins>
  </build>
</project>
```

---

## Test Execution Hooks & Runner Configuration

### File: `src/test/java/Hooks/Hooks.java`
```java
package Hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import utils.LoggerUtility;

import utils.TokenManager;
import utils.RuntimeEntityFactory;
import utils.CleanupManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger logger = LoggerUtility.getLogger(Hooks.class);

    public static DriverFactory factory;

    @Before("@CreditCardAPI")
    public void setupAPI() {
        logger.info("Setting up API Runtime Environment");
        TokenManager.login();
        RuntimeEntityFactory.createRuntimeEnvironment();
    }

    @After("@CreditCardAPI")
    public void tearDownAPI() {
        logger.info("Cleaning up API Runtime Environment");
        CleanupManager.cleanup();
    }

    @Before("not @CreditCardAPI")
    public void setup() {
        factory = new DriverFactory();
        factory.getDriver().manage().window().maximize();
        logger.info("Browser Started");
    }

    @After("not @CreditCardAPI")
    public void tearDown() {
        if (factory != null) {
            factory.CloseDriver();
        }
        logger.info("Browser Closed");
    }
}
```

---

## Step Definition Layers

### File: `src/test/java/stepdefinitions/api/CreditCard_API_Steps.java`
```java
package stepdefinitions.api;

import api.creditcard.Credit_API;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.ExcelReader;
import utils.LoggerUtility;

import utils.ScenarioContext;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreditCard_API_Steps {
    private static final Logger logger = LoggerUtility.getLogger(CreditCard_API_Steps.class);

    private final Credit_API creditAPI = new Credit_API();

    private Map<String, String> testData;
    private Response response;

    @Given("test data is loaded for {string}")
    public void test_data_is_loaded_for(String testcaseId) throws IOException {
        logger.info("Loading test data for testcaseId: {}", testcaseId);

        ExcelReader excelReader = new ExcelReader(
                "src/test/resources/Data/Credit_Card_TestData_Final.xlsx",
                "Test_Data"
        );

        testData = excelReader.getData(testcaseId);
    }

    private String getAuthToken() {
        String authState = testData.get("Auth_State");

        if ("logged_in".equalsIgnoreCase(authState)) {
            return utils.TokenManager.getToken(
                    testData.get("Login_Email"),
                    testData.get("Login_Password")
            );
        }
        return authState;
    }

    private Map<String, Object> buildApplicationPayload() {
        Map<String, Object> payload = new HashMap<>();

        payload.put("userId", ScenarioContext.get().getLoggedInUserId());
        payload.put("source_account_id", ScenarioContext.get().getRuntimeAccountId());
        payload.put("requested_limit", parseDouble(testData.get("Requested_Limit")));
        payload.put("card_tier", testData.get("Card_Tier"));
        payload.put("existing_liabilities", parseDouble(testData.get("Monthly_Liabilities")));

        return payload;
    }

    private Double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        return Double.parseDouble(value.trim());
    }

    private Map<String, Object> buildPurchasePayload() {
        Map<String, Object> payload = new HashMap<>();

        payload.put("cardId", getCardId());
        payload.put("amount", parseDouble(testData.get("Amount")));
        payload.put("merchant", testData.get("Merchant"));
        payload.put("category", testData.get("Category"));

        return payload;
    }

    private Map<String, Object> buildRepaymentPayload() {
        Map<String, Object> payload = new HashMap<>();

        payload.put("cardId", getCardId());
        payload.put("amount", parseDouble(testData.get("Amount")));
        payload.put("minimumDue", parseDouble(testData.get("Minimum_Due")));
        payload.put("outstandingBalance", parseDouble(testData.get("Outstanding_Balance")));

        return payload;
    }

    private Map<String, Object> buildBlockPayload() {
        Map<String, Object> payload = new HashMap<>();

        payload.put("cardId", getCardId());
        payload.put("cardStatus", testData.get("Card_Status"));

        return payload;
    }

    private Map<String, Object> buildClosePayload() {
        Map<String, Object> payload = new HashMap<>();

        payload.put("outstandingBalance", parseDouble(testData.get("Outstanding_Balance")));
        payload.put("minimumDue", parseDouble(testData.get("Minimum_Due")));

        return payload;
    }

    private Map<String, Object> buildPaymentCallbackPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("cardId", ScenarioContext.get().getRuntimeCardId());
        payload.put("userId", testData.get("Seed_Profile"));
        payload.put("amount", testData.get("Amount"));
        payload.put("merchant", testData.get("Merchant"));
        return payload;
    }

    private Map<String, Object> buildSchedulerPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("cardId", ScenarioContext.get().getRuntimeCardId());
        payload.put("userId", testData.get("Seed_Profile"));
        payload.put("dueDate", testData.get("Due_Date"));
        return payload;
    }

    @When("user submits credit card application")
    public void user_submits_credit_card_application() {
        response = creditAPI.applyCreditCard(
                getAuthToken(),
                buildApplicationPayload()
        );
        System.out.println("Apply Response: " + response.getBody().asString());
    }

    @When("user fetches linked accounts")
    public void user_fetches_linked_accounts() {
        response = creditAPI.getUserAccounts(
                getAuthToken()
        );
    }

    @When("user fetches credit card details")
    public void user_fetches_credit_card_details() {
        response = creditAPI.getCreditCardById(
                getAuthToken(), getCardId()
        );
    }

    @When("user fetches user credit cards")
    public void user_fetches_user_credit_cards() {
        response = creditAPI.getUserCreditCards(
                getAuthToken(),
                ScenarioContext.get().getLoggedInUserId()
        );
    }

    @When("user performs purchase transaction")
    public void user_performs_purchase_transaction() {
        response = creditAPI.purchaseTransaction(
                getAuthToken(),
                buildPurchasePayload()
        );
    }

    @When("user performs repayment transaction")
    public void user_performs_repayment_transaction() {
        response = creditAPI.repayCreditCardBalance(
                getAuthToken(),
                buildRepaymentPayload()
        );
    }

    @When("user blocks the credit card")
    public void user_blocks_the_credit_card() {
        response = creditAPI.blockCreditCard(
                getAuthToken(),
                buildBlockPayload()
        );
    }

    @When("user closes the credit card")
    public void user_closes_the_credit_card() {
        response = creditAPI.closeCreditCard(
                getAuthToken(),
                getCardId(),
                buildClosePayload()
        );
    }

    @When("user deletes the credit card")
    public void user_deletes_the_credit_card() {
        response = creditAPI.deleteCreditCard(
                getAuthToken(),
                getCardId()
        );
    }

    @When("user fetches credit card statement")
    public void user_fetches_credit_card_statement() {
        response = creditAPI.getCardStatement(
                getAuthToken(),
                getCardId()
        );
    }

    @When("user downloads statement PDF")
    public void user_downloads_statement_pdf() {
        response = creditAPI.downloadStatementPdf(
                getAuthToken(),
                getCardId()
        );
    }

    @When("user fetches repayment history")
    public void user_fetches_repayment_history() {
        response = creditAPI.getRepaymentHistory(
                getAuthToken(),
                getCardId()
        );
    }

    @When("payment callback is triggered")
    public void payment_callback_is_triggered() {
        response = creditAPI.paymentCallback(
                buildPaymentCallbackPayload()
        );
    }

    @When("billing scheduler is triggered")
    public void billing_scheduler_is_triggered() {
        response = creditAPI.triggerBillingScheduler(
                getAuthToken(),
                buildSchedulerPayload()
        );
    }

    @When("late payment scheduler is triggered")
    public void late_payment_scheduler_is_triggered() {
        response = creditAPI.triggerLatePenaltyScheduler(
                getAuthToken(),
                buildSchedulerPayload()
        );
    }

    @When("payment reconciliation service is triggered")
    public void payment_reconciliation_service_is_triggered() {
        response = creditAPI.triggerPaymentReconciliation(
                getAuthToken(),
                buildSchedulerPayload()
        );
    }

    @When("notification service is triggered")
    public void notification_service_is_triggered() {
        response = creditAPI.triggerNotificationService(
                getAuthToken(),
                buildSchedulerPayload()
        );
    }

    @When("audit logging service is triggered")
    public void audit_logging_service_is_triggered() {
        response = creditAPI.triggerAuditLoggingService(
                getAuthToken(),
                buildSchedulerPayload()
        );
    }

    @Then("API response status code should be {int}")
    public void api_response_status_code_should_be(Integer expectedStatusCode) {
        logger.info("Verifying API response status code is {}", expectedStatusCode);
        Assert.assertNotNull(response, "Response object is null");
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Unexpected status code");
    }

    @Then("response should contain generated card id")
    public void response_should_contain_generated_card_id() {
        Assert.assertNotNull(response, "Response object is null");
        String generatedCardId = response.jsonPath().getString("data.card_id");
        Assert.assertNotNull(generatedCardId, "Generated card id is null");
        Assert.assertFalse(generatedCardId.trim().isEmpty(), "Generated card id is empty");
    }

    private String getCardId() {
        return ScenarioContext.get().getRuntimeCardId();
    }
}
```

### File: `src/test/java/stepdefinitions/ui/ProfilePage/ProfileStepDefinition.java`
```java
package stepdefinitions.ui.ProfilePage;

import Hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProfilePage;
import pages.common.CommonDashBoard;
import utils.ConfigReader;
import utils.ExcelReader;

import java.io.IOException;
import java.time.Duration;

public class ProfileStepDefinition {
    WebDriver driver;
    ConfigReader config;
    String profileUrl;
    private ProfilePage page;
    String sucessMessage;

    @Given("User launches the banking application")
    public void user_launches_the_banking_application() throws IOException {
        this.config = new ConfigReader();
        this.driver = Hooks.factory.getDriver();
        Hooks.factory.FetchPage(this.config.getProp("baseUrl"));
        this.profileUrl = config.getProp("profileUrl");
        this.page = new ProfilePage(this.driver);
    }

    @And("User logs in using valid credentials")
    public void user_logs_in_using_valid_credentials() {
        LoginPage logingPage = new LoginPage(this.driver);
        logingPage.clickFirstLogin();
        logingPage.enterEmail("abhirampb9@gmail.com");
        logingPage.enterPassword("Strong@123");
        logingPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    @And("User navigates to the Profile page")
    public void user_navigates_to_the_profile_page() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("Navigated to profile URL: " + this.config.getProp("profileUrl"));
        this.driver.get(this.config.getProp("profileUrl"));
        Thread.sleep(100);
    }

    @Then("User profile page should load successfully")
    public void user_profile_page_should_load_successfully() {
        ProfilePage profileInstance = new ProfilePage(this.driver);
        profileInstance.WaitTillProfileVisibility();
    }

    @When("User clicks on Logout button")
    public void user_clicks_on_logout_button() {
        CommonDashBoard DashBoard = new CommonDashBoard(this.driver);
        DashBoard.ClickLogout();
    }

    @And("User manually navigates to Profile page URL")
    public void user_manually_navigates_to_profile_page_url() {
        this.driver.get(profileUrl);
    }

    @Then("User should get redirected to Home page")
    public void user_should_get_redirected_to_home_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));
    }

    @When("User updates profile details")
    public void user_updates_profile_details() throws IOException, InterruptedException {
        ExcelReader reader = new ExcelReader("C:\\Users\\abhiram.x1\\Desktop\\Testing-Bank-scripts\\Banking_Scripts\\src\\test\\resources\\Data\\Profile_TestData.xlsx" , "ValidProfileData");
        this.page = new ProfilePage(this.driver);
        page.ClickEditProfileButton();
        page.ClearNameField();
        page.SetNameField(reader.GetCellData(1,0));
        Thread.sleep(1000);
        page.ClearOccupationField();
        page.setOccupationField(reader.GetCellData(1,2));
        Thread.sleep(1000);
        page.ClearPhoneField();
        page.setPhoneField(reader.GetCellData(1,1));
        Thread.sleep(1000);
        page.ClearAddressField();
        page.SetAddressField(reader.GetCellData(1,3));
        Thread.sleep(1000);
        page.ClearAnnualIncomeField();
        page.SetAnnualIncomeField(reader.GetCellData(1,4));
        Thread.sleep(1000);
        page.ClickSaveProfileButton();
    }

    @Then("User should see message {string}")
    public void user_should_see_message(String expectedMessage) {
        Assert.assertEquals(page.getToastMessage() , expectedMessage);
    }

    @And("Details to be Displayed Correctly After updation")
    public void Correct_details() throws IOException {
        ExcelReader reader = new ExcelReader("C:\\Users\\abhiram.x1\\Desktop\\Testing-Bank-scripts\\Banking_Scripts\\src\\test\\resources\\Data\\Profile_TestData.xlsx" , "ValidProfileData");
        Assert.assertEquals(this.page.getNameFieldText() ,reader.GetCellData(1,0) );
        Assert.assertEquals(this.page.getOccupationFieldText(),reader.GetCellData(1,2));
        Assert.assertEquals(this.page.getAnnualIncomeFieldText(),"50000.00");
        Assert.assertEquals(this.page.getAddressFieldText(),reader.GetCellData(1,3));
        Assert.assertEquals(this.page.getPhoneFieldText() ,reader.GetCellData(1,1));
    }

    @When("User enters invalid full name {string}")
    public void user_enters_invalid_full_name(String fullName) throws InterruptedException {
        ProfilePage page = new ProfilePage(this.driver);
        this.page = page;
        this.page = new ProfilePage(this.driver);
        this.page.ClickEditProfileButton();
        this.page.ClearNameField();
        this.page.SetNameField(fullName);
    }

    @And("User clicks on Save Profile button")
    public void user_clicks_on_save_profile_button() {
        this.page.ClickSaveProfileButton();
    }

    @Then("Proper validation message {string} should be displayed")
    public void proper_validation_message_should_be_displayed(String expectedMessage) {
        Assert.assertEquals(page.getToastMessage(), expectedMessage);
        page.ClickCancelProfileButton();
    }

    @When("User clicks Edit Profile button")
    public void user_clicks_edit_profile_button() {
        this.page.ClickEditProfileButton();
    }

    @When("User clicks Save Profile button")
    public void user_clicks_save_profile_button() {
        this.page.ClickSaveProfileButton();
    }

    @And("User clears Phone number field")
    public void user_clears_phone_number_field() {
        this.page.ClearPhoneField();
    }

    @And("User enters Phone number {string}")
    public void user_enters_phone_number(String phoneNumber) {
        this.page.setPhoneField(phoneNumber);
    }

    @Then("User should not be able to edit Email field")
    public void user_should_not_be_able_to_edit_email_field() {
        Boolean status = this.page.CheckEmailVisibility();
        Assert.assertFalse(status);
    }

    @Then("User should see toast message {string}")
    public void user_should_see_toast_message(String expectedMessage) {
        String actualMessage = this.page.getToastMessage();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @And("User clears Annual Income field")
    public void user_clears_annual_income_field() {
        this.page.ClearAnnualIncomeField();
    }

    @And("User enters Annual Income {string}")
    public void user_enters_annual_income(String annualIncome) {
        this.page.SetAnnualIncomeField(annualIncome);
    }

    @Then("User profile should be updated successfully")
    public void user_profile_should_be_updated_successfully() {
        Assert.assertEquals(this.page.getToastMessage() , "Profile updated successfully");
    }

    @Then("User should not be able to enter Annual Income {string}")
    public void user_should_not_be_able_to_enter_annual_income(String annualIncome) {
        this.page.SetAnnualIncomeField(annualIncome);
        Assert.assertEquals(this.page.getAnnualIncomeFieldText(), "");
    }

    String oldName, oldPhone, oldOccupation, oldIncome, oldAddress;
    String NewName, NewPhone, newOccupation, newIncome, newAddress;

    @And("User edits profile details")
    public void user_edits_profile_details() throws InterruptedException {
        this.oldName = this.page.getNameFieldText();
        this.oldPhone = this.page.getPhoneFieldText();
        this.oldOccupation = this.page.getOccupationFieldText();
        this.oldIncome = this.page.getAnnualIncomeFieldText();
        this.oldAddress = this.page.getAddressFieldText();
        this.NewName = this.oldName + " AAAAAA";
        long number = 1000000000L + (long)(Math.random() * 9000000000L);
        this.NewPhone = String.valueOf(number);
        this.newOccupation = oldOccupation + " aaa";
        this.newIncome = String.format("%.2f", Double.parseDouble(oldIncome) + 100);
        this.newAddress = oldAddress + "BBBBBBB";
        this.page.ClearNameField();
        this.page.SetNameField(NewName);
        this.page.ClearPhoneField();
        this.page.setPhoneField(NewPhone);
        this.page.ClearAnnualIncomeField();
        this.page.SetAnnualIncomeField(this.newIncome);
        this.page.ClearOccupationField();
        this.page.setOccupationField(newOccupation);
        this.page.ClearAddressField();
        this.page.SetAddressField(newAddress);
    }

    @And("User clicks Cancel Profile button")
    public void user_clicks_cancel_profile_button() {
        this.page.ClickCancelProfileButton();
    }

    @And("User logs out from application")
    public void user_logs_out_from_application() {
        CommonDashBoard logout = new CommonDashBoard(this.driver);
        logout.ClickLogout();
    }

    @And("User relogins with valid credentials")
    public void user_relogins_with_valid_credentials() {
        LoginPage logingPage = new LoginPage(this.driver);
        logingPage.clickFirstLogin();
        logingPage.enterEmail("abhirampb9@gmail.com");
        logingPage.enterPassword("Strong@123");
        logingPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        this.driver.get(this.config.getProp("profileUrl"));
    }

    @When("User changes browser screen size")
    public void user_changes_browser_screen_size() {
        this.driver.manage().window().setSize(new Dimension(375, 812));
    }

    @Then("Profile data should revert back to previous data")
    public void profile_data_should_revert_back_to_previous_data() {
        Assert.assertEquals(this.page.getNameFieldText() , this.oldName);
        Assert.assertEquals(this.page.getPhoneFieldText() , this.oldPhone);
        Assert.assertEquals(this.page.getOccupationFieldText() , this.oldOccupation);
        Assert.assertEquals(this.page.getAnnualIncomeFieldText(),this.oldIncome);
        Assert.assertEquals(this.page.getAddressFieldText(), this.oldAddress);
    }

    @Then("Updated profile data should be visible properly")
    public void updated_profile_data_should_be_visible_properly() {
       this.page.WaitTillProfileVisibility();
    }

    @Then("Saved profile data should persist after relogin")
    public void saved_profile_data_should_persist_after_relogin() {
            Assert.assertEquals(this.page.getAnnualIncomeFieldText(), this.newIncome);
            Assert.assertEquals(this.page.getNameFieldText(), this.NewName);
            Assert.assertEquals(this.page.getAddressFieldText() , this.newAddress);
            Assert.assertEquals(this.page.getPhoneFieldText() , this.NewPhone);
            Assert.assertEquals(this.page.getOccupationFieldText(), this.newOccupation);
    }
}
```

---

## API Execution Clients & Models

### File: `src/test/java/api/creditcard/Credit_API.java`
```java
package api.creditcard;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;
import utils.LoggerUtility;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class Credit_API {
    private static final Logger logger = LoggerUtility.getLogger(Credit_API.class);

    private final RequestSpecification requestSpecification;

    public Credit_API() {
        try {
            ConfigReader configReader = new ConfigReader();
            RestAssured.baseURI = configReader.getProp("APIUrl");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
        this.requestSpecification = RestAssured.given().header("Content-Type", "application/json");
    }

    private RequestSpecification addAuthHeader(String authToken) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .cookie("access_token", authToken);
    }

    private RequestSpecification addRequestBody(RequestSpecification request, Map<String, Object> payload) {
        return request.body(payload);
    }

    public Response applyCreditCard(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/credit-cards/apply");
    }

    public Response getCreditCardById(String authToken, String cardId) {
        return addAuthHeader(authToken).get("/credit-cards/" + cardId);
    }

    public Response getUserCreditCards(String authToken, String userId) {
        return addAuthHeader(authToken).get("/credit-cards/user/" + userId);
    }

    public Response purchaseTransaction(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/credit-cards/purchase");
    }

    public Response repayCreditCardBalance(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/credit-cards/payment");
    }

    public Response blockCreditCard(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/credit-cards/block");
    }

    public Response closeCreditCard(String authToken, String cardId, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).patch("/credit-cards/close/" + cardId);
    }

    public Response deleteCreditCard(String authToken, String cardId) {
        return addAuthHeader(authToken).delete("/credit-cards/" + cardId);
    }

    public Response getCardStatement(String authToken, String cardId) {
        return addAuthHeader(authToken).get("/credit-cards/statements/" + cardId);
    }

    public Response downloadStatementPdf(String authToken, String cardId) {
        return addAuthHeader(authToken).get("/credit-cards/statements/" + cardId + "/pdf");
    }

    public Response getRepaymentHistory(String authToken, String cardId) {
        return addAuthHeader(authToken).get("/credit-cards/repayments/" + cardId);
    }

    public Response getUserAccounts(String authToken) {
        return addAuthHeader(authToken).get("/accounts/user/me");
    }

    public Response getCurrentUserProfile(String authToken) {
        return addAuthHeader(authToken).get("/user/me");
    }

    public Response paymentCallback(Map<String, Object> payload) {
        return addRequestBody(requestSpecification, payload).post("/payment/callback");
    }

    public Response triggerBillingScheduler(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/billing/scheduler/trigger");
    }

    public Response triggerLatePenaltyScheduler(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/billing/penalty/trigger");
    }

    public Response triggerPaymentReconciliation(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/payment/reconciliation/trigger");
    }

    public Response triggerNotificationService(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/notification/trigger");
    }

    public Response triggerAuditLoggingService(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/audit/logs/trigger");
    }

    private String accessToken;
    private String loggedInUserId;

    public String login(String email, String password) {
        logger.info("Attempting login for user: {}", email);
        String body = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
        Response loginResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/auth/login");

        System.out.println("Login Response: " + loginResponse.getBody().asString());
        if (loginResponse.getStatusCode() == 200 || loginResponse.getStatusCode() == 201) {
            logger.info("Login successful for user: {}", email);
        } else {
            logger.error("Login failed for user: {}", email);
        }
        accessToken = loginResponse.getCookie("access_token");
        loggedInUserId = loginResponse.jsonPath().getString("user.user_id");
        return accessToken;
    }

    public Response createAccount(String authToken, Map<String, Object> payload) {
        Response response = addRequestBody(addAuthHeader(authToken), payload).post("/accounts");
        logger.info("Runtime account created");
        return response;
    }

    public Response withdrawMoney(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/transactions/withdraw");
    }

    public Response deleteAccount(String authToken, String accountId) {
        logger.info("Deleting runtime account");
        return addAuthHeader(authToken).delete("/accounts/" + accountId);
    }

    public Response createRuntimeCard(String authToken, Map<String, Object> payload) {
        Response response = addRequestBody(addAuthHeader(authToken), payload).post("/credit-cards/apply");
        logger.info("Runtime card created successfully");
        return response;
    }

    public Response repayCreditCard(String authToken, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).post("/credit-cards/payment");
    }

    public Response closeRuntimeCard(String authToken, String cardId, Map<String, Object> payload) {
        return addRequestBody(addAuthHeader(authToken), payload).patch("/credit-cards/close/" + cardId);
    }

    public Response deleteRuntimeCard(String authToken, String cardId) {
        logger.info("Deleting runtime card");
        return addAuthHeader(authToken).delete("/credit-cards/" + cardId);
    }

    public String getLoggedInUserId() {
        return loggedInUserId;
    }
}
```

### File: `src/test/java/api/creditcard/RuntimeCardManager.java`
```java
package api.creditcard;

import io.restassured.response.Response;
import utils.ScenarioContext;
import utils.LoggerUtility;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

public class RuntimeCardManager {
    private static final Logger logger = LoggerUtility.getLogger(RuntimeCardManager.class);

    private static final String CARDS_BASE = "/credit-cards";

    public static void applyCard(String cardTier, double requestedLimit, double existingLiabilities) {
        ScenarioContext ctx = ScenarioContext.get();

        String body = "{"
                + "\"source_account_id\":\"" + ctx.getRuntimeAccountId() + "\","
                + "\"requested_limit\":" + requestedLimit + ","
                + "\"card_tier\":\"" + cardTier + "\","
                + "\"existing_liabilities\":" + existingLiabilities
                + "}";

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .body(body)
                .post(CARDS_BASE + "/apply");

        if (response.statusCode() != 201) {
            throw new RuntimeException("Card apply failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        ctx.setRuntimeCardId(response.jsonPath().getString("data.card_id"));
        ctx.setRuntimeCardNumber(response.jsonPath().getString("data.card_number"));
        ctx.setRuntimeCreditLimit(response.jsonPath().getDouble("data.credit_limit"));
        ctx.setRuntimeAvailableLimit(response.jsonPath().getDouble("data.available_limit"));
        ctx.setRuntimeOutstandingBalance(0);

        logger.info("Runtime card created: " + ctx.getRuntimeCardId());
    }

    public static void makePurchase(double amount, String merchant, String category) {
        ScenarioContext ctx = ScenarioContext.get();

        String body = "{"
                + "\"card_id\":\"" + ctx.getRuntimeCardId() + "\","
                + "\"amount\":" + amount + ","
                + "\"merchant\":\"" + merchant + "\","
                + "\"category\":\"" + category + "\""
                + "}";

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .body(body)
                .post(CARDS_BASE + "/purchase");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Purchase failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        ctx.setRuntimeAvailableLimit(response.jsonPath().getDouble("data.remaining_limit"));
        ctx.setRuntimeOutstandingBalance(response.jsonPath().getDouble("data.outstanding_balance"));
    }

    public static void repayFull() {
        ScenarioContext ctx = ScenarioContext.get();
        double outstanding = ctx.getRuntimeOutstandingBalance();

        if (outstanding <= 0) {
            logger.info("No outstanding balance to repay.");
            return;
        }

        String body = "{"
                + "\"card_id\":\"" + ctx.getRuntimeCardId() + "\","
                + "\"amount\":" + outstanding
                + "}";

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .body(body)
                .post(CARDS_BASE + "/payment");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Repayment failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        ctx.setRuntimeOutstandingBalance(0);
        logger.info("Full repayment done for card: " + ctx.getRuntimeCardId());
    }

    public static void closeCard() {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .patch(CARDS_BASE + "/close/" + ctx.getRuntimeCardId());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Card close failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        logger.info("Card closed: " + ctx.getRuntimeCardId());
    }

    public static void deleteCard() {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .cookie("access_token", ctx.getAccessToken())
                .delete(CARDS_BASE + "/" + ctx.getRuntimeCardId());

        if (response.statusCode() != 200 && response.statusCode() != 204) {
            throw new RuntimeException("Card delete failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        logger.info("Card deleted: " + ctx.getRuntimeCardId());
    }
}
```

### File: `src/test/java/api/Account/RuntimeAccountManager.java`
```java
package api.Account;

import io.restassured.response.Response;
import utils.ScenarioContext;
import utils.LoggerUtility;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

public class RuntimeAccountManager {
    private static final Logger logger = LoggerUtility.getLogger(RuntimeAccountManager.class);

    private static final String ACCOUNTS_BASE = "/accounts";

    public static void createAccount() {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .body("{\"account_type\":\"savings\",\"initial_deposit\":100000}")
                .post(ACCOUNTS_BASE);

        if (response.statusCode() != 201) {
            throw new RuntimeException("Account creation failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        ctx.setRuntimeAccountId(response.jsonPath().getString("data.account_id"));
        ctx.setRuntimeAccountNumber(response.jsonPath().getString("data.account_number"));
        ctx.setRuntimeAccountBalance(response.jsonPath().getDouble("data.balance"));

        logger.info("Runtime account created: " + ctx.getRuntimeAccountId());
    }

    public static double fetchBalance() {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .cookie("access_token", ctx.getAccessToken())
                .get(ACCOUNTS_BASE + "/" + ctx.getRuntimeAccountId());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Fetch balance failed | status=" + response.statusCode());
        }

        double balance = response.jsonPath().getDouble("data.balance");
        ctx.setRuntimeAccountBalance(balance);
        return balance;
    }

    public static void withdrawBalance(double amount) {
        ScenarioContext ctx = ScenarioContext.get();

        String pin;
        try {
            pin = new utils.ConfigReader().getProp("transactionPin");
        } catch (Exception e) {
            throw new RuntimeException("transactionPin missing from config.properties", e);
        }

        Response response = given()
                .contentType("application/json")
                .cookie("access_token", ctx.getAccessToken())
                .body(String.format(
                        "{\"account_number\":\"%s\",\"amount\":%s,\"transaction_pin\":\"%s\"}",
                        ctx.getRuntimeAccountNumber(), amount, pin))
                .post("/transactions/withdraw");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Withdraw failed | amount=" + amount
                    + " | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        logger.info("Withdrawn " + amount + " from account " + ctx.getRuntimeAccountId());
    }

    public static void deleteAccount() {
        ScenarioContext ctx = ScenarioContext.get();

        Response response = given()
                .cookie("access_token", ctx.getAccessToken())
                .delete(ACCOUNTS_BASE + "/" + ctx.getRuntimeAccountId());

        if (response.statusCode() != 200 && response.statusCode() != 204) {
            throw new RuntimeException("Account deletion failed | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        logger.info("Runtime account deleted: " + ctx.getRuntimeAccountId());
    }
}
```

---

## Shared Automation Utilities & State Contexts

### File: `src/test/java/utils/ScenarioContext.java`
```java
package utils;

public class ScenarioContext {

    private static final ThreadLocal<ScenarioContext> instance = ThreadLocal.withInitial(ScenarioContext::new);

    // Auth
    private String accessToken;
    private String loggedInUserId;
    private String loggedInEmail;

    // Runtime Account
    private String runtimeAccountId;
    private String runtimeAccountNumber;
    private double runtimeAccountBalance;

    // Runtime Card
    private String runtimeCardId;
    private String runtimeCardNumber;
    private double runtimeCreditLimit;
    private double runtimeAvailableLimit;
    private double runtimeOutstandingBalance;

    // Execution metadata
    private String executionMode; // SEED | RUNTIME | SKIP
    private String currentScenarioId;

    public static ScenarioContext get() {
        return instance.get();
    }

    public static void reset() {
        instance.remove();
    }

    public static void resetEntitiesOnly() {
        ScenarioContext ctx = instance.get();
        ctx.runtimeAccountId      = null;
        ctx.runtimeAccountNumber  = null;
        ctx.runtimeAccountBalance = 0;
        ctx.runtimeCardId         = null;
        ctx.runtimeCardNumber     = null;
        ctx.runtimeCreditLimit    = 0;
        ctx.runtimeAvailableLimit = 0;
        ctx.runtimeOutstandingBalance = 0;
        ctx.executionMode         = null;
        ctx.currentScenarioId     = null;
    }

    public String getAccessToken()                     { return accessToken; }
    public void   setAccessToken(String v)             { this.accessToken = v; }

    public String getLoggedInUserId()                  { return loggedInUserId; }
    public void   setLoggedInUserId(String v)          { this.loggedInUserId = v; }

    public String getLoggedInEmail()                   { return loggedInEmail; }
    public void   setLoggedInEmail(String v)           { this.loggedInEmail = v; }

    public String getRuntimeAccountId()                { return runtimeAccountId; }
    public void   setRuntimeAccountId(String v)        { this.runtimeAccountId = v; }

    public String getRuntimeAccountNumber()            { return runtimeAccountNumber; }
    public void   setRuntimeAccountNumber(String v)    { this.runtimeAccountNumber = v; }

    public double getRuntimeAccountBalance()           { return runtimeAccountBalance; }
    public void   setRuntimeAccountBalance(double v)   { this.runtimeAccountBalance = v; }

    public String getRuntimeCardId()                   { return runtimeCardId; }
    public void   setRuntimeCardId(String v)           { this.runtimeCardId = v; }

    public String getRuntimeCardNumber()               { return runtimeCardNumber; }
    public void   setRuntimeCardNumber(String v)       { this.runtimeCardNumber = v; }

    public double getRuntimeCreditLimit()              { return runtimeCreditLimit; }
    public void   setRuntimeCreditLimit(double v)      { this.runtimeCreditLimit = v; }

    public double getRuntimeAvailableLimit()           { return runtimeAvailableLimit; }
    public void   setRuntimeAvailableLimit(double v)   { this.runtimeAvailableLimit = v; }

    public double getRuntimeOutstandingBalance()       { return runtimeOutstandingBalance; }
    public void   setRuntimeOutstandingBalance(double v){ this.runtimeOutstandingBalance = v; }

    public String getExecutionMode()                   { return executionMode; }
    public void   setExecutionMode(String v)           { this.executionMode = v; }

    public String getCurrentScenarioId()               { return currentScenarioId; }
    public void   setCurrentScenarioId(String v)       { this.currentScenarioId = v; }

    public boolean isRuntime() { return "RUNTIME".equalsIgnoreCase(executionMode); }
    public boolean isSeed()    { return "SEED".equalsIgnoreCase(executionMode); }
    public boolean isSkip()    { return "SKIP".equalsIgnoreCase(executionMode); }
    public boolean hasCard()   { return runtimeCardId != null && !runtimeCardId.isEmpty(); }
    public boolean hasAccount(){ return runtimeAccountId != null && !runtimeAccountId.isEmpty(); }
}
```

### File: `src/test/java/utils/TokenManager.java`
```java
package utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.apache.logging.log4j.Logger;

public class TokenManager {
    private static final Logger logger = LoggerUtility.getLogger(TokenManager.class);

    private static final ThreadLocal<String>  cachedToken     = new ThreadLocal<>();
    private static final ThreadLocal<Long>    tokenTimestamp  = new ThreadLocal<>();
    private static final long                 TOKEN_TTL_MS    = 55 * 60 * 1000; // 55 min

    private TokenManager() {}

    public static String getToken(String email, String password) {
        if (isTokenValid()) {
            logger.info("Using cached token");
            return cachedToken.get();
        }
        return login(email, password);
    }

    public static void login() {
        getToken("yilap59703@deapad.com", "Kuttichatan@123");
    }

    public static String getToken() {
        ScenarioContext ctx = ScenarioContext.get();
        if (ctx.getAccessToken() != null && isTokenValid()) {
            return ctx.getAccessToken();
        }
        throw new IllegalStateException("No token in context. Call getToken(email, password) first.");
    }

    public static void invalidate() {
        cachedToken.remove();
        tokenTimestamp.remove();
    }

    private static boolean isTokenValid() {
        Long ts = tokenTimestamp.get();
        return cachedToken.get() != null
                && ts != null
                && (System.currentTimeMillis() - ts) < TOKEN_TTL_MS;
    }

    private static String login(String email, String password) {
        try {
            utils.ConfigReader config = new utils.ConfigReader();
            io.restassured.RestAssured.baseURI = config.getProp("APIUrl");
        } catch (java.io.IOException e) {
            logger.error("Failed to load base URI from config", e);
        }

        Response response = given()
                .contentType("application/json")
                .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                .post("/auth/login");

        if (response.statusCode() != 200) {
            throw new RuntimeException("Login failed for " + email
                    + " | status=" + response.statusCode()
                    + " | body=" + response.getBody().asString());
        }

        String token = response.getCookie("access_token");
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Login succeeded but no access_token cookie received for " + email);
        }
        String userId = response.jsonPath().getString("user.user_id");

        cachedToken.set(token);
        tokenTimestamp.set(System.currentTimeMillis());

        ScenarioContext ctx = ScenarioContext.get();
        ctx.setAccessToken(token);
        ctx.setLoggedInUserId(userId);
        ctx.setLoggedInEmail(email);

        return token;
    }
}
```

### File: `src/test/java/utils/CleanupManager.java`
```java
package utils;

import api.Account.RuntimeAccountManager;
import api.creditcard.RuntimeCardManager;
import org.apache.logging.log4j.Logger;

public class CleanupManager {
    private static final Logger logger = LoggerUtility.getLogger(CleanupManager.class);

    public static void cleanup() {
        ScenarioContext ctx = ScenarioContext.get();

        if (!ctx.isRuntime()) {
            return;
        }

        try {
            if (ctx.hasCard()) {
                cleanupCard();
            }
            if (ctx.hasAccount()) {
                cleanupAccount();
            }
        } catch (Exception e) {
            logger.error("Cleanup failed for scenario: " + ctx.getCurrentScenarioId() + " | " + e.getMessage());
        } finally {
            ScenarioContext.resetEntitiesOnly();
        }
    }

    private static void cleanupCard() {
        try { RuntimeCardManager.repayFull();  } catch (Exception e) { logger.error("Repay failed: "  + e.getMessage()); }
        try { RuntimeCardManager.closeCard();  } catch (Exception e) { logger.error("Close failed: "  + e.getMessage()); }
        try { RuntimeCardManager.deleteCard(); } catch (Exception e) { logger.error("Delete card failed: " + e.getMessage()); }
    }

    private static void cleanupAccount() {
        try {
            double balance = RuntimeAccountManager.fetchBalance();
            if (balance > 0) {
                RuntimeAccountManager.withdrawBalance(balance);
            }
        } catch (Exception e) {
            logger.error("Withdraw failed: " + e.getMessage());
        }
        try { RuntimeAccountManager.deleteAccount(); } catch (Exception e) { logger.error("Delete account failed: " + e.getMessage()); }
    }
}
```

### File: `src/test/java/utils/ExcelReader.java`
```java
package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;

public class ExcelReader {
    private static final Logger logger = LoggerUtility.getLogger(ExcelReader.class);

    private Workbook workbook;
    private Sheet sheet;

    public ExcelReader(String filepath, String sheetName) throws IOException {
        FileInputStream fs = new FileInputStream(filepath);
        this.workbook = new XSSFWorkbook(fs);
        this.sheet = workbook.getSheet(sheetName);
        logger.info("Loaded Excel file: {} | Sheet: {}", filepath, sheetName);
    }

    public String GetCellData(int row, int column) {
        Cell cell = this.sheet.getRow(row).getCell(column);
        return cell.toString();
    }

    public int GetNumberOfRows() {
        return sheet.getPhysicalNumberOfRows();
    }

    public Map<String, String> getData(String testcaseId) {
        logger.info("Fetching Excel data for testcaseId: {}", testcaseId);
        Map<String, String> dataMap = new HashMap<>();

        Row headerRow = sheet.getRow(0);

        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);

            if (currentRow == null) {
                continue;
            }

            Cell testcaseCell = currentRow.getCell(2);

            if (testcaseCell == null) {
                continue;
            }

            String currentTestcaseId = testcaseCell.toString().trim();

            if (currentTestcaseId.equalsIgnoreCase(testcaseId.trim())) {
                for (int columnIndex = 0; columnIndex < headerRow.getPhysicalNumberOfCells(); columnIndex++) {
                    Cell headerCell = headerRow.getCell(columnIndex);

                    if (headerCell == null) {
                        continue;
                    }

                    String columnName = headerCell.toString().trim();
                    Cell valueCell = currentRow.getCell(columnIndex);
                    String cellValue = "";

                    if (valueCell != null) {
                        cellValue = valueCell.toString().trim();
                    }

                    dataMap.put(columnName, cellValue);
                }
                return dataMap;
            }
        }
        logger.warn("TestcaseId {} not found in the Excel sheet", testcaseId);
        return dataMap;
    }
}
```
