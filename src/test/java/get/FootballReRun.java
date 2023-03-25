package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.FootballPojo;
import pojo.GoRestPojo;

import java.util.List;
import java.util.Map;

public class FootballReRun {


    @Test
    public void test1(){


        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";

        Response response=
        RestAssured.given().accept(ContentType.JSON)
                .header("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a")
                .when().get()
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
        Map<String,Object>responseMap=response.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String,Object>> competittions=(List<Map<String,Object>>)responseMap.get("competitions");

        for (int i=0;i<competittions.size();i++){
            if (competittions.get(i).get("name").equals("Premier Liga")){
                Assert.assertTrue(true);
                System.out.println(competittions.get(i).get("id"));
            }
        }


    }
    @Test
    public void test2(){

        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";

        Response response=
        RestAssured.given().accept(ContentType.JSON)
                .header("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a")
                .when().get()
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        JsonPath jsonPath=response.jsonPath();
        List<Map<String,Object>> competitions=jsonPath.getList("competitions");
        for (int i=0;i<competitions.size();i++){
            if (competitions.get(i).get("name").equals("Premier Liga")){
                Assert.assertEquals((int)competitions.get(i).get("id"),2035);
            }
        }
    }
    @Test
    public void test3(){

        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";

        RestAssured.given().accept(ContentType.JSON)
                .header("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a")
                .when().get()
                .then().statusCode(200)
                .body("competitions.find{it.name=='Premier Liga'}.id", Matchers.is(2035));
//                .extract().response();
    }
    @Test
    public void test4(){

        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";

        Response response=
        RestAssured.given().accept(ContentType.JSON)
                .header("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a")
                .when().get()
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        Map<String,Object>mapResponse=response.as(new TypeRef<Map<String,Object>>() {
        });


        List<Map<String,Object>>competitions=(List<Map<String,Object>>)mapResponse.get("competitions");
        for (int i=0;i<competitions.size();i++){
            if (competitions.get(i).get("name").equals("Australia")){
                Assert.assertTrue(competitions.get(i).get("countryCode").equals("AUS"));
            }
        }

    }
    @Test
    public void test5(){

        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";

        Response response=
                RestAssured.given().accept(ContentType.JSON)
                        .header("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a")
                        .when().get()
                        .then().statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().response();
        FootballPojo footballPojo=response.as(FootballPojo.class);
        for (int i=0;i<footballPojo.getCompetitions().size();i++){
            if (footballPojo.getCompetitions().get(i).getArea().getName().equals("Australia")){
                Assert.assertEquals(footballPojo.getCompetitions().get(i).getArea().getCountryCode(),"AUS");
            }
        }
    }
}
