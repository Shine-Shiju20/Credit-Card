package runners.API;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/resources/features/API/FD",
        glue = {
                "stepdefinitions.api.FD",
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
//        tags = "@KYCFieldValidation"
)

public class APIFd extends AbstractTestNGCucumberTests {

}