package api_demo;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {

	public static void main(String[] args) throws IOException {
		String new_address = "test test test";
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		// add place

		String response = given().queryParam("key", "qaclick123").header("Content-type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("/Users/grigorii/eclipse-workspace/api_demo/placeBody.json")))).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println("here the variable value"); 
		System.out.println(response);
		
		JsonPath js = ReusableMethods.row_to_json(response);
		String place_id = js.getString("place_id");
		System.out.println("here the place_id value"); 
		System.out.println(place_id);
		//put place
		given().log().all().queryParam("key", "qaclick123").header("Content-type", "application/json")
		.body("{\n"
				+ "    \"place_id\": \""+place_id+"\",\n"
				+ "    \"key\": \"qaclick123\",\n"
				+ "    \"accuracy\": 100,\n"
				+ "    \"address\": \""+new_address+"\"\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		//get place
		String response_1 = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", place_id)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200)
		.body("address",equalTo(new_address)).extract().response().asString();
		
		System.out.println(response_1);
//		JsonPath js_1 = new JsonPath(response_1);
		JsonPath js_1 = ReusableMethods.row_to_json(response_1);
		String actual_address = js_1.getString("address");
		System.out.println(actual_address);
		Assert.assertEquals(actual_address, new_address);
		
	}

}
