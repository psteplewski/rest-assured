import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.Test;
import utils.Properties;

import static io.restassured.RestAssured.given;

public class FirstTest {
    Properties properties = new Properties();

    @Test
    public void givenNonExistingPetIdWhenGetPetThenPetNotFoundTest() {

        given().filter(new AllureRestAssured()).log().all().when().get(properties.baseUrl + "/v2/pet/0").then().statusCode(404);
    }
}