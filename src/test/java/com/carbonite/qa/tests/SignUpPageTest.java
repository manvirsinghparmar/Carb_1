package com.carbonite.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.carbonite.qa.base.TestBase;
import com.carbonite.qa.utils.ExcelUtils;
import com.carbonite.qa.webpages.SignUpPage;

public class SignUpPageTest extends TestBase {

	SignUpPage signUpPage;

	WebDriverWait wait;

	@BeforeMethod
	void setUp() throws Exception {

		initialisation();

		signUpPage = new SignUpPage();
		wait = new WebDriverWait(driver, 20);
	}

	@Test(priority = 1, dataProvider = "PositiveSignUpDataFromExcelFile")
	public void fillSignUpFormWithValidTestData(String email, String ConfirmEmail, String password,
			String ConfirmPassword) {

		signUpPage.clickCookieDialogBoxCloseButton();

		signUpPage.fillSignUpForm(email, ConfirmEmail, password, ConfirmPassword);

		signUpPage.selectCountryFromDropDown(prop.getProperty("Country"));

		signUpPage.clickOnSubmitButton();

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'Welcome to Carbonite Safe')]"))));

		Assert.assertEquals(driver.getTitle(), "Download");

	}

	@Test(priority = 2, dataProvider = "NegativeSignUpDataFromExcelFile")
	public void fillSignUpFormWithNegativeTestData(String email, String ConfirmEmail, String password,
			String confirmPassword) {

		signUpPage.clickCookieDialogBoxCloseButton();

		signUpPage.fillSignUpForm(email, ConfirmEmail, password, confirmPassword);

		if (!email.equalsIgnoreCase(ConfirmEmail)) {

			Assert.assertEquals(signUpPage.emailNotMatchingAlert.getText(), prop.getProperty("EmailMissMatch"));
		}

		if (!password.equalsIgnoreCase(confirmPassword)) {

			Assert.assertEquals(signUpPage.passwordNotMatchingAlert.getText(), prop.getProperty("PasswordMissMatch"));

		}

	}

	@Test(enabled = false)
	void test() {
		Assert.assertEquals(false, true);
	}

	@DataProvider(name = "PositiveSignUpDataFromExcelFile")
	String[][] positiveTestDataProvider() throws Exception {

		String filePath = "./TestData\\PositiveTestData.xlsx";

		int row = ExcelUtils.getRowCount(filePath, "Sheet1");
		int col = ExcelUtils.getCellCount(filePath, "Sheet1", 1);

		String[][] SignUpTestData = new String[row][col];

		for (int i = 1; i <= row; i++) {

			for (int j = 0; j < col; j++) {

				SignUpTestData[i - 1][j] = ExcelUtils.getCellData(filePath, "Sheet1", i, j);
			}

		}

		return SignUpTestData;

	}

	@DataProvider(name = "NegativeSignUpDataFromExcelFile")
	String[][] NegativeTestDataProvider() throws Exception {

		String filePath = "./TestData\\NegativeTestData .xlsx";

		int row = ExcelUtils.getRowCount(filePath, "Sheet1");
		int col = ExcelUtils.getCellCount(filePath, "Sheet1", 1);

		String[][] SignUpTestData = new String[row][col];

		for (int i = 1; i <= row; i++) {

			for (int j = 0; j < col; j++) {

				SignUpTestData[i - 1][j] = ExcelUtils.getCellData(filePath, "Sheet1", i, j);
			}

		}

		return SignUpTestData;

	}

	@AfterMethod
	void tearDown() throws InterruptedException {

		driver.quit();

	}

}
