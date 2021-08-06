package com.cleartrip.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.cleartrip.reporters.ReportManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SeleniumSEPTest {

	public final String URL = "http://www.cleartrip.com";

	protected WebDriver driver;

	protected ReportManager reportManager;

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
		reportManager = new ReportManager(this);
	}

	public void login() {

		driver.get(URL);
		driver.manage().window().maximize();
	}


	@BeforeTest
	public void setUp() {

		login();
	}

	public void intializeDriver() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();

		//		System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
		//		driver = new FirefoxDriver();
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

	public ReportManager getReportManager() {
		return reportManager;

	}



	@AfterTest
	public void tearDown() {

		driver.quit();

		extentReport.endTest(extentTest);
		extentReport.flush();

		if(extentTest.getRunStatus() == LogStatus.FAIL)
			Assert.fail("Test Failed : One or more steps failed");

		sAssert.assertAll();
	}

}
