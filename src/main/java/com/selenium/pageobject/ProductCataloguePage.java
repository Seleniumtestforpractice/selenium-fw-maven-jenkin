package com.selenium.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.common.BasePage;

public class ProductCataloguePage extends BasePage {
	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productListElement;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By byLocator = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("div[class='card-body'] button:last-of-type");
	By toastMessage =By.cssSelector("#toast-container");

	public List<WebElement> getProductListElement() {
		waitElementToApper(byLocator);
		return productListElement;
	}

	public WebElement getProductByName(String productName) {
		System.out.println(productName);
		WebElement prodElement = getProductListElement().stream().filter(productElement -> productElement
				.findElement(By.cssSelector("b")).getText().trim().equals(productName)).findFirst()
				.orElse(null);
		return prodElement;
	}

	public void addProductToCart(String productName) {

		WebElement prodElement = getProductByName(productName);
		prodElement.findElement(addToCart).click();
		waitElementToApper(toastMessage);
		waitElementToDisapper(spinner);
	}

}
