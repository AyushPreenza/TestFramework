package step_definitions;

import java.io.FileInputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import mobileutil.MultipleDeviceAmazonMobilefunc;
import utilities.LogUtil;

public class MultipleDevicesStepFile {

	public static String userName = "praveenqa1";
	public static String accessKey = "kSNuvtpRs4dxaj7vy6eT";
	public static AndroidDriver<MobileElement> Mdriver;

	public static XSSFWorkbook wb;
	public static XSSFSheet sheet1;

	public static int row;
	public static String NDevice_Name = null;
	public static String ODevice_Name;

	@Given("^Launch the amazon apk file$")
	public static AndroidDriver<MobileElement> launch_the_amazon_apk_file() throws Throwable {
		FileInputStream fis = new FileInputStream("D:\\TestData\\AutomationControlSheet.xlsx");
		wb = new XSSFWorkbook(fis);
		sheet1 = wb.getSheet("AndroidList");
		int rowCount = sheet1.getLastRowNum();
		for (int row = 1; row <= rowCount; row++) {

			String ExecuteFlag = sheet1.getRow(row).getCell(4).getStringCellValue();
			if (ExecuteFlag.equals("Yes")) {
				ODevice_Name = sheet1.getRow(row).getCell(1).getStringCellValue();

				DataFormatter formatter = new DataFormatter();
				String Device_Version = formatter.formatCellValue(sheet1.getRow(row).getCell(2));
				LogUtil.infoLog(MultipleDevicesStepFile.class, ODevice_Name);
				LogUtil.infoLog(MultipleDevicesStepFile.class, Device_Version);
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("device", ODevice_Name);
				caps.setCapability("os_version", Device_Version);
				caps.setCapability("name", "Bstack-[Java] Sample Test");
				caps.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");
				;
				Mdriver = new AndroidDriver<MobileElement>(
						new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);
				Mdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				click_on_SKIP_SIGN_IN_button();
				NDevice_Name = ODevice_Name;
			}
		}
		return Mdriver;
	}

	@Given("^click on SKIP SIGN IN button$")
	public static void click_on_SKIP_SIGN_IN_button() throws Throwable {
		LogUtil.infoLog(MultipleDevicesStepFile.class, "Old Device Name passed=" + ODevice_Name);
		if (ODevice_Name != NDevice_Name) {
			MultipleDeviceAmazonMobilefunc.skipSignIn(Mdriver);
		}
		Mdriver.quit();

	}

	@When("select for an item by category in the application")
	public void select_for_an_item_by_category_in_the_application() {
		LogUtil.infoLog(MultipleDevicesStepFile.class, "Step not implemented yet.");
	}

	@When("add the item to cart on the screen")
	public void add_the_item_to_cart_on_the_screen() {
		LogUtil.infoLog(MultipleDevicesStepFile.class, "Step not implemented yet.");
	}

	@Then("verify item is added to cart on screen")
	public void verify_item_is_added_to_cart_on_screen() {
		LogUtil.infoLog(MultipleDevicesStepFile.class, "Step not implemented yet.");
	}

}
