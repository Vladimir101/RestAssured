package realsqa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import realsqa.common.*;
import realsqa.reqbodies.Order;

class TestPOSTWithSpec extends BaseTest
{
	@Test
	void testHttpbin()
	{
// printing request specifications		
		QueryableRequestSpecification queryable = SpecificationQuerier.query(requestSpec);
		
		String contentType = queryable.getHeaders().getValue("Content-Type");
		String accept = queryable.getHeaders().getValue("Accept");
		System.out.println("Content-Type: " + contentType + "\nAccept: " + accept);

// POST body		
		var order = new Order();
		order.setOrderID("123");
		
		given().
			spec(requestSpec).		// the same, as given(requestSpec). 
        	baseUri("http://httpbin.org").
        	body(order).
        when().
        	post("/post").
        then().
        	spec(responseSpec).
        and().
        	body("json.orderID", equalTo("123"));
	}
}
