package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.HomePage;
import objects.Menu;

public class TestMenu {
	private static WebDriver wd;

	@BeforeClass // setProperty, create Driver
	public void createDriver() {
		System.setProperty("wedriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
	}
	
	@AfterClass // close driver
	public void closeDriver() {
		wd.close();
	}
	
	@BeforeMethod // opening homepage, maximize window
	public static void goToHomePage() {
		HomePage.openHomePage(wd);
		wd.manage().window().maximize();
	}
	
	@Test // hover women, click on Summer Dresses
	public void testHoverWomen() {
		
		Menu.hoverToMainMenu(wd, "women");	
		String actualURL = Menu.submenuClick(wd, "Summer Dresses");
		String expectedURL = "http://automationpractice.com/index.php?id_category=11&controller=category";
		SoftAssert sa = new SoftAssert(); 
		sa.assertEquals(actualURL, expectedURL);
		sa.assertAll();
	}  
	
	@Test // hover dresses, click on SUMMER DRESSES
	public void testHoverDresses() {
		
		Menu.hoverToMainMenu(wd, "dresses");	
		String actualURL = Menu.submenuClick(wd, "SUMMER DRESSES");
		String expectedURL = "http://automationpractice.com/index.php?id_category=11&controller=category";
		Assert.assertEquals(actualURL, expectedURL);
	} 
	
	@Test // check if Summer Dresses from WOMEN URL is equal to SUMMER DRESSES from DRESSES URL
	public void testEqualSummerDressesURL() {
		//wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Menu.hoverToMainMenu(wd, "women");	
		String womenURL = Menu.submenuClick(wd, "Summer Dresses");
		Menu.hoverToMainMenu(wd, "dresses");
		String dressesURL = Menu.submenuClick(wd, "SUMMER DRESSES");
		Assert.assertTrue(womenURL.equals(dressesURL));
	}
}
