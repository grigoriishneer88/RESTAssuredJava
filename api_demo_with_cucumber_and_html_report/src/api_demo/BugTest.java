package api_demo;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BugTest {
	@Test()
	public void create_bug() {
		RestAssured.baseURI = "https://learn-tests-automation.atlassian.net";
		
		String response_create_bug = 
				given()
					.header("Content-Type","application/json")
					.header("Authorization", "Basic Z3JpZ29yaWlzaG5lZXI4OEBnbWFpbC5jb206QVRBVFQzeEZmR0YwUnpySEQ1T1pnb0FrTzl6NjFjckJsbkdVSUZLdlVHLTJPNHFVVlQySEMzUzREUzZzdEZMSDhaeldOd0lnMXJaQU9DUFRuMlNoZTlraHFlbnpWTmJLQzNuMTRXODc0X1NTZW5IMjc0TUZ0VzQxaXVxcmY0UkxVT2tPX1NFUkhBZmZWSGoxVENIb2t5a01GWGxVTWR5M19zZ3NwT0RZWVZsRl9jaEFmTEVaUTRZPTRCOTdGRjRBw")
					.body("{\n"
							+ "    \"fields\":{\n"
							+ "        \"project\":{\n"
							+ "            \"key\":\"SCRUM\"\n"
							+ "        },\n"
							+ "        \"summary\":\"dropdowns are not working via selenium 3\", \n"
							+ "        \"issuetype\":{\n"
							+ "            \"name\":\"Bug\"\n"
							+ "        }\n"
							+ "\n"
							+ "    }\n"
							+ "}").
					when()
					.post("/rest/api/3/issue")
					.then().assertThat().statusCode(201)
					.extract().response().asString();
//					JsonPath js = ReusableMethods.row_to_json(response);
					System.out.println(response_create_bug);
					
			JsonPath js = ReusableMethods.row_to_json(response_create_bug);
			String id = js.getString("id");
			System.out.println(id);

			
		String response_attach_image = 
				given()
					.header("Authorization", "Basic Z3JpZ29yaWlzaG5lZXI4OEBnbWFpbC5jb206QVRBVFQzeEZmR0YwUnpySEQ1T1pnb0FrTzl6NjFjckJsbkdVSUZLdlVHLTJPNHFVVlQySEMzUzREUzZzdEZMSDhaeldOd0lnMXJaQU9DUFRuMlNoZTlraHFlbnpWTmJLQzNuMTRXODc0X1NTZW5IMjc0TUZ0VzQxaXVxcmY0UkxVT2tPX1NFUkhBZmZWSGoxVENIb2t5a01GWGxVTWR5M19zZ3NwT0RZWVZsRl9jaEFmTEVaUTRZPTRCOTdGRjRBw")
					.header("X-Atlassian-Token", "no-check")
					.multiPart("file",new File("/Users/grigorii/Downloads/97c89b8ecd51d6d970d0847b494e9da0.jpg"))
					.when()
						.post("/rest/api/3/issue/"+id+"/attachments")
					.then()
						.assertThat()
						.statusCode(200)
						.extract()
						.response()
						.asString();
		System.out.println(response_attach_image);

		}
}
