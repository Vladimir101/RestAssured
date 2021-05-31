// Documentation: https://restful-booker.herokuapp.com/apidoc/index.html
package realsqa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.*;

import java.util.Base64;
import org.json.JSONObject;
import io.restassured.http.ContentType;

public class TestPUT
{
	private static String token;
	private static String username = "admin";
	private static String password = "password123";
	
	@BeforeAll
	static void getAuthToken()
	{
		baseURI = "https://restful-booker.herokuapp.com";
		
		var reqBody = new JSONObject();
		reqBody.put("username", username);
		reqBody.put("password", password);
		
		token = given().
			contentType(ContentType.JSON).
			body(reqBody.toString()).
		when().
			post("/auth").
		then().
			statusCode(200).
			extract().response().
			path("token");
		
		System.out.println("The token is " + token);
	}
	
/*  PUT request body
	{
	    "firstname" : "James",
	    "lastname" : "Brown",
	    "totalprice" : 111,
	    "depositpaid" : true,
	    "bookingdates" : {
	        "checkin" : "2018-01-01",
	        "checkout" : "2019-01-01"
	    },
	    "additionalneeds" : "Breakfast"
	} 
*/
	@Test
	void testPUT()
	{
		String basicAuth = username + ":" + password;
		
		var reqBody = new JSONObject();
		reqBody.put("firstname", "Tony");
		reqBody.put("lastname", "B");
		reqBody.put("totalprice", 10000);
		reqBody.put("depositpaid", true);
		reqBody.put("additionalneeds", "Breakfast");
		
		var bookingDates = new JSONObject();
		bookingDates.put("checkin", "2016-04-16");
		bookingDates.put("checkout", "2016-10-31");
		reqBody.put("bookingdates", bookingDates);
		
		given().			
//			header("Authorization", "Basic " + 
//				Base64.getEncoder().encodeToString(basicAuth.getBytes())).
			cookie("token", token).
			contentType(ContentType.JSON).
			body(reqBody.toString()).
			log().all().
		when().
			put("/booking/1").
		then().
			statusCode(200).
			body("firstname", equalTo("Tony")).
			body("lastname", equalTo("B")).
			log().all();
	}
}
