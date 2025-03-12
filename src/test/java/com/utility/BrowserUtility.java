package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.BrowserSelection;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver =new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
	}

	public BrowserUtility(String browserName, boolean isHeadLess) {
		logger.info("Launching browser " + browserName);
		if (browserName.equalsIgnoreCase("chrome") ) {
			if(isHeadLess) {
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--headless=old");
			options.addArguments("--window-size=1920,1080");
			driver.set(new ChromeDriver());
			}else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName.equalsIgnoreCase("edge")) {
			if(isHeadLess) {
				EdgeOptions options=new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver());
			}else {
			driver.set(new EdgeDriver());
			}
		} else {
			logger.error("Invalid browser...!please select chrome or edge");
			System.err.println("Invalid browser...!please select chrome or edge");
		}
	}

	public BrowserUtility(BrowserSelection browserName,boolean isHeadLess) {
		logger.info("Launching browser " + browserName);
		if (browserName == BrowserSelection.CHROME ) {
			if(isHeadLess) {
				ChromeOptions options= new ChromeOptions();
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
				}else {
					driver.set(new ChromeDriver());
				}
		} else if (browserName == BrowserSelection.EDGE) {
			if(isHeadLess) {
				EdgeOptions options=new EdgeOptions();
				options.addArguments("--headless=new");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			}else {
			driver.set(new EdgeDriver());
			}
		} else if (browserName == BrowserSelection.FIREFOX) {
			if(isHeadLess) {
				FirefoxOptions options=new FirefoxOptions();
				options.addArguments("--headless=new");
				options.addArguments("disable-gpu");
				driver.set(new FirefoxDriver(options));
			}else {
			driver.set(new FirefoxDriver());
			}
		} else
			logger.error("Invalid browser...!please select chrome or edge");

	}

	public void goToURL(String url) {
		logger.info("Launching the website " + url);
		driver.get().get(url);
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found performing click  " + locator);
		element.click();
	}

	public void sendText(By locator, String text) {
		logger.info("Findinding element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found. Now entering text.. " + text);
		element.sendKeys(text);
	}

	public String getVisibleText(By locator) {
		logger.info("Findinding element with locator " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now returning visible text  " + element.getText());
		return element.getText();
	}

	public String takeScreenShot(String name) {
		Date date= new Date();
		SimpleDateFormat format= new SimpleDateFormat("HH-mm-ss");
		String timeStamp=format.format(date);
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		
		String path= System.getProperty("user.dir") + "//Screenshots//" + name+" -" + timeStamp +".png";
		File screenShotData = screenshot.getScreenshotAs(OutputType.FILE);
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenShotData, screenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;
	}

}
