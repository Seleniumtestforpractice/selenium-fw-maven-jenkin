package com.selenium.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.selenium.common.BasePage;

public class CartPage extends BasePage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProductElements;

	@FindBy(css = ".totalRow button")
	WebElement checkout;

	public boolean verifyProductDisplay(String productName) {
		boolean match = cartProductElements.stream()
				.anyMatch(cartProductElement -> cartProductElement.getText().equals(productName));
		return match;
	}

	public CheckoutPage checkOut() {
		checkout.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
