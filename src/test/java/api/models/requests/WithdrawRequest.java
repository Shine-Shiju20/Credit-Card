package api.models.requests;

public class WithdrawRequest {

    private String account_number;
    private Double amount;
    private String transaction_pin;

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(
            String account_number) {

        this.account_number =
                account_number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(
            Double amount) {

        this.amount =
                amount;
    }

    public String getTransaction_pin() {
        return transaction_pin;
    }

    public void setTransaction_pin(
            String transaction_pin) {

        this.transaction_pin =
                transaction_pin;
    }

}