package dhruvanshtanwar.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dhruvanshtanwar.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	// Defining elememnts on page object model
	// Page object should not hold any data like emails and password
	// it should only have web elements, locators and actions
	WebDriver driver;

	// Defining things under constructor since they will be the first one to execute
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	// driver.findElement(By.cssSelector("[placeholder*='Select']"))
	@FindBy(css = "[placeholder*='Select']")
	WebElement searchCountry;

	// driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)"))
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;

	// By.cssSelector("section[class*='ta']"
	By populateCountry = By.cssSelector("section[class*='ta']");

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(searchCountry, countryName).build().perform();

		// Waiting for India to show up
		// wait.until(ExpectedConditions.visibilityOfElementLocated(populateCountry));
		waitForElementToAppear(populateCountry);
		// selection India
		selectCountry.click();
	}
	
	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;
	
	// creating action method
	public ConfirmationPage submitOrder() {

		placeOrderButton.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
