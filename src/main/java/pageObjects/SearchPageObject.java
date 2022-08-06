package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPageObject {

	WebDriver driver;

	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public By selectSearchedItem = By.xpath("//span[text()='Apple iPhone 13 (128GB) - Green']");

	public By selectedProductName = By.id("productTitle");

	By percentageOff = By.cssSelector(
			".a-size-large.a-color-price.savingPriceOverride");

	public By productPrice = By.cssSelector(".a-price.aok-align-center");  // ?70,900
	//00
	
	public By productSize = By.id("size_name_1");
	
	By cart = By.id("add-to-cart-button");
	
	By quantity = By.cssSelector("#selectQuantity #quantity");
	
	public By sliderCart = By.cssSelector(".a-button.a-button-base.attach-button-large");
	
	
	
	
	

	public WebElement SelectSearchedItem() {
		return driver.findElement(selectSearchedItem);
	}

	public WebElement SelectedProductName() {
		return driver.findElement(selectedProductName);
	}

	public WebElement PercentageOff() {
		return driver.findElement(percentageOff);
	}

	public String ProductPrice() throws InterruptedException {
		Thread.sleep(3000);
		 String decimalPrice = driver.findElement(productPrice).getText();  // ?70,900
			                                                                //00
		 
		 return decimalPrice.replace("?", " ").trim();
		 
	}
	
	public WebElement ProductSize() {
		return driver.findElement(productSize);
	}

	public WebElement Cart() {
		return driver.findElement(cart);
	}
	
	public WebElement Quantity() {
		return driver.findElement(quantity);
	}
	
	public WebElement SliderCart() {
		return driver.findElement(sliderCart);
	}
}
