package dhruvanshtanwar.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dhruvanshtanwar.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	// Defining elememnts on page object model
	// Page object should not hold any data like emails and password
	// it should only have web elements, locators and actions
	WebDriver driver;

	// Defining things under constructor since they will be the first one to execute
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Page Factory
	// how does this know about driver = PageFactory.initElements(driver, this);
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName));
		return match;
	}
//
//	public CheckoutPage goToCheckout() {
//		checkoutButton.click();
//		return new CheckoutPage(driver);
//		
//		
//	}
}