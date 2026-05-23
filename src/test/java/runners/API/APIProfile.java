package runners.API;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/resources/features/API/profilePage",
        glue = {
                "stepdefinitions.api",
                "API_Hooks.Profile"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"

        },
        monochrome = true,
        publish = true
//        tags = "@KYCFieldValidation"
)

public class APIProfile extends AbstractTestNGCucumberTests {

}