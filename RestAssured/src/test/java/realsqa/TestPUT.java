package realsqa;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import org.json.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestPUT
{
	private static String token;
	
	@BeforeAll
	static void getAuthToken()
	{
		baseURI = "https://restful-booker.herokuapp.com";
		
		var reqBody = new JSONObject();
		reqBody.put("username", "admin");
		reqBody.put("password", "password123");
		
		Response response = given().
			contentType(ContentType.JSON).
			body(reqBody.toString()).
		when().
			post("/auth").
		then().
			statusCode(200).extract().response();

		token = response.path("token");
	}
	
	@Test
	void testPUT()
	{
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
		
		System.out.println(reqBody.toString());
		
		given().
			cookie("token", token).
			contentType(ContentType.JSON).
				body(reqBody.toString()).
		when().
			put("/booking/1").
		then().
			statusCode(200).
			body("firstname", equalTo("Tony")).
			body("lastname", equalTo("B")).
			log().all();
	}
}