package com.cleartrip.tests;

import java.util.Date;
import java.util.Set;

import org.testng.annotations.Test;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.pages.FlightBooking;
import com.cleartrip.pages.FlightBooking.tripType;
import com.cleartrip.pages.Payment;
import com.cleartrip.pages.ReviewItinerary;
import com.cleartrip.pages.TopPanel;
import com.cleartrip.utils.DateUtils;

public class Regression_001_BookRoundTrip extends SeleniumSEPTest{

	@Test
	public void NRE_001_BookRoundTrip_test() {

		TopPanel homePage = new TopPanel(this);
		homePage.actionSignIn();
		
		Date departureDate = DateUtils.returnDate(10);
		Date arrivalDate = DateUtils.returnDate(15);
		Date[] dates = {departureDate, arrivalDate};
		
		FlightBooking flightBooking = new FlightBooking(this);
		flightBooking.selectTripType(tripType.ROUND_TRIP);
		flightBooking.fillRoundTripDetails("BLR", "DEL", dates, "1");
		
		flightBooking.clickSearchFlights();

		String parentWindow = driver.getWindowHandle();

		flightBooking.clickBookButton();
		Set<String> windows = driver.getWindowHandles();
		for(String window : windows) {
			if(!window.equalsIgnoreCase(parentWindow)) {
				driver.switchTo().window(window);
			}
		}
		
		ReviewItinerary reviewItinerary = new ReviewItinerary(this);
		reviewItinerary.selectFares("Standard fare");
		reviewItinerary.clickContinueButton();
		reviewItinerary.clickStepThisStepButton();
		reviewItinerary.enterMobileNumber("8197696308");
		reviewItinerary.clickContinueButton();
		reviewItinerary.fillTravellerDetails("Sanjay", "Ceekala", "Male");
		reviewItinerary.clickContinueToPaymentButton();

		new Payment(this);

		driver.switchTo().window(parentWindow);
	}

}
