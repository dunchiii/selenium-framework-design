package dhruvanshtanwar.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dhruvanshtanwar.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	// Defining elememnts on page object model
	// Page object should not hold any data like emails and password
	// it should only have web elements, locators and actions
	

	// Defining things under constructor since they will be the first one to execute


	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Page Factory
	// how does this know about driver = PageFactory.initElements(driver, this);
	// List<WebElement> cartProducts =
	// driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	// driver.findElement(By.cssSelector(".action__submit"))

	// driver.findElement(By.cssSelector(".hero-primary"))
	@FindBy(css = ".hero-primary")
	WebElement thankyouText;
	
	// driver.findElement(By.cssSelector(".hero-primary"))
	@FindBy(css = ".hero-primary")
	List<WebElement> orders;

	public Boolean getConfirmationMessage() {
		Boolean b = thankyouText.getText().contains("THANKYOU");
		return b;
	}

	public List<WebElement> getOrderIDS() {
		List<WebElement> ordersIds = this.orders;
		return ordersIds;
	}

}