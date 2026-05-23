package runners.UI;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features =
                "src/test/java/resources/features/UI/ProfilePage",

        glue = {
                "stepdefinitions.ui.ProfilePage",
                "Hooks.Profile"
        },

        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        },

        monochrome = true,

        publish = true

//        tags = "@TC_10"
)

public class ProfileRunner
        extends AbstractTestNGCucumberTests {

}