package com.volopa.listeners;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.velocity.util.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.json.JSONObject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.volopa.base.BaseClass;
import com.volopa.utilities.ModelTests;
import com.volopa.utilities.ReportUtils;
import com.volopa.utilities.SendEmail;
import com.volopa.utilities.SlackUtils;
import com.volopa.utilities.TestsDataConverter;
import com.volopa.utilities.ZipUtils;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.http.client.methods.*;
import org.apache.http.util.EntityUtils;
import org.apache.commons.configuration2.builder.fluent.Parameters;


public class ExtentListeners extends BaseClass implements ITestListener,ISuiteListener {
	static String fileName = "Android Automation Test Report.html";
	static Map<Long,ExtentTest> extentTestMap = new HashMap<Long,ExtentTest>();
	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"/reports/"+fileName);
	public static String currentDateString;
	public static String currentTestMethodName = "Test";
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		currentTestMethodName = result.getMethod().getMethodName();
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		extentTestMap.put(Thread.currentThread().getId(), test);
		testReport.set(test); 
	}
	
	public void onTestSuccess(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";
		try {

			ExtentManager.captureScreenshot();
			testReport.get().pass("<b>" + "<font color=" + "green>" + "Screenshot of Test Pass" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
							.build());
		} catch (IOException e) {

		}

		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		if (BaseClass.driver!=null) {
			driver.quit();
		}
	}

	public void onTestFailure(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		try {
			ExtentManager.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
							.build());
		} catch (IOException e) {
		}
		String failureLogg="TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
		if (BaseClass.driver!=null) {
			driver.quit();
		}
	}

	public void onTestSkipped(ITestResult result) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor)BaseClass.getDriver();
			jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \""+"TEST CASE:- "+ result.getMethod().getMethodName().toUpperCase()+ " PASSED"+"\"}}");
			if (BaseClass.driver!=null) {
				driver.quit();
			}
		} catch (Exception e) {
		}
		extent.removeTest(getTest());
		String methodName=result.getMethod().getMethodName();
		updateTestData(testSuiteRunnerFileName,testSuiteRunnerSheetName, methodName, "Skipped");
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}
	
	public static ExtentTest getTest() {
		return (ExtentTest)extentTestMap.get((Thread.currentThread().getId()));
	}
	
	public static void captureErrorAndScreenshot(Throwable result) {
		String excepionMessage=Arrays.toString(result.getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		try {

			ExtentManager.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
							.build());
		} catch (IOException e) {

		}
	}

	@Override
	public void onStart(ISuite suite) {
		
	}

	@Override
	public void onFinish(ISuite suite) {

			ZipUtils.generateZipFile();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}


		try {
			Map<String, ISuiteResult> getResults = suite.getResults();
			ISuiteResult iSuiteResult = getResults.get(getResults.keySet().toArray()[0]);
			ITestContext iTestContext = iSuiteResult.getTestContext();
			String nameString = iTestContext.getName();
			int pass = iTestContext.getPassedTests().size();
			int fail = iTestContext.getFailedTests().size();
			int skip = iTestContext.getSkippedTests().size();
			//Failed Test Cases
	 		Object[]FailedTest =  iTestContext.getFailedTests().getAllMethods().toArray() ;
	 		String FailedTestCases =  "-------------------------------------\nFailed Test Cases: ";
	 		for(int i = 0;i<FailedTest.length;i++) {
	 			String ClassName = FailedTest[i].toString().split("\\(")[0];
	 			String MethodName = ClassName.toString().split("\\.")[1];
	 			FailedTestCases = FailedTestCases+"\n-> "+MethodName;
	 		}
	 		FailedTestCases = FailedTestCases+"\n-------------------------------------\n";
	 		
	 		//Passed Test Cases
	 		Object[]PassedTest =  iTestContext.getPassedTests().getAllMethods().toArray() ;
	 		String PassedTestCases =  "-------------------------------------\nPassed Test Cases: ";
	 		for(int i = 0;i<PassedTest.length;i++) {
	 			String ClassName = PassedTest[i].toString().split("\\(")[0];
	 			String MethodName = ClassName.toString().split("\\.")[1];
	 			PassedTestCases = PassedTestCases+"\n-> "+MethodName;
	 		}
	 		PassedTestCases = PassedTestCases+"\n-------------------------------------\n";
	 		
	 		int total = pass+fail+skip;
	 		String emailBody = "=============================================================\n"+
	 							nameString+"\n"+
	 							"Tests Run: "+total+", Passed: "+pass+", Failures: "+fail+", Skipped: "+skip+"\n"+FailedTestCases+"\n"+PassedTestCases+"\n"+
	 							"=============================================================\n";
	 		System.out.print(emailBody);	
		}catch(Exception e) {
			printString("Unable to send Email: "+e.getMessage());
		}
	}

	public static void attachScreenShot(String name) {
		try {

			ExtentManager.captureScreenshot();
			testReport.get().pass("<b>" + "<font color=" + "green>" + name + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
							.build());
		} catch (IOException e) {

		}
	}
	
}
