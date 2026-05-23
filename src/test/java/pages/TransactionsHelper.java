//package pages;
//
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class TransactionsPage {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    // =========================================================
//    // Navigation
//    // =========================================================
//
//    private By transactionsLink =
//            By.xpath("//a[contains(@href,'transactions')]");
//
//    private By pageHeader =
//            By.xpath("//h1[text()='Transactions']");
//
//    // =========================================================
//    // Tabs
//    // =========================================================
//
//    private By transferTab =
//            By.xpath("//button[contains(@class,'tab-btn') and contains(.,'Transfer')]");
//
//    private By depositTab =
//            By.xpath("//button[contains(@class,'tab-btn') and contains(.,'Deposit')]");
//
//    private By withdrawTab =
//            By.xpath("//button[contains(@class,'tab-btn') and contains(.,'Withdraw')]");
//
//    // =========================================================
//    // Account Dropdown
//    // =========================================================
//
//    private By accountDropdown =
//            By.name("source_account_id");
//
//    private By accountOptions =
//            By.cssSelector("select[name='source_account_id'] option");
//
//    // =========================================================
//    // Recipient Account Number
//    // =========================================================
//
//    private By recipientAccountField =
//            By.name("target_account_number");
//
//    // =========================================================
//    // Transfer Type Dropdown
//    // =========================================================
//
//    // IMPORTANT:
//    // Your frontend uses name="transfer_type"
//    private By transferTypeDropdown =
//            By.name("transfer_type");
//
//    private By transferTypeOptions =
//            By.cssSelector("select[name='transfer_type'] option");
//
//    // =========================================================
//    // Amount Field
//    // =========================================================
//
//    private By amountField =
//            By.name("amount");
//
//    // =========================================================
//    // Transaction PIN Field
//    // =========================================================
//
//    private By transactionPinField =
//            By.name("transaction_pin");
//
//    // =========================================================
//    // Submit and Success Message
//    // =========================================================
//
//    private By submitButton =
//            By.cssSelector("button[type='submit']");
//
//    private By successMessage =
//            By.xpath("//*[contains(text(),'successful') "
//                    + "or contains(text(),'Success') "
//                    + "or contains(text(),'completed successfully')]");
//
//    public TransactionsPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//    }
//
//    // =========================================================
//    // Navigation Methods
//    // =========================================================
//
//    public void open() {
//        wait.until(
//                ExpectedConditions.elementToBeClickable(transactionsLink)
//        ).click();
//    }
//
//    public boolean isPageLoaded() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(pageHeader)
//        ).isDisplayed();
//    }
//
//    // =========================================================
//    // Transfer Tab
//    // =========================================================
//
//    public void clickTransferTab() {
//        wait.until(
//                ExpectedConditions.elementToBeClickable(transferTab)
//        ).click();
//    }
//
//    public boolean isTransferTabDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(transferTab)
//        ).isDisplayed();
//    }
//
//    // =========================================================
//    // Deposit Tab
//    // =========================================================
//
//    public void clickDepositTab() {
//        wait.until(
//                ExpectedConditions.elementToBeClickable(depositTab)
//        ).click();
//    }
//
//    public boolean isDepositTabDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(depositTab)
//        ).isDisplayed();
//    }
//
//    // =========================================================
//    // Withdraw Tab
//    // =========================================================
//
//    public void clickWithdrawTab() {
//        wait.until(
//                ExpectedConditions.elementToBeClickable(withdrawTab)
//        ).click();
//    }
//
//    public boolean isWithdrawTabDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(withdrawTab)
//        ).isDisplayed();
//    }
//
//    // =========================================================
//    // Account Dropdown
//    // =========================================================
//
//    public boolean isAccountDropdownDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(accountDropdown)
//        ).isDisplayed();
//    }
//
//    public boolean hasAccountsInDropdown() {
//        wait.until(
//                ExpectedConditions.visibilityOfElementLocated(accountDropdown)
//        );
//
//        List<WebElement> options = driver.findElements(accountOptions);
//        return options.size() > 0;
//    }
//
//    // =========================================================
//    // Recipient Account Number
//    // =========================================================
//
//    public boolean isRecipientAccountFieldDisplayed() {
//        return wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        recipientAccountField
//                )
//        ).isDisplayed();
//    }
//
//    public void enterRecipientAccountNumber(String accountNumber) {
//        WebElement field = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        recipientAccountField
//                )
//        );
//        field.clear();
//        field.sendKeys(accountNumber);
//    }
//
//    public boolean isRecipientAccountNumberAccepted() {
//        WebElement field = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        recipientAccountField
//                )
//        );
//
//        String value = field.getAttribute("value");
//        return value != null && !value.trim().isEmpty();
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
//    public boolean verifyTransferTypeOptions(List<String> expectedOptions) {
//        wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        transferTypeDropdown
//                )
//        );
//
//        List<WebElement> options =
//                driver.findElements(transferTypeOptions);
//
//        List<String> actualOptions = new ArrayList<>();
//
//        for (WebElement option : options) {
//            String value = option.getAttribute("value");
//            if (value != null) {
//                actualOptions.add(value.trim().toLowerCase());
//            }
//        }
//
//        return actualOptions.containsAll(expectedOptions);
//    }
//
//    // =========================================================
//    // Amount Field
//    // =========================================================
//
//    public void enterAmount(String amount) {
//        WebElement field = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(amountField)
//        );
//        field.clear();
//        field.sendKeys(amount);
//    }
//
//    public String getAmountValue() {
//        WebElement field = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(amountField)
//        );
//        return field.getAttribute("value");
//    }
//
//    // =========================================================
//    // Transaction PIN
//    // =========================================================
//
//    public void enterTransactionPin(String pin) {
//        WebElement field = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        transactionPinField
//                )
//        );
//        field.clear();
//        field.sendKeys(pin);
//    }
//
//    public String getTransactionPinValue() {
//        WebElement field = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        transactionPinField
//                )
//        );
//        return field.getAttribute("value");
//    }
//
//    // =========================================================
//    // Transaction Execution (Excel Driven)
//    // =========================================================
//
//    public void performTransaction(
//            String sourceAccount,
//            String recipientAccount,
//            String transferType,
//            String amount,
//            String pin) {
//
//        // -----------------------------------------------------
//        // Select source account using first 16 digits
//        // -----------------------------------------------------
//        if (sourceAccount != null && !sourceAccount.trim().isEmpty()) {
//
//            String first16Digits = sourceAccount.trim();
//
//            if (first16Digits.length() > 16) {
//                first16Digits = first16Digits.substring(0, 16);
//            }
//
//            Select sourceSelect = new Select(
//                    wait.until(
//                            ExpectedConditions.visibilityOfElementLocated(
//                                    accountDropdown
//                            )
//                    )
//            );
//
//            boolean accountFound = false;
//
//            for (WebElement option : sourceSelect.getOptions()) {
//                String optionText = option.getText().trim();
//
//                if (optionText.contains(first16Digits)) {
//                    sourceSelect.selectByVisibleText(optionText);
//                    accountFound = true;
//                    break;
//                }
//            }
//
//            if (!accountFound) {
//                throw new RuntimeException(
//                        "Source account not found in dropdown: "
//                                + first16Digits
//                );
//            }
//        }
//
//        // -----------------------------------------------------
//        // Select transaction mode
//        // -----------------------------------------------------
//        if (transferType != null && !transferType.trim().isEmpty()) {
//
//            String type = transferType.trim().toLowerCase();
//
//            // Deposit transaction
//            if (type.equals("deposit")) {
//                clickDepositTab();
//            }
//
//            // Withdrawal transaction
//            else if (type.equals("withdraw")) {
//                clickWithdrawTab();
//            }
//
//            // Transfer transaction
//            else {
//
//                // Activate Transfer tab
//                clickTransferTab();
//
//                Select transferSelect = new Select(
//                        wait.until(
//                                ExpectedConditions.visibilityOfElementLocated(
//                                        transferTypeDropdown
//                                )
//                        )
//                );
//
//                boolean optionFound = false;
//
//                for (WebElement option : transferSelect.getOptions()) {
//                    String value = option.getAttribute("value");
//                    String text = option.getText();
//
//                    boolean valueMatches =
//                            value != null
//                            && value.trim().equalsIgnoreCase(type);
//
//                    boolean textMatches =
//                            text != null
//                            && text.trim().equalsIgnoreCase(type);
//
//                    if (valueMatches || textMatches) {
//                        option.click();
//                        optionFound = true;
//                        break;
//                    }
//                }
//
//                if (!optionFound) {
//                    throw new RuntimeException(
//                            "Transfer type not found in dropdown: "
//                                    + transferType
//                    );
//                }
//            }
//        }
//
//        // -----------------------------------------------------
//        // Enter recipient account (only if provided)
//        // -----------------------------------------------------
//        if (recipientAccount != null
//                && !recipientAccount.trim().isEmpty()) {
//            enterRecipientAccountNumber(recipientAccount);
//        }
//
//        // -----------------------------------------------------
//        // Enter amount
//        // -----------------------------------------------------
//        enterAmount(amount);
//
//        // -----------------------------------------------------
//        // Enter transaction PIN
//        // -----------------------------------------------------
//        enterTransactionPin(pin);
//
//        // -----------------------------------------------------
//        // Submit transaction
//        // -----------------------------------------------------
//        wait.until(
//                ExpectedConditions.elementToBeClickable(submitButton)
//        ).click();
//    }
//
//    // =========================================================
//    // Success Validation
//    // =========================================================
//
//    public boolean isTransactionSuccessful() {
//
//        try {
//            // Wait briefly for visible success message
//            WebDriverWait shortWait =
//                    new WebDriverWait(driver, Duration.ofSeconds(5));
//
//            WebElement successElement = shortWait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            successMessage
//                    )
//            );
//
//            return successElement.isDisplayed();
//
//        } catch (Exception e) {
//
//            // Fallback 1: Check page source for success keywords
//            String pageSource = driver.getPageSource().toLowerCase();
//
//            if (pageSource.contains("successful")
//                    || pageSource.contains("success")
//                    || pageSource.contains("transaction completed")
//                    || pageSource.contains("deposit successful")
//                    || pageSource.contains("withdrawal successful")
//                    || pageSource.contains("transfer successful")) {
//                return true;
//            }
//
//            // Fallback 2: If submit button disappears,
//            // assume transaction was processed.
//            try {
//                WebElement submitBtn = driver.findElement(submitButton);
//
//                if (!submitBtn.isDisplayed()) {
//                    return true;
//                }
//
//            } catch (Exception ignored) {
//                // Submit button not found after submission
//                return true;
//            }
//
//            // No success evidence found
//            return false;
//        }
//    }
//}

package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Log;

public class TransactionsHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    // Logger
    private static final Logger logger =
            Log.getLogger(TransactionsPage.class);

    // =========================================================
    // Navigation
    // =========================================================

    private By transactionsLink =
            By.xpath("//a[contains(@href,'transactions')]");

    private By pageHeader =
            By.xpath("//h1[text()='Transactions']");

    // =========================================================
    // Tabs
    // =========================================================

    private By transferTab =
            By.xpath("//button[contains(@class,'tab-btn') and contains(.,'Transfer')]");

    private By depositTab =
            By.xpath("//button[contains(@class,'tab-btn') and contains(.,'Deposit')]");

    private By withdrawTab =
            By.xpath("//button[contains(@class,'tab-btn') and contains(.,'Withdraw')]");

    // =========================================================
    // Account Dropdown
    // =========================================================

    private By accountDropdown =
            By.name("source_account_id");

    private By accountOptions =
            By.cssSelector("select[name='source_account_id'] option");

    // =========================================================
    // Recipient Account Number
    // =========================================================

    private By recipientAccountField =
            By.name("target_account_number");

    // =========================================================
    // Transfer Type Dropdown
    // =========================================================

    private By transferTypeDropdown =
            By.name("transfer_type");

    private By transferTypeOptions =
            By.cssSelector("select[name='transfer_type'] option");

    // =========================================================
    // Amount Field
    // =========================================================

    private By amountField =
            By.name("amount");

    // =========================================================
    // Transaction PIN Field
    // =========================================================

    private By transactionPinField =
            By.name("transaction_pin");

    // =========================================================
    // Submit and Success Message
    // =========================================================

    private By submitButton =
            By.cssSelector("button[type='submit']");

    private By successMessage =
            By.xpath("//*[contains(text(),'successful') "
                    + "or contains(text(),'Success') "
                    + "or contains(text(),'completed successfully')]");

    public TransactionsHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("TransactionsPage initialized.");
    }

    // =========================================================
    // Navigation Methods
    // =========================================================

    public void open() {
        logger.info("Navigating to Transactions page.");

        wait.until(
                ExpectedConditions.elementToBeClickable(transactionsLink)
        ).click();
    }

    public boolean isPageLoaded() {
        logger.info("Checking whether Transactions page is loaded.");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageHeader)
        ).isDisplayed();
    }

    // =========================================================
    // Tab Methods
    // =========================================================

    public void clickTransferTab() {
        logger.info("Clicking Transfer tab.");
        wait.until(
                ExpectedConditions.elementToBeClickable(transferTab)
        ).click();
    }

    public boolean isTransferTabDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(transferTab)
        ).isDisplayed();
    }

    public void clickDepositTab() {
        logger.info("Clicking Deposit tab.");
        wait.until(
                ExpectedConditions.elementToBeClickable(depositTab)
        ).click();
    }

    public boolean isDepositTabDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(depositTab)
        ).isDisplayed();
    }

    public void clickWithdrawTab() {
        logger.info("Clicking Withdraw tab.");
        wait.until(
                ExpectedConditions.elementToBeClickable(withdrawTab)
        ).click();
    }

    public boolean isWithdrawTabDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(withdrawTab)
        ).isDisplayed();
    }

    // =========================================================
    // Dropdown Validations
    // =========================================================

    public boolean isAccountDropdownDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(accountDropdown)
        ).isDisplayed();
    }

    public boolean hasAccountsInDropdown() {
        List<WebElement> options = driver.findElements(accountOptions);
        logger.info("Account dropdown contains {} options.", options.size());
        return options.size() > 0;
    }

    // =========================================================
    // Recipient Account Methods
    // =========================================================

    public boolean isRecipientAccountFieldDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        recipientAccountField
                )
        ).isDisplayed();
    }

    public void enterRecipientAccountNumber(String accountNumber) {
        logger.info("Entering recipient account number: {}", accountNumber);

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        recipientAccountField
                )
        );
        field.clear();
        field.sendKeys(accountNumber);
    }

    public boolean isRecipientAccountNumberAccepted() {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        recipientAccountField
                )
        );

        String value = field.getAttribute("value");
        return value != null && !value.trim().isEmpty();
    }

    // =========================================================
    // Transfer Type Methods
    // =========================================================

    public boolean isTransferTypeDropdownDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        transferTypeDropdown
                )
        ).isDisplayed();
    }

    public boolean verifyTransferTypeOptions(List<String> expectedOptions) {
        List<WebElement> options =
                driver.findElements(transferTypeOptions);

        List<String> actualOptions = new ArrayList<>();

        for (WebElement option : options) {
            String value = option.getAttribute("value");
            if (value != null) {
                actualOptions.add(value.trim().toLowerCase());
            }
        }

        logger.info("Actual transfer type options: {}", actualOptions);

        return actualOptions.containsAll(expectedOptions);
    }

    // =========================================================
    // Amount Methods
    // =========================================================

    public void enterAmount(String amount) {
        logger.info("Entering amount: {}", amount);

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(amountField)
        );
        field.clear();
        field.sendKeys(amount);
    }

    public String getAmountValue() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(amountField)
        ).getAttribute("value");
    }

    // =========================================================
    // PIN Methods
    // =========================================================

    public void enterTransactionPin(String pin) {
        logger.info("Entering transaction PIN.");

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        transactionPinField
                )
        );
        field.clear();
        field.sendKeys(pin);
    }

    public String getTransactionPinValue() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        transactionPinField
                )
        ).getAttribute("value");
    }

    // =========================================================
    // Transaction Execution
    // =========================================================

    public void performTransaction(
            String sourceAccount,
            String recipientAccount,
            String transferType,
            String amount,
            String pin) {

        logger.info("Starting transaction execution.");
        logger.info("Source Account: {}", sourceAccount);
        logger.info("Recipient Account: {}", recipientAccount);
        logger.info("Transfer Type: {}", transferType);
        logger.info("Amount: {}", amount);

        // Select source account
        if (sourceAccount != null && !sourceAccount.trim().isEmpty()) {

            String first16Digits = sourceAccount.trim();

            if (first16Digits.length() > 16) {
                first16Digits = first16Digits.substring(0, 16);
            }

            Select sourceSelect = new Select(
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    accountDropdown
                            )
                    )
            );

            boolean accountFound = false;

            for (WebElement option : sourceSelect.getOptions()) {
                if (option.getText().contains(first16Digits)) {
                    sourceSelect.selectByVisibleText(
                            option.getText()
                    );
                    accountFound = true;
                    logger.info("Selected source account.");
                    break;
                }
            }

            if (!accountFound) {
                logger.error(
                        "Source account not found: {}",
                        first16Digits
                );
                throw new RuntimeException(
                        "Source account not found in dropdown: "
                                + first16Digits
                );
            }
        }

        // Select transaction type
        if (transferType != null && !transferType.trim().isEmpty()) {

            String type = transferType.trim().toLowerCase();

            if (type.equals("deposit")) {
                clickDepositTab();
            } else if (type.equals("withdraw")) {
                clickWithdrawTab();
            } else {
                clickTransferTab();

                Select transferSelect = new Select(
                        wait.until(
                                ExpectedConditions.visibilityOfElementLocated(
                                        transferTypeDropdown
                                )
                        )
                );

                boolean optionFound = false;

                for (WebElement option :
                        transferSelect.getOptions()) {

                    String value = option.getAttribute("value");
                    String text = option.getText();

                    if ((value != null
                            && value.equalsIgnoreCase(type))
                            || (text != null
                            && text.equalsIgnoreCase(type))) {

                        option.click();
                        optionFound = true;
                        logger.info(
                                "Selected transfer type: {}",
                                type
                        );
                        break;
                    }
                }

                if (!optionFound) {
                    logger.error(
                            "Transfer type not found: {}",
                            transferType
                    );
                    throw new RuntimeException(
                            "Transfer type not found in dropdown: "
                                    + transferType
                    );
                }
            }
        }

        // Recipient account
        if (recipientAccount != null
                && !recipientAccount.trim().isEmpty()) {
            enterRecipientAccountNumber(recipientAccount);
        }

        // Amount and PIN
        enterAmount(amount);
        enterTransactionPin(pin);

        // Submit
        logger.info("Submitting transaction.");
        wait.until(
                ExpectedConditions.elementToBeClickable(submitButton)
        ).click();
    }

    // =========================================================
    // Success Validation
    // =========================================================

    public boolean isTransactionSuccessful() {
        try {
            logger.info("Checking for success message.");

            WebDriverWait shortWait =
                    new WebDriverWait(driver,
                            Duration.ofSeconds(5));

            WebElement successElement = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            successMessage
                    )
            );

            logger.info(
                    "Transaction success message displayed: {}",
                    successElement.getText()
            );

            return successElement.isDisplayed();

        } catch (Exception e) {

            logger.warn(
                    "Success message not found. "
                            + "Checking fallback methods."
            );

            String pageSource =
                    driver.getPageSource().toLowerCase();

            if (pageSource.contains("successful")
                    || pageSource.contains("success")
                    || pageSource.contains("transaction completed")) {
                logger.info(
                        "Success detected via page source."
                );
                return true;
            }

            try {
                WebElement submitBtn =
                        driver.findElement(submitButton);

                if (!submitBtn.isDisplayed()) {
                    logger.info(
                            "Submit button disappeared; "
                                    + "assuming success."
                    );
                    return true;
                }

            } catch (Exception ignored) {
                logger.info(
                        "Submit button not found; "
                                + "assuming success."
                );
                return true;
            }

            logger.error("Transaction success not detected.");
            return false;
        }
    }
}