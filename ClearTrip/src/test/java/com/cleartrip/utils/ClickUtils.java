package com.cleartrip.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.reporters.ReportManager;

public class ClickUtils {

	/**
	 * Method to click on the webelement w.r.t the locator
	 * @param test
	 * @param loc
	 * @param failureMessage
	 */
	
	public static void clickButtonOrFail(SeleniumSEPTest test, By loc, String failureMessage) {

		WebElement element = CheckUtils.getElement(test, loc);
		clickButtonOrFail(test, element, failureMessage);
	}

	/**
	 * Method to click on the webelement
	 * @param test
	 * @param element
	 * @param failureMessage
	 */
	
	public static void clickButtonOrFail(SeleniumSEPTest test, WebElement element, String failureMessage) {
		
		ReportManager reportManager = test.getReportManager();
		
		try {
			element.click();
			reportManager.reportPassed("WebElement Click", "Clicked on the webelement successfully");
		}
		catch (Exception e) {
			reportManager.reportFailed("WebElement Click", failureMessage);
		}
	}
	
}
