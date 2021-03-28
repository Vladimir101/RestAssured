package realsqa;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

class TestPOST
{
	@Test
	void test()
	{		
		baseURI = "http://postman-echo.com";

		var reqBody = new JSONObject();
		reqBody.put("name", "Tony");
		reqBody.put("relation", "son");
//		System.out.println(reqBody.toString(4));
		
		given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(reqBody.toString()).
		when().
			post("/post").
		then().
			statusCode(200).
			body("json.name", equalTo("Tony")).
			log().body(true);
		
	}
}
