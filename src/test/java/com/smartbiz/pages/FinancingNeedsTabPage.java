package com.smartbiz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinancingNeedsTabPage extends BasePage{
	
	public FinancingNeedsTabPage(WebDriver driver) {
		super(driver);
	}

	public By financingNeedsTaboptions(String option) {
		return By.xpath("//label/div[contains(normalize-space(.),\""+option+"\")]");
	}
}
