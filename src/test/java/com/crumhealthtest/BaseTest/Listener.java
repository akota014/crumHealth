package com.crumhealthtest.BaseTest;

import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.crumhealthtest.resources.ExtentReporterNG;

public class Listener extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getExtentReportes();
	
	ThreadLocal<ExtentTest> extenttest = new ThreadLocal<>();
	  public  void onTestStart(ITestResult result) {
		    // not implemented
		  System.out.println("Start");
		  String testName = result.getMethod().getMethodName();
		 ExtentTest	test = extent.createTest(testName);
		 	extenttest.set(test);
		  }

		  /**
		   * Invoked each time a test succeeds.
		   *
		   * @param result <code>ITestResult</code> containing information about the run test
		   * @see ITestResult#SUCCESS
		   */
		  public  void onTestSuccess(ITestResult result) {
		    // not implemented
			  extenttest.get().log(Status.PASS, "Passsed");
			  System.out.println("PAss");
		  }

		  /**
		   * Invoked each time a test fails.
		   *
		   * @param result <code>ITestResult</code> containing information about the run test
		   * @see ITestResult#FAILURE
		   */
		  public  void onTestFailure(ITestResult result) {
		    // not implemented
			  extenttest.get().log(Status.FAIL, result.getThrowable());
			  
			  WebDriver driver = null;
		       String screenshotPath = null;
			
			  System.out.println("I am printed");
			  try {
				  driver = getDriverFromClass(result);
			  }catch(Exception e) {
				  e.printStackTrace();
			  }	
			  if(driver !=null) {
				  try {
						screenshotPath = getScreenShot(driver,result.getMethod().getMethodName());
						extenttest.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
						System.out.println("Screenshot");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  }
			  else {
				  System.out.println("Driver is null — cannot capture screenshot");
			  }
		  }
		  
		  private WebDriver getDriverFromClass(ITestResult result) throws Exception {
			  Object testInstance = result.getInstance();
		        Class<?> clazz = testInstance.getClass();

		        Field driverField = clazz.getDeclaredField("driver");
		        driverField.setAccessible(true);

		        return (WebDriver) driverField.get(testInstance);
		  }

		  /**
		   * Invoked each time a test is skipped.
		   *
		   * @param result <code>ITestResult</code> containing information about the run test
		   * @see ITestResult#SKIP
		   */
		  public  void onTestSkipped(ITestResult result) {
		    // not implemented
		  }

		  /**
		   * Invoked each time a method fails but has been annotated with successPercentage and this failure
		   * still keeps it within the success percentage requested.
		   *
		   * @param result <code>ITestResult</code> containing information about the run test
		   * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
		   */
		  public  void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    // not implemented
		  }

		  /**
		   * Invoked each time a test fails due to a timeout.
		   *
		   * @param result <code>ITestResult</code> containing information about the run test
		   */
		  public  void onTestFailedWithTimeout(ITestResult result) {
		    onTestFailure(result);
		  }

		  /**
		   * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
		   * tag and calling all their Configuration methods.
		   *
		   * @param context The test context
		   */
		  public  void onStart(ITestContext context) {
		    // not implemented
		  }

		  /**
		   * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
		   * run and all their Configuration methods have been called.
		   *
		   * @param context The test context
		   */
		  public  void onFinish(ITestContext context) {
		    // not implemented
			  extent.flush();
		  }
		  
		
		
		}



