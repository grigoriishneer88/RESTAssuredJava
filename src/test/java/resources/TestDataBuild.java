package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.RequestBody;

public class TestDataBuild {
	public RequestBody add_place_payload(String name, String language, String address ) 
	{
		RequestBody body = new RequestBody(); 
		Location location = new Location();
		location.setLat("-38.383494");
		location.setLng("33.427362");
		body.setLocation(location);
		body.setAccuracy("50");
		body.setName(name);
		//"Rahul Shetty Academy "
		body.setPhone_number("(+91) 983 893 3937");
		body.setAddress(address);
		//"29, side layout, cohen 09"
		List <String> types = new ArrayList <String>();
		types.add("shoe park");
		types.add("shop");
		body.setTypes(types);
		body.setWebsite("https://rahulshettyacademy.com/");
		body.setLanguage(language);
		//"French-IN"
		return body;
	}
	public String deletePlacePayload(String place_id) {
		return "{\r\n  \"place_id\":\""+place_id+"\"\r\n}";
	}
		 
}
