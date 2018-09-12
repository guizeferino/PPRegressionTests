package PPRegressionTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import PPRegressionTests.utils.Util;

public class MenusPage {
	WebDriver driver;
	Util util;
	WebDriverWait wait;
	
	@FindBy(linkText="Cadastros")
	WebElement menuCadastros;
	
	@FindBy(linkText="Gerenciar Pessoa Física (Corretora)")
	WebElement submenuGerenciaPessoaFisicaCorretora;
	
	public MenusPage(WebDriver driver) {
		util = new Util();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void acessaTelaCadastroPessoaFisicaCorretora() {
		Actions builder = new Actions(driver);
		util.waitVisibilityOfElement(menuCadastros, wait, driver);
		builder.moveToElement(menuCadastros).build().perform();
		util.waitUntilElementTobeClickAble(submenuGerenciaPessoaFisicaCorretora, wait, driver);
		submenuGerenciaPessoaFisicaCorretora.click();
	}
	
	

}
