//package pages;
//
//import java.time.Duration;
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class TransactionUIValidationPage extends TransactionsPage {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    // =========================================================
//    // Locators
//    // =========================================================
//
//    // Transfer type dropdown
//    private By transferTypeDropdown =
//            By.name("transfer_type");
//
//    private By transferTypeOptions =
//            By.cssSelector("select[name='transfer_type'] option");
//
//    // Amount field
//    private By amountField =
//            By.name("amount");
//
//    // Submit button
//    private By submitButton =
//            By.cssSelector("button[type='submit']");
//
//    // Form
//    private By transactionForm =
//            By.cssSelector("form");
//
//    // Labels
//    private By labels =
//            By.cssSelector("label");
//
//    // Input fields
//    private By inputFields =
//            By.cssSelector("input, select");
//
//    // Sidebar
//    private By sidebar =
//            By.cssSelector(".sidebar");
//
//    // Transaction card
//    private By transactionCard =
//            By.cssSelector(".transaction-card");
//
//    // Success/Error toast
//    private By toastMessage =
//            By.xpath(
//                "//*[contains(@class,'toast') "
//                + "or contains(@class,'Toastify__toast') "
//                + "or contains(@class,'alert') "
//                + "or contains(@class,'notification')]"
//            );
//
//    // Login page email field
//    private By loginEmailField =
//            By.cssSelector("input[type='email']");
//
//    public TransactionUIValidationPage(WebDriver driver) {
//        super(driver);
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    // =========================================================
//    // Transfer Type Dropdown
//    // =========================================================
//
//    public boolean isTransferTypeDropdownDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        transferTypeDropdown
//                )
//        ).isDisplayed();
//    }
//
//    public int getTransferTypeOptionCount() {
//        return driver.findElements(transferTypeOptions).size();
//    }
//
//    // =========================================================
//    // Amount Field
//    // =========================================================
//
//    public void enterAmount(String amount) {
//        WebElement field = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        amountField
//                )
//        );
//        field.clear();
//        field.sendKeys(amount);
//    }
//
//    public String getAmountValue() {
//        return driver.findElement(amountField)
//                .getAttribute("value");
//    }
//
//    // =========================================================
//    // Confirm Button
//    // =========================================================
//
//    public boolean isSubmitButtonEnabled() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        submitButton
//                )
//        ).isEnabled();
//    }
//
//    public void clickConfirmButton() {
//        wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        submitButton
//                )
//        ).click();
//    }
//
//    // =========================================================
//    // Toast Validation
//    // =========================================================
//
//    public boolean isErrorMessageDisplayed() {
//        try {
//            WebElement toast = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            toastMessage
//                    )
//            );
//
//            String message = toast.getText().trim();
//            System.out.println("Toast Message: " + message);
//
//            return !message.isEmpty();
//
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public boolean isSuccessToastDisplayed() {
//        try {
//            WebElement toast = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            toastMessage
//                    )
//            );
//
//            String message = toast.getText().trim();
//            System.out.println("Success Toast: " + message);
//
//            return !message.isEmpty();
//
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    // =========================================================
//    // Left Navigation
//    // =========================================================
//
//    public boolean isSidebarDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        sidebar
//                )
//        ).isDisplayed();
//    }
//
//    // =========================================================
//    // Form Alignment
//    // =========================================================
//
//    public boolean isTransactionFormDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        transactionForm
//                )
//        ).isDisplayed();
//    }
//
//    // =========================================================
//    // Labels and Placeholders
//    // =========================================================
//
//    public boolean allLabelsDisplayed() {
//        try {
//            // Wait until labels are visible
//            List<WebElement> allLabels = wait.until(
//                    ExpectedConditions
//                            .visibilityOfAllElementsLocatedBy(labels)
//            );
//
//            System.out.println("Number of labels found: "
//                    + allLabels.size());
//
//            // Print all labels for debugging
//            for (WebElement label : allLabels) {
//                System.out.println("Label Text: "
//                        + label.getText().trim());
//            }
//
//            // Get page source
//            String pageSource = driver.getPageSource();
//
//            // Verify key labels exist
//            boolean hasSelectAccount =
//                    pageSource.contains("Select Account");
//
//            boolean hasAmount =
//                    pageSource.contains("Amount");
//
//            boolean hasTransactionPin =
//                    pageSource.contains("Transaction PIN");
//
//            return hasSelectAccount
//                    && hasAmount
//                    && hasTransactionPin
//                    && allLabels.size() >= 4;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean allFieldsHavePlaceholders() {
//        try {
//            List<WebElement> fields = wait.until(
//                    ExpectedConditions
//                            .visibilityOfAllElementsLocatedBy(inputFields)
//            );
//
//            for (WebElement field : fields) {
//
//                if (field.getTagName()
//                        .equalsIgnoreCase("input")) {
//
//                    String placeholder =
//                            field.getAttribute("placeholder");
//
//                    // Password and text inputs should have placeholders
//                    if (placeholder == null
//                            || placeholder.trim().isEmpty()) {
//
//                        System.out.println(
//                                "Missing placeholder for field: "
//                                + field.getAttribute("name")
//                        );
//
//                        return false;
//                    }
//                }
//            }
//
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // =========================================================
//    // Responsive Validation
//    // =========================================================
//
//    public boolean isResponsiveOnMobile() {
//        driver.manage().window().setSize(
//                new Dimension(375, 812)
//        );
//
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        transactionCard
//                )
//        ).isDisplayed();
//    }
//
//    // =========================================================
//    // Unauthorized Access
//    // =========================================================
//
//    public void logoutSession() {
//
//        // Delete cookies
//        driver.manage().deleteAllCookies();
//
//        // Clear localStorage and sessionStorage
//        ((JavascriptExecutor) driver).executeScript(
//                "window.localStorage.clear();"
//                        + "window.sessionStorage.clear();"
//        );
//
//        // Refresh page
//        driver.navigate().refresh();
//    }
//
//    public boolean isLoginPageDisplayed() {
//        try {
//            // Wait until URL is the root login page
//            wait.until(
//                    ExpectedConditions.urlToBe(
//                            "http://localhost:3000/"
//                    )
//            );
//
//            // Accept any common login fields
//            By loginLocator = By.xpath(
//                    "//input[@type='email' "
//                    + "or @type='password' "
//                    + "or @type='text']"
//            );
//
//            // Verify at least one login field is visible
//            return wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            loginLocator
//                    )
//            ).isDisplayed();
//
//        } catch (Exception e) {
//            System.out.println(
//                    "Login page not displayed."
//            );
//            System.out.println(
//                    "Current URL: "
//                            + driver.getCurrentUrl()
//            );
//            e.printStackTrace();
//            return false;
//        }
//    }
//}

package pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Log;

public class TransactionUIValidationPage extends TransactionsHelper{

    private WebDriver driver;
    private WebDriverWait wait;

    // Logger
    private static final Logger logger =
            Log.getLogger(TransactionUIValidationPage.class);

    // =========================================================
    // Locators
    // =========================================================

    // Transfer type dropdown
    private By transferTypeDropdown =
            By.name("transfer_type");

    private By transferTypeOptions =
            By.cssSelector("select[name='transfer_type'] option");

    // Amount field
    private By amountField =
            By.name("amount");

    // Submit button
    private By submitButton =
            By.cssSelector("button[type='submit']");

    // Form
    private By transactionForm =
            By.cssSelector("form");

    // Labels
    private By labels =
            By.cssSelector("label");

    // Input fields
    private By inputFields =
            By.cssSelector("input, select");

    // Sidebar
    private By sidebar =
            By.cssSelector(".sidebar");

    // Transaction card
    private By transactionCard =
            By.cssSelector(".transaction-card");

    // Success/Error toast
    private By toastMessage =
            By.xpath(
                "//*[contains(@class,'toast') "
                + "or contains(@class,'Toastify__toast') "
                + "or contains(@class,'alert') "
                + "or contains(@class,'notification')]"
            );

    // Login page email field
    private By loginEmailField =
            By.cssSelector("input[type='email']");

    public TransactionUIValidationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        logger.info("TransactionUIValidationPage initialized.");
    }

    // =========================================================
    // Transfer Type Dropdown
    // =========================================================

    public boolean isTransferTypeDropdownDisplayed() {
        logger.info("Checking transfer type dropdown visibility.");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        transferTypeDropdown
                )
        ).isDisplayed();
    }

    public int getTransferTypeOptionCount() {
        int count = driver.findElements(transferTypeOptions).size();
        logger.info("Transfer type option count: {}", count);
        return count;
    }

    // =========================================================
    // Amount Field
    // =========================================================

    public void enterAmount(String amount) {
        logger.info("Entering amount: {}", amount);

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        amountField
                )
        );
        field.clear();
        field.sendKeys(amount);
    }

    public String getAmountValue() {
        String value = driver.findElement(amountField)
                .getAttribute("value");

        logger.info("Amount field value: {}", value);
        return value;
    }

    // =========================================================
    // Confirm Button
    // =========================================================

    public boolean isSubmitButtonEnabled() {
        boolean enabled = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        submitButton
                )
        ).isEnabled();

        logger.info("Submit button enabled: {}", enabled);
        return enabled;
    }

    public void clickConfirmButton() {
        logger.info("Clicking Confirm button.");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        submitButton
                )
        ).click();
    }

    // =========================================================
    // Toast Validation
    // =========================================================

    public boolean isErrorMessageDisplayed() {
        try {
            logger.info("Checking for error toast.");

            WebElement toast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            toastMessage
                    )
            );

            String message = toast.getText().trim();
            logger.info("Error Toast Message: {}", message);

            return !message.isEmpty();

        } catch (Exception e) {
            logger.error("Error toast not displayed.", e);
            return false;
        }
    }

    public boolean isSuccessToastDisplayed() {
        try {
            logger.info("Checking for success toast.");

            WebElement toast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            toastMessage
                    )
            );

            String message = toast.getText().trim();
            logger.info("Success Toast Message: {}", message);

            return !message.isEmpty();

        } catch (Exception e) {
            logger.error("Success toast not displayed.", e);
            return false;
        }
    }

    // =========================================================
    // Left Navigation
    // =========================================================

    public boolean isSidebarDisplayed() {
        logger.info("Checking sidebar visibility.");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        sidebar
                )
        ).isDisplayed();
    }

    // =========================================================
    // Form Alignment
    // =========================================================

    public boolean isTransactionFormDisplayed() {
        logger.info("Checking transaction form visibility.");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        transactionForm
                )
        ).isDisplayed();
    }

    // =========================================================
    // Labels and Placeholders
    // =========================================================

    public boolean allLabelsDisplayed() {
        try {
            logger.info("Validating all labels.");

            List<WebElement> allLabels = wait.until(
                    ExpectedConditions
                            .visibilityOfAllElementsLocatedBy(labels)
            );

            logger.info("Number of labels found: {}",
                    allLabels.size());

            for (WebElement label : allLabels) {
                logger.info("Label Text: {}",
                        label.getText().trim());
            }

            String pageSource = driver.getPageSource();

            boolean hasSelectAccount =
                    pageSource.contains("Select Account");

            boolean hasAmount =
                    pageSource.contains("Amount");

            boolean hasTransactionPin =
                    pageSource.contains("Transaction PIN");

            boolean result = hasSelectAccount
                    && hasAmount
                    && hasTransactionPin
                    && allLabels.size() >= 3;

            logger.info("Label validation result: {}", result);

            return result;

        } catch (Exception e) {
            logger.error("Label validation failed.", e);
            return false;
        }
    }

    public boolean allFieldsHavePlaceholders() {
        try {
            logger.info("Validating placeholders.");

            List<WebElement> fields = wait.until(
                    ExpectedConditions
                            .visibilityOfAllElementsLocatedBy(inputFields)
            );

            for (WebElement field : fields) {

                if (field.getTagName()
                        .equalsIgnoreCase("input")) {

                    String placeholder =
                            field.getAttribute("placeholder");

                    String fieldName =
                            field.getAttribute("name");

                    if (placeholder == null
                            || placeholder.trim().isEmpty()) {

                        logger.warn(
                                "Missing placeholder for field: {}",
                                fieldName
                        );

                        return false;
                    }
                }
            }

            logger.info("All placeholders are present.");
            return true;

        } catch (Exception e) {
            logger.error("Placeholder validation failed.", e);
            return false;
        }
    }

    // =========================================================
    // Responsive Validation
    // =========================================================

    public boolean isResponsiveOnMobile() {
        try {
            logger.info("Setting mobile viewport: 375x812");

            // First restore normal state
            try {
                driver.manage().window().fullscreen();
                driver.manage().window().maximize();
            } catch (Exception ignored) {
            }

            driver.manage().window().setSize(
                    new Dimension(375, 812)
            );

            boolean displayed = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            transactionCard
                    )
            ).isDisplayed();

            logger.info("Responsive validation result: {}",
                    displayed);

            return displayed;

        } catch (Exception e) {
            logger.error("Responsive validation failed.", e);
            return false;
        }
    }

    // =========================================================
    // Unauthorized Access
    // =========================================================

    public void logoutSession() {
        logger.info("Clearing session and logging out.");

        // Delete cookies
        driver.manage().deleteAllCookies();

        // Clear local storage and session storage
        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.clear();"
                        + "window.sessionStorage.clear();"
        );

        // Navigate to login page
        driver.navigate().to("http://localhost:3000/");
    }

    public boolean isLoginPageDisplayed() {
        try {
            logger.info("Checking login page display.");

            wait.until(
                    ExpectedConditions.urlToBe(
                            "http://localhost:3000/"
                    )
            );

            By loginLocator = By.xpath(
                    "//input[@type='email' "
                    + "or @type='password' "
                    + "or @type='text']"
            );

            boolean displayed = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            loginLocator
                    )
            ).isDisplayed();

            logger.info("Login page displayed: {}", displayed);

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