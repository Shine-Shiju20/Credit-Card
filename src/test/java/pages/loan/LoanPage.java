package pages.loan;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
public class LoanPage {
    WebDriver driver;

    WebDriverWait wait;

    public LoanPage(WebDriver driver) {

        this.driver = driver;

        wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(50)
                );
    }

    // Locators


    By loanMenu =
            By.xpath("//span[contains(text(),'Loans')]");

    By applyLoanButton =
            By.xpath("//button[@class='btn-apply-loan']");

    By noActiveLoanMessage =
            By.xpath("//*[contains(text(),'No active loans')]");


    By loanAmountField =
            By.xpath("//input[contains(@placeholder,'500000')]");

    By tenureField =
            By.xpath("//input[@name='tenure_months']");

    By incomeField =
            By.xpath("//input[@name='annual_income']");

    By liabilitiesField =
            By.xpath("//input[@name='existing_liabilities']");

    By submitLoanButton =
            By.xpath("//*[contains(text(),'Submit Application')]");

    By successToast =
            By.xpath("(//span[contains(@class,'toast-message')])");

    By toastMessages =
            By.xpath("//span[contains(@class,'toast-message')]");

    By modalOverlay =
            By.xpath("//div[contains(@class,'modal-overlay')]");
    By loanSlotMessage =
            By.xpath("//*[contains(text(),'1 loan slot available')]");

    By loanLimitMessage =
            By.xpath("//*[contains(text(),'Loan limit reached')]");
    By linkedAccountDropdown =
            By.xpath("//select[@name='linked_account_id']");

    By noActiveAccountsMessage =
            By.xpath("//*[contains(text(),'No active accounts found')]");

    By disabledSubmitButton =
            By.xpath("//button[contains(text(),'Submit') and @disabled]");

    By liabilitiesValidationMessage =
            By.xpath("//*[contains(text(),'Minimum')]");

    By amountValidationMessage =
            By.xpath("//div[contains(@class,'lam-alert-error')]");

    By minimumAmountValidation =
            By.xpath("//*[contains(.,'Minimum loan amount')]");

    By tenureValidationMessage =
            By.xpath("//div[contains(@class,'lam-alert-error')]");

    By approvedLoanStatus =
            By.xpath("//div[contains(@class,'loan-status') and text()='approved']");

    By rejectedLoanStatus =
            By.xpath("//div[contains(@class,'loan-status') and text()='rejected']");



    // Methods
    public void SelectLoanType(String loanType) {

        By loanTypeLocator =
                By.xpath("//*[text()='" + loanType + "']");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loanTypeLocator
                )
        ).click();
    }

    public void NavigateToLoanPage() {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        loanMenu
                )
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loanMenu
                )
        );

        driver.findElement(
                loanMenu
        ).click();
    }

    public boolean IsLoanPageDisplayed() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        applyLoanButton
                )
        );

        return driver.findElement(
                applyLoanButton
        ).isDisplayed();
    }

    public boolean IsNoActiveLoanMessageDisplayed() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        noActiveLoanMessage
                )
        );

        return driver.findElement(
                noActiveLoanMessage
        ).isDisplayed();
    }

    public boolean IsApplyLoanButtonEnabled() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        applyLoanButton
                )
        );

        return driver.findElement(
                applyLoanButton
        ).isEnabled();
    }

    public void ClickApplyLoanButton() {

        WebDriverWait buttonWait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                );

        buttonWait.until(driver -> {

            try {

                WebElement loanButton =
                        driver.findElement(applyLoanButton);

                ((JavascriptExecutor) driver)
                        .executeScript(
                                "arguments[0].scrollIntoView({block: 'center'});",
                                loanButton
                        );

                if(loanButton.isDisplayed()
                        && loanButton.isEnabled()) {

                    loanButton.click();

                    return true;
                }

                return false;

            } catch (StaleElementReferenceException e) {

                return false;
            }
        });
    }

    public void EnterLoanDetails(
            String loanType,
            String amount,
            String tenure,
            String income,
            String liabilities
    ) {

        SelectLoanType(loanType);

        driver.findElement(loanAmountField)
                .clear();

        driver.findElement(loanAmountField)
                .sendKeys(amount);

        driver.findElement(tenureField)
                .clear();

        driver.findElement(tenureField)
                .sendKeys(tenure);

    /*
       Income field handling

       If field is editable:
       enter income from excel.

       If field is disabled/read-only:
       skip typing.
    */

        if(driver.findElement(incomeField).isEnabled()) {

            driver.findElement(incomeField)
                    .clear();

            driver.findElement(incomeField)
                    .sendKeys(income);
        }

        driver.findElement(liabilitiesField)
                .clear();

        driver.findElement(liabilitiesField)
                .sendKeys(liabilities);
    }

    public void ClickSubmitLoanButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        submitLoanButton
                )
        );

        driver.findElement(
                submitLoanButton
        ).click();
    }

    public boolean IsLoanApplicationSuccessMessageDisplayed() {

        try {

            WebDriverWait shortWait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(15)
                    );

            shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            successToast
                    )
            );

            String toastText =
                    driver.findElement(successToast)
                            .getText();

            System.out.println("Toast Message: " + toastText);

            return toastText.contains(
                    "Loan application submitted successfully"
            );

        } catch (Exception e) {

            System.out.println(
                    "Toast not found"
            );

            return false;
        }
    }


    public boolean IsLoanSlotAvailableDisplayed() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        loanSlotMessage
                )
        );

        return driver.findElement(
                loanSlotMessage
        ).isDisplayed();
    }




    public String GetLoanLimitMessage() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        loanLimitMessage
                )
        );

        return driver.findElement(
                loanLimitMessage
        ).getText();
    }
    public boolean IsNoActiveAccountsMessageDisplayed() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        noActiveAccountsMessage
                )
        );

        return driver.findElement(
                noActiveAccountsMessage
        ).isDisplayed();
    }
    public boolean IsLinkedAccountDropdownDisplayed() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        linkedAccountDropdown
                )
        );

        return driver.findElement(
                linkedAccountDropdown
        ).isDisplayed();
    }
    public boolean IsSubmitLoanButtonDisabled() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        submitLoanButton
                )
        );

        return !driver.findElement(
                submitLoanButton
        ).isEnabled();
    }
    public void EnterLiabilities(String liabilities) {

        WebElement liabilitiesInput =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                liabilitiesField
                        )
                );

        liabilitiesInput.click();

        liabilitiesInput.sendKeys(Keys.CONTROL + "a");
        liabilitiesInput.sendKeys(Keys.DELETE);

        liabilitiesInput.sendKeys(liabilities);

        String enteredValue =
                liabilitiesInput.getAttribute("value");

        if(!enteredValue.equals(liabilities)) {

            System.out.println("Normal sendKeys failed. Using JavaScript.");

            org.openqa.selenium.JavascriptExecutor js =
                    (org.openqa.selenium.JavascriptExecutor) driver;

            js.executeScript(
                    "arguments[0].value=arguments[1];" +
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    liabilitiesInput,
                    liabilities
            );
        }

        liabilitiesInput.sendKeys(Keys.TAB);

        System.out.println(
                "Liabilities Entered : "
                        + liabilitiesInput.getAttribute("value")
        );
    }
    public String GetLiabilitiesValue() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        liabilitiesField
                )
        );

        return driver.findElement(
                liabilitiesField
        ).getAttribute("value");
    }
    public boolean IsLiabilityValidationDisplayed() {

        try {

            WebElement liabilityInput =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    liabilitiesField
                            )
                    );

            String validationMessage =
                    liabilityInput.getAttribute("validationMessage");

            System.out.println(
                    "Liability Browser Validation Message : "
                            + validationMessage
            );

            return validationMessage != null
                    && validationMessage.contains(
                    "Value must be greater than or equal to 0"
            );

        } catch(Exception e) {

            System.out.println(
                    "Liability validation not found : "
                            + e.getMessage()
            );

            return false;
        }
    }

    public String GetAutoPopulatedLiabilities() {

        try {

            WebDriverWait explicitWait =
                    new WebDriverWait(driver, Duration.ofSeconds(20));

            String liabilities =
                    explicitWait.until(driver -> {

                        WebElement liabilitiesInput =
                                driver.findElement(liabilitiesField);

                        String value =
                                liabilitiesInput.getAttribute("value");

                        if(value != null
                                && !value.trim().isEmpty()
                                && !value.equals("0")) {

                            return value.trim();
                        }

                        return null;
                    });

            System.out.println(
                    "Auto Populated Liabilities : " + liabilities
            );

            return liabilities;

        } catch (Exception e) {

            System.out.println(
                    "Liabilities Not Auto Populated : "
                            + e.getMessage()
            );

            return "";
        }
    }

    public void ClearLoanAmountField() {

        driver.findElement(
                loanAmountField
        ).clear();
    }
    public String GetLoanAmountFieldValue() {

        return driver.findElement(
                loanAmountField
        ).getAttribute("value");
    }

    public boolean IsMinimumAmountValidationDisplayed() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement validation =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    minimumAmountValidation
                            )
                    );

            String actualMessage =
                    validation.getText();

            System.out.println(
                    "Minimum Amount Validation: "
                            + actualMessage
            );

            return actualMessage.contains(
                    "Minimum loan amount"
            );

        } catch (Exception e) {

            System.out.println(
                    "Minimum amount validation not displayed"
            );

            return false;
        }
    }
    public void EnterLoanAmount(String amount) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        loanAmountField
                )
        );

        driver.findElement(
                loanAmountField
        ).clear();

        driver.findElement(
                loanAmountField
        ).sendKeys(amount);
    }
    public void EnterLoanTenure(String tenure) {

        driver.findElement(
                tenureField
        ).clear();

        driver.findElement(
                tenureField
        ).sendKeys(tenure);
    }
    public String GetAmountValidationMessage() {

        try {

            WebElement amountField =
                    driver.findElement(loanAmountField);

            String validationMessage =
                    amountField.getAttribute("validationMessage");

            if(validationMessage != null
                    && !validationMessage.isEmpty()) {

                System.out.println(
                        "Browser Validation Message : "
                                + validationMessage
                );

                return validationMessage;
            }

            WebElement validation =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    amountValidationMessage
                            )
                    );

            String message =
                    validation.getText();

            System.out.println(
                    "DOM Validation Message : "
                            + message
            );

            return message.trim();

        } catch (Exception e) {

            System.out.println(
                    "Validation Message Not Captured"
            );

            return "";
        }
    }

    public boolean IsSuccessToastDisplayed() {

        try {

            String toastText =
                    wait.until(driver -> {

                        List<WebElement> toasts =
                                driver.findElements(toastMessages);

                        for(WebElement toast : toasts) {

                            String text =
                                    toast.getText().trim();

                            if(toast.isDisplayed()
                                    && text.toLowerCase()
                                    .contains("loan application submitted successfully")) {

                                return text;
                            }
                        }

                        return null;
                    });

            System.out.println("Toast Message : " + toastText);

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            modalOverlay
                    )
            );

            return true;

        } catch (Exception e) {

            System.out.println("Toast Not Found : " + e.getMessage());

            return false;
        }
    }
    public void EnterTenure(String tenure) {

        WebElement tenureInput =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                tenureField
                        )
                );

        tenureInput.click();

        tenureInput.sendKeys(
                Keys.CONTROL + "a"
        );

        tenureInput.sendKeys(Keys.DELETE);

        tenureInput.sendKeys(tenure);
    }
    public String GetTenureValidationMessage() {

        try {

            WebElement tenureInput =
                    driver.findElement(tenureField);

            String browserValidation =
                    tenureInput.getAttribute(
                            "validationMessage"
                    );

            if(browserValidation != null
                    && !browserValidation.isEmpty()) {

                System.out.println(
                        "Browser Validation : "
                                + browserValidation
                );

                return browserValidation;
            }

            WebElement validation =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    tenureValidationMessage
                            )
                    );

            String message =
                    validation.getText();

            System.out.println(
                    "DOM Validation : "
                            + message
            );

            return message.trim();

        } catch (Exception e) {

            System.out.println(
                    "Tenure Validation Not Captured"
            );

            return "";
        }
    }

    public String GetEnteredLoanAmount() {

        WebElement amountInput =
                driver.findElement(loanAmountField);

        return amountInput.getAttribute("value");
    }

    public String GetLoanApprovalStatus() {

        try {

            WebDriverWait statusWait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(50)
                    );

            return statusWait.until(driver -> {

                try {

                    List<WebElement> statusList =
                            driver.findElements(
                                    By.xpath(
                                            "//div[contains(@class,'loan-status')]"
                                    )
                            );

                    for(WebElement statusElement : statusList) {

                        String text =
                                statusElement.getText().trim();

                        if(text.equalsIgnoreCase("APPROVED")) {

                            System.out.println(
                                    "Loan Status : " + text
                            );

                            return "APPROVED";
                        }

                        if(text.equalsIgnoreCase("REJECTED")) {

                            System.out.println(
                                    "Loan Status : " + text
                            );

                            return "REJECTED";
                        }
                    }

                    return null;

                } catch (StaleElementReferenceException e) {

                    return null;
                }
            });

        } catch (Exception e) {

            System.out.println(
                    "Loan Status Not Found : "
                            + e.getMessage()
            );

            return "";
        }
    }




    public String GetIncomeValidationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(incomeField));
        return driver.findElement(incomeField)
                .getAttribute("validationMessage");
    }
    public String GetAnnualIncomeFieldValue() {

        return driver.findElement(incomeField)
                .getAttribute("value");
    }
    public String GetDisplayedAnnualIncome() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        incomeField
                )
        );

        return driver.findElement(incomeField)
                .getAttribute("value");
    }
    public boolean IsAnnualIncomeFieldDisabled() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        incomeField
                )
        );

        return !driver.findElement(
                incomeField
        ).isEnabled();
    }

    public String getToastMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));

        return toast.getText();
    }


}



