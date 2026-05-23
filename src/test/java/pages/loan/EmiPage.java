package pages.loan;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EmiPage {

    WebDriver driver;

    WebDriverWait wait;

    public EmiPage(WebDriver driver) {

        this.driver =
                driver;

        wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );
    }

    // ================= EMI PAYMENT LOCATORS =================

    By payEmiButton =
            By.xpath("//button[contains(text(),'Pay EMI')]");

    By plusInstallmentButton =
            By.xpath("//button[contains(@class,'lpm-count-btn') and text()='+' and not(@disabled)]");

    By paymentAmountField =
            By.xpath("//input[@name='amount']");

    By submitPaymentButton =
            By.xpath("//div[contains(@class,'modal-overlay')]//button[contains(text(),'Submit') or contains(text(),'Pay')]");

    By paymentModal =
            By.xpath("//div[contains(@class,'modal-overlay')]");

    By toastMessages =
            By.xpath("//span[contains(@class,'toast-message')]");

    By successToast =
            By.xpath("(//span[contains(@class,'toast-message')])");

    By paymentErrorMessage =
            By.xpath("//*[contains(@class,'error') or contains(@class,'alert') or contains(@class,'validation')]");

    // TC_PAY_EMI_003 locators

    By emiAccountDropdown =
            By.xpath("//select[@class='lpm-select']");

    By insufficientBalanceAccountOption =
            By.xpath("//option[contains(text(),'SALARY') and @disabled]");

    By insufficientBalanceAlertMessage =
            By.xpath("//div[@class='lpm-alert lpm-alert-error']");

    // ================= EMI PAYMENT ACTIONS =================

    public void ClickPayEmiButton() {




        wait.until(
                ExpectedConditions.elementToBeClickable(
                        payEmiButton
                )
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        paymentModal
                )
        );
    }

    public boolean IsPayEmiButtonAvailable() {

        try {

            WebDriverWait shortWait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(5)
                    );

            shortWait.until(
                    ExpectedConditions.elementToBeClickable(
                            payEmiButton
                    )
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public void EnterInstallmentsToPay(String installmentsToPay) {

        int targetInstallments =
                Integer.parseInt(installmentsToPay);

        for(int i = 1; i < targetInstallments; i++) {

            try {

                WebElement plusButton =
                        wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        plusInstallmentButton
                                )
                        );

                plusButton.click();

            } catch (Exception e) {

                System.out.println(
                        "Maximum installment limit reached"
                );

                break;
            }
        }
    }

    public void TryToUseInsufficientBalanceAccount() {

        WebElement dropdown =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                emiAccountDropdown
                        )
                );

        Select select =
                new Select(dropdown);

        WebElement salaryOption =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                insufficientBalanceAccountOption
                        )
                );

        if(salaryOption.isSelected()) {

            System.out.println(
                    "Insufficient balance account is already selected"
            );

            return;
        }

        if(salaryOption.isEnabled()) {

            select.selectByVisibleText(
                    salaryOption.getText()
            );
        } else {

            System.out.println(
                    "Insufficient balance account is disabled, so keeping default selected account"
            );
        }
    }

    public void EnterBelowEmiAmount() {

        try {

            WebElement amountField =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    paymentAmountField
                            )
                    );

            amountField.clear();

            amountField.sendKeys(
                    "1"
            );

        } catch (Exception e) {

            System.out.println(
                    "Payment amount field not editable or not available"
            );
        }
    }

    public void ClickSubmitPaymentButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(submitPaymentButton));
        WebElement elem = this.driver.findElement(submitPaymentButton);
         Actions actions = new Actions(driver);
         actions.moveToElement(elem).perform();
        WebElement submitButton =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                submitPaymentButton
                        )
                );

        submitButton.click();
    }

    public void PayEmi(
            String paymentType,
            String installmentsToPay
    ) {

        ClickPayEmiButton();

        EnterInstallmentsToPay(
                installmentsToPay
        );

        if(paymentType.equalsIgnoreCase("BELOW_EMI")) {

            TryToUseInsufficientBalanceAccount();

            EnterBelowEmiAmount();
        }

        ClickSubmitPaymentButton();
    }

    public String GetPaymentMessage() {

        try {

            WebDriverWait explicitWait =
                    new WebDriverWait(driver, Duration.ofSeconds(25));

            String message =
                    explicitWait.until(driver -> {

                        List<WebElement> toasts =
                                driver.findElements(toastMessages);

                        for(WebElement toast : toasts) {

                            String text =
                                    toast.getText().trim();

                            if(toast.isDisplayed()
                                    && text.toLowerCase()
                                    .contains("payment processed successfully")) {

                                return text;
                            }
                        }

                        List<WebElement> alerts =
                                driver.findElements(insufficientBalanceAlertMessage);

                        for(WebElement alert : alerts) {

                            String text =
                                    alert.getText().trim();

                            if(alert.isDisplayed()
                                    && !text.isEmpty()) {

                                return text;
                            }
                        }

                        List<WebElement> errors =
                                driver.findElements(paymentErrorMessage);

                        for(WebElement error : errors) {

                            String text =
                                    error.getText().trim();

                            if(error.isDisplayed()
                                    && !text.isEmpty()
                                    && !text.toLowerCase().contains("welcome back")) {

                                return text;
                            }
                        }

                        return null;
                    });

            System.out.println("Payment Message : " + message);

            return message;

        } catch (Exception e) {

            System.out.println("Payment Message Not Found : " + e.getMessage());

            return "";
        }
    }

    public String getToastMessage() {

        WebElement toast =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                successToast
                        )
                );

        return toast.getText();
    }

    public boolean IsSuccessToastDisplayed() {

        try {

            WebDriverWait explicitWait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(20)
                    );

            WebElement toast =
                    explicitWait.until(driver -> {

                        List<WebElement> toasts =
                                driver.findElements(toastMessages);

                        for(WebElement currentToast : toasts) {

                            String text =
                                    currentToast.getText().trim();

                            if(currentToast.isDisplayed()
                                    && text.toLowerCase()
                                    .contains("payment processed successfully")) {

                                return currentToast;
                            }
                        }

                        return null;
                    });

            String toastText =
                    toast.getText().trim();

            System.out.println("Toast Message : " + toastText);

            explicitWait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            paymentModal
                    )
            );

            return true;

        } catch (Exception e) {

            System.out.println("Toast Not Found : " + e.getMessage());

            return false;
        }
    }
}
