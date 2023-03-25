package put;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.PayloadUtils;
public class UpdatePet {

    @Test
    public void test(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        Response response=
                RestAssured.given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(PayloadUtils.getPetPayload("11111111111111","hefuegh","fdsds"))
                        .when().put()
                        .then().statusCode(200)
                        .log().all()
                        .extract().response();

    }

}
