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

import static io.restassured.RestAssured.given;

public class PetTests extends TestDataGenerator {

    Properties properties = new Properties();
    Pet actualPet;
    private final RequestSpecification headers = RequestConfigurationBuilder.getDefaultRequestSpecification();
    private final Pet requestBody = new PetTestDataGenerator().generatePetData();


    @Test
    @Description("POST request for Pet creation")
    @TmsLink("TC-123") //ID test case form Test Management system, configurable in allure.properties
    @Severity(SeverityLevel.CRITICAL)
    public void postPetRequest() {

        actualPet = given().spec(headers)
                .body(requestBody)
                .when().post(properties.baseUrl + "/v2/pet")
                .then().statusCode(HttpStatus.SC_OK).log().all()
                .extract().as(Pet.class);

        Assertions.assertThat(actualPet).describedAs("Send Pet was different than received by API").isEqualToComparingFieldByFieldRecursively(requestBody);
    }

    /**
     * Request for get data about Pet
     *
     * @param expectedHTTPStatus excepted HTTP response status code
     * @param id petId
     * @return object of this class
     */
    public PetTests getRequest(int expectedHTTPStatus, int id) {
        given().spec(headers).pathParam("petId", id).
                when().get(properties.baseUrl + "/v2/pet/{petId}").
                then().statusCode(expectedHTTPStatus).log().all();
        return this;
    }

    /**
     * Request for update Pet
     *
     * @param expectedHTTPStatus excepted HTTP response status code
     * @return object of this class
     */
    public PetTests putRequest(int expectedHTTPStatus) {
        given().spec(headers)
                .body(requestBody)
                .put(properties.baseUrl + "/v2/pet")
                .then().statusCode(expectedHTTPStatus).log().all();
        return this;
    }

    /**
     * Request for delete Pet
     *
     * @param expectedHTTPStatus excepted HTTP response status code
     * @param id petId
     * @return object of this class
     */
    public PetTests deleteRequest(int expectedHTTPStatus, int id) {
        given().spec(headers).pathParam("petId", id)
                .delete(properties.baseUrl + "/v2/pet/{petId}")
                .then().statusCode(expectedHTTPStatus).log().all();
        return this;
    }

    @Test
    public void test() {
        putRequest(HttpStatus.SC_OK).deleteRequest(HttpStatus.SC_OK,2).getRequest(HttpStatus.SC_NOT_FOUND, 2);
    }
}
