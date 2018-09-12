package PPRegressionTests.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

	public String geraCPF(){
		String numero = "";

		if(CpfJaCadastrado() == true) {
			Random rand = new Random();
			int n = 10;
			int n1 = rand.nextInt(n);
			int n2 = rand.nextInt(n);
			int n3 = rand.nextInt(n);
			int n4 = rand.nextInt(n);
			int n5 = rand.nextInt(n);
			int n6 = rand.nextInt(n);
			int n7 = rand.nextInt(n);
			int n8 = rand.nextInt(n);
			int n9 = rand.nextInt(n);
			int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;
			d1 = 11 - (d1 % 11);
			if (d1 >= 10)
				d1 = 0;
			int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;
			d2 = 11 - (d2 % 11);
			if (d2 >= 10)
				d2 = 0;

			numero = "" + n1 + n2 + n3 + "." + n4 + n5 + n6 + "." + n7 + n8 + n9 + "-" + d1 + d2;
		}
		else {
			numero = "41844043835";
		}
		return numero;
	}

	public String geraRG() {
		String numero = "";
		if(RgJaCadastrado() == true) {
			Random rand = new Random();
			int n = 100000000 + rand.nextInt(900000000);
			numero = Integer.toString(n);
			System.out.println(numero);
		}
		else {
			numero = "486028264";
		}
		return numero;
	}

	public boolean RgJaCadastrado() {
		ConectaBanco conexao = new ConectaBanco();
		Connection conn;
		boolean rgJaExisteNaBase = true;
		try {
			conn = conexao.conectarBancoTeste();
			Properties prop = new Properties();
			lerArquivo("src/test/java/PPRegressionTests.files.documentos.properties", prop);
			String rg =  prop.getProperty("RG");

				String sql = "SELECT count(*) as resultados FROM Documento where numeroSemFormato = '" + rg + "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					int resultados = rs.getInt("resultados");
					if (resultados == 0)
						rgJaExisteNaBase = false;
				}
				conn.close();
				if (conn.isClosed()) {
					System.out.println("fechou");
				}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return rgJaExisteNaBase;
	}

	public boolean CpfJaCadastrado() {
		ConectaBanco conexao = new ConectaBanco();
		Connection conn;
		boolean cpfJaExisteNaBase = true;
		try {
			Properties prop = new Properties();
			lerArquivo("src/test/java/PPRegressionTests.files.documentos.properties", prop);
			String cpf =  prop.getProperty("CPF");
			conn = conexao.conectarBancoTeste();
			
				String sql = "SELECT count(*) as resultados FROM Documento where numeroSemFormato = '" + cpf + "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int resultados = rs.getInt("resultados");
					if (resultados == 0)
						cpfJaExisteNaBase = false;
				}
	

			conn.close();
			if (conn.isClosed()) {
				System.out.println("fechou");
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return cpfJaExisteNaBase;
	}
	
	public void lerArquivo(String path, Properties prop){
		
		InputStream fs;
		try {
			fs = getClass().getClassLoader().getResourceAsStream(path);
			prop.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waitUntilElementTobeClickAble(WebElement e, WebDriverWait wait, WebDriver driver){
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}
	
	public void waitVisibilityOfElement(WebElement e, WebDriverWait wait, WebDriver driver){
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	public void waitVisibilityOfListOfElements(List<WebElement> e, WebDriverWait wait, WebDriver driver){
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements((List<WebElement>)e));
	}

	
	
}
