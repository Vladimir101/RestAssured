package realsqa;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class DDT
{
	@BeforeAll
	static void setUp()
	{
		baseURI = "https://airportgap.dev-tester.com/api";
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "airports.csv", numLinesToSkip = 1)
	void test(String airport, String country)
	{
		given().
			pathParam("airport", airport).
		when().
			get("/airports/{airport}").
		then().
			body("data.attributes.country", equalTo(country));
	}
}
