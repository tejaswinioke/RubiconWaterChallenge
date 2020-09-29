package helpers;



import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

/**
 * Helper class to handle WebDriver
 * @author Teja Oke
 *
 */
public class SeleniumDriver {

    private static SeleniumDriver seleniumDriver;
    public static Logger log = Logger.getLogger(SeleniumDriver.class.getName());
   
    private static WebDriver driver;
    
    /**
     * This private constructor initializes webdriver 
     * @param browserType The type of browser for webdriver
     * @param implicitWait the wait time defined in properties for implicit wait
     * @param pageloadWait the wait time defined in properties for page load
     * @param url the url of the test site
     */
    private SeleniumDriver(String browserType,int implicitWait,int pageloadWait, String url) {       
    	log.info("Setting up driver->"+browserType);
    	
    	switch (browserType) {
		case "chrome":
			driver = initChromeDriver();
			break;
		case "firefox":
			driver = initFirefoxDriver();
			break;
		case "internetexplorer":
			driver = initInternetExplorerDriver();
			break;
		case "headless":
			driver=new HtmlUnitDriver(true);
			break;
		default:
			log.info("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver();
		}
    	
    	driver.get(url);
    	log.info("Opening up URL->"+url);
    	
    	// set the driver
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(pageloadWait, TimeUnit.SECONDS);
        
       
    }

    /**
	 * Method to initiate Chrome Driver
	 * @return chrome driver
	 */
	private WebDriver initChromeDriver() {
		log.info("Launching google chrome with new profile..");
		WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
		WebDriver driver = new ChromeDriver();		
		return driver;
	}

	/**
	 * Method to initiate Firefox Driver
	 * @return firefox driver
	 */
	private WebDriver initFirefoxDriver() {
		log.info("Launching Firefox browser..");
		WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
		WebDriver driver = new FirefoxDriver();		
		return driver;
	}

	/**
	 * Method to initiate InterExplorer Driver
	 * @return internetExplorer driver
	 */
	private WebDriver initInternetExplorerDriver() {
		log.info("Launching Internet Explorer browser..");
		WebDriverManager.getInstance(DriverManagerType.IEXPLORER).setup();
		WebDriver driver = new InternetExplorerDriver();		
		return driver;
	}
	

	/**
	 * public method to get the driver instance
	 * @return
	 */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * This public method can be called to setup driver for browser type, url, implicit wait and pageload wait
     * @param browserType
     * @param implicitWait
     * @param pageloadWait
     * @param url
     */
    public static void setUpDriver(String browserType,int implicitWait, int pageloadWait,String url) {
       
    	// check if seleniumdriver instance is null, then initialize
    	if (seleniumDriver == null)
            seleniumDriver = new SeleniumDriver(browserType,implicitWait,pageloadWait,url);      
    }

    /**
     * Method to teardown the driver and helper instance
     */
    public static void tearDown() {
    	log.info("quitting the driver");
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        seleniumDriver = null;
    }
    
  
}
