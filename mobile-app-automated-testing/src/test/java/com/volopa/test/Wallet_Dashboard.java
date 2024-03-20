package com.volopa.test;

import java.net.MalformedURLException;
import java.text.DecimalFormat;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.volopa.base.BaseClass;
import com.volopa.base.PropertiesReader;
import com.volopa.errorCollectors.ErrorCollector;
import com.volopa.listeners.ExtentListeners;
import com.volopa.pages.LoginPage;
import com.volopa.pages.WalletDashboardPage;

public class Wallet_Dashboard extends BaseClass {
	LoginPage loginPage;

	@Test(priority = 1)
	public void VerifyBalanceOnWalletDashboard() throws InterruptedException {

		initReport();
		initConfiguration();
		LoginPage loginPage = new LoginPage();
		WalletDashboardPage walletDashboardPage = new WalletDashboardPage();

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
		waitfor3Sec();
		scrollToText("Balances");
		ErrorCollector.extentLogInfo("Step " + step + " : Verify 'Balance Section' is Displaying");
		ErrorCollector.verifyTrue(walletDashboardPage.isBalanceSectionDisplaying(),"Verified: 'Balance Section' is Displaying");
		step++;
		
		ErrorCollector.extentLogInfo("Step " + step + " : Closing the Application");
	}
	
	
	@Test(priority = 2)
	public void VerifyRecentFundingHistoryOnDashboard() throws InterruptedException {

		initReport();
		initConfiguration();
		LoginPage loginPage = new LoginPage();
		WalletDashboardPage walletDashboardPage = new WalletDashboardPage();

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
		waitfor3Sec();
		scrollToText("Recent funding history");
		ErrorCollector.extentLogInfo("Step " + step + " : Verify 'Recent Funding History' is Displaying");
		ErrorCollector.verifyTrue(walletDashboardPage.isRecentFundingHistoryDisplaying(),"Verified: 'Recent Funding History' is Displaying");
		step++;
		
		ErrorCollector.extentLogInfo("Step " + step + " : Closing the Application");
	}

	
	@Test(priority = 3)
	public void VerifyUserCanRepeatTransactionHistory() throws InterruptedException {

		initReport();
		initConfiguration();
		LoginPage loginPage = new LoginPage();
		WalletDashboardPage walletDashboardPage = new WalletDashboardPage();

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
		waitfor3Sec();
		scrollToText("Recent funding history");
		ErrorCollector.extentLogInfo("Step " + step + " : Verify 'Recent Funding History' is Displaying");
		ErrorCollector.verifyTrue(walletDashboardPage.isRecentFundingHistoryDisplaying(),"Verified: 'Recent Funding History' is Displaying");
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Click On <b>'Repeat'</b> Button");
		walletDashboardPage.clickOnRepeatFundingButton();
		step++;
		
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Verify 'Funding Confirmation Modal' is displaying'");
		ErrorCollector.verifyTrue(walletDashboardPage.isFundingConfirmationModalDisplaying(),"Verified: 'Funding Confirmation Modal is displaying'");
		step++;
		
		
		ErrorCollector.extentLogInfo("Step " + step + " : Closing the Application");
	}

	
	@Test(priority = 4)
	public void VerifyUserCanConvertCurrencies() throws InterruptedException {

		initReport();
		initConfiguration();
		LoginPage loginPage = new LoginPage();
		WalletDashboardPage walletDashboardPage = new WalletDashboardPage();

		String email = PropertiesReader.getPropertyValue("Email");
		String password = PropertiesReader.getPropertyValue("Password");
		String convertFromAmount = "10";
		String convertToType = "DKK";
		String convertFromType = "CAD";
		DecimalFormat df = new DecimalFormat("#.##");

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
		waitfor3Sec();
		scrollToText("Convert From");
		waitfor3Sec();

		ErrorCollector.extentLogInfo("Step " + step + " : Click On <b>'Convert To'</b> Dropdown");
		walletDashboardPage.clickOnConvertToDropdown();
		step++;
		ErrorCollector.extentLogInfo("Step " + step + " : Selecting <b>'"+convertToType+"'</b> Option");
		walletDashboardPage.selectConvertOption(convertToType);
		step++;
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Click On <b>'Convert From'</b> Dropdown");
		walletDashboardPage.clickOnConvertFromDropdown();
		step++;
		ErrorCollector.extentLogInfo("Step " + step + " : Selecting <b>'"+convertFromType+"'</b> Option");
		walletDashboardPage.selectConvertOption(convertFromType);
		step++;
		
		
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Entering the Amount:<b>"+convertFromAmount);
		walletDashboardPage.enterConvertFromAmount(convertFromAmount);
		step++;
		waitfor3Sec();
		scrollToText("Recent funding history");
		
		String Amount = walletDashboardPage.getConvertToAmount();
		ErrorCollector.extentLogInfo("Step " + step + " : Getting Amount From Convert To:<b> '"+Amount+"'</b>");
		
		String FxRate = walletDashboardPage.getFxRate(convertToType);
		ErrorCollector.extentLogInfo("Step " + step + " : Getting Fx Rate: '1"+convertFromType+"= "+FxRate+" "+convertToType+"'");
		System.out.println(FxRate);
		
		waitfor3Sec();
		ErrorCollector.extentLogInfo("Step " + step + " : Verify Converted Amount is according to FxRate");
		ErrorCollector.verifyEquals(Amount,df.format(Double.parseDouble(FxRate) *Double.parseDouble(convertFromAmount)),"Verified: 'Converted Amount is according to FxRate'");
		step++;
				
		ErrorCollector.extentLogInfo("Step " + step + " : Closing the Application");
	}	
}
