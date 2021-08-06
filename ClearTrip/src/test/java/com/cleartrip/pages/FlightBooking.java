package com.cleartrip.pages;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.reporters.ReportManager;
import com.cleartrip.utils.CheckUtils;
import com.cleartrip.utils.ClickUtils;
import com.cleartrip.utils.DateUtils;
import com.cleartrip.utils.FillUtils;
import com.cleartrip.utils.WaitUtils;

public class FlightBooking {

	WebDriver driver;
	SeleniumSEPTest test;
	ReportManager reportManager;
	
	private final By LOC_WL_FLIGHT_SEARCH_PAGE = By.cssSelector("[class*='homeba']");
	private final MessageFormat LOC_RB_TRIP_TYPE = new MessageFormat(".//p[text()=''{0}'']/ancestor::label//span");
	private final By LOC_IN_FROM_FLIGHT_SEARCH = By.xpath(".//h4[text()='From']/parent::div//input");
	private final By LOC_IN_TO_FLIGHT_SEARCH = By.xpath(".//h4[text()='To']/parent::div//input");
	private final By LOC_CL_CALENDARS = By.cssSelector("[class*=homeCalender] button");
	private final By LOC_WL_MONTH_CALENDER = By.cssSelector("[class=DayPicker-Month]");
	private final By LOC_WL_MONTH_NAME = By.cssSelector("[class=DayPicker-Caption] div");
	private final By LOC_WL_RIGHT_ARROW_DATE_PICKER = By.cssSelector("[data-testid=rightArrow] path:nth-child(2)");
	private final By LOC_WL_DAYS_IN_CALENDAR = By.cssSelector("[class=DayPicker-Body] [class*=day]");
	private final By LOC_DD_ADULTS = By.xpath(".//h4[text()='Adults']/parent::div//select");
	private final By LOC_WL_FROM_SUGGESTIONS = By.xpath(".//h4[text()='From']/parent::div//div[text()='Suggestions']/parent::ul//p");
	private final By LOC_WL_TO_SUGGESTIONS = By.xpath(".//h4[text()='To']/parent::div//div[text()='Suggestions']/parent::ul//p");
	private final By LOC_BT_SEARCH_FLIGHTS = By.xpath(".//button[text()='Search flights']");
	private final By LOC_BT_BOOK_BUTTON = By.xpath(".//button[text()='Book']");
	
	public FlightBooking(SeleniumSEPTest test) {

		driver = test.getDriver();
		this.test = test;
		
		reportManager = test.getReportManager();
		
		if(WaitUtils.waitForElementPresent(test, LOC_WL_FLIGHT_SEARCH_PAGE, 10, "Flight search page is not present") &&
				WaitUtils.waitForElementVisible(test, LOC_WL_FLIGHT_SEARCH_PAGE, 10, "Flight search page is not visible")) {
			reportManager.reportPassed("Flight Booking Page", "Flight Booking page is displayed");
		}
		else {
			reportManager.fail("Flight Booking page is not displayed");
		}
	}

	public enum tripType{
		ONE_WAY("One way"),
		ROUND_TRIP("Round trip");

		private String tripType;
		private tripType(String tripType) {
			this.tripType = tripType;
		}

		public String getTripType() {
			return tripType;
		}

	}

	public void selectTripType(tripType tripType) {

		Object[] type = {tripType.getTripType()};
		ClickUtils.clickButtonOrFail(test, By.xpath(LOC_RB_TRIP_TYPE.format(type)), "Unable to click on the trip type radio button");
	}

	public void fillRoundTripDetails(String fromCity, String toCity, Date[] dates, String noOfAdults) {
		fillFromCity(fromCity);
		fillToCity(toCity);
		selectDatesRoundTrip(dates);
		fillNoOfAdults(noOfAdults);
	}

	public void fillFromCity(String fromCity) {
		FillUtils.fillInputOrFail(test, LOC_IN_FROM_FLIGHT_SEARCH, fromCity, "Unable to fill the from city");
		WaitUtils.waitForElementVisible(test, LOC_WL_FROM_SUGGESTIONS, 2, "From Suggestions not displayed");
		ClickUtils.clickButtonOrFail(test, LOC_WL_FROM_SUGGESTIONS, "Unable to click on the from city suggestions");
	}

	public void fillToCity(String toCity) {
		FillUtils.fillInputOrFail(test, LOC_IN_TO_FLIGHT_SEARCH, toCity, "Unable to fill the to city");
		WaitUtils.waitForElementVisible(test, LOC_WL_TO_SUGGESTIONS, 2, "To Suggestions not displayed");
		ClickUtils.clickButtonOrFail(test, LOC_WL_TO_SUGGESTIONS, "Unable to click on the to city suggestions");
	}

	public void selectDatesRoundTrip(Date[] dates) {

		ClickUtils.clickButtonOrFail(test, LOC_CL_CALENDARS, "Unable to click on the date field");

		WaitUtils.waitForElementVisible(test, LOC_WL_MONTH_CALENDER, 2, "Calendar is not displyed");

		for(int i = 0; i < dates.length; i++) {
			selectDate(dates[i]);
		}
	}

	public void fillNoOfAdults(String noOfAdults) {
		FillUtils.fillSelectByVisibleText(test, LOC_DD_ADULTS, noOfAdults, "Unable to select the adults");
	}

	public void selectDate(Date date) {
		WebElement month = CheckUtils.getElement(test, LOC_WL_MONTH_CALENDER);

		int iDay = DateUtils.returnDay(date);
		String sMonthName = DateUtils.returnMonth(date);
		int iYear = DateUtils.returnYear(date);

		WebElement monthName = month.findElement(LOC_WL_MONTH_NAME);
		if(monthName != null) {
			if(monthName.getText().contains(sMonthName + " " + iYear)) {
				List<WebElement> days = CheckUtils.getElements(test, LOC_WL_DAYS_IN_CALENDAR);
				for(WebElement day : days) {
					if(day.getText().equals("" + iDay)) {
						ClickUtils.clickButtonOrFail(test, day, "Unable to select the day");
						break;
					}
				}
			}
			else {
				ClickUtils.clickButtonOrFail(test, LOC_WL_RIGHT_ARROW_DATE_PICKER, "Unable to click on the right arrow");
				WaitUtils.waitForElementVisible(test, LOC_WL_MONTH_CALENDER, 5, "Calendar is not displyed");
				if(iYear >= Integer.parseInt(monthName.getText().split(" ")[1])) {
					selectDate(date);
				}
			}
		}
		else {
			reportManager.fail("Month name is not present");
		}
	}

	public void clickSearchFlights() {
		WaitUtils.waitForElementVisible(test, LOC_BT_SEARCH_FLIGHTS, 5, "Search Flights button is not visible");
		ClickUtils.clickButtonOrFail(test, LOC_BT_SEARCH_FLIGHTS, "Unable to click on the search flights button");
		WaitUtils.waitForElementPresent(test, LOC_BT_BOOK_BUTTON, 15, "Book button not present");
	}

	public void clickBookButton() {
		ClickUtils.clickButtonOrFail(test, LOC_BT_BOOK_BUTTON, "Unable to click on book button");
	}

}
