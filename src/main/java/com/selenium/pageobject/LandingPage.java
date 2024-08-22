package com.selenium.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.common.BasePage;

public class LandingPage extends BasePage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement userEmail=driver.findElement(By.id("userEmail")); //We can write
	// this way or we can use Page Factory design pattern

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCataloguePage loginApplication(String email,String password) {

		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCataloguePage productCataloguePage=new ProductCataloguePage(driver);
		return productCataloguePage;
	}
	
	public String getErrorMessage() {
		
		waitWebElementToApper(errorMessage);
		return errorMessage.getText();
	}
}
