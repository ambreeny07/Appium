package com.volopa.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.volopa.base.BaseClass;


public class Utilities extends BaseClass {

	public static String screenshotPath;
	public static String screenshotName;
	

	public static String removeDollarandSpaces(String amount) {
		printString("Amount :  " + amount);
		if(amount.contains("$")) {
			amount = amount.replace("$", "").trim();
			printString("Remove Dollar :  " + amount);			
		}
		if(amount.contains(",")) {
			amount = amount.replace(",", "").trim();
			printString("Remove Dollar :  " + amount);						
		}
		
		return amount.trim();
	}
	
	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "/target/surefire-reports/html/" + screenshotName));

	}

	private static String[][] convertToArray(List<List<Object>> data) {
	    String[][] array = new String[data.size()][];

	    int i = 0;
	    for (List<Object> row : data) {
	        array[i++] = row.toArray(new String[row.size()]);
	    }
	    return array;
	}
	
	
	public static boolean isTestRunnable(String testName, ExcelReader excel){
		
		String sheetName="test_suite";
		int rows = excel.getRowCount(sheetName);
		
		
		for(int rNum=2; rNum<=rows; rNum++){
			
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			
			if(testCase.equalsIgnoreCase(testName)){
				
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				
				if(runmode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
			
			
		}
		return false;
	}
	
}
