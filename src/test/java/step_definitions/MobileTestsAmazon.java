package step_definitions;

import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mobileutil.AmazonAppFunctions;
import mobileutil.MobileKeywords2;
import utilities.DriverUtil;
import utilities.GlobalUtil;
import utilities.KeywordUtil;

public class MobileTestsAmazon {

    public static String NDevice_Name;
    public static String ODevice_Name;

    @Given("^open the Amazon app on \"([^\"]*)\"$")
    public void open_the_Amazon_app(String deviceDetails) {
        try {
            KeywordUtil.cucumberTagName = "MobileTestsAmazon";
            if (GlobalUtil.getCommonSettings().getExecutionEnv().equalsIgnoreCase("Local"))
                DriverUtil.invokeLocalMobileApp(GlobalUtil.getCommonSettings().getExecutionEnv(), deviceDetails);

            else if (GlobalUtil.getCommonSettings().getExecutionEnv().equalsIgnoreCase("Remote"))
                DriverUtil.invokeBrowserStackMobileApp(deviceDetails);
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @Given("^click on Views option$")
    public void click_on_Views_option() throws Throwable {
        Thread.sleep(10000);
        while (3 > 1) {
            try {
                GlobalUtil.mdriver.findElementsById("//android.widget.TextView").get(12).click();
                break;
            } catch (Exception e) {
                MobileKeywords2.scrollscroll();
            }
        }
    }

    @Given("^click on Controls$")
    public void click_on_Controls() throws Throwable {
        GlobalUtil.mdriver.findElementsById("android:id/text1").get(6).click();
    }

    @Given("^Select darktheme option$")
    public void select_darktheme_option() throws Throwable {
        GlobalUtil.mdriver.findElementsById("android:id/text1").get(1).click();
    }

    @Given("^Enter  text in textbox$")
    public void enter_text_in_textbox() throws Throwable {

    }

    @Given("^select check box$")
    public void select_check_box() throws Throwable {

    }

    @Given("^Radio button$")
    public void radio_button() {
    }

    @Given("^click on skip sign in$")
    public void click_on_skip_sign_in() {
        try {
            AmazonAppFunctions.skipSignIn();
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @When("^select for an item by category$")
    public void search_for_an_item() {
        try {
            AmazonAppFunctions.clickShopByCategory();
            AmazonAppFunctions.clickFirstCategoryExpandButton();
            AmazonAppFunctions.selectProduct();
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }

    }

    @When("^select for an seconditem by category$")
    public void select_for_an_seconditem_by_category() throws Throwable {

        try {
            AmazonAppFunctions.clickShopByCategory();
            AmazonAppFunctions.clickSecondCategoryExpandButton();
            AmazonAppFunctions.selectsecondProduct();
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }

    }

    @When("^add the item to cart$")
    public void add_the_item_to_cart() {
        try {
            AmazonAppFunctions.addProductToCart();
            RunCukesTest.logger.log(LogStatus.PASS, "Selected Product Added to the cart");
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @Then("^verify item is added to cart$")
    public void verify_item_is_added_to_cart() {
        try {
            AmazonAppFunctions.verifyItemAddedToCart();
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @Given("^login to the app with username \"([^\"]*)\"$")
    public void login_to_the_app_with_username_and_password(String username) {
        try {
            AmazonAppFunctions.loginToAmazonApp(username, ODevice_Name, NDevice_Name);
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @Given("^search for product$")
    public void search_for_product() {
        try {
            AmazonAppFunctions.Searchproduct();
            RunCukesTest.logger.log(LogStatus.PASS, "Searching the Product");
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @Given("^searching for product$")
    public void searching_for_product() {
        try {
            AmazonAppFunctions.Searchingproduct();
            RunCukesTest.logger.log(LogStatus.PASS, "Searching the Product");
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @Given("^Enter pincode$")
    public void Enter_pincode() {
        try {
            AmazonAppFunctions.Enterpincode();
            RunCukesTest.logger.log(LogStatus.PASS, "Pincode entered");
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @Given("^add the item to amazoncart$")
    public void add_the_item_to_amazoncart() {
        try {
            AmazonAppFunctions.addProductToCart();
            RunCukesTest.logger.log(LogStatus.PASS, "Selected Product Added to the cart");
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }

    }

    @Then("^verify items are added in to the cart or not$")
    public void verify_items_are_added_to_cart_or_not() throws Throwable {
        try {

            AmazonAppFunctions.verifyItemincart();
            RunCukesTest.logger.log(LogStatus.PASS, "Verified Product is Added to the cart");
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @Then("^verify item is added to cart or not$")
    public void verify_item_is_added_to_cart_or_not() throws Throwable {

        try {

            AmazonAppFunctions.verifyItemAddedToCart();
            RunCukesTest.logger.log(LogStatus.PASS, "Verified Product is Added to the cart");
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @Given("^open the API demo app on device \"([^\"]*)\"$")
    public void open_the_APIdemo_app(String deviceDetails) {
        try {
            KeywordUtil.cucumberTagName = "MobileTestsAmazon";
            if (GlobalUtil.getCommonSettings().getExecutionEnv().equalsIgnoreCase("Local"))
                DriverUtil.invokeLocalMobileApp_1(GlobalUtil.getCommonSettings().getExecutionEnv(), deviceDetails);

            else if (GlobalUtil.getCommonSettings().getExecutionEnv().equalsIgnoreCase("Remote"))
                DriverUtil.invokeBrowserStackMobileApp(deviceDetails);

        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }

    }

    @When("^Click on preferences$")
    public void click_on_preferences() {
        try {
            AmazonAppFunctions.clickPreference();
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on preference dependencies$")
    public void click_on_preference_dependecy() {
        try {
            AmazonAppFunctions.clickPreferenceDependency();
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @And("^Enable wifi checkbox and add wifi settings$")
    public void enable_wifi_and_add_settings() {
        try {
            AmazonAppFunctions.enableWifiCheckbox();
            ;
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @And("^verify wifi settings is saved$")
    public void verify_wifi_settings() {
        try {
            AmazonAppFunctions.verify_wifi_settings();
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on breadcrumb menu$")
    public void click_on_breadcrumb_menu() throws Throwable {
        try {
            AmazonAppFunctions.Breadcrumb();
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }
    }

    @And("^click on Language and change the language$")
    public void click_on_Language_and_change_the_language() throws Throwable {
        try {
            AmazonAppFunctions.changeLanguage();
        } catch (Exception e) {
            GlobalUtil.errorMsg = e.getMessage();
            Assert.fail(e.getMessage());
        }

    }

    @Given("Open the application")
    public void openTheApplication() {
        DriverUtil.invokeLocalMobileApp(GlobalUtil.getCommonSettings().getExecutionEnv(), "emulator-5554_10");
    }
}
