package api_demo;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BugTest {

    @Test
    public void create_bug() {

        RestAssured.baseURI = "https://learn-tests-automation.atlassian.net";

        String jiraEmail = System.getenv("JIRA_EMAIL");
        String jiraApiToken = System.getenv("JIRA_API_TOKEN");

        String response_create_bug =
                given()
                    .auth()
                    .preemptive()
                    .basic(jiraEmail, jiraApiToken)
                    .header("Content-Type", "application/json")
                    .body("{\n"
                            + "    \"fields\":{\n"
                            + "        \"project\":{\n"
                            + "            \"key\":\"SCRUM\"\n"
                            + "        },\n"
                            + "        \"summary\":\"dropdowns are not working via selenium 3\", \n"
                            + "        \"issuetype\":{\n"
                            + "            \"name\":\"Bug\"\n"
                            + "        }\n"
                            + "    }\n"
                            + "}")
                    .when()
                    .post("/rest/api/3/issue")
                    .then()
                    .assertThat()
                    .statusCode(201)
                    .extract()
                    .response()
                    .asString();

        System.out.println(response_create_bug);

        JsonPath js = ReusableMethods.row_to_json(response_create_bug);
        String id = js.getString("id");

        System.out.println("Created Jira bug ID: " + id);

        String imagePath = "src/test/resources/97c89b8ecd51d6d970d0847b494e9da0.jpg";

        String response_attach_image =
                given()
                    .auth()
                    .preemptive()
                    .basic(jiraEmail, jiraApiToken)
                    .header("X-Atlassian-Token", "no-check")
                    .multiPart("file", new File(imagePath))
                    .when()
                    .post("/rest/api/3/issue/" + id + "/attachments")
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response()
                    .asString();

        System.out.println(response_attach_image);
    }
}