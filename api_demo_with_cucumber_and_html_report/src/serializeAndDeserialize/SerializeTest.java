package serializeAndDeserialize;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import serializeAndDeserializeDataClasses.Location;
import serializeAndDeserializeDataClasses.RequestBody;
import serializeAndDeserializeDataClasses.ResponseBody;

public class SerializeTest {

	public static void main(String[]args) {
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
		RestAssured.baseURI="https://rahulshettyacademy.com";
		ResponseBody response_obj = new ResponseBody(); 
		response_obj = given().log().all().queryParam("key", "qaclick123")
			.body(body)
			.when()
			.post("/maps/api/place/add/json")
			.then()
			.assertThat()
			.statusCode(200)
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
