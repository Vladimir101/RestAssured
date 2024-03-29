package realsqa.tests;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import realsqa.reqbodies.Relation;
import java.io.File;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class TestPOST
{
	@BeforeAll
	static void setUpURI()
	{
		baseURI = "http://postman-echo.com";
	}

// POST body as a JSON object	
	@Test
	void testPostmanEcho()
	{		
		var reqBody = new JSONObject();
		reqBody.put("name", "Tony");
		reqBody.put("relation", "son");
		
		given().
			log().all().		
			contentType(ContentType.JSON).
			accept("application/json").
			body(reqBody.toString()).
		when().
			post("/post").
		then().
			statusCode(200).
			statusLine("HTTP/1.1 200 OK").
			body("json.name", equalTo("Tony"));
//			log().body();	
	}

// POST body as a POJO	
	@Test
	void testPostmanEcho2()
	{		
		var relation = new Relation();
		relation.setName("Sofya");
		relation.setRelation("mama");
		
		given().
			log().all().		
			contentType(ContentType.JSON).
			accept("application/json").
			body(relation).
		when().
			post("/post").
		then().
			statusCode(200).
			statusLine("HTTP/1.1 200 OK").
			body("json.name", equalTo("Sofya")).
			log().body(true);	
	}
	
// POST body as a JSON file	
	@Test
	void testPostmanEcho3()
	{		
		var relation = new File("src/test/java/realsqa/reqbodies/relation.json");
		
		given().		
			contentType(ContentType.JSON).
			accept("application/json").
			body(relation).
		when().
			post("/post").
		then().
			statusCode(200).
			statusLine("HTTP/1.1 200 OK").
			body("json.name", equalTo("Boris")).
			log().body(true);	
	}
}
