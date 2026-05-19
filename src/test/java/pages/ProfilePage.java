package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    WebDriver Driver;
    WebDriverWait wait ;

    By ProfileEditButton = new By.ByXPath("//button[contains(text(),\"Edit\")]");
    By ProfileSaveButton = new By.ByXPath("(//div[@class=\"profile-section\"])[1]//button[contains(text(),'Save')]");
    By ProfileCancelButton = new By.ByXPath("(//div[@class=\"profile-section\"])[1]//button[contains(text(),'Cancel')]");
    By ForgotTransactionPinButton = new By.ByXPath("//button[contains(text(),\"Forgot Transaction PIN?\")]");
    By ForgotTransactionCancelButton = new  By.ByXPath("//form[contains(@class,\"pin-reset-form\")]//button[contains(text(), \"Cancel\")]");
    By ForgotTransactionResetPinButton = new By.ByXPath("//form[contains(@class,\"pin-reset-form\")]//button[contains(text(), \"Reset PIN\")]");

    By CancelKYCButton = new By.ByXPath("(//div[@class=\"profile-section\"])[2]//button[contains(text(),'Cancel')]");
    By SaveKYCButton = new By.ByXPath("(//div[@class=\"profile-section\"])[2]//button[contains(text(),'Save KYC')]");
    By EditKycButton = new By.ByXPath("//button[@title=\"Update KYC information\"]");

    By NameField = new By.ByXPath("//div[@class=\"input-group\"]//label[contains(text(),'Full Name' )]/following-sibling::*");
    By PhoneField = new By.ByXPath("//div[@class=\"input-group\"]//label[contains(text(),'Phone' )]/following-sibling::*");
    By OccupationField = new By.ByXPath("//div[@class=\"input-group\"]//label[contains(text(),'Occupation' )]/following-sibling::*");
    By AnnualIncomeField = new By.ByXPath("//div[@class=\"input-group\"]//label[contains(text(),'Annual Income' )]/following-sibling::*");
    By AddressField = new By.ByXPath("//div[@class=\"input-group full-width\"]//label[contains(text(),' Address' )]/following-sibling::*");
    By EmailField = new By.ByXPath("//div[@class=\"input-group\"]//label[contains(text(),' Email' )]/following-sibling::input");


    By AadhaarField = new By.ByXPath("//div[@class=\"input-group\"]//label[contains(text(),' Aadhaar Number' )]/following-sibling::*");
    By PanField = new By.ByXPath("//div[@class=\"input-group\"]//label[contains(text(),' PAN Number' )]/following-sibling::*");
    By DateOfBirthField = new By.ByXPath("//div[@class=\"input-group\"]//label[contains(text(),' Date of Birth' )]/following-sibling::*");
    By GenderField = new By.ByXPath("//div[@class=\"input-group\"]//label[contains(text(),' Gender' )]/following-sibling::*");


    public ProfilePage(WebDriver driver){
        this.Driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void SetDriver(WebDriver Driver){
        this.Driver = Driver;
    }

    public WebDriver getDriver(){
        return Driver;
    }


    //profile Section
    public void setOccupationField(String Data){
        this.wait.until(ExpectedConditions.presenceOfElementLocated(OccupationField));
        WebElement OccupationElement = this.Driver.findElement(this.OccupationField);
        OccupationElement.sendKeys(Data);
    }

    public Boolean CheckEmailVisibility(){
        WebElement EmailElement  =this.Driver.findElement(this.EmailField);
        return EmailElement.isEnabled();
    }

    public void setPhoneField(String data){
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.PhoneField));
        WebElement PhoneElement = this.Driver.findElement(this.PhoneField);
        PhoneElement.sendKeys(data);
    }

    public void SetNameField(String data) throws InterruptedException {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.NameField));
        WebElement NameElement = this.Driver.findElement(this.NameField);
        Actions actions = new Actions(this.Driver);
        actions.moveToElement(NameElement).perform();
        this.wait.until(ExpectedConditions.elementToBeClickable(this.NameField));
        NameElement.sendKeys(data);
        Thread.sleep(1000);
    }

    public void SetAddressField(String data){
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.AddressField));
        WebElement AddressElement = this.Driver.findElement(this.AddressField);
        AddressElement.sendKeys(data);
    }
    public void SetAnnualIncomeField(String data){
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.AnnualIncomeField));
        WebElement AnnualElement = this.Driver.findElement(this.AnnualIncomeField);
        AnnualElement.sendKeys(data);
    }


    public void ClickEditProfileButton(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.ProfileEditButton));
        WebElement EditProfileElement = this.Driver.findElement(this.ProfileEditButton);
        EditProfileElement.click();
    }

    public void ClickCancelProfileButton() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.ProfileCancelButton));
        WebElement CancelProfileElement = this.Driver.findElement(this.ProfileCancelButton);
        CancelProfileElement.click();
    }

    public void ClickSaveProfileButton(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.ProfileSaveButton));
        WebElement SaveProfileButton  = this.Driver.findElement(this.ProfileSaveButton);
        SaveProfileButton.click();
    }

    public void ClickEditKYCButton(){
        this.wait.until(
                ExpectedConditions.elementToBeClickable(this.EditKycButton)
        );
        WebElement element =
                this.Driver.findElement(this.EditKycButton);

        element.click();
    }


    public void ClickSaveKYCButton(){

        this.wait.until(
                ExpectedConditions.elementToBeClickable(this.SaveKYCButton)
        );

        WebElement element =
                this.Driver.findElement(this.SaveKYCButton);

        element.click();
    }


    public void ClickCancelKYCButton(){

        this.wait.until(
                ExpectedConditions.elementToBeClickable(this.CancelKYCButton)
        );

        WebElement element =
                this.Driver.findElement(this.CancelKYCButton);

        element.click();
    }

    public void ClearOccupationField(){

        WebElement NameElement = this.Driver.findElement(this.OccupationField);
        NameElement.click();
        NameElement.sendKeys(
                Keys.CONTROL + "a"
        );
        NameElement.sendKeys(
                Keys.DELETE
        );
    }



    public void ClearPhoneField(){
        WebElement NameElement = this.Driver.findElement(this.PhoneField);
        NameElement.click();
        NameElement.sendKeys(
                Keys.CONTROL + "a"
        );
        NameElement.sendKeys(
                Keys.DELETE
        );
    }

    public void ClearNameField(){
        WebElement NameElement = this.Driver.findElement(this.NameField);
        NameElement.click();
        NameElement.sendKeys(
                Keys.CONTROL + "a"
        );
        NameElement.sendKeys(
                Keys.DELETE
        );
    }

    public String getToastMessage() {

        WebDriverWait wait =
                new WebDriverWait(this.Driver, Duration.ofSeconds(10));

        WebElement toast =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath(
                                        "//span[contains(@class,'toast-message')]"
                                )
                        )
                );

        return toast.getText();
    }

    public void ClearAnnualIncomeField(){
        WebElement NameElement = this.Driver.findElement(this.AnnualIncomeField);
        NameElement.click();
        NameElement.sendKeys(
                Keys.CONTROL + "a"
        );
        NameElement.sendKeys(
                Keys.DELETE
        );
    }

    public void ClearAddressField(){
        WebElement NameElement = this.Driver.findElement(this.AddressField);
        NameElement.click();
        NameElement.sendKeys(
                Keys.CONTROL + "a"
        );
        NameElement.sendKeys(
                Keys.DELETE
        );
    }

    public void ClearAadhaarField(){
        WebElement NameElement = this.Driver.findElement(this.AadhaarField);
        NameElement.click();
        NameElement.sendKeys(
                Keys.CONTROL + "a"
        );
        NameElement.sendKeys(
                Keys.DELETE
        );
    }

    public void ClearPanField(){
        WebElement NameElement = this.Driver.findElement(this.PanField);
        NameElement.click();
        NameElement.sendKeys(
                Keys.CONTROL + "a"
        );
        NameElement.sendKeys(
                Keys.DELETE
        );
    }



    public void WaitTillProfileVisibility(){
        WebDriverWait wait = new WebDriverWait(this.Driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(NameField));

        wait.until(ExpectedConditions.visibilityOfElementLocated(PhoneField));

        wait.until(ExpectedConditions.visibilityOfElementLocated(OccupationField));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AnnualIncomeField));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AddressField));

        wait.until(ExpectedConditions.visibilityOfElementLocated(EmailField));

        wait.until(ExpectedConditions.visibilityOfElementLocated(AadhaarField));

        wait.until(ExpectedConditions.visibilityOfElementLocated(PanField));

        wait.until(ExpectedConditions.visibilityOfElementLocated(DateOfBirthField));

        wait.until(ExpectedConditions.visibilityOfElementLocated(GenderField));
    }


    public String getNameFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.NameField));

        WebElement element = this.Driver.findElement(this.NameField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    public String getPhoneFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.PhoneField));

        WebElement element = this.Driver.findElement(this.PhoneField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    public String getOccupationFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.OccupationField));

        WebElement element = this.Driver.findElement(this.OccupationField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    public String getAnnualIncomeFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.AnnualIncomeField));

        WebElement element = this.Driver.findElement(this.AnnualIncomeField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    public String getAddressFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.AddressField));

        WebElement element = this.Driver.findElement(this.AddressField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    public String getEmailFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.EmailField));

        WebElement element = this.Driver.findElement(this.EmailField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    public String getAadhaarFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.AadhaarField));

        WebElement element = this.Driver.findElement(this.AadhaarField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    public String getPanFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.PanField));

        WebElement element = this.Driver.findElement(this.PanField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    public String getDateOfBirthFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.DateOfBirthField));

        WebElement element = this.Driver.findElement(this.DateOfBirthField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    public String getGenderFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.GenderField));

        WebElement element = this.Driver.findElement(this.GenderField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    public void SetPanField(String pan){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.PanField)
        );

        WebElement element =
                this.Driver.findElement(this.PanField);

        element.clear();

        element.sendKeys(pan);
    }

    public void SetAadhaarField(String pan){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.AadhaarField)
        );
        WebElement element =
                this.Driver.findElement(this.AadhaarField);
        element.clear();
        element.sendKeys(pan);
    }


    public Boolean IsDateOfBirthFieldEnabled(){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.DateOfBirthField)
        );

        WebElement element =
                this.Driver.findElement(this.DateOfBirthField);

        return element.isEnabled();
    }


    public Boolean IsGenderFieldEnabled(){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.GenderField)
        );

        WebElement element =
                this.Driver.findElement(this.GenderField);

        return element.isEnabled();
    }

}
