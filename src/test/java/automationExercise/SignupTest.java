package automationExercise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import utils.PayloadUtils;

public class SignupTest {
    @Before
    public void setup(){
        RestAssured.baseURI="https://automationexercise.com";
        RestAssured.basePath="api/createAccount";
    }
    @Test
    public void signUp(){

        Response response=
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(PayloadUtils.getAutomationExercisePayload(
                                "Toitoi","toitoi@email.com", "1111222",
                                "","19","January","2000","Ross",
                                "Arunin","Sym","333 Json", "api",
                                "Singapore","IL","Chicago","99999"))
                        .when().post()
                        .then().statusCode(200)
                        .log().all()
                        .extract().response();
    }
}
