package api.clients;

import context.ApiContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

public class BaseApiClient {

    protected RequestSpecification request;

    public BaseApiClient() {

        try {

            ConfigReader config =
                    new ConfigReader();

            RestAssured.baseURI =
                    config.getApiBaseUrl();

        }
        catch(Exception e){

            throw new RuntimeException(
                    "Config initialization failed");

        }

        request =
                RestAssured
                        .given()
                        .contentType(
                                ContentType.JSON)
                        .log()
                        .ifValidationFails();

        // Add cookies automatically
        Object storedCookies =
                ApiContext.get(
                        "cookies");

        if(storedCookies != null
                && storedCookies instanceof Cookies){

            request.cookies(
                    (Cookies)
                            storedCookies);

        }

    }


    protected Response get(
            String endpoint){

        return request
                .when()
                .get(endpoint);
    }


    protected Response post(
            String endpoint,
            Object body){

        return request
                .body(body)
                .when()
                .post(endpoint);
    }


    protected Response put(
            String endpoint,
            Object body){

        return request
                .body(body)
                .when()
                .put(endpoint);
    }


    protected Response delete(
            String endpoint){

        return request
                .when()
                .delete(endpoint);
    }

}