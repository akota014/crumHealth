package com.crumhealthtest.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	public static ExtentReports getExtentReportes() {
		String path = System.getProperty("user.dir")+"\\result\\index.html";
		ExtentSparkReporter extent =new ExtentSparkReporter(path);
		extent.config().setDocumentTitle("Test Report");
		extent.config().setReportName("Web Automation Result");
		
		ExtentReports ex = new ExtentReports();
		ex.attachReporter(extent);
		ex.setSystemInfo("CrumHealth", "Srinibash");
		return ex;
	}

}
