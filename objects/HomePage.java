package objects;

import org.openqa.selenium.WebDriver;

public class HomePage {

	public static final String HOME_URL = "http://automationpractice.com/index.php";
	
	// Method for opening homepage
	public static void openHomePage(WebDriver wd) {
		wd.get(HOME_URL);
	}

	// Method to navigate to homepage from another page
	public static void navigateToHomePage(WebDriver wd) {
		wd.navigate().to(HOME_URL);
	}

	
}
