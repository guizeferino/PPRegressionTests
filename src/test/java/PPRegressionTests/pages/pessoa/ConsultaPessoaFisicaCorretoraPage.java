package PPRegressionTests.pages.pessoa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import PPRegressionTests.utils.Util;

public class ConsultaPessoaFisicaCorretoraPage {
	WebDriver driver;
	Util util;
	WebDriverWait wait;
	
	@FindBy(id="btnAdicionarPessoaFisica")
	WebElement btnAdicionarPessoaFisica;
	
	public ConsultaPessoaFisicaCorretoraPage(WebDriver driver) {
		util = new Util();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void cliqueEmAdicionarPessoaFisicaCorretora() {
		util.waitUntilElementTobeClickAble(btnAdicionarPessoaFisica, wait, driver);
		btnAdicionarPessoaFisica.click();
	}
	
	
	
}
