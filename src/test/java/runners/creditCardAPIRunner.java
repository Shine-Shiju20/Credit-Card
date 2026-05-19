package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/resources/features/creditcard",
        glue = {"stepdefinitions", "Hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/creditcard/cucumber.html",
                "json:target/cucumber-reports/creditcard/cucumber.json",
                "junit:target/cucumber-reports/creditcard/cucumber.xml"
        },
        tags = "@CreditCardAPI",
        monochrome = true,
        publish = true
)
public class creditCardAPIRunner extends AbstractTestNGCucumberTests{
}
