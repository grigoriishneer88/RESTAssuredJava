import java.io.IOException;
import java.util.ArrayList;

public class testSample {
	public static void main(String[]args) throws IOException {
		dataDriven data_class = new dataDriven();
		ArrayList data = data_class.getData("Add profile", "RestAssured");
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
	}
}
