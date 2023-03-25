package automationExercise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

public class SignupValidation {
    @Test
    public void signupTest() {
        RestAssured.baseURI = "https://automationexercise.com";
        RestAssured.basePath = "api/getUserDetailByEmail";

        RestAssured.given()
                .queryParam("email","toi@email.com")
                .accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .log().all();

    }
}
