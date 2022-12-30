package resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitializeDriver {

	WebDriver driver;

	public WebDriver initializeWebdriver() throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\ravneesi\\eclipse-workspace\\AmazonTestproject\\src\\test\\java\\data.properties");

		prop.load(fis);

		String browserName = prop.getProperty("browser");

		if (browserName.equals("Chrome")) {
//			System.setProperty("webdriver.chrome.driver",
//					"C:\\\\Users\\\\ravneesi\\\\OneDrive - Cisco\\\\Desktop\\\\Selenium\\\\Drivers\\\\chromedriver_win32\\\\chromedriver.exe");

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\ravneesi\\OneDrive - Cisco\\Desktop\\Selenium\\Drivers\\chromedriver_win32\\chromedriver.exe");

			driver = new ChromeDriver();
		}

		else if (browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\ravneesi\\OneDrive - Cisco\\Desktop\\Selenium\\Drivers\\geckodriver-v0.31.0-win64\\geckodriver.exe");

			driver = new FirefoxDriver();
		}

		else if (browserName.equals("InternetExplorer")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\ravneesi\\OneDrive - Cisco\\Desktop\\Selenium\\Drivers\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}
}
