package com.ui.Listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.test.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {

	Logger logger = LoggerUtility.getLogger(this.getClass());
	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	@Override
	public void onTestStart(ITestResult result) {
		// ITestListener.super.onTestStart(result);
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		logger.info(result.getMethod().getMethodName() + "  passed");
		ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + "  passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		logger.error(result.getMethod().getMethodName() + "  failed");
		logger.error(result.getThrowable().getMessage());
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + "  failed");
		ExtentReporterUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());
		Object testClass= result.getInstance();
		BrowserUtility browserUtility=((TestBase)testClass).getInstance();
		logger.info("Capturing screenshot for the failed tests ");
		//logger.info(result.getMethod().getDescription());
		String screenShotPath=browserUtility.takeScreenShot(result.getMethod().getMethodName());
		logger.info("Attaching the  screenshot to the HTML file ");
		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenShotPath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + "   Skipped");
		ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + "  Skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("Test Suite Started");
		ExtentReporterUtility.setUpSparkReporter("report.html");

	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test Suite completed");
		ExtentReporterUtility.flushReport();
	}

}
