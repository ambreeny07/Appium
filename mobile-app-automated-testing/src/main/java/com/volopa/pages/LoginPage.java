package com.volopa.pages;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import com.volopa.base.BaseClass;
import com.volopa.base.PropertiesReader;
import com.volopa.webElements.LoginPageElements;

public class LoginPage extends BaseClass{
	
    
	@FindBy(xpath = "//android.widget.EditText[@text='Email']")
	public WebElement emailField;
	
	@FindBy(xpath = "//android.widget.EditText[@text='Password']")
	public WebElement passwordField;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Sign in']")
	public WebElement SignInButton;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Wallet Dashboard']")
	public WebElement dashboardHeading;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Login failed']")
	public WebElement failedLoginErrorMessage;
		
		
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickOnSignInButton(){
		click(SignInButton);
    }
	
	public void enterEmail(String email){
		sendKeysToWebElement(emailField,email);
    }
	
	public void enterPassword(String password){
		sendKeysToWebElement(passwordField,password);
    }
	

	public boolean isErrorMessageFailed() {
		return isElementDisplayed(failedLoginErrorMessage);
	}
	public boolean isDashboardTitleDisplaying() {
		return isElementDisplayed(dashboardHeading);
	}
	
	
	
   
}
