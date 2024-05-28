package jirautil;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import utilities.ExcelDataUtil;
import utilities.GlobalUtil;
import utilities.LogUtil;
import utilities.TestLinkUtil;

@SuppressWarnings("deprecation")
public class JiraUtil {

	public static String USERNAME = null;
	public static String PASSWORD = null;
	public static String JIRA_URL = null;
	public static String JIRA_PROJECT = null;
	public static String JIRA_PROJECT_ID = null;
	public static String JIRA_CYCLE_ID = null;
	public static String ZEPHYR_URL = null;
	// zephyr accessKey , we can get from Addons >> zapi section
	public static String ZAPI_ACCESS_KEY = "YmQ2YjBjM2YtMmEwZS0zNGRjLTllYTgtMWEzYmY4Mjg3NGNjIDYyMDJmODVjNzMzNDA3MDA2NzU0Nzg5OSBVU0VSX0RFRkFVTFRfTkFNRQ";
	// zephyr secretKey , we can get from Addons >> zapi section
	public static String ZAPI_SECRET_KEY = "0zVM-EjTbUoJgpz18P5Hf21wJV-qbIEKLf-G0ycnfuw";

	static BasicCredentials creds = null;
	static JiraClient jira = null;
	static Issue issue;
	static ZFJCloudRestClient ZAPI_CLIENT;
	@SuppressWarnings("unused")
	private static final String API_UPDATE_EXECUTION = "{SERVER}/public/rest/api/1.0/execution/";
	private static final String API_GET_EXECUTIONS = "{SERVER}/public/rest/api/1.0/executions/search/cycle/";

	public JiraUtil() {
		creds = new BasicCredentials(USERNAME, PASSWORD);
		jira = new JiraClient(JIRA_URL, creds);
		ZAPI_CLIENT = ZFJCloudRestClient.restBuilder(ZEPHYR_URL, ZAPI_ACCESS_KEY, ZAPI_SECRET_KEY, USERNAME).build();
	}

	/**
	 * This method returns the issue from Jira to perform actions on that issue.
	 *
	 * @param key The key of the issue in Jira
	 */
	public static void getIssue(String key) {
		try {
			issue = jira.getIssue(key);
		} catch (JiraException e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
		}
	}

	/**
	 * This method changes the state of an issue from TO-DO to IN PROGRESS
	 */
	public static void changeStatusOfIssueFrom_TODO_to_INPROGRESS() {
		try {
			issue.transition().execute("Start Progress");
		} catch (JiraException e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
		}
	}

	/**
	 * This method changes the state of an issue from IN PROGRESS to DONE
	 */
	public static void changeStatusOfIssueFrom_INPROGRESS_to_DONE() {
		try {
			issue.transition().execute("Resolved");
		} catch (JiraException e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
		}
	}

	/**
	 * This method changes the state of an issue from IN PROGRESS to TO-DO
	 */
	public static void changeStatusOfIssueFrom_INPROGRESS_to_TODO() {
		try {
			issue.transition().execute("Stop Progress");
		} catch (JiraException e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
		}
	}

	/**
	 * This method changes the state of an issue from DONE to IN PROGRESS
	 */
	public static void changeStatusOfIssueFrom_DONE_to_INPROGRESS() {
		try {
			issue.transition().execute("Unresolved");
		} catch (JiraException e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
		}
	}

	/**
	 * This method changes the state of an issue from DONE to TO-DO
	 */
	public static void changeStatusOfIssueFrom_DONE_to_TODO() {
		try {
			issue.transition().execute("Reopen");
		} catch (JiraException e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
		}
	}

	/**
	 * This method updates the value in the Description filed of a Jira Issue.
	 *
	 * @param description Description to be added to a Jira issue.
	 */
	public static void changeDesc(String description) {
		try {
			issue.update().field(Field.DESCRIPTION, description).execute();
		} catch (JiraException e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
		}
	}

	/**
	 * This method updates the value in the Summary field of an issue in Jira.
	 *
	 * @param summary Summary to be added into the Jira Issue
	 */
	public static void changeSummary(String summary) {
		try {
			issue.update().field(Field.SUMMARY, summary).execute();
		} catch (JiraException e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
		}
	}

