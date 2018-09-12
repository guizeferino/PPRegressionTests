package PPRegressionTests.pages;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import PPRegressionTests.utils.Util;

public class LoginPage {
	
	
	WebDriver driver;
	Util util;
	WebDriverWait wait;
	
	@FindBy(id="j_username")
	WebElement txtUserName;
	
	@FindBy(id="password")
	WebElement txtPassWord;

	
	
	public LoginPage(WebDriver driver) {
		util = new Util();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void logarNoSocc() {
		
		Properties usuario = new Properties();
		util.lerArquivo("files/usuarios.properties", usuario);
		util.waitVisibilityOfElement(txtUserName, wait, driver);
		txtUserName.sendKeys(usuario.getProperty("USERDEXTRA"));
		txtUserName.sendKeys(Keys.RETURN);
		util.waitVisibilityOfElement(txtPassWord, wait, driver);
		txtPassWord.sendKeys(usuario.getProperty("SENHA"));
		txtPassWord.sendKeys(Keys.RETURN);
	}


}
