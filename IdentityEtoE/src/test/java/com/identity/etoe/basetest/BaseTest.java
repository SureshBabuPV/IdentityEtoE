package com.identity.etoe.basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import com.identity.etoe.excelutils.XlsReader;
import com.identity.etoe.extentreports.ExtentManager;
import com.identity.etoe.fileservice.FileInfo;
import com.identity.etoe.fileservice.FileInfoService;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	public Properties config_prop = null;
	public XlsReader test_data = null;
	public String testcase_name;
	public Object dataHashTable[][] = null;
	public WebDriver driver;
	public SoftAssert softAssertion;
	public ExtentReports extReport = ExtentManager.getInstance();
	public ExtentTest testLog;
	public ITestResult result;
	public String strTestName;

    /**
     * Reads configuration properties from config/configuration.properties file 
     */

	public void getConfigProperties() throws IOException {
		// Reading Property files
		File file = new File(System.getProperty("user.dir")
				+ "\\config\\Configuration.properties");
		FileInputStream fileis = new FileInputStream(file);
		config_prop = new Properties();
		config_prop.load(fileis);
		fileis.close();
	}

    /**
     * To Take screen shots and store them in reports folder with current date and time stamp 
     */
	public void takeScreenShot() throws IOException {
		String strScreenShotPath= System.getProperty("user.dir")+ "\\reports\\" + ExtentManager.folderName ;
		Date d=new Date();
		String imgFileName =d.toString().replace(":", "_").replace(" ", "_");
		String imagePath = strScreenShotPath + "\\"	+ imgFileName + ".jpg";
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,
				new File(strScreenShotPath + "\\"
						+ imgFileName + ".jpg"));
		testLog.log(LogStatus.INFO, testLog.addScreenCapture(imagePath));
	}
	
	 /**
     * Reads vehicle Reg number from all excel files located under testdata and make a two dimensional array of hash map as a dataprovider
     * for reading test data from excel using FileInfoService been
     */
	
	public void readTestDatFromXLS() {
		int len, colcount, rowcount, totalrowcount =0, i, j,k=0;

		
		String testDataFolderPath= System.getProperty("user.dir") + config_prop.getProperty("testdat_path");
		String supportedFiles= config_prop.getProperty("supported_files_selenium");
		
		FileInfoService fileInfoService;
		fileInfoService = new FileInfoService(
				testDataFolderPath, supportedFiles);

		List<FileInfo> filesinfo = fileInfoService.getSupportedFiles();
		for (FileInfo info : filesinfo) {
			test_data = new XlsReader(testDataFolderPath + "\\" + info.getStrFilename());
			if(info.getStrFileExt().toLowerCase().contains("csv")){
				testLog.log(LogStatus.INFO, "Ignoring file CSV ");
			}
			else
			{
			rowcount = test_data.getRowCount("Data");
			totalrowcount= totalrowcount+rowcount-1;
			}
		}

		dataHashTable = new Object[totalrowcount][1];
		for (FileInfo info : filesinfo) {
		
			test_data = new XlsReader(testDataFolderPath + "\\" + info.getStrFilename());
			colcount = test_data.getColumnCount("Data");
			rowcount = test_data.getRowCount("Data");
			for (i = 2; i <=rowcount; i++) {
				Hashtable<String, String> tempHashTable = new Hashtable<String, String>();
				for (j = 0; j < colcount; j++) {
					tempHashTable.put(test_data.getCellData("Data", j, 1),
							test_data.getCellData("Data", j, i));
				}
				dataHashTable[k][0] = tempHashTable;
				k++;
			}
		}
		
}

	 /**
     * To Open chrome browser
     * 
     */
	public void openBrowser() throws Exception {

		// To open browser currently implemented only chrome.
		if (config_prop.getProperty("browser").equals("chrome")) {
			System.setProperty(
					"webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ config_prop.getProperty("drivers_path")
							+ "\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else {
			throw new Exception("This Browser Type Not Implemented");
		}

	}

}
