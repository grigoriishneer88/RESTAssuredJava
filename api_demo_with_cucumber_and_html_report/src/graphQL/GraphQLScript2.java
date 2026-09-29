package graphQL;
import static io.restassured.RestAssured.*;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQLScript2 {
	public static void main(String[] args) {
		//Query
		int 

		characterId = 47;
		String response = given().log().all().header("Content-type", "application/json")
		.body("{\"query\":\"query ($characterId:Int!, $episodeId:Int!){\\n  character(characterId:$characterId){\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId:47)\\n  {\\n    name\\n    dimension\\n  }\\n  episode(episodeId:$episodeId)\\n  {\\n    name\\n    air_date\\n    episode\\n    \\n  }\\n  characters(filters:{name:\\\"Tom\\\"})\\n  {\\n  \\tinfo\\n    {\\n      count\\n    }\\n    result\\n    {\\n      name\\n      type\\n    }\\n  }\\n}\",\"variables\":{\"characterId\":"+characterId+",\"episodeId\":11}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		System.out.println (response);
		JsonPath js = new JsonPath(response);
		String character_name = js.getString("data.character.name");
		System.out.println(character_name);
		Assert.assertEquals(character_name, "Tom");	
	
		
		//mutation
		String character_name_RO = "RRRR22OOOB";
		String mutation_response = given().log().all().header("Content-type", "application/json")
				.body("{\n"
						+ "  \"query\": \"mutation($locationName:String!, $characterName:String!, $episodeName:String!)\\n{\\n  createLocation(location:{name:$locationName,type:\\\"New York\\\", dimension:\\\"234\\\"})\\n  {\\n    id\\n  }\\n  createCharacter(character:{\\n    name:$characterName,\\n    type:\\\"Muchacho\\\",\\n    status:\\\"hello\\\",\\n    species:\\\"fantasy\\\",\\n    gender:\\\"male\\\",\\n    image:\\\"png\\\",\\n    locationId:7138,\\n    originId: 7138\\n  }){\\n    id\\n  }\\n  createEpisode(episode:{\\n    name:$episodeName,\\n    air_date:\\\"1975\\\",\\n    episode:\\\"vosemj\\\"\\n  }){\\n    id\\n  }\\n  deleteLocations(locationIds:[7140]){\\n    locationsDeleted\\n  }\\n}\",\n"
						+ "  \"variables\": {\n"
						+ "    \"locationName\": \"US\",\n"
						+ "    \"characterName\": \""+character_name_RO+"\",\n"
						+ "    \"episodeName\": \"ny pogodi\"\n"
						+ "  }\n"
						+ "}")
				.when().post("https://rahulshettyacademy.com/gq/graphql")
				.then().extract().response().asString();
				System.out.println (mutation_response);
				JsonPath js_1 = new JsonPath(mutation_response);
				System.out.println(js_1.toString());

	}
}
