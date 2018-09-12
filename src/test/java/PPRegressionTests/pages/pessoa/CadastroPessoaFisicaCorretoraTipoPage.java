package PPRegressionTests.pages.pessoa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.swing.plaf.basic.BasicTreeUI.TreeExpansionHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import PPRegressionTests.utils.Util;

public class CadastroPessoaFisicaCorretoraTipoPage {

	WebDriver driver;
	Util util;
	WebDriverWait wait;

	@FindBy(id = "abaTipoTitular")
	WebElement abaTipoTitular;

	public CadastroPessoaFisicaCorretoraTipoPage(WebDriver driver) {
		util = new Util();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private void irParaAbaTipo() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		util.waitUntilElementTobeClickAble(abaTipoTitular, wait, driver);
		abaTipoTitular.click();
	}
	
	private List<WebElement> getTipoClienteCorretora() {

		List<WebElement> checkboxes = driver.findElements(
				By.cssSelector("div[id='wrapperTipoTitular'] fieldset ul li fieldset[class='tipoTitular'] ul li"));
		util.waitVisibilityOfListOfElements(checkboxes, wait, driver);
		System.out.println("List size is: " + checkboxes.size());
		return checkboxes;

	}
	
	private void selecionaTipoClienteCorretora() {
		List<WebElement> list = getTipoClienteCorretora();
		util.waitVisibilityOfListOfElements(list, wait, driver);
		for (int i = 1; i <= list.size(); i++) {
			if (i == 16) {
				WebElement checkTipoCliente = driver.findElement(By.id("tipo-" + i));
				util.waitUntilElementTobeClickAble(checkTipoCliente, wait, driver);
				checkTipoCliente.click();
			}
		}
	}

	public void insereDadosPessoaFisicaCorretoraTipo() {
		irParaAbaTipo();
		selecionaTipoClienteCorretora();
	}


	

}
