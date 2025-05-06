package com.crumhealthtest.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crumhealthtest.BaseTest.BaseTest;
import com.crumhealthtest.pageobject.LoginPage;

public class Rough extends BaseTest{
	@Test
	public void loginWithoutUsernameWithPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login("", "ThisIsNotAPassword");
		Assert.assertEquals(loginpage.getErrorMsg(),"");
		
	}
}
