package com.crumhealthtest.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@id='btn-make-appointment']")
	WebElement makeAppointment;
	
	@FindBy(css="#txt-username")
	WebElement userName;
	
	@FindBy(css="#txt-password")
	WebElement passWord;
	
	@FindBy(css="#btn-login")
	WebElement loginbutton;
	
	@FindBy(xpath="//p[@class='lead text-danger']")
	WebElement errorMessage;
	
	public void login(String username, String password)
	{
		makeAppointment.click();
		userName.sendKeys(username);
		passWord.sendKeys(password);
		loginbutton.click();
	}
	
	public String getErrorMsg() {
		return errorMessage.getText();
	}
}
