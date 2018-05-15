package com.identity.etoe.tests;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.identity.etoe.basetest.BaseTest;
import com.identity.etoe.pages.VehicleRegDetailsPage;
import com.relevantcodes.extentreports.LogStatus;

public class VehicleRegDetailsTest extends BaseTest {
	VehicleRegDetailsPage vehicleRegDetailsPage;
    /**
     * Reads vehicle Reg number from an excel file located under testdata and verifies the make and colour
     * and takes a screen shot for each vehicle and stores them in reports folder with current date and time stamp
     * report will be created under reports folder with current date and time stamp
     * @see getData for data provider
     */
	@Test(testName = "VehicleRegDetailsTest", dataProvider = "getData")
	public void vehicleRegDetailsTest(Hashtable<String, String> testData)
			throws Exception {
		// Starting Test for reports
		strTestName = "VehicleRegDetailsTest";
		testLog = extReport.startTest(strTestName);
		testLog.log(LogStatus.INFO, "Vehicle RegDetails Test Started");
		openBrowser(); // open chrome browser

		vehicleRegDetailsPage = new VehicleRegDetailsPage(driver, testLog);

		driver.get(config_prop.getProperty("url"));
		testLog.log(LogStatus.INFO,
				"Navigated to :" + config_prop.getProperty("url"));
		vehicleRegDetailsPage.clickOn_StartNow();
		vehicleRegDetailsPage.enter_VehicleRegNo(testData.get("RegNo"));
		vehicleRegDetailsPage.clickOn_Continue();
		vehicleRegDetailsPage.verifyColor(testData.get("Color"));
		vehicleRegDetailsPage.verifyMake(testData.get("Make"));
		takeScreenShot();
	}

    /**
     * Closing the browser and flush report logs using extent reports
     */

	@AfterMethod
	public void aftermethod() throws Exception {

		// Ending Test for reports
		if (extReport != null) {
			extReport.endTest(testLog);
			extReport.flush();
		}
		// Closing Browser
		if (driver != null)
			driver.quit();
	}

    /**
     * Pushing all soft asserts in case any
     */
	@AfterClass
	public void afterTest() {
		softAssertion.assertAll();
	}

	
    /**
     * Reads vehicle Reg number from all excel files located under testdata and verifies the make and colour
     * @see BaseTest for reading test data from excel using FileInfoService been
     */
	
	@DataProvider(name = "getData")
	public Object[][] getData(ITestContext context) throws IOException {
		softAssertion = new SoftAssert();
		getConfigProperties(); // Initialising Configuration properties
		readTestDatFromXLS(); // Initialising Test Case Data using xls
		return dataHashTable;
	}
}
