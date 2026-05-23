package Hooks.Accounts;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import utils.LoggerUtility;
import utils.ScreenShotUtil;
import utils.UIApiCleanupUtility;
import org.openqa.selenium.Dimension;

import java.io.IOException;
import java.time.Duration;

public class Hooks {
    public static DriverFactory factory;

    @Before
    public void setup() {
        factory = new DriverFactory();
        factory.getDriver().manage().window().setSize(new Dimension(1920, 1080));
        factory.getDriver()
                .manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(0)
                );
        LoggerUtility.info("Browser Started");
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) throws IOException {

        LoggerUtility.info(
                "Scenario Status : "
                        + scenario.getStatus()
        );

        if (scenario.isFailed()) {
            System.out.println("Scenario Failed: " + scenario.getName());
            ScreenShotUtil screenShotUtil = new ScreenShotUtil();
            screenShotUtil.TakeScreenShot(factory.getDriver(), scenario.getName());
        }

        try{
            UIApiCleanupUtility.cleanUp();
        }
        catch(Exception e){

            LoggerUtility.info(
                    "Cleanup Failed"
            );
        }

        factory.CloseDriver();

        LoggerUtility.info(
                "Scenario Completed : "
                        + scenario.getName()
        );
    }
}
