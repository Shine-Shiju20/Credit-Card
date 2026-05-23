package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsPage {

    WebDriver driver;

    // Constructor
    public AccountsPage(WebDriver driver) {
        this.driver = driver;
    }
    String updatedBalance;
    // Locators

    By accountTypeDropdown = By.xpath("//select[@name='account_type']");
    By depositField = By.xpath("//input[@name='initial_deposit']");
    By createAccountSubmitButton = By.xpath("//button[@class='ca-btn-submit']");
    By openAccount = By.xpath("//div[@class='page-header']//button");
    By loginOverlay = By.xpath("//div[contains(@class,'login-modal-overlay')]");
    By toastMsg = By.xpath("//span[contains(@class,'toast-message')]");
    By validationMsg = By.xpath("//div[@class='alert alert-danger']");
    By LogoutBtn = By.xpath("//button[@class='nav-btn-logout']");
    By AccountNumber = By.xpath("//span[@class='acc-item-number']");
    By LastaccountNumber = By.xpath("(//span[contains(@class,'acc-item-number')])[last()]");
    By selectedAccountNumber = By.xpath("//span[contains(@class,'acc-item-number')]");
    //button[@class='btn btn-secondary btn-sm']
    By changeTypeButton = By.xpath("//button[contains(text(),'Change Type')]");
    By updateDropdown = By.xpath("//select[@class='input-field']");
    By saveButton = By.xpath("//button[contains(text(),'Save')]");
    By closeAccountButton = By.xpath("//button[@class='btn btn-danger btn-sm']");
    By confirmCloseButton = By.xpath("//button[contains(.,'Yes, Close Account')]");
    // Actions
    public void clickOpenAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement openBtn = wait.until(ExpectedConditions.elementToBeClickable(openAccount));

        openBtn.click();
    }

    public void selectAccountType(String accountType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(accountTypeDropdown));
        Select select = new Select(dropdown);

        select.selectByVisibleText(accountType);
    }

    public void enterDeposit(String amount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement deposit = wait.until(ExpectedConditions.visibilityOfElementLocated(depositField));
        deposit.clear();
        deposit.sendKeys(amount);
    }

    public void clickSubmitButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(createAccountSubmitButton));
        button.click();
    }

    //Create Account
    public void createAccount(String accountType, String amount) {
        clickOpenAccount();
        selectAccountType(accountType);
        enterDeposit(amount);
        clickSubmitButton();
    }
    public String getCreatedAccountNumber(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(LastaccountNumber));
        String accountNo = account.getText();
        System.out.println("Created Account Number : " +accountNo);

        return accountNo;
    }

    public String getToastMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastMsg));

        return toast.getText();
    }

    public String getValidationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(validationMsg));

        return message.getText();
    }

    public String getBrowserValidationMessage() {
        return driver.findElement(depositField).getAttribute("validationMessage");
    }

    public String getDepositFieldValue() {
        return driver.findElement(depositField).getAttribute("value");

    }

    public void clickLogout() {
        driver.findElement(LogoutBtn).click();
    }

    //Unique Account NO
    public List<String> getAllAccountNumbers() {
        List<WebElement> accountList = driver.findElements(AccountNumber);
        List<String> accountNumbers = new ArrayList<>();

        for(WebElement account : accountList) {
            accountNumbers.add(account.getText());
        }
        return accountNumbers;
    }

    public String getLatestAccountNumber() {
        List<WebElement> accountList = driver.findElements(AccountNumber);
        return accountList.get(0).getText();
    }


    //updateAccountType
    public String getSelectedAccountNumber(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement accountNo = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                selectedAccountNumber
                        )
                );

        String number =
                accountNo.getText();

        System.out.println(
                "Selected Account Number : "
                        + number
        );

        return number;
    }
    public String getCurrentAccountType(){
        return driver.findElement(By.xpath("//span[contains(@class,'acc-item-type')]")).getText();
    }
    public void clickChangeType() {
        driver.findElement(changeTypeButton).click();
    }

    public void selectUpdatedAccountType(String accountType) {
        Select select = new Select(driver.findElement(updateDropdown));
        select.selectByVisibleText(accountType);
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public void openAccountCardByType(String accountType) {
        By accountCard = By.xpath("(//span[contains(@class,'acc-item-type') and contains(.,'" + accountType.toLowerCase() + "')])[1]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(accountCard));

        driver.findElement(accountCard).click();
    }

    public void updateAccountType(String existingType, String newType) throws InterruptedException {
        openAccountCardByType(existingType);
        Thread.sleep(1000);
        clickChangeType();
        Thread.sleep(1000);
        selectUpdatedAccountType(newType);
        Thread.sleep(1000);
        clickSaveButton();
    }

    //updatedDetailsReflection
    public String getUpdatedAccountType(){
        return driver.findElement(By.xpath("(//span[contains(@class,'acc-item-type')])[1]")).getText();
    }

    //fetchAccounts
    public int getAccountCount() {
        return driver.findElements(AccountNumber).size();
    }

    //AccountCloseSteps
    public void clickCloseAccount() {
        driver.findElement(closeAccountButton).click();
    }

    public void clickConfirmClose() {
        driver.findElement(confirmCloseButton).click();
    }

    //@balancePersistence
    public String getBalanceByAccountNumber(String accountNumber){
        By balanceLocator = By.xpath("//span[contains(text(),'" + accountNumber +
                "')]/ancestor::div[contains(@class,'acc-item')]//span[contains(@class,'acc-item-balance')]"
        );
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement balance = wait.until(ExpectedConditions.visibilityOfElementLocated(balanceLocator));

        return balance.getText();
    }

    public void storeUpdatedBalance(String accountNumber){
        updatedBalance = getBalanceByAccountNumber(accountNumber);
        System.out.println("Stored Balance : "+ updatedBalance);
    }

    public String getStoredBalance(){
        return updatedBalance;
    }

    public String getCurrentBalance(String accountNumber){
        return getBalanceByAccountNumber(
                accountNumber
        );
    }

    //closeAccountWithBalance
    public void closeAccount(String accountType) throws InterruptedException {
        openAccountCardByType(accountType);
        Thread.sleep(1000);
        clickCloseAccount();
        Thread.sleep(1000);
        clickConfirmClose();
    }

    //validAccountClosure

    public void openAccountCardByNumber(String accountNumber) {
        By accountCard = By.xpath("//span[contains(@class,'acc-item-number') and contains(text(),'" + accountNumber + "')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCard));

        driver.findElement(accountCard).click();
    }


    public void closeAccountByNumber(String accountNumber) throws InterruptedException {
        openAccountCardByNumber(accountNumber);
        Thread.sleep(2000);
        clickCloseAccount();
        Thread.sleep(2000);
        clickConfirmClose();
    }

}