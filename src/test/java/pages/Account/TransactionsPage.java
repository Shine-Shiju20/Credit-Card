package pages.Account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerUtility;

import java.time.Duration;

public class TransactionsPage {
    WebDriver driver;

    // Constructor
    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
    }

    String selectedAccountBalance;
    String selectedAccountNumber;


    By withdrawTab=By.xpath("//button[contains(.,'Withdraw')]");
    By accountDropdown=By.xpath("//select[@class='account-selector']");
    By amountField=By.xpath("//input[@name='amount']");
    By pinField=By.xpath("//input[@name='transaction_pin']");
    By confirmWithdrawButton=By.xpath("//button[contains(.,'Confirm Withdraw')]");

    public void waitForTransactionsPage(){

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(

                ExpectedConditions
                        .visibilityOfElementLocated(
                                withdrawTab
                        )
        );
    }
    public void clickWithdrawTab() {
        WebElement element=
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                ).until(

                        ExpectedConditions
                                .elementToBeClickable(
                                        withdrawTab
                                )
                );

        element.click();
    }

    public String getSelectedAccountNumber(){
        return selectedAccountNumber;
    }

    public void selectWithdrawAccount(String accountType) {
        Select select = new Select(driver.findElement(accountDropdown));

        for(WebElement option : select.getOptions()) {
            String text = option.getText().toLowerCase();
            if(text.contains("(" + accountType.toLowerCase() + ")")) {
                selectedAccountBalance = option.getText();
                selectedAccountNumber = option.getText().split("\\(")[0].trim();
                option.click();
                LoggerUtility.info("Selected Account : " + selectedAccountBalance);
                LoggerUtility.info("Selected Account Number : " + selectedAccountNumber);
                break;
            }
        }
    }

    public void openAccountCardByNumber(String accountNumber) {
        By accountCard = By.xpath("//span[contains(@class,'acc-item-number') and contains(text(),'" + accountNumber + "')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCard));
        driver.findElement(accountCard).click();
    }

    public String getSelectedBalance() {
        if(selectedAccountBalance == null) {
            throw new RuntimeException("No matching account found in dropdown");
        }

        return selectedAccountBalance
                .split("-")[1]
                .replace("₹","")
                .replace(",","")
                .trim();
    }

    public void enterWithdrawAmount(
            String amount){

        WebElement element=
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                ).until(

                        ExpectedConditions
                                .elementToBeClickable(
                                        amountField
                                )
                );

        element.clear();

        element.click();

        element.sendKeys(
                amount
        );

        LoggerUtility.info(
                "Entered Amount : "
                        + element.getAttribute(
                        "value"
                )
        );
    }

    public void enterTransactionPin(
            String pin){

        WebElement element=
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                ).until(

                        ExpectedConditions
                                .elementToBeClickable(
                                        pinField
                                )
                );

        element.clear();

        element.click();

        element.sendKeys(
                pin
        );

        LoggerUtility.info(
                "Entered Transaction PIN"
        );
    }

    public void clickConfirmWithdraw() {
        WebElement element=
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                ).until(

                        ExpectedConditions
                                .elementToBeClickable(
                                        confirmWithdrawButton
                                )
                );

        element.click();
    }

    By toastMessage=
            By.xpath(
                    "//span[contains(@class,'toast-message')]"
            );

    public String getToast(){

        WebDriverWait wait=
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(5)
                );

        return wait.until(

                ExpectedConditions
                        .visibilityOfElementLocated(
                                toastMessage
                        )
        ).getText();
    }

}
