package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Football {
    @Test
    public void test(){

        Response response=
                RestAssured.given().accept(ContentType.JSON)
                        .header("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a")
                .when().get("http://api.football-data.org/v2/competitions")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().response();
        Map<String,Object>responseMap=response.as(new TypeRef<Map<String,Object>>() {
        });
        List<Map<String,Object>>xx=(List<Map<String,Object>>)responseMap.get("competitions");
        boolean hasPremier=false;
        for (int i=0;i<xx.size();i++){
            if (xx.get(i).get("name").equals("Premier Liga")){hasPremier=true;}
            if (xx.get(i).get("name").equals("Premier Liga")){
                System.out.println(xx.get(i).get("id"));

            }
//            System.out.println(xx.get(i).get("name"));
        }
        Assert.assertTrue(hasPremier);
    }
    @Test
    public void test2(){
        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";

        Response response=
                RestAssured.given().accept(ContentType.JSON)
                        .header("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a")
                        .when().get()
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract().response();

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();
        List<Map<String,Object>> competitions=jsonPath.getList("competitions");
        for (int i=0;i<competitions.size();i++){
            Map<String,Object>competitionMap=competitions.get(i);
            if (competitionMap.get("name").equals("Premier Liga")){
                int id=(int)competitionMap.get("id");
                Assert.assertEquals(id,2035);
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
                .body("competitions.find {it.name=='Premier Liga'}.id", Matchers.is(2035))
                .extract().response();
    }
    @Test
    public void test4(){
        RestAssured.baseURI="http://api.football-data.org";
        RestAssured.basePath="v2/competitions";

        Response response=
                RestAssured.given().accept(ContentType.JSON)
                        .header("X-Auth-Token","c55b7a64e8424d46a52051bce36d1c0a")
                        .when().get()
                        .then().statusCode(200).contentType(ContentType.JSON)
                        .extract().response();
        Map<String,Object>mapResponse=response.as(new TypeRef<Map<String,Object>>() {
        });
        List<Map<String,Object>>competitions=(List<Map<String,Object>>)mapResponse.get("competitions");
        for (int i=0;i<competitions.size();i++){
            Map<String,Object>area=(Map<String,Object>)competitions.get(i).get("area");
            if(area.get("name").equals("Australia")){
                Assert.assertEquals("AUS",area.get("countryCode"));
            }
            System.out.println(area.get("name"));
        }
        String countryCode=response.path("competitions.find{it.area.name=='Australia'}.area.countryCode");
        Assert.assertEquals("AUS",countryCode);
    }
}
