package com.uiFrameWork.zone92.projectAutomation.testBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
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
import com.uiFrameWork.zone92.projectAutomation.helper.resource.ResourceHelper;
import com.uiFrameWork.zone92.projectAutomation.helper.wait.WaitHelper;
import com.uiFrameWork.zone92.projectAutomation.utils.ExtentManager;

public class TestBase{

	public static ExtentReports extent;
	public static ExtentTest test;
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectory;
	
	
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
	}
	
	@BeforeTest
	public void beforeTest() throws Exception {
		ObjectReader.reader = new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourcePath("\\src\\main\\resources\\screenShots"));
		setUpDriver(ObjectReader.reader.getBrowserType());
	}
	
	@AfterTest
	public void afterTest() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	@BeforeClass
	public void beforeClass() {
		test = extent.createTest(getClass().getSimpleName());
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		test.log(Status.INFO, method.getName()+" test started");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName(), driver);
			test.addScreenCaptureFromPath(imagePath);
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName()+" is pass");
			String imagePath = captureScreen(result.getName(), driver);
			test.addScreenCaptureFromPath(imagePath);
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
	}
	
	public String captureScreen(String fileName, WebDriver driver) {
		if(driver == null) {
			log.info("driver is null..");
			return null;
		}
		if(fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calander = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			destFile = new File(reportDirectory+"/"+fileName+"_"+formater.format(calander.getTime())+".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return destFile.toString();		
	}
	
	public void getNavigationScreen(WebDriver driver) {
		log.info("capturing ui navigation screen...");
		String screen = captureScreen("", driver);
		try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
