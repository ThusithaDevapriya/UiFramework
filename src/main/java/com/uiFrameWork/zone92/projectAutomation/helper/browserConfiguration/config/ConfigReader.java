package com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration.config;

import com.uiFrameWork.zone92.projectAutomation.helper.browserConfiguration.BrowserType;

public interface ConfigReader {

	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
}
