package utils;

public class ScenarioContext {

    private static final ThreadLocal<ScenarioContext> instance = ThreadLocal.withInitial(ScenarioContext::new);

    // Auth
    private String accessToken;
    private String loggedInUserId;
    private String loggedInEmail;

    // Runtime Account
    private String runtimeAccountId;
    private String runtimeAccountNumber;
    private double runtimeAccountBalance;

    // Runtime Card
    private String runtimeCardId;
    private String runtimeCardNumber;
    private double runtimeCreditLimit;
    private double runtimeAvailableLimit;
    private double runtimeOutstandingBalance;

    // Execution metadata
    private String executionMode; // SEED | RUNTIME | SKIP
    private String currentScenarioId;

    public static ScenarioContext get() {
        return instance.get();
    }

    public static void reset() {
        instance.remove();
    }

    public static void resetEntitiesOnly() {
        ScenarioContext ctx = instance.get();
        ctx.runtimeAccountId      = null;
        ctx.runtimeAccountNumber  = null;
        ctx.runtimeAccountBalance = 0;
        ctx.runtimeCardId         = null;
        ctx.runtimeCardNumber     = null;
        ctx.runtimeCreditLimit    = 0;
        ctx.runtimeAvailableLimit = 0;
        ctx.runtimeOutstandingBalance = 0;
        ctx.executionMode         = null;
        ctx.currentScenarioId     = null;
        // accessToken, loggedInUserId, loggedInEmail intentionally preserved
    }

    // ── Auth ──────────────────────────────────────────────────────────────────
    public String getAccessToken()                     { return accessToken; }
    public void   setAccessToken(String v)             { this.accessToken = v; }

    public String getLoggedInUserId()                  { return loggedInUserId; }
    public void   setLoggedInUserId(String v)          { this.loggedInUserId = v; }

    public String getLoggedInEmail()                   { return loggedInEmail; }
    public void   setLoggedInEmail(String v)           { this.loggedInEmail = v; }

    // ── Runtime Account ───────────────────────────────────────────────────────
    public String getRuntimeAccountId()                { return runtimeAccountId; }
    public void   setRuntimeAccountId(String v)        { this.runtimeAccountId = v; }

    public String getRuntimeAccountNumber()            { return runtimeAccountNumber; }
    public void   setRuntimeAccountNumber(String v)    { this.runtimeAccountNumber = v; }

    public double getRuntimeAccountBalance()           { return runtimeAccountBalance; }
    public void   setRuntimeAccountBalance(double v)   { this.runtimeAccountBalance = v; }

    // ── Runtime Card ──────────────────────────────────────────────────────────
    public String getRuntimeCardId()                   { return runtimeCardId; }
    public void   setRuntimeCardId(String v)           { this.runtimeCardId = v; }

    public String getRuntimeCardNumber()               { return runtimeCardNumber; }
    public void   setRuntimeCardNumber(String v)       { this.runtimeCardNumber = v; }

    public double getRuntimeCreditLimit()              { return runtimeCreditLimit; }
    public void   setRuntimeCreditLimit(double v)      { this.runtimeCreditLimit = v; }

    public double getRuntimeAvailableLimit()           { return runtimeAvailableLimit; }
    public void   setRuntimeAvailableLimit(double v)   { this.runtimeAvailableLimit = v; }

    public double getRuntimeOutstandingBalance()       { return runtimeOutstandingBalance; }
    public void   setRuntimeOutstandingBalance(double v){ this.runtimeOutstandingBalance = v; }

    // ── Execution Metadata ────────────────────────────────────────────────────
    public String getExecutionMode()                   { return executionMode; }
    public void   setExecutionMode(String v)           { this.executionMode = v; }

    public String getCurrentScenarioId()               { return currentScenarioId; }
    public void   setCurrentScenarioId(String v)       { this.currentScenarioId = v; }

    // ── Helpers ───────────────────────────────────────────────────────────────
    public boolean isRuntime() { return "RUNTIME".equalsIgnoreCase(executionMode); }
    public boolean isSeed()    { return "SEED".equalsIgnoreCase(executionMode); }
    public boolean isSkip()    { return "SKIP".equalsIgnoreCase(executionMode); }
    public boolean hasCard()   { return runtimeCardId != null && !runtimeCardId.isEmpty(); }
    public boolean hasAccount(){ return runtimeAccountId != null && !runtimeAccountId.isEmpty(); }
}