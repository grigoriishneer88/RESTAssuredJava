package api_demo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	@Test(dataProvider = "books")
	public void addBook(String aisle, String isbn) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type","application/json")
		.body(payload.AddBook(aisle,isbn)).
		when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = ReusableMethods.row_to_json(response);
		String id = js.get("ID");
		System.out.println(id);
	}
	@DataProvider(name="books")
	public Object[][] getData() {
		return new Object[][]{{"aswfde","r555"},{"aksn", "343"},{"asdf","4344"}};
	}

}
