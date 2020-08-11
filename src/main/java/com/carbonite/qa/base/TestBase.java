package com.carbonite.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeClass;

import com.carbonite.qa.utils.Utils;
import com.carbonite.qa.utils.WebEventsListener;


public class TestBase {
	


		public static WebDriver driver;
		public static Properties prop;
		public static Logger logger;
		public static EventFiringWebDriver e_driver;
		public static WebEventsListener eventlistner;

		public TestBase() {

			prop = new Properties();

			FileInputStream file;
			try {
				file = new FileInputStream("./src\\main\\java\\com\\carbonite\\qa\\config\\config.properties");

				prop.load(file);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		@BeforeClass
		public void loggersetUp() {

			logger = Logger.getLogger(TestBase.class);
			PropertyConfigurator.configure("log4j.properties");
			BasicConfigurator.configure();

			logger.setLevel(Level.INFO);

		}

		public static void failedTestScreenShot(String testMethodName) {

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

			File screenShotFfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			try {
				FileUtils.copyFile(screenShotFfile,
						new File("./FailedTestCasesScreenShot\\" + "_" + testMethodName + "_" + timeStamp + ".jpg"));
			} catch (IOException e) {

				System.out.println("................................The IO Exception is ...... " + e);
				e.printStackTrace();
			}

		}

		public void initialisation() {

			String browsername = prop.getProperty("browser");

			if (browsername.equalsIgnoreCase("chrome")) {

				System.setProperty(prop.getProperty("Chrome_System_Property"), prop.getProperty("Chrome_Driver_Path"));

				driver = new ChromeDriver();

			}

			else if (browsername.equalsIgnoreCase("firefox")) {

				System.setProperty(prop.getProperty("Firefox_System_Property"), prop.getProperty("Firefox_Driver_Path"));

				driver = new FirefoxDriver();
			}

			e_driver = new EventFiringWebDriver(driver);

			eventlistner = new WebEventsListener();
			e_driver.register(eventlistner);

			driver = e_driver;

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Utils.PAGE_LOADOUT_WAIT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Utils.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
		}


}
