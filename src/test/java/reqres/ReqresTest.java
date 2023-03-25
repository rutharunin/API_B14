package reqres;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ReqresTest {
    @Test
    public void getListUsers(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .extract().response();

        Map<String,Object>deserializedPage1=response.as(new TypeRef<Map<String,Object>>() {
        });
        List<Map<String,Object>>listOfUsers=(List<Map<String,Object>>)deserializedPage1.get("data");
        response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get("?page=2")
                .then().statusCode(200)
                .extract().response();

        Map<String,Object>deserializedPage2=response.as(new TypeRef<Map<String,Object>>() {
        });
        List<Map<String,Object>>listOfUser2=(List<Map<String,Object>>)deserializedPage2.get("data");
        for (int i=0;i<listOfUser2.size();i++){
            listOfUsers.add(listOfUser2.get(i));
        }

        Assert.assertEquals((int)deserializedPage1.get("total"), listOfUsers.size());
        Assert.assertEquals("{\n" +
                "    \"page\": 2,\n" +
                "    \"per_page\": 6,\n" +
                "    \"total\": 12,\n" +
                "    \"total_pages\": 2,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 7,\n" +
                "            \"email\": \"michael.lawson@reqres.in\",\n" +
                "            \"first_name\": \"Michael\",\n" +
                "            \"last_name\": \"Lawson\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/7-image.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 8,\n" +
                "            \"email\": \"lindsay.ferguson@reqres.in\",\n" +
                "            \"first_name\": \"Lindsay\",\n" +
                "            \"last_name\": \"Ferguson\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/8-image.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 9,\n" +
                "            \"email\": \"tobias.funke@reqres.in\",\n" +
                "            \"first_name\": \"Tobias\",\n" +
                "            \"last_name\": \"Funke\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/9-image.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 10,\n" +
                "            \"email\": \"byron.fields@reqres.in\",\n" +
                "            \"first_name\": \"Byron\",\n" +
                "            \"last_name\": \"Fields\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/10-image.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 11,\n" +
                "            \"email\": \"george.edwards@reqres.in\",\n" +
                "            \"first_name\": \"George\",\n" +
                "            \"last_name\": \"Edwards\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/11-image.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 12,\n" +
                "            \"email\": \"rachel.howell@reqres.in\",\n" +
                "            \"first_name\": \"Rachel\",\n" +
                "            \"last_name\": \"Howell\",\n" +
                "            \"avatar\": \"https://reqres.in/img/faces/12-image.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"support\": {\n" +
                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                "    }\n" +
                "}",response.prettyPrint().toString());
    }
    @Test
    public void getAuser(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users";

        Response response=
        RestAssured.given()
                .accept("application/json")
                .when().get()
                .then().statusCode(200)
                .extract().response();
        Map<String,Object>deserializedPage1=response.as(new TypeRef<Map<String,Object>>() {
        });
        List<Map<String,Object>>listOfMembers=(List<Map<String,Object>>)deserializedPage1.get("data");
        for (int i=0;i<listOfMembers.size();i++) {
            if ((int) listOfMembers.get(i).get("id") == 6) {
                Assert.assertTrue(listOfMembers.get(i).get("last_name").toString().equals("Ramos"));
            }
        }
    }
    @Test
    public void UserNotFound(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get("23")
                .then().statusCode(404)
                .extract().response();
        Map<String,Object>deserializedResponse=response.as(new TypeRef<Map<String,Object>>() {
        });
        Assert.assertEquals("[]",deserializedResponse.entrySet().toString());

    }
    @Test
    public void listOfResource(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/unknown";
        Response response=
                RestAssured.given()
                        .accept(ContentType.JSON)
                        .when().get()
                        .then().statusCode(200)
                        .extract().response();

        Assert.assertEquals("{\n" +
                "    \"page\": 1,\n" +
                "    \"per_page\": 6,\n" +
                "    \"total\": 12,\n" +
                "    \"total_pages\": 2,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"cerulean\",\n" +
                "            \"year\": 2000,\n" +
                "            \"color\": \"#98B2D1\",\n" +
                "            \"pantone_value\": \"15-4020\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"name\": \"fuchsia rose\",\n" +
                "            \"year\": 2001,\n" +
                "            \"color\": \"#C74375\",\n" +
                "            \"pantone_value\": \"17-2031\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 3,\n" +
                "            \"name\": \"true red\",\n" +
                "            \"year\": 2002,\n" +
                "            \"color\": \"#BF1932\",\n" +
                "            \"pantone_value\": \"19-1664\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 4,\n" +
                "            \"name\": \"aqua sky\",\n" +
                "            \"year\": 2003,\n" +
                "            \"color\": \"#7BC4C4\",\n" +
                "            \"pantone_value\": \"14-4811\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 5,\n" +
                "            \"name\": \"tigerlily\",\n" +
                "            \"year\": 2004,\n" +
                "            \"color\": \"#E2583E\",\n" +
                "            \"pantone_value\": \"17-1456\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 6,\n" +
                "            \"name\": \"blue turquoise\",\n" +
                "            \"year\": 2005,\n" +
                "            \"color\": \"#53B0AE\",\n" +
                "            \"pantone_value\": \"15-5217\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"support\": {\n" +
                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                "    }\n" +
                "}",response.prettyPrint().toString());

    }
    @Test
    public void singleResource(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/unknown/2";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .extract().response();

        Assert.assertEquals("{\n" +
                "    \"data\": {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"fuchsia rose\",\n" +
                "        \"year\": 2001,\n" +
                "        \"color\": \"#C74375\",\n" +
                "        \"pantone_value\": \"17-2031\"\n" +
                "    },\n" +
                "    \"support\": {\n" +
                "        \"url\": \"https://reqres.in/#support-heading\",\n" +
                "        \"text\": \"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                "    }\n" +
                "}",response.prettyPrint().toString());

    }

    @Test
    public void negativeResult(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/unknown/23";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get()
                .then().statusCode(404)
                .extract().response();

        Map<String,Object>deserializedResponse=response.as(new TypeRef<Map<String,Object>>() {
        });
        Assert.assertEquals("[]",deserializedResponse.entrySet().toString());
    }

    @Test
    public void getDelayedResponse(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users";

        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get("?delay=3")
                .then().statusCode(200);

    }
    @Test
    public void makeUser(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/users";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when().post()
                .then().statusCode(201)
                .extract().response();

        Map<String,String>deserializedResponse=response.as(new TypeRef<Map<String,String>>() {
        });
        Assert.assertEquals(deserializedResponse.get("name"),"morpheus");
        Assert.assertEquals(deserializedResponse.get("job"),"leader");
    }
    @Test
    public void happyPathRegister(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/register";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .when().post()
                .then().statusCode(200)
                .extract().response();

        Map<String,Object>deserializedResponse=response.as(new TypeRef<Map<String,Object>>() {
        });
        Assert.assertEquals(4,deserializedResponse.get("id"));
        Assert.assertEquals("QpwL5tke4Pnpja7X4",deserializedResponse.get("token"));

    }
    @Test
    public void negativeRegister(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/register";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}")
                .when().post()
                .then().statusCode(400)
                .extract().response();
        Map<String,String>deserializedResponse=response.as(new TypeRef<Map<String,String>>() {
        });
        Assert.assertEquals(deserializedResponse.get("error"),"Missing password");

    }

    @Test
    public void happyPathLogin(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/login";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .when().post()
                .then().statusCode(200)
                .extract().response();

        Map<String,String>deserializedResponse=response.as(new TypeRef<Map<String,String>>() {
        });
        Assert.assertEquals("QpwL5tke4Pnpja7X4",deserializedResponse.get("token"));
    }

    @Test
    public void negativeLogin(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/login";

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}")
                .when().post()
                .then().statusCode(400)
                .extract().response();
        Map<String,String>deserializedResponse=response.as(new TypeRef<Map<String,String>>() {
        });
        Assert.assertEquals("Missing password",deserializedResponse.get("error"));
    }
    @Test
    public void delete(){

        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/login";

        Response response=
                RestAssured.given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body("{\n" +
                                "    \"email\": \"peter@klaven\"\n" +
                                "}")
                        .when().post()
                        .then().statusCode(400)
                        .extract().response();
        Map<String,String>deserializedResponse=response.as(new TypeRef<Map<String,String>>() {
        });
        Assert.assertEquals("Missing password",deserializedResponse.get("error"));
    }
}
