package pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By HomeLoginbutton = By.xpath("//div[@class='app']//button[@class='nav-btn-outline']");
    By validationMsg = By.xpath("//div[@class='error-alert']");
    By firstLoginButton = By.xpath("//div[@class='app']//button[@class='nav-btn-outline']");

    By emailField = By.xpath("//div[@class='input-group']//label[contains(text(),'Email Address')]/following-sibling::input");

    By passwordField = By.xpath("//div[@class='input-group']//label[text()='Password']/following-sibling::*/input");

    By loginButton = By.xpath("//button[@class='login-button']");

    By forgetPassword = By .xpath("//div[@class='login-secondary-actions']//button[@type='button']");

    By loginOverlay =
            By.xpath("//div[contains(@class,'login-modal-overlay')]");

    // Login actions
    public void clickFirstLogin() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions
                .elementToBeClickable(firstLoginButton));

        driver.findElement(firstLoginButton).click();
    }

    public void enterEmail(String email) {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions
                .elementToBeClickable(emailField));
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions
                .elementToBeClickable(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions
                .elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    public void login(String email, String password) {

        clickFirstLogin();

        enterEmail(email);

        enterPassword(password);

        clickLogin();
    }

    public void clickForgetPassword() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(forgetPassword));

        driver.findElement(forgetPassword).click();
    }
    public String getValidationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(validationMsg));

        return message.getText();
    }
    public String getEmailFieldValue() {

        return driver.findElement(emailField)
                .getAttribute("value");
    }
    public String getEmailFieldValidationMessage() {

        return driver.findElement(emailField)
                .getAttribute("validationMessage");
    }

    public void clickHomeLoginButton() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(HomeLoginbutton));

        wait.until(ExpectedConditions
                .elementToBeClickable(HomeLoginbutton));

        driver.findElement(HomeLoginbutton).click();
    }

}