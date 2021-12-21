package com.smartbiz.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

 import driver.DriverFactory;

public class LandingPage extends BasePage{
	
	public LandingPage(WebDriver driver) {
		super(driver);
	}
	
	public By header(String header) {
		return By.xpath("//a[@class = 'main-logo ']/descendant::img[(@alt = '"+header+"')]");
	}
	
	@FindBy(xpath = "//span[contains(.,'Allow')]")
	public WebElement allowCookie;
	
	public By prequalifyButtonDisbaledLabel(String buttonLabel, String disabled) {
		return By.xpath("//a[contains(@class,'"+disabled+"')]/div[contains(normalize-space(.),'"+buttonLabel+"')]");
	}
	
	public By prequalifyButton(String buttonLabel) {
		return By.xpath("//a/div[contains(normalize-space(.),\""+buttonLabel+"\")]");
	}

	public By loanUsageOption(String loanUsageLabel) {
		return By.xpath("//p[contains(normalize-space(.), '"+loanUsageLabel+"')]/ancestor::div[contains(@class,'square-selector')]/descendant::div[contains(@class,'box relative')]");
	}
	
	public By loanType(String loanTypeLabel) {
		return By.xpath("//h3[contains(normalize-space(.), '"+loanTypeLabel+"')]/ancestor::div[contains(@class,'info-content')]");
	}
	
	public By personalDetailsTextBox(String textBoxLabel) {
		return By.xpath("//div[contains(normalize-space(.), '"+textBoxLabel+"')]/input");
	}
	
	@FindBy(xpath = "//select[@id='referral_source']")
	public WebElement referralStatusDropdown;
	
	public By referralOptions(String referralOptionLabel) {
		return By.xpath("//select[@id='referral_source']/option[contains(normalize-space(.), '"+referralOptionLabel+"')]");
	}
	
	@FindBy(xpath = "//input[@id='privacy_policy']")
	public WebElement privacyPolicyCheckbox;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public By pageHeading = By.xpath("//div[contains(@class,'copilot')]");
	
	@FindBy(xpath = "//a[contains(@class,'nav-btn')]/span[contains(text(),'Logout')]")
	public WebElement logoutTab;
	//a[contains(@class,'nav-btn')]/span[contains(text(),'Logout')]
	//md-sidenav/md-list/md-list-item/a[contains(@class,'nav-btn')]/span[contains(text(),'Logout')]
	public By usernameLocator(String username) {
		return By.xpath("//span[text()='"+username+"']");
	}	
	public By headingLocator(String heading) {
		return By.xpath("//h1[contains(@class,'page-title') and contains(text(),'"+heading+"')]");
	}
	public By tabLocator(String tabName) {
		return By.xpath("//a[contains(@class,'nav-btn')]/span[contains(text(),'"+tabName+"')]");
	}
	
	@FindBy(xpath = "//input[@placeholder='Search' and @aria-hidden='false']")
	public WebElement searchBar;
	
	@FindBy(xpath = "(//input[@placeholder='Search' and @aria-hidden='false'])[2]")
	public WebElement retrievalsSearchBar;
	
	@FindBy(xpath = "//div[contains(@class,'search-box')]/i[text()='clear']")
	public WebElement searchBarClear;
	
	public By landingButton(String buttonLabel) {
		return By.xpath("//button[contains(normalize-space(.),'"+buttonLabel+"')]");
	}
	
	public By landingIcon(String iconLabel) {
		return By.xpath("//button[@aria-label = '"+iconLabel+"']");
	}
	
	@FindBy(xpath = "//button[@aria-label='Column Selector']")
	public WebElement columnSelector;
	
	public By availableColumnsHeading = By.xpath("//md-dialog/descendant::*[text()='Available Columns']");
	
	public By columnCheckBoxAttr(String checkBoxLabel) {
		return By.xpath("//md-checkbox[contains(@aria-label,'"+checkBoxLabel+"')]");
	}
	
	public By columnCheckBox(String checkBoxLabel) {
		return By.xpath("//div[contains(@class,'md-list') and contains(normalize-space(.), '"+checkBoxLabel+"')]");
	}
	
	public By merchantColumnCheckBox(String checkBoxLabel) {
		return By.xpath("//md-dialog[contains(@aria-describedby,'dialogContent')]/descendant::md-checkbox[contains(@aria-label,'"+checkBoxLabel+"')]");
	}
	
	@FindBy(xpath = "//md-dialog-actions/button/span[contains(text(),'Close')]")
	public WebElement availableCoumnsCloseButton;
	
	@FindBy(xpath = "//table[contains(@class,'cc-data-table')]")
	public WebElement searchResultsTable;
	
	public List<WebElement> searchResultsTableColumns() {
		return searchResultsTable.findElements(By.tagName("md-menu"));
	}
	
	public List<WebElement> searchResultsTableRows() {
		return searchResultsTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
	}
	
	public String getRowValue(WebElement rowElement, int index) {
		return rowElement.findElements(By.tagName("td")).get(index).getText();
	}
	
	public By linkInSearchResultsTable(String linkText) {
		return By.xpath("//tr/descendant::a[contains(text(),'"+linkText+"')]");
	}
	
	public By exactLinkInSearchResultsTable(String linkText) {
		return By.xpath("//tr/descendant::a[text()='"+linkText+"']");
	}
	
	public By accountSummaryHeadingElement(String dbaName, String MID) {
		return By.xpath("//div[contains(@class,'fullscreen-header') and contains(normalize-space(.),'"+dbaName+"') and contains(normalize-space(.),' - "+MID+"')]");
	}
	
	@FindBy(xpath = "//div[contains(@class,'tableStats')]")
	public WebElement searchResultsCount;
	
	@FindBy(xpath = "//input[@placeholder='Search Back End Platform' and @aria-hidden='false']")
	public WebElement backEndPlatformSearchBar;
	
    @FindBy(xpath = "//div[contains(@class,'md-open-menu-container') and @aria-hidden='false']/md-menu-content/md-menu-item[contains(normalize-space(.),'Sort Ascending') and @aria-hidden='false']")
    public WebElement ascendingSort;
    
    @FindBy(xpath = "//div[contains(@class,'md-open-menu-container') and @aria-hidden='false']/md-menu-content/md-menu-item[contains(normalize-space(.),'Sort Descending') and @aria-hidden='false']")
    public WebElement descendingSort;
    
    @FindBy(xpath = "//div[contains(@class,'md-open-menu-container') and @aria-hidden='false']/md-menu-content/md-menu-item[contains(normalize-space(.),'Remove Sort') and @aria-hidden='false']")
    public WebElement removeSort;

    @FindBy(xpath = "//a[contains(@class,'md-button')]/span[contains(text(),'New Account')]")
	public WebElement newAccountButton;
    
    public By merchantEditPageHeader(String editPageHeader) {
		return By.xpath("//div[contains(@class,'fullscreen-header') and contains(normalize-space(.),'"+editPageHeader+"')]");
	}
    @FindBy(xpath = "//footer[contains(normalize-space(.),'SaaS Solutions Terms')]")
	public WebElement termsAndConditionsFooter;
  
    @FindBy(xpath = "//md-dialog[@aria-label='Terms and Conditions']")
   	public WebElement termsAndConditionsDialog;
}