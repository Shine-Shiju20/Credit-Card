package API_Hooks.Accounts;

import context.ApiContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.LoggerUtility;

public class Hooks {

    @Before(order = 0)
    public void setupScenario(
            Scenario scenario) {

        LoggerUtility.info(
                "================================================");

        LoggerUtility.info(
                "Scenario Started : "
                        + scenario.getName());

        ApiContext.clear();
    }


    @After(order = 0)
    public void cleanupScenario(
            Scenario scenario) {

        LoggerUtility.info(
                "Scenario Status : "
                        + scenario.getStatus());

        LoggerUtility.info(
                "Scenario Completed : "
                        + scenario.getName());

        ApiContext.clear();

        LoggerUtility.info(
                "================================================");
    }

}