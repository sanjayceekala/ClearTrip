package com.cleartrip.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.reporters.ReportManager;
import com.cleartrip.utils.CheckUtils;
import com.cleartrip.utils.ClickUtils;
import com.cleartrip.utils.FillUtils;
import com.cleartrip.utils.WaitUtils;

public class ReviewItinerary {

	WebDriver driver;

	SeleniumSEPTest test;

	ReportManager reportManager;
	
	private By LOC_WL_REVIEW_ITINERARY_HEADER = By.xpath(".//h2[text()='Review your itinerary']");
	private By LOC_RB_FARE_TYPES = By.cssSelector("data-testid*=fareCard");
	private By LOC_BT_CONTINUE_BUTTON = By.xpath(".//h2[text()='Continue']");
	private By LOC_BT_SKIP_THIS_STEP_BUTTON = By.xpath(".//h2[text()='Skip this step']");
	private By LOC_IN_MOBILE_NUMBER = By.cssSelector("data-testid=mobileNumber");
	private By LOC_IN_FIRSTNAME = By.cssSelector("[placeholder='First name']");
	private By LOC_IN_LASTNAME = By.cssSelector("[placeholder='Last name']");
	private By LOC_WL_GENDER = By.xpath(".//div[text()='Gender']");
	private By LOC_WL_GENDER_OPTIONS = By.xpath(".//div[text()='Gender']/ancestor:button//ul/li");
	private By LOC_BT_CONTINUE_TO_PAYMENT = By.xpath(".//button[text()='Continue to payment']");

	public ReviewItinerary(SeleniumSEPTest test) {

		driver = test.getDriver();
		this.test = test;

		reportManager = test.getReportManager();

		if(WaitUtils.waitForElementPresent(test, LOC_WL_REVIEW_ITINERARY_HEADER, 30, "Flight search page is not present")) {
			reportManager.reportPassed("Review Itinerary Booking Page", "Review Itinerary page is displayed");
		}
		else {
			reportManager.fail("Review Itinerary page is displayed");
		}
	}

	public void selectFares(String fareType) {
		List<WebElement> fareTypes = CheckUtils.getElements(test, LOC_RB_FARE_TYPES);
		for(WebElement type : fareTypes) {
			WebElement typeText = type.findElement(By.cssSelector("h3"));
			if(typeText != null) {
				if(typeText.getText().equalsIgnoreCase(fareType)) {
					ClickUtils.clickButtonOrFail(test, type.findElement(By.cssSelector("circle")), "Unable to select the fare");
				}
			}
			else {
				System.out.println("Type text is not present");
			}
		}
	}

	public void clickContinueButton() {
		ClickUtils.clickButtonOrFail(test, LOC_BT_CONTINUE_BUTTON, "Unable to click on Continue Button");
	}

	public void clickStepThisStepButton() {
		WaitUtils.waitForElementVisible(test, LOC_BT_SKIP_THIS_STEP_BUTTON, 5, "Skip This button is not displayed");
		ClickUtils.clickButtonOrFail(test, LOC_BT_SKIP_THIS_STEP_BUTTON, "Unable to click on skip this step Button");
	}

	public void enterMobileNumber(String mobileNo) {
		WaitUtils.waitForElementVisible(test, LOC_IN_MOBILE_NUMBER, 5, "Mobile No field is not displayed");
		FillUtils.fillInputOrFail(test, LOC_IN_MOBILE_NUMBER, mobileNo, "Unable to fill the mobile no");
	}

	public void fillTravellerDetails(String firstName, String lastName, String gender) {
		WaitUtils.waitForElementVisible(test, LOC_IN_FIRSTNAME, 5, "Firstname field is not displayed");
		FillUtils.fillInputOrFail(test, LOC_IN_FIRSTNAME, firstName, "Unable to fill the firstname");
		FillUtils.fillInputOrFail(test, LOC_IN_LASTNAME, lastName, "Unable to fill the lastname");
		selectGender(gender);
	}

	public void clickContinueToPaymentButton() {
		ClickUtils.clickButtonOrFail(test, LOC_BT_CONTINUE_TO_PAYMENT, "Unable to click on continue to payment button");
	}

	public void selectGender(String gender) {
		ClickUtils.clickButtonOrFail(test, LOC_WL_GENDER, "unable to click on the gender field");
		List<WebElement> gOptions = CheckUtils.getElements(test, LOC_WL_GENDER_OPTIONS);
		for(WebElement option : gOptions) {
			if(option.getText().equalsIgnoreCase(gender)) {
				ClickUtils.clickButtonOrFail(test, option, "unable to click on the gender option : " + gender);
			}
		}
	}

}
