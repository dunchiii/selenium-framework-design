package dhruvanshtanwar.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class SeleniumRefresh {

	WebDriver driver;
	public SeleniumRefresh(WebDriver driver) {
		
//		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Writing locator as Page Factory
	@FindBy(id = "id")
	WebElement submitBtn;

	
	
	public static void main(String[] args) throws Exception {

		// SSL Certificate
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);

		WebDriver driver = new ChromeDriver(options);
		driver.get("amazon.in");

		// Windows switching
		Set<String> windows = driver.getWindowHandles();

		Iterator<String> it = windows.iterator();
		String p = it.next();
		String ch = it.next();
		driver.switchTo().window(p);
		driver.switchTo().window(ch);

		// Broken Links
		List<WebElement> links = driver.findElements(By.tagName("a"));
		String url;
		for (WebElement link : links) {
			url = link.getAttribute("href");
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int code = conn.getResponseCode();
			if (code >= 400) {
				System.out.println("Link is broken");

			} else {
				System.out.println("Link is working");
			}
		}

		// Screenshot
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyDirectory(src, new File("PATH"));

		// ActionsClass
		Actions a = new Actions(driver);
		a.contextClick().build().perform();

		// ImpicitWat
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// ExplicitWait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("url")));

		// FluentWait
		Wait<WebDriver> foo = new FluentWait<WebDriver>(driver).withTimeout(null).pollingEvery(null)
				.ignoring(NoSuchElementException.class);

		// Frames
		driver.switchTo().frame(0);

		// LeftClick
		driver.findElement(By.id("id")).click();

		// Select Class or Dropdown
		Select s = new Select(driver.findElement(By.id("id")));
		s.selectByIndex(0);
		s.selectByValue("value");
		s.selectByVisibleText("visibleText");

		// Window Scrolling or Java Script Executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,500)");

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(false, false);

		// PageObjectModel
		/*
		 * file: browserName = chrome
		 */

		// WebDriver initializeDriver()
		{
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream("location of the prop file");
			prop.load(fis);
			String browserName = prop.getProperty("browserName");
		}
		// How does Base Test class get idea of the webdriver
		/*
		 * The initialize method above will return the driver object at the end and then
		 * this instance will be passed as an arguement to any other methods in the same
		 * class or any other class
		 */

	}
	
	
//	@DataProvider
	
}
