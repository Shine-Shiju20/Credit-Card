package api.clients;

import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.post;

public class AuthApiClient extends BaseApiClient {

    public Response login(
            Object body){

        return RestAssured
                .given()
                .contentType(
                        ContentType.JSON)
                .body(body)
                .log()
                .ifValidationFails()
                .post("/auth/login");
    }

}