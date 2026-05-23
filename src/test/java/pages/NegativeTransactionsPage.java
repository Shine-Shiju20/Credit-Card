//package pages;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class NegativeTransactionsPage extends TransactionsPage {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    /**
//     * Error toast locator.
//     *
//     * Frontend uses showToast("error", "message"),
//     * which typically renders a toast element with classes like:
//     * - toast
//     * - Toastify__toast
//     * - alert
//     * - notification
//     */
//    private By errorMessage =
//            By.xpath(
//                "//*[contains(@class,'toast') " +
//                "or contains(@class,'Toastify__toast') " +
//                "or contains(@class,'alert') " +
//                "or contains(@class,'notification')]"
//            );
//
//    /**
//     * Login page locator.
//     */
//    private By loginEmailField =
//            By.cssSelector("input[type='email']");
//
//    public NegativeTransactionsPage(WebDriver driver) {
//        super(driver);
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    /**
//     * Verify error toast is displayed.
//     */
//    public boolean isErrorMessageDisplayed() {
//        try {
//            WebElement toast = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            errorMessage
//                    )
//            );
//
//            String message = toast.getText().trim();
//            System.out.println("Error Toast Message: " + message);
//
//            return !message.isEmpty();
//
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    /**
//     * Expire session by clearing cookies and refreshing.
//     */
//    public void expireSession() {
//        driver.manage().deleteAllCookies();
//        driver.navigate().refresh();
//    }
//
//    /**
//     * Verify login page is displayed after session expiration.
//     */
//    public boolean isLoginPageDisplayed() {
//        try {
//            WebElement emailField = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            loginEmailField
//                    )
//            );
//
//            return emailField.isDisplayed();
//
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}

package pages;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Log;

public class NegativeTransactionsPage extends TransactionsHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    // Logger
    private static final Logger logger =
            Log.getLogger(NegativeTransactionsPage.class);

    /**
     * Error toast locator.
     *
     * Frontend uses showToast("error", "message"),
     * which typically renders a toast element with classes like:
     * - toast
     * - Toastify__toast
     * - alert
     * - notification
     */
    private By errorMessage =
            By.xpath(
                "//*[contains(@class,'toast') " +
                "or contains(@class,'Toastify__toast') " +
                "or contains(@class,'alert') " +
                "or contains(@class,'notification')]"
            );

    /**
     * Login page locator.
     */
    private By loginEmailField =
            By.cssSelector("input[type='email']");

    public NegativeTransactionsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("NegativeTransactionsPage initialized.");
    }

    /**
     * Verify error toast is displayed.
     */
    public boolean isErrorMessageDisplayed() {
        try {
            logger.info("Waiting for error toast message.");

            WebElement toast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            errorMessage
                    )
            );

            String message = toast.getText().trim();
            logger.info("Error Toast Message: {}", message);

            return !message.isEmpty();

        } catch (Exception e) {
            logger.error("Error message was not displayed.", e);
            return false;
        }
    }

    /**
     * Expire session by clearing cookies and browser storage,
     * then navigating to the login page.
     */
    public void expireSession() {
        logger.info("Expiring session by clearing cookies and storage.");

        // Delete cookies
        driver.manage().deleteAllCookies();

        // Clear localStorage and sessionStorage
        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.clear();" +
                "window.sessionStorage.clear();"
        );

        // Navigate to login page
        driver.navigate().to("http://localhost:3000/");

        logger.info("Session expired and redirected to login page.");
    }

    /**
     * Verify login page is displayed after session expiration.
     */
    public boolean isLoginPageDisplayed() {
        try {
            logger.info("Verifying login page is displayed.");

            // Wait until URL is the root login page
            wait.until(
                    ExpectedConditions.urlToBe(
                            "http://localhost:3000/"
                    )
            );

            // Verify email field is visible
            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            loginEmailField
                    )
            );

            boolean displayed = emailField.isDisplayed();

            logger.info("Login page displayed successfully.");
            return displayed;

        } catch (Exception e) {
            logger.error(
                    "Login page not displayed. Current URL: {}",
                    driver.getCurrentUrl()
            );
            logger.error("Exception: ", e);
            return false;
        }
    }
}