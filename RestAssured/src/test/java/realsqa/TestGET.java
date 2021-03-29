package realsqa;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

class TestGET
{
	@Test
	void testZippopotam()
	{		
		given().
			baseUri("http://api.zippopotam.us").
		when().
			get("/us/94404").
		then().
			statusCode(200). // the same as assertThat().statusCode(equalTo(200));
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
		when().
			get("/users").
		then().
			contentType("application/json").  // the same as ContentType.JSON
			body("username", hasItems("Bret", "Samantha")).
			body("username", hasItem("Delphine")).			
			body("[7].username", equalTo("Maxime_Nienow")).
			body("", hasSize(10));	
	}
}
