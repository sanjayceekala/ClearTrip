package com.cleartrip.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.reporters.ReportManager;

public class FillUtils {

	/**
	 * Method to fill the text in the webelement w.r.t locator
	 * @param test
	 * @param loc
	 * @param text
	 * @param failureMessage
	 */
	
	public static void fillInputOrFail(SeleniumSEPTest test, By loc, String text, String failureMessage) {

		WebElement element = CheckUtils.getElement(test, loc);
		fillInputOrFail(test, element, text, failureMessage);
	}
	
	/**
	 * Method to fill the text in the webelement
	 * @param test
	 * @param loc
	 * @param text
	 * @param failureMessage
	 */
	
	public static void fillInputOrFail(SeleniumSEPTest test, WebElement element, String text, String failureMessage) {

		ReportManager reportManager = test.getReportManager();
		
		if(element != null) {
			((JavascriptExecutor) test.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			element.clear();
			element.sendKeys(text);
			reportManager.reportPassed("Fill text box", "Successfully filled the text in the text box");
		}
		else {
			reportManager.reportFailed("Fill text box", failureMessage);
		}
	}
	
	/**
	 * Method to select the text in the dropdown
	 * @param test
	 * @param loc
	 * @param text
	 * @param failureMessage
	 */
	
	public static void fillSelectByVisibleText(SeleniumSEPTest test, By loc, String text, String failureMessage) {
		WebElement element = CheckUtils.getElement(test, loc);
		ReportManager reportManager = test.getReportManager();
		
		if(element != null) {
			((JavascriptExecutor) test.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			Select select = new Select(element);
			select.selectByVisibleText(text);
			reportManager.reportPassed("Select Dropdown Text", "Successfully selected the text in the dropdown");
		}
		else {
			reportManager.reportFailed("Select Dropdown Text", failureMessage);
		}
	}
	
	/**
	 * Method to select the index in the dropdown
	 * @param test
	 * @param loc
	 * @param index
	 * @param failureMessage
	 */
	
	public static void fillSelectByIndex(SeleniumSEPTest test, By loc, int index, String failureMessage) {
		WebElement element = CheckUtils.getElement(test, loc);
		ReportManager reportManager = test.getReportManager();
		
		if(element != null) {
			((JavascriptExecutor) test.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
			Select select = new Select(element);
			select.selectByIndex(index);
			reportManager.reportPassed("Select Dropdown Index", "Successfully selected the Index in the dropdown");
		}
		else {
			reportManager.reportFailed("Select Dropdown Index", failureMessage);
		}
	}
}
