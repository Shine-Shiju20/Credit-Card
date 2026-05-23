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

    //button Locators
    By ProfileEditButton = new By.ByXPath("//button[contains(text(),\"Edit\")]");
    By ProfileSaveButton = new By.ByXPath("(//div[@class=\"profile-section\"])[1]//button[contains(text(),'Save')]");
    By ProfileCancelButton = new By.ByXPath("(//div[@class=\"profile-section\"])[1]//button[contains(text(),'Cancel')]");
    By ForgotTransactionPinButton = new By.ByXPath("//button[contains(text(),\"Forgot Transaction PIN?\")]");
    By ForgotTransactionCancelButton = new  By.ByXPath("//form[contains(@class,\"pin-reset-form\")]//button[contains(text(), \"Cancel\")]");
    By ForgotTransactionResetPinButton = new By.ByXPath("//form[contains(@class,\"pin-reset-form\")]//button[contains(text(), \"Reset PIN\")]");
    By CancelKYCButton = new By.ByXPath("(//div[@class=\"profile-section\"])[2]//button[contains(text(),'Cancel')]");
    By SaveKYCButton = new By.ByXPath("(//div[@class=\"profile-section\"])[2]//button[contains(text(),'Save KYC')]");
    By EditKycButton = new By.ByXPath("//button[@title=\"Update KYC information\"]");
    By KYCDisabledButton = new By.ByXPath("//button[text()=\" Update KYC\"]");
    //field Locators
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
    By TransactionPasswordField = new By.ByXPath("//div[@class=\"input-group\"]//label[text()=\"Account Password\"]/following-sibling::input");
    By TransactionResetField = new By.ByXPath("//div[@class=\"input-group\"]//label[text()=\"New Transaction PIN\"]/following-sibling::input");
    By TransactionResetConfirmField = new By.ByXPath("//div[@class=\"input-group\"]//label[text()=\"Confirm New PIN\"]/following-sibling::input");

    //constructor Initialize Driver and wait
    public ProfilePage(WebDriver driver){
        this.Driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //Setter For Driver
    public void SetDriver(WebDriver Driver)
    {
        this.Driver = Driver;

    }

    //Getter for Driver
    public WebDriver getDriver(){
        return Driver;

    }

    //profile Section
    //Sets Occupation Field with the given Data
    public void setOccupationField(String Data){
        this.wait.until(ExpectedConditions.presenceOfElementLocated(OccupationField));
        WebElement OccupationElement = this.Driver.findElement(this.OccupationField);
        OccupationElement.sendKeys(Data);
    }

    //Checks if the Email Field is Enabled or not
    public Boolean CheckEmailVisibility(){
        WebElement EmailElement  =this.Driver.findElement(this.EmailField);
        return EmailElement.isEnabled();
    }

    //Sets Phone field in Form with given Data
    public void setPhoneField(String data){
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.PhoneField));
        WebElement PhoneElement = this.Driver.findElement(this.PhoneField);
        PhoneElement.sendKeys(data);
    }

    //sets Name field in form with given Data also moves to the Name field element using Actions
    public void SetNameField(String data) throws InterruptedException {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.NameField));
        WebElement NameElement = this.Driver.findElement(this.NameField);
        Actions actions = new Actions(this.Driver);
        actions.moveToElement(NameElement).perform();
        this.wait.until(ExpectedConditions.elementToBeClickable(this.NameField));
        NameElement.sendKeys(data);
        Thread.sleep(1000);
    }

    //sets Address field in form With the given data
    public void SetAddressField(String data){
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.AddressField));
        WebElement AddressElement = this.Driver.findElement(this.AddressField);
        AddressElement.sendKeys(data);
    }

    //sets the Annual Income field in form with Given Data
    public void SetAnnualIncomeField(String data){
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.AnnualIncomeField));
        WebElement AnnualElement = this.Driver.findElement(this.AnnualIncomeField);
        AnnualElement.sendKeys(data);
    }

    //Clicks Profile Edit button
    public void ClickEditProfileButton(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.ProfileEditButton));
        WebElement EditProfileElement = this.Driver.findElement(this.ProfileEditButton);
        EditProfileElement.click();
    }

    //clicks the Cancel profile button
    public void ClickCancelProfileButton() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.ProfileCancelButton));
        WebElement CancelProfileElement = this.Driver.findElement(this.ProfileCancelButton);
        JavascriptExecutor js = (JavascriptExecutor) Driver;

        // Scroll element to center of screen
        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                CancelProfileElement
        );
        CancelProfileElement.click();
    }

    //clicks Save Profile button
    public void ClickSaveProfileButton(){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.ProfileSaveButton));
        WebElement SaveProfileButton  = this.Driver.findElement(this.ProfileSaveButton);
        JavascriptExecutor js = (JavascriptExecutor) Driver;

        // Scroll element to center of screen
        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                SaveProfileButton
        );
        SaveProfileButton.click();
    }

    //clicks Edit KYC button
    public void ClickEditKYCButton(){
        this.wait.until(
                ExpectedConditions.elementToBeClickable(this.EditKycButton)
        );
        WebElement element =
                this.Driver.findElement(this.EditKycButton);

        element.click();
    }


    //clicks Edit KYC button
    public void ClickSaveKYCButton(){

        this.wait.until(
                ExpectedConditions.elementToBeClickable(this.SaveKYCButton)
        );

        WebElement element =
                this.Driver.findElement(this.SaveKYCButton);

        element.click();
    }

    //Clears Occupation field (uses control A + Delete to delete because of React XSS Attack protection)
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


    //Clears Phone field (uses control A + Delete to delete because of React XSS Attack protection)
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
    //Clears Name field d (uses control A + Delete to delete because of React XSS Attack protection)
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

    //Gets the error Message from the Toast
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

    //Clears Annual field (uses control A + Delete to delete because of React XSS Attack protection)
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
    //Clears Address field (uses control A + Delete to delete because of React XSS Attack protection)
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

    ////Clears Occupation field (uses control A + Delete to delete because of React XSS Attack protection)
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
    //Clears Pan field (uses control A + Delete to delete because of React XSS Attack protection)
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


 // waits for all the fields of Profil Edit section to be visible
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

    //Gets the Current value present in the field and returns it
    public String getNameFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.NameField));

        WebElement element = this.Driver.findElement(this.NameField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    //Gets the Phone Field value and returns it
    public String getPhoneFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.PhoneField));

        WebElement element = this.Driver.findElement(this.PhoneField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    //gets the Occupation Field Value and returns it
    public String getOccupationFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.OccupationField));

        WebElement element = this.Driver.findElement(this.OccupationField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    //gets the Annual Income field value and returns it
    public String getAnnualIncomeFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.AnnualIncomeField));

        WebElement element = this.Driver.findElement(this.AnnualIncomeField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    //gets the Address field value and returns it
    public String getAddressFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.AddressField));

        WebElement element = this.Driver.findElement(this.AddressField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    //gets the Aadhaar Field value and returns it
    public String getAadhaarFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.AadhaarField));

        WebElement element = this.Driver.findElement(this.AadhaarField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    //gets the Pan field Value and returns it
    public String getPanFieldText(){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.PanField));

        WebElement element = this.Driver.findElement(this.PanField);

        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.getAttribute("value");
    }

    //Sets the pan field with given data
    public void SetPanField(String pan){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.PanField)
        );

        WebElement element =
                this.Driver.findElement(this.PanField);

        element.clear();

        element.sendKeys(pan);
    }

    //sets the Aadhaar field with given data
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
        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.isEnabled();
    }

    public Boolean IsPanFieldEnabled(){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.PanField)
        );

        WebElement element =
                this.Driver.findElement(this.PanField);
        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.isEnabled();
    }

    public Boolean IsAadhaarFieldEnabled(){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.AadhaarField)
        );

        WebElement element =
                this.Driver.findElement(this.AadhaarField);
        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.isEnabled();
    }

    public boolean IsUpdateKYCButtonEnabled(){
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.KYCDisabledButton));
        WebElement element = this.Driver.findElement(this.KYCDisabledButton);
        new Actions(this.Driver).moveToElement(element).perform();
        return element.isEnabled();
    }
    public Boolean IsGenderFieldEnabled(){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.GenderField)
        );

        WebElement element =
                this.Driver.findElement(this.GenderField);
        new Actions(this.Driver)
                .moveToElement(element)
                .perform();

        return element.isEnabled();
    }






    public void SetTransactionPasswordField(String password){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        this.TransactionPasswordField
                )
        );

        WebElement element =
                this.Driver.findElement(
                        this.TransactionPasswordField
                );

        element.sendKeys(password);
    }






