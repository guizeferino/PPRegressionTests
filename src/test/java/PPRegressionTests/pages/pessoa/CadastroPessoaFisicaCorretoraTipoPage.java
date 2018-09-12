package PPRegressionTests.pages.pessoa;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
		wait = new WebDriverWait(driver, 5);
		boolean invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='holyLoadingShow']")));
		if(invisible) {
			abaTipoTitular.click();
		}
		
	}
	
	public List<WebElement> getTipoClienteCorretora() {

		List<WebElement> checkboxes = driver.findElements(
				By.cssSelector("div[id='wrapperTipoTitular'] fieldset ul li fieldset[class='tipoTitular'] ul li"));
		util.waitVisibilityOfListOfElements(checkboxes, wait, driver);
		System.out.println("List size is: " + checkboxes.size());
		return checkboxes;

	}
	
	private void selecionaTipoClienteCorretora(){
		List<WebElement> list = getTipoClienteCorretora();
		util.waitVisibilityOfListOfElements(list, wait, driver);
		for (int i = 1; i <= list.size(); i++) {
			if (i == 16) {
					WebElement checkTipoCliente = driver.findElement(By.id("tipo-" + i));
					if(!checkTipoCliente.isSelected())
					{
						JavascriptExecutor executor = (JavascriptExecutor) driver;
						executor.executeScript("arguments[0].click();", checkTipoCliente);
					}
				Assert.assertTrue(driver.findElement(By.id("tipo-" + i + "")).isSelected());
			}
		}
	}

	public void insereDadosPessoaFisicaCorretoraTipo(){
		irParaAbaTipo();
		selecionaTipoClienteCorretora();
	}


	

}
