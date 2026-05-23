package api.Profile;



import io.restassured.RestAssured;

import io.restassured.http.ContentType;

import io.restassured.response.Response;
import utils.ConfigReader;

import java.io.IOException;

public class RequestHelper {


    public RequestHelper() throws IOException {
        utils.ConfigReader ConfigReader = new ConfigReader();
        RestAssured.baseURI =
                ConfigReader.getProp(
                        "api_baseurl"
                );
    }




    // sends Request
    public Response sendGetRequest(
            String endpoint,
            String token
    ){

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .cookie(
                        "access_token",
                        token
                )
                .when()
                .get(endpoint);
    }




    //Get method without Cookie
    public Response sendGetRequestWithoutCookie(
            String endpoint
    ){

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint);
    }



    //Post request
    public Response sendPostRequest(
            String endpoint,
            Object payload,
            String token
    ){

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .cookie(
                        "access_token",
                        token
                )
                .body(payload)
                .when()
                .post(endpoint);
    }


    //post method without Cookie
    public Response sendPostRequestWithoutCookie(
            String endpoint,
            Object payload
    ){

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(endpoint);
    }


    //put method request
    public Response sendPutRequest(
            String endpoint,
            Object payload,
            String token
    ){

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .cookie(
                        "access_token",
                        token
                )
                .body(payload)
                .when()
                .put(endpoint);
    }



    //path request method
    public Response sendPatchRequest(
            String endpoint,
            Object payload,
            String token
    ){

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .cookie(
                        "access_token",
                        token
                )
                .body(payload)
                .when()
                .patch(endpoint);
    }



}