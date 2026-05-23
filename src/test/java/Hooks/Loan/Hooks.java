package Hooks.Loan;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import utils.ScreenShotUtil;

import java.io.IOException;

public class Hooks {

    public static DriverFactory factory;

    @Before
    public void setup() {

        factory = new DriverFactory();
        factory.getDriver().manage().window().maximize();
        System.out.println("Browser Started");
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            System.out.println("Scenario Failed: " + scenario.getName());
            ScreenShotUtil screenShotUtil = new ScreenShotUtil();
            screenShotUtil.TakeScreenShot(factory.getDriver(), scenario.getName());
        }

        DbCleanup.deleteLoansByEmail(
                "rohansnaik2004@gmail.com"
        );

        factory.CloseDriver();
        System.out.println("Browser Closed");
    }

}