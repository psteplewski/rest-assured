package http.methods;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import pojo.pet.Category;
import pojo.pet.Pet;
import pojo.pet.Tag;
import utils.PetTestDataGenerator;
import utils.Properties;
import utils.TestDataGenerator;

import java.util.Collections;
import io.qameta.allure.restassured.AllureRestAssured;


import static io.restassured.RestAssured.given;

public class PetTests extends TestDataGenerator {

    Properties properties = new Properties();

    @Test
    @Description("Description field")
    @TmsLink("TC-123") //ID test case form Test Management system, configurable in allure.properties
    @Severity(SeverityLevel.CRITICAL)
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

        given().filter(new AllureRestAssured()).log().all().body(pet).contentType("application/json")
                .when().post(properties.baseUrl + "/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given().filter(new AllureRestAssured()).log().all().pathParam("petId", 1).
                when().baseUri(properties.baseUrl + "/v2/pet/{petId}").
                then().statusCode(200).log().all();
    }

    @Test
    public void putRequest() {
        Pet petRequestBody = new PetTestDataGenerator().generatePet();
        given().filter(new AllureRestAssured()).log().all().body(petRequestBody).contentType("application/json")
                .put(properties.baseUrl + "/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void deleteRequest() {
        given().filter(new AllureRestAssured()).log().all().pathParam("petId", 1)
                .delete(properties.baseUrl + "/v2/pet/{petId}")
                .then().log().all().statusCode(200);
    }
}
