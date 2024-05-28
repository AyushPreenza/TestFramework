package step_definitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import mobileutil.AndriodConstants;
import mobileutil.MobileKeywords;
import mobileutil.RedDoorzConstants;
import mobileutil.RedDoorzHelpers;
import utilities.GlobalUtil;
import utilities.KeywordUtil;

public class RedDoorzTest {

	@Given("^open the RedDoorz app on$")
	public void open_the_RedDoorz_app_on() throws Throwable {
		try {
			KeywordUtil.cucumberTagName = "MobileTestsAmazon";
		} catch (Exception e) {
			GlobalUtil.errorMsg = e.getMessage();
			Assert.fail(e.getMessage());
		}
	}

	@When("^tap on action refrence run$")
	public void tap_on_action_refrence_run() throws Throwable {
		MobileKeywords.click(RedDoorzConstants.btn_skip, AndriodConstants.Common.type_id, "click on skip button");
		MobileKeywords.click(RedDoorzConstants.id_action_refer_earn, AndriodConstants.Common.type_id,
				"click on action refer earn button");
	}

	@When("^tap on login btn$")
	public void tap_on_login_btn() throws Throwable {
		MobileKeywords.click(RedDoorzConstants.id_btn_login, AndriodConstants.Common.type_id, "click on login button");
	}

	@When("^tap on google login btn$")
	public void tap_on_google_login_btn() throws Throwable {
		MobileKeywords.click(RedDoorzConstants.id_btn_google_login, AndriodConstants.Common.type_id,
				"click on google login button");
	}

	@When("^select email$")
	public void select_email() throws Throwable {
		RedDoorzHelpers.clickByText("praveen.crosswinds@gmail.com", "selected email id");
	}

	@When("^tap on search$")
	public void tap_on_search() throws Throwable {
		MobileKeywords.click(RedDoorzConstants.id_search, AndriodConstants.Common.type_id, "click on search");
	}

	@When("^select area$")
	public void select_area() throws Throwable {
		MobileKeywords.click(RedDoorzConstants.id_select_area, AndriodConstants.Common.type_id, "select area");
		RedDoorzHelpers.clickByText("Jakarta, Indonesia", "select area");
	}

	@When("^select all localities$")
	public void select_all_localities() throws Throwable {
		MobileKeywords.click(RedDoorzConstants.id_select_alllocalities, AndriodConstants.Common.type_id,
				"click on all localities");
	}

	@When("^select rest from recommended$")
	public void select_rest_from_recommended() throws Throwable {
		Thread.sleep(5000);
		GlobalUtil.getMDriver().navigate().back();

		MobileKeywords.click(RedDoorzConstants.xpath_select_hotel, AndriodConstants.Common.type_xpath, "select rest");
	}

	@When("^tap on book now$")
	public void tap_on_book_now() throws Throwable {
		Thread.sleep(5000);
		RedDoorzHelpers.clickBookNow();
		RedDoorzHelpers.clickDismisspopup();
	}

	@When("^tap on pay now$")
	public void tap_on_pay_now() throws Throwable {
		Thread.sleep(5000);
		RedDoorzHelpers.clickDismisspopup();
		MobileKeywords.click(RedDoorzConstants.id_pay_now, AndriodConstants.Common.type_id, "click on pay now");
		RedDoorzHelpers.clickDismissBtn();
	}

	@When("^select payment method and confirm payment$")
	public void select_payment_method_and_confirm_payment() throws Throwable {
		RedDoorzHelpers.clickByText("Credit Card", "select gmail id");
		MobileKeywords.click(RedDoorzConstants.id_btn_confirm_payment, AndriodConstants.Common.type_id,
				"click on confirm payment");
	}

	@When("^enter invalid credentials and tap on login$")
	public void enter_invalid_credentials_and_tap_on_login() throws Throwable {
		MobileKeywords.writeInInput(RedDoorzConstants.id_username, AndriodConstants.Common.type_id, "sdfkjf",
				"invalid user name");
		MobileKeywords.writeInInput(RedDoorzConstants.id_password, AndriodConstants.Common.type_id, "234235",
				"invalid password");
		MobileKeywords.click(RedDoorzConstants.id_prelogin, AndriodConstants.Common.type_id, "tap on login");
		MobileKeywords.click(RedDoorzConstants.id_search, AndriodConstants.Common.type_id, "click on search");
	}

}
