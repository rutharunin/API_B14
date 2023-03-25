package step_defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.PetPojo;
import utils.PayloadUtils;

public class PetStepDef {
private String petId="99991";
private String petName="Joey";
private String petStatus="peeing";
private Response response;

    @Given("User has valid URL")
    public void user_has_valid_url() {
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="v2/pet";
    }
    @When("User sends POST request to create a pet")
    public void user_sends_post_request_to_create_a_pet() {

        response=RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload(petId,petName,petStatus))
                .when().post();
    }
    @Then("Statue code is {int}")
    public void statue_code_is(int expectedStatusCode) {
        int actualStatusCode=response.getStatusCode();
        Assert.assertEquals("Failed to validate status code ",expectedStatusCode,actualStatusCode);
    }
    @Then("pet name, pet id, pet status are correct")
    public void pet_name_pet_id_pet_status_are_correct() {
        PetPojo parsedResponse=response.as(PetPojo.class);
        Assert.assertEquals(petId,String.valueOf(parsedResponse.getId()));
        Assert.assertEquals(petName,parsedResponse.getName());
        Assert.assertEquals(petStatus,parsedResponse.getStatus());
    }
}
