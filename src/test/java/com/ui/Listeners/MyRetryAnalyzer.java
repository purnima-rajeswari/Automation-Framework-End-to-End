package com.ui.Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Environments;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer{
//private static final int MAX_NUMBER_OF_ATTEMPTS= Integer.parseInt(PropertiesUtil.readProperty(Environments.DEV, "MAX_NUMBER_OF_ATTEMPTS"));
private static final int MAX_NUMBER_OF_ATTEMPTS=JSONUtility.readJSON(Environments.QA).getMAX_NUMBER_OF_ATTEMPTS();
private static int currentAttempt=1;
	@Override
	public boolean retry(ITestResult result) {
		if (currentAttempt<=MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		return false;
	}

}
