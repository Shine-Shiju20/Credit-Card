package utils;

import api.Account.RuntimeAccountManager;
import api.creditcard.RuntimeCardManager;

import org.apache.logging.log4j.Logger;

public class RuntimeEntityFactory {
    private static final Logger logger = LoggerUtility.getLogger(RuntimeEntityFactory.class);

    // Default card params — override via overloaded method if needed
    private static final String  DEFAULT_TIER        = "entry";
    private static final double  DEFAULT_LIMIT       = 50000;
    private static final double  DEFAULT_LIABILITIES = 0;

    public static void createRuntimeEnvironment() {
        createRuntimeEnvironment(DEFAULT_TIER, DEFAULT_LIMIT, DEFAULT_LIABILITIES);
    }

    public static void createRuntimeEnvironment(String cardTier, double requestedLimit, double existingLiabilities) {
        RuntimeAccountManager.createAccount();
        RuntimeCardManager.applyCard(cardTier, requestedLimit, existingLiabilities);
        ScenarioContext.get().setExecutionMode("RUNTIME");
        logger.info("Runtime environment ready | account="
                        + ScenarioContext.get().getRuntimeAccountId()
                        + " | card=" + ScenarioContext.get().getRuntimeCardId());
    }
}