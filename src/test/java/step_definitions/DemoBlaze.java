package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import utilities.KeywordUtil;

public class DemoBlaze {
    @Given("User launches the DemoBlaze website")
    public void user_launches_the_demoblaze_website() {
        KeywordUtil.navigateToUrl("https://www.demoblaze.com/");
    }

    @Then("Verify logo is visible")
    public void verifyLogoIsVisible() {
        //module.isLogoVisible();
    }
}
