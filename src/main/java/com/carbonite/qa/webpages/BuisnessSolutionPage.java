package com.carbonite.qa.webpages;

import org.openqa.selenium.support.PageFactory;

import com.carbonite.qa.base.TestBase;

public class BuisnessSolutionPage extends TestBase {
	
	
	public BuisnessSolutionPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	public String getTitleOfThePage() {
		
		String titleOfThePage = driver.getTitle();

		return titleOfThePage;
	}

}
