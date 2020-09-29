package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base class for all Pages
 * @author Teja Oke
 *
 */
public class BasePage {
	
	public WebDriver driver;
	public Logger loggerObj = Logger.getLogger(BasePage.class.getName());
	
	
	public BasePage(WebDriver driver) {
		this.driver=driver;			
	}
	
	/**
	 * Prints a message (msg) to both console (eclipse) and log file
	 * 
	 * @param msg
	 */
	public void logInfo(String msg) {
		System.out.println(msg);
		loggerObj.info(msg);
	}
	
	/**
	 * This method waits for page to load and contain URL
	 * @param url bit
	 */
	public void waitForPageURL(String url) {
		logInfo("Waiting for page URL "+url);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(Duration.ofSeconds(30))		
				.pollingEvery(Duration.ofSeconds(5)) 			
				.ignoring(TimeoutException.class);
		wait.until(ExpectedConditions.urlContains(url));	
	}
	
	/**
	 * This method waits for page title to contain titile provided
	 * @param title
	 */
	public void waitForPageTitle(String title) {
		logInfo("Waiting for page title "+title);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(Duration.ofSeconds(30))		
				.pollingEvery(Duration.ofSeconds(5)) 			
				.ignoring(TimeoutException.class);
		wait.until(ExpectedConditions.titleContains(title));	
	}
	
	/**
	 * This method waitd for element to be displayed by given locator
	 * @param locator
	 */
	public void waitForElementDisplayed(By locator) {
		logInfo("Waiting for the element to be displayed for "+locator);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(Duration.ofSeconds(30))		
				.pollingEvery(Duration.ofSeconds(5)) 			
				.ignoring(TimeoutException.class);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));	
	}
	
	/**
	 * This method waits for page to load completely
	 */
	public void waitForPageLoad()  {
		
		logInfo("Waiting for page load to complete");
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until( webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	
	}
	
	/**
	 * This method switches for window wit hprovided url
	 * @param url
	 */
	public void switchToNewWindowWithURL(String url) {
		
		logInfo("Switching to new window containing url "+url);
		
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		
		if(browserTabs.size() >1) {
			for (String handle : browserTabs) {			
				
				logInfo("This page URL is "+getPageURL());
				
				//switch to new tab
				driver.switchTo().window(handle);
				if(getPageURL().contains(url))
					break;
				
			}
			logInfo("Switched to other window");
		}
	}
	
	/**
	 * Get the page title
	 * @return
	 */
	public String getPageTitle()
	{
		logInfo("Getting page title ");
		return driver.getTitle();
	}

	/**
	 * Get the page URL
	 * @return
	 */
	public String getPageURL() {
		logInfo("Getting page URL ");
		return driver.getCurrentUrl();
	}
}
