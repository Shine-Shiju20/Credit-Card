//package pages;
//
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class AdvancedTransactionsPage extends TransactionsPage {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    public AdvancedTransactionsPage(WebDriver driver) {
//        super(driver);
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//    }
//
//    /**
//     * Performs advanced transaction using Excel-driven data.
//     */
//    public void performAdvancedTransaction(
//            String sourceAccount,
//            String recipientAccount,
//            String transferType,
//            String amount,
//            String transactionPin) {
//
//        performTransaction(
//                sourceAccount,
//                recipientAccount,
//                transferType,
//                amount,
//                transactionPin
//        );
//    }
//
//    /**
//     * Placeholder for future balance validation.
//     */
//    public String getCurrentBalance() {
//        return "";
//    }
//}

package pages;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Log;

public class AdvancedTransactionsPage extends TransactionsHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    // Logger
    private static final Logger logger =
            Log.getLogger(AdvancedTransactionsPage.class);

    public AdvancedTransactionsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        logger.info("AdvancedTransactionsPage initialized.");
    }

    /**
     * Performs advanced transaction using Excel-driven data.
     */
    public void performAdvancedTransaction(
            String sourceAccount,
            String recipientAccount,
            String transferType,
            String amount,
            String transactionPin) {

        logger.info("Starting advanced transaction.");
        logger.info("Source Account: {}", sourceAccount);
        logger.info("Recipient Account: {}", recipientAccount);
        logger.info("Transfer Type: {}", transferType);
        logger.info("Amount: {}", amount);

        performTransaction(
                sourceAccount,
                recipientAccount,
                transferType,
                amount,
                transactionPin
        );

        logger.info("Advanced transaction completed.");
    }

    /**
     * Placeholder for future balance validation.
     */
    public String getCurrentBalance() {
        logger.info("Fetching current balance (placeholder).");
        return "";
    }
}