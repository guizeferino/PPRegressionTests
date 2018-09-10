package PPRegressionTests.Corretora;

import PPRegressionTests.dao.DataBaseTesting;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class criaPessoaCPFCorretora {

    WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException {

    }

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {

        driver = new ChromeDriver();
        baseUrl = "http://10.90.0.63:8080/confidence/index.html";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {

        driver.get("http://10.90.0.63:8080/confidence/index.html");
    }

}
