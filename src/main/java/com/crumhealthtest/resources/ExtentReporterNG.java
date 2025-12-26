package com.crumhealthtest.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	 private static ExtentReports extent;
	public static ExtentReports getExtentReportes() {
		 if (extent == null) {
	            String path = System.getProperty("user.dir") + "/reports/index.html";
	            System.out.println(System.getProperty("user.dir"));
	            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	            reporter.config().setReportName("Automation Results");
	            reporter.config().setDocumentTitle("Test Results");

	            extent = new ExtentReports();
	            extent.attachReporter(reporter);
	            extent.setSystemInfo("QA Engineer", "Your Name");
	        }

	        return extent;
	}

}
