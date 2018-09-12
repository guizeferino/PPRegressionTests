package PPRegressionTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PPRegressionTests.utils.Util;

public class HomePage {
	
	WebDriver driver;
	Util util;
	WebDriverWait wait;
	
	@FindBy(id="cbxPerfilUsuarioLogado")
	WebElement dropDownPerfil;

	public HomePage(WebDriver driver) {
		util = new Util();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selecionaPerfil(String nomePerfil) {
		util.waitUntilElementTobeClickAble(dropDownPerfil, wait, driver);
		Select dropdownPerfil = new Select(dropDownPerfil);
		dropdownPerfil.selectByVisibleText(nomePerfil);
	}
}
