package restutil;

import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import step_definitions.RunCukesTest;
import utilities.HTMLReportUtil;

import static io.restassured.RestAssured.given;

/**
 * The type Rest utils.
 */
public class RestUtils {
    /**
     * The constant path.
     */
    public static String path;
    /**
     * The constant jsonPathTerm.
     */
    public static String jsonPathTerm;
    /**
     * The Rs.
     */
    static RequestSpecification rs;

    /**
     * Sets base uri.
     *
     * @param baseURI the base uri
     * @param logStep the log step
     */
// Sets Base URI
    public static void setBaseURI(String baseURI, String logStep) {
        RestAssured.baseURI = baseURI;
        RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
    }

    /**
     * Sets base path.
     *
     * @param basePathTerm the base path term
     * @param logStep      the log step
     */
// Sets base path
    public static void setBasePath(String basePathTerm, String logStep) {
        RestAssured.basePath = basePathTerm;
        RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
    }

    /**
     * Reset base uri.
     *
     * @param logStep the log step
     */
// Reset Base URI (after test)
    public static void resetBaseURI(String logStep) {
        RestAssured.baseURI = null;
        RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
    }

    /**
     * Reset base path.
     *
     * @param logStep the log step
     */
// Reset base path
    public static void resetBasePath(String logStep) {
        RestAssured.basePath = null;
        RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
    }

    /**
     * Sets content body type.
     *
     * @param Type    the type
     * @param body    the body
     * @param logStep the log step
     */
// Sets ContentType
    public static void setContentBodyType(ContentType Type, String body, String logStep) {
        rs = given().contentType(Type).body(body);
        RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
    }

    /**
     * Sets content body type.
     *
     * @param Type        the type
     * @param accessToken the access token
     * @param body        the body
     * @param logStep     the log step
     */
    public static void setContentBodyType(ContentType Type, String accessToken, String body, String logStep) {
        rs = given().contentType(Type).header("Authorization", accessToken).body(body).log().all();
        RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
    }

    /**
     * Gets response.
     *
     * @param logStep the log step
     * @return the response
     */
// Returns response
    public static Response getResponse(String logStep) {
        RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
        return rs.when().get();
    }

    /**
     * Post response response.
     *
     * @param type    the type
     * @param logStep the log step
     * @return the response
     */
// Returns response
    public static Response PostResponse(String type, String logStep) {
        RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
        return rs.when().post("");
    }

    /**
     * Gets json path.
     *
     * @param res     the res
     * @param logStep the log step
     * @return the json path
     */
// Returns JsonPath object
    public static JsonPath getJsonPath(Response res, String logStep) {
        String json = res.asString();
        RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
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

    /**
     * Gets value from json.
     *
     * @param res     the res
     * @param path    the path
     * @param logStep the log step
     * @return the value from json
     */
    public static Object getValueFromJson(Response res, String path, String logStep) {
        String json = res.asString();
        RunCukesTest.logger.log(LogStatus.PASS, HTMLReportUtil.passStringGreenColor(logStep));
        return new JsonPath(json).getString(path);

    }
}