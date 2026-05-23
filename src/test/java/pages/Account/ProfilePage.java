package pages.Account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.ScreenShotUtil;

public class ProfilePage {

    WebDriver Driver;

    By ProfileEditButton = new By.ByXPath("//button[contains(text(),\"Edit\")]");
    By ProfileSaveButton = new By.ByXPath("(//div[@class=\"profile-section\"])[1]//button[contains(text(),'Save')]");
    By ProfileCancelButton = new By.ByXPath("(//div[@class=\"profile-section\"])[1]//button[normalize-space(text())='Cancel']");
    By ForgotTransactionPinButton = new By.ByXPath("//button[contains(text(),\"Forgot Transaction PIN?\")]");
    By ForgotTransactionCanceButton = new  By.ByXPath("//form[contains(@class,\"pin-reset-form\")]//button[contains(text(), \"Cancel\")]");
    By ForgotTransactionResetPinButton = new By.ByXPath("//form[contains(@class,\"pin-reset-form\")]//button[contains(text(), \"Reset PIN\")]");
    By CancelKYCButton = new By.ByXPath("(//div[@class=\"profile-section\"])[2]//button[contains(text(),'Cancel')]");
    By SaveKYCButton = new By.ByXPath("(//div[@class=\"profile-section\"])[2]//button[contains(text(),'Save KYC')]");

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
    }

    public void SetDriver(WebDriver Driver){
        this.Driver = Driver;
    }

    public WebDriver getDriver(){
        return Driver;
    }


    public void SetNameField(){

    }

    public void ClickEditProfileButton(){

    }
    public void ClickCancelProfileButton(){

    }

}
