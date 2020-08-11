package com.qa.extentReportListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp for the event

		String repName = "Extent - Test-Report-" + "---" + timeStamp + ".html";

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Extent Reports/" + repName);// location
																											// of the
																											// report in
																											// the
																											// framework

		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Name of the Host", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("Tester Name", "Manvir");

		htmlReporter.config().setDocumentTitle("Carbonite QA Automation Project"); // Title of the report generated
		htmlReporter.config().setReportName("Sign Up Automation Report"); // Name of the report generated
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart in the report
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	public void onTestSuccess(ITestResult tr) {
		test = extent.createTest(tr.getName()); // create new entry in the report on test success
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult tr) {
		test = extent.createTest(tr.getName()); // create new entry in the report on test failure
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

	}

	public void onTestSkipped(ITestResult tr) {
		test = extent.createTest(tr.getName()); // create new entry in the report in case the test is skipped
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
