package com.identity.etoe.fileservice;

public class FileInfo {
	private String strFilename;
	private String strFileMimeType;
	private long longFilesize;
	private String strFileExt;
	/**
	 * @return the strFilename
	 */
	public String getStrFilename() {
		return strFilename;
	}
	/**
	 * @param strFilename the strFilename to set
	 */
	public void setStrFilename(String strFilename) {
		this.strFilename = strFilename;
	}
	/**
	 * @return the strFileMimeType
	 */
	public String getStrFileMimeType() {
		return strFileMimeType;
	}
	/**
	 * @param strFileMimeType the strFileMimeType to set
	 */
	public void setStrFileMimeType(String strFileMimeType) {
		this.strFileMimeType = strFileMimeType;
	}
	/**
	 * @return the strFilesize
	 */
	public long getLongFilesize() {
		return longFilesize;
	}
	/**
	 * @param strFilesize the strFilesize to set
	 */
	public void setLongFilesize(long longFilesize) {
		this.longFilesize = longFilesize;
	}
	/**
	 * @return the strFileExt
	 */
	public String getStrFileExt() {
		return strFileExt;
	}
	/**
	 * @param strFileExt the strFileExt to set
	 */
	public void setStrFileExt(String strFileExt) {
		this.strFileExt = strFileExt;
	}

}
