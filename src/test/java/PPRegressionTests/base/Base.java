package PPRegressionTests.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	protected WebDriver driver;
	private String baseUrl;
	

	
	public Base(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeTest
	public void setUp() {
		baseUrl = "http://10.90.0.63:8080/confidence/index.html";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseUrl);
		System.out.println("ROdando BEFORE Test");
		System.out.println("Valor do driver: " + this.driver);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	@AfterMethod
	public void returnToHomePage() {
		
	}

	@AfterTest
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}


}
