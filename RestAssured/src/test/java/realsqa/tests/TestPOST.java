package realsqa.tests;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class TestPOST
{
	@BeforeAll
	static void setUpURI()
	{
		baseURI = "http://postman-echo.com";
	}
	
	@Test
	void testPostmanEcho()
	{		
		var reqBody = new JSONObject();
		reqBody.put("name", "Tony");
		reqBody.put("relation", "son");
		
		given().
			log().all().		// print request
			contentType(ContentType.JSON).
			accept("application/json").
			body(reqBody.toString()).
		when().
			post("/post").
		then().
			statusCode(200).
			statusLine("HTTP/1.1 200 OK").
			body("json.name", equalTo("Tony")).
			log().body(true);	// print response
	}
}
