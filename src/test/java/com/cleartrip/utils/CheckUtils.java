package com.cleartrip.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.reporters.ReportManager;

public class CheckUtils {
	
	/**
	 * Method to return the Webelement
	 * @param test
	 * @param loc
	 * @return
	 */

	public static WebElement getElement(SeleniumSEPTest test, By loc) {

		WebElement element = null;
		WebDriver driver = test.getDriver();
		ReportManager reportManager = test.getReportManager();
		try {
			element = driver.findElement(loc);
			return element;
		}
		catch (Exception e) {
			reportManager.reportFailed("WebElement", "The Webelement is not returned : " +  loc.toString());
			return element;
		}
	}
	
	/**
	 * Method to return the list of Webelements
	 * @param test
	 * @param loc
	 * @return
	 */

	public static List<WebElement> getElements(SeleniumSEPTest test, By loc) {

		List<WebElement> elements = null;
		WebDriver driver = test.getDriver();
		ReportManager reportManager = test.getReportManager();
		try {
			elements = driver.findElements(loc);
			return elements;
		}
		catch (Exception e) {
			reportManager.reportFailed("WebElements", "The Webelements are not returned : " +  loc.toString());
			return elements;
		}
	}

}
