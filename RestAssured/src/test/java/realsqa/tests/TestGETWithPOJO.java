// Documentation: http://worldtimeapi.org/
package realsqa.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import org.junit.jupiter.api.Test;

import realsqa.common.BaseTest;
import realsqa.pages.TimezonePage;

class TestGETWithPOJO extends BaseTest
{
	@Test
	void testWorldTime()
	{
// deserializing response to POJO		
		TimezonePage response = 
				given(requestSpec).   // spec is inside given()
				baseUri("http://worldtimeapi.org/api/timezone").
			when().
				get("/America/Los_Angeles").
				as(TimezonePage.class);
		
		assertThat(response.getAbbreviation(), equalTo("PST"));
		assertThat(response.getTimezone(), equalTo("America/Los_Angeles"));
	}
}
