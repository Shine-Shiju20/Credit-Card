package api.clients;

import constants.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class AccountApiClient
        extends BaseApiClient {

    public Response createAccount(
            Object requestBody) {

        return post(
                Endpoints.CREATE_ACCOUNT,
                requestBody);

    }

    public Response getAllAccounts() {

        return get(
                Endpoints.GET_ALL_ACCOUNTS);

    }

    public Response getAccount(
            String accountId) {

        return get(
                "/accounts/" + accountId);

    }

    public Response updateAccount(
            String accountId,
            Object body) {

        return put(
                "/accounts/" + accountId,
                body);
    }

    public Response closeAccount(
            String accountId) {

        return delete(
                "/accounts/" + accountId);

    }

}