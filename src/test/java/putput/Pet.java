package putput;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import utils.PayloadUtils;

public class Pet {

    @Test
    public void updatePetTest(){

        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType("application/json")
                .body(PayloadUtils.getPetPayload("82828282","Chamlee","sleeping"))
                .when().put()
                .then().statusCode(200)
                .extract().response();
        PetPojo deserializedResponse=response.as(PetPojo.class);

        Assert.assertEquals("Chamlee",deserializedResponse.getName());
        Assert.assertEquals("sleeping",deserializedResponse.getStatus());

    }



    @Test
    public void end2EndTest(){

        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
    /*
    create POST
    update PUT
    list GET
     */
    String petName="Chamcham";
    String petId="77777";
    String petStatus="shitting";

        RequestSpecification reqSpec=new RequestSpecBuilder().setAccept("application/json")
                .setContentType("application/json").build();
        ResponseSpecification responseSpec=new ResponseSpecBuilder().expectStatusCode(200).build();

    Response response=
    RestAssured.given().spec(reqSpec)
            .body(PayloadUtils.getPetPayload(petId,petName,petStatus))
            .when().post()
            .then().spec(responseSpec)
            .extract().response();

    PetPojo deserializedResponse=response.as(PetPojo.class);
    Assert.assertEquals(petName,deserializedResponse.getName());
    Assert.assertEquals(petId,String.valueOf(deserializedResponse.getId()));
    Assert.assertEquals(petStatus,deserializedResponse.getStatus());

    Response putResponse=
    RestAssured.given().spec(reqSpec)
            .body(PayloadUtils.getPetPayload(petId,petName,"vomit"))
            .when().put()
            .then().spec(responseSpec)
            .extract().response();

    PetPojo deserializedPutResponse=putResponse.as(PetPojo.class);
    Assert.assertEquals("vomit",deserializedPutResponse.getStatus());

    Response getResponse=
    RestAssured.given()
            .accept(ContentType.JSON)
            .when().get(petId)
            .then().spec(responseSpec)
            .extract().response();

    PetPojo deserializedGetResponse=getResponse.as(PetPojo.class);
    Assert.assertEquals(petId,String.valueOf(deserializedGetResponse.getId()));
    Assert.assertEquals(petName,deserializedGetResponse.getName());
//    Assert.assertEquals(petStatus,deserializedGetResponse.getStatus());

    }
}
