package get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Countries {

    @Before
    public void setup(){

        RestAssured.baseURI="https://restcountries.com";
        RestAssured.basePath="v3.1/all";
    }

    @Test
    public void countryAndCapitalTest(){

        Response response=RestAssured.given()
                .accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .extract().response();
        JsonPath jsonPath=response.jsonPath();
        List<Map<String,Object>>objectList= jsonPath.getList(".");
        System.out.println("number of all countries"+objectList.size());

        Map<String,String>country_Capital=new HashMap<>();

        for (Map<String,Object>country:objectList){
            Map<String,Object>nameMap=(Map<String,Object>)country.get("name");
            List<String>capitalist=(List<String>)country.get("capital");
            if (!(capitalist==null)){
                country_Capital.put(nameMap.get("common").toString(),capitalist.get(0));}
            else {country_Capital.put(nameMap.get("common").toString(),"No capital");
                System.out.println("country without capital: "+nameMap.get("common"));}
        }
        Assert.assertEquals(objectList.size(),country_Capital.size());
    }
    @Test
    public void lookForBangkok(){

        Response response=RestAssured.given()
                .accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .extract().response();
        JsonPath jsonPath=response.jsonPath();
        List<Map<String,Object>>objectList= jsonPath.getList(".");
        List<String>capitalList=new ArrayList<>();
        Map<String,Object>nameMap=new HashMap<>();

        for (Map<String,Object>country:objectList){
            nameMap=(Map<String,Object>)country.get("name");
            capitalList=(List<String>)country.get("capital");
            if (nameMap.get("common").equals("Thailand")){
                Assert.assertEquals("Bangkok",capitalList.get(0));
                System.out.println(capitalList.get(0));
            }
        }

    }
    @Test
    public void test(){

        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .body("[0].name.common", Matchers.equalTo("United Kingdom"))
                .body("[0].capital[0]",Matchers.is("London"));
        //Matchers.is AND Matchers.equalTo are similar
    }
}
