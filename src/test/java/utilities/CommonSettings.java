package utilities;

import io.appium.java_client.android.AndroidDriver;

/**
 * This CommonSetting class help in generate results
 */
public class CommonSettings {

	@SuppressWarnings("rawtypes")
	private static AndroidDriver mdriver;
	private String appType;
	private String appEnviornment;
	private String projectName;
	private String emailOutput;
	private String emailId;
	private String htmlReport;
	private String xlsReport;
	private String testLogs;
	private String executionEnv;
	private String cloudProvider;
	private String hostName;
	private String key;
	private String remoteOS;
	private String BuildNumber;
	private String Browser;
	private String Url;
	private String ManageToolName;
	private String testlinkTool;
	private String testLinkHostName;
	private String testlinkAPIKey;
	private String testlinkProjectName;
	private String testlinkPlanName;
	private String jiraTestManagement;
	private String jiraCycleID;
	private String jiraProjectID;
	private String bugToolName;
	private String bugTool;
	private String bugToolHostName;
	private String bugToolUserName;
	private String bugToolPassword;
	private String bugToolProjectName;
	private String restURL;
	private String restAccessToken;
	private Double androidVersion;
	private String androidName;
	private String androidID;
	private String androidBrowser;

	/**
	 * Instantiates a new Common settings.
	 *
	 * @param projectName    the project name
	 * @param appType        the app type
	 * @param appEnvironment the app environment
	 * @param emailOutput    the email output
	 * @param emailId        the email id
	 * @param htmlReport     the html report
	 * @param xlsReport      the xls report
	 * @param testLogs       the test logs
	 * @param defectMgmt     the defect mgmt
	 * @param testMgmt       the test mgmt
	 */
	public CommonSettings(String projectName, String appType, String appEnvironment, String emailOutput, String emailId,
						  String htmlReport, String xlsReport, String testLogs, String defectMgmt, String testMgmt) {
		super();
		this.projectName = projectName;
		this.appType = appType;
		this.appEnviornment = appEnvironment;
		this.emailOutput = emailOutput;
		this.emailId = emailId;
		this.htmlReport = htmlReport;
		this.xlsReport = xlsReport;
		this.testLogs = testLogs;
	}

	/**
	 * Instantiates a new Common settings.
	 */
	public CommonSettings() {
		super();
	}

	/**
	 * Gets project name.
	 *
	 * @return project name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets project name.
	 *
	 * @param projectName the project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Gets app type.
	 *
	 * @return app type
	 */
	public String getAppType() {
		return appType;
	}

	/**
	 * Sets app type.
	 *
	 * @param appType the app type
	 */
	public void setAppType(String appType) {
		this.appType = appType;
	}

	/**
	 * Gets app enviornment.
	 *
	 * @return app enviornment
	 */
	public String getAppEnviornment() {
		return appEnviornment;
	}

	/**
	 * Sets app enviornment.
	 *
	 * @param appEnviornment the app enviornment
	 */
	public void setAppEnviornment(String appEnviornment) {
		this.appEnviornment = appEnviornment;
	}

	/**
	 * Gets email output.
	 *
	 * @return email output
	 */
	public String getEmailOutput() {
		return emailOutput;
	}

	/**
	 * Sets email output.
	 *
	 * @param emailOutput the email output
	 */
	public void setEmailOutput(String emailOutput) {
		this.emailOutput = emailOutput;
	}

	/**
	 * Gets email ids.
	 *
	 * @return email ids
	 */
	public String getEmailIds() {
		return emailId;
	}

