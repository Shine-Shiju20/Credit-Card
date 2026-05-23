package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {

        this.driver = driver;
    }

    // LOCATORS

    By homeRegisterButton =
            By.xpath("//div[@class='nav-menu']//button[@class='nav-btn-primary']");

    By emailField =
            By.xpath("//div[@class='input-group']//label[contains(text(),'Email Address')]/following-sibling::input");

    By phoneField =
            By.xpath("//div[@class='input-group']//label[contains(text(),'Phone Number')]/following-sibling::input");

    By passwordField =
            By.xpath("//input[@placeholder='Min 8 chars, mixed']");

    By confirmPasswordField =
            By.xpath("//div[@class='input-group']//label[contains(text(),'Confirm Password')]/following-sibling::input");

    By transactionPinField =
            By.xpath("//div[@class='input-group']//label[contains(text(),'Transaction PIN')]/following-sibling::input");

    By nextButton =
            By.xpath("//div[@class='modal-actions']//button[@type='button']");

    By validationMessage =
            By.xpath("//div[contains(@class,'error')]");

    // KYC LOCATORS

    By fullNameField =
            By.xpath("//div[@class='input-group']//label[contains(text(),' Full Name (as per Aadhaar)')]/following-sibling::input");

    By dobField =
            By.xpath("//div[@class='input-group']//label[contains(text(),' Date of Birth')]/following-sibling::input");

    By genderDropdown =
            By.xpath("//div[@class='input-group']//label[contains(text(),'Gender')]/following-sibling::select");

    By addressField =
            By.xpath("//div[@class='input-group']//label[contains(text(),' Address')]/following-sibling::*");

    By aadhaarField =
            By.xpath("//div[@class='input-group']//label[contains(text(),' Aadhaar Number')]/following-sibling::input");

    By panField =
            By.xpath("//div[@class='input-group']//label[contains(text(),' PAN Number')]/following-sibling::input");

    By annualIncomeField =
            By.xpath("//div[@class='input-group']//label[contains(text(),' Annual Income (₹)')]/following-sibling::input");

    By occupationField =
            By.xpath("//div[@class='input-group']//label[contains(text(),' Occupation')]/following-sibling::input");

    By submitKycButton =
            By.xpath("//div[@class='modal-actions']//button[@class='btn btn-primary btn-lg']");

    // COMMON WAIT


    public WebDriverWait getWait() {

        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // CLICK HOME REGISTER BUTTON

    public void clickHomeRegisterButton() {

        getWait().until(ExpectedConditions.elementToBeClickable(homeRegisterButton));
        driver.findElement(homeRegisterButton).click();
    }

    // ENTER EMAIL

    public void enterEmail(String email) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    // ENTER PHONE NUMBER

    public void enterPhoneNumber(String phone) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(phoneField));
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phone);
    }

    // ENTER PASSWORD

    public void enterPassword(String password) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    // ENTER CONFIRM PASSWORD

    public void enterConfirmPassword(String confirmPassword) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        driver.findElement(confirmPasswordField).clear();
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    // ENTER TRANSACTION PIN

    public void enterTransactionPin(String pin) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(transactionPinField));
        driver.findElement(transactionPinField).clear();
        driver.findElement(transactionPinField).sendKeys(pin);
    }

    // CLICK NEXT BUTTON

    public void clickNextButton() {

        getWait().until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }


    // ENTER FULL NAME

    public void enterFullName(String fullName) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(fullNameField));
        driver.findElement(fullNameField).clear();
        driver.findElement(fullNameField).sendKeys(fullName);
    }


    // ENTER DOB

    public void enterDOB(String dob) {

        getWait().until(ExpectedConditions.visibilityOfElementLocated(dobField));
        driver.findElement(dobField).clear();
        driver.findElement(dobField).sendKeys(dob);
    }

    // SELECT GENDER

    public void selectGender(String gender) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(genderDropdown));
        driver.findElement(genderDropdown).sendKeys(gender);
    }

    // ENTER ADDRESS

    public void enterAddress(String address) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(addressField));
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
    }

    // ENTER AADHAAR

    public void enterAadhaar(String aadhaar) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(aadhaarField));
        driver.findElement(aadhaarField).clear();
        driver.findElement(aadhaarField).sendKeys(aadhaar);
    }

    // ENTER PAN

    public void enterPAN(String pan) {

        getWait().until(ExpectedConditions.visibilityOfElementLocated(panField));
        driver.findElement(panField).clear();
        driver.findElement(panField).sendKeys(pan);
    }

    // ENTER ANNUAL INCOME

    public void enterAnnualIncome(String income) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(annualIncomeField));
        driver.findElement(annualIncomeField).clear();
        driver.findElement(annualIncomeField).sendKeys(income);
    }

    // ENTER OCCUPATION

    public void enterOccupation(String occupation) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(occupationField));
        driver.findElement(occupationField).clear();
        driver.findElement(occupationField).sendKeys(occupation);
    }

    // CLICK SUBMIT KYC BUTTON

    public void clickSubmitKYCButton() {
        getWait().until(ExpectedConditions.elementToBeClickable(submitKycButton));
        driver.findElement(submitKycButton).click();
    }

    // GET VALIDATION MESSAGE

    public String getValidationMessage() {
        WebElement message = getWait().until(ExpectedConditions.visibilityOfElementLocated(validationMessage));
        return message.getText();
    }

    // GET EMAIL FIELD VALUE

    public String getEmailFieldValue() {
        return driver.findElement(emailField).getAttribute("value");
    }

    // GET PHONE FIELD VALUE

    public String getPhoneFieldValue() {
        return driver.findElement(phoneField).getAttribute("value");
    }

    // GET PASSWORD FIELD TYPE

    public String getPasswordFieldType() {
        return driver.findElement(passwordField).getAttribute("type");
    }

    // GET CONFIRM PASSWORD FIELD TYPE

    public String getConfirmPasswordFieldType() {
        return driver.findElement(confirmPasswordField).getAttribute("type");
    }

    // GET PIN FIELD VALUE

    public String getPinFieldValue() {
        return driver.findElement(transactionPinField).getAttribute("value");
    }

    // GET PAN FIELD VALUE

    public String getPanFieldValue() {
        return driver.findElement(panField).getAttribute("value");
    }


    // GET AADHAAR FIELD VALUE

    public String getAadhaarFieldValue() {
        return driver.findElement(aadhaarField).getAttribute("value");
    }


    // GET FULL NAME FIELD VALUE

    public String getFullNameFieldValue() {
        return driver.findElement(fullNameField).getAttribute("value");
    }

    // GET OCCUPATION FIELD VALUE

    public String getOccupationFieldValue() {
        return driver.findElement(occupationField).getAttribute("value");
    }

    // GET BROWSER VALIDATION MESSAGE

    public String getBrowserValidationMessage(By locator) {
        return driver.findElement(locator).getAttribute("validationMessage");
    }
}