package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Catalog Page
 * 
 * @author Teja Oke
 *
 */
public class CatalogPage extends BasePage {

	// Elements defined
	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'page-content-wrapper')]")
	private WebElement catalogPageContent;
	
	By donwloadBrochireLink=By.xpath(".//a[text()='Download brochure']");

	public CatalogPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		loggerObj = Logger.getLogger(CatalogPage.class.getName());
	}

	/**
	 * This method returns the title of the result displayed
	 * @return
	 */
	public String getResultsHeaderDisplayed() {

		WebElement titleanchor = catalogPageContent.findElement(By.tagName("h1"));
		String title = titleanchor.getText();
		logInfo("Title of the Results " + title);
		return title;

	}

	/**
	 * This method clicks the tab
	 * @param tabname
	 */
	public void clickTab(String tabname) {
		logInfo("Clicking tab " + tabname);

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", catalogPageContent
				.findElement(By.xpath(".//div[@class='subpage-tab']//span[contains(text(),'" + tabname + "')]")));

	}

	/**
	 * This method gets the specification details for the given type
	 * @param type
	 * @return
	 */
	public String getSpecificationDetails(String type) {
		logInfo("Getting specifications details for " + type);
		WebElement specificationDiv = catalogPageContent.findElement(By.id("tab-specifictaions"));
		String details = specificationDiv
				.findElement(By.xpath(".//table//tr//td[contains(text(),'" + type + "')]//following-sibling::td"))
				.getText();
		return details;
	}

	/**
	 * This method gets the overview details of the product for given type
	 * @param type
	 * @return
	 */
	public String getOverviewDetails(String type) {
		logInfo("Getting overview details for " + type);
		WebElement overviewDiv = catalogPageContent.findElement(By.id("tab-overview"));
		String details = overviewDiv
				.findElement(By.xpath(".//h5[normalize-space()='" + type + "']//following-sibling::p")).getText();
		return details;
	}

	/**
	 * This method clicks to 'Download brochure'
	 */
	public void downloadBrochure() {
		logInfo("Clicking link to download brochure");

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();",
				catalogPageContent.findElement(donwloadBrochireLink));

	}

}
