package http.methods;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import utils.Properties;

import static io.restassured.RestAssured.given;

public class BasicHttpMethods {
    Properties properties = new Properties();

    String requestBody1 = "{\n" +
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

    String requestBody2 = "{\n" +
            "  \"id\": 123,\n" +
            "  \"category\": {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"dogs\"\n" +
            "  },\n" +
            "  \"name\": \"Reksio\",\n" +
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

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {
        given().log().all().body(requestBody1).contentType("application/json")
                .when().post(properties.baseUrl + "/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        RestAssured.given().log().all().pathParam("petId", 1).
                when().baseUri(properties.baseUrl + "/v2/pet/{petId}").
                then().statusCode(200).log().all();
    }

    @Test
    public void putRequest() {
        RestAssured.given().log().all().body(requestBody2).contentType("application/json")
                .put(properties.baseUrl+"/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void deleteRequest() {
        RestAssured.given().log().all().pathParam("petId",1)
                .delete(properties.baseUrl+"/v2/pet/{petId}")
                .then().log().all().statusCode(200);
    }
}
