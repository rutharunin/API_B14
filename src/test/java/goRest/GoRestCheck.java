package goRest;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import pojo.GoRestPojo;

import java.util.Map;

public class GoRestCheck {
    @Test
    public void endToEnd(){
        int whateverId=
        makeUser("hohoho","nomail@notemail.th","female","Active");

    }

    private int makeUser(String name,String email,String gender,String status){

        Response response= RestAssured.given()
          .accept(ContentType.JSON)
          .contentType(ContentType.JSON)
          .header("Authorization","Bearer 106b8f21995c73c87f315a314df2a751097151c10820b7bf28bed937c94a191f")
          .body(   "{\n" +
                  "  \"name\": \""+name+"\",\n" +
                  "  \"email\": \""+email+"\",\n" +
                  "  \"gender\": \""+gender+"\",\n" +
                  "  \"status\": \""+status+"\"\n" +
                  "}")
          .when().post("https://gorest.co.in/public-api/users")

          .then().statusCode(200)
                .contentType(ContentType.JSON)
                //.log().all()
                .extract().response();
        GoRestPojo goRestPojo = response.as(GoRestPojo.class);
        int id=(int)goRestPojo.getData().get("id");

        return id;
    }
}
