//package pages;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class LoginPage {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    // Login modal fields
//    private By emailField = By.cssSelector("input[type='email']");
//    private By passwordField = By.cssSelector("input[type='password']");
//    private By loginButton = By.cssSelector("button.login-button");
//
//    // Landing page button that opens the login modal
//    private By openLoginModalButton = By.xpath(
//            "//button[contains(.,'Login to Account') "
//            + "or contains(.,'Login') "
//            + "or contains(.,'Sign In')]"
//    );
//
//    // Dashboard header
//    private By dashboardHeader = By.xpath(
//            "//h1[contains(normalize-space(),'Welcome back')]"
//    );
//
//    public LoginPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    }
//
//    /**
//     * Opens the application and launches the login modal.
//     */
//    public void openApplication() {
//        // Open application
//        driver.get("http://localhost:3000/");
//
//        // Safely maximize window
//        try {
//            driver.manage().window().maximize();
//        } catch (Exception e) {
//            System.out.println(
//                    "Window maximize skipped: " + e.getMessage()
//            );
//        }
//
//        // Wait until page is fully loaded
//        wait.until(webDriver ->
//                ((JavascriptExecutor) webDriver)
//                        .executeScript("return document.readyState")
//                        .equals("complete")
//        );
//
//        // Wait for landing page login button
//        WebElement loginTrigger = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        openLoginModalButton
//                )
//        );
//
//        // Scroll into view
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView(true);",
//                loginTrigger
//        );
//
//        // Click using JavaScript
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();",
//                loginTrigger
//        );
//
//        // Wait until email field appears in modal
//        wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        emailField
//                )
//        );
//    }
//
//    /**
//     * Enters the email address.
//     */
//    public void enterEmail(String email) {
//        WebElement emailInput = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        emailField
//                )
//        );
//        emailInput.clear();
//        emailInput.sendKeys(email);
//    }
//
//    /**
//     * Enters the password.
//     */
//    public void enterPassword(String password) {
//        WebElement passwordInput = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        passwordField
//                )
//        );
//        passwordInput.clear();
//        passwordInput.sendKeys(password);
//    }
//
//    /**
//     * Clicks the Secure Login button.
//     */
//    public void clickLogin() {
//        WebElement loginBtn = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        loginButton
//                )
//        );
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();",
//                loginBtn
//        );
//    }
//
//    /**
//     * Performs complete login.
//     */
//    public void login(String email, String password) {
//        enterEmail(email);
//        enterPassword(password);
//        clickLogin();
//    }
//
//    /**
//     * Verifies that the dashboard is displayed.
//     */
//    public boolean isDashboardDisplayed() {
//        try {
//            // Wait until URL contains dashboard
//            wait.until(
//                    ExpectedConditions.urlContains("/dashboard")
//            );
//
//            // Verify "Welcome back, <Name>" heading
//            return wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            dashboardHeader
//                    )
//            ).isDisplayed();
//
//        } catch (Exception e) {
//            System.out.println(
//                    "Dashboard not displayed. Current URL: "
//                            + driver.getCurrentUrl()
//            );
//            e.printStackTrace();
//            return false;
//        }
//    }
//}

package pages.Transaction;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Log;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Logger
    private static final Logger logger =
            Log.getLogger(LoginPage.class);

    // Login modal fields
    private By emailField = By.cssSelector("input[type='email']");
    private By passwordField = By.cssSelector("input[type='password']");
    private By loginButton = By.cssSelector("button.login-button");

    // Landing page button that opens the login modal
    private By openLoginModalButton = By.xpath(
            "//button[contains(.,'Login to Account') "
            + "or contains(.,'Login') "
            + "or contains(.,'Sign In')]"
    );

    // Dashboard header
    private By dashboardHeader = By.xpath(
            "//h1[contains(normalize-space(),'Welcome back')]"
    );

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logger.info("LoginPage initialized.");
    }

    /**
     * Opens the application and launches the login modal.
     */
    public void openApplication() {
        logger.info("Opening application: http://localhost:3000/");

        // Open application
        driver.get("http://localhost:3000/");

        // Safely maximize window
        try {
            driver.manage().window().maximize();
            logger.info("Browser window maximized.");
        } catch (Exception e) {
            logger.warn("Window maximize skipped: {}", e.getMessage());
        }

        // Wait until page is fully loaded
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
        logger.info("Landing page fully loaded.");

        // Wait for landing page login button
        WebElement loginTrigger = wait.until(
                ExpectedConditions.elementToBeClickable(
                        openLoginModalButton
                )
        );
        logger.info("Login button is clickable.");

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                loginTrigger
        );

        // Click using JavaScript
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                loginTrigger
        );
        logger.info("Clicked login button to open modal.");

        // Wait until email field appears in modal
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        emailField
                )
        );
        logger.info("Login modal displayed successfully.");
    }

    /**
     * Enters the email address.
     */
    public void enterEmail(String email) {
        logger.info("Entering email: {}", email);

        WebElement emailInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        emailField
                )
        );
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    /**
     * Enters the password.
     */
    public void enterPassword(String password) {
        logger.info("Entering password.");

        WebElement passwordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        passwordField
                )
        );
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /**
     * Clicks the Secure Login button.
     */
    public void clickLogin() {
        logger.info("Clicking Secure Login button.");

        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                loginBtn
        );
    }

    /**
     * Performs complete login.
     */
    public void login(String email, String password) {
        logger.info("Starting login process.");

        enterEmail(email);
        enterPassword(password);
        clickLogin();

        logger.info("Login form submitted.");
    }

    /**
     * Verifies that the dashboard is displayed.
     */
    public boolean isDashboardDisplayed() {
        try {
            logger.info("Verifying dashboard page.");

            // Wait until URL contains dashboard
            wait.until(
                    ExpectedConditions.urlContains("/dashboard")
            );

            // Verify "Welcome back, <Name>" heading
            boolean displayed = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            dashboardHeader
                    )
            ).isDisplayed();

            logger.info("Dashboard displayed successfully.");
            return displayed;

        } catch (Exception e) {
            logger.error(
                    "Dashboard not displayed. Current URL: {}",
                    driver.getCurrentUrl()
            );
            logger.error("Exception: ", e);
            return false;
        }
    }
}