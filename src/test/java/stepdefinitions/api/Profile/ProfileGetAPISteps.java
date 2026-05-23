package stepdefinitions.api.Profile;



import api.Common.AuthHelper;
import api.Profile.RequestHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;

import org.testng.Assert;

import java.io.IOException;

public class ProfileGetAPISteps {


    RequestHelper requestHelper = new RequestHelper();

    AuthHelper authHelper = new AuthHelper();




    String token;

    public ProfileGetAPISteps() throws IOException {
    }




    @Given("User has valid session cookie")
    public void user_has_valid_session_cookie() throws IOException {

        token = authHelper.getValidSessionCookie();
    }


    @Given("User has invalid session cookie")
    public void user_has_invalid_session_cookie() {

        token = "access_token=abhiramskdaksjdlkasjndkajksflasklsfjaklj";
    }


    @Given("User has expired session cookie")
    public void user_has_expired_session_cookie() {

        token = "acess_token";
    }





    @When("User sends GET request to {string}")
    public void user_sends_get_request_to(
            String endpoint
    ) {

        APIContext.response = requestHelper.sendGetRequest(endpoint, token);
    }



    @When("User sends GET request to {string} without cookie")
    public void user_sends_get_request_to_without_cookie(String endpoint) {
        APIContext.response = requestHelper.sendGetRequestWithoutCookie(endpoint);
    }




    @And("Response field {string} should be true")
    public void response_field_should_be_true(String field) {

        boolean actualValue = APIContext.response.jsonPath().getBoolean(field);
        Assert.assertTrue(actualValue);
    }




    @Then("Response should contain field {string}")
    public void response_should_contain_field(String field) {

        Object value = APIContext.response.jsonPath().get("data." + field);
        System.out.println(value);
        Assert.assertNotNull(value);
    }

    @Then("Response should not contain field {string}")
    public void response_should_not_contain_field(
            String field
    ) {

        Object value =
                APIContext.response.jsonPath()
                        .get("data." + field);

        Assert.assertNull(
                value,
                "Sensitive field exposed in API response: " + field
        );
    }
}