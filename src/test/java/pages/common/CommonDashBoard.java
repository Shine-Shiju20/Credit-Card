package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.*;

public class CommonDashBoard {
    WebDriver Driver;
    By LogoutButton = By.xpath("//div[@class=\"user-menu\"]//button");
    public CommonDashBoard(WebDriver Driver){
        this.Driver = Driver;
    }
    //clicks logout button
    public void ClickLogout(){
        WebDriverWait wait = new WebDriverWait(this.Driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(LogoutButton));
        this.Driver.findElement(LogoutButton).click();
    }

}
