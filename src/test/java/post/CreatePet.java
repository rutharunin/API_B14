package post;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.PetPojo;
import utils.PayloadUtils;

public class CreatePet {
    @Test
    public void test() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload("11111","hohoho","healthy"))
                .when().post()
                .then().contentType(ContentType.JSON)
                .statusCode(200).log().all()
                .extract().response();
    }
    @Test
    public void test2(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
        PetPojo petPojo=new PetPojo();
        petPojo.setId(22222);
        petPojo.setName("hahaha");
        petPojo.setStatus("young");

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petPojo)
                .when().post()
                .then().statusCode(200)
                .log().all()
                .extract().response();

    }
}
