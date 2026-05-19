package utils;

public class ExecutionModeResolver {

    private static final String RUNTIME = "RUNTIME";
    private static final String SEED    = "SEED";
    private static final String SKIP    = "SKIP";

    public static String resolve(String modeFromExcel) {
        if (modeFromExcel == null || modeFromExcel.isBlank()) {
            return SEED; // safe default
        }
        switch (modeFromExcel.trim().toUpperCase()) {
            case RUNTIME: return RUNTIME;
            case SKIP:    return SKIP;
            default:      return SEED;
        }
    }

    public static boolean isRuntime(String mode) { return RUNTIME.equalsIgnoreCase(mode); }
    public static boolean isSeed(String mode)    { return SEED.equalsIgnoreCase(mode); }
    public static boolean isSkip(String mode)    { return SKIP.equalsIgnoreCase(mode); }
}