import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class SecondTest
{
    Properties properties =new Properties();
    @Test
    public void testForHttp200Status() {
        RestAssured.given().when().baseUri(properties.baseUrl+"/v2/pet/1").then().statusCode(200);
    }
}
