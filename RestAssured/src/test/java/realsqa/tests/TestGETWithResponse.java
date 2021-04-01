// Documentation: https://jsonplaceholder.typicode.com/
package realsqa.tests;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.restassured.response.Response;
import realsqa.common.BaseTest;

class TestGETWithResponse extends BaseTest
{
	@Test
	void testWithJsonPath()
	{
		Response result = 
			given().
				spec(requestSpec).
				baseUri("https://jsonplaceholder.typicode.com").
				basePath("/users").
			when().
				get().
			then().
				spec(responseSpec).
				extract().response();

// print response as JSON		
		System.out.println(result.prettyPrint());
		
/* Array of JSON objects
 * [
    {
        "id": 1,
        "name": "Leanne Graham",
        "username": "Bret",
        "email": "Sincere@april.biz",
        "address": {
            "street": "Kulas Light",
            "suite": "Apt. 556",
            "city": "Gwenborough",
            "zipcode": "92998-3874",
            "geo": {
                "lat": "-37.3159",
                "lng": "81.1496"
            }
        },
        "phone": "1-770-736-8031 x56442",
        "website": "hildegard.org",
        "company": {
            "name": "Romaguera-Crona",
            "catchPhrase": "Multi-layered client-server neural-net",
            "bs": "harness real-time e-markets"
        }
    },		
 */
// second element, "website" value		
		String element2website = result.jsonPath().get("[1].website"); 
		System.out.println(element2website);
		
// "website" array from all JSON objects		
		List<String> usersWebsites = result.jsonPath().getList("website");
		System.out.println("\nList of websites:");
		for (String website : usersWebsites)
			System.out.println(website);
		
// converting company JSON object from the second response element into a map		
		Map<String, String> company = result.jsonPath().getMap("[1].company");
		String companyName = company.get("name");
		System.out.println("\nCompany name: " + companyName);
	}
}
