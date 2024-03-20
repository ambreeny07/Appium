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

public class WalletDashboardPage extends BaseClass {

	@FindBy(xpath = "//android.widget.TextView[@text='Balances']")
	public WebElement balanceSection;

	@FindBy(xpath = "//android.widget.TextView[@text='Recent funding history']")
	public WebElement recentFundingHistory;

	@FindBy(xpath = "(//android.widget.TextView[@text='Repeat'])[1]")
	public WebElement repeatFundingButton;

	@FindBy(xpath = "//android.widget.TextView[@text='Funding']")
	public WebElement fundingConfirmationModal;

	@FindBy(xpath = "(//android.widget.TextView[@text='Select'])[1] | (//android.widget.TextView[@text='Convert To']//following-sibling::android.view.ViewGroup)[1]//android.view.ViewGroup")
	public WebElement convertToDropdow;

	@FindBy(xpath = "(//android.widget.EditText)[1] | (//android.widget.TextView[@text='Select'])[1]/parent::android.view.ViewGroup/following-sibling::android.widget.EditText |(//android.widget.TextView[@text='Convert To']//following-sibling::android.view.ViewGroup)[1]//android.widget.EditText[@text='Enter amount']")
	public WebElement convertToAmountField;

	@FindBy(xpath = "(//android.widget.TextView[@text='Select'])[1] | (//android.widget.TextView[@text='Select'])[2] | (//android.widget.TextView[@text='Convert From']//following-sibling::android.view.ViewGroup)[1]//android.view.ViewGroup")
	public WebElement convertFromDropdow;

	@FindBy(xpath = "(//android.widget.EditText)[2] | //android.widget.TextView[@text='Convert From']/following-sibling::android.view.ViewGroup[1]/android.widget.EditText")
	public WebElement convertFromAmountField;
	
	@FindBy(xpath = "(//android.widget.ImageView[@index='0'])[1]")
	public WebElement closeIcon;
	
	@FindBy(xpath = "//android.widget.TextView[@text='FX Rate']//following-sibling::android.widget.TextView")
	public WebElement fxRate;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Check rate']")
	public WebElement checkRate;
	
	
	
	
	

	public WalletDashboardPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isBalanceSectionDisplaying() {
		return isElementDisplayed(balanceSection);
	}

	public boolean isRecentFundingHistoryDisplaying() {
		return isElementDisplayed(recentFundingHistory);
	}

	public void clickOnRepeatFundingButton() {
		click(repeatFundingButton);
	}

	public void clickOnConvertToDropdown() {
		click(convertToDropdow);
	}
	
	public void selectConvertOption(String type) {
		WebElement ele = null;
		ele = driver.findElement(By.xpath("//android.widget.TextView[@text='"+type+"']"));
		click(ele);
		
	}

	public void enterConvertToAmount(String email) {
		click(convertToAmountField);
		sendKeysToWebElement(convertToAmountField, email);
	}

	public void clickOnConvertFromDropdown() {
		click(convertFromDropdow);
	}
	public void selectCADConvertFromOption() {
		clickWithTouchActionsByCoordinates(255, 870);
	}
	
	public void clickOnCheckRate() {
		click(checkRate);
	}

	
	
	public void enterConvertFromAmount(String email) {
		convertFromAmountField.click();
		sendKeysToWebElement(convertFromAmountField, email);
	}
	
	public String getConvertToAmount() {
		String val = convertToAmountField.getText();
		return val;
	}
	
	public String getConvertFromAmount() {
		String val = convertFromAmountField.getText();
		return val;
	}
	
	public String getFxRate(String To) {
		String val = fxRate.getText();
		String[] value = val.split("=");
		val = value[1].replace(To, "");
		return val;
	}

	public boolean isFundingConfirmationModalDisplaying() {
		return isElementDisplayed(fundingConfirmationModal);
	}

}
