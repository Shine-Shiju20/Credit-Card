package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import utils.DriverFactory;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FDPage {

    WebDriver driver;
    WebDriverWait wait;

    public FDPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //1
    // Login Page Locators
    By loginToAccountButton = By.xpath("//button[contains(text(),'Login to Account')]");
    By emailField = By.cssSelector("input[placeholder='Enter your registered email']");
    By passwordField = By.cssSelector("input[placeholder='Enter your password']");
    By loginButton = By.xpath("//button[contains(.,'Secure Login')]");

    // Dashboard / FD Locators
    By fixedDepositMenu = By.xpath("//a[contains(@href,'fd')]");
    By createFDButton = By.xpath("//button[contains(text(),'Create New FD')]");
    By createFDModal = By.xpath("//h2[contains(text(),'Create Fixed Deposit')]");

    // FD Form Locators
    By accountDropdown = By.xpath("//select[@name='account_id']");
    By depositAmount = By.xpath("//input[@name='amount']");
    By tenureDropdown = By.xpath("//select[@name='tenure_months']");
    By interestRate = By.xpath("//input[@name='interest_rate']");
    By createFixedDepositBtn = By.xpath("//button[@type='submit']");
    By cancelBtn = By.xpath("//button[contains(text(),'Cancel')]");
    //By successMessage = By.xpath("//div[contains(@class,'toast') or contains(@class,'alert')]");
    By successMessage = By.xpath("//*[contains(text(),'successfully')]");
    By fdListRow = By.xpath("//*[contains(text(),'₹5,000')]");

    By investmentSummary = By.xpath("//div[@class='fd-calculator-box']");
    By summaryDepositAmount = By.xpath("//span[text()='Deposit Amount:']/following-sibling::strong");
    By summaryInterestRate = By.xpath("//span[text()='Interest Rate:']/following-sibling::strong");
    By summaryTenure = By.xpath("//span[text()='Tenure:']/following-sibling::strong");
    By summaryInterestEarned = By.xpath("//span[text()='Total Interest Earned:']/following-sibling::strong");
    By summaryMaturityAmount = By.xpath("//span[text()='Final Maturity Amount:']/following-sibling::strong");
    By summaryROI = By.xpath("//span[text()='Return on Investment:']/following-sibling::strong");

    //Cancelbutton
    By closeIcon = By.xpath("//button[@type='button']");

    //verify linked accounts
    By accountOptions = By.xpath("//select[@name='account_id']/option");
    By zeroAmountValidation = By.xpath("//div[@class='input-hint' and contains(text(),'Minimum ₹1,000 required')]");
    By insufficientBalanceValidation = By.xpath("//*[contains(text(),'Insufficient balance')]");
    By tenureOptions = By.xpath("//select[@name='tenure_months']/option");

    By creatingFDSpinner = By.xpath("//*[contains(text(),'Creating FD')]");

    By currentAccountBalance = By.xpath("//p[text()='Available Balance']/following-sibling::h3");
    By activeFDCard = By.xpath("//div[contains(@class,'fd-card')][.//div[contains(@class,'fd-status-active')]]");
    By fdCardAmount = By.xpath("(//div[contains(@class,'fd-card')]//div[contains(@class,'fd-amount')])[1]");
    By activeStatus = By.xpath("//div[contains(text(),'active')]");
    By remainingDays = By.xpath("(//div[contains(@class,'fd-card')])[1]//*[contains(text(),'days remaining')]");

    By modalOverlay = By.xpath("//div[@class='modal-overlay']");
    By fdCardMaturityAmount = By.xpath("//span[text()='Final Maturity Amount:']/following-sibling::strong");
    By logoutButton = By.xpath("//button[contains(@class,'nav-btn-logout')]");
    By latestFDCard = By.xpath("//div[@class='fd-card'][1]");


    By viewDetailsButton = By.xpath("//button[@class='fd-view-btn']");
    By availableBalanceText = By.xpath("//div[@class='stat-info']");
    By closeFDButton = By.xpath("//button[contains(text(),'Close FD')]");

    // Open Application
    public void openApplication() {
        driver.manage().deleteAllCookies();
        driver.get("http://localhost:3000");
    }

    // Login
    public void login(String email, String password) {

        try {
            List<WebElement> loginButtons = driver.findElements(loginToAccountButton);

            // If login button not visible, refresh once
            if (loginButtons.isEmpty()) {
                driver.navigate().refresh();
            }

            wait.until(ExpectedConditions.elementToBeClickable(loginToAccountButton)).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
            driver.findElement(emailField).sendKeys(email);

            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
            driver.findElement(passwordField).sendKeys(password);

            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

            new WebDriverWait(driver, Duration.ofSeconds(40))
                    .until(ExpectedConditions.visibilityOfElementLocated(fixedDepositMenu));

            System.out.println("Login successful");

        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

    // Navigate to FD Page
    public void navigateToFDPage() {
        wait.until(ExpectedConditions.elementToBeClickable(fixedDepositMenu)).click();
        wait.until(ExpectedConditions.urlContains("/fd"));
    }

    // Open Create FD Modal
    public void clickCreateFD() {
        wait.until(ExpectedConditions.elementToBeClickable(createFDButton)).click();
    }

    // Verify Modal
    public boolean isModalDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(createFDModal)).isDisplayed();
    }

    // Select Account
    public void selectAccount() {

        wait.until(ExpectedConditions.elementToBeClickable(accountDropdown));

        for (int retry = 0; retry < 3; retry++) {

            try {
                Select select = new Select(driver.findElement(accountDropdown));

                List<WebElement> options = select.getOptions();

                for (int i = 0; i < options.size(); i++) {

                    String text = select.getOptions().get(i).getText().trim();

                    if (!text.equalsIgnoreCase("Select Account")
                            && !text.equalsIgnoreCase("No active accounts found")
                            && !text.isEmpty()) {

                        select.selectByVisibleText(text);
                        return;
                    }
                }

                throw new RuntimeException("No valid linked accounts available for FD creation");

            } catch (StaleElementReferenceException e) {

                wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(accountDropdown)
                ));
            }
        }

        throw new RuntimeException("Account dropdown became stale repeatedly");
    }

    // Enter Amount
    public void enterDepositAmount(String amount) {
        WebElement amountField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(depositAmount)
        );

        amountField.clear();
        amountField.sendKeys(amount);
    }

    // Select Tenure
    public void selectTenure(String tenure) {
        wait.until(ExpectedConditions.elementToBeClickable(tenureDropdown));

        Select select = new Select(driver.findElement(tenureDropdown));

        if (tenure.equalsIgnoreCase("12 months")) {
            select.selectByVisibleText("12 months (7% p.a.) - 1 year");
        }
    }

    // Verify Interest Rate
    public boolean isInterestRateAutoFilled() {
        String value = wait.until(ExpectedConditions.visibilityOfElementLocated(interestRate))
                .getAttribute("value");

        return value != null && !value.trim().isEmpty();
    }

    // Create FD
    public void createFixedDeposit() {
        wait.until(ExpectedConditions.elementToBeClickable(createFixedDepositBtn)).click();
    }
    public boolean isSuccessMessageDisplayed() {

        try {
            WebDriverWait toastWait = new WebDriverWait(driver, Duration.ofSeconds(5));

            return toastWait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//div[contains(text(),'created successfully')]")
                    )
            ).isDisplayed();

        } catch (Exception e) {
            return isFDCreated();
        }
    }

    public boolean isFDCreated() {
        By fdCreated = By.xpath("//*[contains(text(),'Active')]");
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(fdCreated)
        ).isDisplayed();
    }

    //cancel functions
    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
    }

    public boolean isModalClosed() {
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(createFDModal)
        );
    }

    public boolean isOnFDPage() {
        return driver.getCurrentUrl().contains("/fd");
    }

    //close button
    public void clickCloseIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(closeIcon)).click();
    }

    //Verify account dropdown populates linked accounts
    public boolean verifyLinkedAccountsDisplayed() {

        wait.until(ExpectedConditions.elementToBeClickable(accountDropdown));

        for (int i = 0; i < 3; i++) {

            Select select = new Select(driver.findElement(accountDropdown));
            List<WebElement> options = select.getOptions();

            for (WebElement option : options) {
                String text = option.getText().trim();

                System.out.println("Dropdown option: " + text);

                if (!text.equalsIgnoreCase("Select Account")
                        && !text.equalsIgnoreCase("No active accounts found")
                        && !text.isEmpty()) {
                    return true;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    //verify 1st acc is selected
    public boolean isFirstAccountAutoSelected() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountDropdown));

        Select select = new Select(driver.findElement(accountDropdown));

        String selectedOption = select.getFirstSelectedOption().getText();

        System.out.println("Selected account: " + selectedOption);

        return selectedOption != null && !selectedOption.trim().isEmpty();
    }

    //Verify deposit amount field accepts valid numeric input
    public boolean isDepositAmountAccepted(String expectedAmount) {
        String actualAmount = driver.findElement(depositAmount).getAttribute("value");

        System.out.println("Entered amount: " + actualAmount);

        return actualAmount.equals(expectedAmount);
    }

    public boolean isFDSubmissionPrevented() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createFixedDepositBtn));

        return !driver.findElement(createFixedDepositBtn).isEnabled();
    }

    public void enterZeroDepositAmount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(depositAmount)).clear();
        driver.findElement(depositAmount).sendKeys("0");
    }
    public boolean isZeroDepositRejected() {
        return !driver.findElement(createFixedDepositBtn).isEnabled();
    }

    public void enterNegativeDepositAmount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(depositAmount)).clear();
        driver.findElement(depositAmount).sendKeys("-10000");
    }
    public boolean isNegativeDepositRejected() {
        return !driver.findElement(createFixedDepositBtn).isEnabled();
    }

    public void selectLowBalanceAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(accountDropdown));

        Select select = new Select(driver.findElement(accountDropdown));

        for (WebElement option : select.getOptions()) {
            if (option.getText().contains("Bal: ₹0")) {
                option.click();
                break;
            }
        }
    }
    public boolean isInsufficientBalanceDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(insufficientBalanceValidation)
        ).isDisplayed();
    }

    public void clickTenureDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(tenureDropdown)).click();
    }

    public boolean areTenureOptionsDisplayed() {
        wait.until(ExpectedConditions.elementToBeClickable(tenureDropdown));

        Select select = new Select(driver.findElement(tenureDropdown));

        List<WebElement> options = select.getOptions();

        System.out.println("Available tenure options:");

        for (WebElement option : options) {
            System.out.println(option.getText());
        }

        return options.size() > 1; // first option may be placeholder
    }

    public boolean isCreateFDButtonDisabled() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createFixedDepositBtn));
        return !driver.findElement(createFixedDepositBtn).isEnabled();
    }

    public void selectTenureByVisibleText(String tenureText) {
        wait.until(ExpectedConditions.elementToBeClickable(tenureDropdown));

        Select select = new Select(driver.findElement(tenureDropdown));
        select.selectByVisibleText(tenureText);
    }

    public boolean isInterestRateCorrect(String expectedRate) {
        String actualRate = wait.until(
                ExpectedConditions.visibilityOfElementLocated(interestRate)
        ).getAttribute("value");

        System.out.println("Expected Interest: " + expectedRate);
        System.out.println("Actual Interest: " + actualRate);

        return actualRate.contains(expectedRate);
    }

    public boolean isInterestRateFieldReadOnly() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(interestRate));

        WebElement field = driver.findElement(interestRate);

        String readonly = field.getAttribute("readonly");
        String disabled = field.getAttribute("disabled");

        System.out.println("readonly: " + readonly);
        System.out.println("disabled: " + disabled);

        return readonly != null || disabled != null || !field.isEnabled();
    }

    public void enterValidFDDetails() {
        enterDepositAmount("50000");
        selectTenureByVisibleText("12 months (7% p.a.) - 1 year");
    }
    public boolean isInvestmentSummaryDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(investmentSummary)
        ).isDisplayed();
    }
    public boolean isDepositAmountCorrect(String expectedAmount) {
        String actual = driver.findElement(summaryDepositAmount).getText();
        System.out.println(actual);
        return actual.contains(expectedAmount);
    }

    public boolean isSummaryInterestRateCorrect(String expectedRate) {
        String actual = driver.findElement(summaryInterestRate).getText();
        return actual.contains(expectedRate);
    }

    public boolean isSummaryTenureCorrect(String expectedTenure) {
        String actual = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(summaryTenure))
                .getText();

        System.out.println("Tenure shown: " + actual);

        return actual.contains(expectedTenure);
    }

    public boolean isInterestEarnedCorrect(String expectedAmount) {
        String actual = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(summaryInterestEarned))
                .getText();

        System.out.println("Interest earned shown: " + actual);

        return actual.replaceAll("[^0-9]", "").contains(expectedAmount);
    }

    public boolean isMaturityAmountCorrect(String expectedAmount) {
        String actual = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(summaryMaturityAmount))
                .getText();

        System.out.println("Maturity amount shown: " + actual);

        return actual.replaceAll("[^0-9]", "").contains(expectedAmount);
    }

    public boolean isROICorrect(String expectedROI) {
        String actual = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(summaryROI))
                .getText();

        System.out.println("ROI shown: " + actual);

        return actual.contains(expectedROI);
    }

    public void leaveAmountBlank() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(depositAmount)).clear();
    }
    public boolean isCreateFDButtonEnabled() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createFixedDepositBtn));
        return driver.findElement(createFixedDepositBtn).isEnabled();
    }

    public void selectCurrentAccount() {

        wait.until(ExpectedConditions.elementToBeClickable(accountDropdown));

        Select select = new Select(driver.findElement(accountDropdown));

        boolean found = false;

        for (WebElement option : select.getOptions()) {

            String text = option.getText().trim();

            System.out.println("Available account: " + text);

            if (text.toUpperCase().contains("CURRENT")
                    && !text.contains("No active accounts found")) {

                select.selectByVisibleText(text);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new RuntimeException("No valid account available");
        }
    }


    public void createValidFD() {
        clickCreateFD();
        selectAccount();
        enterDepositAmount("50000");
        selectTenureByVisibleText("24 months (8.5% p.a.) - 2 years");
        createFixedDeposit();
    }

    public boolean isNewFDVisible() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(activeFDCard)
        ).isDisplayed();
    }
    public boolean isFDCardAmountCorrect(String expectedAmount) {
        String actual = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fdCardAmount)
        ).getText();

        String cleanActual = actual.replaceAll("[^0-9]", "");
        String cleanExpected = expectedAmount.replaceAll("[^0-9]", "");

        System.out.println("Actual FD Amount: " + cleanActual);
        System.out.println("Expected FD Amount: " + cleanExpected);

        return cleanActual.equals(cleanExpected);
    }
    public boolean isFDStatusActive() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(activeStatus)
        ).isDisplayed();
    }
    public boolean isRemainingDaysDisplayed() {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(remainingDays)
        );

        String text = element.getText();

        System.out.println("Remaining Days: " + text);

        return text.contains("days remaining");
    }

    int initialFDCount;

    By fdCards = By.xpath("//div[contains(@class,'fd-card')]");

    public void storeActiveFDCount() {
        initialFDCount = driver.findElements(fdCards).size();
        System.out.println("Stored Initial FD Count: " + initialFDCount);
    }

    public boolean isActiveFDCountIncremented() {

        try {
            // Wait for FD creation success message first
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));

            // Small wait for backend/UI sync
            Thread.sleep(2000);

            // Refresh page so latest FD list loads
            driver.navigate().refresh();

            // Wait for FD page to reload
            wait.until(ExpectedConditions.visibilityOfElementLocated(createFDButton));

            // Wait until count increases
            wait.until(driver ->
                    driver.findElements(fdCards).size() > initialFDCount
            );

            int updatedCount = driver.findElements(fdCards).size();

            System.out.println("Initial FD Count: " + initialFDCount);
            System.out.println("Updated FD Count: " + updatedCount);

            return updatedCount > initialFDCount;

        } catch (Exception e) {
            System.out.println("FD count did not update in time");
            return false;
        }
    }
    double initialReturns;

    By expectedReturns = By.xpath("//span[contains(text(),'Expected Returns')]");

    public void storeExpectedReturns() {
        String text = driver.findElement(expectedReturns).getText();
        initialReturns = Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    public boolean isExpectedReturnsUpdated() {
        String updated = driver.findElement(expectedReturns).getText();
        double updatedValue = Double.parseDouble(updated.replaceAll("[^0-9.]", ""));
        return updatedValue > initialReturns;
    }

    //profile1.feature
    public boolean isModalClosedAfterSuccess() {
        try {
            Thread.sleep(4000);   // allow backend request to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(createFDModal)
        );
    }
    public boolean isFDFormReset() {
        String amount = driver.findElement(depositAmount).getAttribute("value");

        Select select = new Select(driver.findElement(tenureDropdown));
        String selectedTenure = select.getFirstSelectedOption().getText();

        System.out.println("Amount after reopen: " + amount);
        System.out.println("Tenure after reopen: " + selectedTenure);

        return amount.isEmpty() &&
                selectedTenure.contains("Select");
    }

    public void refreshBrowserMultipleTimes(int count) {
        for (int i = 0; i < count; i++) {
            driver.navigate().refresh();

            wait.until(ExpectedConditions.urlContains("/fd"));

            wait.until(ExpectedConditions.visibilityOfElementLocated(createFDButton));
        }
    }
    public boolean isFDPageLoadedProperly() {
        boolean buttonVisible = wait.until(
                ExpectedConditions.visibilityOfElementLocated(createFDButton)
        ).isDisplayed();

        boolean dashboardVisible = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fixedDepositMenu)
        ).isDisplayed();

        return buttonVisible && dashboardVisible;
    }
    public boolean areAccountNumbersMasked() {
        wait.until(ExpectedConditions.elementToBeClickable(accountDropdown));

        Select select = new Select(driver.findElement(accountDropdown));

        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            String text = option.getText();

            System.out.println("Account option: " + text);

            if (text.matches(".*\\*\\*\\*\\*\\d{4}.*")) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    public boolean areSummaryValuesFormattedInINR() {
        String deposit = wait.until(
                ExpectedConditions.visibilityOfElementLocated(summaryDepositAmount)
        ).getText();

        String interest = wait.until(
                ExpectedConditions.visibilityOfElementLocated(summaryInterestEarned)
        ).getText();

        String maturity = wait.until(
                ExpectedConditions.visibilityOfElementLocated(summaryMaturityAmount)
        ).getText();

        System.out.println("Deposit: " + deposit);
        System.out.println("Interest: " + interest);
        System.out.println("Maturity: " + maturity);

        return isINRFormat(deposit) &&
                isINRFormat(interest) &&
                isINRFormat(maturity);
    }
    private boolean isINRFormat(String amount) {
        return amount.matches("₹[\\d,]+");
    }

    public boolean areFDCardValuesFormattedInINR() {

        List<WebElement> amounts = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class,'fd-card')]//*[contains(text(),'₹')]")
                )
        );

        for (WebElement amount : amounts) {
            String text = amount.getText();

            System.out.println("FD Card Value: " + text);

            if (!isINRFormat(text)) {
                return false;
            }
        }

        return true;
    }
    public boolean isKeyboardNavigationWorking() {
        WebElement firstElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(accountDropdown));

        firstElement.click();

        firstElement.sendKeys(Keys.TAB);

        WebElement active1 = driver.switchTo().activeElement();

        active1.sendKeys(Keys.TAB);

        WebElement active2 = driver.switchTo().activeElement();

        active2.sendKeys(Keys.TAB);

        WebElement active3 = driver.switchTo().activeElement();

        System.out.println("Focused elements via TAB navigation");

        return active1 != null && active2 != null && active3 != null;
    }

    public void logout() {
        try {
            List<WebElement> overlays = driver.findElements(
                    By.xpath("//div[contains(@class,'modal-overlay')]")
            );

            if (!overlays.isEmpty()) {
                Actions actions = new Actions(driver);
                actions.moveByOffset(10, 10).click().perform();

                wait.until(ExpectedConditions.invisibilityOf(overlays.get(0)));
            }

        } catch (Exception e) {
            System.out.println("No modal overlay present");
        }

        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();

        // wait until logout completes
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginToAccountButton));
    }

    public boolean isFDCreationBlocked() {

        try {
            // If login button is visible, user is logged out / blocked
            List<WebElement> loginButtons = driver.findElements(loginToAccountButton);

            if (!loginButtons.isEmpty() && loginButtons.get(0).isDisplayed()) {
                return true;
            }

            // If login fields are visible, user is blocked
            List<WebElement> loginFields = driver.findElements(
                    By.xpath("//input[@type='email' or @name='email']")
            );

            if (!loginFields.isEmpty()) {
                return true;
            }

            // If still on protected FD/dashboard page, access was not blocked
            String currentUrl = driver.getCurrentUrl();

            return currentUrl.contains("login");

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLatestFDPresent() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(latestFDCard)
        ).isDisplayed();
    }
    public void openFDPageDirectly() {
        driver.get("http://localhost:3000/fd");
    }

    //71
    public long measureFDPageLoadTime() {
        long start = System.currentTimeMillis();

        driver.get("http://localhost:3000/fd");

        wait.until(ExpectedConditions.visibilityOfElementLocated(createFDButton));

        long end = System.currentTimeMillis();

        System.out.println("FD Page Load Time: " + (end - start) + " ms");

        return end - start;
    }

    //72
    public void clickViewDetails() {
        try {
            List<WebElement> closeIcons = driver.findElements(
                    By.xpath("//i[contains(@class,'fa-times')]")
            );

            if (!closeIcons.isEmpty()) {
                closeIcons.get(0).click();

                wait.until(ExpectedConditions.invisibilityOf(closeIcons.get(0)));
            }

        } catch (Exception e) {
            System.out.println("No popup blocking View Details");
        }

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(viewDetailsButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", button
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", button
        );
    }

    public boolean isOnFDDetailsPage() {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL after View Details: " + url);

        return url.contains("fd")
                || url.contains("details");
    }

    public void createMultipleFDs(int count, String accountType, String amount, String tenure) {

        for (int i = 0; i < count; i++) {

            clickCreateFD();

            if (accountType.equalsIgnoreCase("current")) {
                selectCurrentAccount();
            } else {
                selectAccount();
            }

            enterDepositAmount(amount);

            selectTenureByVisibleText(tenure);

            createFixedDeposit();

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.navigate().refresh();

            wait.until(ExpectedConditions.visibilityOfElementLocated(createFDButton));
        }
    }
    public boolean areMultipleFDCardsDisplayed() {
        List<WebElement> cards = driver.findElements(
                By.xpath("//div[contains(@class,'fd-card')]")
        );

        System.out.println("FD Cards Count: " + cards.size());

        return cards.size() >= 3;
    }

    public boolean isPageScrollable() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Long pageHeight = (Long) js.executeScript(
                "return document.body.scrollHeight;"
        );

        Long viewportHeight = (Long) js.executeScript(
                "return window.innerHeight;"
        );

        System.out.println("Page Height: " + pageHeight);
        System.out.println("Viewport Height: " + viewportHeight);

        if (pageHeight > viewportHeight) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            return true;
        }

        return false;
    }

    //failed testcases
    //16
    public void enterDecimalFDAmount(String amount) {
        WebElement amountField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(depositAmount)
        );

        amountField.clear();
        amountField.sendKeys(amount);
    }
    public boolean isDecimalFDAccepted() {
        WebElement amountField = driver.findElement(depositAmount);

        String validationMessage =
                amountField.getAttribute("validationMessage");

        System.out.println("Validation message: " + validationMessage);

        return validationMessage == null || validationMessage.isEmpty();
    }

    public void enterExactAvailableBalance() {
        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(accountDropdown))
        );

        String selectedText = select.getFirstSelectedOption().getText();

        System.out.println("Selected account text: " + selectedText);

        Pattern pattern = Pattern.compile("Bal:\\s*₹([0-9,]+)");
        Matcher matcher = pattern.matcher(selectedText);

        if (matcher.find()) {
            String balance = matcher.group(1).replace(",", "");

            WebElement amountField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(depositAmount)
            );

            amountField.clear();
            amountField.sendKeys(balance);

            System.out.println("Entered balance: " + balance);
        } else {
            throw new RuntimeException("Balance not found in account text");
        }
    }
    public boolean isFDCreatedSuccessfully() {
        try {
            WebElement successToast = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'successfully')]")
                    )
            );

            return successToast.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void enterCustomDepositAmount(String amount) {
        WebElement amountField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(depositAmount)
        );

        amountField.clear();
        amountField.sendKeys(amount);
    }
    public boolean isCustomAmountAccepted() {
        WebElement amountField = driver.findElement(depositAmount);

        String validationMessage =
                amountField.getAttribute("validationMessage");

        System.out.println("Validation message: " + validationMessage);

        return validationMessage == null || validationMessage.isEmpty();
    }

    public void openActiveFDDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(activeFDCard)).click();

        wait.until(ExpectedConditions.urlContains("fd"));
    }
    public boolean isCloseFDOptionVisible() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(closeFDButton)
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFDCreationLimitEnforced() {
        try {
            WebElement limitMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'limit') or " +
                                    "contains(text(),'maximum') or " +
                                    "contains(text(),'not allowed') or " +
                                    "contains(text(),'No active accounts found')]")
                    )
            );

            return limitMessage.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public int getExistingFDCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createFDButton));
        return driver.findElements(fdCards).size();
    }
    public void createFDUntilLimit(int maxLimit, String accountType, String amount, String tenure) {

        int existingCount = getExistingFDCount();

        int remaining = maxLimit - existingCount;

        System.out.println("Existing FD count: " + existingCount);
        System.out.println("Remaining allowed creations: " + remaining);

        for (int i = 0; i < remaining + 1; i++) {

            clickCreateFD();

            if (accountType.equalsIgnoreCase("current")) {
                selectCurrentAccount();
            } else {
                selectAccount();
            }

            enterDepositAmount(amount);
            selectTenureByVisibleText(tenure);
            createFixedDeposit();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            driver.navigate().refresh();

            wait.until(ExpectedConditions.visibilityOfElementLocated(createFDButton));
        }
    }


    // Close Browser

}