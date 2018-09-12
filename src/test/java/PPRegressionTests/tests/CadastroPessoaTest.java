package PPRegressionTests.tests;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import PPRegressionTests.base.Base;
import PPRegressionTests.pages.HomePage;
import PPRegressionTests.pages.LoginPage;
import PPRegressionTests.pages.MenusPage;
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
	public void cadastraPessoaFisicaCorretora() throws SQLException, IOException, InterruptedException {
		
		login.logarNoSocc();
		home.selecionaPerfil("Administrador Corretora");
		menu.acessaTelaCadastroPessoaFisicaCorretora();
		consultaPF.cliqueEmAdicionarPessoaFisicaCorretora();
		cadastraPF.insereDadosPessoaFisicaCorretora();

//
//		Select dropdownJustificativa = new Select(driver.findElement(By.id("justificativaPadraoDePendencia")));
//		dropdownJustificativa.selectByValue("4");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("salvarPendencias")).click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/*
		 * Select dropdoswnProfissao = new
		 * Select(driver.findElement(By.linkText("cbxPerfilUsuarioLogado")));
		 * dropdownPerfil.selectByVisibleText("Administrador Corretora");
		 */

		// geraCPF();
		// conectarBancoTeste();

	}

}
