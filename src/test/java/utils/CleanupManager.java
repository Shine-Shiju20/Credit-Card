package utils;

import api.Account.RuntimeAccountManager;
import api.creditcard.RuntimeCardManager;

import org.apache.logging.log4j.Logger;

public class CleanupManager {
    private static final Logger logger = LoggerUtility.getLogger(CleanupManager.class);

    public static void cleanup() {
        ScenarioContext ctx = ScenarioContext.get();

        try {
            if (ctx.hasCard()) {
                try {
                    cleanupCard();
                } catch (Exception e) {
                    logger.warn("Skipping card cleanup: {}", e.getMessage());
                }
            }
            if (ctx.hasAccount()) {
                cleanupAccount();
            }
        } catch (Exception e) {
            logger.error("Cleanup failed for scenario: " + ctx.getCurrentScenarioId() + " | " + e.getMessage());
        } finally {
            ScenarioContext.resetEntitiesOnly();
        }
    }

    private static void cleanupCard() {
        try { RuntimeCardManager.repayFull();  } catch (Exception e) { logger.error("Repay failed: "  + e.getMessage()); }
        try { RuntimeCardManager.closeCard();  } catch (Exception e) { logger.error("Close failed: "  + e.getMessage()); }
        try { RuntimeCardManager.deleteCard(); } catch (Exception e) { logger.error("Delete card failed: " + e.getMessage()); }
    }

    private static void cleanupAccount() {
        try {
            double balance = RuntimeAccountManager.fetchBalance();
            if (balance > 0) {
                RuntimeAccountManager.withdrawBalance(balance);
            }
        } catch (Exception e) {
            logger.error("Withdraw failed: " + e.getMessage());
        }
        try { RuntimeAccountManager.deleteAccount(); } catch (Exception e) { logger.error("Delete account failed: " + e.getMessage()); }
    }
}