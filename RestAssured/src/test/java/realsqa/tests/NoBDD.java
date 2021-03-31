package realsqa.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

class NoBDD
{
	@Test
	void testZipCode()
	{
		Response result = RestAssured.get("http://api.zippopotam.us/us/94404");
		assertEquals(200, result.getStatusCode());
		
		System.out.println(result.getStatusLine());
		System.out.println(result.getBody().asPrettyString());
		System.out.println(result.getHeader("Content-Type"));
	}
}
