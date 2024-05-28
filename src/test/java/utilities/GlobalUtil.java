package utilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import jirautil.JiraUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * This class will get date and time and it will rename the file with date and
 * time
 */
public class GlobalUtil {

	private static CommonSettings commonSettings = new CommonSettings();
	private static WebDriver driver = null;
	/**
	 * The Mdriver.
	 */
	public static AndroidDriver<MobileElement> mdriver;
	private static int totalSuites = 0;
	private static boolean suitesRunStarted = false;
	private static int lastRunId = 0;
	private static Exception testException;
	private static String currentBrowser;
	private static String currentSuiteName;
	private static String currentUserEmail;
	private static String currentUserType;
	/**
	 * The Current user first name.
	 */
	static String currentUserFirstName;
	/**
	 * The Current user last name.
	 */
	static String currentUserLastName;
	/**
	 * The constant currentUserFullName.
	 */
	public static String currentUserFullName;
	/**
	 * The constant propertyCurrentRecord.
	 */
	public static Map<String, String> propertyCurrentRecord = new HashMap<>();
	/**
	 * The constant propertyDeletedRecord.
	 */
	public static HashMap<String, String> propertyDeletedRecord = new HashMap<>();
	/**
	 * The constant updatesScheduleRecord.
	 */
	public static HashMap<String, String> updatesScheduleRecord = new HashMap<>();
	/**
	 * The constant propertyRestoredRecord.
	 */
	public static HashMap<String, String> propertyRestoredRecord = new HashMap<>();
	/**
	 * The constant listOfClients.
	 */
	public static ArrayList<String> listOfClients = new ArrayList<>();
	/**
	 * The constant listOfProperties.
	 */
	public static ArrayList<String> listOfProperties = new ArrayList<>();
	/**
	 * The constant client.
	 */
	public static HashMap<String, String> client = new HashMap<>();
	/**
	 * The Client full name.
	 */
	static String clientFullName = "FullName";
	/**
	 * The Client email.
	 */
	static String clientEmail = "Email";
	/**
	 * The constant result_FolderName.
	 */
	public static String result_FolderName = System.getProperty("user.dir") + "/target/cucumber-html-report";
	/**
	 * The constant testlinkapi.
	 */
	public static TestLinkUtil testlinkapi;
	/**
	 * The constant jiraapi.
	 */
	public static JiraUtil jiraapi;
	/**
	 * The constant errorMsg.
	 */
	public static String errorMsg;
	/**
	 * The constant e.
	 */
	public static Throwable e;

	/**
	 * The constant PROPERTYADDRESSKEY.
	 */
// For HashMap
	public static final String PROPERTYADDRESSKEY = "Address";
	/**
	 * The constant PROPERTYDATETIMEKEY.
	 */
	public static final String PROPERTYDATETIMEKEY = "DateTime";
	/**
	 * The constant PROPERTYCLIENTKEY.
	 */
	public static final String PROPERTYCLIENTKEY = "Client";
	/**
	 * The constant PROPERTYAGENTKEY.
	 */
	public static final String PROPERTYAGENTKEY = "Agent";
	/**
	 * The constant PROPERTYNOTEKEY.
	 */
	public static final String PROPERTYNOTEKEY = "Note";

	/**
	 * The constant popupCurrentData.
	 */
	protected static final HashMap<String, String> popupCurrentData = new HashMap<String, String>();

