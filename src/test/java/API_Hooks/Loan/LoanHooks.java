package API_Hooks.Loan;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.Loan.ApiSessionManager;
import utils.Loan.DatabaseCleanup;
import utils.Loan.LoanTestContext;

public class LoanHooks {

    @Before
    public static void beforeAllScenarios() {
        DatabaseCleanup.deleteLoansForTestUser();
        ApiSessionManager.resetSession();
        System.out.println("Loan cleanup completed only for rohansafenet123@gmail.com.");
    }

    @Before
    public void beforeScenario() throws Exception {
        ApiSessionManager.loginIfNeeded();
    }

    @After
    public void afterScenario(io.cucumber.java.Scenario scenario) {
        LoanTestContext.clear();
    }
}