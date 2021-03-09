package eql.shopizerbot;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketTest {
	
	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;  
	
	String BROWSER = "Chrome";//System.getProperty("browser");
	@Before
	public void setup() {
		driver = SocleTechnique.choisirNavigateur(BROWSER);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void test() throws InterruptedException {
		driver.get("http://192.168.102.120:9999/shopizer");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		WelcomePage page_welcome = PageFactory.initElements(driver, WelcomePage.class);
		
		wait.until(ExpectedConditions.elementToBeClickable(page_welcome.thai_flat_add));
		page_welcome.thai_flat_add.click();
		assertEquals(page_welcome.cart_quantity.getText(), "(1)");
		
		Actions action = new Actions(driver);
		action.moveToElement(page_welcome.cart).perform();
		wait.until(ExpectedConditions.elementToBeClickable(page_welcome.basket_link));
		page_welcome.basket_link.click();
		
		BasketPage page_basket = PageFactory.initElements(driver, BasketPage.class);
		
		page_basket.checkProductName("flat", "Thai flat cussion", driver);
		page_basket.checkProductPrice("flat", "US$59.99", driver);
		page_basket.checkTotalOneProductPrice("flat", "US$59.99", driver);
		page_basket.checkProductQuantity("flat", "1", driver);
		page_basket.changeProductQuantity("flat", "2", driver);
		
		page_basket.recalculer.click();
		
		Thread.sleep(2000);
		
		page_basket.checkProductQuantity("flat", "2", driver);
		page_basket.checkTotalOneProductPrice("flat", "US$119.98", driver);
		page_basket.checkTotalCart("US$119.98", driver);
		
		page_basket.checkout.click();
		String URL = driver.getCurrentUrl();
		assertEquals(URL, "http://192.168.102.120:9999/shopizer/shop/order/checkout.html" );
		page_basket.submit_order.isDisplayed();
		
		Thread.sleep(2000);
		
	}

}
