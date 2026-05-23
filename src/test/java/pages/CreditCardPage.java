package pages;

import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

public class CreditCardPage {

    WebDriver driver;

    public CreditCardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ================= LOCATORS =================

    @FindBy(xpath = "//*[contains(text(),'Apply Card')]")
    WebElement applyCreditCardButton;

    @FindBy(xpath = "//div[@class='modal-content']")
    WebElement creditCardModal;

    @FindBy(xpath = "//select[@name='card_tier']")
    WebElement cardTierDropdown;

    @FindBy(xpath = "//select[@name='card_tier']/option")
    List<WebElement> cardTypeOptions;

    @FindBy(xpath = "//input[@name='requested_limit']")
    WebElement requestedLimitField;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    WebElement continueButton;

    @FindBy(xpath = "//*[contains(text(),'Please enter a requested credit limit')]")
    WebElement validationMessage;

    @FindBy(xpath = "//button[contains(text(),'Submit Application')]")
    WebElement submitApplicationButton;

    @FindBy(xpath = "//span[contains(text(),'Credit Cards')]")
    WebElement creditCardsMenu;

    @FindBy(xpath = "//div[@class='cc-card-front']")
    WebElement creditCard;

    @FindBy(xpath = "//button[contains(text(),'Close Card')]")
    WebElement closeCardButton;

    @FindBy(xpath = "//button[contains(text(),'Confirm')]")
    WebElement confirmCloseButton;

    @FindBy(xpath = "//button[contains(text(),'Delete Card')]")
    WebElement deleteCardButton;

    @FindBy(xpath = "//button[contains(text(),'Statement')]")
    WebElement viewStatementButton;

    @FindBy(xpath = "//h2[contains(text(),'Credit Card Statement')]")
    WebElement statementModal;

    @FindBy(xpath = "//select")
    WebElement transactionTypeFilter;

    @FindBy(xpath = "//*[contains(text(),'PURCHASE')]")
    List<WebElement> purchaseTransactions;

    @FindBy(xpath = "//span[contains(text(),'Transaction declined: Insufficient available credit limit')]")
    WebElement insufficientLimitMessage;



