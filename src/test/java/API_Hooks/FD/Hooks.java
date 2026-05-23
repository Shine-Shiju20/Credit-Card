package API_Hooks.FD;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.annotations.AfterSuite;
import utils.DriverFactory;
import utils.FD.DatabaseUtility;
import utils.LoggerUtility;
import utils.ScreenShotUtil;

import java.io.IOException;

public class Hooks {

    public static DriverFactory factory;

    @Before
    public void setup() {
        factory = new DriverFactory();

        factory.getDriver().manage().window().maximize();

        LoggerUtility.info("Browser Started");
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            System.out.println("Scenario Failed: " + scenario.getName());

            ScreenShotUtil screenShotUtil = new ScreenShotUtil();

            screenShotUtil.TakeScreenShot(
                    factory.getDriver(),
                    scenario.getName()
            );
        }

        if (factory != null) {
            factory.CloseDriver();
        }

        LoggerUtility.info("Browser Closed");
    }

    @AfterSuite
    public void cleanDatabase() {

        LoggerUtility.info(
                "Starting post-suite database cleanup"
        );

        DatabaseUtility.deleteTestFDs();

        LoggerUtility.pass(
                "Database cleanup completed"
        );
    }
}
