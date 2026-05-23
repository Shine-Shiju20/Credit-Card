package api.Common;





import api.Profile.Routes;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;

import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;

public class AuthHelper {
    private static String token;
    private static  String UnverifiedKycToken;
    public String getValidSessionCookie() throws IOException {
        utils.ConfigReader configReader = new utils.ConfigReader();
        RestAssured.baseURI = configReader.getProp ("api_baseurl");
        HashMap<String, String> payload = new HashMap<>();

        payload.put("email", "abhirampb9@gmail.com");

        payload.put("password", "Strong@123");

        Response response = RestAssured.given().contentType(ContentType.JSON).body(payload).when().post(Routes.LOGIN);
        token =response.getCookie("access_token");
        return response.getCookie("access_token");
    }

    public String getValidUnverifiedKycSessionCookie() throws IOException {
        utils.ConfigReader configReader = new utils.ConfigReader();
        RestAssured.baseURI = configReader.getProp ("api_baseurl");
        HashMap<String, String> payload = new HashMap<>();
        payload.put("email", "prajwalp.123p@gmail.com");
        payload.put("password", "Strong@123");
        Response response = RestAssured.given().contentType(ContentType.JSON).body(payload).when().post(Routes.LOGIN);
        UnverifiedKycToken =response.getCookie("access_token");
        return response.getCookie("access_token");
    }


    public String GetToken(){
        return token;
    }

    public String GetUnverifiedToken(){
        return UnverifiedKycToken;
    }

}