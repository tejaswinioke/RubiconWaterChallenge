package stepdefinition;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import helpers.SeleniumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CatalogPage;
import pages.Header;
import pages.HomePage;
import pages.ProductsPage;

/**
 * Step definition class for Search feature
 * @author z0040c8h
 *
 */
public class SearchSteps {

	Header header;
	HomePage home;
	private WebDriver driver = SeleniumDriver.getDriver();
	ProductsPage products;
	CatalogPage catalog;

	@Given("I am on home page")
	public void i_am_on_home_page() {
		home = new HomePage(driver);
		header = new Header(driver);
	}

	@When("I search for {string}")
	public void i_search(String seacrhText) {
		
		// search for the given text
		products = header.performProductSearch(seacrhText);
		
		// verify the search step is correct
		String title = products.getResultsDisplayed();		
		assertTrue(title.contains(seacrhText.split(" ")[0]), seacrhText+" result not shown");
	}
	
	@When("click on search result for {string}")
	public void click_on_search_result(String resultTitle) {
		
		// click on the result title to view the details
		catalog = products.clickResultsToView(resultTitle);
		
		// verify the details are displayed
		String title = catalog.getResultsHeaderDisplayed();
		assertTrue(title.contains(resultTitle),"Product is not searched correctly");
		
	}
	
	@Then("I should be able to see details of {string}")
	public void i_should_be_able_to_see_details_of(String type) {
		
		// get the overview details for the product
		String cropMonitoringDetails = catalog.getOverviewDetails(type);
		
		assertTrue(cropMonitoringDetails.contains("Connected devices such as soil moisture sensors"),
				"Crop Monitoring details are not shown");
	}

	

}
