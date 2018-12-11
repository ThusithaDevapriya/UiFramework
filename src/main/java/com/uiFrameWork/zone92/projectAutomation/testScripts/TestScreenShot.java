package com.uiFrameWork.zone92.projectAutomation.testScripts;

import org.testng.annotations.Test;

import com.uiFrameWork.zone92.projectAutomation.testBase.TestBase;

public class TestScreenShot extends TestBase {

	@Test
	public void testScreen() {
		driver.get("https://www.facebook.com/");
		captureScreen("fbSCR", driver);
	}
}
