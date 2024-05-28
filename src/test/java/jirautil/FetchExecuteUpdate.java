package jirautil;

import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utilities.LogUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class FetchExecuteUpdate {
	// JIRA Cloud URL of the instance
	private static final String jiraBaseURL = "https://testingxpert.atlassian.net";
	// Replace zephyr baseurl <ZAPI_Cloud_URL> shared with the user for ZAPI Cloud
	// Installation
	private static final String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
	// zephyr accessKey , we can get from Addons >> zapi section
	private static final String accessKey = "YmQ2YjBjM2YtMmEwZS0zNGRjLTllYTgtMWEzYmY4Mjg3NGNjIDYyMDJmODVjNzMzNDA3MDA2NzU0Nzg5OSBVU0VSX0RFRkFVTFRfTkFNRQ";
	// zephyr secretKey , we can get from Addons >> zapi section
	private static final String secretKey = "0zVM-EjTbUoJgpz18P5Hf21wJV-qbIEKLf-G0ycnfuw";

	// Declare JIRA,Zephyr URL,access and secret Keys
	// Declare parameter values here
	private static final String userName = "testaccount1@testingxperts.com";
	@SuppressWarnings("unused")
	private static final String password = "72phbdI56sCaaCYIr7Se5451";
	// inspect element: <li id="cycle-0001503993710547-242ac112-0001">
	private static final String versionId = "-1";
	// element & check for example:
	// href:/plugins/servlet/ac/com.thed.zephyr.je/general-zephyr-welcome?user.key=admin&project.key=TES01&*******project.id=10000****&user.id=admin
	private static final String projectId = "10001"; // mouse hover of the main menu any tab like Test and inspect
	static ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName)
			.build();
	private static final String API_SEARCH_ISSUES = "{SERVER}/rest/api/2/search";
	@SuppressWarnings("unused")
	private static final String API_ADD_TESTS = "{SERVER}/public/rest/api/1.0/executions/add/cycle/";
	private static final String API_GET_EXECUTIONS = "{SERVER}/public/rest/api/1.0/executions/search/cycle/";
	private static final String API_UPDATE_EXECUTION = "{SERVER}/public/rest/api/1.0/execution/";
	private static String cycleId = "0001505811674768-242ac112-0001";// after creating cycle check for example in
	JwtGenerator jwtGenerator = client.getJwtGenerator();

	@SuppressWarnings("unused")
	public static void main(String[] args) throws JSONException, URISyntaxException, ParseException, IOException {
		final String issueSearchUri = API_SEARCH_ISSUES.replace("{SERVER}", jiraBaseURL);

		// Get Execution Ids by CycleId and Add them to Ad Hoc cycle of UnScheduled
		// Version
		Map<String, String> executionIds;
		final String getExecutionsUri = API_GET_EXECUTIONS.replace("{SERVER}", zephyrBaseUrl) + cycleId + "?projectId="
				+ projectId + "&versionId=" + versionId;

		executionIds = getExecutionsByCycleId(getExecutionsUri, client, accessKey);

		// Bulk Update Executions with Status by Execution Id

		JSONObject statusObj = new JSONObject();
		// id = 1 --> pass
		// id = 2 --> fail

		statusObj.put("id", "1");

		JSONObject executeTestsObj = new JSONObject();
		executeTestsObj.put("status", statusObj);
		executeTestsObj.put("cycleId", cycleId);
		executeTestsObj.put("projectId", projectId);
		executeTestsObj.put("versionId", versionId);
		executeTestsObj.put("comment", "Executed by ZAPI Cloud");

		for (String key : executionIds.keySet()) {
			final String updateExecutionUri = API_UPDATE_EXECUTION.replace("{SERVER}", zephyrBaseUrl) + key;
			executeTestsObj.put("issueId", executionIds.get(key));
			StringEntity executeTestsJSON = null;
			try {
				executeTestsJSON = new StringEntity(executeTestsObj.toString());
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			updateExecutions(updateExecutionUri, client, accessKey, executeTestsJSON);
		}

	}

	/**
	 * @param issueSearchURL URL to search for Issues
	 * @param userName       Name of the user performing the actions through API
	 * @param password       Password of the user performing the actions through API
	 * @param jqlJsonObj     JSON object of the JQL
	 * @return issueIds String Array containing the Ids of the issue
	 * @throws JSONException Exception if the JSON body has some errors
	 */
	@SuppressWarnings({ "unused", "resource" })
	private static String[] getIssuesByJQL(String issueSearchURL, String userName, String password,
			JSONObject jqlJsonObj) throws JSONException {

		byte[] bytesEncoded = Base64.encodeBase64((userName + ":" + password).getBytes());
		String authorizationHeader = "Basic " + new String(bytesEncoded);
		Header header = new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationHeader);

		String[] issueIds = new String[jqlJsonObj.getInt("maxResults")];

		StringEntity jqlJSON = null;
		try {
			jqlJSON = new StringEntity(jqlJsonObj.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		HttpResponse response = null;
		HttpClient restClient = new DefaultHttpClient();
		try {
			HttpPost createProjectReq = new HttpPost(issueSearchURL);
			createProjectReq.addHeader(header);
			createProjectReq.addHeader("Content-Type", "application/json");
			createProjectReq.setEntity(jqlJSON);

			response = restClient.execute(createProjectReq);
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert response != null;
		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity1 = response.getEntity();
			String string1 = null;
			try {
				string1 = EntityUtils.toString(entity1);
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}

			LogUtil.infoLog(FetchExecuteUpdate.class, string1);
			assert string1 != null;
			JSONObject allIssues = new JSONObject(string1);
			JSONArray IssuesArray = allIssues.getJSONArray("issues");
			if (IssuesArray.length() == 0) {
				return issueIds;
			}

			for (int j = 0; j < IssuesArray.length(); j++) {
				JSONObject jobj = IssuesArray.getJSONObject(j);
				String issueId = jobj.getString("id");
				issueIds[j] = issueId;
			}
		}
		return issueIds;
	}

	/**
	 * @param uriStr    URI of the Jira instance
	 * @param client    Object of ZFJCloudRestClient
	 * @param accessKey Access Key of the user performing the actions
	 * @return executionIds Map containing the Execution Ids of the tests in the
	 *         Test Cycle
	 * @throws URISyntaxException Exception if the URI is incorrect
	 * @throws JSONException      Exception if the JSON body is incorrect
	 */
	@SuppressWarnings("resource")
	private static Map<String, String> getExecutionsByCycleId(String uriStr, ZFJCloudRestClient client,
			String accessKey) throws URISyntaxException, JSONException {
		Map<String, String> executionIds = new HashMap<>();
		URI uri = new URI(uriStr);
		int expirationInSec = 360;
		JwtGenerator jwtGenerator = client.getJwtGenerator();
		String jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);

		HttpResponse response = null;
		HttpClient restClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader("Authorization", jwt);
		httpGet.setHeader("zapiAccessKey", accessKey);

		try {
			response = restClient.execute(httpGet);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		assert response != null;
		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity1 = response.getEntity();
			String string1 = null;
			try {
				string1 = EntityUtils.toString(entity1);
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}

			assert string1 != null;
			JSONObject allIssues = new JSONObject(string1);
			JSONArray IssuesArray = allIssues.getJSONArray("searchObjectList");
			if (IssuesArray.length() == 0) {
				return executionIds;
			}
			for (int j = 0; j <= IssuesArray.length() - 1; j++) {
				JSONObject jobj = IssuesArray.getJSONObject(j);
				JSONObject jobj2 = jobj.getJSONObject("execution");
				String executionId = jobj2.getString("id");
				long IssueId = jobj2.getLong("issueId");
				executionIds.put(executionId, String.valueOf(IssueId));
			}
		}
		return executionIds;
	}

	/**
	 * @param uriStr        URI of the Jira instance
	 * @param client        Object of ZFJCloudRestClient
	 * @param accessKey     Access Key of the user performing the actions
	 * @param executionJSON JSON body for the request
	 * @return executionStatus Status of the Execution of Tests
	 * @throws URISyntaxException Exception if the URI is incorrect
	 * @throws JSONException      Exception if the JSON body is incorrect
	 * @throws ParseException     Exception if any error with Parsing
	 * @throws IOException        Exception if anu issues with the IO operations
	 */
	@SuppressWarnings("resource")
	public static String updateExecutions(String uriStr, ZFJCloudRestClient client, String accessKey,
			StringEntity executionJSON) throws URISyntaxException, JSONException, ParseException, IOException {

		URI uri = new URI(uriStr);
		int expirationInSec = 360;
		JwtGenerator jwtGenerator = client.getJwtGenerator();
		String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);

		HttpResponse response = null;
		HttpClient restClient = new DefaultHttpClient();

		HttpPut executeTest = new HttpPut(uri);
		executeTest.addHeader("Content-Type", "application/json");
		executeTest.addHeader("Authorization", jwt);
		executeTest.addHeader("zapiAccessKey", accessKey);
		executeTest.setEntity(executionJSON);

		try {
			response = restClient.execute(executeTest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert response != null;
		int statusCode = response.getStatusLine().getStatusCode();
		String executionStatus = "No Test Executed";
		HttpEntity entity = response.getEntity();

		if (statusCode >= 200 && statusCode < 300) {
			String string = null;
			try {
				string = EntityUtils.toString(entity);
				JSONObject executionResponseObj = new JSONObject(string);
				JSONObject descriptionResponseObj = executionResponseObj.getJSONObject("execution");
				JSONObject statusResponseObj = descriptionResponseObj.getJSONObject("status");
				executionStatus = statusResponseObj.getString("description");
				LogUtil.infoLog(FetchExecuteUpdate.class,
						executionResponseObj.get("issueKey") + "--" + executionStatus);
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}

		} else {

			try {
				String string = null;
				string = EntityUtils.toString(entity);
				JSONObject executionResponseObj = new JSONObject(string);
				cycleId = executionResponseObj.getString("clientMessage");
				throw new ClientProtocolException("Unexpected response status: " + statusCode);

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
		return executionStatus;
	}
}