package http.methods;

import org.testng.annotations.Test;
import utils.Properties;

import static io.restassured.RestAssured.given;


public class UserCreationTests {
    Properties properties = new Properties();

    String postRequestBody = "{\n" +
            "  \"id\": 445,\n" +
            "  \"username\": \"firstuser\",\n" +
            "  \"firstName\": \"Krzysztof\",\n" +
            "  \"lastName\": \"Kowalski\",\n" +
            "  \"email\": \"krzysztof@test.com\",\n" +
            "  \"password\": \"password\",\n" +
            "  \"phone\": \"+123456789\",\n" +
            "  \"userStatus\": 1\n" +
            "}";

    @Test
    public void createUser() {
        given().log().all().body(postRequestBody).contentType("application/json").when().post(properties.baseUrl + "/v2/user").then().statusCode(200);
    }
}
