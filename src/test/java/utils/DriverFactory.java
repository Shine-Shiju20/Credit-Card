package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.Logger;

public class DriverFactory {

    private static final Logger logger =
            LoggerUtility.getLogger(DriverFactory.class);

    private WebDriver driver;

    // Constructor
    public DriverFactory() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");

        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        logger.info("Chrome Driver Initialized");
    }

    // Navigate to URL
    public void FetchPage(String url) {

        if (driver == null) {
            logger.warn("Driver Not Initialised");
        } else {
            driver.get(url);
        }
    }

    // Close Browser
    public void CloseDriver() {

        if (driver == null) {
            logger.warn("Driver Not Initialised");
        } else {
            driver.quit();
        }
    }

    // Getter
    public WebDriver getDriver() {
        return driver;
    }

    // Setter
    public void SetDriver(WebDriver driver) {
        this.driver = driver;
    }
}