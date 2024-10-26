package http.methods;

import org.testng.annotations.Test;
import pojo.pet.Category;
import pojo.pet.Pet;
import pojo.pet.Tag;
import utils.Properties;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class BasicHttpMethods {
    Properties properties = new Properties();

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
        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("Reksio");

        Pet pet = new Pet();
        pet.setId(1);
        pet.setName("Reksio");
        pet.setCategory(category);
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().log().all().body(pet).contentType("application/json")
                .when().post(properties.baseUrl + "/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
       given().log().all().pathParam("petId", 1).
                when().baseUri(properties.baseUrl + "/v2/pet/{petId}").
                then().statusCode(200).log().all();
    }

    @Test
    public void putRequest() {
        given().log().all().body(requestBody2).contentType("application/json")
                .put(properties.baseUrl+"/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void deleteRequest() {
        given().log().all().pathParam("petId",1)
                .delete(properties.baseUrl+"/v2/pet/{petId}")
                .then().log().all().statusCode(200);
    }
}
