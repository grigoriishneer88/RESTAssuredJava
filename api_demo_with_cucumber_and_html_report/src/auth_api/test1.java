package auth_api;

import static io.restassured.RestAssured.given;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class test1 {

    public static void main(String[] args) throws InterruptedException {

        // OAuth callback URL should be provided through an environment variable.
        String url = System.getenv("OAUTH_CALLBACK_URL");

        if (url == null || url.isEmpty()) {
            throw new IllegalStateException(
                    "OAUTH_CALLBACK_URL environment variable is not set"
            );
        }

        String partialcode = url.split("code=")[1];
        String code = partialcode.split("&scope")[0];

        System.out.println("Authorization code received.");

        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");

        if (clientId == null || clientId.isEmpty()
                || clientSecret == null || clientSecret.isEmpty()) {
            throw new IllegalStateException(
                    "CLIENT_ID or CLIENT_SECRET environment variable is not set"
            );
        }

        String response =
                given()
                        .urlEncodingEnabled(false)
                        .queryParams("code", code)
                        .queryParams("client_id", clientId)
                        .queryParams("client_secret", clientSecret)
                        .queryParams("grant_type", "authorization_code")
                        .queryParams("state", "verifyfjdss")
                        .queryParams(
                                "redirect_uri",
                                "https://rahulshettyacademy.com/getCourse.php"
                        )
                        .when()
                        .post("https://www.googleapis.com/oauth2/v4/token")
                        .asString();

        JsonPath jsonPath = new JsonPath(response);

        String accessToken = jsonPath.getString("access_token");

        System.out.println("Access token received successfully.");

        String r2 =
                given()
                        .contentType("application/json")
                        .queryParams("access_token", accessToken)
                        .expect()
                        .defaultParser(Parser.JSON)
                        .when()
                        .get("https://rahulshettyacademy.com/getCourse.php")
                        .asString();

        System.out.println(r2);
    }
}