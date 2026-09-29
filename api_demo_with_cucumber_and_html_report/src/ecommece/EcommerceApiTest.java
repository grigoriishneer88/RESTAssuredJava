package ecommece;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.*;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import ecommece_add_product.AddProductResponse;
import ecommece_login.LoginRequest;
import ecommece_login.LoginResponsePayload;
import ecommece_orders.CreateOrderResponse;
import ecommece_orders.Orders;
import ecommece_orders.OrdersDetails;

public class EcommerceApiTest {
    public static void main(String[] args) throws InterruptedException {
		//login
		RequestSpecification requirement_login = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
		LoginRequest login_request = new LoginRequest();
		login_request.setUserEmail("test_qa@test.com");
		login_request.setUserPassword("Qwer1234!");

		RequestSpecification request_login = given().log().all().spec(requirement_login).body(login_request);
		LoginResponsePayload login_response = request_login.when().post("/api/ecom/auth/login")
		.then().log().all().extract()
		.response().as(LoginResponsePayload.class);
		System.out.println(login_response.getToken());
		System.out.println(login_response.getUserId());
		String user_id = login_response.getUserId();
		String token = login_response.getToken();
		
		//add product
		RequestSpecification requirements_add_product = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("authorization",token)
		.build();
		
		RequestSpecification request_add_product = given().log().all().spec(requirements_add_product)
		.param("productName", "Laptop6")
		.param("productAddedBy", user_id)
		.param("productCategory", "IT6")
		.param("productSubCategory", "Laptops6")
		.param("productPrice", "55006")
		.param("productDescription", "apppy macbuk6")
		.param("productFor", "all")
	    .multiPart("productImage", new File("/Users/grigorii/Desktop/Screenshot 2026-09-17 at 17.17.01.png"));
		
		AddProductResponse add_product_response_payload = request_add_product.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().response().as(AddProductResponse.class);
		String productId = add_product_response_payload.getProductId();
		String message = add_product_response_payload.getMessage();
		System.out.println("-------------------------------------------------------"+productId);
		System.out.println("-------------------------------------------------------"+message);
		Thread.sleep(5000);		
		
		//create order
		RequestSpecification requirements_create_order = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization",token)
				.setContentType(ContentType.JSON)
				.build();
		
		System.out.println("BEFORE SLEEP: " + System.currentTimeMillis());

		Thread.sleep(5000);

		System.out.println("AFTER SLEEP: " + System.currentTimeMillis());
		
		//add order
		OrdersDetails order_details = new OrdersDetails();
		order_details.setCountry("British Indian Ocean Territory");
		order_details.setProductOrderedId(productId);
		List <OrdersDetails> order_details_list = new ArrayList<>();
		order_details_list.add(order_details);
		Orders orders = new Orders();
		orders.setOrders(order_details_list);
		
		RequestSpecification request_add_order= given().log().all().spec(requirements_create_order).body(orders);
		
		CreateOrderResponse create_order_responses = request_add_order.when().post("/api/ecom/order/create-order").then().log().all().extract().response().as(CreateOrderResponse.class);
		System.out.println("-------------------------------------------------------"+create_order_responses.getOrders().getFirst());
		System.out.println("-------------------------------------------------------"+create_order_responses.getProductOrderId().getFirst());

		//delete order
		RequestSpecification requirements_delete_order = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization",token)
				.setContentType(ContentType.JSON)
				.build();
		RequestSpecification request_delete_order = given().log().all().spec(requirements_delete_order).pathParam("productId", productId);
		
		
		String delete_order_response_payload = request_delete_order.when().delete("api/ecom/product/delete-product/{productId}")
				.then().log().all().extract().response().asString();
		System.out.println(delete_order_response_payload);
		JsonPath js = new JsonPath(delete_order_response_payload);
		Assert.assertEquals("Product Deleted Successfully", js.get("message"));
	}
}
