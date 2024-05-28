package mobileutil;

import java.io.IOException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import step_definitions.RunCukesTest;
import utilities.LogUtil;

public class MultipleDeviceAmazonMobilefunc extends RunCukesTest {

	public static AndroidDriver<MobileElement> Mdriver;

	@SuppressWarnings("unchecked")
	public static <AndroidElement> void skipSignIn(AndroidDriver<MobileElement> Mdriver) throws InterruptedException {
		Thread.sleep(5000);
		LogUtil.infoLog(MultipleDeviceAmazonMobilefunc.class, "In SKip Signin");
		AndroidElement searchElement = (AndroidElement) new WebDriverWait(Mdriver, 30)
				.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
		((RemoteWebElement) searchElement).click();
		AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(Mdriver, 30)
				.until(ExpectedConditions.elementToBeClickable(MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
		((RemoteWebElement) insertTextElement).sendKeys("BrowserStack");
		Thread.sleep(5000);
		java.util.List<MobileElement> allProductsName = Mdriver.findElementsByClassName("android.widget.TextView");
		assert (allProductsName.size() > 0);
	}

	public static void clickShopByCategory() {
		LogUtil.infoLog(MultipleDeviceAmazonMobilefunc.class, "In clickShopByCategory");

	}

	public static void clickFirstCategoryExpandButton() throws InterruptedException {
		LogUtil.infoLog(MultipleDeviceAmazonMobilefunc.class, "In clickFirstCategoryExpandButton");
	}

	public static void selectProduct() {
		LogUtil.infoLog(MultipleDeviceAmazonMobilefunc.class, "In selectProduct");
	}

	public static void addProductToCart() throws IOException, InterruptedException {
		LogUtil.infoLog(MultipleDeviceAmazonMobilefunc.class, "In addProductToCart");
	}

	public static void verifyItemAddedToCart() {
		LogUtil.infoLog(MultipleDeviceAmazonMobilefunc.class, "In verifyItemAddedToCart");
	}
}
