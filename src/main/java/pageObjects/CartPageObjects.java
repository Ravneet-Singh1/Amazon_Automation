package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPageObjects {

	WebDriver driver;
	
	public CartPageObjects(WebDriver driver){
		this.driver = driver;
	}
	
	public By proceedBuy = By.xpath("//input[@name='proceedToRetailCheckout']");
	
	public By address = By.xpath("//input[@aria-labelledby='orderSummaryPrimaryActionBtn-announce']");

	
	public WebElement ProceedBuy() {
		return driver.findElement(proceedBuy);
	}
	
	public WebElement Address() {
		return driver.findElement(address);
	}
}
