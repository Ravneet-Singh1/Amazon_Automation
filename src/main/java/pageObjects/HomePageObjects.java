package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObjects {

	WebDriver driver;

	public HomePageObjects(WebDriver driver) {

		this.driver = driver;
	}

	By dropdown = By.id("searchDropdownBox");

	By searchBar = By.id("twotabsearchtextbox");

	By search = By.id("nav-search-submit-button");

	By signIn = By.id("nav-link-accountList-nav-line-1");

	By returnsAndOrders = By.id("nav-orders");

	public By cart = By.id("nav-cart-count-container");
	
	By language = By.id("icp-nav-flyout");
	
	By amazonLogo = By.id("nav-logo-sprites");
	

	public WebElement Dropdown() {
		return driver.findElement(dropdown);
	}
	
	public WebElement SearchBar() {
		return driver.findElement(searchBar);
	}
	
	public WebElement Search() {
		return driver.findElement(search);
	}
	
	public WebElement SignIn() {
		return driver.findElement(signIn);
	}
	
	public WebElement ReturnsAndOrders() {
		return driver.findElement(returnsAndOrders);
	}
	
	public WebElement Cart() {
		return driver.findElement(cart);
	}
	
	public WebElement Language() {
		return driver.findElement(language);
	}
	
	public WebElement AmazonLogo() {
		return driver.findElement(amazonLogo);
	}

}
