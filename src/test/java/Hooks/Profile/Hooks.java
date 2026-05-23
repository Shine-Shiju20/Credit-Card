package Hooks.Profile;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.ProfilePage;
import pages.common.CommonDashBoard;
import utils.DriverFactory;
import utils.ScreenShotUtil;

import java.io.IOException;
import java.time.Duration;

public class Hooks {

    public static DriverFactory factory;
    static String oldName;
    static String oldPhone;
    static String oldOccupation;
    static String oldIncome;
    static String oldAddress;

    static String oldPAN;
    static String oldAadhaar;

    //records the Data fields at the start of the Test so that it can be restored Later
    @BeforeAll
    public static void beforeAllTests() throws InterruptedException {

        factory = new DriverFactory();

        WebDriver driver = factory.getDriver();

        driver.manage().window().maximize();

        System.out.println("Capturing Original Data");


        // ==================================================
        // VERIFIED ACCOUNT PROFILE DATA
        // ==================================================

        LoginPage loginPage =
                new LoginPage(driver);

        driver.get("http://localhost:3000");

        loginPage.clickFirstLogin();

        loginPage.enterEmail("abhirampb9@gmail.com");

        loginPage.enterPassword("Strong@123");

        loginPage.clickLogin();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.urlContains("dashboard")
        );

        driver.get("http://localhost:3000/profile");

        ProfilePage page =
                new ProfilePage(driver);


        // ---------------- STORE PROFILE DATA ----------------

        oldName = page.getNameFieldText();

        oldPhone = page.getPhoneFieldText();

        oldOccupation = page.getOccupationFieldText();

        oldIncome = page.getAnnualIncomeFieldText();

        oldAddress = page.getAddressFieldText();


        // ---------------- LOGOUT ----------------

        CommonDashBoard common =
                new CommonDashBoard(driver);

        common.ClickLogout();


        // ==================================================
        // UNVERIFIED ACCOUNT KYC DATA
        // ==================================================

        loginPage.clickFirstLogin();

        loginPage.enterEmail("prajwalp.123p@gmail.com");

        loginPage.enterPassword("Strong@123");

        loginPage.clickLogin();

        wait.until(
                ExpectedConditions.urlContains("dashboard")
        );

        driver.get("http://localhost:3000/profile");


        // ---------------- STORE KYC DATA ----------------

        oldPAN = page.getPanFieldText();

        oldAadhaar = page.getAadhaarFieldText();

        factory.CloseDriver();

        System.out.println("Original Data Captured");
    }

    //Creates an Driver (browser instance) and Maximize the Window
    @Before
    public void setup() {

        factory = new DriverFactory();

        factory.getDriver().manage().window().maximize();

        System.out.println("Browser Started");
    }

    //after each test case it closes the Driver and Takes Screenshot if it fails the test
    @After
    public void tearDown(
            io.cucumber.java.Scenario scenario
    ) throws IOException {

        WebDriver driver =
                factory.getDriver();

        try {

            ScreenShotUtil screenShotUtil =
                    new ScreenShotUtil();

            String screenshot =
                    screenShotUtil.TakeScreenShotForExtent(
                            driver,
                            scenario.getName()
                    );

            ExtentCucumberAdapter
                    .addTestStepScreenCaptureFromPath(
                            screenshot
                    );

        }
        catch (Exception e) {

            e.printStackTrace();
        }

        // Existing failed screenshot save
        if (scenario.isFailed()) {

            System.out.println(
                    "Scenario Failed: "
                            + scenario.getName()
            );

            ScreenShotUtil screenShotUtil =
                    new ScreenShotUtil();

            screenShotUtil.TakeScreenShot(
                    driver,
                    scenario.getName()
            );
        }

        try {

            Thread.sleep(3000);

        }
        catch (Exception e) {

            e.printStackTrace();
        }

        factory.CloseDriver();

        System.out.println(
                "Browser Closed"
        );
    }

    //Restores the data back to What ever it was At the start of Test
    @AfterAll
    public static void afterAllTests() throws InterruptedException {

        factory = new DriverFactory();

        WebDriver driver = factory.getDriver();

        driver.manage().window().maximize();

        System.out.println("Restoring Original Data");

        LoginPage loginPage =
                new LoginPage(driver);

        driver.get("http://localhost:3000");

        loginPage.clickFirstLogin();

        loginPage.enterEmail("abhirampb9@gmail.com");

        loginPage.enterPassword("Strong@123");

        Thread.sleep(10000);

        loginPage.clickLogin();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.urlContains("dashboard")
        );

        driver.get("http://localhost:3000/profile");

        ProfilePage page =
                new ProfilePage(driver);

        page.ClickEditProfileButton();

        page.ClearNameField();

        page.SetNameField(oldName);

        page.ClearPhoneField();

        page.setPhoneField(oldPhone);

        page.ClearOccupationField();

        page.setOccupationField(oldOccupation);

        page.ClearAnnualIncomeField();

        page.SetAnnualIncomeField(oldIncome);

        page.ClearAddressField();

        page.SetAddressField(oldAddress);

        page.ClickSaveProfileButton();


        // ---------------- LOGOUT ----------------

        CommonDashBoard common =
                new CommonDashBoard(driver);

        common.ClickLogout();


        loginPage.clickFirstLogin();

        loginPage.enterEmail("prajwalp.123p@gmail.com");

        loginPage.enterPassword("Strong@123");

        loginPage.clickLogin();

        wait.until(
                ExpectedConditions.urlContains("dashboard")
        );

        driver.get("http://localhost:3000/profile");

        page.ClickEditKYCButton();

        page.ClearPanField();

        page.SetPanField(oldPAN);

        page.ClearAadhaarField();

        page.SetAadhaarField(oldAadhaar);

        page.ClickSaveKYCButton();

        factory.CloseDriver();

        System.out.println("Original Data Restored");
    }
}