package com.selenium.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.common.BaseTest;
import com.selenium.common.Retry;
import com.selenium.pageobject.CartPage;
import com.selenium.pageobject.ProductCataloguePage;

public class ErrorValidationTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	String userEmail = "seleniumtest2024@gmail.com";
	String userPassword = "Welcome@01";

	@Test(groups= {"errorHandling"})
	public void loginValidation() throws IOException {

		String userEmail = "seleniumtest2025@gmail.com";
		
		landingPage.loginApplication(userEmail, userPassword);
		Assert.assertEquals(landingPage.getErrorMessage().trim(), "Incorrect email or password.");

	}

	@Test(retryAnalyzer = Retry.class)
	public void productErrorValidation() {

		

		ProductCataloguePage productCataloguePage = landingPage.loginApplication(userEmail, userPassword);
		productCataloguePage.addProductToCart(productName);

		CartPage cartPage = productCataloguePage.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}
}
