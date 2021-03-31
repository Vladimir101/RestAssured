package realsqa.tests;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import realsqa.common.BaseTest;

class TestGETWithParams extends BaseTest
{
	@Test
	void test()
	{
		given().
			baseUri("https://jsonplaceholder.typicode.com/").
			basePath("/comments").
			param("postId", 6).
		when().
			get().
		then().
			body("", hasSize(5));
	}
}