	/**
	 * This method updates the execution status of the tests.
	 *
	 * @param uriStr        URI of Jira Instance
	 * @param client        Object of ZFJCloudRestClient
	 * @param accessKey     Key of the user performing the action through the API
	 * @param executionJSON JSON body of the Execution
	 * @return executionStatus Status of the Execution
	 */
	@SuppressWarnings({ "unused", "resource" })
	private static String updateExecutions(String uriStr, ZFJCloudRestClient client, String accessKey,
			StringEntity executionJSON) {
		String executionStatus = "No Test Executed";
		try {

			URI uri = new URI(uriStr);
			int expirationInSec = 360;
			JwtGenerator jwtGenerator = client.getJwtGenerator();
			String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);

			HttpResponse response;
			HttpClient restClient = new DefaultHttpClient();

			HttpPut executeTest = new HttpPut(uri);
			executeTest.addHeader("Content-Type", "application/json");
			executeTest.addHeader("Authorization", jwt);
			executeTest.addHeader("zapiAccessKey", accessKey);
			executeTest.setEntity(executionJSON);
			response = restClient.execute(executeTest);

			// get the status of the rest
			int statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();

			if (statusCode >= 200 && statusCode < 300) {
				String string;
				try {
					string = EntityUtils.toString(entity);
					JSONObject executionResponseObj = new JSONObject(string);
					JSONObject descriptionResponseObj = executionResponseObj.getJSONObject("execution");
					JSONObject statusResponseObj = descriptionResponseObj.getJSONObject("status");
					executionStatus = statusResponseObj.getString("description");
					LogUtil.infoLog(JiraUtil.class, executionResponseObj.get("issueKey") + "--" + executionStatus);
				} catch (Exception e) {
					LogUtil.errorLog(JiraUtil.class, e.getMessage());
					e.printStackTrace();
				}

			} else {
				try {
					throw new ClientProtocolException("Unexpected response status: " + statusCode);
				} catch (ClientProtocolException e) {
					LogUtil.errorLog(JiraUtil.class, e.getMessage());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
			e.printStackTrace();
		}
		return executionStatus;
	}

	/**
	 * This method fetches the Execution Ids of the tests in a Cycle
	 *
	 * @return Map of Execution Ids
	 */
	@SuppressWarnings("unused")
	private static Map<String, String> getExecutionsID() {
		Map<String, String> executionIds = new HashMap<>();
		try {
			final String getExecutionsUri = API_GET_EXECUTIONS.replace("{SERVER}", ZEPHYR_URL) + JIRA_CYCLE_ID
					+ "?projectId=" + JIRA_PROJECT_ID + "&versionId=-1";
			executionIds = getExecutionsByCycleId(getExecutionsUri, ZAPI_CLIENT, ZAPI_ACCESS_KEY);
		} catch (Exception e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
			e.printStackTrace();
		}
		return executionIds;
	}

	/**
	 * This method gets the Execution ID with the help of the Cycle ID
	 *
	 * @param uriStr    URI of Jira Instance
	 * @param client    Object of ZFJCloudRestClient
	 * @param accessKey Key of the user performing the action through the API
	 * @return executionIds Map containing the Ids of Executions
	 * @throws URISyntaxException Exception if there are any issues with the URI
	 * @throws JSONException      Exception if there is any issue with the JSON Body
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
			LogUtil.errorLog(JiraUtil.class, e1.getMessage());
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
				LogUtil.errorLog(JiraUtil.class, e.getMessage());
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

				executionIds.put(String.valueOf(IssueId), executionId);
			}
		}
		return executionIds;
	}

	/**
	 * This method logs a Bug in Jira.
	 *
	 * @param summary        Specify the summary to be added to the new bug.
	 * @param description    Specify the description to be added to the new bug.
	 * @param attachmentPath Specify the path of the attachment to be added to the
	 *                       new bug. The name of the attachment is same as the
	 *                       original name of the image.
	 * @return bugId ID of the Bug recently logged
	 */
	public String reportIssue(String summary, String description, String attachmentPath) {

		if (GlobalUtil.getCommonSettings().getbugTool().equalsIgnoreCase("NO")) {
			LogUtil.infoLog(JiraUtil.class, "Jira Bug tool is Disabled in Control sheet");
			return "Bug tool not configured";
		}
		Issue newIssue;
		String bugID = "No Bug Created. Please check Configuration";
		try {
			// create an issue
			newIssue = jira.createIssue(JIRA_PROJECT, "Bug").field(Field.SUMMARY, summary)
					.field(Field.DESCRIPTION, description).field(Field.PRIORITY, "High").execute();

			// add an attachment to the issue created
			if (!attachmentPath.equalsIgnoreCase("RestAPI")) {
				File file = new File(attachmentPath);
				newIssue.addAttachment(file);
			}
			bugID = newIssue.getId();
			return bugID;

		} catch (JiraException e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
			e.printStackTrace();
		}
		return bugID;

	}

	/**
	 * This method updates the results of a Test Case in Jira
	 *
	 * @param ExecutionID: ID is the number which we get when the testcase is added
	 *                     to a Test cycle and do inspect element and in the html
	 *                     search for "data-issueid"
	 * @param Comments:    String
	 * @param result       : Fail
	 */
	@SuppressWarnings("unused")
	public void updateJiraTestResults(String ExecutionID, String Comments, String result) {
		try {

			if (ExecutionID == null)
				return;
			if (GlobalUtil.getCommonSettings().getJiraTestManagement().equalsIgnoreCase("No")) {
				LogUtil.infoLog(JiraUtil.class, "Jira tool is Disabled in Control sheet");
				return;
			}
			// creating the Rest object to pass through REST API for Updating the testcase
			// Result
			Map<String, String> executionIds = getExecutionsID();
			JSONObject statusObj = new JSONObject();
			final String updateExecutionUri = API_UPDATE_EXECUTION.replace("{SERVER}", ZEPHYR_URL)
					+ executionIds.get(ExecutionID);
			String statusID = "1";
			if (result.equalsIgnoreCase("Fail"))
				statusID = "2";
			statusObj.put("id", statusID);
			JSONObject executeTestsObj = new JSONObject();
			executeTestsObj.put("status", statusObj);
			executeTestsObj.put("cycleId", JIRA_CYCLE_ID);
			executeTestsObj.put("projectId", JIRA_PROJECT_ID);
			executeTestsObj.put("versionId", "-1");
			executeTestsObj.put("comment", Comments);
			executeTestsObj.put("issueId", ExecutionID);
			StringEntity executeTestsJSON = null;
			executeTestsJSON = new StringEntity(executeTestsObj.toString());
			// call the function to update the result
			updateExecutions(updateExecutionUri, ZAPI_CLIENT, ZAPI_ACCESS_KEY, executeTestsJSON);
			LogUtil.infoLog(TestLinkUtil.class, "Updated Results to Jira");
		} catch (Exception e) {
			LogUtil.errorLog(JiraUtil.class, e.getMessage());
			e.printStackTrace();
		}
	}

	// DO NOT DELETE
	public static void main(String[] args) {
		String issueKey = "TES-21088";
		getTheIdOfIssue(issueKey);
	}

	private static void getTheIdOfIssue(String issueKey) {
		try {
			GlobalUtil.setCommonSettings(ExcelDataUtil.getCommonSettings());
			JIRA_URL = GlobalUtil.getCommonSettings().getbugToolHostName();
			USERNAME = GlobalUtil.getCommonSettings().getbugToolUserName();
			PASSWORD = GlobalUtil.getCommonSettings().getbugToolPassword();

			creds = new BasicCredentials(USERNAME, PASSWORD);
			jira = new JiraClient(JIRA_URL, creds);
			Issue issue = jira.getIssue(issueKey);
			System.out.println(String.format("Issue Key = %s\nIssue Id = %s\nIssue Summary = %s\n", issueKey,
					issue.getId(), issue.getSummary()));
		} catch (JiraException e) {
			e.printStackTrace();
		}
	}

}
