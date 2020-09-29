package stepdefinition;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import helpers.SeleniumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Base Test Class to handle setup and teardown functionality
 * 
 * @author Teja Oke
 *
 */
public class Hooks {

	public Logger log = Logger.getLogger(Hooks.class.getName());
	public Properties Config = new Properties();

	@Before
	public void initializeTest(Scenario scenario) {
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");

		log.info("-----------------Beginning test execution-----------------");
		try {
			// get the environment from runtime parameter
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			Config.load(fis);

			log.info("Config Properties loaded");

		} catch (Exception e) {
			log.error(String.format("[ERROR] Exception has been thrown during setup process. " + "\n  %s",
					e.getMessage()));
			System.exit(-1);
		}

		// Code to setup initial configurations
		SeleniumDriver.setUpDriver(Config.getProperty("browser"), Integer.parseInt(Config.getProperty("implicit.wait")),
				Integer.parseInt(Config.getProperty("pageload.wait")), Config.getProperty("testsiteurl"));

	}

	@After
	public void embedScreenshot(Scenario scenario) throws IOException {
		// Embed screenshot if the scenario is failed
		if (scenario.isFailed()) {

			if (!(SeleniumDriver.getDriver() instanceof TakesScreenshot)) {
				log.error("Could not capture screenshot - selected web driver does not support taking screenshots");
				return;
			} else {
				try {

					byte[] screenshot = ((TakesScreenshot) SeleniumDriver.getDriver())
							.getScreenshotAs(OutputType.BYTES);
					scenario.attach(screenshot, "image/png", "attachmentNAme");
				} catch (WebDriverException wde) {
					log.error(wde.getMessage());
				} catch (ClassCastException cce) {
					cce.printStackTrace();
				}
			}
		}

		SeleniumDriver.tearDown();
		log.info("-----------------Finished test execution-----------------");

	}

}
