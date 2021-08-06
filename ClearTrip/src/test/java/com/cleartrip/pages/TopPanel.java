package com.cleartrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.reporters.ReportManager;
import com.cleartrip.utils.ClickUtils;
import com.cleartrip.utils.FillUtils;
import com.cleartrip.utils.WaitUtils;

public class TopPanel {

	WebDriver driver;

	SeleniumSEPTest test;

	private final By LOC_WL_YOUR_TRIPS = By.xpath(".//p[text()='Your trips']");
	private final By LOC_BT_SIGNIN_YOUR_TRIPS = By.xpath(".//button[text()='Sign in']");
	private final By LOC_IN_EMAIL_ADDRESS = By.cssSelector("input[placeholder='Email address']");
	private final By LOC_IN_EMAIL_PASSWORD = By.cssSelector("input[placeholder='Password']");
	private final By LOC_BT_SIGNIN_CREDENTIALS = By.xpath(".//button//span[text()='Sign in']");

	public TopPanel(SeleniumSEPTest test) {

		driver = test.getDriver();
		this.test = test;

		ReportManager reportManager = test.getReportManager();

		if(WaitUtils.waitForElementPresent(test, LOC_WL_YOUR_TRIPS, 10, "Flight search page is not present")) {
			reportManager.reportPassed("Top Panel", "Top Panel is displayed");
		}
		else {
			reportManager.fail("Top Panel is not displayed");
		}
	}

	public void actionSignIn() {
		clickYourTrips();
		clickSignInYourTrips();
		enterCredentials("cleartripsixtproject@gmail.com", "Sanjay@89");
		clickSignInCredentials();
	}

	public void clickYourTrips() {
		ClickUtils.clickButtonOrFail(test, LOC_WL_YOUR_TRIPS, "Unable to click on the your trips");
		WaitUtils.waitForElementVisible(test, LOC_BT_SIGNIN_YOUR_TRIPS, 2, "Signin button is not displayed");
	}

	public void clickSignInYourTrips() {
		ClickUtils.clickButtonOrFail(test, LOC_BT_SIGNIN_YOUR_TRIPS, "Unable to click on Sign in button after clicking on the your trips");
		WaitUtils.waitForElementVisible(test, LOC_IN_EMAIL_ADDRESS, 2, "email addressn is not displayed");
	}

	public void enterCredentials(String emailAddress, String password) {
		FillUtils.fillInputOrFail(test, LOC_IN_EMAIL_ADDRESS, emailAddress, "Unable to enter the email address");
		FillUtils.fillInputOrFail(test, LOC_IN_EMAIL_PASSWORD, password, "Unable to enter the password");
	}

	public void clickSignInCredentials() {
		ClickUtils.clickButtonOrFail(test, LOC_BT_SIGNIN_CREDENTIALS, "Unable to click on Sign in button after entering the credentials");
	}

}
