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
import com.crumhealthtest.pageobject.AppointmentPage;
import com.crumhealthtest.pageobject.LoginPage;

public class VerifyUserLoginTest extends BaseTest {
	
	@Test
	public void loginWithValidUsernamePassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.makeAppointment();
		AppointmentPage ap =loginpage.login("John Doe", "ThisIsNotAPassword");
		
		Assert.assertTrue(ap.isButtonDisplayed(),"\"Login failed!");
	}
	
	@Test
	public void loginWithInalidUsernamePassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.makeAppointment();
		loginpage.login("QWERT", "qwert");
		Assert.assertEquals(loginpage.getErrorMsg(),"Login failed! Please ensure the username and password are valid.");
	}
	
	@Test
	public void loginWithoutUsernamePassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.makeAppointment();
		loginpage.login("", "");
		Assert.assertEquals(loginpage.getErrorMsg(),"Login failed! Please ensure the username and password are valid.");
		
	}
	
	@Test
	public void loginWithValidUsernameInvalidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.makeAppointment();
		loginpage.login("John Doe", "QWERT");
		Assert.assertEquals(loginpage.getErrorMsg(),"Login failed! Please ensure the username and password are valid.");
		
	}
	
	@Test
	public void loginWithInvalidUsernameValidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.makeAppointment();
		loginpage.login("QWERT", "ThisIsNotAPassword");
		Assert.assertEquals(loginpage.getErrorMsg(),"Login failed! Please ensure the username and password are valid.");
		
	}
	
	@Test
	public void passwordMasking() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.makeAppointment();
//		String str = ;
		Assert.assertEquals(loginpage.getPasswordType(),"password","Password type is not password");
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data= getDataFromJSON(System.getProperty("user.dir")+"//src//test//java//com//crumhealthtest//data//data.json");
		
		return new Object[][] {{data.get(0)}};
	}

}
