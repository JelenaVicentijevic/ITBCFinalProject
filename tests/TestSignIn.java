package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.SignIn;
import utility.ExcelUtils;

public class TestSignIn {

	private static WebDriver wd;

	@BeforeClass // setProperty, create Driver
	public void createDriver() {
		System.setProperty("wedriver.chrome.driver", "chromedriver.exe");
		wd = new ChromeDriver();
		wd.get(SignIn.SIGN_IN_PAGE);
		wd.manage().window().maximize();
	}

	@AfterClass // close driver
	public void closeDriver() {
		wd.close();
	}

	@Test (priority = 0)
	public void logIn() {
		
		SignIn.logIn(wd, "mejltest3@gmail.com", "apple5"); // data from TestRegistration

		String textBtn = SignIn.checkSignOutBTN(wd);
		String expectedBtnText = "Sign out";
		String firstLastName = SignIn.checkAccount(wd);
		String expectedName = "Jelena Vicentijevic"; // data from TestRegistration

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(textBtn, expectedBtnText);
		sa.assertEquals(firstLastName, expectedName);
		sa.assertAll();
	}

	@Test (priority = 1) // precondition - user is logged in
	public void logOut() {
		SignIn.logOut(wd);
		String textBtn = SignIn.checkSignInBTN(wd);
		String expectedBtnText = "Sign in";
		Assert.assertEquals(textBtn, expectedBtnText); // check if logout button and log out method are working
	}
	
	@Test (priority = 2) // log in 30 users
	public void logInMultipleUsers() {
		
		ExcelUtils.setExcell("RandomGeneratedUsers.xlsx");
		ExcelUtils.setWorkSheet(0);
		
		SoftAssert sa = new SoftAssert();
		
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			
			wd.navigate().to(SignIn.SIGN_IN_PAGE);
			
			SignIn.logIn(wd, ExcelUtils.getDataAt(i, 0), ExcelUtils.getDataAt(i, 3));
			
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
