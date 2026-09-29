package serializeAndDeserialize;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import serializeAndDeserializeDataClasses.Location;
import serializeAndDeserializeDataClasses.RequestBody;
import serializeAndDeserializeDataClasses.ResponseBody;

public class SpecBuilderTest {

	public static void main(String[]args) {
		// Request body details
		RequestBody body = new RequestBody(); 
		Location location = new Location();
		location.setLat("-38.383494");
		location.setLng("33.427362");
		body.setLocation(location);
		body.setAccuracy("50");
		body.setName("Rahul Shetty Academy ");
		body.setPhone_number("(+91) 983 893 3937");
		body.setAddress("29, side layout, cohen 09");
		List <String> types = new ArrayList <String>();
		types.add("shoe park");
		types.add("shop");
		body.setTypes(types);
		body.setWebsite("https://rahulshettyacademy.com/");
		body.setLanguage("French-IN");
		
		// Spec builder
		RequestSpecification req_spec = new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON)
		.build();
		
		
		// Request and response 
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		//request spec
		RequestSpecification request_ = given().log().all().spec(req_spec)
			.body(body);
		//response spec
		ResponseSpecification res_spec = new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON).build();
		
		//response body
		ResponseBody response_obj = new ResponseBody();
		response_obj = request_.when()
			.post("/maps/api/place/add/json")
			.then()
			.spec(res_spec)
			.extract()
			.response()
			.then()
			.log()
			.all()
			.extract()
			.response()
			.as(ResponseBody.class);
		
		System.out.println("------------------------------------");
		System.out.println("lat - " + response_obj.getId());
		System.out.println("lng - " + response_obj.getPlace_id());

		
	}
}
