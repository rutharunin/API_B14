package get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.MarvelPojo;

import java.util.ArrayList;
import java.util.List;

public class Marvel {


    @Test
    public void marvelCharactersTest(){

        RestAssured.baseURI="http://gateway.marvel.com";
        RestAssured.basePath="v1/public/characters";

        Response response=RestAssured.given().accept("application/json")
                .queryParam("ts",1)
                .queryParam("apikey","51d5508b8bb07bc8c3ae4ff030e4a950")
                .queryParam("hash","819a13c8716cf506571d5461ae73c4ff")
                .queryParam("limit",20)
                .when().get()
                .then().statusCode(200).extract().response();
//              .then().statusCode(200).log().body().extract().response();
//       .log().body >>> prints what's in the console

        MarvelPojo deserializedResponse=response.as(MarvelPojo.class);
        List<String>namesOfChar=new ArrayList<>();
        for (int i=0;i<deserializedResponse.getData().getResults().size();i++){
            namesOfChar.add(deserializedResponse.getData().getResults().get(i).getName());
        }

        Assert.assertEquals(namesOfChar.size(),deserializedResponse.getData().getCount());

        response=RestAssured.given().accept("application/json")
                .queryParam("ts",1)
                .queryParam("limit",100)
                .queryParam("apikey","51d5508b8bb07bc8c3ae4ff030e4a950")
                .queryParam("hash","819a13c8716cf506571d5461ae73c4ff")
                .when().get()
                .then().statusCode(200).extract().response();
        deserializedResponse=response.as(MarvelPojo.class);

        boolean hasAntMan=false;
        for (int i=0;i<deserializedResponse.getData().getResults().size();i++){
            if(deserializedResponse.getData().getResults().get(i).getName().contains("Ant-Man")){hasAntMan=true;}
        }
        Assert.assertEquals(deserializedResponse.getData().getCount(),100);
        Assert.assertTrue(hasAntMan);
    }
}
