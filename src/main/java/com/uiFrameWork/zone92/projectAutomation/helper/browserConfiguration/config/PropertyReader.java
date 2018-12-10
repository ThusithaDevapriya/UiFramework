package com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration.BrowserType;
import com.uiFrameWork.zone92.projectAutomation.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader{

	private static FileInputStream file;
	private static Properties pr;
	
	public PropertyReader() {
		try {
			String filePath = ResourceHelper.getResourcePath("\\src\\main\\resources\\configFile\\config.properties");
			file = new FileInputStream(new File(filePath));
			pr = new Properties();
			pr.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		
	
	public int getImplicitWait() {
		return Integer.parseInt(pr.getProperty("implicitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(pr.getProperty("explicitWait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(pr.getProperty("pageLoadTime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(pr.getProperty("browserType"));
	}

}
