package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import utils.LoggerUtility;
import utils.TokenManager;
import utils.CleanupManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class Hooks {

    private static final Logger logger = LoggerUtility.getLogger(Hooks.class);

    public static DriverFactory factory;

    @Before("@CreditCardAPI")
    public void setupAPI() {
        logger.info("Setting up API base authentication context");
        TokenManager.invalidate();
        TokenManager.login();
    }

    @After("@CreditCardAPI")
    public void tearDownAPI() {
        logger.info("Cleaning up API Runtime Environment");
        CleanupManager.cleanup();
    }

    @Before(value = "@CreditCardUI", order = 0)
    public void setupUI() {
        factory = new DriverFactory();
        factory.getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser Started");
    }

    @After(value = "@CreditCardUI", order = 1)
    public void tearDownUI() {
        if (factory != null) {
            factory.CloseDriver();
            factory = null;
        }
        logger.info("Browser Closed");
    }
}




//package Hooks;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import utils.DriverFactory;
//import utils.LoggerUtility;
//
//import utils.TokenManager;
//import utils.RuntimeEntityFactory;
//import utils.CleanupManager;
//import org.apache.logging.log4j.Logger;
//
//public class Hooks {
//    private static final Logger logger = LoggerUtility.getLogger(Hooks.class);
//
//    public static DriverFactory factory;
//
////    @Before("@CreditCardAPI")
////    public void setupAPI() {
////        logger.info("Setting up API Runtime Environment");
////        // Always reset to main account token before creating runtime entities
////        utils.TokenManager.invalidate();
////        TokenManager.login(); // logs in as main account
////        RuntimeEntityFactory.createRuntimeEnvironment();
////    }
//
//    @Before("@CreditCardUI")
//    public void setupUI() {
//        factory = new DriverFactory();
//        factory.getDriver().manage().window().maximize();
//        logger.info("Browser Started");
//    }
//
//    @After("@CreditCardUI")
//    public void tearDownUI() {
//        if (factory != null) {
//            factory.CloseDriver();
//            factory = null;
//        }
//        logger.info("Browser Closed");
//    }
//
//@Before("@CreditCardAPI")
//public void setupAPI() {
//    logger.info("Setting up API base authentication context");
//    utils.TokenManager.invalidate();
//    TokenManager.login(); // login only, do not create runtime entities here
//}
//
//    @After("@CreditCardAPI")
//    public void tearDownAPI() {
//        logger.info("Cleaning up API Runtime Environment");
//        CleanupManager.cleanup();
//    }
//
//    @Before("not @CreditCardAPI")
//    public void setup() {
//        factory = new DriverFactory();
//        factory.getDriver().manage().window().maximize();
//        logger.info("Browser Started");
//    }
//
//    @After("not @CreditCardAPI")
//    public void tearDown() {
//        if (factory != null) {
//            factory.CloseDriver();
//        }
//        logger.info("Browser Closed");
//    }
//
//}
