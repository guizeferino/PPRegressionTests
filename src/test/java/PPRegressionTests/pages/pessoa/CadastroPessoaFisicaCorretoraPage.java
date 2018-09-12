package PPRegressionTests.pages.pessoa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import PPRegressionTests.utils.Util;

public class CadastroPessoaFisicaCorretoraPage {
	WebDriver driver;
	Util util;
	WebDriverWait wait;
	
	CadastroPessoaFisicaCorretoraGeralPage CPFGeral;
	CadastroPessoaFisicaCorretoraTipoPage CPFTipo;
	
	public CadastroPessoaFisicaCorretoraPage(WebDriver driver) {
		CPFGeral = new CadastroPessoaFisicaCorretoraGeralPage(driver);
		CPFTipo = new CadastroPessoaFisicaCorretoraTipoPage(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void insereDadosPessoaFisicaCorretora(){
	
		CPFGeral.insereDadosPessoaFisicaCorretoraGeral();
		CPFTipo.insereDadosPessoaFisicaCorretoraTipo();
		
	}

}
