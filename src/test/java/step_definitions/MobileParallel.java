package step_definitions;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import mobileutil.AmazonAppFunctions;
import utilities.GlobalUtil;
import utilities.LogUtil;

public class MobileParallel {

	String platformName;
	String platformVersion;
	String deviceName;
	String port;
	String URL;

	@SuppressWarnings("unused")
	private AppiumDriverLocalService service;
	@SuppressWarnings("unused")
	private AppiumServiceBuilder builder;
	@SuppressWarnings("unused")
	private DesiredCapabilities cap;

	@Parameters({ "platformName", "platformVersion", "deviceName", "port", "URL" })
	@Test(priority = 0)
	@Given("^Open the amazon apk file$")
	public void open_the_amazon_apk_file(String platformName, String platformVersion, String deviceName, String port,
			String URL) throws Throwable {
		int portz = Integer.valueOf(port);

		LogUtil.infoLog(MobileParallel.class, "platformName =" + platformName);
		LogUtil.infoLog(MobileParallel.class, "platformVersion =" + platformVersion);
		LogUtil.infoLog(MobileParallel.class, "deviceName =" + deviceName);
		LogUtil.infoLog(MobileParallel.class, "URL =" + URL);
		LogUtil.infoLog(MobileParallel.class, "Port Number =" + portz);

		String URLL = URL + ":" + portz;

		LogUtil.infoLog(MobileParallel.class, "The Final URL is =" + URLL);

		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a" + URL + " -p" + portz);
			Thread.sleep(10000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

		try {
			GlobalUtil.mdriver = new AndroidDriver<MobileElement>(new URL("http://" + URLL + "/wd/hub"), capabilities);
			GlobalUtil.mdriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	@Given("^click on SKIP SIGN in button$")
	public void click_on_SKIP_SIGN_in_button() throws Throwable {

		try {
			AmazonAppFunctions.skipSignIn();
		} catch (Exception e) {
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 2)
	@When("^select item by category in the application$")
	public void select_item_by_category_in_the_application() throws Throwable {
		try {
			AmazonAppFunctions.clickShopByCategory();

		} catch (Exception e) {
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 3)
	@When("^add selected item to cart on the screen$")
	public void add_selected_item_to_cart_on_the_screen() throws Throwable {
		try {
			AmazonAppFunctions.clickFirstCategoryExpandButton();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 4)
	@Then("^verify selected item is added to cart on screen$")
	public void verify_selected_item_is_added_to_cart_on_screen() throws Throwable {
		try {
			AmazonAppFunctions.selectProduct();
			AmazonAppFunctions.addProductToCart();
			AmazonAppFunctions.verifyItemAddedToCart();

			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("taskkill /F /IM node.exe");
				runtime.exec("taskkill /F /IM cmd.exe");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

}
