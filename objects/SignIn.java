package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignIn {
	public static final String SIGN_IN_PAGE = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
	private static final String ID_EMAIL = "email";
	private static final String ID_PASSWORD = "passwd";
	private static final String SIGN_IN_BTN = "SubmitLogin";
	private static final String XPATH_LOGOUT = "//a[@class='logout']";
	private static final String XPATH_ACCOUNT = "//a[@class='account']";
	private static final String XPATH_LOGIN = "//a[@class='login']";
	
	public static void logIn(WebDriver wd, String mail, String password) {
		
		WebElement el = wd.findElement(By.id(ID_EMAIL));
		el.click();
		el.sendKeys(mail);

		el = wd.findElement(By.id(ID_PASSWORD));
		el.click();
		el.sendKeys(password);

		el = wd.findElement(By.name(SIGN_IN_BTN));
		el.click();
	}
	
	// when user is logged in sign out button is visible
		public static String checkSignOutBTN(WebDriver wd) {
			return wd.findElement(By.xpath(XPATH_LOGOUT)).getText();
		}
	
	// when user is logged in account button with user first and last name is visible
		public static String checkAccount(WebDriver wd) {
			return wd.findElement(By.xpath(XPATH_ACCOUNT)).getText();
		}
		
		public static void logOut(WebDriver wd) {
			wd.findElement(By.xpath(XPATH_LOGOUT)).click();
		}
	
		// when user is not logged in sign in button is visible
		public static String checkSignInBTN(WebDriver wd) {
			return wd.findElement(By.xpath(XPATH_LOGIN)).getText();
		}
}
