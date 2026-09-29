package auth_api;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;



public class AuthTest {
	
	public String get_token_for_test(String client_id, String client_secret, String grant_type, String scope) {
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi";
		String response = given().header("Content-Type","application/json")
							.contentType("application/x-www-form-urlencoded")
							.formParam("client_id", client_id)
							.formParam("client_secret", client_secret)
							.formParam("grant_type", grant_type)
							.formParam("scope", scope)
							.when()
							.post("/oauth2/resourceOwner/token")
							.then()
							.extract().response().asString();
		JsonPath js = ReusableMethods.row_to_json(response);
		System.out.println(response.toString());
		String access_token = js.get("access_token");
		System.out.println(access_token);
		return access_token;
	}
	
	public GetCourse get_courses(String access_token){
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi";
		GetCourse gc = given()
						.queryParam("access_token", access_token)
						.contentType("application/x-www-form-sencoded")
						.when()
						.get("/getCourseDetails")
						.then()
						.extract().response().as(GetCourse.class);
		System.out.println(gc.getLinkedIn());
		return gc;
	}
	
	@Test
	public void get_token_and_use_in_next_request() {
		String actual_access_token = get_token_for_test("692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com", "erZOWM9g3UtwNRj340YYaK_W", "client_credentials", "trust");
		GetCourse gc = get_courses(actual_access_token);
		List <Api> api_courses_list = gc.getCourses().getApi();
		System.out.println(api_courses_list.get(1).getCourseTitle());
	
		for(int i=0; i <api_courses_list.size(); i++) {
			String i_course_title = api_courses_list.get(i).getCourseTitle();
			if (i_course_title.equalsIgnoreCase("SoapUI Webservices testing")){
				System.out.println("found!!!!  " + api_courses_list.get(i).getCourseTitle());
			}
		}
		List <WebAutomation> webAutomation_courses = gc.getCourses().getWebAutomation();
		for(int i=0; i <webAutomation_courses.size(); i++) {
			String i_course_title = webAutomation_courses.get(i).getCourseTitle();
			System.out.println("#" + (i+1) +  ": "+ webAutomation_courses.get(i).getCourseTitle());
			
		}

		
	}
	
}