package PPRegressionTests.pages.pessoa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PPRegressionTests.utils.Util;

public class CadastroPessoaFisicaCorretoraPage {
	WebDriver driver;
	Util util;
	WebDriverWait wait;
	
	@FindBy(id="salvarPessoa")
	WebElement btnSalvarPessoa;
	
	CadastroPessoaFisicaCorretoraGeralPage CPFGeral;
	CadastroPessoaFisicaCorretoraTipoPage CPFTipo;
	CadastroPessoaFisicaCorretoraEnderecoPage CPFEndereco;
	
	public CadastroPessoaFisicaCorretoraPage(WebDriver driver) {
		CPFGeral = new CadastroPessoaFisicaCorretoraGeralPage(driver);
		CPFTipo = new CadastroPessoaFisicaCorretoraTipoPage(driver);
		CPFEndereco = new CadastroPessoaFisicaCorretoraEnderecoPage(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private void salvarCadastroPessoaFisicaCorretora() {
		
		wait = new WebDriverWait(driver, 5);
		boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='holyLoadingShow']")));
		if(invisible) {
			btnSalvarPessoa.click();
		}

	}
	
	public void insereDadosPessoaFisicaCorretora() throws InterruptedException{
	
		CPFGeral.insereDadosPessoaFisicaCorretoraGeral();
		CPFTipo.insereDadosPessoaFisicaCorretoraTipo();
		CPFEndereco.insereDadosPessoaFisicaCorretoraEndereco();
		salvarCadastroPessoaFisicaCorretora();
		
	}

}
