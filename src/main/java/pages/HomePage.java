package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Home page for home page elements
 * @author Teja Oke
 *
 */
public class HomePage extends BasePage{
	
	
	
	public HomePage(WebDriver driver) {
		super(driver);	
		loggerObj = Logger.getLogger(HomePage.class.getName());
		waitForPageLoad();
	}
	
	
	
	
	
}
