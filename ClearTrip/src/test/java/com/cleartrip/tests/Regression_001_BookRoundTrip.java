package com.cleartrip.tests;

import java.util.Date;

import org.testng.annotations.Test;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.pages.FlightBooking;
import com.cleartrip.pages.FlightBooking.tripType;
import com.cleartrip.pages.TopPanel;
import com.cleartrip.utils.DateUtils;

public class Regression_001_BookRoundTrip extends SeleniumSEPTest{

	@Test(testName = "NRE_001_BookRoundTrip")
	public void NRE_001_BookRoundTrip_test() throws InterruptedException{

		TopPanel homePage = new TopPanel(this);
		homePage.actionSignIn();
		
		Date departureDate = DateUtils.returnDate(10);
		Date arrivalDate = DateUtils.returnDate(15);
		Date[] dates = {departureDate, arrivalDate};
		
		FlightBooking flightBooking = new FlightBooking(this);
		flightBooking.selectTripType(tripType.ROUND_TRIP);
		flightBooking.fillRoundTripDetails("BLR", "DEL", dates, 1);

	}

}