// =========================
// NEW TRANSACTION PIN FIELD
// =========================




    public void SetTransactionResetField(String pin){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        this.TransactionResetField
                )
        );

        WebElement element =
                this.Driver.findElement(
                        this.TransactionResetField
                );

        element.sendKeys(pin);
    }







// CONFIRM TRANSACTION PIN FIELD




    public void SetTransactionResetConfirmField(String pin){

        this.wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        this.TransactionResetConfirmField
                )
        );

        WebElement element =
                this.Driver.findElement(
                        this.TransactionResetConfirmField
                );

        element.sendKeys(pin);
    }


    public void ClickForgotTransactionPinButton(){

        this.wait.until(
                ExpectedConditions.elementToBeClickable(
                        this.ForgotTransactionPinButton
                )
        );

        WebElement element =
                this.Driver.findElement(
                        this.ForgotTransactionPinButton
                );

        element.click();
    }







    public void ClickForgotTransactionResetPinButton(){

        this.wait.until(
                ExpectedConditions.elementToBeClickable(
                        this.ForgotTransactionResetPinButton
                )
        );

        WebElement element =
                this.Driver.findElement(
                        this.ForgotTransactionResetPinButton
                );

        element.click();
    }

    public String GetTransactionPasswordValidationMessage(){

        WebElement element =
                this.Driver.findElement(
                        this.TransactionPasswordField
                );

        return element.getAttribute("validationMessage");
    }

    public String GetTransactionResetFieldValidationMessage(){

        WebElement element =
                this.Driver.findElement(
                        this.TransactionResetField
                );

        return element.getAttribute("validationMessage");
    }

    public String GetTransactionResetConfirmFieldValidationMessage(){

        WebElement element =
                this.Driver.findElement(
                        this.TransactionResetConfirmField
                );

        return element.getAttribute("validationMessage");
    }

    public String GetTransactionResetPinField() throws InterruptedException {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.TransactionResetField));
        return this.Driver.findElement(this.TransactionResetField).getAttribute("value");

    }
}
