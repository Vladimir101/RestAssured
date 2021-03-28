package realsqa;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

class TestGET
{
	@Test
	void testZippopotam()
	{
		baseURI = "http://api.zippopotam.us";
		
		given().
			get("/us/94404").
		then().
			statusCode(200).
			and().
			body("places[0].'place name'", equalTo("San Mateo")).
			and().
			body("places[0].state", equalTo("California")).
			and().
			body("'post code'", equalTo("94404")).
			log().all();
	}
	
	@Test
	void testJsonview()
	{
		baseURI = "https://jsonplaceholder.typicode.com";
		
		given().
			get("/users").
		then().
			body("username", hasItems("Bret", "Samantha")).
			body("[7].username", equalTo("Maxime_Nienow"));
		
	}
}
