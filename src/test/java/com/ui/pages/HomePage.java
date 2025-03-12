package com.ui.pages;
/*
 * page object design pattern rules
 * if you are creating any variables inside the page class they will be locators.
 * and all the locators which we do not change trough the entire program ,
 * they are constants and we will mark them final. and if a variable is final 
 * then it is also static.
 * parent classes are abstract and child classes are final.
 * like BrowserUtility is abstract and HomePage is final.
 * Can an abstract class have a constructor?
 * yes, and bcoz we wont be able to create an object of abstract class,it's
 * constructor can be called by its child class constructor using super keyword. 
 * goToLoginPage is a page function in home page class
 * and a page function cannot have a void return type this is another rule.
 * */

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.BrowserSelection;
import static com.constants.Environments.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;
import com.utility.PropertiesUtil;

public final class HomePage extends BrowserUtility {
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public HomePage(BrowserSelection browserName, boolean isHeadLess) {
		super(browserName, isHeadLess);
		// This line below is to read from properties file
		// goToURL(PropertiesUtil.readProperty(QA, "URL"));
		// and the below line is to read from JSON file
		// System.out.println(JSONUtility.readJSON(QA));
		goToURL(JSONUtility.readJSON(QA).getUrl());

	}

	public HomePage(WebDriver driver) {
		super(driver);
		goToURL(JSONUtility.readJSON(QA).getUrl());
	}

	public LoginPage goToLoginPage() {
		logger.info("Trying to perform click to go to login page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
}