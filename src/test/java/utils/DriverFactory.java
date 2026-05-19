package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.logging.log4j.Logger;

public class DriverFactory {
    private static final Logger logger = LoggerUtility.getLogger(DriverFactory.class);
    WebDriver Driver;

    //Constructor (creates an Object of WebDriver)
    public DriverFactory(){
        this.Driver = new ChromeDriver();
    }

    //Navigates to an URL provided by the user
    public void FetchPage(String url){
        if(this.Driver == null){
            logger.warn("Driver Not initialised");
        }else {
            this.Driver.get(url);
        }
    }

    public void CloseDriver(){
        if(this.Driver == null){
            logger.warn("Driver Not initialised");
        }else {
            this.Driver.close();
        }
    }

    //getter (returns the Created WebDriver)
    public WebDriver getDriver(){
        return this.Driver;
    }

    //Setter (sets the Driver)
    public void SetDriver(WebDriver Driver){
        this.Driver = Driver;
    }

}
