package com.crumhealthtest.test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crumhealthtest.BaseTest.BaseTest;
import com.crumhealthtest.pageobject.AppointmentPage;
import com.crumhealthtest.pageobject.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Rough extends BaseTest{
	static WebDriver driver;
//	@Test
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions option=new ChromeOptions();
		final Map<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("credentials_enable_service", false);
		chromePrefs.put("profile.password_manager_enabled", false);
		chromePrefs.put("profile.password_manager_leak_detection", false);
		option.setExperimentalOption("prefs", chromePrefs);
		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		
		driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).click();
		
		driver.findElement(By.cssSelector("#txt-username")).sendKeys("John Doe");
		driver.findElement(By.cssSelector("#txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("btn-book-appointment"))));
		
		WebElement fac=driver.findElement(By.id("combo_facility"));
		Select dropdown = new Select(fac);
		dropdown.selectByValue("Tokyo CURA Healthcare Center");
		
		WebElement check =driver.findElement(By.id("chk_hospotal_readmission"));
		if(!check.isSelected()) check.click();
		
		driver.findElement(By.id("txt_visit_date")).click();
		dateFinder("12","December","2025");
		
		driver.findElement(By.id("txt_comment")).sendKeys("LALALA");
		
		driver.findElement(By.id("btn-book-appointment")).click();
		
		
	}
	
	public static void dateFinder(String expDate,String expMonth,String expYear) {
		WebElement monthFinder = driver.findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[2]/th[@class='datepicker-switch']"));
		
		WebElement nextMonth = driver.findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[2]/th[@class='next']"));
		
		WebElement prevMonth = driver.findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[2]/th[@class='prev']"));
		
		while(true) {
			String displayedMonthYear = monthFinder.getText();
			if(displayedMonthYear.equalsIgnoreCase(expMonth+" "+expYear)) {
				break;
			}
			nextMonth.click();
		}
		
		List<WebElement> days = driver.findElements(By.xpath("//table[@class='table-condensed']/tbody/tr/td"));
		days
		.stream()
		.filter(day->day.getText().equalsIgnoreCase(expDate))
		.findFirst()
		.ifPresent(WebElement::click);
	}
}
