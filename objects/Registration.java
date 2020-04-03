package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Registration {
	public static final String registrationURL = "http://automationpractice.com/index.php?controller=authentication&multi-shipping=0&display_guest_checkout=0&back=http%3A%2F%2Fautomationpractice.com%2Findex.php%3Fcontroller%3Dorder%26step%3D1%26multi-shipping%3D0";
	
	private static final String XPATH_CHECKOUT = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]";
	private static final String XPATH_MAIL = "//input[@id='email_create']";
	private static final String XPATH_CREATE_ACCOUNT_BTN = "//form[@id='create-account_form']//span[1]";
	private static final String XPATH_FIRSTNAME = "//input[@id='customer_firstname']";
	private static final String XPATH_LASTNAME = "//input[@id='customer_lastname']";
	private static final String XPATH_PASSWORD = "//input[@id='passwd']";
	private static final String XPATH_ADDRESS = "//input[@name='address1']";
	private static final String XPATH_CITY = "//input[@name='city']";
	private static final String STATES = "option";
	private static final String XPATH_POSTALCODE = "//input[@id='postcode']";
	private static final String XPATH_PHONE = "//input[@id='phone_mobile']";
	private static final String XPATH_ALIAS = "//input[@id='alias']";
	private static final String BTN = "submitAccount";
	
	private static WebElement el;
	
	// navigation to registration from card (basket)
	public static String navigateToRegistrationPage(WebDriver wd) {
		el = wd.findElement(By.xpath(XPATH_CHECKOUT));
		el.click();
		return wd.getCurrentUrl();
	}

	// fill in email in the first part of registration
	public static String registrationFillIn(WebDriver wd, String mail) {
		
		el = wd.findElement(By.xpath(XPATH_MAIL));
				el.clear();
		el = wd.findElement(By.xpath(XPATH_MAIL));
		el.sendKeys(mail);
		el = wd.findElement(By.xpath(XPATH_CREATE_ACCOUNT_BTN));
		el.click();
		return wd.getCurrentUrl();
	}

	// first name
	public static void insertFirstName(WebDriver wd, String name) {
		el = wd.findElement(By.xpath(XPATH_FIRSTNAME));
		el.clear();
		el.sendKeys(name);
	}
	
	// last name
	public static void insertLastName(WebDriver wd, String surname) {
		el = wd.findElement(By.xpath(XPATH_LASTNAME));
		el.clear();
		el.sendKeys(surname);
	}
	
	// password
	public static void insertPassword(WebDriver wd, String password) {
		el = wd.findElement(By.xpath(XPATH_PASSWORD));
		el.clear();
		el.sendKeys(password);
	}

	// address
	public static void insertAddress(WebDriver wd, String address) {
		el = wd.findElement(By.xpath(XPATH_ADDRESS));
		el.clear();
		el.sendKeys(address);
	}

	// city
	public static void insertCity(WebDriver wd, String city) {
		el = wd.findElement(By.xpath(XPATH_CITY)); 
		el.clear();
		el.sendKeys(city);
	}

	// state
	public static void choseState(WebDriver wd, String state) {
		List<WebElement> states = wd.findElements(By.tagName(STATES));
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).getText().equals(state)) {
				states.get(i).click();
			}
		}
	}

	// postal code
	public static void insertZipCode(WebDriver wd, String zip) {
		el = wd.findElement(By.xpath(XPATH_POSTALCODE)); 
		el.clear();
		el.sendKeys(zip);
	}

	// country
	public static void choseCountry(WebDriver wd, String country) {
		if (country.equals("United States"))
			wd.findElement(By.xpath("//option[contains(text(),'United States')]")).click();
		else
			wd.findElement(By.xpath("//select[@id='id_country']//option[contains(text(),'-')]")).click();
	}

	// mobile phone number
	public static void insertPhoneNum(WebDriver wd, String number) {
		el = wd.findElement(By.xpath(XPATH_PHONE));
		el.clear();
		el.sendKeys(number);
	}

	// alias address
	public static void insertAlias(WebDriver wd, String alias) {
		el = wd.findElement(By.xpath(XPATH_ALIAS)); 
		el.clear();
		el.sendKeys(alias);
	}
	
	// click on submit button
	public static void submitRegistration(WebDriver wd) {
		wd.findElement(By.id(BTN)).click();
	}

}
