package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import objects.HomePage;
import objects.SummerDresses;

public class TestHomePage {

	private static WebDriver wd;
	private static String url;
	
	@BeforeClass // setProperty, create Driver, expected URL
	public void createDriver() {
		System.setProperty("wedriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		url = "http://automationpractice.com/index.php";
	}
	
	@AfterClass // close driver
	public void closeDriver() {
		wd.close();
	}
	
	@Test (priority = 0) // check opening homepage
	public void testOpeningHomePage() {
		HomePage.openHomePage(wd);
		wd.manage().window().maximize();
		String actualURL = wd.getCurrentUrl();
		String expectedURL = url;
		
		Assert.assertEquals(actualURL, expectedURL);
	}
	
	@Test (priority = 1) // check navigation to homepage from other website page 
	public void testNavigateToHomePage() {
		HomePage.openHomePage(wd);
		wd.manage().window().maximize();
		wd.navigate().to(SummerDresses.SUMMER_DRESSES_URL);
		HomePage.navigateToHomePage(wd);
		String actualURL = wd.getCurrentUrl();
		String expectedURL = url;
		
		Assert.assertEquals(actualURL, expectedURL);
	}
}
