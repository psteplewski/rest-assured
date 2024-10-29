package http.methods;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.Test;
import pojo.user.User;
import utils.Properties;

import static io.restassured.RestAssured.given;


public class UserCreationTests {
    Properties properties = new Properties();

    @Test
    @Description("Description field")
    @TmsLink("TC-123") //ID test case form Test Management system, configurable in allure.properties
    @Severity(SeverityLevel.CRITICAL)
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

        given().filter(new AllureRestAssured()).log().all().body(userBodyRequest).contentType("application/json")
                .when().post(properties.baseUrl + "/v2/user")
                .then().log().all().statusCode(200);

        given().filter(new AllureRestAssured()).log().all().contentType("application/json").pathParam("username", "firstuser")
                .when().get(properties.baseUrl + "/v2/user/{username}").then().log().all().statusCode(200);
    }
}
