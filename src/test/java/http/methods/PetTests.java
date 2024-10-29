package http.methods;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.assertj.core.api.Assertions;
import pojo.pet.Pet;
import request.configuration.RequestConfigurationBuilder;
import utils.PetTestDataGenerator;
import utils.Properties;
import utils.TestDataGenerator;

import io.qameta.allure.restassured.AllureRestAssured;


import static io.restassured.RestAssured.given;

public class PetTests extends TestDataGenerator {

    Properties properties = new Properties();
    Pet actualPet;
    private final RequestSpecification headers = RequestConfigurationBuilder.getDefaultRequestSpecification();
    ;
    private final Pet requestBody = new PetTestDataGenerator().generatePet();


    @Test
    @Description("Description field")
    @TmsLink("TC-123") //ID test case form Test Management system, configurable in allure.properties
    @Severity(SeverityLevel.CRITICAL)
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        actualPet = given().spec(headers)
                .body(requestBody).contentType("application/json")
                .when().post(properties.baseUrl + "/v2/pet")
                .then().statusCode(HttpStatus.SC_OK).log().all()
                .extract().as(Pet.class);

        Assertions.assertThat(actualPet).describedAs("Send Pet was different than received by API").isEqualToComparingFieldByFieldRecursively(requestBody);
    }

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given().spec(headers).pathParam("petId", 1).
                when().get(properties.baseUrl + "/v2/pet/{petId}").
                then().statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void putRequest() {
        given().spec(headers)
                .body(requestBody)
                .put(properties.baseUrl + "/v2/pet")
                .then().statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void deleteRequest() {
        given().spec(headers).pathParam("petId", 1)
                .delete(properties.baseUrl + "/v2/pet/{petId}")
                .then().statusCode(HttpStatus.SC_OK).log().all();
    }
}
