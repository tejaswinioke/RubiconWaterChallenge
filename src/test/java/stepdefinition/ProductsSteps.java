package stepdefinition;

import static org.testng.Assert.assertEquals;
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
 * Step definition class for products feature
 * 
 * @author Teja Oke
 *
 */
public class ProductsSteps {
	Header header;
	HomePage home;
	private WebDriver driver = SeleniumDriver.getDriver();
	ProductsPage products;
	CatalogPage catalog;

	@Given("I am on the home page")
	public void i_am_on_the_home_page() {
		home = new HomePage(driver);
		header = new Header(driver);
	}

	@When("I go to sub menu {string} in menu {string}")
	public void i_go_to_sub_menu_in_menu(String subMenu, String menu) throws InterruptedException {

		// Click on menu -> sub menu
		catalog = header.hoverMenuAndClickSubTitle(menu, subMenu);

		// check the results and verify if correct result displayed
		String title = catalog.getResultsHeaderDisplayed();
		assertTrue(title.contains(subMenu), subMenu + " is not selected correctly");
	}

	@When("I open {string} tab")
	public void i_open_tab(String tabName) {
		
		// select the tab
		catalog.clickTab(tabName);
	}

	@Then("I should be able to find {string} details")
	public void i_should_be_able_to_find_details(String type) {
		
		// get the specification value for the given type
		String solarDetails = catalog.getSpecificationDetails(type);
		assertEquals(solarDetails, "85W monocrystalline");
	}

	@When("I click on {string} link on sidebar")
	public void i_click_on_link_on_sidebar(String linkText) {

		// download brochure
		catalog.downloadBrochure();

	}

	@Then("I should be able to see PDF file in browser")
	public void i_should_be_able_to_see_pdf_file_in_browser() {

		// switch to window
		catalog.switchToNewWindowWithURL("pdf");

		// check page URL
		assertTrue(catalog.getPageURL().contains("pdf"), "PDF brochure is not displayed");
	}

}
