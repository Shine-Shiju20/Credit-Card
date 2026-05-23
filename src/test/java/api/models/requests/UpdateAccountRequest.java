package api.models.requests;

public class UpdateAccountRequest {

    private String account_type;

    public String getAccount_type() {

        return account_type;
    }

    public void setAccount_type(
            String account_type) {

        this.account_type =
                account_type;
    }

}