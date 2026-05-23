package runners.API;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {
            "src/test/java/resources/features/API/CreditCard",
            "src/test/java/resources/features/UI/CreditCard"
        },
        glue = {
            "stepdefinitions.api.CreditCard",
            "stepdefinitions.ui",
            "Hooks.CreditCard"
        },
        plugin = {
            "pretty",
            "html:target/cucumber-reports/creditcard.html",
            "json:target/cucumber-reports/creditcard.json",
            "junit:target/cucumber-reports/creditcard.xml",
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@CreditCardUI or @CreditCardAPI",
        monochrome = true,
        publish = false
)
public class CreditCardRunner extends AbstractTestNGCucumberTests {
}
