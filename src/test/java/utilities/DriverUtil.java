package utilities;

import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mobileutil.AmazonAppFunctions;
import mobileutil.MobileKeywords;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import step_definitions.RunCukesTest;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * This DriverUtil class refer to browsers, os details, browser versions and
 * will close all browsers
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverUtil {

    /**
     * The constant IE.
     */
    public static final String IE = "IE";
    /**
     * The constant REMOTE.
     */
    public static final String REMOTE = "BrowserStack";
    /**
     * The constant EDGE.
     */
    public static final String EDGE = "edge";
    /**
     * The constant CHROME.
     */
    public static final String CHROME = "Chrome";
    /**
     * The constant FIREFOX.
     */
    public static final String FIREFOX = "Firefox";
    private static final Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
    private static final HashMap<String, String> checkLogin = new HashMap<String, String>();
    /**
     * The constant row.
     */
    public static int row;
    /**
     * The constant NDevice_Name.
     */
    public static String NDevice_Name = null;
    /**
     * The constant ODevice_Name.
     */
    public static String ODevice_Name;
    /**
     * The constant username.
     */
    public static String username;
    /**
     * The constant appium_ip_address.
     */
    public static String appium_ip_address = mobileutil.MobileKeywords.GetValue("appium_ip_address");
    /**
     * The constant appium_port.
     */
    public static String appium_port = mobileutil.MobileKeywords.GetValue("appium_port");
    /**
     * The constant capabilities.
     */
    public static DesiredCapabilities capabilities = new DesiredCapabilities();

    /**
     * The constant wb.
     */
    public static XSSFWorkbook wb;
    /**
     * The constant sheet1.
     */
    public static XSSFSheet sheet1;

    /**
     * Gets mobile app.
     *
     * @param exeEnv the exe env
     * @return the mobile app
     * @throws Exception the exception
     */
    public static AndroidDriver<MobileElement> getMobileApp(String exeEnv) throws Exception {

        String USER_NAME = GlobalUtil.getCommonSettings().getHostName();
        String ACCESS_KEY = GlobalUtil.getCommonSettings().getKey();

        if (exeEnv.equalsIgnoreCase(REMOTE)) {
            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/resources/ExcelFiles/MobileDevicesList.xlsx");
            wb = new XSSFWorkbook(fis);
            sheet1 = wb.getSheet("AndroidList");
            int rowCount = sheet1.getLastRowNum();
            for (int row = 1; row < rowCount; row++) {
                String ExecuteFlag = sheet1.getRow(row).getCell(4).getStringCellValue();
                if (ExecuteFlag.equals("Yes")) {
                    ODevice_Name = sheet1.getRow(row).getCell(1).getStringCellValue();
                    DataFormatter formatter = new DataFormatter();
                    String Device_Version = formatter.formatCellValue(sheet1.getRow(row).getCell(2));
                    LogUtil.infoLog(DriverUtil.class, ODevice_Name);
                    LogUtil.infoLog(DriverUtil.class, Device_Version);
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("device", ODevice_Name);
                    caps.setCapability("os_version", Device_Version);
                    caps.setCapability("autoDismissAlerts", true);
                    caps.setCapability("name", "Bstack-[Java]-Mobile Amazon Test");
                    caps.setCapability("app", "bs://2d814536cd2e82e0da6fc5a0838e546cb9474e03");

                    GlobalUtil.mdriver = new AndroidDriver<MobileElement>(
                            new URL("https://" + USER_NAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub"),
                            caps);
                    GlobalUtil.mdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                    // click_on_SKIP_SIGN_IN_button();
                    AmazonAppFunctions.skipSignIn(ODevice_Name, NDevice_Name);
                    NDevice_Name = ODevice_Name;
                }
            }

            return GlobalUtil.mdriver;
        } else {

            try {
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
                        GlobalUtil.getCommonSettings().getAndroidName());
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                        GlobalUtil.getCommonSettings().getAndroidVersion());
                capabilities.setCapability("platformName", MobileKeywords.GetValue("platformName"));
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
                        MobileKeywords.GetValue("automationName"));
                capabilities.setCapability(MobileCapabilityType.UDID, GlobalUtil.getCommonSettings().getAndroidID());
                capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,
                        MobileKeywords.GetValue("newCommandTimeout"));
                capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
                capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

                GlobalUtil.mdriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),
                        capabilities);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } // else close
        return GlobalUtil.mdriver;

    }

    /**
     * Invoke local mobile app android driver.
     *
     * @param exeEnv        the exe env
     * @param deviceDetails the device details
     * @return the android driver
     */
    public static AndroidDriver<MobileElement> invokeLocalMobileApp(String exeEnv, String deviceDetails) {
        String deviceName = deviceDetails.split("_")[0];
        String osVersion = deviceDetails.split("_")[1];

        LogUtil.infoLog(DriverUtil.class, deviceName);
        LogUtil.infoLog(DriverUtil.class, osVersion);

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobileKeywords.GetValue("platformName"));
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "in.amazon.mShop.android.shopping");
        File apkFile = new File("src/test/resources/APK/Amazon.apk");
        capabilities.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.amazon.mShop.home.HomeActivity");
        try {
            GlobalUtil.mdriver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            LogUtil.infoLog(DriverUtil.class, e.getMessage());
            e.printStackTrace();
        }
        GlobalUtil.mdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        RunCukesTest.logger.log(LogStatus.INFO,
                "<font color=blue>Execution Done By The Device:" + deviceDetails + "</font>");
        return GlobalUtil.mdriver;
    }

    /**
     * Invoke local mobile app 1 android driver.
     *
     * @param exeEnv        the exe env
     * @param deviceDetails the device details
     * @return the android driver
     */
    public static AndroidDriver<MobileElement> invokeLocalMobileApp_1(String exeEnv, String deviceDetails) {

        String deviceName = deviceDetails.split("_")[0];
        String osVersion = deviceDetails.split("_")[1];

        LogUtil.infoLog(DriverUtil.class, deviceName);
        LogUtil.infoLog(DriverUtil.class, osVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobileKeywords.GetValue("platformName"));
        capabilities.setCapability("appPackage", "io.appium.android.apis");
        capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
        try {
            GlobalUtil.mdriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);

        } catch (MalformedURLException e) {
            LogUtil.errorLog(DriverUtil.class, e.getMessage());
            e.printStackTrace();
        }
        GlobalUtil.mdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        RunCukesTest.logger.log(LogStatus.INFO,
                "<font color=blue>Execution Done By The Device:" + deviceDetails + "</font>");
        return GlobalUtil.mdriver;
    }

    /**
     * Invoke browser stack mobile app android driver.
     *
     * @param deviceDetails the device details
     * @return the android driver
     */
    public static AndroidDriver<MobileElement> invokeBrowserStackMobileApp(String deviceDetails) {
        String USER_NAME = GlobalUtil.getCommonSettings().getHostName();
        String ACCESS_KEY = GlobalUtil.getCommonSettings().getKey();
        String deviceName = deviceDetails.split("_")[0];
        String osVersion = deviceDetails.split("_")[1];
        LogUtil.infoLog(DriverUtil.class, deviceName);
        LogUtil.infoLog(DriverUtil.class, osVersion);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("device", deviceName);
        caps.setCapability("os_version", osVersion);
        caps.setCapability("autoDismissAlerts", true);
        caps.setCapability("name", "Bstack-[Java]-Mobile Amazon Test");
        caps.setCapability("app", "bs://722fce94b95ac73576be62be9371ae8e67484784");

        try {
            GlobalUtil.mdriver = new AndroidDriver<MobileElement>(
                    new URL("http://" + USER_NAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        GlobalUtil.mdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return GlobalUtil.mdriver;
    }

    /**
     * Gets browser.
     *
     * @param exeEnv      the exe env
     * @param browserName the browser name
     * @return browser
     */
    @SuppressWarnings("deprecation")
    public static WebDriver getDummyBrowser(String exeEnv, String browserName) {
        WebDriver browser = null;
        try {
            DesiredCapabilities caps;
            String URL = null;

            if (exeEnv.equalsIgnoreCase("Remote")) {

                if (browserName.equalsIgnoreCase(CHROME)) {
                    caps = DesiredCapabilities.chrome();
                    caps.setCapability("os", "WINDOWS");
                    if (GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("WINDOWS")) {
                        caps.setCapability("os_version", GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);
                    }
                    if (GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("MAC")) {

                        caps.setCapability("os", "OS X");
                        caps.setCapability("os_version", GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);
                    }
                } else if (browserName.equalsIgnoreCase(IE)) {
                    caps = DesiredCapabilities.internetExplorer();
                    caps.setCapability("os", "WINDOWS");
                    if (GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("WINDOWS")) {
                        caps.setCapability("os_version", GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);
                    }
                } else if (browserName.equalsIgnoreCase(EDGE)) {
                    caps = DesiredCapabilities.edge();
                    caps.setCapability("os", "WINDOWS");
                    caps.setCapability("version", "14.0");
                    caps.setCapability("os_version", "10");
                } else if (browserName.equalsIgnoreCase("Safari")) {
                    caps = DesiredCapabilities.safari();
                    if (GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("WINDOWS")) {
                        caps.setCapability("os", "OS X");
                        caps.setCapability("os_version", "SIERRA");
                    } else {
                        caps.setCapability("os", "OS X");
                        caps.setCapability("os_version", GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);
                    }
                }

                // firefox
                else {

                    caps = DesiredCapabilities.firefox();
                    caps.setCapability("os", "WINDOWS");
                    if (GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("WINDOWS")) {
                        caps.setCapability("os_version", GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);
                    }
                    if (GlobalUtil.getCommonSettings().getRemoteOS().toUpperCase().contains("MAC")) {
                        caps.setCapability("os", "OS X");
                        caps.setCapability("os_version", GlobalUtil.getCommonSettings().getRemoteOS().split("_")[1]);
                    }
                }

                if (GlobalUtil.getCommonSettings().getCloudProvider().equalsIgnoreCase("BrowserStack")) {
                    caps.setCapability("browserstack.debug", "true");
                    caps.setCapability("browserstack.geoLocation", "US");
                    caps.setCapability("build", GlobalUtil.getCommonSettings().getBuildNumber());
                    URL = "https://" + GlobalUtil.getCommonSettings().getHostName() + ":"
                            + GlobalUtil.getCommonSettings().getKey() + "@hub-cloud.browserstack.com/wd/hub";
                }
                try {
                    browser = new RemoteWebDriver(new URL(URL), caps);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                drivers.put(browserName, browser);

            } else {

                if (browserName.equalsIgnoreCase(CHROME)) {
                    // Write code for chrome
                    browser = drivers.get(browserName);
                    if (browser == null) {
                        WebDriverManager.chromedriver().setup();
                        browser = new ChromeDriver();
                        drivers.put("Chrome", browser);
                        exeEnv = REMOTE;
                    } // End if
                } else if (browserName.equalsIgnoreCase(IE)) {
                    // Write code for IE
                    browser = drivers.get(browserName);
                    if (browser == null) {
                        File ieExecutable = new File(ConfigReader.getValue("IEDriverPath"));
                        System.setProperty("webdriver.ie.driver", ieExecutable.getAbsolutePath());
                        DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
                        capabilitiesIE.setCapability("ie.ensureCleanSession", true);
                        capabilitiesIE.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                        browser = new InternetExplorerDriver(capabilitiesIE);
                        drivers.put("IE", browser);
                        checkLogin.put(browserName, "Y");
                    }
                } else if (browserName.equalsIgnoreCase("Firefox")) {
                    // Getting Firefox Browser
                    browser = drivers.get("Firefox");
                    if (browser == null) {
                        WebDriverManager.firefoxdriver().setup();
                        browser = new FirefoxDriver();
                        drivers.put("Firefox", browser);
                        checkLogin.put(browserName, "Y");
                    }
                }
            }
            browser.manage().window().maximize();
            LogUtil.infoLog(DriverUtil.class,
                    GlobalUtil.getCommonSettings().getBrowser() + " : Browser Launched and Maximized.");
        } catch (Exception e) {
            LogUtil.errorLog(DriverUtil.class, "Browser not launched. Please check the configuration ", e);
            e.printStackTrace();
        }
        return browser;
    }

    public static WebDriver getBrowser(String exeEnv, String browserName) {
        if (exeEnv.equalsIgnoreCase("Window")) {
            if (browserName.equalsIgnoreCase("Chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("load-extension=C:\\Users\\Gurdev\\Downloads\\v5.4 2\\build");
                GlobalUtil.setDriver(new ChromeDriver(options));
            } else if (browserName.equalsIgnoreCase("IE")) {
                WebDriverManager.iedriver().setup();
                GlobalUtil.setDriver(new InternetExplorerDriver());
            } else if (browserName.equalsIgnoreCase("Firefox")) {
                WebDriverManager.firefoxdriver().setup();
                GlobalUtil.setDriver(new FirefoxDriver());

                //For Mac Operating System
            } else if (exeEnv.equalsIgnoreCase("MAC")) {
                if (browserName.equalsIgnoreCase("CHROME")) {
                    WebDriverManager.chromedriver().setup();
                    GlobalUtil.setDriver(new ChromeDriver());
                } else if (browserName.equalsIgnoreCase("IE")) {
                    WebDriverManager.iedriver().setup();
                    GlobalUtil.setDriver(new InternetExplorerDriver());
                } else if (browserName.equalsIgnoreCase("Firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    GlobalUtil.setDriver(new FirefoxDriver());
                } else if (browserName.equalsIgnoreCase("Safari")) {
                    GlobalUtil.setDriver(new SafariDriver());
                }
            }

            GlobalUtil.getDriver().manage().window().maximize();
            LogUtil.infoLog(DriverUtil.class,
                    GlobalUtil.getCommonSettings().getBrowser() + " : Browser Launched and Maximized.");
        }
        return GlobalUtil.getDriver();
    }

    /**
     * will get browser type and version
     *
     * @param browser the browser
     * @param cap     the cap
     * @return browser and version
     */
    public static String getBrowserAndVersion(WebDriver browser, DesiredCapabilities cap) {
        String browserversion;
        String windows = "Windows";
        String browsername = cap.getBrowserName();
        // This block to find out IE Version number
        if ("IE".equalsIgnoreCase(browsername)) {
            String uAgent = (String) ((JavascriptExecutor) browser).executeScript("return navigator.userAgent;");
            LogUtil.infoLog(DriverUtil.class, uAgent);
            // uAgent return as "MSIE 8.0 Windows" for IE8
            if (uAgent.contains("MSIE") && uAgent.contains(windows)) {
                browserversion = uAgent.substring(uAgent.indexOf("MSIE") + 5, uAgent.indexOf(windows) - 2);
            } else if (uAgent.contains("Trident/7.0")) {
                browserversion = "11.0";
            } else {
                browserversion = "0.0";
            }
        } else {
            // Browser version for Firefox and Chrome
            browserversion = cap.getVersion();
            //browserversion = cap.getBrowserVersion();
        }
        String browserversion1 = browserversion.substring(0, browserversion.indexOf('.'));
        return browsername + " " + browserversion1;
    }

    /**
     * will get operating system information
     *
     * @return
     */

    /**
     * close all browsersw
     *
     * @return
     */
    public static void closeAllDriver() {

        drivers.entrySet().forEach(key -> {
            key.getValue().quit();
            key.setValue(null);
        });

        LogUtil.infoLog(DriverUtil.class, "Closing Browsers");
    }

    /**
     * Gets img ref.
     *
     * @param imgFile the img file
     * @return the img ref
     */
    public static String getImgRef(String imgFile) {
        return new DriverUtil().getRefImage(imgFile);
    }

    private String getRefImage(String imgFile) {
        String openCVImgsFolder = "OpenCVImages/";
        URL refImgUrl = getClass().getClassLoader().getResource(openCVImgsFolder + imgFile + ".png");
        File refImgFile;
        try {
            refImgFile = Paths.get(refImgUrl.toURI()).toFile();
            LogUtil.infoLog(DriverUtil.class, "File Found : " + refImgFile.exists());
            return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}