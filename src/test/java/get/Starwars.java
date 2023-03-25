package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Starwars {

    @Test
    public void CountOfCharacters(){

        String apiUrl="https://swapi.dev/api/people?page=";
        Map<String,Object> deserializedResponse=new HashMap<>();
        Map<String,Object> numberOfFemale=new HashMap<>();
        Map<String,Object> numberOfPeople=new HashMap<>();
        Map<String,Object> numberOfMale=new HashMap<>();
        Map<String,Object> numberOfFluidGender=new HashMap<>();
        List<String> allFemaleNames=new ArrayList<>();
        List<String> allMaleNames=new ArrayList<>();
        List<String> allNoGenderNames=new ArrayList<>();
        int female=1;
        int people=1;
        int male=1;
        int noGender=1;

        for (int page=1;page<10;page++){
            deserializedResponse=
                    RestAssured.given().header("Accept", "Application/json")
                    .when().get(apiUrl+page)
                    .then().statusCode(200).extract().response().as(new TypeRef<Map<String, Object>>() {
                            });

        List<Map<String,Object>> results=(List<Map<String,Object>>)deserializedResponse.get("results");

        for (int i=0;i<results.size();i++) {
            Map<String, Object> result = results.get(i);
            if (result.get("gender").toString().equals("female")){
                numberOfFemale.put("amountOfFemale",female);
                allFemaleNames.add(result.get("name").toString());
                female++;
                }
            if (result.containsKey("name")){
                numberOfPeople.put("amountOfPeople",people);
                people++;
            }
            if (result.get("gender").toString().equals("male")){
                numberOfMale.put("amountOfMale",male);
                allMaleNames.add(result.get("name").toString());
                male++;
            }
            if (!result.get("gender").toString().equals("male") && !result.get("gender").toString().equals("female")){
                numberOfFluidGender.put("amountOfFluidGender",noGender);
                allNoGenderNames.add(result.get("name").toString());
                noGender++;
            }
//            System.out.println(result.get("gender"));
            }
        }
        for (int i=0;i<allFemaleNames.size();i++){
            System.out.println("female: "+allFemaleNames.get(i));
        }
        for (int i=0;i<allMaleNames.size();i++){
            System.out.println("male: "+allMaleNames.get(i));
        }
        for (int i=0;i<allNoGenderNames.size();i++){
            System.out.println("no gender: "+allNoGenderNames.get(i));
        }


        Assert.assertTrue(numberOfPeople.get("amountOfPeople").toString().equals("82"));
        System.out.println("amount of female: "+numberOfFemale.get("amountOfFemale"));
        System.out.println("amount of male: "+numberOfMale.get("amountOfMale"));
        System.out.println("amount of things without gender: "+numberOfFluidGender.get("amountOfFluidGender"));
    }
    @Test
    public void SecondTime(){

        Map<String,Object> deserializedResponse=new HashMap<>();
        deserializedResponse=
                RestAssured.given().header("Accept", "Application/json")
                        .when().get("https://swapi.dev/api/people")
                        .then().statusCode(200).extract().response().as(new TypeRef<Map<String, Object>>() {
                        });
        int expectedCount=(int)deserializedResponse.get("count");
        String nextPageUrl=(String)deserializedResponse.get("next");
        int totalCharacterCount=0;
        List<Map<String,Object>> results=(List<Map<String,Object>>)deserializedResponse.get("results");
        totalCharacterCount+=results.size();
        while (nextPageUrl != null) {
            deserializedResponse=
                    RestAssured.given().header("Accept", "Application/json")
                            .when().get(nextPageUrl)
                            .then().statusCode(200).extract().response().as(new TypeRef<Map<String, Object>>() {
                            });

            nextPageUrl = (String) deserializedResponse.get("next");
            results = (List<Map<String, Object>>) deserializedResponse.get("results");
            totalCharacterCount += results.size();
        }
        Assert.assertEquals(totalCharacterCount, expectedCount);
    }
}
