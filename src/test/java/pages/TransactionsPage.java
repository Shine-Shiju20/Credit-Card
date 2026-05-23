package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void clickWithdrawTab() {
        driver.findElement(withdrawTab).click();
    }

    public String getSelectedAccountNumber() {
        return selectedAccountNumber;
    }

    public void selectWithdrawAccount(String accountType) {
        Select select = new Select(driver.findElement(accountDropdown));

        for(WebElement option : select.getOptions()) {
            String text = option.getText().toLowerCase();
            System.out.println("Dropdown Option : " + text);
            if(text.contains("(" + accountType.toLowerCase() + ")")) {
                selectedAccountBalance = option.getText();
                selectedAccountNumber = option.getText().split("\\(")[0].trim();
                option.click();
                System.out.println("Selected Account : " + selectedAccountBalance);
                System.out.println("Selected Account Number : " + selectedAccountNumber);
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

    public void enterWithdrawAmount(String amount) {
        driver.findElement(amountField).sendKeys(amount);
    }

    public void enterTransactionPin(String pin) {
        driver.findElement(pinField).sendKeys(pin);
    }

    public void clickConfirmWithdraw() {
        driver.findElement(confirmWithdrawButton).click();
    }


}
 