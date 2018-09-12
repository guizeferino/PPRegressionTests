package PPRegressionTests.tests;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import PPRegressionTests.base.Base;
import PPRegressionTests.pages.home.HomePage;
import PPRegressionTests.pages.login.LoginPage;
import PPRegressionTests.pages.menu.MenusPage;
import PPRegressionTests.pages.pessoa.CadastroPessoaFisicaCorretoraPage;
import PPRegressionTests.pages.pessoa.ConsultaPessoaFisicaCorretoraPage;
import PPRegressionTests.utils.Util;

public class CadastroPessoaTest extends Base{

	Util util;
	LoginPage login;
	HomePage home;
	MenusPage menu;
	ConsultaPessoaFisicaCorretoraPage consultaPF;
	CadastroPessoaFisicaCorretoraPage cadastraPF;
	WebDriver driver;
	Base base;

	public CadastroPessoaTest() {
		util = new Util();
		driver = getDriver();
		login = new LoginPage(driver);
		home = new HomePage(driver);
		menu = new MenusPage(driver);
		consultaPF = new ConsultaPessoaFisicaCorretoraPage(driver);
		cadastraPF = new CadastroPessoaFisicaCorretoraPage(driver);
	}

	@Test
	public void cadastraPessoaFisicaCorretora() {
		
		login.logarNoSocc();
		home.selecionaPerfil("Administrador Corretora");
		menu.acessaTelaCadastroPessoaFisicaCorretora();
		consultaPF.cliqueEmAdicionarPessoaFisicaCorretora();
		cadastraPF.insereDadosPessoaFisicaCorretora();
		assertThat(util.RgJaCadastrado()).isTrue();
		assertThat(util.CpfJaCadastrado()).isTrue();
		
	}
	
}
