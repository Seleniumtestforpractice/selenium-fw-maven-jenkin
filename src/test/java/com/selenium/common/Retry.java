package com.selenium.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//IRetry Analyzer to rerun the flaky failed Selenium tests in the framework
public class Retry implements IRetryAnalyzer {

	int count = 0;
	int MaxTry = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (count < MaxTry) {
			count++;
			return true;
		}

		return false;
	}

}
