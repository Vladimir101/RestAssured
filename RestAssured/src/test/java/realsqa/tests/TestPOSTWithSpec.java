package realsqa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import realsqa.common.*;
import realsqa.reqbodies.Order;

class TestPOSTWithSpec extends BaseTest
{
	@Test
	void testHttpbin()
	{
		var order = new Order();
		order.setOrderID("123");
		
		given().
        	spec(requestSpec).
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
