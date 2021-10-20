package com.qa.stepDefs;

import com.qa.utilities.ConfigPropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ServicesStepDef {

    String endpointOfServices = ConfigPropertyReader.get("endpointOfServices");
    String createdServiceID, createdServiceName;
    String actualNoRecordMessage;
    String updatedServiceID, updatedServiceName;
    String deletedServiceName;

    @When("user sends a GET request to see services")
    public void user_sends_a_GET_request_to_see_services() {
        Response response = given().accept(ContentType.JSON)
                .get(endpointOfServices);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("after user skipped {int} times, service id should be started from {int}")
    public void first_service_name_should_be(int skippedNumber, int serviceID) {
        Response response = given().accept(ContentType.JSON)
                .queryParam("$skip", skippedNumber)
                .get(endpointOfServices);

        JsonPath js = response.jsonPath();
        int actualServiceID = js.getInt("data.id[0]");
        Assert.assertEquals(serviceID, actualServiceID);
    }

    @When("user creates a new service called {string}")
    public void user_creates_a_new_service_called(String newServiceName) {
        String newServiceBody = "{\"name\"" + ": " + "\"" + newServiceName + "\"}";
        Response response = given().contentType("application/json")
                .accept(ContentType.JSON)
                .body(newServiceBody)
                .post(endpointOfServices);
        Assert.assertEquals(201, response.getStatusCode());

        JsonPath js = response.jsonPath();
        createdServiceID = js.getString("id");
        createdServiceName = js.getString("name");
    }

    @And("user updates a service with createdNewID and a new name {string}")
    public void user_is_able_to_update_a_service_called(String newServiceName) {
        String updateServiceName = "{\"name\"" + ": " + "\"" + newServiceName + "\"}";
        Response response = given().contentType("application/json")
                .accept(ContentType.JSON)
                .body(updateServiceName)
                .patch(endpointOfServices + "/" + createdServiceID);
        Assert.assertEquals(200, response.getStatusCode());

        JsonPath js = response.jsonPath();
        updatedServiceName = js.getString("name");
        updatedServiceID = js.getString("id");
    }

    @And("user is able to delete a service updated")
    public void user_is_able_to_delete_a_service_called() {
        Response response = given().contentType("application/json")
                .accept(ContentType.JSON)
                .delete(endpointOfServices + "/" + updatedServiceID);
        Assert.assertEquals(200, response.getStatusCode());

        JsonPath js = response.jsonPath();
        deletedServiceName = js.getString("name");
    }

    @Then("user is able to see success message as {string}")
    public void user_is_able_to_see_success_message_as(String expectedMessage) {
        Assert.assertEquals(expectedMessage, deletedServiceName);
    }

    @When("user sends an invalid service ID as {string}")
    public void user_sends_an_invalid_service_ID_x8(String invalidID) {
        Response response = given().contentType("application/json")
                .accept(ContentType.JSON)
                .get(endpointOfServices + "/" + invalidID);
        Assert.assertEquals(404, response.getStatusCode());

        JsonPath js = response.jsonPath();
        actualNoRecordMessage = js.getString("message");
    }

    @Then("user is able to see error message as {string}")
    public void user_is_able_to_see_message(String noRecordMessage) {
        Assert.assertEquals(noRecordMessage, actualNoRecordMessage);
    }

    @When("user sends a GET request with {int}")
    public void user_sends_a_GET_request_with_ID(int id) {
        Response response = given().contentType("application/json")
                .accept(ContentType.JSON)
                .get(endpointOfServices + "/" + id);
        Assert.assertEquals(200, response.getStatusCode());
    }
    @Then("user is able to see requested service {int}")
    public void user_is_able_to_see_requested_service(int id) {
        Response response = given().contentType("application/json")
                .accept(ContentType.JSON)
                .get(endpointOfServices + "/" + id);

        JsonPath js = response.jsonPath();
        int actualID = js.getInt("id");
        Assert.assertEquals(id,actualID);
    }


}