    // ================= METHODS =================
    public void clickCreditCardsMenu() {
        creditCardsMenu.click();
    }
    public void clickApplyCreditCardButton() {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.visibilityOf(applyCreditCardButton)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(applyCreditCardButton)
        );
        applyCreditCardButton.click();
    }

    public void verifyApplicationModalDisplayed() {
        Assert.assertTrue(creditCardModal.isDisplayed());
    }

    public void clickCardTierDropdown() {
        cardTierDropdown.click();
    }

    public void verifyCardTypeExists(String cardType) {

        boolean found = false;

        for (WebElement option : cardTypeOptions) {

            if (option.getText().trim().equalsIgnoreCase(cardType)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found, cardType + " not found in dropdown");
    }

    public void selectCardType(String cardType) {

//        cardTierDropdown.click();

        Select select =
                new Select(cardTierDropdown);

        select.selectByVisibleText(cardType);
    }


    public void verifySelectedCardType(String expectedType) {

        Select select =
                new Select(cardTierDropdown);

        String actual =
                select.getFirstSelectedOption().getText();

        Assert.assertEquals(actual.trim(), expectedType.trim());
    }

    public void enterRequestedLimit(String limit) {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        requestedLimitField
                )
        );

        requestedLimitField.click();

        requestedLimitField.clear();

        requestedLimitField.sendKeys(limit);
    }

    public void clickContinueButton() {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        );
        continueButton.click();
    }

    public void verifyValidationMessageDisplayed() {
        Assert.assertTrue(validationMessage.isDisplayed());
    }

    public void verifyContinueButtonDisabled() {
        Assert.assertFalse(continueButton.isEnabled());
    }

    public void clickSubmitApplicationButton() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(submitApplicationButton)
        );

        submitApplicationButton.click();
    }
    public void verifyRequestedLimitFieldIsEmpty() {

        String fieldValue =
                requestedLimitField.getAttribute("value");

        Assert.assertTrue(
                fieldValue.isEmpty(),
                "Field accepted invalid input"
        );
    }
    public void flipCreditCard() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.elementToBeClickable(creditCard)
        );

        creditCard.click();
    }

    public void closeCreditCardSuccessfully() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.elementToBeClickable(closeCardButton)
        );

        closeCardButton.click();

        wait.until(
                ExpectedConditions.elementToBeClickable(confirmCloseButton)
        );

        confirmCloseButton.click();
    }

    public void verifyDeleteCardButtonVisible() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.visibilityOf(deleteCardButton)
        );

        Assert.assertTrue(deleteCardButton.isDisplayed());
    }

    public void verifyProfileOrAccountDataMissingReason() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement reasonMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[contains(@class,'eligibility-reason') and contains(.,'Profile or account data missing')]")
                )
        );

        Assert.assertTrue(
                reasonMessage.isDisplayed(),
                "Profile or account data missing reason is not displayed"
        );
    }
    public void verifyRequestedCreditLimitValidationMessage(String expectedMessage) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(normalize-space(.),'" + expectedMessage + "')]")
                )
        );

        Assert.assertTrue(
                message.isDisplayed(),
                "Expected validation message not displayed: " + expectedMessage
        );
    }
    public void verifyMaximumEligibleLimitValidationMessage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[contains(@class,'eligibility-reason') and contains(.,'Requested amount exceeds your maximum eligible limit')]")
                )
        );

        Assert.assertTrue(
                message.isDisplayed(),
                "Maximum eligible limit validation message is not displayed"
        );
    }
    public void selectActiveCreditCard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement activeCard = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[contains(text(),'Active') or contains(text(),'ACTIVE')]/ancestor::div[contains(@class,'cc-card')]")
                )
        );

        activeCard.click();
    }

    public void selectLowLimitCreditCard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement card = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//div[contains(@class,'cc-card')])[1]")
                )
        );

        card.click();
    }

    public void selectInactiveCreditCard() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement inactiveCard = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//*[contains(text(),'CLOSED') or contains(text(),'BLOCKED')]/ancestor::div[contains(@class,'cc-card')])[last()]")
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", inactiveCard);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", inactiveCard);
    }

    public void performPurchaseTransaction(String amount) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement purchaseButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[@class='cc-btn cc-btn-primary']")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                purchaseButton
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                purchaseButton
        );

        WebElement amountField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@name='amount']")
                )
        );

        amountField.click();
        amountField.clear();
        amountField.sendKeys(amount);

        WebElement merchantNameField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@name='merchant']")
                )
        );

        merchantNameField.click();
        merchantNameField.clear();
        merchantNameField.sendKeys("Amazon");

        WebElement makePurchaseButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@type='submit']")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                makePurchaseButton
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                makePurchaseButton
        );
    }

    public void performInactiveCardPurchaseTransaction(String amount) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement purchaseButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[@class='cc-btn cc-btn-primary']")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                purchaseButton
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                purchaseButton
        );

        WebElement amountField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@name='amount']")
                )
        );

        amountField.click();
        amountField.clear();
        amountField.sendKeys(amount);

        WebElement merchantNameField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@name='merchant']")
                )
        );

        merchantNameField.click();
        merchantNameField.clear();
        merchantNameField.sendKeys("Amazon");

        WebElement makePurchaseButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@type='submit']")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                makePurchaseButton
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                makePurchaseButton
        );

        verifyInactiveCardValidationMessage();
    }

    public void verifyInsufficientLimitMessage() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(insufficientLimitMessage));

        Assert.assertTrue(
                insufficientLimitMessage.isDisplayed(),
                "Insufficient limit validation message is not displayed"
        );
    }

    public void verifyTransactionDeclined() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement declinedMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(.,'declined') or contains(.,'failed') or contains(.,'Insufficient')]")
                )
        );

        Assert.assertTrue(declinedMessage.isDisplayed());
    }

    public void verifyInactiveCardValidationMessage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By errorMessage = By.xpath(
                "//*[contains(text(),'Cannot process transaction') or contains(.,'Card is not active')]"
        );

        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        );

        System.out.println("Actual validation message: " + message.getText());

        Assert.assertTrue(
                message.getText().trim().contains("Cannot process transaction: Card is not active"),
                "Inactive card validation message is not displayed correctly"
        );
    }

    public void verifyTransactionNotProcessed() {
        verifyInactiveCardValidationMessage();
    }

    public void completeSuccessfulPurchaseTransaction() {
        selectActiveCreditCard();
        performPurchaseTransaction("1000");
    }

    public void verifyAvailableLimitUpdated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement limitText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(.,'Available') and contains(.,'Limit')]")
                )
        );

        Assert.assertTrue(limitText.isDisplayed());
    }
}