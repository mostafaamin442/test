package maven.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGParameters {

	public static WebDriver driver;

	// we added groups annotation to before test so that the testng.xml can open the driver 
	@BeforeTest(groups = {"Regression"})
	public void StartDriver(@Optional("chrome") String browsername) {
		if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		String url = "https://www.google.com/";
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	//............................................................

	// Priority 
	@Test(priority = 0 , groups = {"Regression"})
	public void OpenUdemy() {

		String udemy = "https://www.udemy.com/home/my-courses/learning/";
		driver.navigate().to(udemy);
		System.out.println("Running from jenkins");
	}

	//............................................................

	// always run parameter , groups
	@Test(priority = 1 , alwaysRun = true , groups = {"Regression"})
	public void OpenFace() {

		String Face = "https://www.facebook.com/";
		driver.navigate().to(Face);
	}

	//............................................................

	// disable specific test case
	@Test(priority = 2, enabled = true)

	public void OpenTwitter() {

		String Twitter = "https://www.linkedin.co/";
		driver.navigate().to(Twitter);

	}

	//............................................................

	// dependency
	//test
	@Test(dependsOnMethods = { "OpenFace" })
	public void OpenWhatsapp() {

		String Whatsapp = "https://web.whatsapp.com/";
		driver.navigate().to(Whatsapp);

	}
}
