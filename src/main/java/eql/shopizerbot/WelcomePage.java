package eql.shopizerbot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage {
	@FindBy(xpath = "//a[@href='/shopizer/shop/product/thai-flat-cussion.html']/following-sibling::div[@class='store-btn']/descendant::a[@class='addToCart']")
	public WebElement thai_flat_add;
	
	@FindBy(xpath = "//a[@href='/shopizer/shop/product/compact-night-table.html']/following-sibling::div[@class='store-btn']/descendant::a[@class='addToCart']")
	public WebElement night_table;
	
	@FindBy(xpath = "//span[contains(@class,'lnr-cart')]")
	public WebElement cart;
	
	@FindBy(xpath = "//span[contains(@class,'lnr-cart')]/following-sibling::*[@color='red']/descendant::strong")
	public WebElement cart_quantity;
	
	@FindBy(xpath = "(//*[@onclick='viewShoppingCartPage();'])[1]")
	public WebElement basket_link;
	
	@FindBy(xpath = "//div[contains(@class,'mainmenu')]//a[contains(@href,'bedroom')]")
	public WebElement bedroom_cat;
	
	@FindBy(xpath = "//div[contains(@class,'mainmenu')]//a[contains(@href,'bedroom')]//following-sibling::ul//a[contains(@href,'tables')]")
	public WebElement table_sub_cat;
	
	@FindBy(xpath = "//div[contains(@class,'mainmenu')]//a[contains(@href,'category/tables')]")
	public WebElement tables_cat;
	
	
}
