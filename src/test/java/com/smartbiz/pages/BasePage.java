package com.smartbiz.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

public class BasePage {
	
	private final WebDriver driver;
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getElement(By locator)
	{
		return driver.findElement(locator);
	}
	
	public boolean isElementPresent(By by) {
	    try {
	      
	      return driver.findElements(by).size()!=0;
	    } catch (Exception e) {
	      return false;
	    }
	}
	
	public boolean isLocatorPresent(WebElement element) {
	    try {
	      return element.isDisplayed();
	    } catch (Exception e) {
	      return false;
	    }
	}
	
	public boolean isElementPresent(WebElement parent,By by) {
	    try {
	      return parent.findElements(by).size()!=0;
	    } catch (Exception e) {
	      return false;
	    }
	}
	
	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	public void scrollToElement(WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	
	public void scrollIntoView(WebElement element)
	{
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public void scrollToElementWithOffset(WebElement element)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element, 0, -100).build().perform();
	}
	
	public void selectValueInDropdown(WebElement webelement, String dropdownText)
	{
		Actions action = new Actions(driver);
		action.click(webelement).build().perform();
		action.sendKeys(dropdownText.toLowerCase()).sendKeys(Keys.RETURN).build().perform();
	}
	
	public void fluentWaitForElement(WebElement ele){
		new FluentWait<WebElement>(ele).withTimeout(Duration.ofSeconds(2))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(StaleElementReferenceException.class).until(new Function<WebElement, WebElement>() {
			public WebElement apply(WebElement elem) {
				return elem;
			}
		});
	}
	
	public void fluentWaitForElementPresence(WebElement ele){
		new FluentWait<WebElement>(ele).withTimeout(Duration.ofSeconds(2))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class).until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement elem) {
				return elem.getAttribute("aria-hidden").equalsIgnoreCase("false");
			}
		});
	}
	
	public void fluentWaitForElementEnabled(WebElement ele){
		new FluentWait<WebElement>(ele).withTimeout(Duration.ofSeconds(2))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class).until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement elem) {
				return elem.isDisplayed();
			}
		});
	}
}