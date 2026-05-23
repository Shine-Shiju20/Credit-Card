package api.clients;

import io.restassured.response.Response;

public class TransactionApiClient
        extends BaseApiClient{

    public Response withdraw(
            Object body){

        return post(
                "/transactions/withdraw",
                body);

    }

}