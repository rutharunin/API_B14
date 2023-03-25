package utils;

import io.cucumber.java.sl.In;

public class PayloadUtils {

    public static String getPetPayload(String id, String name, String status){
        String payload = "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"hatiko\"\n" +
                "  },\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://s3.amazon.com\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+status+"\"\n" +
                "}";
        return payload;
    }
    public static String negativeGetPetPayLoad(){

        String payload="{\n" +
                "    \"code\": 1,\n" +
                "    \"type\": \"error\",\n" +
                "    \"message\": \"Pet not found\"\n" +
                "}";
        return payload;
    }
    public static String getSlackPayload(String message){

        String payload="{\n" +
                "  \"channel\": \"C04JYTRQTRR\",\n" +
                "  \"text\": \"Mahmut: "+message+"\"\n" +
                "}";

return payload;
    }
    public static String getAutomationExercisePayload(String name, String email, String password, String title,
                                                      String day, String month, String year, String firstName,
                                                      String lastName, String company, String address1,
                                                      String address2, String country, String state,
                                                      String city, String zip){

                        String payload=("{\n" +
                                "        \"name\": \""+name+"\",\n" +
                                "        \"email\": \""+email+"\",\n" +
                                "        \"password\": \""+password+"\",\n" +
                                "        \"title\": \""+title+"\",\n" +
                                "        \"birth_date\": \""+day+"\",\n" +
                                "        \"birth_month\": \""+month+"\",\n" +
                                "        \"birth_year\": \""+year+"\",\n" +
                                "        \"firstname\": \""+firstName+"\",\n" +
                                "        \"lastname\": \""+lastName+"\",\n" +
                                "        \"company\": \""+company+"\",\n" +
                                "        \"address1\": \""+address1+"\",\n" +
                                "        \"address2\": \""+address2+"\",\n" +
                                "        \"country\": \""+country+"\",\n" +
                                "        \"state\": \""+state+"\",\n" +
                                "        \"city\": \""+city+"\",\n" +
                                "        \"zipcode\": \""+zip+"\"\n" +
                                "    }");
        return payload;
    }
}
