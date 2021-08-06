package com.cleartrip.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.reporters.ReportManager;

public class WaitUtils {
	
	/**
	 * Method to wait for an element present
	 * @param test
	 * @param locator
	 * @param timeOut
	 * @param failureMessage
	 * @return
	 */

	public static boolean waitForElementPresent(SeleniumSEPTest test, By locator, int timeOut, String failureMessage) {

		WebDriver driver = test.getDriver();
		ReportManager reportManager = test.getReportManager();

		WebDriverWait wait = new WebDriverWait(driver, timeOut, 10);

		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if(element != null) {
			reportManager.reportPassed("Element", "Element is present in the webpage");
			return true;
		}
		else {
			reportManager.reportFailed("Element", failureMessage);
			return false;
		}
	}
	
	/**
	 * Method to wait for an element visible
	 * @param test
	 * @param locator
	 * @param timeOut
	 * @param failureMessage
	 * @return
	 */

	public static boolean waitForElementVisible(SeleniumSEPTest test, By locator, int timeOut, String failureMessage) {

		WebDriver driver = test.getDriver();
		ReportManager reportManager = test.getReportManager();

		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		if(element != null) {
			reportManager.reportPassed("Element", "Element is visible in the webpage");
			return true;
		}
		else {
			reportManager.reportFailed("Element", failureMessage);
			return false;
		}

	}
}
