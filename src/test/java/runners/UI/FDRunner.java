package runners.UI;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

        features =
                "src/test/java/resources/features/UI/FD",

        glue = {
                "stepdefinitions.ui.FD",
                "Hooks.FD"
        },

        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        },
//        tags = "@tc_12 or @tc_01",
        monochrome = true,

        publish = true

//        tags = "@TC_10"
)

public class FDRunner
        extends AbstractTestNGCucumberTests {

}