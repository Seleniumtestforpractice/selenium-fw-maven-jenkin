package com.selenium.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.common.BasePage;

public class ConfirmationPage extends BasePage {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(css = ".hero-primary")
	WebElement orderActualMessage;
	
	public String getConfirmPage() {
		 return orderActualMessage.getText();
	}
}
