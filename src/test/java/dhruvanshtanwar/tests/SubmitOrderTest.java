package dhruvanshtanwar.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dhruvanshtanwar.TestComponents.BaseTest;
import dhruvanshtanwar.pageobjects.CartPage;
import dhruvanshtanwar.pageobjects.CheckoutPage;
import dhruvanshtanwar.pageobjects.ConfirmationPage;
import dhruvanshtanwar.pageobjects.OrderPage;
import dhruvanshtanwar.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	// This class is for all the Positive Test cases
	public String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> inputHashMap) throws InterruptedException, IOException {

		// LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication(inputHashMap.get("email"),
				inputHashMap.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		CartPage cartPage = productCatalogue.addProductToCart(inputHashMap.get("productName"));
		cartPage.goToCartPage();
		Boolean match = cartPage.verifyProductsDisplay(inputHashMap.get("productName"));
		Assert.assertTrue(match);
		cartPage.goToCheckout();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Assert.assertTrue(confirmationPage.getConfirmationMessage());
		System.out.println(confirmationPage.getConfirmationMessage());
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("dhruvansht@gmail.com", "Hardrockcafe1.");
		OrderPage orderPage = productCatalogue.goToOderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay("ZARA COAT 3"));
		System.out.println(productName + " found in the orders");
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		// New implementation using Json file

		List<HashMap<String, String>> listOfHashMaps = getDataToJson(
				System.getProperty("user.dir") + "\\src\\main\\java\\dhruvanshtanwar\\data\\PurchaseOrder.json");

		return new Object[][] { { listOfHashMaps.get(0) }, { listOfHashMaps.get(1) } };

	}

	// Previous implementation using HashMaps
//	HashMap<String, String> hashMap = new HashMap<String, String>();
//	hashMap.put("email", "dhruvansht@gmail.com");
//	hashMap.put("password", "Hardrockcafe1.");
//	hashMap.put("productName", "ZARA COAT 3");
//
//	HashMap<String, String> hashMap1 = new HashMap<String, String>();
//	hashMap1.put("email", "tanwar@gmail.com");
//	hashMap1.put("password", "Hardrockcafe1.");
//	hashMap1.put("productName", "ADIDAS ORIGINAL");

}
