package api.models.responses;

public class CreateAccountResponse {

    private boolean success;
    private String message;
    private String account_id;
    private String account_number;
    private String account_type;
    private String status;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(
            boolean success) {

        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(
            String message) {

        this.message = message;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(
            String account_id) {

        this.account_id =
                account_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(
            String account_number) {

        this.account_number =
                account_number;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(
            String account_type) {

        this.account_type =
                account_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status) {

        this.status = status;
    }
}