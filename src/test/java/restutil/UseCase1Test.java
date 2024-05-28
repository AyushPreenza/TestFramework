package restutil;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * The type Use case 1 test.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // For Ascending order test execution
public class UseCase1Test {

    private Response res = null; // Response
    private JsonPath jp = null; // JsonPath

    /**
     * Sets .
     */
// Instantiate a Helper Test Methods (htm) Object
    @Before
    public void setup() {
        // Test Setup
        Utils.setBaseURI("http://api.5min.com"); // Setup Base URI
        Utils.setBasePath("search"); // Setup Base Path
        Utils.setContentType(ContentType.JSON); // Setup Content Type
        Utils.setJsonPathTerm("videos.json"); // Setup Json Path Term
        Utils.createSearchQueryPath("paris hilton", "num_of_videos", "5"); // Set up search term, param and param value
        res = Utils.getResponse(); // Get response
        jp = Utils.getJsonPath(res); // Set JsonPath
    }

    /**
     * T 01 status code test.
     */
    @Test
    public void T01_StatusCodeTest() {
        // Verify the http response status returned. Check Status Code is 200?
        Utils.checkStatusIs200(res);
    }

    /**
     * T 02 search term test.
     */
    @Test
    public void T02_SearchTermTest() {
        // Verify the title is correct
        Assert.assertEquals("Title is wrong!", ("Search results for \"paris hilton\""), jp.get("api-info.title"));
    }

    /**
     * After test.
     */
    @After
    public void afterTest() {
        // Reset Values
        Utils.resetBaseURI();
        Utils.resetBasePath();
    }
}