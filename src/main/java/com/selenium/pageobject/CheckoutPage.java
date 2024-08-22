package com.selenium.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.selenium.common.BasePage;

public class CheckoutPage extends BasePage {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//button[contains(@class,'ta-item')] [2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;

	By countryListDisplay = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		Actions actions = new Actions(driver);
		actions.sendKeys(country, countryName).build().perform();
		waitElementToApper(countryListDisplay);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
