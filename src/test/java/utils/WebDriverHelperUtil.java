package utils;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import config.Config;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author abitha
 * @implNote This class is a container for different functions of web driver
 *
 */

public class WebDriverHelperUtil extends DriverUtil {

	public enum Locators {xpath, id, name, classname, linktext, tagname, paritallinktext, cssLocator}

	public static String parentWindow;
	public static String childWindow;
	public static String time = "";
	private static final long DEFAULT_TIME_OUT = 20;
	private static final long IMPLICIT_WAIT = 10;
	private static final long FLUENT_WAIT = 120;
	public static String screenShotPath;
	public static Config setup = new Config();
	public static LogUtil logthis = new LogUtil();

	public WebElement getWebElement(Locators locator, String element) throws Exception {
		By byElement;
		switch (locator) {            //determine which locator item we are interested in
			case xpath:
				byElement = By.xpath(element);
				break;
			case id:
				byElement = By.id(element);
				break;
			case name:
				byElement = By.name(element);
				break;
			case classname:
				byElement = By.className(element);
				break;
			case linktext:
				byElement = By.linkText(element);
				break;
			case paritallinktext:
				byElement = By.partialLinkText(element);
				break;
			case tagname:
				byElement = By.tagName(element);
				break;
			default:
				throw new Exception();
		}
		WebElement query = driver.findElement(byElement);
		return query;
	}

	public WebElement findElement(By by) {
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
			element = driver.findElement(by);
			LogUtil.file_logger.info(by + " FOUND");
		} catch (NoSuchElementException e) {
			LogUtil.root_logger.error(by + "NOT FOUND " + e);
			Assert.fail(by + " NOT FOUND!");
		}
		return element;
	}

	public void click(WebElement element) {
		if (element.isDisplayed()) {
			element.click();
			try {
				wait(1);
			} catch (InterruptedException e) {
				Assert.fail();
			}
		} else {
			LogUtil.root_logger.error("Element " + element + " is not displayed in browser");
			Assert.fail();
		}
	}
	
		public void setText(Locators locator, String element, String text) throws Exception {
			if (!text.equals("IGNORE")) {
				setText(getWebElement(locator, element), text);
			}
		}

		public void setText(WebElement element, String text) {
				if (element.isEnabled()) {
					element.sendKeys(text);
					LogUtil.file_logger.info("Element set - " + element + " - " + text);
				} else {
					LogUtil.root_logger.error("Element: " + element + " not enabled");
					Assert.fail("Element: " + element + " not enabled");
				}
		}

		public void setText(By by, String text) {
				if (!text.isEmpty()) {

					driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
					WebElement element = null;
					try {
						element = findElement(by);
						if (element.isDisplayed()) {
							if (element.isEnabled()) {
								//element.clear();
								element.sendKeys(text);
								LogUtil.file_logger.info("Element set - " + element + " - " + text);
							} else {
								LogUtil.root_logger.error("Element: " + element + " not enabled");
								Assert.fail("Element: " + element + " not enabled");
							}
						} else {
							LogUtil.root_logger.error("Element " + element + " is not displayed");
							Assert.fail("Element " + element + " is not displayed");
						}
					} catch (NoSuchElementException e) {
						LogUtil.root_logger.error("Element " + element + " cannot be found");
						Assert.fail("Element " + element + " cannot be found");
					}
				}
		}

	/**
	* Returns a boolean value that will return true if try block executes successfully. 
	* The by argument is the locating mechanism. The argument seconds is the wait time by seconds.
	* <p>
	* This method will wait until the time given in seconds parameter. Then, 
	* it will try to find the element given in by parameter in the current screen.
	* Always returns true upon successful try block execution. 
	* return false if exception occurred.
	* 
	* @param by locating mechanism of type By
	* @param seconds the wait time by seconds
	* @return the boolean value
	* @see <Write similar methods>
	*/
	public boolean isElementExist(By by, long seconds) {
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
			element = driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementDisplayed(final By locator, long d) {
		try {
			new FluentWait<>(driver)
				.withTimeout(d, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return (driver.findElement(locator).isDisplayed());	
		} catch (Exception e) {
		}
		return false;
	}

	public boolean isElementEnabled(final By locator, long d) {
		try {
			new FluentWait<>(driver)
				.withTimeout(d, TimeUnit.SECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return (driver.findElement(locator).isDisplayed());	
		} catch (Exception e) {
		}
		return false;
	}
	
// have this
	public void click(By by) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
			waitForElementClickable(by);
			//waitForElementEnabled(by);
			element.getTagName();
			element.click();
			LogUtil.file_logger.info("Element click " + element + " successful");
		} catch (TimeoutException te) {
			LogUtil.root_logger.error("Element " + by + " is not displayed " + te);
			//extentReport.createFailStepWithScreenshot("Element " + by + " is not displayed ");
			Assert.fail("Element " + by + " is not displayed");
		} catch (NoSuchElementException ne) {
			System.err.println("Element " + by + " cannot be found");
			LogUtil.root_logger.error("Element " + by + " cannot be found " + ne);
			Assert.fail("Element " + by + " cannot be found");
		} catch (Exception e) {
			LogUtil.root_logger.error("Element " + by + " is not displayed " + e);
			Assert.fail("Element " + by + " is not displayed");
		}
	}

	public void clearAndSetText(WebElement element, String text) {
		if (!text.equals("IGNORE")) {
			element.clear();
			setText(element, text);
		}
	}

	public void clearAndSetText(By by, String text) {
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
			try {
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
				if(setup.getProperty("ChromeVersion").equalsIgnoreCase("72")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", element);
				}
				else {
					element.clear();
					setText(element, text);
				}
			} catch (TimeoutException te) {
				LogUtil.root_logger.error("Element " + by + " is not displayed");
				Assert.fail("Element " + by + " is not displayed");
			} catch (NoSuchElementException e) {
				LogUtil.root_logger.error("Element " + by + " cannot be found");
				Assert.fail("Element " + by + " cannot be found");
			}
	}

	public WebElement waitForElementClickable(final By locator) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			LogUtil.root_logger.error("Element " + locator + " not found");
			Assert.fail("Element " + locator + " not found");
		}
		return element;
	}

	//explicit wait  - Selemiun Dynamic wait - used to stop the script execution 
	//on a particular condition for a specified duration
	public void waitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementVisible(By by) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementNotVisible(By by) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_OUT);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void waitForText(final By locator) {
		try {
			new FluentWait<>(driver)
					.withTimeout(FLUENT_WAIT, TimeUnit.SECONDS)
					.pollingEvery(10, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(locator));			
		} catch (Exception e) {
			LogUtil.root_logger.error("Element " + locator + " not found" + e);
			Assert.fail("Element " + locator + " not found");
		}
	}

	public void waitForElement(final By locator) {
		try {
			new FluentWait<>(driver)
					.withTimeout(FLUENT_WAIT, TimeUnit.SECONDS)
					.pollingEvery(10, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(locator));		
		} catch (Exception e) {
			LogUtil.root_logger.error("Element " + locator + " not found" + e);
			Assert.fail("Element " + locator + " not found");
		}
	}

	public void hardWait(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException ie) {

		}
	}

	public boolean isElementDisplayed(By by) {
		return driver.findElement(by).isDisplayed();
	}
}