	/**
	 * Get data and time stamp
	 *
	 * @return the date time
	 */
	public static String getDateTime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateOfExecution = dateFormat.format(date);
		return dateOfExecution;
	}

	/**
	 * Update filename with time stamp
	 */
	public static void renameFile() {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		String timeStamp = dateFormat.format(date);

		try {

			File oldFile = new File(System.getProperty("user.dir") + ConfigReader.getValue("testResultExcelPath"));

			String newFilePath = oldFile.getAbsolutePath().replace(oldFile.getName(), "") + "\\ReportHistory\\"
					+ timeStamp + "-TestResult.xlsx";

			File newFile = new File(newFilePath);

			FileUtils.copyFile(oldFile, newFile);
			LogUtil.infoLog(GlobalUtil.class, "History File created successfully");

		} catch (IOException e) {
			LogUtil.errorLog(GlobalUtil.class, "Exception caught", e);
		}
	}

	// =======================
	/**
	 * @return
	 */

	/**
	 * Gets common settings.
	 *
	 * @return common settings
	 */
	public static CommonSettings getCommonSettings() {
		return commonSettings;
	}

	/**
	 * Sets common settings.
	 *
	 * @param commonSettings the common settings
	 */
	public static void setCommonSettings(CommonSettings commonSettings) {
		GlobalUtil.commonSettings = commonSettings;
	}

	/**
	 * Gets total suites.
	 *
	 * @return total suites
	 */
	public static int getTotalSuites() {
		return totalSuites;
	}

	/**
	 * Sets total suites.
	 *
	 * @param totalSuites the total suites
	 */
	public static void setTotalSuites(int totalSuites) {
		GlobalUtil.totalSuites = totalSuites;
	}

	/**
	 * Is suites run started boolean.
	 *
	 * @return boolean
	 */
	public static boolean isSuitesRunStarted() {
		return suitesRunStarted;
	}

	/**
	 * Sets suites run started.
	 *
	 * @param suitesRunStarted the suites run started
	 */
	public static void setSuitesRunStarted(boolean suitesRunStarted) {
		GlobalUtil.suitesRunStarted = suitesRunStarted;
	}

	/**
	 * Gets last run id.
	 *
	 * @return last run id
	 */
	public static int getLastRunId() {
		return lastRunId;
	}

	/**
	 * Sets last run id.
	 *
	 * @param lastRunId the last run id
	 */
	public static void setLastRunId(int lastRunId) {
		GlobalUtil.lastRunId = lastRunId;
	}

	/**
	 * Gets test exception.
	 *
	 * @return test exception
	 */
	public static Exception getTestException() {
		return testException;
	}

	/**
	 * Sets test exception.
	 *
	 * @param testException the test exception
	 */
	public static void setTestException(Exception testException) {
		GlobalUtil.testException = testException;
	}

	/**
	 * Gets current browser.
	 *
	 * @return the currentBrowser
	 */
	public static String getCurrentBrowser() {
		return currentBrowser;
	}

	/**
	 * Sets current browser.
	 *
	 * @param currentBrowser the currentBrowser to set
	 */
	public static void setCurrentBrowser(String currentBrowser) {
		GlobalUtil.currentBrowser = currentBrowser;
	}

	/**
	 * Gets current suite name.
	 *
	 * @return the currentSuiteName
	 */
	public static String getCurrentSuiteName() {
		return currentSuiteName;
	}

	/**
	 * Sets current suite name.
	 *
	 * @param currentSuiteName the currentSuiteName to set
	 */
	public static void setCurrentSuiteName(String currentSuiteName) {
		GlobalUtil.currentSuiteName = currentSuiteName;
	}

	/**
	 * Gets current user email.
	 *
	 * @return the currentUserEmail
	 */
	public static String getCurrentUserEmail() {
		return currentUserEmail;
	}

	/**
	 * Sets current user email.
	 *
	 * @param currentUserEmail the currentUserEmail to set
	 */
	public static void setCurrentUserEmail(String currentUserEmail) {
		GlobalUtil.currentUserEmail = currentUserEmail;
	}

	/**
	 * Gets current user type.
	 *
	 * @return the currentUserType
	 */
	public static String getCurrentUserType() {
		return currentUserType;
	}

	/**
	 * Sets current user type.
	 *
	 * @param currentUserType the currentUserType to set
	 */
	public static void setCurrentUserType(String currentUserType) {
		GlobalUtil.currentUserType = currentUserType;
	}

	/**
	 * Gets driver.
	 *
	 * @return the driver
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * Gets m driver.
	 *
	 * @return the m driver
	 */
	public static AndroidDriver<?> getMDriver() {
		return mdriver;
	}

	/**
	 * Sets m driver.
	 *
	 * @param Mdriver the mdriver
	 */
	@SuppressWarnings("unchecked")
	public static void setMDriver(AndroidDriver<?> Mdriver) {
		GlobalUtil.mdriver = (AndroidDriver<MobileElement>) Mdriver;
	}

	/**
	 * Sets driver.
	 *
	 * @param driver the driver to set
	 */
	public static void setDriver(WebDriver driver) {
		GlobalUtil.driver = driver;
	}

	/**
	 * Create zip file string.
	 *
	 * @return the string
	 * @throws IOException the io exception
	 */
	public static String createZipFile() throws IOException {
		result_FolderName = result_FolderName.replace("\\", "/");
		String outputFile = result_FolderName + ".zip";
		FileOutputStream fos = new FileOutputStream(outputFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		packCurrentDirectoryContents(result_FolderName, zos);
		zos.closeEntry();
		zos.close();
		fos.close();
		return outputFile;
	}

	/**
	 * Pack current directory contents.
	 *
	 * @param directoryPath the directory path
	 * @param zos           the zos
	 * @throws IOException the io exception
	 */
	public static void packCurrentDirectoryContents(String directoryPath, ZipOutputStream zos) throws IOException {

		for (String dirElement : new File(directoryPath).list()) {

			String dirElementPath = directoryPath + "/" + dirElement;

			if (new File(dirElementPath).isDirectory()) {
				packCurrentDirectoryContents(dirElementPath, zos);

			} else {
				ZipEntry ze = new ZipEntry(dirElementPath.replaceAll(result_FolderName + "/", ""));

				zos.putNextEntry(ze);

				FileInputStream fis = new FileInputStream(dirElementPath);
				byte[] bytesRead = new byte[512];

				int bytesNum;
				while ((bytesNum = fis.read(bytesRead)) > 0) {
					zos.write(bytesRead, 0, bytesNum);
				}

				fis.close();
			}
		}

	}

}
