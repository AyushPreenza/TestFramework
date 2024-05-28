package utilities;

import testlink.api.java.client.TestLinkAPIClient;

/**
 * The type Test link util.
 */
public class TestLinkUtil {

    /**
     * The Testlink api client.
     */
    static TestLinkAPIClient testlinkAPIClient;
    /**
     * The constant projectName.
     */
    public static String projectName;
    /**
     * The constant testPlanName.
     */
    public static String testPlanName;
    /**
     * The constant testCaseNameOrVisibleID.
     */
    public static String testCaseNameOrVisibleID;
    /**
     * The constant buildName.
     */
    public static String buildName;
    /**
     * The constant DEV_KEY.
     */
    public static String DEV_KEY;
    /**
     * The constant SERVER_URL.
     */
    public static String SERVER_URL;
    /**
     * The constant needUpdate.
     */
    public static String needUpdate;

    /**
     * Instantiates a new Test link util.
     */
    public TestLinkUtil() {
        testlinkAPIClient = new TestLinkAPIClient(DEV_KEY, SERVER_URL);
    }

    /**
     * Update test link result.
     *
     * @param testCase  the test case
     * @param exception the exception
     * @param result    the result
     */
    public void updateTestLinkResult(String testCase, String exception, String result) {
        try {
            if (testCase == null)
                return;
            if (needUpdate.equalsIgnoreCase("Yes")) {
                testlinkAPIClient.reportTestCaseResult(projectName, testPlanName, testCase, buildName, exception,
                        result);
                LogUtil.infoLog(TestLinkUtil.class, "Updated Results to Testlink");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.errorLog(TestLinkUtil.class, "Not able to update the Testlink. Please check the Exception", e);
        }
    }

}
