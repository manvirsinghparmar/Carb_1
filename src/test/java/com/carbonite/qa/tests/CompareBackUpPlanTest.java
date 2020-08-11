package com.carbonite.qa.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.carbonite.qa.base.TestBase;
import com.carbonite.qa.webpages.BuisnessSolutionPage;
import com.carbonite.qa.webpages.CompareBackUpPlanPage;
import com.carbonite.qa.webpages.SignUpPage;

public class CompareBackUpPlanTest extends TestBase {

	SignUpPage signUpPage;
	CompareBackUpPlanPage backUpPlan;

	@BeforeMethod
	void setUp() {

		initialisation();

		signUpPage = new SignUpPage();

		backUpPlan = signUpPage.ClickOnCompareBackUpPlanPage();

	}

	@Test(priority = 1)
	void getTitleOfThePage() {

		String titleOfThePage = backUpPlan.getTitleOfThePage();

		Assert.assertEquals(titleOfThePage, prop.getProperty("Title-CompareBackUpPlan"));

	}

	@AfterMethod
	void tearDown() {

		driver.quit();
	}

}
