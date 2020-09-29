package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Products page
 * 
 * @author Teja Oke
 *
 */
public class ProductsPage extends BasePage {

	// elements defined
	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'prod-list')]")
	private WebElement searchResultDiv;

	private By searchResultLink = By.xpath(".//div[@class='prod-desc']/h2/a");

	public ProductsPage(WebDriver driver) {
		super(driver);		
		loggerObj = Logger.getLogger(ProductsPage.class.getName());
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method returns the title of the results displayed on the page
	 * 
	 * @return
	 */
	public String getResultsDisplayed() {

		WebElement titleanchor = searchResultDiv.findElement(searchResultLink);
		String title = titleanchor.getText();
		logInfo("Title for the results " + title);
		return title;

	}

	/**
	 * This method clicks on the result title to view the result details
	 * 
	 * @param resultTitle
	 * @return
	 */
	public CatalogPage clickResultsToView(String resultTitle) {
		logInfo("Clicking on results title to view " + resultTitle);

		WebElement titleanchor = searchResultDiv.findElement(searchResultLink);
		titleanchor.click();
		waitForPageTitle(resultTitle);

		return new CatalogPage(driver);

	}

}
