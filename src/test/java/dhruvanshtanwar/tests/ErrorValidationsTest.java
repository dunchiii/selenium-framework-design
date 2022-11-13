package dhruvanshtanwar.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import dhruvanshtanwar.TestComponents.BaseTest;
import dhruvanshtanwar.pageobjects.CartPage;
import dhruvanshtanwar.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	//This class is for all the Negative Test Cases

	@Test(groups = {"ErrorHandling"})
	public void loginErrorValidation() throws InterruptedException, IOException {

//		String productName = "ZARA COAT 3";
		
		//Passing wrong email and password
		landingPage.loginApplication("dhruvanht@gmail.com", "Hardrockcafe1");
		Assert.assertEquals("Incorrect email  passsword.", landingPage.getErrorMessage());
		//#toast-container > div
		//class = ng-tns-c4-7 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
		System.out.println("Incorrect email or password.");
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException, IOException {

		String productName = "ZARA COAT 3";
		//LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication("dhruvansht@gmail.com", "Hardrockcafe1.");
		List<WebElement> products = productCatalogue.getProductList();
		CartPage cartPage = productCatalogue.addProductToCart(productName);
		cartPage.goToCartPage();
		Boolean match = cartPage.verifyProductsDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
}
