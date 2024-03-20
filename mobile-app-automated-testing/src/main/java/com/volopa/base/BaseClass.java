package com.volopa.base;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.MobileBy;

import io.appium.java_client.MobileDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentTest;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import com.volopa.listeners.ExtentListeners;
import com.volopa.utilities.BrowserStackJob;
import com.volopa.utilities.ExcelReader;
import com.volopa.utilities.Waits;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class BaseClass {
	public static boolean closeDriver = false;
	public static WebDriverWait wait;
	public static String browser;
	public static String env;
	public static FileInputStream fis;
	public static ExtentTest extentReport;
	public static SoftAssert softAssert;
	public static Integer waitInSeconds = 5;

	// This is the default path to data package
	public static final String excelFilePath = System.getProperty("user.dir") + "/src/test/resources/data/";

	// This is the default path to imageUpload
	public static String imagePath = System.getProperty("user.dir") + "/src/test/resources/data/Images/";
	// This is column name from which we need to get row
	public static final String colName = "env";
	// This is row index of environment column from which we need to get data
	public static int rowIndex = 0;
	// Excel file name
	public static final String testDataFile = "testData";

	// Test Suite Runner File Name
	public static final String testSuiteRunnerFileName = "SuiteTests_Android";
	// Test Suite Runner Sheet Name
	public static final String testSuiteRunnerSheetName = "Tests";

	public static BrowserStackJob browserStackJob;
	public static String browserStackJobStatus;

	public static final int defaultTimeForVisibility = 30;
	public static final int defaultTimeTOBeClickable = 30;

	public static URL url;
	public static DesiredCapabilities capabilities;

	public static AndroidDriver<MobileElement> driver;

	public static String userName = PropertiesReader.getPropertyValue("BrowserstackUsername");
	public static String accessKey = PropertiesReader.getPropertyValue("BrowserstackAccessKey");
	public static boolean runOnLocal = true;

	public static void initReport() {
		extentReport = ExtentListeners.testReport.get();
		softAssert = new SoftAssert();
	}

	public static void initConfiguration() {
		try {

			if (extentReport == null) {
				initReport();
			}

			if (runOnLocal) {

				String buildPath = System.getProperty("user.dir")+ "/src/test/resources/executables/Volopa1.apk";

				final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
				url = new URL(URL_STRING);
				capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 12000);
				capabilities.setCapability(MobileCapabilityType.APP, buildPath);
				capabilities.setCapability("ignoreUnimportantViews", true);
				capabilities.setCapability("disableAndroidWatchers", true);
				capabilities.setCapability("automationName", "uiautomator2");
				capabilities.setCapability("autoGrantPermissions", "true");
				driver = new AndroidDriver<MobileElement>(url, capabilities);
			} else {
				
							
				String app_url = PropertiesReader.getPropertyValue("app_build");
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("device", "Google Pixel 4"/*"Samsung Galaxy S22 Ultra"*/);
				caps.setCapability("os_version", "11.0");
				caps.setCapability("project", "Volopa");
				caps.setCapability("build", "Android Tests Run on " + ExtentListeners.currentDateString);
				caps.setCapability("interactiveDebugging", true);
				caps.setCapability("name", ExtentListeners.currentTestMethodName);
				caps.setCapability("app", app_url);
				driver = new AndroidDriver<MobileElement>(
						new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);

			}

			driver.manage().timeouts().implicitlyWait(
					Integer.parseInt(PropertiesReader.getPropertyValue("implicit.wait")), TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Cause: " + e.getCause());
			System.out.println("Message: " + e.getMessage());
			e.printStackTrace();
		}
		try {
	    	JavascriptExecutor jse = (JavascriptExecutor)driver;
			Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
			System.out.println(response);
			browserStackJobStatus = response.toString();
			browserStackJob = BrowserStackJob.fromJsonString(response.toString());
	    }catch(Exception e) {}
		System.out.println("Application Started");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	public void pressBack() {
		driver.navigate().back();
	}

	public static AndroidDriver<MobileElement> getDriver() {
		return driver;
	}

	public static void printString(String message) {
		try {
			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForElementToBePresent(WebElement locator, int timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public void waitForElementsToBePresent(List<WebElement> locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfAllElements(locator));
	}

	public void waitForElementToBeClickable(WebElement by, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public boolean isElementClickable(WebElement by, int timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public AndroidElement waitForElementToBePresent(By locator, int timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
		return (AndroidElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void clickElement(AndroidElement element, String elementLabel) {
		// ExtentTestManager.getTest().info("Clicking " + elementLabel);
		element.click();
	}

	public void clickWebElement(WebElement element) {
		element.click();
	}

	public AndroidElement findElement(By locator) {
		return (AndroidElement) getDriver().findElement(locator);

	}

	public void sendKeysToElement(AndroidElement element, String keys, String elementLabel) {
		// ExtentTestManager.getTest().info("Entering "+ elementLabel + ": " + keys);
		element.sendKeys(keys);
	}

	public static void enterValue(WebElement element, String value) {
		// ExtentTestManager.getTest().info("Entering "+ elementLabel + ": " + keys);
		element.clear();
		element.sendKeys(value);
		Waits.wait1s();

	}

	public void sendKeysToWebElement(WebElement element, String keys, String elementLabel) {
		// ExtentTestManager.getTest().info("Entering "+ elementLabel + ": " + keys);
		element.clear();
		element.sendKeys(keys);
		Waits.wait1s();

	}
	
	public void sendKeysToWebElement(WebElement element, double keys, String elementLabel) {
		// ExtentTestManager.getTest().info("Entering "+ elementLabel + ": " + keys);
		element.clear();
		element.sendKeys(String.valueOf(keys));
		Waits.wait1s();

	}

	public String getElementText(WebElement element) {
		return element.getText();
	}

	public String getWebElementText(WebElement element, String elementLabel) {
		// ExtentTestManager.getTest().info("Getting "+elementLabel+ " Text" );
		return element.getText();
	}

	public boolean isElementClickable(By locator, int timeOutInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementSelected(WebElement element) {
		boolean isSelected = element.isSelected();
		return isSelected;
	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			if (element.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		scrollObject.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: scroll", scrollObject);
	}

	public void swipeUpSlightly() {
		new TouchAction((PerformsTouchActions) getDriver()).press(PointOption.point(550, 330)).waitAction()
				.moveTo(PointOption.point(550, 60)).release().perform();
	}

	public void swipeUpSlightlyWithUiAutomator() {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()");
	}

	public void swipeUp() {
		// ExtentTestManager.getTest().info("Swiping screen");
		TouchAction action = new TouchAction(getDriver());
		PointOption p1 = new PointOption();
		Dimension dimensions = getDriver().manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.55;
		Double screenWidth = dimensions.getWidth() * .5;
		int width = screenWidth.intValue();
		int h1 = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int h2 = screenHeightEnd.intValue();
		try {
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true)).flingForward()");
		} catch (Exception e) {
		}
	}

	public void scrollUp() {
		// ExtentTestManager.getTest().info("Swiping screen");
		TouchAction action = new TouchAction(getDriver());
		PointOption p1 = new PointOption();
		Dimension dimensions = getDriver().manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.55;
		Double screenWidth = dimensions.getWidth() * .5;
		int width = screenWidth.intValue();
		int h1 = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int h2 = screenHeightEnd.intValue();
		action.press(PointOption.point(width, h1)).waitAction().moveTo(PointOption.point(width, h2)).release()
				.perform();
	}

	public void scrollDown() {
		// ExtentTestManager.getTest().info("Swiping screen");
		TouchAction action = new TouchAction(getDriver());
		PointOption p1 = new PointOption();
		Dimension dimensions = getDriver().manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 1.0;
		Double screenWidth = dimensions.getWidth() * .5;
		int width = screenWidth.intValue();
		int h1 = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.55;
		int h2 = screenHeightEnd.intValue();
		action.press(PointOption.point(width, h1)).waitAction().moveTo(PointOption.point(width, h2)).release()
				.perform();
	}

	public void scrollIntoViewSmoothly(WebElement Element) {
		Dimension dimension = getDriver().manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.5);
		int scrollEnd = (int) (dimension.getHeight() * 0.2);

		new TouchAction((PerformsTouchActions) getDriver()).press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(0, scrollEnd))
				.release().perform();
	}

	public void scrollIntoViewSmoothlyFullScreen() {
		Dimension dimension = getDriver().manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.63);
		int scrollEnd = (int) (dimension.getHeight() * 0.2);

		new TouchAction((PerformsTouchActions) getDriver()).press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(0, scrollEnd))
				.release().perform();
	}
	
	public void scrollIntoViewSmoothlyLess() {
		Dimension dimension = getDriver().manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.45);
		int scrollEnd = (int) (dimension.getHeight() * 0.2);

		new TouchAction((PerformsTouchActions) getDriver()).press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(0, scrollEnd))
				.release().perform();
	}
	
	
	

	public String randomString(int len) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public String randomNumberString(int len) {
		String AB = "123456789";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public void longPress(AndroidElement ele, String elementLabel) {
		// ExtentTestManager.getTest().info("Long press "+elementLabel );
		Duration d1 = Duration.between(LocalTime.MAX, LocalTime.NOON);
		new TouchAction(getDriver())
				.longPress(new LongPressOptions().withElement(ElementOption.element(ele)).withDuration(d1)).release()
				.perform();
	}

	public boolean isElementPresent(WebElement element, String elementLabel) {
		// ExtentTestManager.getTest().info("Checking " + elementLabel + " is present");
		try {
			waitForElementToBePresent(element, 30);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementPresent(WebElement element, String elementLabel, int timeOutInSeconds) {
		// ExtentTestManager.getTest().info("Checking " + elementLabel + " is present");
		try {
			waitForElementToBePresent(element, timeOutInSeconds);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void appRunInBackGround() {
//		////ExtentTestManager.getTest().info("Minimizing application");
		getDriver().runAppInBackground(Duration.ofSeconds(1));
		// ExtentTestManager.getTest().info("Re-opening application");
	}

	public void killApplication() {
//		//ExtentTestManager.getTest().info("closing application");
		getDriver().closeApp();
		getDriver().quit();
	}

	public void launchApplication() {
		getDriver().launchApp();
	}

	public void swipeScreen(String dir) {

		final int ANIMATION_TIME = 200;
		final int PRESS_TIME = 200;
		int edgeBorder = 10;

		PointOption pointOptionStart, pointOptionEnd;

		Dimension dims = getDriver().manage().window().getSize();
		pointOptionStart = point(dims.width / 2, dims.height / 2);

		switch (dir.toLowerCase()) {
		case "down":
			pointOptionEnd = point(dims.width / 2, dims.height - edgeBorder);
			break;
		case "up":
			pointOptionEnd = point(dims.width / 2, edgeBorder);
			break;
		case "left":
			pointOptionEnd = point(edgeBorder, dims.height / 2);
			break;
		case "right":
			pointOptionEnd = point(dims.width - edgeBorder, dims.height / 2);
			break;
		default:
			throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
		}

		try {
			new TouchAction((PerformsTouchActions) getDriver()).press(pointOptionStart)
					.waitAction(waitOptions(ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release().perform();
		} catch (Exception e) {
			System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
			return;
		}

		try {
			Thread.sleep(ANIMATION_TIME);
		} catch (InterruptedException e) {

		}
	}

	public void swipeByElements(AndroidElement startElement, AndroidElement endElement) {
		int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
		int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
		int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
		int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
		new TouchAction((PerformsTouchActions) getDriver()).press(point(startX, startY))
				.waitAction(waitOptions(ofMillis(1000))).moveTo(point(endX, endY)).release().perform();

	}

	public void goBack(int numberOfTimes) {

		for (int i = 0; i < numberOfTimes; i++) {

			getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
			Waits.wait1s();

		}
	}

	public static String getUniqueEmailId(String value) {

		SimpleDateFormat formatter = new SimpleDateFormat("MMddyyHmm");
		Date date = new Date();
		String unique = formatter.format(date).toString();
		String uniqueEmailId = value + unique + "@codeautomation.ai";
		return uniqueEmailId;
	}

	public static String getUniquePassword() {
		SimpleDateFormat formatter = new SimpleDateFormat("mmss");
		Date date = new Date();
		String unique = formatter.format(date).toString();
		String passString = "Pass" + unique + "#";
		return passString;
	}

	public Object[][] getData(String filename, String SheetName) {

		ExcelReader excel = new ExcelReader(excelFilePath + filename + ".xlsx");
		int rows = excel.getRowCount(SheetName);
		int columns = excel.getColumnCount(SheetName);

		Object[][] data = new Object[rows - 1][columns];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < columns; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData(SheetName, colNum, rowNum);
			}
		}
		return data;
	}

	public static void waitTime(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void scrollToText(String text) {
		String str1 = "new UiScrollable(new UiSelector().scrollable(true).";
		String str2 = "instance(0)).scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0))";
		String user_scroll = str1.concat(str2);
		getDriver().findElementByAndroidUIAutomator(user_scroll);
		getDriver().findElementByAndroidUIAutomator(user_scroll);
	}

	public void scrollTo(String selector) {
		String selectorString = String.format(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + selector + ")");
		getDriver().findElement(MobileBy.AndroidUIAutomator(selectorString));
	}

	public void scrollHorizontalList(String text) {
		MobileElement element = (MobileElement) driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList()"
						+ ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"));
	}

	public void scrollToTextContains(String text) {
		String str1 = "new UiScrollable(new UiSelector().scrollable(true).";
		String str2 = "instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))";
		String user_scroll = str1.concat(str2);
		getDriver().findElementByAndroidUIAutomator(user_scroll);
	}

	public static void waitForElementVisibility(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementInvisibility(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void click(WebElement element) {
//		waitForElementVisibility(element, defaultTimeForVisibility);
		waitForElementToBeClickable(element, defaultTimeTOBeClickable);
		element.click();
	}
	public void clickWithTouchActionsByCoordinates(int x, int y) {
		// Create a TouchAction instance
        TouchAction touchAction = new TouchAction(driver);
        // Perform the tap action using the x, y coordinates
        touchAction.tap(TapOptions.tapOptions().withPosition(PointOption.point(x, y))).perform();

	}

	public void sendKeysToWebElement(WebElement element, String keys) {
		// waitForElementVisibility(element, defaultTimeForVisibility);
		// waitForElementToBeClickable(element, defaultTimeTOBeClickable);
		element.clear();
		element.sendKeys(keys);
		Waits.wait1s();

	}

	public static String generateRandomNumberWithGivenNumberOfDigits(int number) {
		String randomNumber = "123456789";
		StringBuilder sb = new StringBuilder(number);
		for (int i = 0; i < number; i++) {
			int index = (int) (randomNumber.length() * Math.random());
			sb.append(randomNumber.charAt(index));
		}
		return sb.toString();
	}

	public Object[][] getSuiteTests(String filename, String SheetName) {

		ExcelReader excel = new ExcelReader(
				System.getProperty("user.dir") + "/src/test/resources/data/" + filename + ".xlsm");
		int rows = excel.getRowCount(SheetName);
		int columns = excel.getColumnCount(SheetName);

		Object[][] data = new Object[rows - 1][columns];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < columns; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData(SheetName, colNum, rowNum);
			}
		}
		return data;
	}

	public void updateTestData(String filename, String sheetName, String testName, String status) {

		ExcelReader excel = new ExcelReader(
				System.getProperty("user.dir") + "/src/test/resources/data/" + filename + ".xlsm");
		Object[][] data = getSuiteTests(filename, sheetName);
		int rowNum = -1;
		for (int x = 0; x < data.length; x++) {
			if (data[x][1].toString().equals(testName)) {
				rowNum = x + 2;
			}
		}
		printString("Updated Test Result: " + excel.setCellData(sheetName, "Status", rowNum, status));
	}

	public String randomNumber(int len) {
		String AB = "0123456789";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String getDate(int days, String Format, String Time_Zone) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat(Format);
		dateFormat.setTimeZone(TimeZone.getTimeZone(Time_Zone));
		Date date = new Date();
		String DF = dateFormat.format(date);

		Calendar c = Calendar.getInstance();
		c.setTime(dateFormat.parse(DF));
		c.add(Calendar.DATE, days);
		String formattedDate = dateFormat.format(c.getTime());

		printString("date : " + formattedDate);
		return formattedDate;

	}

	public static ArrayList<String> getDateList(int maxdays, String Format, String Time_Zone) throws ParseException {
		ArrayList<String> dates = new ArrayList<>();
		for (int i = 0; i <= maxdays; i++) {
			DateFormat dateFormat = new SimpleDateFormat(Format);
			dateFormat.setTimeZone(TimeZone.getTimeZone(Time_Zone));
			Date date = new Date();
			String DF = dateFormat.format(date);
			Calendar c = Calendar.getInstance();
			c.setTime(dateFormat.parse(DF));
			c.add(Calendar.DATE, i);
			String formattedDate = dateFormat.format(c.getTime());
			dates.add(formattedDate);
		}
		return dates;
	}

	public void swipeUpLess() {
		// ExtentTestManager.getTest().info("Swiping screen");
		TouchAction action = new TouchAction(getDriver());
		PointOption p1 = new PointOption();
		Dimension dimensions = getDriver().manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.55;
		Double screenWidth = dimensions.getWidth() * .5;
		int width = screenWidth.intValue();
		int h1 = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int h2 = screenHeightEnd.intValue();
		action.press(PointOption.point(width, h1)).waitAction(new WaitOptions().withDuration(Duration.ofMillis(1100)))
				.moveTo(PointOption.point(width, h2)).release().perform();
		// new
		// TouchAction((PerformsTouchActions)getDriver()).press(PointOption.point(width,h1)).waitAction().moveTo(PointOption.point(width,
		// h2)).release().perform();
//		try {
//			driver.findElementByAndroidUIAutomator(
//					"new UiScrollable(new UiSelector().scrollable(true)).flingForward()");
//		} catch (Exception e) {
//		}
	}

	public void updateData(String filename, String sheetName, String status) {
		XSSFWorkbook wb = null;
		XSSFSheet sh = null;
		File file = new File(System.getProperty("user.dir") + "/src/test/resources/data/" + filename + ".xlsx");
		try {

//			ExcelReader excel = new ExcelReader(
//					System.getProperty("user.dir") + "/src/test/resources/data/" + filename +".xlsx");
////			Object[][] data = getSuiteTests(filename, sheetName);
////			int rowNum = -1;
//			printString("Updated Test Result: " + excel.setCellData(sheetName, "NewUserPassword", 0, status));

			FileInputStream file_2 = new FileInputStream(file);
			wb = new XSSFWorkbook(file_2);
			sh = wb.getSheetAt(0);
			sh.getRow(1).createCell(26).setCellValue(status);

			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getSortedList(ArrayList<String> symbol) {
		Collections.sort(symbol);
		return symbol;
	}

	public ArrayList<Double> getSortedDoubleList(ArrayList<Double> symbol) {
		Collections.sort(symbol);
		return symbol;
	}

	public void scrollToTop() {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingBackward()");
	}

	public void waitfor3Sec() throws InterruptedException {
		Thread.sleep(3000);
	}

	public void swipeDown() {
//        ExtentTestManager.getTest().info("Swiping screen");
		TouchAction action = new TouchAction(getDriver());
		PointOption p1 = new PointOption();
		Dimension dimensions = getDriver().manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		Double screenWidth = dimensions.getWidth() * .5;
		int width = screenWidth.intValue();
		int h1 = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.99;
		int h2 = screenHeightEnd.intValue();
		action.press(PointOption.point(width, h1)).waitAction(new WaitOptions().withDuration(Duration.ofMillis(600)))
				.moveTo(PointOption.point(width, h2)).release().perform();

	}
	
	public void swipeDownCount(int num) {
		for(int i=0;i<num;i++) {
//      ExtentTestManager.getTest().info("Swiping screen");
		TouchAction action = new TouchAction(getDriver());
		PointOption p1 = new PointOption();
		Dimension dimensions = getDriver().manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		Double screenWidth = dimensions.getWidth() * .5;
		int width = screenWidth.intValue();
		int h1 = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.99;
		int h2 = screenHeightEnd.intValue();
		action.press(PointOption.point(width, h1)).waitAction(new WaitOptions().withDuration(Duration.ofMillis(600)))
				.moveTo(PointOption.point(width, h2)).release().perform();
		}

	}
	
    public void scrollIntoElement(WebElement elementValue) {
		boolean element;
		int count=0;
		swipeDown();
		swipeDown();
		swipeDown();
		swipeDown();
		swipeDown();
		while (true) {
			scrollIntoViewSmoothlyFullScreen();
			element = isElementDisplayed(elementValue);
			count++;
			if(count == 8){
				element = true;
			}
			if (element == true) {
				scrollIntoViewSmoothlyFullScreen();
				break;
			}
		}
	}
    
    public void swipeLeft() {
        // Get the screen dimensions
        int width = getDriver().manage().window().getSize().width;
        int height = getDriver().manage().window().getSize().height;

        // Set the starting and ending points for the swipe
        int startX = (int) (width * 0.9); // Swipe starts from 80% of the screen width
        int endX = (int) (width * 0.2); // Swipe ends at 20% of the screen width
        int startY = height / 2; // Swipe starts from the center of the screen

        // Create a new TouchAction instance and perform the swipe
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, startY))
                   .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))) // Optional: Add a small delay
                   .moveTo(PointOption.point(endX, startY))
                   .release()
                   .perform();
    }


}
