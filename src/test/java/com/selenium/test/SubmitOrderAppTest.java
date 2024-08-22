package com.selenium.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.common.BaseTest;
import com.selenium.pageobject.CartPage;
import com.selenium.pageobject.CheckoutPage;
import com.selenium.pageobject.ConfirmationPage;
import com.selenium.pageobject.OrderPage;
import com.selenium.pageobject.ProductCataloguePage;

public class SubmitOrderAppTest extends BaseTest {

	

	/**@Test(dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(String userEmail,String userPassword,String productName ) throws IOException {

		ProductCataloguePage productCataloguePage = landingPage.loginApplication(userEmail, userPassword);
		productCataloguePage.addProductToCart(productName);

		CartPage cartPage = productCataloguePage.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.checkOut();

		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String orderActualMessage = confirmationPage.getConfirmPage();
		Assert.assertTrue(orderActualMessage.equalsIgnoreCase("Thankyou for the order."));

	}*/
	
	
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> map ) throws IOException {
        System.out.println("submitOrder Log")
		ProductCataloguePage productCataloguePage = landingPage.loginApplication(map.get("userEmail"), map.get("userPassword"));
		productCataloguePage.addProductToCart(map.get("productName"));

		CartPage cartPage = productCataloguePage.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(map.get("productName"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.checkOut();

		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
       
		String orderActualMessage = confirmationPage.getConfirmPage();
		Assert.assertTrue(orderActualMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	
	
	

	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistory() {
		String productName = "ZARA COAT 3";
		String userEmail = "seleniumtest2024@gmail.com";
		String userPassword = "Welcome@01";
		ProductCataloguePage productCataloguePage = landingPage.loginApplication(userEmail, userPassword);
		OrderPage orderPage = productCataloguePage.goToOrderPage();
		Assert.assertTrue(orderPage.isOrderPlaced(productName));
	}
	
	/**@DataProvider
	public Object[][] getData() {
		
		return new Object[][] {{"seleniumtest2024@gmail.com","Welcome@01","ZARA COAT 3"},{"shakti@gmail.com","Test@01","IPHONE 13 PRO"}};
	}*/
	
	
	//Integration of Hashmap to Data provider to send the data as one Hash object
	
	/**@throws IOException 
	 * @DataProvider
	public Object[][] getData() {
		
		HashMap<String, String> map1=new HashMap();
		map1.put("userEmail", "seleniumtest2024@gmail.com");
		map1.put("userPassword", "Welcome@01");
		map1.put("productName", "ZARA COAT 3");
	
		HashMap<String, String> map2=new HashMap();
		map2.put("userEmail", "shakti@gmail.com");
		map2.put("userPassword", "Test@01");
		map2.put("productName", "IPHONE 13 PRO");
		
		return new Object[][] {{map1},{map2}};
		
	}*/
	
	// How to read the data from Json files and create the list of Hashmaps for testing
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/com/selenium/data/purchase-orders.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
	
	
	
	
	
	
}
