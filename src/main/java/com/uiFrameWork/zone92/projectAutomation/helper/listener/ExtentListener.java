package com.uiFrameWork.zone92.projectAutomation.helper.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiFrameWork.zone92.projectAutomation.helper.database.DatabaseHelper;
import com.uiFrameWork.zone92.projectAutomation.helper.logger.LoggerHelper;
import com.uiFrameWork.zone92.projectAutomation.utils.ExtentManager;

public class ExtentListener implements ITestListener{
	private Logger log = LoggerHelper.getLogger(ExtentListener.class);
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public void onFinish(ITestContext context) {
		//extent.flush();
		Reporter.log(context.getName()+" Test Finished..");
		log.info(context.getName()+" Test Finished..");
	}
	
	public void onStart(ITestContext context) {
		//extent = ExtentManager.getInstance();
		//test = extent.createTest(context.getCurrentXmlTest().getName());
		Reporter.log(context.getCurrentXmlTest().getName()+" Class Started..");
		log.info(context.getCurrentXmlTest().getName()+" Class Started..");
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" Test Failed.."+result.getThrowable());
		log.error(result.getMethod().getMethodName()+" Test Failed.."+result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		//test.log(Status.SKIP, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" Test Skipped.."+result.getThrowable());
		log.warn(result.getMethod().getMethodName()+" Test Skipped.."+result.getThrowable());
	}
	
	public void onTestStart(ITestResult result) {
		//test.log(Status.INFO, result.getName()+" started..");
		Reporter.log(result.getMethod().getMethodName()+" Test Started..");
		log.info(result.getMethod().getMethodName()+" Test Started..");
	}

	public void onTestSuccess(ITestResult result) {
		//test.log(Status.INFO, result.getName()+" Passed..");
		Reporter.log(result.getMethod().getMethodName()+" Test Passed..");
		log.info(result.getMethod().getMethodName()+" Test Passed..");	
	}

}
