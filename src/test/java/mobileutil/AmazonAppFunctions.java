package mobileutil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import step_definitions.RunCukesTest;
import utilities.GlobalUtil;
import utilities.LogUtil;

public class AmazonAppFunctions extends MobileKeywords2 {

	@SuppressWarnings("unchecked")
	public static AndroidDriver<MobileElement> driver1 = (AndroidDriver<MobileElement>) GlobalUtil.getMDriver();

	public static void skipSignIn(String ODevice_Name, String NDevice_Name) {
		if (ODevice_Name != NDevice_Name) {
			executionDelay(12000);
			click(AndriodConstants.Amazon.skip_sign_in_btn, AndriodConstants.Common.type_id,
					"Click on Skip Sign In Button");
		} else {
			executionDelay(5000);
			click(AndriodConstants.Amazon.skip_sign_in_btn, AndriodConstants.Common.type_id,
					"Click on Skip Sign In Button");
		}
	}

	public static void skipSignIn() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.skip_sign_in_btn, AndriodConstants.Common.type_id,
				"Click on Skip Sign In Button");

	}

	public static void clickShopByCategory(String ODevice_Name, String NDevice_Name) {
		if (ODevice_Name != NDevice_Name) {
			click(AndriodConstants.Amazon.shop_by_category_btn, AndriodConstants.Common.type_id,
					"Click on Shop By Category Button");
		} else {
			click(AndriodConstants.Amazon.shop_by_category_btn, AndriodConstants.Common.type_id,
					"Click on Shop By Category Button");
		}
	}

	public static void clickShopByCategory() {

		executionDelay(6000);

		click(AndriodConstants.Amazon.shop_by_category_btn, AndriodConstants.Common.type_id,
				"Click on Shop By Category Button");

	}

	public static void clickFirstCategoryExpandButton(String ODevice_Name, String NDevice_Name) {
		if (ODevice_Name != NDevice_Name) {
			executionDelay(12000);
			List<MobileElement> ListElemnts = GlobalUtil.mdriver.findElements(MobileBy.xpath("//android.view.View"));
			for (int i = 1; i <= ListElemnts.size(); i++) {
				if (ListElemnts.get(i).getText().contains("Echo & Alexa")) {
					LogUtil.infoLog(AmazonAppFunctions.class, "Gootcha-Echo & Alexa");
					ListElemnts.get(i).click();
					RunCukesTest.logger.log(LogStatus.PASS,
							"Click on the product to be selected" + "<-ExecutedInDevice:->" + ODevice_Name);
					break;
				}
			}
		} else {
			executionDelay(12000);
			click(AndriodConstants.Amazon.first_category_expand_btn, AndriodConstants.Common.type_xpath,
					"Click on the product to be selected");
		}
	}

	public static void clickFirstCategoryExpandButton() {
		executionDelay(12000);
		try {
			List<MobileElement> ListElemnts = GlobalUtil.mdriver.findElements(MobileBy.xpath("//android.view.View"));

			for (int i = 1; i <= ListElemnts.size(); i++) {
				LogUtil.infoLog(AmazonAppFunctions.class, "Element=" + i + "=" + ListElemnts.get(i).getText());
				if (ListElemnts.get(i).getText().contains("Echo & Alexa")) {
					LogUtil.infoLog(AmazonAppFunctions.class, "Gootcha-Echo & Alexa");
					ListElemnts.get(i).click();
					RunCukesTest.logger.log(LogStatus.PASS, "First Category option selected");
					break;
				}
			}
		} catch (Exception e) {
			AmazonAppFunctions.click(AndriodConstants.Amazon.first_category_expand_btn,
					AndriodConstants.Common.type_xpath, "First Category option selected");
		}
	}

	public static void clickSecondCategoryExpandButton() {
		executionDelay(12000);
		try {
			List<MobileElement> ListElemnts = GlobalUtil.mdriver.findElements(MobileBy.xpath("//android.view.View"));

			for (int i = 1; i <= ListElemnts.size(); i++) {
				LogUtil.infoLog(AmazonAppFunctions.class, "Element=" + i + "=" + ListElemnts.get(i).getText());
				if (ListElemnts.get(i).getText().contains("Fire TV")) {
					LogUtil.infoLog(AmazonAppFunctions.class, "Gootcha-Fire TV");
					ListElemnts.get(i).click();
					RunCukesTest.logger.log(LogStatus.PASS, "Second Category option selected");
					break;
				}
			}
		} catch (Exception e) {
			AmazonAppFunctions.click(AndriodConstants.Amazon.second_category_expand_btn,
					AndriodConstants.Common.type_xpath, "Second Category option selected");
		}
	}

	public static void selectProduct(String ODevice_Name, String NDevice_Name)
			throws IOException, InterruptedException, TimeoutException {
		if (ODevice_Name != NDevice_Name) {
			executionDelay(1000);
			List<MobileElement> ListElemnts = GlobalUtil.mdriver.findElements(MobileBy.xpath("//android.view.View"));
			for (int i = 1; i <= ListElemnts.size(); i++) {
				if (ListElemnts.get(i).getText().contains("Echo Plus")) {
					LogUtil.infoLog(AmazonAppFunctions.class, "Gootcha-Echo Plus");
					ListElemnts.get(i).click();
					RunCukesTest.logger.log(LogStatus.PASS,
							"Click on the product to be selected" + "<-ExecutedInDevice:->" + ODevice_Name);
					break;
				}
			}
			executionDelay(5000);
			MobileKeywords.scrollDown();

		} else {
			executionDelay(1000);
			click(AndriodConstants.Amazon.select_product_btn, AndriodConstants.Common.type_xpath,
					"Click on the product to be selected");
			executionDelay(5000);
			MobileKeywords.scrollDown();
		}
	}

	public static void selectProduct() {
		executionDelay(12000);
		try {
			List<MobileElement> ListElemnts = GlobalUtil.mdriver.findElements(MobileBy.xpath("//android.view.View"));
			for (int i = 1; i <= ListElemnts.size(); i++) {
				LogUtil.infoLog(AmazonAppFunctions.class, "Element=" + i + "=" + ListElemnts.get(i).getText());
				if (ListElemnts.get(i).getText().contains("Echo Studio")) {
					LogUtil.infoLog(AmazonAppFunctions.class, "Gootcha-Echo Studio");
					ListElemnts.get(i).click();
					break;
				}
			}
		} catch (Exception e) {
			click(AndriodConstants.Amazon.select_product_btn, AndriodConstants.Common.type_xpath,
					"Click on the product to be selected");
		}
		RunCukesTest.logger.log(LogStatus.PASS, "Click on the product to be selected");

	}

	public static void selectsecondProduct() {
		executionDelay(12000);
		try {
			List<MobileElement> ListElemnts = GlobalUtil.mdriver.findElements(MobileBy.xpath("//android.view.View"));
			for (int i = 1; i <= ListElemnts.size(); i++) {
				LogUtil.infoLog(AmazonAppFunctions.class, "Element=" + i + "=" + ListElemnts.get(i).getText());
				if (ListElemnts.get(i).getText().contains("All-new Fire TV Stick (3rd Gen)")) {
					LogUtil.infoLog(AmazonAppFunctions.class, "Gootch-All-new Fire TV Stick (3rd Gen)");
					ListElemnts.get(i).click();
					break;
				}
			}
		} catch (Exception e) {
			click(AndriodConstants.Amazon.select_product_btn, AndriodConstants.Common.type_xpath,
					"Click on the product to be selected");
		}
		RunCukesTest.logger.log(LogStatus.PASS, "Click on the product to be selected");
	}

	public static void addProductToCart(String ODevice_Name, String NDevice_Name)
			throws IOException, InterruptedException {

		if (ODevice_Name != NDevice_Name) {
			executionDelay(3000);
			click(AndriodConstants.Amazon.add_to_cart_btn, AndriodConstants.Common.type_xpath,
					"Click on Add to Cart button" + "<-ExecutedInDevice:->" + ODevice_Name);
		} else {
			executionDelay(3000);
			click(AndriodConstants.Amazon.add_to_cart_btn, AndriodConstants.Common.type_xpath,
					"Click on Add to Cart button");
		}
	}

	public static void ads() {
		WebElement a = GlobalUtil.mdriver.findElement(By.id("in.amazon.mShop.android.shopping:id/deny_access_button"));

		if (a.isDisplayed()) {
			a.click();
		}
	}

	public static void addProductToCart() throws TimeoutException, InterruptedException, IOException {
		executionDelay(2000);

		while (4 > 1) {
			try {

				MobileKeywords2.click(AndriodConstants.Amazon.add_to_cart_btn2, AndriodConstants.Common.type_xpath);
				LogUtil.infoLog(AmazonAppFunctions.class, "Product added into cart");
				break;

			} catch (Exception e) {
				MobileKeywords2.scrollscroll();

			}

		}

	}

	public static void verifyItemAddedToCart(String ODevice_Name, String NDevice_Name) {
		if (ODevice_Name != NDevice_Name) {
			executionDelay(1000);
			click(AndriodConstants.Amazon.checkout_cart_img, AndriodConstants.Common.type_id,
					"Verify Product is added to Cart" + "<-ExecutedInDevice:->" + ODevice_Name);

		} else {
			executionDelay(3000);
			click(AndriodConstants.Amazon.checkout_cart_img, AndriodConstants.Common.type_id,
					"Verify Product is added to Cart");
		}
		GlobalUtil.mdriver.quit();
	}

	public static void verifyItemAddedToCart() {
		executionDelay(6000);

		WebElement Cart = GlobalUtil.mdriver.findElementByXPath("//android.widget.ImageView[@content-desc='Cart']");
		Cart.click();
		LogUtil.infoLog(AmazonAppFunctions.class, "Clicked on Cart");
		executionDelay(4000);
	}

	public static void verifyItemincart() {
		executionDelay(3000);
		WebElement Hindi_Cart = GlobalUtil.mdriver
				.findElementByXPath("//android.widget.ImageView[@content-desc='कार्ट']");
		executionDelay(3000);
		Hindi_Cart.click();
	}

	public static void loginToAmazonApp(String username, String ODevice_Name, String NDevice_Name) {
		if (ODevice_Name != NDevice_Name) {

			GlobalUtil.mdriver.closeApp();
			executionDelay(1000);
			GlobalUtil.mdriver.launchApp();
			executionDelay(1000);

			List<MobileElement> ListElemnts = GlobalUtil.mdriver
					.findElements(MobileBy.xpath("//android.widget.Button"));
			for (int i = 0; i <= ListElemnts.size(); i++) {
				LogUtil.infoLog(AmazonAppFunctions.class, "Element=" + i + "=" + ListElemnts.get(i).getText());
				if (ListElemnts.get(i).getText().contains("Sign in")) {
					LogUtil.infoLog(AmazonAppFunctions.class, "Gootcha-Sign In");
					ListElemnts.get(i).click();
					RunCukesTest.logger.log(LogStatus.PASS,
							"Click on Sign In Button" + "<-ExecutedInDevice:->" + ODevice_Name);
					break;
				}
			}

			executionDelay(1000);

			try {
				MobileElement element = (MobileElement) GlobalUtil.mdriver
						.findElements(By.className("android.widget.Button")).get(0);
				element.sendKeys("WrongUsername");
				executionDelay(1000);
			} catch (Exception e) {

				List<MobileElement> ListElemnts1 = GlobalUtil.mdriver
						.findElements(MobileBy.xpath("//android.widget.EditText"));
				for (int i = 0; i <= ListElemnts1.size(); i++) {
					LogUtil.infoLog(AmazonAppFunctions.class, "Element=" + i + "=" + ListElemnts1.get(i).getText());
					if (ListElemnts1.get(i).getText().contains("ap_email_login")) {
						LogUtil.infoLog(AmazonAppFunctions.class, "Gootcha-ap_email_login");
						ListElemnts1.get(i).sendKeys("Wrong UserName");
						RunCukesTest.logger.log(LogStatus.PASS,
								"Enter UserName" + "<-ExecutedInDevice:->" + ODevice_Name);
						break;
					}
				}

			}
			executionDelay(5000);
			List<MobileElement> ListElemnts11 = GlobalUtil.mdriver
					.findElements(MobileBy.xpath("//android.widget.Button"));
			for (int i = 0; i <= ListElemnts11.size(); i++) {
				LogUtil.infoLog(AmazonAppFunctions.class, "Element=" + i + "=" + ListElemnts11.get(i).getText());
				if (ListElemnts11.get(i).getText().contains("Continue")) {
					LogUtil.infoLog(AmazonAppFunctions.class, "Gootcha-Continue");
					ListElemnts11.get(i).click();
					RunCukesTest.logger.log(LogStatus.PASS,
							"Click Continue Button" + "<-ExecutedInDevice:->" + ODevice_Name);
					break;
				}
			}

		} else {
			executionDelay(1000);
			click(AndriodConstants.Amazon.sign_In_btn, AndriodConstants.Common.type_id, "Click on Sign In Button");
			executionDelay(1000);
			writeInInput(AndriodConstants.Amazon.email_Login_txtbx, AndriodConstants.Common.type_id, username,
					"Enter Invalid Username");
			executionDelay(5000);
			click(AndriodConstants.Amazon.continue_btn, AndriodConstants.Common.type_id, "Click on Continue Button");
		}
	}

	@SuppressWarnings("rawtypes")
	public static void Searchingproduct() throws InterruptedException {
		executionDelay(5000);
		GlobalUtil.mdriver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text")).click();

		executionDelay(5000);
		GlobalUtil.mdriver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text"))
				.sendKeys("Cello Pinpoint Ball Pen (Pack of 10 pens - Blue)" + "\n");
		LogUtil.infoLog(AmazonAppFunctions.class, "Searching the given product");
		((AndroidDriver) GlobalUtil.mdriver).pressKey(new KeyEvent(AndroidKey.ENTER));

		executionDelay(5000);
		GlobalUtil.mdriver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_results_image")).click();

	}

	@SuppressWarnings({ "rawtypes" })
	public static void Searchproduct() throws InterruptedException {
		executionDelay(5000);
		GlobalUtil.mdriver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text")).click();

		executionDelay(5000);
		GlobalUtil.mdriver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text"))
				.sendKeys("Cello Pinpoint Ball Pen (Pack of 10 pens - Blue)" + "\n");
		LogUtil.infoLog(AmazonAppFunctions.class, "Searching the given product");
		((AndroidDriver) GlobalUtil.mdriver).pressKey(new KeyEvent(AndroidKey.ENTER));

		GlobalUtil.mdriver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_results_image")).click();

		if (GlobalUtil.mdriver.findElement(By.id("in.amazon.mShop.android.shopping:id/loc_ux_gps_enter_pincode"))
				.isDisplayed()) {
			GlobalUtil.mdriver.findElement(By.id("in.amazon.mShop.android.shopping:id/loc_ux_gps_enter_pincode"))
					.click();

			WebElement pincode = GlobalUtil.mdriver
					.findElement(By.id("in.amazon.mShop.android.shopping:id/loc_ux_pin_code_text_pt1"));

			pincode.sendKeys("500025");
			LogUtil.infoLog(AmazonAppFunctions.class, "Entered Pincode");
			WebElement apply2 = GlobalUtil.mdriver.findElementByXPath("//android.widget.Button[@text='Apply']");
			apply2.click();
			LogUtil.infoLog(AmazonAppFunctions.class, "After Entering Pincode, Tap on Apply button");
		}
	}

	public static void Enterpincode() {
		executionDelay(5000);

		WebElement pincode = GlobalUtil.mdriver
				.findElement(By.id("in.amazon.mShop.android.shopping:id/loc_ux_gps_enter_pincode"));
		pincode.click();
		executionDelay(5000);

		WebElement Enterpincode = GlobalUtil.mdriver
				.findElement(By.id("in.amazon.mShop.android.shopping:id/loc_ux_pin_code_text_pt1"));
		Enterpincode.sendKeys("500025");
		LogUtil.infoLog(AmazonAppFunctions.class, "Entered Pincode");
		executionDelay(5000);
		WebElement apply3 = GlobalUtil.mdriver.findElementByXPath("//android.widget.Button[@text='लागू करें']");
		executionDelay(5000);

		apply3.click();
	}

	public static void clickPreference() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.preference, AndriodConstants.Common.type_xpath, "Click on Prefernce Button");

	}

	public static void clickPreferenceDependency() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.preferenceDependency, AndriodConstants.Common.type_xpath,
				"Click on Prefernce Dependency Button");

	}

	public static void enableWifiCheckbox() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.wifiCheckbox, AndriodConstants.Common.type_id, "Click on Wifi Checkbox");
		executionDelay(2000);
		click(AndriodConstants.Amazon.wifiSettings, AndriodConstants.Common.type_xpath, "Click on Wifi Settings");
		executionDelay(2000);
		writeInInput(AndriodConstants.Amazon.wifiSettingsInput, AndriodConstants.Common.type_id, "Wifi switched on",
				"Wifi settings entered");
		executionDelay(2000);
		click(AndriodConstants.Amazon.okButton, AndriodConstants.Common.type_id, "Click on Ok button");
	}

	public static void verify_wifi_settings() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.wifiSettings, AndriodConstants.Common.type_xpath, "Click on Wifi Settings");
		executionDelay(2000);
		Assert.assertEquals(GetTextOfElement(By.id("android:id/edit")), "Wifi switched on");
		RunCukesTest.logger.log(LogStatus.PASS, "Verify wifi settings is saved correctly");

	}

	public static void Breadcrumb() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.BreadcrumbIcon, AndriodConstants.Common.type_xpath, "Click on Breadcrumb icon");
	}

	public static void changeLanguage() {
		executionDelay(5000);
		click(AndriodConstants.Amazon.Language, AndriodConstants.Common.type_xpath, "Click on Language option");

		executionDelay(6000);

		click(AndriodConstants.Amazon.HindiRadiobutton, AndriodConstants.Common.type_xpath,
				"Click on Hindi radio button");

		executionDelay(5000);

		click(AndriodConstants.Amazon.HindiButton, AndriodConstants.Common.type_xpath, "Click on Hindi button");
	}
}
