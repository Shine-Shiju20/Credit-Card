package utils;


import org.apache.logging.log4j.LogManager;


public final class LoggerUtility {


    private LoggerUtility() {
        throw new IllegalStateException(
                "Utility class"
        );
    }


    public static org.apache.logging.log4j.Logger getLogger(
            Class<?> className
    ) {


        return LogManager.getLogger(className);
    }
}
