package com.identity.etoe.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class VehicleRegDetailsPage {
	WebDriver driver;
	public ExtentTest testLog;
	public String strPageName = "VehicleRegDetailsPage : ";

	// Declaring all web elements in VehicleRegDetailsPage page
	@FindBy(xpath = "//a[@class='gem-c-button gem-c-button--start']")
	private WebElement btn_StartNow;
	
	@FindBy(xpath = "//input[@name='Vrm']")
	private WebElement txt_VehicleRegNo;
	
	@FindBy(xpath = "//button[@name='Continue']")
	private WebElement btn_Continue;
	
	@FindBy(xpath = "//div[@id='pr3']/div/ul/li[2]/span[2]/strong")
	private WebElement lab_Make;
	
	@FindBy(xpath = "//div[@id='pr3']/div/ul/li[3]/span[2]/strong")
	private WebElement lab_Color;
	
	public VehicleRegDetailsPage(WebDriver driver, ExtentTest testLog) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.testLog = testLog;
	}

	public void enter_VehicleRegNo(String strVehicleRegNo) {
		txt_VehicleRegNo.sendKeys(strVehicleRegNo);
		testLog.log(LogStatus.INFO, strPageName
				+ "Entered Vehicle Registration number as :" + strVehicleRegNo);
	}

	public void clickOn_StartNow() {
		btn_StartNow.click();
		testLog.log(LogStatus.INFO, strPageName + "Clicked on Start Now ");
	}
	
	public void clickOn_Continue() {
		btn_Continue.click();
		testLog.log(LogStatus.INFO, strPageName + "Clicked on Continue ");
	}
	public void verifyMake(String strExpMake) {
		String strActMake = lab_Make.getText();

		strExpMake =strExpMake.toLowerCase().trim();
		testLog.log(LogStatus.INFO, strPageName + "Verifying Make of Vehicle : ");
		strActMake= strActMake.trim().toLowerCase();

		if (strActMake.contains(strExpMake)) {
			testLog.log(
					LogStatus.INFO,
					strPageName
							+ " Make of Vehicle is displayed as: "
							+ lab_Make.getText() + "; Expected :"
							+ strExpMake);
		} else {
			testLog.log(
					LogStatus.ERROR,
					strPageName
							+ " Make of Vehicle is displayed as:"
							+ lab_Make.getText() + "; Expected :"
							+ strExpMake);
		}

	}
	public void verifyColor(String strExpColor) {
		String strActColor = lab_Color.getText();

		strExpColor =strExpColor.toLowerCase().trim();
		testLog.log(LogStatus.INFO, strPageName + "Verifying Color of Vehicle : ");
		strActColor= strActColor.trim().toLowerCase();

		if (strActColor.contains(strExpColor)) {
			testLog.log(
					LogStatus.INFO,
					strPageName
							+ " Color of Vehicle is displayed as: "
							+ lab_Color.getText() + "; Expected :"
							+ strExpColor);
		} else {
			testLog.log(
					LogStatus.ERROR,
					strPageName
							+ " Color of Vehicle  is displayed as: "
							+ lab_Color.getText() + "; Expected :"
							+ strExpColor);
		}

	}
}
