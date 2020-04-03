package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Menu {

	private static final String XPATH_WOMEN = "//a[@class='sf-with-ul'][contains(text(),'Women')]";
	private static final String XPATH_DRESSES = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[6]/ul[1]/li[2]/a[1]";
	private static final String XPATH_WSUMMER_DRESSES = "//ul[@class='submenu-container clearfix first-in-line-xs']//ul//li//a[contains(text(),'Summer Dresses')]";
	private static final String XPATH_SUMMER_DRESSES = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[6]/ul[1]/li[2]/ul[1]/li[3]/a[1]";
	private static WebElement menu;

	// method that hover mouse over main menu
	public static void hoverToMainMenu(WebDriver wd, String button) {
		switch (button) {
		case "women":
			menu = wd.findElement(By.xpath(XPATH_WOMEN)); //case if women is hover
			break;
		case "dresses":
			menu = wd.findElement(By.xpath(XPATH_DRESSES)); //case if dresses is hover
			break;
		default:
			break;
		}
		Actions action = new Actions(wd);
		action.moveToElement(menu).build().perform();
	}

	// method that clicks on submenu link
	public static String submenuClick(WebDriver wd, String link) {
		switch (link) {
		case "Summer Dresses":
			menu = wd.findElement(By.xpath(XPATH_WSUMMER_DRESSES)); // case if Summer Dresses from WOMEN menu is clicked
			break;
		case "SUMMER DRESSES":
			menu = wd.findElement(By.xpath(XPATH_SUMMER_DRESSES)); // case if SUMMER DRESSES from DRESSES menu is clicked
			break;
		}
		Actions action = new Actions(wd);
		action.moveToElement(menu).build().perform();
		menu.click();
		return wd.getCurrentUrl(); // returns pageURL opened after link is clicked
	}

}
