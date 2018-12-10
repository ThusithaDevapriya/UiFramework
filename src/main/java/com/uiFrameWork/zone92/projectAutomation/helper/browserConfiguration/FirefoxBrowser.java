package com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiFrameWork.zone92.projectAutomation.helper.resource.ResourceHelper;

public class FirefoxBrowser {

	public FirefoxOptions getFirefoxOptions() {
		
		DesiredCapabilities fireFox = DesiredCapabilities.firefox();
		
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		
		fireFox.setCapability(FirefoxDriver.PROFILE, profile);
		fireFox.setCapability("marionette", true);
		
		FirefoxOptions fireFoxOptions = new FirefoxOptions(fireFox);
		
		
		return fireFoxOptions;
	}
	public WebDriver getFireFoxDriver(FirefoxOptions fop) {
		
		if(System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\geckodriver"));
			return new FirefoxDriver(fop);
		}
		else if(System.getProperty("os.name").contains("WIN10")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\geckodriver.exe"));
			return new FirefoxDriver(fop);
		}
		else if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\usr\\bin\\geckodriver"));
			return new FirefoxDriver(fop);
		}
		return null; 
	}
}
