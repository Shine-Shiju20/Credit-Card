package assertions;

import io.restassured.response.Response;
import org.testng.Assert;

public class APIAssertions {

    public static void verifyStatusCode(
            Response response,
            int expectedStatusCode){

        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code mismatch");

    }


    public static void verifyResponseField(
            String actualValue,
            String expectedValue){

        Assert.assertEquals(
                actualValue,
                expectedValue,
                "Field value mismatch");

    }


    public static void verifyTrue(
            boolean actual){

        Assert.assertTrue(
                actual,
                "Expected value is false");

    }


    public static void verifyNotNull(
            Object object){

        Assert.assertNotNull(
                object,
                "Object is null");

    }

}