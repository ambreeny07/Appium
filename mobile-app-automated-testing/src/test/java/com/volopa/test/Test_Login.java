package com.volopa.test;

import java.net.MalformedURLException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.volopa.base.BaseClass;
import com.volopa.base.PropertiesReader;
import com.volopa.errorCollectors.ErrorCollector;
import com.volopa.listeners.ExtentListeners;
import com.volopa.pages.LoginPage;

public class Test_Login extends BaseClass {
	LoginPage loginPage;

	@Test(priority = 1)
	public void LoginWithValidCredentials() throws InterruptedException {

		initReport();
		initConfiguration();
		LoginPage loginPage = new LoginPage();

		String email = PropertiesReader.getPropertyValue("Email");
		String password = PropertiesReader.getPropertyValue("Password");

		int step = 1;
		
		ErrorCollector.extentLogInfo("Step " + step + " : Navigating to application");
		step++;
		
		ErrorCollector.extentLogInfo("Step " + step + " : Entering the Email:"+email);
		loginPage.enterEmail(email);
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Entering the Passowrd:"+password);
		loginPage.enterPassword(password);
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Click On <b>'Sign In'</b>");
		loginPage.clickOnSignInButton();
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Verify 'User is Successfully Logged In'");
		ErrorCollector.verifyTrue(loginPage.isDashboardTitleDisplaying(),"Verified: 'User is Successfully Logged In'");
		step++;
		ErrorCollector.extentLogInfo("Step " + step + " : Closing the Application");
	}
	
	
	@Test(priority = 2)
	public void LoginWithInvalidCredentials() throws InterruptedException {

		initReport();
		initConfiguration();
		LoginPage loginPage = new LoginPage();

		String email = PropertiesReader.getPropertyValue("WrongEmail");
		String password = PropertiesReader.getPropertyValue("WrongPassword");

		int step = 1;
		ErrorCollector.extentLogInfo("Step " + step + " : Navigating to application");
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Entering the Email:"+email);
		loginPage.enterEmail(email);
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Entering the Passowrd:"+password);
		loginPage.enterPassword(password);
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Click On <b>'Sign In'</b>");
		loginPage.clickOnSignInButton();
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Verify 'Login Failed Alert Message is displaying'");
		ErrorCollector.verifyTrue(loginPage.isErrorMessageFailed(),"Verified: 'Login Failed Alert Message is displaying'");
		step++;
		
		ErrorCollector.extentLogInfo("Step " + step + " : Closing the Application");
	}
	
	@Test(priority = 3)
	public void VerifyErrorMessagesWithEmptyFiels() throws InterruptedException {

		initReport();
		initConfiguration();
		LoginPage loginPage = new LoginPage();

		String email = PropertiesReader.getPropertyValue("Email");
		String password = PropertiesReader.getPropertyValue("Password");

		int step = 1;
		
		ErrorCollector.extentLogInfo("Step " + step + " : Navigating to application");
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Click On <b>'Sign In'</b>");
		loginPage.clickOnSignInButton();
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Verify 'Login Failed Alert Message is displaying'");
		ErrorCollector.verifyTrue(loginPage.isErrorMessageFailed(),"Verified: 'Login Failed Alert Message is displaying'");
		step++;
		
		ErrorCollector.extentLogInfo("Step " + step + " : Closing the Application");
	}


	
}
