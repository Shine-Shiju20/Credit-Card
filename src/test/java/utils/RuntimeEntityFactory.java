package utils;

import api.Account.RuntimeAccountManager;
import api.creditcard.RuntimeCardManager;

import org.apache.logging.log4j.Logger;

public class RuntimeEntityFactory {
    private static final Logger logger = LoggerUtility.getLogger(RuntimeEntityFactory.class);

    private static final String DEFAULT_TIER = "entry";
    private static final double DEFAULT_LIMIT = 50000;
    private static final double DEFAULT_LIABILITIES = 0;
    private static final double DEFAULT_INITIAL_DEPOSIT = 100000;

    public static void createRuntimeEnvironment() {
        createRuntimeEnvironment(
                DEFAULT_INITIAL_DEPOSIT,
                DEFAULT_TIER,
                DEFAULT_LIMIT,
                DEFAULT_LIABILITIES
        );
    }

    public static void createRuntimeEnvironment(double initialDeposit) {
        createRuntimeEnvironment(
                initialDeposit,
                DEFAULT_TIER,
                DEFAULT_LIMIT,
                DEFAULT_LIABILITIES
        );
    }

    public static void createRuntimeEnvironment(
            double initialDeposit,
            String cardTier,
            double requestedLimit,
            double existingLiabilities
    ) {
        RuntimeAccountManager.createAccount(initialDeposit);
        RuntimeCardManager.applyCard(cardTier, requestedLimit, existingLiabilities);

        ScenarioContext.get().setExecutionMode("RUNTIME");

        logger.info("Runtime environment ready | account="
                + ScenarioContext.get().getRuntimeAccountId()
                + " | card=" + ScenarioContext.get().getRuntimeCardId()
                + " | initialDeposit=" + initialDeposit);
    }
}