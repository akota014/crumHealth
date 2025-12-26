package com.crumhealthtest.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AppointmentPage {
	WebDriver driver;
	public AppointmentPage(WebDriver driver) {
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h2")
	WebElement heading;
	
	@FindBy(id="combo_facility")
	WebElement combo1;
	
	@FindBy(id="chk_hospotal_readmission")
	WebElement hospital_readmission;
	
	@FindBy(id="txt_visit_date")
	WebElement visit_date;
	
	@FindBy(id="txt_comment")
	WebElement txt_comment;
	
	@FindBy(id="btn-book-appointment")
	WebElement btn_appoint;
	
	@FindBy(xpath="//h2")
	WebElement headline;
	
	@FindBy(xpath="//div[@class='datepicker-days']/table/thead/tr[2]/th[@class='datepicker-switch']")
	WebElement monthFinder;
	
	@FindBy(xpath="//div[@class='datepicker-days']/table/thead/tr[2]/th[@class='next']")
	WebElement nextMonth;
	
	@FindBy(xpath="//table[@class='table-condensed']/tbody/tr/td")
	List<WebElement> days;
	
	public void chooseFacility(String val) {
		Select dropdown = new Select(combo1);
		dropdown.selectByValue(val);		
	}
	
	public void checkReadmission(Boolean check) {
		if(check==true) {
			if(!hospital_readmission.isSelected()) hospital_readmission.click();	
		}
		else {
			if(hospital_readmission.isSelected()) hospital_readmission.click();
		}
	}
	
	public boolean isButtonDisplayed() {
		return btn_appoint.isDisplayed();
	}
	
	public void dateFinder(String expDate,String expMonth,String expYear) {
		visit_date.click();
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
		
		txt_comment.click();
	}
	
	public void bookButton() {
		btn_appoint.click();
	}
	
	public void comment(String str) {
		txt_comment.click();
		txt_comment.sendKeys(str);
	}
}
