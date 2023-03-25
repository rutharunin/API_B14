package postpost;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;

public class Pet {
    @Test
    public void createPetTest(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        Response response=
        RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": 82828282,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"jAnGa\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"https://djdjd.ic\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"waiting\"\n" +
                        "}").when().post()
                .then().statusCode(200)
                .extract().response();

        PetPojo parsedResponse=response.as(PetPojo.class);
        long actualPetId=parsedResponse.getId();
        Assert.assertEquals(82828282,actualPetId);
        String actualPetName=parsedResponse.getName();
        Assert.assertEquals("jAnGa",actualPetName);

        //then get
        response=RestAssured.given().accept("application/json")
                .when().get("82828282")
                .then().statusCode(200)
                .extract().response();
        PetPojo parsedResponse2=response.as(PetPojo.class);
        Assert.assertEquals(82828282,parsedResponse2.getId());

    }
}
