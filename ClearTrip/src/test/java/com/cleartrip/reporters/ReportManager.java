package com.cleartrip.reporters;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.cleartrip.common.SeleniumSEPTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportManager {

	public int imageNo = 1;

	protected SeleniumSEPTest seleniumTest = null;

	private ExtentTest extentTest;

	private WebDriver driver;

	private String testName;

	private SoftAssert sAssert;

	private String imagePath;

	private String imageFolderPath;

	/**
	 * Constructor
	 */
	public ReportManager(SeleniumSEPTest test) {
		super();

		seleniumTest = test;
		driver = test.getDriver();
		extentTest = test.getExtentTest();
		testName = test.getTestClassName();
		sAssert = test.getsAssert();
	}

	/**
	 * Method to report a passed event
	 * @param title the title
	 * @param description the description
	 */
	public void reportPassed(String title, String description) {

		imagePath = takeScreenShot();
		imageFolderPath = extentTest.addScreenCapture(imagePath);

		Reporter.log(description);
		extentTest.log(LogStatus.PASS, description, imageFolderPath);

		sAssert.assertTrue(true, description);
	}

	/**
	 * Method to report a failed event
	 * @param title the title
	 * @param description the description
	 */
	public void reportFailed(String title, String description) {
		imagePath = takeScreenShot();
		imageFolderPath = extentTest.addScreenCapture(imagePath);

		Reporter.log(description);
		extentTest.log(LogStatus.FAIL, description, imageFolderPath);

		sAssert.assertTrue(false, description);
	}
	
	/**
	 * Method to report a failed event
	 * @param description the description
	 */
	public void fail(String description) {
		imagePath = takeScreenShot();
		imageFolderPath = extentTest.addScreenCapture(imagePath);

		Reporter.log(description);
		extentTest.log(LogStatus.FAIL, description, imageFolderPath);

		Assert.fail(description);
	}

	/**
	 * Method to capture the screenshot and return the screenshot path
	 * @return
	 */
	public String takeScreenShot() {

		String projectPath = System.getProperty("user.dir");
		
		String strImageNo = "";
		try{
			strImageNo = String.format("%03d", imageNo);  // 0009   
			String screenShotPath = projectPath + "/screenshots/" + testName + "/" + strImageNo + ".png";
			TakesScreenshot screenshot = (TakesScreenshot) driver; 
			File screenShotFile = screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShotFile, new File(screenShotPath));
			this.imageNo++;

			return screenShotPath;
		}catch (Exception e) {
			System.out.println("Screenshot is not captured : " + e.getClass().getName());
			return "";
		}
	}


}
