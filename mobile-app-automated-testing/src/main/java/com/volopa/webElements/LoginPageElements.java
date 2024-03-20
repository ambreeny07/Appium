package com.volopa.webElements;

import org.openqa.selenium.By;

public interface LoginPageElements {


	public static String loginButton = "(//android.widget.TextView[contains(@text,'Login')])[last()]";
	public static String loginWithEmailButton = "(//android.widget.TextView[contains(@text,'Login with email')])[last()]";
	public static String StartKYCButton = "//android.widget.TextView[@text='START KYC']";
	public static String explorPlatformButton = "//android.widget.TextView[@text='EXPLORE THE PLATFORM']";
	public static String profileButton = "//android.widget.TextView[@text='Profile']";
	public static String loginErrorMessage = "//android.widget.TextView[@text='Incorrect email ID and password combination.']";
	public static String pinPage = "//android.widget.TextView[@text='Enter Vested PIN']";
}
