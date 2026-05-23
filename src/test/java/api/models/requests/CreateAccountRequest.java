package api.models.requests;

public class CreateAccountRequest {

    private String account_type;
    private Double initial_deposit;

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(
            String account_type) {

        this.account_type = account_type;
    }

    public Double getInitial_deposit() {
        return initial_deposit;
    }

    public void setInitial_deposit(
            Double initial_deposit) {

        this.initial_deposit =
                initial_deposit;
    }

}