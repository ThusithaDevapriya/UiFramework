package com.uiFrameWork.zone92.projectAutomation.testScripts;

import org.testng.annotations.Test;

import com.uiFrameWork.zone92.projectAutomation.helper.assertion.AssertionHelper;
import com.uiFrameWork.zone92.projectAutomation.testBase.TestBase;

public class Test2 extends TestBase{

	@Test
	public void testLogin() {
		AssertionHelper.makeTrue();
	}
	
	@Test
	public void testLogin1() {
		AssertionHelper.makeFalse();
	}
	
	@Test
	public void testLogin2() {
		AssertionHelper.makeTrue();
	}
	
	@Test
	public void testLogin3() {
		AssertionHelper.makeFalse();
	}
}
