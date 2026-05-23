package pages.loan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ForeclosurePage {

    WebDriver driver;

    WebDriverWait wait;

    String foreclosureMessage;

    public ForeclosurePage(WebDriver driver) {

        this.driver =
                driver;

        wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );
    }

    By forecloseButton =
            By.xpath("//button[@class='btn-loan-action btn-foreclose']");

    By confirmForeclosureButton =
            By.xpath("//button[@class='lpm-btn lpm-btn-danger']");

    By foreclosureModal =
            By.xpath("//div[contains(@class,'modal-overlay')]");

    By accountDropdown =
            By.xpath("//div[contains(@class,'modal-overlay')]//select");

    By insufficientBalanceAccountOption =
            By.xpath("//div[contains(@class,'modal-overlay')]//option[contains(text(),'SALARY')]");

    By toastMessages =
            By.xpath("//*[contains(@class,'toast-message') or contains(@class,'Toastify__toast')]");

    By foreclosureAlertMessage =
            By.xpath("//*[contains(@class,'error') or contains(@class,'alert') or contains(@class,'validation')]");

    private boolean IsForeclosureMessage(String text) {

        String lowerText =
                text.toLowerCase();

        if(lowerText.contains("loan application submitted successfully")
                || lowerText.contains("welcome back")) {

            return false;
        }

        return lowerText.contains("payment processed successfully")
                || lowerText.contains("minimum balance violation")
                || lowerText.contains("foreclosure")
                || lowerText.contains("foreclose");
    }

    public void ClickForecloseButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        forecloseButton
                )
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        foreclosureModal
                )
        );
    }

    public void SelectInsufficientBalanceAccount() {

        WebElement dropdown =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                accountDropdown
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

    public void ClickConfirmForeclosureButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        confirmForeclosureButton
                )
        ).click();
    }

    public void ForecloseLoan() {

        ClickForecloseButton();

        ClickConfirmForeclosureButton();

        foreclosureMessage =
                WaitForForeclosureMessage();
    }

    public void ForecloseLoanUsingInsufficientBalanceAccount() {

        ClickForecloseButton();

        SelectInsufficientBalanceAccount();

        ClickConfirmForeclosureButton();

        foreclosureMessage =
                WaitForForeclosureMessage();
    }

    private String WaitForForeclosureMessage() {

        try {

            WebDriverWait explicitWait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(20)
                    );

            String message =
                    explicitWait.until(driver -> {

                        for(WebElement toast : driver.findElements(toastMessages)) {

                            String text =
                                    toast.getText().trim();

                            if(toast.isDisplayed()
                                    && !text.isEmpty()
                                    && IsForeclosureMessage(text)) {

                                return text;
                            }
                        }

                        for(WebElement alert : driver.findElements(foreclosureAlertMessage)) {

                            String text =
                                    alert.getText().trim();

                            if(alert.isDisplayed()
                                    && !text.isEmpty()
                                    && IsForeclosureMessage(text)) {

                                return text;
                            }
                        }

                        return null;
                    });

            System.out.println("Foreclosure Message : " + message);

            return message;

        } catch (Exception e) {

            System.out.println("Foreclosure Message Not Found : " + e.getMessage());

            return "";
        }
    }

    public String GetForeclosureMessage() {

        if(foreclosureMessage != null
                && !foreclosureMessage.isEmpty()) {

            return foreclosureMessage;
        }

        return WaitForForeclosureMessage();
    }
}
