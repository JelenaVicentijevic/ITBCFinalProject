package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.HomePage;
import objects.Menu;
import objects.SummerDresses;

public class TestSummerDresses {
	private static WebDriver wd;

	@BeforeClass // setProperty, create Driver
	public void createDriver() {
		System.setProperty("wedriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
	}

	@BeforeMethod // opening homepage, maximize window
	public static void goToHomePage() {
		HomePage.openHomePage(wd);
		wd.manage().window().maximize();
	}

	@AfterClass // close driver
	public void closeDriver() {
		wd.close();
	}

	@Test // add dress from summer dresses to card, choose quantity, color, size then check if added dress is as chosen
	public void addDressToCard() {
		Menu.hoverToMainMenu(wd, "women");
		Menu.submenuClick(wd, "Summer Dresses");
		SummerDresses.clickOnFirstDress(wd);
		SummerDresses.quantity(wd);
		SummerDresses.sizeM(wd);
		SummerDresses.colorBlue(wd);
		SummerDresses.addToCard(wd);
		SummerDresses.goToCard(wd);

		SoftAssert sa = new SoftAssert();

		String actualQuantity = SummerDresses.dressAddedQuantity(wd);
		String expectedQuantity = "2";
		sa.assertEquals(actualQuantity, expectedQuantity);

		String actualColorSize = SummerDresses.dressAddedColorSize(wd);
		String expectedColor = "Blue";
		String expectedSize = "M";

		sa.assertTrue(actualColorSize.contains(expectedColor));
		sa.assertTrue(actualColorSize.contains(expectedSize));

		String actualName = SummerDresses.dressAddedName(wd);
		String expectedName = "Printed Summer Dress";
		sa.assertEquals(actualName, expectedName);

		sa.assertAll();
	}
}
