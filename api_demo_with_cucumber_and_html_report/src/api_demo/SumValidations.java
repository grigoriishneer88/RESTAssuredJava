package api_demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidations {

	@Test
	public void SumOfCourses() {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(payload.CourcePrice());
		int count = js.getInt("courses.size()");
		int full_amount = 0;
		for (int i=0;i<count; i++) {
			int price = js.get("courses["+i+"].price");
			int copies_num = js.get("courses["+i+"].copies");
			int amount = price*copies_num;
			full_amount = full_amount + amount;
			System.out.println(amount);
		}
		System.out.println(full_amount);
		int purchaseAmount_number = js.getInt("dashboard.purchaseAmount");

		Assert.assertEquals(full_amount, purchaseAmount_number);

	}

}
