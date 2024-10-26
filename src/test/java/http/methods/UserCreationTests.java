package http.methods;

import org.testng.annotations.Test;
import pojo.user.User;
import utils.Properties;

import static io.restassured.RestAssured.given;


public class UserCreationTests {
    Properties properties = new Properties();

    @Test
    public void createUserAndCheckIfExists() {
        User userBodyRequest = new User();
        userBodyRequest.setId(1);
        userBodyRequest.setUsername("firstuser");
        userBodyRequest.setFirstName("Krzysztof");
        userBodyRequest.setLastName("Kowalski");
        userBodyRequest.setEmail("krzysztof@test.com");
        userBodyRequest.setPassword("password");
        userBodyRequest.setPhone("+123456789");
        userBodyRequest.setUserStatus(1);

        given().log().all().body(userBodyRequest).contentType("application/json")
                .when().post(properties.baseUrl + "/v2/user")
                .then().log().all().statusCode(200);

        given().log().all().contentType("application/json").pathParam("username", "firstuser")
                .when().get(properties.baseUrl + "/v2/user/{username}").then().log().all().statusCode(200);
    }
}
