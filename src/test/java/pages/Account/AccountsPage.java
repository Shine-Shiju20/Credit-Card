package pages.Account;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerUtility;
import utils.TestDataStore;

public class AccountsPage {

    WebDriver driver;

    String selectedAccountNumber;
    // Constructor
    public AccountsPage(WebDriver driver) {
        this.driver = driver;
    }
    String updatedBalance;
    // Locators

    By accountTypeDropdown = By.xpath("//select[@name='account_type']");
    By depositField = By.xpath("//input[@name='initial_deposit']");
    By createAccountSubmitButton = By.xpath("//button[@class='ca-btn-submit']");
    By openAccount =
            By.xpath(
                    "//button[normalize-space()='Open New Account']"
            );
    By loginOverlay = By.xpath("//div[contains(@class,'login-modal-overlay')]");
    By toastMsg = By.xpath("//span[contains(@class,'toast-message')]");
    By validationMsg = By.xpath("//div[@class='alert alert-danger']");
    By LogoutBtn = By.xpath("//button[@class='nav-btn-logout']");
    By AccountNumber = By.xpath("//span[@class='acc-item-number']");
    By LastaccountNumber = By.xpath("(//span[contains(@class,'acc-item-number')])[last()]");
    By accountNumberLocator = By.xpath("//span[contains(@class,'acc-item-number')]");
    //button[@class='btn btn-secondary btn-sm']
    By changeTypeButton = By.xpath("//button[contains(text(),'Change Type')]");
    By updateDropdown = By.xpath("//select[@class='input-field']");
    By saveButton = By.xpath("//button[contains(text(),'Save')]");
    By closeAccountButton = By.xpath("//button[@class='btn btn-danger btn-sm']");
    By confirmCloseButton = By.xpath("//button[contains(.,'Yes, Close Account')]");

    // Actions

    public void clickOpenAccount(){

        WebDriverWait wait=
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );

        wait.until(

                ExpectedConditions
                        .elementToBeClickable(
                                openAccount
                        )
        ).click();

        LoggerUtility.info(
                "Open New Account Clicked"
        );
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

    public void closeCreateAccountPopup(){

        driver.findElement(

                By.xpath(
                        "//button[contains(text(),'Cancel')]"
                )

        ).click();
    }
    public String getCreatedAccountNumber(){
        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        wait.until(
                ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(

                                By.xpath(
                                        "//span[contains(@class,'acc-item-number')]"
                                )
                        )
        );

        List<WebElement> accounts =
                driver.findElements(

                        By.xpath(
                                "//span[contains(@class,'acc-item-number')]"
                        )
                );

        String latestAccount = "";

        for(WebElement account : accounts){

            String accountNo =
                    account
                            .getText()
                            .trim();

            latestAccount =
                    accountNo;
        }

        LoggerUtility.info(
                "Created Account Number : "
                        + latestAccount
        );

        return latestAccount;
    }

    public String getToastMessage(){

        WebDriverWait wait=
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(5)
                );

        WebElement toast=
                wait.until(

                        ExpectedConditions
                                .presenceOfElementLocated(
                                        toastMsg
                                )
                );

        return toast.getAttribute(
                "textContent"
        ).trim();
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

    public void clickLogout(){

        List<WebElement> logoutButtons=
                driver.findElements(
                        LogoutBtn
                );

        if(!logoutButtons.isEmpty()){

            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10)
            ).until(

                    ExpectedConditions
                            .elementToBeClickable(
                                    LogoutBtn
                            )
            ).click();

