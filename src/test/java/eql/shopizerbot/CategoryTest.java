package eql.shopizerbot;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryTest {
	WebDriver driver;
	static Logger logger = LoggerFactory.getLogger(CategoryPage.class);
	JavascriptExecutor js = (JavascriptExecutor) driver;  
	
	String BROWSER = System.getProperty("browser");
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
		driver.get("http://192.168.56.1:9999/shopizer");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		WelcomePage page_welcome = PageFactory.initElements(driver, WelcomePage.class);
		SocleTechnique page_socle = PageFactory.initElements(driver, SocleTechnique.class);
		
		Actions action = new Actions(driver);
		action.moveToElement(page_welcome.bedroom_cat).perform();
		wait.until(ExpectedConditions.elementToBeClickable(page_welcome.table_sub_cat));
		page_welcome.table_sub_cat.click();
		
		String URL = driver.getCurrentUrl();
		logger.info("Checking if sub-category was accessed correctly");
		assertEquals(URL, "http://192.168.56.1:9999/shopizer/shop/category/night-tables.html/ref=c:100" );
		
		Thread.sleep(2000);
		
		page_welcome.tables_cat.click();
		CategoryPage page_cat = PageFactory.initElements(driver, CategoryPage.class);
		logger.info("Checking one product display");
		assertTrue(page_cat.coffee_table.isDisplayed());
		logger.info("Checking one product name");
		assertTrue(page_cat.title_plp_coffee.isDisplayed());
		logger.info("Checking one product price");
		assertTrue(page_cat.price_plp_coffee.isDisplayed());
		logger.info("Checking one product presence which should disapear after filter");
		assertTrue(page_cat.natural_root.isDisplayed());
		logger.info("Applying filter");
		Thread.sleep(2000);
		page_cat.filter_asian_wood.click();
		SocleTechnique.isElementPresent(page_cat.natural_root, logger);
		//assertFalse(page_cat.natural_root.isDisplayed());
		logger.info("Checking one product display");
		
		page_socle.explicitScrollIntoView(driver, page_cat.add_to_cart_coffee_table, false);
		
		assertTrue(page_cat.coffee_table.isDisplayed());
		assertTrue(page_cat.title_plp_coffee.isDisplayed());
		assertTrue(page_cat.price_plp_coffee.isDisplayed());
		
		String coffePrice = page_cat.price_plp_coffee.getText();
		String coffeTitle = page_cat.title_plp_coffee.getText();
		logger.info("Acces one product display page");
		page_cat.coffee_table.click();
		logger.info("Checking conformity between PDP and PLP info");
		assertEquals(coffePrice, page_cat.price_pdp_coffee.getText() );
		
		String coffeeTitlePdp = page_cat.title_pdp_coffee.getText().replace("table", "Table");
		assertEquals(coffeTitle, coffeeTitlePdp );
		logger.info("Checking stars display");
		page_cat.checkStars(driver);
		logger.info("Checking add to car button is displayed");
		assertTrue(page_cat.add_to_cart.isDisplayed());
	}

}
