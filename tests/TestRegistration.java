package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.HomePage;
import objects.Menu;
import objects.Registration;
import objects.SignIn;
import objects.SummerDresses;
import utility.ExcelUtils;

public class TestRegistration {

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

	@Test // create one account
	public void createAccount() {
		// go to registration from cart (the first step)
		HomePage.openHomePage(wd);
		wd.manage().window().maximize();
		Menu.hoverToMainMenu(wd, "women");
		Menu.submenuClick(wd, "Summer Dresses");
		SummerDresses.clickOnFirstDress(wd);
		SummerDresses.quantity(wd);
		SummerDresses.sizeM(wd);
		SummerDresses.colorBlue(wd);
		SummerDresses.addToCard(wd);
		SummerDresses.goToCard(wd);

		// check if registration page is as expected
		String actualURL = Registration.navigateToRegistrationPage(wd);
		String expectedURL = Registration.registrationURL;

		SoftAssert sa = new SoftAssert();

		sa.assertEquals(actualURL, expectedURL);

		// go to registration from cart (the second step)

		Registration.registrationFillIn(wd, "mejltest47@gmail.com"); // it is not possible to create user with same email
																	// (if test is rerun with same email it is expected
																	// to fail)

		Registration.insertFirstName(wd, "Jelena");
		Registration.insertLastName(wd, "Vicentijevic");
		Registration.insertPassword(wd, "apple5");
		Registration.insertAddress(wd, "Avenue 25");
		Registration.insertCity(wd, "Phoenix");
		Registration.choseState(wd, "Arizona");
		Registration.insertZipCode(wd, "85003");
		Registration.choseCountry(wd, "United States");
		Registration.insertPhoneNum(wd, "123456789");
		Registration.insertAlias(wd, "Avenue, 85000, 25");
		Registration.submitRegistration(wd);

		// if registration is successful, user is automatically logged in and log out
		// button is visible, user name is visible

		String textBtn = SignIn.checkSignOutBTN(wd);
		String expectedBtnText = "Sign out";
		String firstLastName = SignIn.checkAccount(wd);
		String expectedName = "Jelena Vicentijevic";

		sa.assertEquals(textBtn, expectedBtnText);
		sa.assertEquals(firstLastName, expectedName);

		sa.assertAll();

		SignIn.logOut(wd); // log out

	}

	@Test // create 30 accounts
	public static void createMultipleAccounts() {

		wd.get(Registration.registrationURL); // go to registration page
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		ExcelUtils.setExcell("RandomGeneratedUsers.xlsx");
		ExcelUtils.setWorkSheet(0);
		SoftAssert sa = new SoftAssert();

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {

			wd.navigate().to(Registration.registrationURL); // bilo je get i radilo

			Registration.registrationFillIn(wd, ExcelUtils.getDataAt(i, 0));

			Registration.insertFirstName(wd, ExcelUtils.getDataAt(i, 1));
			Registration.insertLastName(wd, ExcelUtils.getDataAt(i, 2));
			Registration.insertPassword(wd, ExcelUtils.getDataAt(i, 3));
			Registration.insertAddress(wd, ExcelUtils.getDataAt(i, 4));
			Registration.insertCity(wd, ExcelUtils.getDataAt(i, 5));
			Registration.choseState(wd, ExcelUtils.getDataAt(i, 6));
			Registration.insertZipCode(wd, ExcelUtils.getDataAt(i, 7));
			Registration.choseCountry(wd, ExcelUtils.getDataAt(i, 8));
			Registration.insertPhoneNum(wd, ExcelUtils.getDataAt(i, 9));
			Registration.insertAlias(wd, ExcelUtils.getDataAt(i, 10));
			Registration.submitRegistration(wd);

			String textBtn = SignIn.checkSignOutBTN(wd);
			String expectedBtnText = "Sign out";
			String firstLastName = SignIn.checkAccount(wd);
			String expectedName = ExcelUtils.getDataAt(i, 1) + " " + ExcelUtils.getDataAt(i, 2);

			sa.assertEquals(textBtn, expectedBtnText);
			sa.assertEquals(firstLastName, expectedName);

			SignIn.logOut(wd);

		}
		sa.assertAll();
	}
}