            LoggerUtility.info(
                    "Logout Completed"
            );
        }
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
        return selectedAccountNumber;
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

    public void openAccountCardByType(
            String accountType){

        By accountCards=
                By.xpath(
                        "//div[contains(@class,'acc-item ')]"
                );

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(

                ExpectedConditions
                        .numberOfElementsToBeMoreThan(
                                accountCards,
                                0
                        )
        );

        List<WebElement> cards=
                driver.findElements(
                        accountCards
                );

        for(WebElement card : cards){

            String type=
                    card.findElement(
                                    By.xpath(
                                            ".//span[contains(@class,'acc-item-type')]"
                                    )
                            )
                            .getText()
                            .toLowerCase()
                            .replace(
                                    " account",
                                    ""
                            )
                            .trim();


            if(type.equals(
                    accountType.toLowerCase()
            )){

                selectedAccountNumber=
                        card.findElement(
                                        By.xpath(
                                                ".//span[contains(@class,'acc-item-number')]"
                                        )
                                )
                                .getText()
                                .trim();

                LoggerUtility.info(
                        "Selected Account Number : "
                                + selectedAccountNumber
                );

                card.click();

                return;
            }
        }

        throw new RuntimeException(
                accountType
                        + " account not found"
        );
    }

    public void updateAccountType(
            String existingType,
            String newType){

        if(
                !TestDataStore.createdAccounts.isEmpty()
        ){

            String accountNo=
                    TestDataStore.createdAccounts
                            .keySet()
                            .iterator()
                            .next();

            openAccountCardByNumber(
                    accountNo
            );
        }
        else{

            openAccountCardByType(
                    existingType
            );
        }

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(

                ExpectedConditions
                        .elementToBeClickable(
                                changeTypeButton
                        )
        );

        clickChangeType();

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(

                ExpectedConditions
                        .visibilityOfElementLocated(
                                updateDropdown
                        )
        );

        selectUpdatedAccountType(
                newType
        );

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
    public void clickCloseAccount(){

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement closeButton= wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'btn-danger')]"))
                );

        closeButton.click();

        LoggerUtility.info("Close Account Clicked");
    }

    public void clickConfirmClose() {
        driver.findElement(confirmCloseButton).click();
    }

    //@balancePersistence
    public String getBalanceByAccountNumber(
            String accountNumber
    ) {

        By balanceLocator =
                By.xpath(
                        "//span[contains(text(),'"
                                + accountNumber +
                                "')]/ancestor::div[contains(@class,'acc-item')]//span[contains(@class,'acc-item-balance')]"
                );

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        return wait.until(driver -> {

            try {

                WebElement balanceElement =
                        driver.findElement(balanceLocator);

                String balance =
                        balanceElement
                                .getText()
                                .trim();

                if(!balance.isEmpty())
                        {

                    LoggerUtility.info(
                            "Fetched Balance : "
                                    + balance
                    );

                    return balance;
                }

                return null;

            } catch (Exception e) {

                return null;
            }
        });
    }

    public void storeUpdatedBalance(String accountNumber){
        updatedBalance = getBalanceByAccountNumber(accountNumber);
        LoggerUtility.info("Stored Balance : "+ updatedBalance);
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
    public void closeAccount(String accountType){
        if(
                !TestDataStore.createdAccounts.isEmpty()
        ){

            String accountNo=
                    TestDataStore.createdAccounts
                            .keySet()
                            .iterator()
                            .next();

            openAccountCardByNumber(
                    accountNo
            );
        }
        else{

            openAccountCardByType(
                    accountType
            );
        }
        clickCloseAccount();
        clickConfirmClose();
    }

    //validAccountClosure

    public void openAccountCardByNumber(String accountNumber) {
        By accountCard = By.xpath("//span[contains(@class,'acc-item-number') and contains(text(),'" + accountNumber + "')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCard));

        LoggerUtility.info(
                "Selected Account Number : "
                        + accountNumber
        );


        driver.findElement(accountCard).click();
    }


    public void closeAccountByNumber(
            String accountNumber){

        openAccountCardByNumber(
                accountNumber
        );

        clickCloseAccount();

        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        ).until(

                ExpectedConditions
                        .elementToBeClickable(
                                confirmCloseButton
                        )
        );

        clickConfirmClose();
    }
    public String getCreatedAccountNumberForRevert(){

        try{
            new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10)
            ).until(

                    driver->{

                        List<String> current=
                                getAllAccountNumbers();

                        return current.size()>
                                TestDataStore
                                        .existingAccounts
                                        .size();
                    }
            );
        }
        catch(Exception e){
            e.printStackTrace();
        }

        List<String> currentAccounts =
                getAllAccountNumbers();

        for(String accountNo : currentAccounts){

            if(!TestDataStore
                    .existingAccounts
                    .contains(accountNo)){

                LoggerUtility.info(
                        "Actual New Account : "
                                + accountNo
                );

                return accountNo;
            }
        }

        throw new RuntimeException(
                "New account not found"
        );
    }


}