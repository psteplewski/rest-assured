package http.methods;

import org.testng.annotations.Test;
import utils.Properties;

import static io.restassured.RestAssured.given;

public class BasicHttpMethods {
    Properties properties = new Properties();

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {
        String postRequestBody = "{\n" +
                "  \"id\": 123,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"dogs\"\n" +
                "  },\n" +
                "  \"name\": \"Burek\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"http://photos.com/dog1.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"dogs-category\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given().log().all().body(postRequestBody).contentType("application/json")
                .when().post(properties.baseUrl + "/v2/pet")
                .then().log().all().statusCode(200);
    }
}
