package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	public static JsonPath row_to_json(String response)
	{
		JsonPath js_1 = new JsonPath(response);
		return js_1;
		
	}
	

}
