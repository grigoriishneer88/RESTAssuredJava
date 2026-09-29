package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req_spec;
	public RequestSpecification requestSpecification () throws IOException {
		
		if(req_spec==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		// Spec builder
		req_spec = new RequestSpecBuilder()
		.setBaseUri(getGlobalValue("baseUrl"))
		.addQueryParam("key", "qaclick123")
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON)
		.build();
		return req_spec;
		}
		else{
			return req_spec;
		}
	}
	public static String getGlobalValue(String key) throws IOException 
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/Users/grigorii/eclipse-workspace/API_framework/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
}
