package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Airports {

    @Test
    public void homework() throws InterruptedException {

        String apiUrl="https://airportgap.dev-tester.com/api/airports?page=";
        Map<String,Object> deserializedResponse=new HashMap<>();
        Map<String,Object> smallInfo=new HashMap<>();
        int count=0;

        for (int page=1;page<204;page++){
                    deserializedResponse=
                            RestAssured.given().header("Accept", "Application/json")
                            .when().get(apiUrl+page)
                            .then().statusCode(200).extract().response().as(new TypeRef<Map<String,Object>>() {
                            });

            Thread.sleep(500);

        List<Map<String,Object>>data=(List<Map<String,Object>>)deserializedResponse.get("data");
        for (int i=0;i<data.size();i++) {
            Map<String, Object> airport = data.get(i);
            System.out.println(airport.get("id"));
            count++;
            System.out.println(count);
            Assert.assertTrue(airport.get("id").toString().length()==3);
//
//            smallInfo.put("name of "+airport.get("id").toString(),airport.get("name"));
//            smallInfo.put("city of "+airport.get("id").toString(),airport.get("city"));
//        }
//        }
//        System.out.println(smallInfo.get("city of ZXT").toString());
    }}}
    @Test
    public void AirPortReRun() throws InterruptedException {

        Map<String,Object> deserializedResponse=new HashMap<>();

        RestAssured.baseURI="https://airportgap.dev-tester.com";
        RestAssured.basePath="api/airports";

        deserializedResponse=RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200).extract().response().as(new TypeRef<Map<String,Object>>() {
                });
        List<Map<String,Object>> data=(List<Map<String,Object>>)deserializedResponse.get("data");
        Map<String,String>pageControl=(Map<String,String>)deserializedResponse.get("links");

        List<String>nextUrl=new ArrayList<>();
        nextUrl.add(pageControl.get("next"));

        for (Map<String,Object> datum:data){
            Assert.assertTrue(datum.get("id").toString().length()==3);
            System.out.println("Airport ID: "+datum.get("id"));
            Map<String,Object>attrib=(Map<String,Object>)datum.get("attributes");
            System.out.println("Airport Name: "+attrib.get("name"));
            System.out.println("City: "+attrib.get("city"));
            System.out.println("================================================");
        }

        int pageIndex=0;
        while (pageControl.get("next").contains("page")){

            deserializedResponse=RestAssured.given().accept("application/json")
                    .when().get(nextUrl.get(pageIndex))
                    .then().statusCode(200).extract().response().as(new TypeRef<Map<String,Object>>() {
                    });
            pageControl=(Map<String,String>)deserializedResponse.get("links");
            nextUrl.add(pageControl.get("next"));

            data=(List<Map<String,Object>>)deserializedResponse.get("data");

            for (Map<String,Object> datum:data){
                Assert.assertTrue(datum.get("id").toString().length()==3);
                System.out.println("Airport ID: "+datum.get("id"));
                Map<String,Object>attrib=(Map<String,Object>)datum.get("attributes");
                System.out.println("Airport Name: "+attrib.get("name"));
                System.out.println("City: "+attrib.get("city"));
                System.out.println("================================================");
            }
            Thread.sleep(600);
            pageIndex++;
        }
    }
}