package com.identity.etoe.fileservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileInfoService {
	private List<FileInfo> listAllFilesInfo = new ArrayList<FileInfo>();
	private List<FileInfo> listSupportedFilesInfo = new ArrayList<FileInfo>();

	/**
	 * 
	 * Read All the files under the folder strFolderPath with name, size, mime-type and extension info
	 * And set listAllFilesInfo with all files FileInfo metadata
	 * And set listSupportedFilesInfo with supported files FileInfo metadata
	 */
	public FileInfoService(String strFolderPath, String strSupportedMimeTypes) {
		File fileFolder = new File(strFolderPath);
		for (File eachFile : fileFolder.listFiles()) {
			if (eachFile.isFile()) {
				try {
					FileInfo fileInfo = new FileInfo();
					String strFileName = eachFile.getName();
					String strMimeType = Files.probeContentType(eachFile
							.toPath());
					fileInfo.setStrFilename(strFileName);
					fileInfo.setLongFilesize(eachFile.length());
					fileInfo.setStrFileMimeType(strMimeType);
					fileInfo.setStrFileExt(strFileName.substring(strFileName
							.lastIndexOf(".") + 1));
					listAllFilesInfo.add(fileInfo);
					String[] supportedMimeTypes = strSupportedMimeTypes
							.split(",");
					for (int i = 0; i < supportedMimeTypes.length; i++)
						if (strMimeType != null){
						if (strMimeType.contains(supportedMimeTypes[i])) {
							listSupportedFilesInfo.add(fileInfo);
						}
						}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	/**
	 * 
	 * return list of supported files with the FileInfo metadata
	 * 
	 */
	public List<FileInfo> getAllFiles() {

		return listAllFilesInfo;
	}
	
	/**
	 * 
	 * return list of supported files with the FileInfo metadata
	 * 
	 */
	
	public List<FileInfo> getSupportedFiles() {
		return listSupportedFilesInfo;
	}
	
	
	/**
	 * 
	 * For Testing This service layer works
	 * 
	 */
	
/*	public static void main(String[] args) {
		FileInfoService fileInfoService;
		fileInfoService = new FileInfoService(
				"C:\\Users\\SureshBabu\\Downloads", "excel,cvs");

		List<FileInfo> filesinfo = fileInfoService.getSupportedFiles();
		for (FileInfo info : filesinfo) {
			System.out.println(info.getStrFileMimeType() + "-----------"
					+ info.getStrFilename());
		}
		List<FileInfo> filesinfoall = fileInfoService.getAllFiles();
		for (FileInfo info : filesinfoall) {
			System.out.println(info.getStrFileMimeType() + "********"
					+ info.getStrFilename());
		}
	}*/
	
	
}
