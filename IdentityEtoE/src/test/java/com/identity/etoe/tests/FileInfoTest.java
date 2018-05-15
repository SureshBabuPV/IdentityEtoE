package com.identity.etoe.tests;

import java.util.Hashtable;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.identity.etoe.basetest.BaseTest;
import com.identity.etoe.fileservice.FileInfo;
import com.identity.etoe.fileservice.FileInfoService;
import com.relevantcodes.extentreports.LogStatus;

public class FileInfoTest extends BaseTest {
	
	@Test(testName = "FileInfoTest")
	public void fileInfoTest() throws Exception {
		// Starting Test for reportse
		strTestName = "FileInfoTest";
		testLog = extReport.startTest(strTestName);
		testLog.log(LogStatus.INFO, "File Information Test Started");
		FileInfoService fileInfoService;
		String targetFolder = System.getProperty("user.dir") + "\\testdata";
		System.out.println(targetFolder);
		fileInfoService = new FileInfoService(
				targetFolder, "excel,cvs");
		testLog.log(LogStatus.INFO, "File Information for <b>supported </b> file types <b>excel and cvs</b>");
		List<FileInfo> filesinfo = fileInfoService.getSupportedFiles();
		for (FileInfo info : filesinfo) {
			testLog.log(LogStatus.INFO, "File Name: " + info.getStrFilename()
					+ "<br> File Mime Type :" + info.getStrFileMimeType()
					+ "<br> File Size in bytes :" + info.getLongFilesize()
					+ "<br> File Extenction :" + info.getStrFileExt());
		}
		
		testLog.log(LogStatus.INFO, "File Information for <b> All </b> files");
		List<FileInfo> filesinfoall = fileInfoService.getAllFiles();
		for (FileInfo info : filesinfoall) {
			testLog.log(LogStatus.INFO, "File Name: " + info.getStrFilename()
					+ "<br> File Mime Type :" + info.getStrFileMimeType()
					+ "<br> File Size in bytes :" + info.getLongFilesize()
					+ "<br> File Extenction :" + info.getStrFileExt());
		}
	}
	@AfterMethod
	public void aftermethod() throws Exception {

		// Ending Test for reports
		if (extReport != null) {
			extReport.endTest(testLog);
			extReport.flush();
		}

	}
}
