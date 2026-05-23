package utils.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanTestContext {

    private static final ThreadLocal<List<String>> loanIds =
            ThreadLocal.withInitial(ArrayList::new);

    public static void addLoanId(String loanId) {
        if (loanId != null && !loanId.trim().isEmpty()) {
            loanIds.get().add(loanId);
        }
    }

    public static List<String> getLoanIds() {
        return loanIds.get();
    }

    public static void clear() {
        loanIds.get().clear();
    }
}