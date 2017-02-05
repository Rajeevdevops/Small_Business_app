package com.lws.model;

import java.io.File;

public class PurchaseFilesModel {
	private String directory = "D:\\Application\\Purchase";
	private String fileName;
	private File file;
	
	public PurchaseFilesModel() {
		
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	
	

}
