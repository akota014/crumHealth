package com.crumhealthtest.test;

import org.testng.annotations.Test;

import com.crumhealthtest.BaseTest.BaseTest;
import com.crumhealthtest.pageobject.AppointmentPage;
import com.crumhealthtest.pageobject.LoginPage;

public class BookAppointmentTest extends BaseTest {

	@Test
	public void bookAppointment() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.makeAppointment();
		AppointmentPage ap =loginpage.login("John Doe", "ThisIsNotAPassword");
		ap.chooseFacility("Hongkong CURA Healthcare Center");
		ap.checkReadmission(true);
		ap.dateFinder("10", "December", "2025");
		ap.bookButton();
		
	}
}
