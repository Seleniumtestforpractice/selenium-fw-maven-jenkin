package com.selenium.common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.pageobject.CartPage;
import com.selenium.pageobject.OrderPage;

public class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[routerlink*='cart']")
	WebElement cartButton;

	@FindBy(css = "button[routerlink*='myorders']")
	WebElement orderButton;

	public void waitElementToApper(By locator) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitWebElementToApper(WebElement element) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driverWait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitElementToDisapper(WebElement element) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driverWait.until(ExpectedConditions.invisibilityOf(element));
	}

	public CartPage goToCartPage() {

		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrderPage goToOrderPage() {
		orderButton.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
