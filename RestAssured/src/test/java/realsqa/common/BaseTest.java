package realsqa.common;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest
{
	protected static RequestSpecification requestSpec;
	protected static RequestSpecification requestSpec2;
	protected static ResponseSpecification responseSpec;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		requestSpec = new RequestSpecBuilder().
				setAccept(ContentType.JSON).
	            build();
		
		requestSpec2 = new RequestSpecBuilder().
				setContentType(ContentType.JSON).
				setAccept(ContentType.JSON).
	            build();

        responseSpec = new ResponseSpecBuilder().
               expectStatusCode(200).
               expectContentType(ContentType.JSON).  // "text/html"
               build();
	}
}
