package com.crumhealthtest.BaseTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.crumhealthtest.pageobject.LoginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginpage;
	public WebDriver initializeDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option=new ChromeOptions();
		option.addArguments("headless");
		driver = new ChromeDriver();
		return driver;
	}
	
	public String getScreenShot(WebDriver driver,String testcaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"\\result\\"+testcaseName+".png");
		FileUtils.copyFile(source, destination);
		return destination.getPath();
	}
	
	public List<HashMap<String, String>> getDataFromJSON(String Filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(Filepath), 
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		
		return data;
	
	}
	
	@BeforeMethod(alwaysRun=true)
	public WebDriver launchApp() {
		driver=initializeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		
		return driver;
	}
	
	@AfterMethod(alwaysRun=true)
	public void Close() {
		driver.close();
	}

}
