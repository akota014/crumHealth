package com.crumhealthtest.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crumhealthtest.Abstract.AbstractComponent;

public class LoginPage extends AbstractComponent{
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
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
	
	public void makeAppointment() {
		makeAppointment.click();
	}
	
	public AppointmentPage login(String username, String password)
	{
		
		userName.sendKeys(username);
		passWord.sendKeys(password);
		loginbutton.click();
		
		return new AppointmentPage(driver);
	}
	
	public String getErrorMsg() {
		return errorMessage.getText();
	}
	
	public String getPasswordType() {
		waitforWebElement(passWord);
		return passWord.getDomAttribute("type");
	}
}