	/**
	 * Sets email ids.
	 *
	 * @param emailId the email id
	 */
	public void setEmailIds(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets html report.
	 *
	 * @return html report
	 */
	public String getHtmlReport() {
		return htmlReport;
	}

	/**
	 * Sets html report.
	 *
	 * @param htmlReport the html report
	 */
	public void setHtmlReport(String htmlReport) {
		this.htmlReport = htmlReport;
	}

	/**
	 * Gets xls report.
	 *
	 * @return xls report
	 */
	public String getXlsReport() {
		return xlsReport;
	}

	/**
	 * Sets xls report.
	 *
	 * @param xlsReport the xls report
	 */
	public void setXlsReport(String xlsReport) {
		this.xlsReport = xlsReport;
	}

	/**
	 * Gets test logs.
	 *
	 * @return test logs
	 */
	public String getTestLogs() {
		return testLogs;
	}

	/**
	 * Sets test logs.
	 *
	 * @param testLogs the test logs
	 */
	public void setTestLogs(String testLogs) {
		this.testLogs = testLogs;
	}

	/**
	 * Gets execution env.
	 *
	 * @return the execution env
	 */
	public String getExecutionEnv() {
		return executionEnv;
	}

	/**
	 * Sets execution env.
	 *
	 * @param executionEnv the execution env
	 */
	public void setExecutionEnv(String executionEnv) {
		this.executionEnv = executionEnv;
	}

	/**
	 * Gets cloud provider.
	 *
	 * @return the cloud provider
	 */
	public String getCloudProvider() {
		return cloudProvider;
	}

	/**
	 * Sets cloud provider.
	 *
	 * @param cloudProvider the cloud provider
	 */
	public void setCloudProvider(String cloudProvider) {
		this.cloudProvider = cloudProvider;
	}

	/**
	 * Gets host name.
	 *
	 * @return the host name
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * Sets host name.
	 *
	 * @param hostName the host name
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * Gets key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets key.
	 *
	 * @param key the key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets remote os.
	 *
	 * @return the remote os
	 */
	public String getRemoteOS() {
		return remoteOS;
	}

	/**
	 * Sets remote os.
	 *
	 * @param remoteOS the remote os
	 */
	public void setRemoteOS(String remoteOS) {
		this.remoteOS = remoteOS;
	}

	/**
	 * Gets build number.
	 *
	 * @return the build number
	 */
	public String getBuildNumber() {
		return BuildNumber;
	}

	/**
	 * Sets build number.
	 *
	 * @param buildNumber the build number
	 */
	public void setBuildNumber(String buildNumber) {
		BuildNumber = buildNumber;
	}

	/**
	 * Gets browser.
	 *
	 * @return the browser
	 */
	public String getBrowser() {
		return Browser;
	}

	/**
	 * Sets browser.
	 *
	 * @param browser the browser
	 */
	public void setBrowser(String browser) {
		Browser = browser;
	}

	/**
	 * Gets .
	 *
	 * @return the
	 */
	@SuppressWarnings("rawtypes")
	public AndroidDriver getdriver() {
		return mdriver;
	}

	/**
	 * Gets url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return Url;
	}

	/**
	 * Sets url.
	 *
	 * @param url the url
	 */
	public void setUrl(String url) {
		Url = url;
	}

	/**
	 * Gets testlink tool.
	 *
	 * @return the testlink tool
	 */
	public String getTestlinkTool() {
		return testlinkTool;
	}

	/**
	 * Sets testlink tool.
	 *
	 * @param testlinkTool the testlink tool
	 */
	public void setTestlinkTool(String testlinkTool) {
		this.testlinkTool = testlinkTool;
	}

	/**
	 * Gets test link host name.
	 *
	 * @return the test link host name
	 */
	public String getTestLinkHostName() {
		return testLinkHostName;
	}

	/**
	 * Sets test link host name.
	 *
	 * @param testLinkHostName the test link host name
	 */
	public void setTestLinkHostName(String testLinkHostName) {
		this.testLinkHostName = testLinkHostName;
	}

	/**
	 * Gets testlink api key.
	 *
	 * @return the testlink api key
	 */
	public String getTestlinkAPIKey() {
		return testlinkAPIKey;
	}

	/**
	 * Sets testlink api key.
	 *
	 * @param testlinkAPIKey the testlink api key
	 */
	public void setTestlinkAPIKey(String testlinkAPIKey) {
		this.testlinkAPIKey = testlinkAPIKey;
	}

	/**
	 * Gets testlink project name.
	 *
	 * @return the testlink project name
	 */
	public String getTestlinkProjectName() {
		return testlinkProjectName;
	}

	/**
	 * Sets testlink project name.
	 *
	 * @param testlinkProjectName the testlink project name
	 */
	public void setTestlinkProjectName(String testlinkProjectName) {
		this.testlinkProjectName = testlinkProjectName;
	}

	/**
	 * Gets testlink plan name.
	 *
	 * @return the testlink plan name
	 */
	public String getTestlinkPlanName() {
		return testlinkPlanName;
	}

	/**
	 * Sets testlink plan name.
	 *
	 * @param testlinkPlanName the testlink plan name
	 */
	public void setTestlinkPlanName(String testlinkPlanName) {
		this.testlinkPlanName = testlinkPlanName;
	}

	/**
	 * Gets bug tool name.
	 *
	 * @return the bug tool name
	 */
	public String getBugToolName() {
		return bugToolName;
	}

	/**
	 * Sets bug tool name.
	 *
	 * @param bugToolName the bug tool name
	 */
	public void setBugToolName(String bugToolName) {
		this.bugToolName = bugToolName;
	}

	/**
	 * Gets tool.
	 *
	 * @return the tool
	 */
	public String getbugTool() {
		return bugTool;
	}

	/**
	 * Sets tool.
	 *
	 * @param bugTool the bug tool
	 */
	public void setbugTool(String bugTool) {
		this.bugTool = bugTool;
	}

	/**
	 * Gets tool host name.
	 *
	 * @return the tool host name
	 */
	public String getbugToolHostName() {
		return bugToolHostName;
	}

	/**
	 * Sets tool host name.
	 *
	 * @param bugToolHostName the bug tool host name
	 */
	public void setbugToolHostName(String bugToolHostName) {
		this.bugToolHostName = bugToolHostName;
	}

	/**
	 * Gets tool user name.
	 *
	 * @return the tool user name
	 */
	public String getbugToolUserName() {
		return bugToolUserName;
	}

	/**
	 * Sets tool user name.
	 *
	 * @param bugToolUserName the bug tool user name
	 */
	public void setbugToolUserName(String bugToolUserName) {
		this.bugToolUserName = bugToolUserName;
	}

	/**
	 * Gets tool password.
	 *
	 * @return the tool password
	 */
	public String getbugToolPassword() {
		return bugToolPassword;
	}

	/**
	 * Sets tool password.
	 *
	 * @param bugToolPassword the bug tool password
	 */
	public void setbugToolPassword(String bugToolPassword) {
		this.bugToolPassword = bugToolPassword;
	}

	/**
	 * Gets tool project name.
	 *
	 * @return the tool project name
	 */
	public String getbugToolProjectName() {
		return bugToolProjectName;
	}

	/**
	 * Sets tool project name.
	 *
	 * @param bugToolProjectName the bug tool project name
	 */
	public void setbugToolProjectName(String bugToolProjectName) {
		this.bugToolProjectName = bugToolProjectName;
	}

	/**
	 * Gets jira cycle id.
	 *
	 * @return the jira cycle id
	 */
	public String getJiraCycleID() {
		return jiraCycleID;
	}

	/**
	 * Sets jira cycle id.
	 *
	 * @param jiraCycleID the jira cycle id
	 */
	public void setJiraCycleID(String jiraCycleID) {
		this.jiraCycleID = jiraCycleID;
	}

	/**
	 * Gets jira project id.
	 *
	 * @return the jira project id
	 */
	public String getJiraProjectID() {
		return jiraProjectID;
	}

	/**
	 * Sets jira project id.
	 *
	 * @param jiraProjectID the jira project id
	 */
	public void setJiraProjectID(String jiraProjectID) {
		this.jiraProjectID = jiraProjectID;
	}

	/**
	 * Gets jira test management.
	 *
	 * @return the jira test management
	 */
	public String getJiraTestManagement() {
		return jiraTestManagement;
	}

	/**
	 * Sets jira test management.
	 *
	 * @param jiraTestManagement the jira test management
	 */
	public void setJiraTestManagement(String jiraTestManagement) {
		this.jiraTestManagement = jiraTestManagement;
	}

	/**
	 * Gets manage tool name.
	 *
	 * @return the manage tool name
	 */
	public String getManageToolName() {
		return ManageToolName;
	}

	/**
	 * Sets manage tool name.
	 *
	 * @param manageToolName the manage tool name
	 */
	public void setManageToolName(String manageToolName) {
		this.ManageToolName = manageToolName;
	}

	/**
	 * Gets rest url.
	 *
	 * @return the rest url
	 */
	public String getRestURL() {
		return restURL;
	}

	/**
	 * Sets rest url.
	 *
	 * @param restURL the rest url
	 */
	public void setRestURL(String restURL) {
		this.restURL = restURL;
	}

	/**
	 * Gets android version.
	 *
	 * @return the android version
	 */
	public Double getAndroidVersion() {
		return androidVersion;
	}

	/**
	 * Sets android version.
	 *
	 * @param androidVersion the android version
	 */
	public void setAndroidVersion(Double androidVersion) {
		this.androidVersion = androidVersion;
	}

	/**
	 * Gets android name.
	 *
	 * @return the android name
	 */
	public String getAndroidName() {
		return androidName;
	}

	/**
	 * Sets android name.
	 *
	 * @param androidName the android name
	 */
	public void setAndroidName(String androidName) {
		this.androidName = androidName;
	}

	/**
	 * Gets android id.
	 *
	 * @return the android id
	 */
	public String getAndroidID() {
		return androidID;
	}

	/**
	 * Sets android id.
	 *
	 * @param androidID the android id
	 */
	public void setAndroidID(String androidID) {
		this.androidID = androidID;
	}

	/**
	 * Gets android browser.
	 *
	 * @return the android browser
	 */
	public String getAndroidBrowser() {
		return androidBrowser;
	}

	/**
	 * Sets android browser.
	 *
	 * @param androidBrowser the android browser
	 */
	public void setAndroidBrowser(String androidBrowser) {
		this.androidBrowser = androidBrowser;
	}

	/**
	 * Gets rest access token.
	 *
	 * @return the rest access token
	 */
	public String getRestAccessToken() {
		return restAccessToken;
	}

	/**
	 * Sets rest access token.
	 *
	 * @param restAccessToken the rest access token
	 */
	public void setRestAccessToken(String restAccessToken) {
		this.restAccessToken = restAccessToken;
	}
}
