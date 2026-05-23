package api.Auth;

import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RequestHelper {

    public Response sendPostRequest(
            String endpoint,
            HashMap<String, Object> payload
    ) {

        return
                given()

                        .header(
                                "Content-Type",
                                "application/json"
                        )

                        .body(payload)

                .when()

                        .post(endpoint);
    }
}