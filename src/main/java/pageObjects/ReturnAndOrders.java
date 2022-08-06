package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReturnAndOrders {

	WebDriver driver;

	public ReturnAndOrders(WebDriver driver) {
		this.driver = driver;
	}

	public By cancelledOrders = By.cssSelector(".selected");

	By searchOrders = By.id("searchOrdersInput");

	public WebElement CancelledOrders() {
		return driver.findElement(cancelledOrders);
	}

	public WebElement SearchOrders() {
		return driver.findElement(searchOrders);
	}
}
