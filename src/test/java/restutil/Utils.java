package restutil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

/**
 * The type Utils.
 */
public class Utils {
    /**
     * The constant path.
     */
// Global Setup Variables
    public static String path;
    /**
     * The constant jsonPathTerm.
     */
    public static String jsonPathTerm;

    /**
     * Sets base uri.
     *
     * @param baseURI the base uri
     */
// Sets Base URI
    public static void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    /**
     * Sets base path.
     *
     * @param basePathTerm the base path term
     */
// Sets base path
    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
    }

    /**
     * Reset base uri.
     */
// Reset Base URI (after test)
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    /**
     * Reset base path.
     */
// Reset base path
    public static void resetBasePath() {
        RestAssured.basePath = null;
    }

    /**
     * Sets content type.
     *
     * @param Type the type
     */
// Sets ContentType
    public static void setContentType(ContentType Type) {
        given().contentType(Type);
    }

    /**
     * Sets json path term.
     *
     * @param jsonPath the json path
     */
// Sets Json path term
    public static void setJsonPathTerm(String jsonPath) {
        jsonPathTerm = jsonPath;
    }

    /**
     * Create search query path.
     *
     * @param searchTerm the search term
     * @param param      the param
     * @param paramValue the param value
     */
// Created search query path
    public static void createSearchQueryPath(String searchTerm, String param, String paramValue) {
        path = searchTerm + "/" + jsonPathTerm + "?" + param + "=" + paramValue;
    }

    /**
     * Gets response.
     *
     * @return the response
     */
// Returns response
    public static Response getResponse() {
        return get(path);

    }

    /**
     * Gets json path.
     *
     * @param res the res
     * @return the json path
     */
// Returns JsonPath object
    public static JsonPath getJsonPath(Response res) {
        String json = res.asString();
        return new JsonPath(json);
    }

    /**
     * Check status is 200.
     *
     * @param res the res
     */
// Verify the http response status returned. Check Status Code is 200?
    public static void checkStatusIs200(Response res) {
        Assert.assertEquals(200, res.getStatusCode(), "Status Check Failed!");
    }
}