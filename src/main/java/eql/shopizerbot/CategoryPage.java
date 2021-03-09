package eql.shopizerbot;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class CategoryPage {
	WebDriver driver;
	static Logger logger = LoggerFactory.getLogger(CategoryPage.class);
	
	@FindBy(xpath = "//a[@href='/shopizer/shop/product/coffee-table-accacia.html']/following-sibling::div[@class='store-btn']/descendant::a[@class='addToCart']")
	public WebElement coffee_table_add;
	
	@FindBy(xpath = "(//div[contains(@class,'product-content')]/a[@href='/shopizer/shop/product/coffee-table-accacia.html'])[1]")
	public WebElement coffee_table;
	
	@FindBy(xpath = "//a[contains(@href,'filter')][contains(text(),'Asian')]")
	public WebElement filter_asian_wood;
	
	@FindBy(xpath = "//span[@itemprop= 'price']")
	public WebElement price_pdp_coffee;
	
	@FindBy(xpath = "//div[@class='stars']")
	public WebElement stars;
	
	@FindBy(xpath = "//img[@src='/shopizer/resources/img/stars/star-off.png']")
	public WebElement star_image;
	
	@FindBy(xpath = "//div[@class='sinple-c-title']/h3")
	public WebElement title_pdp_coffee;
	
	@FindBy(xpath = "//button[contains(@class, 'addToCart')]")
	public WebElement add_to_cart;
	
	@FindBy(xpath = "(//h3[contains(text(), 'Coffee table Accacia')])[1]")
	public WebElement title_plp_coffee;
	
	@FindBy(xpath = "(//a[contains(@href,'coffee')]/following-sibling::h4/span[@itemprop= 'price'])[1]")
	public WebElement price_plp_coffee;
	
	public void checkStars() {
		boolean test;
	List<WebElement> all_stars = driver.findElements(By.xpath("//img[@src='/shopizer/resources/img/stars/star-off.png']"));
    int RowCount = all_stars.size();
    if (RowCount == 5) {
    	test = true;
    	logger.info("Il y a bien 5 étoiles");
    }
    else {
    	test = false;
    	logger.info("Il n'y a pas 5 étoiles");
    }
    assertTrue(test);
    }
}
