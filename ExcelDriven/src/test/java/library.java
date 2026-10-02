import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

public class library {
	public static String id;
//	public static String book_name = "Learn 4444 222 with 2222";
//	public static String isbn = "33d3d222";
//	public static String aisle = "222";
//	public static String author = "John foe";
	public static HashMap<String, Object> jsonMap = new HashMap<>();

	
	

	@Test
	public void addBook() throws IOException {
		dataDriven d = new dataDriven();
		ArrayList data =d.getData("RestAddBook", "RestAssured");
		
		jsonMap.put("name", data.get(1));
		jsonMap.put("isbn", data.get(2));
		jsonMap.put("aisle", data.get(3));
		jsonMap.put("author", data.get(4));
	    id = given()
	        .header("Content-Type", "application/json")
	        .body(jsonMap)
	    .when()
	        .post("http://216.10.245.166/Library/Addbook.php")
	    .then()
	        .log().body()
	        .assertThat()
	        .statusCode(200)
	        .extract()
	        .path("ID");

	    System.out.println("Book ID: " + id);
	}

	@Test(dependsOnMethods = "addBook")
	public void getBook() {

	    System.out.println("ID inside getBook = [" + id + "]");
	    System.out.println("ID length = " + id.length());

	    given() 
	        .log().all()
	    .when()
	        .get("http://216.10.245.166/Library/GetBook.php?ID=" + id)
	    .then()
	        .log().all()
	        .statusCode(200);
	}


    @Test(dependsOnMethods = "getBook")
    public void deleteBook() {

        

        given()
            .header("Content-Type", "application/json")
            .body("{\n"
                    + "    \"ID\":\"" + id + "\"\n"
                    + "}")
        .when()
            .post("http://216.10.245.166/Library/DeleteBook.php")
        .then()
            .log().body()
            .assertThat()
            .statusCode(200);
    }
}