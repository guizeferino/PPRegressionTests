package PPRegressionTests.corretora;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class criaPessoaCPFCorretora {

    WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException {

    }

    @org.testng.annotations.BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        baseUrl = "http://10.90.0.63:8080/confidence/index.html";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @org.testng.annotations.Test
    public void test() {

        driver.get("http://10.90.0.63:8080/confidence/index.html");
    }

}
