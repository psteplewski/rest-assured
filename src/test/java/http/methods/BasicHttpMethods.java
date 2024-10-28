package http.methods;

import org.testng.annotations.Test;
import pojo.pet.Category;
import pojo.pet.Pet;
import pojo.pet.Tag;
import utils.PetTestDataGenerator;
import utils.Properties;
import utils.TestDataGenerator;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class BasicHttpMethods extends TestDataGenerator {
    
    Properties properties = new Properties();

    @Test(testName = "POST request for Pet creation")
    public void givenPetWhenPostPetThenPetIsCreatedTest() {
        Category category = new Category();
        category.setId(faker().number().numberBetween(1, 9999));
        category.setName(faker().animal().name());

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName(faker().animal().name());

        Pet pet = new Pet();
        pet.setId(1);
        pet.setName(faker().animal().name());
        pet.setCategory(category);
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().log().all().body(pet).contentType("application/json")
                .when().post(properties.baseUrl + "/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test(testName = "GET request for Pet")
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given().log().all().pathParam("petId", 1).
                when().baseUri(properties.baseUrl + "/v2/pet/{petId}").
                then().statusCode(200).log().all();
    }

    @Test(testName = "PUT request for Pet")
    public void putRequest() {
        Pet petRequestBody = new PetTestDataGenerator().generatePet();
        given().log().all().body(petRequestBody).contentType("application/json")
                .put(properties.baseUrl + "/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void deleteRequest() {
        given().log().all().pathParam("petId", 1)
                .delete(properties.baseUrl + "/v2/pet/{petId}")
                .then().log().all().statusCode(200);
    }
}
