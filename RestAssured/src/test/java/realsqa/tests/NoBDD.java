package realsqa.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class NoBDD
{
	@Test
	void testZipCode()
	{
		Response result = get("http://api.zippopotam.us/us/94404");		
		assertThat(result.getStatusCode(), is(equalTo(200)));

		System.out.println(result.getStatusLine());
		System.out.println(result.getBody().asPrettyString()); // asString()
		System.out.println(result.getHeader("Content-Type"));
	}
}
