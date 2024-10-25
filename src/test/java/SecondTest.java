import io.restassured.RestAssured;
import org.testng.annotations.Test;
import utils.Properties;

public class SecondTest {
    Properties properties = new Properties();

    @Test
    public void testForHttp200Status() {
        RestAssured.given().log().all().when().baseUri(properties.baseUrl + "/v2/pet/1").then().statusCode(200);
    }
}
