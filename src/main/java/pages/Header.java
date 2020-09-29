package pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


/**
 * This is the page for Header of all the pages
 * @author Teja Oke
 *
 */
public class Header extends BasePage{
	
	// elements on the page
	@FindBy(how=How.NAME,using="searchstring")
	private WebElement searchBox;
	@FindBy(how=How.NAME,using="search")
	private WebElement searchButton;	
	@FindBy(how=How.XPATH,using=".//nav[@id='main']/ul")
	private WebElement menubar;
	

	public Header(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		loggerObj = Logger.getLogger(Header.class.getName());
		
	}
	
	/**
	 * This method enters the text given in search box on the page
	 * @param srchTxt
	 */
	public void enterSearchText(String srchTxt) {
		searchBox.clear();
		searchBox.sendKeys(srchTxt);
		
		logInfo("Entered text -"+srchTxt);
	}
	
	/**
	 * This method clicks the search button
	 */
	public void clickSearch() {
		logInfo("Clicking on search ");
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", searchButton);
		
	}
	
	/**
	 * This method searches for the provided product name
	 * @param srchtext
	 * @return
	 */
	public ProductsPage performProductSearch(String srchtext) {
		logInfo("Searching for text -"+srchtext);
		enterSearchText(srchtext);
		clickSearch();
		waitForPageTitle("Products");
		return new ProductsPage(driver);
	}
	
	/**
	 * This method clicks the menu as given menu title
	 * @param menuTitle
	 */
	public void clickMenu(String menuTitle) {
		logInfo("Clicking menu "+menuTitle);
		List<WebElement> menuitems=menubar.findElements(By.xpath(".//li//a//span[contains(text(),'"+menuTitle+"')]"));
		if(menuitems.size()>0) {
			menuitems.get(0).click();
		}
	}
	
	/**
	 * This method hovers over main menu and clicks on sub menu
	 * It can be used when sub menu needs to be selected
	 * @param menuTitle
	 * @param subTitle
	 * @return
	 */
	public CatalogPage hoverMenuAndClickSubTitle(String menuTitle,String subTitle) {
		
		logInfo("Selecting menu "+menuTitle+" and sub menu "+subTitle);
		List<WebElement> menuitems=menubar.findElements(By.xpath(".//li//a//span[contains(text(),'"+menuTitle+"')]"));	
			
		Actions action=new Actions(driver);
		action.moveToElement(menuitems.get(0)).build().perform();		
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", menubar.findElement(By.xpath(".//li//div[contains(@class,'sub-menu-wrapper')]//a[contains(text(),'"+subTitle+"')]")));
		
		waitForPageTitle(subTitle);
		return new CatalogPage(driver);		
	}
}
