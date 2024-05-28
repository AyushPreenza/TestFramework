package modules;

import pageobjects.AutomationHomePage;

public class SignoutAction {
	public static void Execute() {
		AutomationHomePage.sign_out.click();
	}
}
