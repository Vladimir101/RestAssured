package realsqa.tests;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import realsqa.common.BaseTest;
import realsqa.reqbodies.Order;

class TestPOSTWithResponse extends BaseTest
{
	@Test
	void testWithJsonPath()
	{
		var order = new Order();
		order.setOrderID("256");
		
		Response result =
			given().
				spec(requestSpec).
				baseUri("http://httpbin.org").
				basePath("/post").
				body(order).
			when().
				post().
			then().
				spec(responseSpec).
				extract().response();
		
		System.out.println(result.asPrettyString());
		
		String host = result.path("headers.Host");		
		System.out.println("Host: " + host);
		
		JsonPath jsonPath = JsonPath.from(result.asPrettyString());
		String userAgent = jsonPath.getString("headers.User-Agent");
		System.out.println("User-Agent: " + userAgent);	
	}
}
