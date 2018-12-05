package com.uiFrameWork.zone92.projectAutomation.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiFrameWork.zone92.projectAutomation.helper.logger.LoggerHelper;

public class WaitHelper {

	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	public void setImplecitWait(WebDriver driver) {
		this.driver = driver;		
	}
	
	/**
	 * This is ImplicitWait method
	 * 
	 * @param timeOut
	 * @param unit
	 */
	public void setImplicitWait(long timeOut, TimeUnit unit) {
		log.info("Implicit Wait has been set to: "+timeOut);
		driver.manage().timeouts().implicitlyWait(timeOut, unit);
	}
	
	/**
	 * This will help us to get WebdriverWait object
	 * 
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);		
		return wait;
	}
	
	/**
	 * This method will make sure element is visible
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info("waiting for :"+element.toString()+" for :"+timeOutInSeconds+" seconds");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}
	
	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :"+element.toString()+" for :"+timeOutInSeconds+" seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is clickable now");		
	}
	
	/**
	 * This method will make sure invisibilityOf element
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElementNotPresent(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :"+element.toString()+" for :"+timeOutInSeconds+" seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisible now");
	}
	
	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForframeToBeAvailableAndSwitchToIt(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :"+element.toString()+" for :"+timeOutInSeconds+" seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame is available and switched");
	} 
	
	/**
	 * This method will gives fluent object
	 * 
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private Wait<WebDriver> getfluentWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec)).ignoring(NoSuchElementException.class);
		return fWait;
	}
	
	/**	 
	 * 
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return 
	 */ 
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
		Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
		fwait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
		
	}
	
	/**
	 * 
	 * 
	 * @param timeOut
	 * @param unit
	 */
	public void pageLoadTime(long timeOut, TimeUnit unit) {
		log.info("waiting for page to load for : "+ unit+ " seconds");
		driver.manage().timeouts().pageLoadTimeout(timeOut, unit);
		log.info("page is loaded");
	}
	
}
