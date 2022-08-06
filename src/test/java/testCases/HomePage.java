package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pageObjects.CartPageObjects;
import pageObjects.HomePageObjects;
import pageObjects.ReturnAndOrders;
import pageObjects.SearchPageObject;
import pageObjects.SignInPageObjects;
import resource.InitializeDriver;

public class HomePage extends InitializeDriver {

	Properties prop;

	// These 2 class are used for extent reporting
	ExtentReports extent;
	ExtentSparkReporter reporter;

	private static Logger log = LogManager.getLogger(HomePage.class.getName()); // HomePage.class.getName() is to get
																				// the
																				// path of the class
	WebDriver driver;
	HomePageObjects hpObj;
	SignInPageObjects sinObj;
	ReturnAndOrders raoObj;
	SearchPageObject spobj;

	WebDriverWait wait;

	String url;
	String phoneNumber;
	String password;
	String product;

	
	
	@BeforeTest
	public void config() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\ravneesi\\eclipse-workspace\\AmazonTestproject\\src\\test\\java\\data.properties");

		prop.load(fis);

		url = prop.getProperty("url");
		phoneNumber = prop.getProperty("PhoneNumber");
		password = prop.getProperty("Password");
		product = prop.getProperty("Product");

		// =============================================================================================
		// EXTENT REPORTING
		// =============================================================================================

		String path = System.getProperty("user.dir") + "\\reports\\index.html"; // To create a folder
		reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName("Web Automation Report"); // Change the heading
		reporter.config().setDocumentTitle("Amazon Test Report"); // To set the title

		extent = new ExtentReports();
		extent.attachReporter(reporter); // To attach Extent Reports with ExtentSparkReporter
		extent.setSystemInfo("Tester", "Ravneet");

	}
	
	

	@Test
	public void basePage() throws IOException {

		extent.createTest("Demo Test");

		// Got the driver from "InitializeDriver" class & initializeWebdriver is the
		// method of that class

		driver = initializeWebdriver();

		driver.manage().window().maximize();

		// Open the browser
		log.info("Opening the browser");
		driver.get(url);
		log.info("Browser opened successfully !!");

		// =============================================================================================
		// HOME PAGE
		// =============================================================================================

		hpObj = new HomePageObjects(driver);

		log.info("Going to signIn page");
		hpObj.SignIn().click();
		log.info("On " + driver.getTitle());

	}

	
	
	// =============================================================================================
	// LOGIN SCREEN
	// =============================================================================================

	@Test(dependsOnMethods = { "basePage" })
	public void LogInTest() {

		sinObj = new SignInPageObjects(driver);

		log.info("Entering Phone number");
		sinObj.Email().sendKeys(phoneNumber);
		sinObj.Email().submit();

		log.info("Entering Password");
		sinObj.Password().sendKeys(password);
		sinObj.Password().submit();
		log.info(driver.getTitle() + " Sucessfull !!");
	}
	
	
	// =============================================================================================
	// Returns And Orders
	// =============================================================================================

	@Test(dependsOnMethods = { "LogInTest" })
	public void ReturnAndOrders() {

		hpObj.ReturnsAndOrders().click();
		log.info("Moved to Return & Orders page");

		raoObj = new ReturnAndOrders(driver);

		log.info("Testing search Orders..");
		raoObj.SearchOrders().sendKeys("Iphone13");
		raoObj.SearchOrders().submit();
		log.info("Checked Search orders");

		log.info("Clicking amazon logo, to come to home screen");
		hpObj.AmazonLogo().click();
		log.info("Back to home screen");

	}
	// =============================================================================================
	// SEARCH FOR PRODUCT
	// =============================================================================================

	@Test(dependsOnMethods = { "ReturnAndOrders" })
	public void SearchProduct() throws InterruptedException {

		log.info("Searching for required product..");
		hpObj.SearchBar().sendKeys(product);
		hpObj.SearchBar().submit();
		log.info("Sucessfully searched for required product !!");

		// =============================================================================================
		// OPENING SELECTED PRODUCTS
		// =============================================================================================

		log.info("Search for Iphone13 Green colour..");
		spobj = new SearchPageObject(driver);

		// Adding Explicit wait

		wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(spobj.selectSearchedItem));

		spobj.SelectSearchedItem().click();
		log.info("Successfully clicked on Iphone 13 Green colour");

		// Here a new tab is opened, so we need to handle the windows

		Set<String> windows = driver.getWindowHandles(); // To handle the windows [parentId, childID]
		Iterator<String> iterator = windows.iterator(); // To iterate between the ID's

		String parentID = iterator.next();
		String childID = iterator.next();

		driver.switchTo().window(childID);

		// Adding Explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(spobj.selectedProductName));

		log.info("Selected Product details are mentioned below");
		log.debug("Product Name : " + spobj.SelectedProductName().getText());
		log.debug("Discount offered : " + spobj.PercentageOff().getText());
		log.debug("Product Price : " + spobj.ProductPrice());

		log.info("Changing product size");
		// Scrolling down the page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		// Explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(spobj.productSize));
		spobj.ProductSize().click();
		log.info("256 GB size selected");

		log.info("Updating price");
		wait.until(ExpectedConditions.visibilityOfElementLocated(spobj.productPrice));
		log.info("New product price : " + spobj.ProductPrice());

		log.info("Selecting quantity as 2");
		Select dropdown = new Select(spobj.Quantity());
		dropdown.selectByValue("2");

	}

	// =============================================================================================
	// ADDING PRODUCT IN CART
	// =============================================================================================

	@Test(dependsOnMethods = { "SearchProduct" })
	public void ProductInCart() {

		log.info("Adding product to cart");
		spobj.Cart().click();
		log.info("Product added to cart successfully !!");

		log.info("Moving to cart");
		// Explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(hpObj.cart));
		hpObj.Cart().click();
		log.info("Moved to cart");

		log.info("Proceed to Buy");
		CartPageObjects cpObj = new CartPageObjects(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cpObj.proceedBuy));
		cpObj.ProceedBuy().click();
		log.info("Moved to purchase page");

		log.info("Use this address");
		wait.until(ExpectedConditions.visibilityOfElementLocated(cpObj.address));
		cpObj.Address().click();
		log.info("Proceeded, to Astabal camp Address");
	}

	// =============================================================================================
	// CLOSING THE BROWSERS
	// =============================================================================================

	@AfterClass
	public void newTest() {
		extent.flush();
		log.info("Closing the driver");
		driver.quit();
	}

}
