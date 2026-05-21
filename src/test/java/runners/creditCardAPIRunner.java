package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/resources/features/creditcardAPI",
        glue = {"stepdefinitions", "Hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/creditcardAPI/cucumber.html",
                "json:target/cucumber-reports/creditcardAPI/cucumber.json",
                "junit:target/cucumber-reports/creditcardAPI/cucumber.xml"
        },
        tags = "@CreditCardAPI",
        monochrome = true,
        publish = true
)
public class creditCardAPIRunner extends AbstractTestNGCucumberTests{
}
