package com.mljr.ocr.util;

import java.util.ResourceBundle;

public class Config {
	private static ResourceBundle resource = ResourceBundle.getBundle("application");
	
	public static String imageUrl = resource.getString("imageUrl");
	public static String imageTmpPath = resource.getString("imageTmpPath");
	public static String tessdataPath = resource.getString("tessdataPath");
	public static String watermarkImagePath = resource.getString("watermarkImagePath");
	
}
