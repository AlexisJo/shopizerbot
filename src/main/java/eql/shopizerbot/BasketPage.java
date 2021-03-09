package eql.shopizerbot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class BasketPage {
	static Logger logger = LoggerFactory.getLogger(CategoryPage.class);
	
	WebDriver driver;
	
	@FindBy(xpath = "//*[contains(@onclick, 'updateCart')]")
	public WebElement recalculer;
	
	@FindBy(xpath = "//a[@href='/shopizer/shop/order/checkout.html']")
	public WebElement checkout;
	
	@FindBy(xpath = "//button[@id='submitOrder']")
	public WebElement submit_order;
	
	@FindBy(xpath = "//span[@itemprop= 'price']")
	public WebElement price_cart_coffee;
	
	@FindBy(xpath = "//div[@class='sinple-c-title']/h3")
	public WebElement title_pdp_coffee;
	
	@FindBy(xpath = "(//span[@class='amount'])[2]")
	public WebElement cart_total;
	
	public void changeProductQuantity (String product, String quantity, WebDriver driver) {
		
		driver.findElement(By.xpath("(//*[contains(text(),'"+product+"')]/following::input[contains(@class,'quantity')])[1]")).clear();
		driver.findElement(By.xpath("(//*[contains(text(),'"+product+"')]/following::input[contains(@class,'quantity')])[1]")).sendKeys(quantity);
		//logger.info("Checking if quantity has been changed correctly");
		//assertEquals(driver.findElement(By.xpath("(//*[contains(text(),'"+product+"')]/following::input[contains(@class,'quantity')])[1]")).getText(), quantity);
	}
	
	public void checkProductQuantity (String product, String quantity, WebDriver driver) {
		
		//driver.findElement(By.xpath("(//*[contains(text(),'"+product+"')]/following::input[contains(@class,'quantity')])[1]")).sendKeys(quantity);
		logger.info("Checking if product's quantity is correct");
		System.out.println(driver.findElement(By.xpath("(//*[contains(text(),'"+product+"')]/following::input[contains(@class,'quantity')])[1]")).getAttribute("value").toString());
		assertEquals(driver.findElement(By.xpath("(//*[contains(text(),'"+product+"')]/following::input[contains(@class,'quantity')])[1]")).getAttribute("value"), quantity);
		
	}
	
	public void checkProductPrice (String product, String price, WebDriver driver) {
		
		//driver.findElement(By.xpath("(//*[contains(text(),'"+product+"')]/following::input[contains(@class,'quantity')])[1]")).sendKeys(quantity);
		logger.info("Checking if unit price is correct");
		assertEquals(driver.findElement(By.xpath("//*[contains(text(),'"+product+"')]/ancestor::td/following-sibling::td[contains(@data-th, 'Prix')]")).getText(), price);
	}
	
	public void checkTotalOneProductPrice (String product, String price, WebDriver driver) {
		
		//driver.findElement(By.xpath("(//*[contains(text(),'"+product+"')]/following::input[contains(@class,'quantity')])[1]")).sendKeys(quantity);
		logger.info("Checking if total price of one product is correct");
		assertEquals(driver.findElement(By.xpath("//*[contains(text(),'"+product+"')]/ancestor::td/following-sibling::td[contains(@data-th, 'Total')]")).getText(), price);
	}
	
	public void checkProductName (String product, String name, WebDriver driver) {
		
		//driver.findElement(By.xpath("(//*[contains(text(),'"+product+"')]/following::input[contains(@class,'quantity')])[1]")).sendKeys(quantity);
		logger.info("Checking if name is correct");
		System.out.println(driver.findElement(By.xpath("//strong[contains(text(),'"+product+"')]")).getText());
		assertEquals(driver.findElement(By.xpath("//strong[contains(text(),'"+product+"')]")).getText(), name);
	}
	
	public void checkTotalCart (String price, WebDriver driver) {
		
		//driver.findElement(By.xpath("(//*[contains(text(),'"+product+"')]/following::input[contains(@class,'quantity')])[1]")).sendKeys(quantity);
		logger.info("Checking if total price in cart is correct");
		assertEquals(cart_total.getText(), price);
	}
}
