package utils;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

    private static final Logger logger =
            LogManager.getLogger(
                    LoggerUtility.class
            );



    public static void info(
            String message
    ) {

        logger.info(message);

        ExtentCucumberAdapter.addTestStepLog(
                "[INFO] " + message
        );
    }



    public static void pass(
            String message
    ) {

        logger.info(
                "[PASS] " + message
        );

        ExtentCucumberAdapter.addTestStepLog(
                "[PASS] " + message
        );
    }



    public static void fail(
            String message
    ) {

        logger.error(
                "[FAIL] " + message
        );

        ExtentCucumberAdapter.addTestStepLog(
                "[FAIL] " + message
        );
    }



    public static void warn(
            String message
    ) {

        logger.warn(
                "[WARN] " + message
        );

        ExtentCucumberAdapter.addTestStepLog(
                "[WARN] " + message
        );
    }



    public static void debug(
            String message
    ) {

        logger.debug(message);

        ExtentCucumberAdapter.addTestStepLog(
                "[DEBUG] " + message
        );
    }

    public static org.apache.logging.log4j.Logger getLogger(Class<?> className) {
        return org.apache.logging.log4j.LogManager.getLogger(className);
    }
}