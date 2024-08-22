package com.selenium.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.common.BasePage;

public class OrderPage extends BasePage {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderNames;

	public boolean isOrderPlaced(String productName) {
		
		return orderNames.stream().anyMatch(orderName -> orderName.getText().equalsIgnoreCase(productName));
		

	}

}
