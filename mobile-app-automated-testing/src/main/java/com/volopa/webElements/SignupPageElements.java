package com.volopa.webElements;

import org.openqa.selenium.By;

public interface SignupPageElements {
	public static String  emailInput1 = "//android.widget.EditText[@text='abc@xyz.com']";
	public static String  passwordInput1 = "//android.widget.EditText[@text='*********']";
    
    
    By signUpButton = By.xpath("//android.widget.TextView[@text='Sign Up']");
    By signUpButtonWithEmail = By.xpath("//android.widget.TextView[contains(@text,'Sign Up with email')]");
    By emailInput = By.xpath("//android.widget.EditText[@text='abc@xyz.com']");
    By passwordInput = By.xpath("//android.widget.EditText[@text='*********']");
    By btnSubmitSignup = By.xpath("(//android.widget.TextView[@text='Sign Up'])[last()]");
    By verificationCodeScreen = By.xpath("//android.widget.TextView[contains(@text,'Verification Code')]");
    By actionbarBackBtn = By.xpath("(//android.widget.TextView[contains(@text,'chevron_left')])[1]");
    By mobileNumberEditText= By.xpath("//android.widget.EditText[contains(@text,'Enter your mobile number')]");
	By sendOTPButton = By.xpath("//android.widget.TextView[@text='SEND OTP']");
	By resendOTPButton = By.xpath("//android.widget.TextView[contains(@text,'Resend OTP')]");
	By recieveOTPViaCallButton = By.xpath("//android.widget.TextView[contains(@text,'Receive OTP via call')]");
	By continueAnywayButton = By.xpath("//android.widget.TextView[contains(@text,'Continue Anyway')]");
	By uploadPanCardButton = By.xpath("//android.widget.TextView[contains(@text,'UPLOAD PAN CARD')]");
	By panCardConfirmButton = By.xpath("//android.widget.TextView[contains(@text,'CONFIRM')]");
	By uploadButtonPanCardDialog = By.xpath("//android.widget.TextView[contains(@text,'UPLOAD')]");
	By chooseFromGalleryButton = By.xpath("//android.widget.TextView[contains(@text,'Choose from Gallery')]");
	By downloadFolder = By.xpath("(//android.widget.TextView[contains(@text,'download')] | //android.widget.TextView[contains(@text,'Download')])[1]");
	By panCardImage = By.xpath("(//android.view.ViewGroup[contains(@content-desc,'Photo taken on')])[1]");
	By panCardUploadedSuccessfully = By.xpath("//android.widget.TextView[contains(@text,'Pan Card Uploaded Successfully')]");
	By uploadAadhaarCardButton = By.xpath("//android.widget.TextView[contains(@text,'UPLOAD AADHAAR')]");
	By addressLine1EditText = By.xpath("//android.widget.EditText[contains(@text,'Address Line 1')]");
	By cityEditText = By.xpath("//android.widget.EditText[contains(@text,'City')]");
	By pinCodeEditText = By.xpath("//android.widget.EditText[contains(@text,'PinCode')]");
	By stateDropDown = By.xpath("(//android.widget.TextView[@text='State'])[last()]/parent::android.view.ViewGroup");
	By checkboxIConfirmedInformation = By.xpath("(//android.widget.TextView[contains(@text,'I confirm that the information outlined')]/preceding-sibling::android.view.ViewGroup)[last()]");
	By checkboxIAgreeDriveWealthPolicy = By.xpath("(//android.widget.TextView[contains(@text,'I agree to Drivewealth’s Privacy')]/preceding-sibling::android.view.ViewGroup)[last()]");
	By fullNameEditText = By.xpath("//android.widget.EditText[contains(@text,'Full Name')]");
	By kycSubmittedMessage = By.xpath("//android.widget.TextView[@text='Your KYC form is submitted']");
	By aadhaarSubmittedMessage = By.xpath("//android.widget.TextView[contains(@text,'Aadhaar uploaded')]");
	By primaryTaxFilesCountryOther = By.xpath("//android.widget.TextView[@text='Other']");
	By primaryTaxFileCountryEditText = By.xpath("//android.widget.EditText[contains(@text,'Country of tax')]");
	By ssnNumberEditText = By.xpath("//android.widget.EditText[contains(@text,'Enter your SSN')]");
	By passportFileNoEditText = By.xpath("//android.widget.EditText[contains(@text,'Passport file no')]");
	By uploadPassportButton = By.xpath("//android.widget.TextView[@text='UPLOAD PASSPORT']");
	By passportUploadedSuccessfully = By.xpath("//android.widget.TextView[contains(@text,'Passport Uploaded Successfully')]");
	By taxIdNumEditText = By.xpath("//android.widget.EditText[contains(@text,'ID number')]");
	By stateEditText = By.xpath("//android.widget.EditText[contains(@text,'State')]");
	By welcomeToVestedPopupAwsomeBtn = By.xpath("//android.widget.TextView[contains(@text,'AWESOME!')]");
	By acceptAndContinueBtn = By.xpath("//android.widget.TextView[contains(@text,'ACCEPT AND CONTINUE')]");
	By genderMaleOption = By.xpath("//android.widget.TextView[@text='Male']");
	By genderFemaleOption = By.xpath("//android.widget.TextView[@text='Female']");
	By maritalStatusSingleOption = By.xpath("//android.widget.TextView[@text='Single']");
	By employmentUnemployedOption = By.xpath("//android.widget.TextView[@text='Unemployed']");
	By checkAnyThatApplytoYouLabel = By.xpath("//android.widget.TextView[@text='Check any that apply to you']");
	By noneOfTheTheseApplyToMeCheckBox = By.xpath("(//android.widget.TextView[@text='None of the these apply to me']/preceding-sibling::android.view.ViewGroup)[last()]");
	By nextBtn = By.xpath("//android.widget.TextView[@text='Next']");
	By riskToleranceLowOption = By.xpath("//android.widget.TextView[@text='Low']");
	By severalTimesAWeekOption = By.xpath("//android.widget.TextView[@text='Several times a week']");
	By investingExperience0To1Year = By.xpath("//android.widget.TextView[@text='0 to 1 year']");
	By yearlyIncome0To10LakhRupees = By.xpath("//android.widget.TextView[@text='0 to 10 lakh rupees']");
	By liquidNetWorth0To5LakhRupees = By.xpath("//android.widget.TextView[@text='0 - 5 lakh rupees']");
	By totalNetWorth0T010LakhRupees = By.xpath("//android.widget.TextView[@text='0 to 10 lakh rupees']");
	By primarySourceSelectSourceDropDownBtn = By.xpath("//android.widget.TextView[@text='Select source']");
	By primarySourceSelectSourceDropDownGiftOption = By.xpath("//android.widget.TextView[@text='Gift']");
	By depositsAndWithdrawals6To10 = By.xpath("//android.widget.TextView[@text='6 to 10']");
	By moneyPlanToDeposits0To50000 = By.xpath("//android.widget.TextView[@text='0 to 50,000 rupees (0 to 700 U.S. dollars)']");
	By investments0To5 = By.xpath("//android.widget.TextView[@text='0 to 5']");
	By primaryTaxFilesCountryIndia = By.xpath("//android.widget.TextView[@text='India']");
	By mobileNumberLinkedWithAdharNo = By.xpath("//android.widget.TextView[@text='NO']");
}
