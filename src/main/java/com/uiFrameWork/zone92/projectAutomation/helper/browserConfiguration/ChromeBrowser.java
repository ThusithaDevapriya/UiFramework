package com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiFrameWork.zone92.projectAutomation.helper.resource.ResourceHelper;

public class ChromeBrowser {

	public ChromeOptions getChromeOptions() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--test-type");
		option.addArguments("--disable-popup-blocking");
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true); 
		option.setCapability(ChromeOptions.CAPABILITY, chrome );
		
		return option;
	}
	
	public WebDriver getChromeDriver(ChromeOptions cop) {
		
		if(System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\chrome"));
			return new ChromeDriver(cop);
		}
		else if(System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\drivers\\chromedriver.exe"));
			return new ChromeDriver(cop);
		}
		else if(System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\usr\\bin\\chrome"));
			return new ChromeDriver(cop);
		}
		return null;
	}
}
 