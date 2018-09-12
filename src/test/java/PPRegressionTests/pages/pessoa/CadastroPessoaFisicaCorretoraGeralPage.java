package PPRegressionTests.pages.pessoa;

import java.io.FileOutputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PPRegressionTests.utils.Util;

public class CadastroPessoaFisicaCorretoraGeralPage {

	WebDriver driver;
	Util util;
	WebDriverWait wait;
	Properties pessoaFisica = new Properties();

	@FindBy(id = "loja")
	WebElement dropLoja;
	@FindBy(id = "cpf")
	WebElement txtCPF;
	@FindBy(name = "{selectedPessoaFisica.nome}")
	WebElement txtNome;
	@FindBy(name = "{selectedPessoaFisica.documentoIdentificacao}")
	WebElement txtRG;
	@FindBy(id = "documentoIdentificacaoOrgaoEmissor")
	WebElement txtOrgaoEmissor;
	@FindBy(xpath = "//a[@id='lnkPopupSelecionarInidicacao']")
	WebElement lnkPopupSelecionarIndicacao;
	@FindBy(id = "padraoConfidence")
	WebElement checkPadraoConfidence;
	@FindBy(id = "btnConfirmarSelecaoIndicacao")
	WebElement btnConfirmarSelecaoIndicacao;
	@FindBy(id = "telefoneCelularDDD")
	WebElement txtDDDCelular;
	@FindBy(id = "telefoneCelular")
	WebElement txtCelular;
	@FindBy(id = "email")
	WebElement txtEmail;
	@FindBy(id = "dataNascimento")
	WebElement txtDtNascimento;
	@FindBy(id = "txtProfissao")
	WebElement txtProfissao;

	public CadastroPessoaFisicaCorretoraGeralPage(WebDriver driver) {
		util = new Util();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util.lerArquivo("files/dadosPessoaFisicaCorretora.properties", pessoaFisica);
	}

	private void selecionaLojaCadastro() {
		util.waitVisibilityOfElement(dropLoja, wait, driver);
		Select dropdownLoja = new Select(dropLoja);
		dropdownLoja.selectByVisibleText("CPS SH GALLERIA");
	}

	private void digitaCPF() {
		String CPF = util.geraCPF();
		txtCPF.sendKeys(CPF);
		try{
			pessoaFisica.setProperty("CPF", CPF);
			util.gravarArquivo("files/dadosPessoaFisicaCorretora.properties", pessoaFisica);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void digitaNome() {
		txtNome.sendKeys(pessoaFisica.getProperty("NOME"));
	}

	private void digitarRG() {
		String RG = util.geraRG();
		txtRG.sendKeys(RG);
		try{
			
			pessoaFisica.setProperty("RG", RG);
			util.gravarArquivo("files/dadosPessoaFisicaCorretora.properties", pessoaFisica);
		}
		catch (Exception e) {
			e.getMessage();
		}
	}

	private void digitarOrgaoEmissor() {
		txtOrgaoEmissor.sendKeys(pessoaFisica.getProperty("ORGAO_EMISSOR"));
		txtOrgaoEmissor.sendKeys(Keys.TAB);
	}

	private void selecionaIndicacao() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", lnkPopupSelecionarIndicacao);
		executor.executeScript("arguments[0].click();", checkPadraoConfidence);
		executor.executeScript("arguments[0].click();", btnConfirmarSelecaoIndicacao);
	}

	private void digitaCelular() {
		wait = new WebDriverWait(driver, 5);
		boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='dxDialog dxInfoDialog']]")));
		if(invisible) {
			txtDDDCelular.sendKeys(pessoaFisica.getProperty("DDD"));
		}
		
		txtCelular.sendKeys(pessoaFisica.getProperty("NUM_CELULAR"));
	}

	private void digitarEmail() {
		txtEmail.sendKeys(pessoaFisica.getProperty("EMAIL"));
	}

	private void digitarDataNascimento() {
		txtDtNascimento.sendKeys(pessoaFisica.getProperty("DT_NASCIMENTO"));
	}

	private void digitarProfissao() {
		txtProfissao.sendKeys(pessoaFisica.getProperty("PROFISSAO"));
		txtProfissao.sendKeys(Keys.TAB);
	}

	public void insereDadosPessoaFisicaCorretoraGeral() {
		selecionaLojaCadastro();
		digitaCPF();
		digitaNome();
		digitarRG();
		digitarOrgaoEmissor();
		selecionaIndicacao();
		digitaCelular();
		digitarEmail();
		digitarDataNascimento();
		digitarProfissao();
	}

}
