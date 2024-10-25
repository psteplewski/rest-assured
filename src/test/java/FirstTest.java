import org.testng.annotations.Test;
import utils.Properties;

import static io.restassured.RestAssured.given;

public class FirstTest {
    Properties properties = new Properties();

    @Test
    public void givenNonExistingPetIdWhenGetPetThenPetNotFoundTest() {

        given().log().all().when().get(properties.baseUrl + "/v2/pet/0").then().statusCode(404);
    }
}