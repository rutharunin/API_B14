package goRest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.GoRestPojo;

public class GoRestReRun {

    @Test
    public void endToEnd(){
            int id=makeUser("haha","ebrdsruiui@notmail.com","female","Active");
        System.out.println(id);
            GoRestPojo goRestPojo=getUser(id);
            Assert.assertEquals("haha",goRestPojo.getData().get("name").toString());
            String nameFromServer=updateUser(id,"huhuhu");
            Assert.assertEquals("huhuhu",nameFromServer);
    }

    private int makeUser(String name,String email,String gender,String status){
        RestAssured.baseURI="https://gorest.co.in/public-api";
        RestAssured.basePath="users";

        Response response=  RestAssured.given()
                .accept(ContentType.JSON)
                .header("Authorization","Bearer 106b8f21995c73c87f315a314df2a751097151c10820b7bf28bed937c94a191f")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"name\": \""+name+"\",\n" +
                        "  \"email\": \""+email+"\",\n" +
                        "  \"gender\": \""+gender+"\",\n" +
                        "  \"status\": \""+status+"\"\n" +
                        "}")
                .when().post()
                .then().statusCode(200)
                .extract().response();
        GoRestPojo goRestPojo=response.as(GoRestPojo.class);
        int id=(int)goRestPojo.getData().get("id");

        return id;
    }
    private GoRestPojo getUser(int id){

//        RestAssured.baseURI="https://gorest.co.in/public-api";
//        RestAssured.basePath="users";

        Response response=RestAssured.given()
                .header("Authorization","Bearer 106b8f21995c73c87f315a314df2a751097151c10820b7bf28bed937c94a191f")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().get("https://gorest.co.in/public-api/users/"+id)
                .then().statusCode(200)
                .extract().response();
                GoRestPojo goRestPojo=response.as(GoRestPojo.class);
                return goRestPojo;
    }
    private String updateUser(int id, String newName){

        RestAssured.baseURI="https://gorest.co.in/public-api";
        RestAssured.basePath="users/"+id;

        Response response=
        RestAssured.given()
                .header("Authorization","Bearer 106b8f21995c73c87f315a314df2a751097151c10820b7bf28bed937c94a191f")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
//                .body("{name :"+newName+"}")
                .body("{\n" +
                        "  \"name\": \""+newName+"\"\n" +
                        "}")
                .when().put()
                .then().statusCode(200)
                .extract().response();
                GoRestPojo goRestPojo=response.as(GoRestPojo.class);
                return goRestPojo.getData().get("name").toString();

    }
}
