package realsqa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
// https://jsonplaceholder.typicode.com/comments?postId=6

class TestGETWithParams 
{
	@Test
	void test()
	{
		given().
			baseUri("https://jsonplaceholder.typicode.com").
			basePath("/comments").
			param("postId", 6).
		when().
			get().
		then().
			body("", hasSize(5)).
			log().body();
	}
}
