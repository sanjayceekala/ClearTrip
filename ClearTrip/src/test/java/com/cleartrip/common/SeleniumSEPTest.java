package com.cleartrip.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SeleniumSEPTest {

	public final String URL = "http://www.cleartrip.com";

	protected WebDriver driver;

	protected ExtentReports extentReport;

	protected ExtentTest extentTest;

	private String testClassName;

	protected SoftAssert sAssert;

	public SeleniumSEPTest() {
		super();

		testClassName = this.getClass().getSimpleName();
		extentReport = new ExtentReports("./ExtentReports/" + testClassName + ".html", true);
		extentTest = extentReport.startTest(testClassName);
		sAssert = new SoftAssert();

		intializeDriver();
	}

	public void login() {

		driver.get(URL);
		driver.manage().window().maximize();
	}

	public void intializeDriver() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();

		//		System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
		//		driver = new FirefoxDriver();
	}


	@BeforeTest
	public void setUp() {

		login();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public ExtentReports getExtentReport() {
		return extentReport;
	}

	public ExtentTest getExtentTest() {
		return extentTest;
	}

	public String getTestClassName() {
		return testClassName;
	}

	public SoftAssert getsAssert() {
		return sAssert;
	}


	@AfterTest
	public void tearDown() {

		driver.quit();

		extentReport.endTest(extentTest);
		extentReport.flush();

		if(extentTest.getRunStatus() == LogStatus.FAIL)
			Assert.fail("Test Failed : One or more steps failed");

	}

}
