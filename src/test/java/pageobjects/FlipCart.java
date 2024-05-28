package pageobjects;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.LogUtil;

public class FlipCart {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "E:\\Chromedrive\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.flipkart.com");

		WebElement cancel = driver.findElement(By.xpath("//*[@class='_2KpZ6l _2doB4z']"));
		cancel.click();
		;
		Thread.sleep(1000);
		List<WebElement> TotalTxtboxs = driver.findElements(By.xpath("//*"));

		Iterator<WebElement> itr = TotalTxtboxs.iterator();

		int Txtboxes = 0;
		int radio = 0;
		int anchor = 0;
		int enablelinks = 0;
		int disablelinks = 0;

		while (itr.hasNext()) {
			WebElement element = itr.next();
			if (element.isDisplayed() && element.isEnabled()) {
				String tagname = element.getTagName();
				if (tagname.equalsIgnoreCase("Input")) {
					Txtboxes++;

				} else if (tagname.equalsIgnoreCase("type")) {
					radio++;
				} else if (tagname.equalsIgnoreCase("a")) {

					enablelinks++;
				} else {
					disablelinks++;
				}
			}

		}
		LogUtil.infoLog(FlipCart.class, "Textboxes are " + Txtboxes);
		LogUtil.infoLog(FlipCart.class, "radioButtons are " + radio);
		LogUtil.infoLog(FlipCart.class, "enablelinks are " + enablelinks);
		LogUtil.infoLog(FlipCart.class, "disablelinks are " + disablelinks);

	}

}
