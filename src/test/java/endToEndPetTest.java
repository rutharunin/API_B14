import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.PayloadUtils;

import java.util.Map;

public class endToEndPetTest {

    String petName="Janga";
    String petId="9991";
    String petStatus="dead";

    @Before
    public void setup(){

        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet/";
    }

    @Test
    public void testPet(){

        //Assert petId does not exist
        Response response=getPet(petId,404);
        Assert.assertEquals(PayloadUtils.negativeGetPetPayLoad(),response.prettyPrint().toString());

        //Assert Create pet
        createPet(petId,petName,petStatus);
        response=getPet(petId,200);
        Map<String,Object>parsedResponse=response.as(new TypeRef<Map<String,Object>>() {
        });
        Assert.assertEquals(petName,parsedResponse.get("name"));
        //Assert update pet
        updatePet(petId,petName,"resurrect");
        response=getPet(petId,200);
        parsedResponse=response.as(new TypeRef<Map<String,Object>>() {
        });
        Assert.assertEquals("resurrect",parsedResponse.get("status"));

        //Assert delete pet
        deletePet(petId);
        response=getPet(petId,404);
        Assert.assertEquals(PayloadUtils.negativeGetPetPayLoad(),response.prettyPrint().toString());
    }
    Response getPet(String id,int statusCode){

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get(id)
                .then().statusCode(statusCode)
                .extract().response();

        return response;
    }
    void createPet(String id,String name,String status){

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload(id,name,status))
                .when().post()
                .then().statusCode(200)
                .extract().response();
        Map<String,Object>parsedResponse=response.as(new TypeRef<Map<String,Object>>() {
        });
        Assert.assertEquals(name,parsedResponse.get("name"));
    }
    void updatePet(String id,String name,String status){

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload(id,name,status))
                .when().put()
                .then().statusCode(200)
                .extract().response();
        Map<String,Object>parsedResponse=response.as(new TypeRef<Map<String,Object>>() {
        });
        Assert.assertEquals("resurrect",parsedResponse.get("status"));
    }
    void deletePet(String id){

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().delete(id)
                .then().statusCode(200)
                .extract().response();
        Map<String,Object>parsedResponse=response.as(new TypeRef<Map<String,Object>>() {
        });
        Assert.assertEquals(200,parsedResponse.get("code"));
        Assert.assertEquals("unknown",parsedResponse.get("type"));
        Assert.assertEquals(petId,parsedResponse.get("message"));
    }
}