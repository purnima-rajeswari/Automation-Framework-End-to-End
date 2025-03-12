package com.ui.test;

import com.ui.pojo.UserCredentials;
import com.utility.LoggerUtility;

import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.constants.BrowserSelection.*;

@Listeners({com.ui.Listeners.TestListener.class})
public class DataProviderForLoginTest extends TestBase{
	
	Logger logger=LoggerUtility.getLogger(this.getClass()) ;
	
	
	@Test(description ="valid credentials to allow the user to loign",groups= {"e2e","sanity"},dataProviderClass=com.ui.DataProviders.LoginDataProvider.class,dataProvider="LoginTestDataProvider")
	public  void loginJSONTest(UserCredentials user) {
		 
		assertEquals(homePage.goToLoginPage().loginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Purnima r");
	}
	@Test(description ="valid credentials to allow the user to loign",groups= {"e2e","sanity"},dataProviderClass=com.ui.DataProviders.LoginDataProvider.class,dataProvider="LoginTestCSVDataProvider")
	public  void loginCSVTest(UserCredentials user) {
		 
		assertEquals(homePage.goToLoginPage().loginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Purnima r");
	}
	@Test(description ="valid credentials to allow the user to loign",groups= {"e2e","sanity"},dataProviderClass=com.ui.DataProviders.LoginDataProvider.class,dataProvider="LoginTestXLDataProvider",retryAnalyzer=com.ui.Listeners.MyRetryAnalyzer.class)
	public  void loginExcelFileReadTest(UserCredentials user) {
		
		assertEquals(homePage.goToLoginPage().loginWith(user.getEmailAddress(),user.getPassword()).getUserName(),"Purnima r");
		
	}

}
