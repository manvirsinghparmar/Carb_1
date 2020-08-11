package com.carbonite.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.carbonite.qa.base.TestBase;
import com.carbonite.qa.webpages.BuisnessSolutionPage;
import com.carbonite.qa.webpages.SignUpPage;

public class BuisnessSolutionPageTest extends TestBase {

	SignUpPage signUpPage;
	BuisnessSolutionPage business;

	@BeforeMethod
	void setUp() {

		initialisation();

		signUpPage = new SignUpPage();

		business = signUpPage.ClickOnBuisnessSolutionPage();

	}

	@Test(priority = 1)
	void getTitleOfThePage() {

		String titleOfThePage = business.getTitleOfThePage();

		Assert.assertEquals(titleOfThePage, prop.getProperty("Title-BuisnessSolutionPage"));

	}

	@AfterMethod
	void tearDown() {

		driver.quit();
	}

}
