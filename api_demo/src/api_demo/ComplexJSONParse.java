package api_demo;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJSONParse {
	public static void main(String[] args) {
	JsonPath js = new JsonPath(payload.CourcePrice());
	int count = js.getInt("courses.size()");
	System.out.println(count);
	
	int purchaseAmount_number = js.getInt("dashboard.purchaseAmount");
	System.out.println(purchaseAmount_number);
	
	String first_course_title = js.get("courses[0].title");
	System.out.println(first_course_title);
	
	String third_course_title = js.get("courses[2].title");
	System.out.println(third_course_title);
	
	
	System.out.println("now all as one  loop");

	
	for(int i=0; i<count;i++) {
		String title = js.get("courses["+i+"].title");
		int price = js.get("courses["+i+"].price");
		System.out.println("-------------------------");
		
		if (title.equals("RPA")) {
			System.out.println(title);
			System.out.println(price);
			int copies_num = js.get("courses["+i+"].copies");
			System.out.println(copies_num);
			break;
		}
		
	}
	int count_dashboard = js.getInt("dashboard.size()");
	System.out.println("dashboard size is " +count_dashboard);
	}
}

