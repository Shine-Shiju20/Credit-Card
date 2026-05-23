package api.fd;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigReader;

import java.io.IOException;

public class FD_API {

    ConfigReader config;

    public FD_API() throws IOException {
        config = new ConfigReader();
    }

    public Response login(String email, String password) {

        String body = "{"
                + "\"email\":\"" + email + "\","
                + "\"password\":\"" + password + "\""
                + "}";

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post(this.config.getProp("baseUrlApi")
                        + this.config.getProp("loginEndpoint"));
    }

    public Response createFD(String token,
                             String accountId,
                             String amount,
                             String tenure,
                             String interestRate) {

        String body = "{"
                + "\"account_id\":\"" + accountId + "\","
                + "\"amount\":" + amount + ","
                + "\"tenure_months\":" + tenure + ","
                + "\"interest_rate\":" + interestRate
                + "}";

        return RestAssured.given()
                .header("Cookie", "access_token=" + token)
                .header("Content-Type", "application/json")
                .body(body)
                .post(config.getProp("baseUrlApi")
                        + config.getProp("createFdEndpoint"));
    }

    public Response getAllFD(String token) {

        return RestAssured.given()
                .header("Cookie", "access_token=" + token)
                .get(config.getProp("baseUrlApi")
                        + config.getProp("getAllFdEndpoint"));
    }

    public Response getFDById(String token, String fdId) {

        return RestAssured.given()
                .header("Cookie", "access_token=" + token)
                .get(config.getProp("baseUrlApi")
                        + config.getProp("getFdByIdEndpoint")
                        + fdId);
    }

    public Response closeFD(String token, String fdId) {

        return RestAssured.given()
                .header("Cookie", "access_token=" + token)
                .patch(config.getProp("baseUrlApi")
                        + config.getProp("closeFdEndpoint")
                        + fdId);
    }
}