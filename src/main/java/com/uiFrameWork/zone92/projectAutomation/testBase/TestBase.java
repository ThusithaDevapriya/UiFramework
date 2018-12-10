package com.uiFrameWork.zone92.projectAutomation.testBase;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.util.TimeUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration.BrowserType;
import com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration.ChromeBrowser;
import com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration.FirefoxBrowser;
import com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration.IExplorerBrowser;
import com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration.config.ObjectReader;
import com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration.config.PropertyReader;
import com.uiFrameWork.zone92.projectAutomation.helper.logger.LoggerHelper;
import com.uiFrameWork.zone92.projectAutomation.helper.wait.WaitHelper;
import com.uiFrameWork.zone92.projectAutomation.utils.ExtentManager;

public class TestBase{

	public static ExtentReports extent;
	public static ExtentTest test;
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
	}
	
	@BeforeTest
	public void beforeTest() {
		ObjectReader.reader = new PropertyReader();
	}
	
	@BeforeClass
	public void beforeClass() {
		test = extent.createTest(getClass().getName());
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName()+" test started");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName()+" is pass");
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}	
		
		extent.flush();
	}
	
	public WebDriver getBrowserObject(BrowserType bType) throws Exception {
		try {
			switch(bType) {
			
			case Chrome:
				// get object of ChromeBrowser Class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);
				
			case Firefox:
				FirefoxBrowser firFox = FirefoxBrowser.class.newInstance();
				FirefoxOptions options = firFox.getFirefoxOptions();
				return firFox.getFireFoxDriver(options);
				
			case Iexplorer:
				IExplorerBrowser ie = IExplorerBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(cap);
				
			default:
				throw new Exception("Driver not Found: "+bType.name());
			}
		} catch (Exception e) {
				log.info(e.getMessage());
				throw e;
		}
	}
	
	public void setUpDriver(BrowserType bType) throws Exception{
		driver = getBrowserObject(bType);
		log.info("Initialize Web driver: "+driver.hashCode());		
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public String captureScreen(String fileName) {
		if(driver == null) {
			log.info("driver is null..");
			return null;
		}
		if(fileName == "") {
			fileName = "blank";
		}
		 
		return fileName;
		
	}
}
