package com.smartbiz.workflow;

import com.smartbiz.pages.FinancingNeedsTabPage;
import com.smartbiz.pages.LandingPage;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import driver.DriverFactory;
import util.Helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Userlogin {

	private final WebDriver driver;
	LandingPage landingPage;
	FinancingNeedsTabPage financingNeedsTabPage;
	WebDriverWait wait;
	
	public Userlogin() {
		this.driver = DriverFactory.getDriver();
		landingPage = PageFactory.initElements(driver, LandingPage.class);
		financingNeedsTabPage = PageFactory.initElements(driver, FinancingNeedsTabPage.class);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}
	@Step("Navigate to Smartbiz website")
	public void gotoLogin() throws InterruptedException {
		String app_url = Helper.getPropertyValue("APP_URL");
		driver.navigate().to(app_url);;
	}	
	
	@Step("Verify the landing page has header <heading>")
	public void landpageHeading(String heading) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(landingPage.header(heading)));
		assertEquals(landingPage.getElement(landingPage.header(heading)).isDisplayed(), true,"Smart Biz heading is not displayed" );
		landingPage.allowCookie.click();
	}
	
	@Step("Verify <buttonLabel> button is <disabled>")
	public void verifyPrequalifyButtonDisabled(String buttonLabel, String disabled) throws InterruptedException {
		assertEquals(landingPage.getElement(landingPage.prequalifyButtonDisbaledLabel(buttonLabel, disabled)).isDisplayed(), true,buttonLabel+" is not displayed" );
	}
	
	@Step("Select <loanUsageOptionLabel> and select <loanTypeLabel> option")
	public void selectLoanUsageOptionAndLoanType(String loanUsageOptionLabel, String loanTypeLabel) throws InterruptedException {
		Thread.sleep(1000);
		landingPage.getElement(landingPage.loanUsageOption(loanUsageOptionLabel)).click();
		Thread.sleep(1000);
		landingPage.getElement(landingPage.loanType(loanTypeLabel)).click();
	}
	
	@Step("Enter <personalDetailsText> in <textBoxLabel> textbox")
	public void enterPersonalDetails(String personalDetailsText, String textBoxLabel) throws InterruptedException {
		landingPage.getElement(landingPage.personalDetailsTextBox(textBoxLabel)).sendKeys(personalDetailsText);
	}
	
	@Step("Enter unique email <emailText> in <textBoxLabel> textbox")
	public void enterUniqueEmail(String emailText, String textBoxLabel) throws InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
		Helper.setPropertyValue("Flow_Timestamp", sdf.format(new Date()));
		emailText = emailText.replace("<TS>", Helper.getPropertyValue("Flow_Timestamp"));
		System.setProperty("Email_Id", emailText);
		landingPage.getElement(landingPage.personalDetailsTextBox(textBoxLabel)).sendKeys(emailText);
	}
	
	@Step("Select <referralType> from how did you hear about drop down")
	public void selectReferralType(String referralType) throws Exception {	
		landingPage.referralStatusDropdown.click();
		Thread.sleep(2000);
		landingPage.getElement(landingPage.referralOptions(referralType)).click();
		Thread.sleep(1000);
	}
	
	@Step("Check the conditions and terms checkbox")
	public void clickPrivacyPolicyCheckbox() throws InterruptedException {
		landingPage.privacyPolicyCheckbox.click();
	}
	
	@Step("Verify <buttonLabel> button is enabled")
	public void verifyPrequalifyButtonEnabled(String buttonLabel) throws InterruptedException {
		assertEquals(landingPage.getElement(landingPage.prequalifyButton(buttonLabel)).isEnabled(), true,buttonLabel+" is not displayed" );
	}
	
	@Step("Click <buttonLabel> button")
	public void clickPrivacyPolicyCheckbox(String buttonLabel) throws InterruptedException {
		landingPage.getElement(landingPage.prequalifyButton(buttonLabel)).click();
		Thread.sleep(6000);
	}
	
	@Step("Verify the url contains <partialUrl>")
	public void verifyUrl(String partialUrl) throws InterruptedException {
		String url = driver.getCurrentUrl();
		assertEquals(url.contains(partialUrl), true, partialUrl+" is not included in the url");
	}
	
	@Step("Verify the button <buttonLabel> is displayed")
	public void verifyGetStartedButton(String buttonLabel) throws InterruptedException {
		assertEquals(landingPage.getElement(landingPage.prequalifyButton(buttonLabel)).isDisplayed(), true,buttonLabel+" is not displayed" );
	}
	
	@Step("Verify the option <option> is displayed")
	public void verifyOptionsLabel(String option) throws InterruptedException {
		assertEquals(financingNeedsTabPage.getElement(financingNeedsTabPage.financingNeedsTaboptions(option)).isDisplayed(), true,option+" is not displayed" );
	}
	
	@Step("Click the option <option>")
	public void clickOption(String option) throws InterruptedException {
		financingNeedsTabPage.getElement(financingNeedsTabPage.financingNeedsTaboptions(option)).click();
		Thread.sleep(6000);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Step("Navigate to Copilot bookmarked URL")
//	public void gotoBookmarkedLoginURL() throws InterruptedException {
//		String book_app_url = System.getenv("Bookmark_APP_URL");
//		driver.get(book_app_url);
//		Thread.sleep(2000);
//	}	
//	
//	@Step("Navigate to Copilot Production")
//	public void gotoProductionLogin() throws InterruptedException {
//		String app_url = System.getenv("APP_PROD_URL");
//		driver.get(app_url + "/");
//		Thread.sleep(3000);
//		assertTrue(driver.getTitle().contains("Log into copilot"));
//	}	
//	
//	@Step("Verify login page has Username, Password, Log in button and Forgot Password link")
//	public void loginPage() throws InterruptedException {
//		assertEquals(loginPage.username.isDisplayed(), true, "Username field is not available");
//		assertEquals(loginPage.password.isDisplayed(), true, "Password field is not available");
//		assertEquals(loginPage.getElement(loginPage.login).isDisplayed(), true, "Login button is not available");
//		assertEquals(loginPage.forgotPassword.isDisplayed(), true, "Forgot Password link is not available");
//	}		
//	@Step("Enter Username: <username>")
//	public void enterUsername(String username) throws InterruptedException {
//		loginPage.username.clear();
//		loginPage.username.sendKeys(username);
//	}
//	@Step("Enter password: <password>")
//	public void enterPassword(String password) throws InterruptedException {
//		loginPage.password.clear();
//		loginPage.password.sendKeys(password);
//	}
//	@Step("Click on Login button")
//	public void clickLogin() throws InterruptedException {
//		loginPage.getElement(loginPage.login).submit();
//		wait.until(ExpectedConditions.presenceOfElementLocated(landingPage.pageHeading));
//	}
//	
//	//Bookmark validations
//	@Step("Click on Login button.")
//	public void clickLoginInvalidCredentials() throws InterruptedException {
//		loginPage.getElement(loginPage.login).submit();
//	}
//	@Step("Verify Error <error> is displayed on login page")
//	public void verifyLoginPageError(String error) throws InterruptedException {
//		Thread.sleep(1000);
//		assertEquals(loginPage.getElement(loginPage.errorMessage(error)).isDisplayed(), true, " Error message is not displayed" );
//	}
//	@Step("Verify login was successful with <username>")
//	public void loginSuccess(String username) throws InterruptedException {
//		By userlink = landingPage.usernameLocator(username);
//		wait.until(ExpectedConditions.presenceOfElementLocated(userlink));
//		assertEquals(landingPage.getElement(userlink).isDisplayed(), true,"Username is not correct" );
//	}
//	@Step("Verify LandingPage header has <heading>")
//	public void landpageHeading(String heading) throws InterruptedException {
//		By header = landingPage.headingLocator(heading);
//		wait.until(ExpectedConditions.presenceOfElementLocated(header));
//		assertEquals(landingPage.getElement(header).isDisplayed(), true, 
//				heading+" heading is not available");
//	}
//	@Step("Verify following tabs exist in landing pages <table>")
//	public void tabsVerification(Table table) throws InterruptedException {
//		List<TableRow> tabsList = table.getTableRows();
//		for (TableRow tab : tabsList) {
//			assertEquals(landingPage.getElement(landingPage.tabLocator(tab.getCell("Tabs"))).isDisplayed(), true, 
//					tab+" tab is not available");
//		}	  
//	}
//	@Step("Click on Administration tab -> New User")
//	public void clickNewUser() throws InterruptedException {
//		landingPage.getElement(landingPage.tabLocator("Administration")).click();
//		wait.until(ExpectedConditions.elementToBeClickable(administrationPage.newUserButtonLocator));
//
//		administrationPage.getElement(administrationPage.newUserButtonLocator).click();
//		wait.until(ExpectedConditions.presenceOfElementLocated(newUserPage.newUserHeadingLocator));
//	}
//	@Step("Verify User Type has following roles <table>")
//	public void usertypeVerify(Table table) throws InterruptedException {
//		newUserPage.userTypeDropdown.click();
//		List<TableRow> userTypesList = table.getTableRows();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(newUserPage.userRoleLocator(userTypesList.get(0).getCell("Value"), userTypesList.get(0).getCell("Tab Name"))));
//		for (TableRow type : userTypesList) {
//			assertEquals(newUserPage.getElement(newUserPage.userRoleLocator(type.getCell("Value"), type.getCell("Tab Name"))).isDisplayed(), true, 
//					type.getCell("Tab Name")+" field in the drop down is not available");
//		}
//		newUserPage.adminOption.click();
//	}
//	@Step("Verify the role in My Profile tab has <userrole>")
//	public void roleVerify(String userrole) throws InterruptedException {
//		myProfilePage.myProfileTab.click();
//		wait.until(ExpectedConditions.presenceOfElementLocated(myProfilePage.myProfileHeadingLocator));
//		Thread.sleep(2000);
//		By user = myProfilePage.userRoleLocator(userrole);
//		assertEquals(myProfilePage.getElement(user).isDisplayed(), 
//				true, "The role of the user is not correct");
//	}
//	@Step("Click on logout")
//	public void clickLogout() throws InterruptedException {
//		Thread.sleep(3000);
//		landingPage.logoutTab.click();
////		CommonMethods.clickElement(landingPage.logoutTab);
//		wait.until(ExpectedConditions.presenceOfElementLocated(logoutPage.returnLogin));
//	}
//	@Step("Verify logout was successful with Goodbye! You have been successfully logged out from CoPilot")
//	public void logoutPageVerify() throws InterruptedException {
//		assertEquals(logoutPage.logoutPageContent.isDisplayed(), true, "verification message is not available");
//	}
//	@Step("Click on return to login")
//	public void clicReturnLogin() throws InterruptedException {
//		logoutPage.getElement(logoutPage.returnLogin).click();
//		wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.login));
//	}
//	@Step("Verify the return to login page")
//	public void returnloginPage() throws InterruptedException {
//		assertEquals(loginPage.username.isDisplayed(), true, "Username field is not available");
//		assertEquals(loginPage.password.isDisplayed(), true, "Password field is not available");
//	}
}