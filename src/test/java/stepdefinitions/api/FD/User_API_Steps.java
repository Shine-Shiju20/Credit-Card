package stepdefinitions.api.FD;

import api.fd.FD_API;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;

public class User_API_Steps {

    FD_API fdApi = new FD_API();
    Response response;
    String token;
    String createdFdId;

    public User_API_Steps() throws IOException {
    }

    //login steps
    @Given("user logs in with email {string} and password {string}")
    public void user_logs_in(String email, String password) {

        response = fdApi.login(email, password);

        System.out.println(response.asPrettyString());

        if (response.getStatusCode() == 200) {
            token = response.getCookie("access_token");
            System.out.println("TOKEN: " + token);
        }
    }

    //fd creation
    @When("user creates FD with account_id {string}, amount {string}, tenure {string}, interest {string}")
    public void create_fd(String accountId,
                          String amount,
                          String tenure,
                          String interest) {

        response = fdApi.createFD(token, accountId, amount, tenure, interest);

        System.out.println("CREATE FD RESPONSE:");
        System.out.println(response.asPrettyString());

        if (response.getStatusCode() == 200) {
            createdFdId = response.jsonPath().getString("data.id");
            System.out.println("CREATED FD ID: " + createdFdId);
        }
    }

    //fetch all
    @When("user fetches all fixed deposits")
    public void fetch_all_fd() {
        response = fdApi.getAllFD(token);
    }

    //fetch by ID
    @When("user fetches FD by id {string}")
    public void fetch_fd_by_id(String fdId) {
        response = fdApi.getFDById(token, fdId);
    }

    //close FD
    @When("user closes created FD")
    public void close_created_fd() {
        response = fdApi.closeFD(token, createdFdId);
    }

    //common validation
    @Then("API status code should be {int}")
    public void validate_status(int expectedStatus) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus);
    }
}