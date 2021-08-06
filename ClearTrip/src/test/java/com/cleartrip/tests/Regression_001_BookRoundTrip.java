package com.cleartrip.tests;

import org.testng.annotations.Test;

import com.cleartrip.common.SeleniumSEPTest;
import com.cleartrip.pages.TopPanel;

public class Regression_001_BookRoundTrip extends SeleniumSEPTest{

	@Test(testName = "NRE_001_BookRoundTrip")
	public void NRE_001_BookRoundTrip_test() throws InterruptedException{

		TopPanel homePage = new TopPanel(this);
		homePage.actionSignIn();

	}

}
