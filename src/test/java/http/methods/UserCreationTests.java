package http.methods;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pojo.Response;
import pojo.user.User;
import request.configuration.RequestConfigurationBuilder;
import utils.Properties;
import utils.UsetTestDataGenerator;

import static io.restassured.RestAssured.given;


public class UserCreationTests {
    Properties properties = new Properties();
    private final RequestSpecification headers = RequestConfigurationBuilder.getDefaultRequestSpecification();
    private final User requestUserBody = new UsetTestDataGenerator().generateUserData();


    @Test
    @Description("Create user with random data using POST request anc check if user was created by GET request")
    @TmsLink("TC-123") //ID test case form Test Management system, configurable in allure.properties
    @Severity(SeverityLevel.CRITICAL)
    public void postUserRequest() {
        Response response = given().spec(headers)
                .body(requestUserBody)
                .when().post(properties.baseUrl + "/v2/user")
                .then().statusCode(HttpStatus.SC_OK).log().all()
                .extract().as(Response.class);

        Response expectedResponse = new Response();
        expectedResponse.setCode(HttpStatus.SC_OK);
        expectedResponse.setType("unknown");
        expectedResponse.setMessage(String.valueOf(requestUserBody.getId()));

        Assertions.assertThat(response).describedAs("Created User was not created by API").isEqualToComparingFieldByFieldRecursively(expectedResponse);
    }

    @Test
    public void getUserRequest() {
        given().spec(headers)
                .pathParam("username", requestUserBody.getUsername())
                .when().get(properties.baseUrl + "/v2/user/{username}")
                .then().statusCode(HttpStatus.SC_OK).log().all()
                .extract().as(Response.class);
    }
}
