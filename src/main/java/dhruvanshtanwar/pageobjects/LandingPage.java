package dhruvanshtanwar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dhruvanshtanwar.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	// Defining elememnts on page object model
	// Page object should not hold any data like emails and password
	// it should only have web elements, locators and actions
	WebDriver driver;

	// Defining things under constructor since they will be the first one to execute
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Page Factory
	// how does this know about driver - PageFactory.initElements(driver, this);
	@FindBy(id = "userEmail")
	WebElement userEmail;

	// driver.findElement(By.cssSelector("#userPassword"))
	@FindBy(id = "userPassword")
	WebElement userPassword;

	// driver.findElement(By.id("login"))
	@FindBy(id = "login")
	WebElement submit;
	
	//
	@FindBy(css = "[class*=flyInOut]")
	WebElement errorMessage;

	// creating action method
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	// creating action method
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
