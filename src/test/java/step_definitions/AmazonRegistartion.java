package step_definitions;

import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import AmazonPages.AccountCreationPage;
import AmazonPages.HomePage;
import AmazonPages.LoginPage;
import AmazonPages.ProductDetailPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ExcelDataUtil;
import utilities.GlobalUtil;
import utilities.KeywordUtil;
import utilities.LogUtil;

public class AmazonRegistartion extends KeywordUtil {

	@SuppressWarnings("rawtypes")
	static Class thisClass = AmazonRegistartion.class;
	static String testCaseID = thisClass.getSimpleName();

	static String logStep;
	public WebDriver driver;
	public static HashMap<String, String> dataMap = new HashMap<String, String>();

	String email = "testing" + KeywordUtil.getCurrentDateTime() + "@testing.com";

	@Given("^Read the test data  \"([^\"]*)\" from Excel file$")
	public void read_the_test_data_from_Excel_file(String arg1) {
		try {
			KeywordUtil.cucumberTagName = "Web";
			dataMap = ExcelDataUtil.getTestDataWithTestCaseID("Amazon", arg1);
		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

	@When("^Navigate to the url$")
	public void navigate_to_the_url() {
		try {
			navigateToUrl(dataMap.get("URL"));
			Thread.sleep(5000);
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.postMessage({ type: 'START' }, '*');");
		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^Click on Account and Lists$")
	public void click_on_Account_and_Lists() {
		try {
			KeywordUtil.click(HomePage.accountList, "Click on Account and list");
			KeywordUtil.click(HomePage.continueButton, "click ContinueButton");

		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^Click on create your Amazon Account$")
	public void click_on_create_your_Amazon_Account() {
		try {
			KeywordUtil.click(LoginPage.createAccount, "Click on create Account");
		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^Fill in the mandatory fields$")
	public void fill_in_the_mandatory_fields() {
		try {
			modules.AmazonRegistration.fillInTheMandatoryFields(dataMap);
		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^Click on create your account$")
	public void click_on_create_your_account() {
		try {
			KeywordUtil.click(AccountCreationPage.createButton, "Click on create Button");
		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@Then("^Verify account created successfully$")
	public void verify_account_created_successfully() {
		try {
			LogUtil.infoLog(AmazonRegistartion.class,
					GlobalUtil.getDriver().findElement(HomePage.verifyAccount).getText());
			if (GlobalUtil.getDriver().findElement(HomePage.verifyAccount).getText().contains(dataMap.get("Username")))
				LogUtil.infoLog(AmazonRegistartion.class, "Account created successfully");

		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^Enter the login credentials$")
	public void enter_the_login_credentials() {

		try {
			modules.AmazonRegistration.login(dataMap);

		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}

	}

	@When("^Search for an item$")
	public void search_for_an_item() {
		try {

			modules.AmazonRegistration.searchForAnItem(dataMap);

		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^Add the item into cart$")
	public void add_the_item_into_cart() {
		try {

			KeywordUtil.switchToWindow();
			boolean xx = KeywordUtil.isWebElementPresent(ProductDetailPage.addToCart, "Click on Add to Cart");

			if (xx)
				KeywordUtil.clickCart(ProductDetailPage.addToCart, "Click on Add to Cart");
			else
				KeywordUtil.clickCart(ProductDetailPage.addToCart1, "Click on Add to Cart");

		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@Then("^Verify item is added to cart$")
	public void verify_item_is_added_to_cart() {
		try {
			KeywordUtil.click(ProductDetailPage.checkCart, "Click  on Cart");
		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@Then("^Navigate to home Page$")
	public void navigate_to_home_Page() {
		try {
			KeywordUtil.click(ProductDetailPage.homePageLink, "Click on home page");

		} catch (Throwable e) {
			GlobalUtil.e = e;
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

}
