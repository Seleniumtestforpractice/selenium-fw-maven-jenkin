package com.selenium.common;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.resources.ExtentReportConfig;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test =null;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>(); // To resolve concurrency problem - Fully Synchronized 
	ExtentReports extentReports=ExtentReportConfig.getExtentReport();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test=extentReports.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // ThreadLocal create an unique id and mapped to ExtentTest class object
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.INFO, "Success");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.FAIL, new Throwable());
		
		// Take Screenshot 
		try {
			WebDriver driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			String path=getScreenshot(result.getMethod().getMethodName(),driver);
			extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
			
		} catch (IOException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReports.flush();
	}

}
