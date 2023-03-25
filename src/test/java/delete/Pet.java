package delete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class Pet {

    @Test
    public void deletePetTest(){

        //98765
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().delete("98765")
                .then().statusCode(200)
                .extract().response();

    }
}
