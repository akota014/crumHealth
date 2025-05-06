package com.crumhealthtest.test;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crumhealthtest.BaseTest.BaseTest;
import com.crumhealthtest.pageobject.LoginPage;

public class VerifyUserLogin extends BaseTest {
	
	@Test
	public void loginWithValidUsernamePassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login("John Doe", "ThisIsNotAPassword");
		Assert.assertEquals(driver.findElement(By.xpath("//h2[normalize-space()='Make Appointment']")).getText(),"Make Appointment");
	}
	
	@Test
	public void loginWithInalidUsernamePassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login("QWERT", "qwert");
		Assert.assertEquals(loginpage.getErrorMsg(),"Login failed! Please ensure the username and password are valid.");
	}
	
	@Test
	public void loginWithoutUsernamePassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login("", "");
		Assert.assertEquals(loginpage.getErrorMsg(),"Login failed! Please ensure the username and password are valid.");
		
	}
	
	@Test
	public void loginWithValidUsernameInvalidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login("John Doe", "QWERT");
		Assert.assertEquals(loginpage.getErrorMsg(),"Login failed! Please ensure the username and password are valid.");
		
	}
	
	@Test
	public void loginWithInvalidUsernameValidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login("QWERT", "ThisIsNotAPassword");
		Assert.assertEquals(loginpage.getErrorMsg(),"ogin failed! Please ensure the username and password are valid.");
		
	}
	
	@Test
	public void loginWithoutUsernameWithPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login("", "ThisIsNotAPassword");
		Assert.assertEquals(loginpage.getErrorMsg(),"Login failed! Please ensure the username and password are valid.");
		
	}
	@Test(dataProvider="getData")
	public void loginWithUsernameWithoutPassword(HashMap<String, String> input) {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login(input.get("email"), "");
		Assert.assertEquals(loginpage.getErrorMsg(),"Login failed! Please ensure the username and password are valid.");
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data= getDataFromJSON(System.getProperty("user.dir")+"//src//test//java//com//crumhealthtest//data//data.json");
		
		return new Object[][] {{data.get(0)}};
	}

}
