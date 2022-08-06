package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPageObjects {

	WebDriver driver;

	public SignInPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	By email = By.id("ap_email");

	By password = By.id("ap_password");

	public WebElement Email() {
		return driver.findElement(email);
	}

	public WebElement Password() {
		return driver.findElement(password);
	}
}
