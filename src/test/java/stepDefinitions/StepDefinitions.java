 package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.RequestBody;
import pojo.ResponseBody;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

public class StepDefinitions extends Utils{
	static RequestSpecification request_ ;
	ResponseSpecification res_spec;
	static ResponseBody response_obj;
	String response_string;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException { 
		
		// Request and response 
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		//request spec
		request_ = given().log().all().spec(requestSpecification())
			.body(data.add_place_payload(name, language, address));

	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String http_method) {
		APIResources resource_api = APIResources.valueOf(resource);
		//response spec
		res_spec = new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON).build();
		//response body
//				response_obj = new ResponseBody();
//				response_obj = request_.when()
//					.post("/maps/api/place/add/json")
//					.then()
//					.spec(res_spec)
//					.extract()
//					.response()
//					.then()
//					.log()
//					.all()
//					.extract()
//					.response()
//					.as(ResponseBody.class);
//				System.out.println("------------------------------------");
//				System.out.println("lat - " + response_obj.getId());
//				System.out.println("lng - " + response_obj.getPlace_id());
//		
		if(http_method.equals("POST")) {
			response = request_
			        .when()
			        .post(resource_api.getResource())
			        .then()
			        .spec(res_spec)
			        .extract()
			        .response();
		}
		else if(http_method.equals("GET")) {
			response = request_
			        .when()
			        .get(resource_api.getResource())
			        .then()
			        .spec(res_spec)
			        .extract()
			        .response();
		}
		response_string = response.asString();
		if (resource.equals("AddPlaceURL")) {
			response_obj = response.as(ResponseBody.class);
		}
		
	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {		
	    assertEquals(int1.intValue(), response.getStatusCode());
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		JsonPath js = new JsonPath(response_string);
		assertEquals(js.get(key).toString(), value);
	}
	
	@Then("verify that place_id created maps to {string} using {string}")
	public void verify_that_place_id_created_maps_to_using(String expected_name, String resource_name) throws IOException {
		String place_id = response_obj.getPlace_id();
		request_ = given().log().all().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource_name, "GET");
		JsonPath js = new JsonPath(response_string);
		String actual_name = js.get("name").toString();
		assertEquals(actual_name, expected_name); 
	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		String place_id = response_obj.getPlace_id();
		System.out.println(place_id);
		request_ = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	    
	}


}
