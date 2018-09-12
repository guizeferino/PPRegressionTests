package PPRegressionTests.pages.pessoa;

import java.util.Properties;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PPRegressionTests.utils.Util;

public class CadastroPessoaFisicaCorretoraEnderecoPage {
	WebDriver driver;
	Util util;
	WebDriverWait wait;
	Properties pessoaFisica = new Properties();

	@FindBy(id = "abaDadosEnderecos")
	WebElement abaDadosEnderecos;
	@FindBy(id = "cep")
	WebElement txtCep;
	@FindBy(id = "numero")
	WebElement txtNumero;
	@FindBy(xpath = "//input[@id='adicionarEndereco']")
	WebElement btnAdicionarEndereco;



	public CadastroPessoaFisicaCorretoraEnderecoPage(WebDriver driver) {
		util = new Util();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util.lerArquivo("files/dadosPessoaFisicaCorretora.properties", pessoaFisica);
	}

	private void navegarParaAbaEndereco() {
		util.waitUntilElementTobeInvisible("//div[@class='holyLoadingInternal']", wait, driver);
		util.waitUntilElementTobeClickAble(abaDadosEnderecos, wait, driver);
		abaDadosEnderecos.click();
	}

	private void digitarCEP() {
		txtCep.sendKeys(pessoaFisica.getProperty("CEP"));
	}

	private void digitarNumero() {
	
			txtNumero.sendKeys(pessoaFisica.getProperty("NUMERO"));
		
	}

	private void clicarAdicionarEndereco() {
		wait = new WebDriverWait(driver, 5);
		boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='holyLoadingShow']")));
		if(invisible) {
			btnAdicionarEndereco.click();
		}
	}

	public void insereDadosPessoaFisicaCorretoraEndereco() {
		navegarParaAbaEndereco();
		digitarNumero();
		digitarCEP();
		clicarAdicionarEndereco();

	}
}
