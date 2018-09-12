package PPRegressionTests.pages.pessoa;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PPRegressionTests.utils.Util;

public class CadastroPessoaFisicaDocumentoPage {

	WebDriver driver;
	Util util;
	WebDriverWait wait;
	Properties pessoaFisica = new Properties();
	
	@FindBy(id="abaDadosDocumentos")
	WebElement abaDadosDocumentos;
	@FindBy(id="btnAdicionarDocumentoDocumentos")
	WebElement btnAdicionarDocumentoDocumentos;
	@FindBy(name="{tipoDigitalizacao}")
	WebElement selectTipoDigitalizacao;
	@FindBy(id="infoDocumento")
	WebElement txtInfoDocumento;
	@FindBy(name="{arquivo}")
	WebElement btnCarregarArquivo;
	@FindBy(id="btnConfirmarDigitalizacao")
	WebElement btnConfirmarDigitalizacao;
	
	
	public CadastroPessoaFisicaDocumentoPage(WebDriver driver) {
		util = new Util();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util.lerArquivo("files/dadosPessoaFisicaCorretora.properties", pessoaFisica);
	}
	
	private void navegarParaAbaDocumentos() {
		util.waitUntilElementTobeInvisible("//div[@class='holyLoadingInternal']", wait, driver);
		util.waitUntilElementTobeClickAble(abaDadosDocumentos, wait, driver);
		abaDadosDocumentos.click();
	}

	private void SelecionarDocumento() {
		
		wait = new WebDriverWait(driver, 5);
		boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='holyLoadingShow']")));
		if(invisible) {
			btnAdicionarDocumentoDocumentos.click();
		}
		util.waitVisibilityOfElement(selectTipoDigitalizacao, wait, driver);
		Select select = new Select(selectTipoDigitalizacao);
		select.selectByValue("4");
		txtInfoDocumento.sendKeys(pessoaFisica.getProperty("INFO_DOCUMENTO"));
		String path = (new File("src/test/resources/atachments/atach.jpg").getAbsolutePath());
		System.out.println(path);
		btnCarregarArquivo.sendKeys(path);
		boolean invisible2 = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='holyLoadingShow']")));
		if(invisible2) {
			btnConfirmarDigitalizacao.click();
		}
	}
	
	public void insereDadosPessoaFisicaCorretoraDocumentos() {
		navegarParaAbaDocumentos();
		SelecionarDocumento();
	}
}
