package com.cleartrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.reporters.ReportManager;
import com.cleartrip.utils.WaitUtils;

public class Payment {

	WebDriver driver;
	
	SeleniumSEPTest test;
	
	ReportManager reportManager;

	private By LOC_WL_PAYMENT_PAGE_HEADER = By.xpath(".//h1[text()='Pay to complete your booking']");
	
	public Payment(SeleniumSEPTest test) {

		driver = test.getDriver();
		this.test = test;

		reportManager = test.getReportManager();
		
		if(WaitUtils.waitForElementPresent(test, LOC_WL_PAYMENT_PAGE_HEADER, 30, "Flight search page is not present")) {
			reportManager.reportPassed("Payment Page", "Payment page is displayed");
		}
		else {
			reportManager.fail("Payment page is not displayed");
		}
	}
}
