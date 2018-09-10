package PPRegressionTests.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.*;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


public class DataBaseTesting {


    WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    /*@FindBy(id="j_username")
    WebElement UserName;

    public DataBaseTesting(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }*/

    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException {

    }

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {

        driver = new ChromeDriver();
        baseUrl = "http://10.90.0.63:8080/confidence/index.html";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static Connection conectarBancoTeste() throws IOException
    {



        String pathname = "/home/guilherme.zeferino/Documents/test.properties";
        Properties prop = new Properties();
        FileInputStream fs = new FileInputStream(pathname);
        prop.load(fs);

        String usuario = prop.getProperty("usuario");
        String senha = prop.getProperty("senha");
        String url = prop.getProperty("url");
        System.out.println(url);
        Connection conexao = null;


        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            System.out.println("Conexão obtida com sucesso");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println(conexao);


        } catch (ClassNotFoundException e1) {

            e1.printStackTrace();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
        return conexao;

    }


    @Test
    public void consultaRgCpfBancoDeDados() throws SQLException, IOException {

        String cpf = null;
        Connection conn = conectarBancoTeste();

        boolean cpfJaExisteNaBase = true;
        while (cpfJaExisteNaBase == true) {
            cpf = geraCPF();
            String sql = "SELECT count(*) as resultados FROM Documento where numero = '" + cpf + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int resultados = rs.getInt("resultados");
                if (resultados == 0)
                    cpfJaExisteNaBase = false;
            }
        }

        String rg = null;
        boolean rgJaExisteNaBase = true;
        while (rgJaExisteNaBase == true) {
            rg = geraRG();
            String sql = "SELECT count(*) as resultados FROM Documento where numero = '" + rg + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int resultados = rs.getInt("resultados");
                if (resultados == 0)
                    rgJaExisteNaBase = false;
            }
        }

        conn.close();

        if(conn.isClosed())
        {
            System.out.println("fechou");
        }

        System.out.println(cpf);



        driver.get("http://10.90.0.63:8080/confidence/index.html");


        driver.findElement(By.id("j_username")).click();
        driver.findElement(By.id("j_username")).sendKeys("userdextra");
        driver.findElement(By.id("j_username")).sendKeys(Keys.RETURN);


        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(Keys.RETURN);


        Select dropdownPerfil = new Select(driver.findElement(By.id("cbxPerfilUsuarioLogado")));
        dropdownPerfil.selectByVisibleText("Administrador Corretora");

        /*driver.findElement(By.id("cbxPerfilUsuarioLogado")).click();
        driver.findElement(By.linkText("Administrador Corretora"));
        new Select (driver.findElement(By.cssSelector("cbxPerfilUsuarioLogado"))).selectByVisibleText("Administrador Corretora");
*/
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.linkText("Cadastros"));
        builder.moveToElement(element).build().perform();

        driver.findElement(By.linkText("Gerenciar Pessoa Física (Corretora)")).click();

        driver.findElement(By.id("btnAdicionarPessoaFisica")).click();


        Select dropdownLoja = new Select(driver.findElement(By.id("loja")));
        dropdownLoja.selectByVisibleText("CPS SH GALLERIA");
        //driver.findElement(By.id("brasileiro")).click();
        //driver.findElement(By.id("estrangeiro")).click();
        //driver.findElement(By.id("brasileiro")).click();
        driver.findElement(By.id("cpf")).click();
        driver.findElement(By.id("cpf")).sendKeys(cpf);
        driver.findElement(By.id("cpf")).sendKeys(Keys.TAB);
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBePresentInElement(By.id("tstLabelOk"), "OK"));*/

        driver.findElement(By.id("tstLabelOk"));
        //driver.findElement(By.id("tstLabelOk")).click();
        //driver.findElement(By.id("nome")).click();
        driver.findElement(By.name("{selectedPessoaFisica.nome}")).sendKeys("TESTE AUTOMATIZADO NOME");
        driver.findElement(By.name("{selectedPessoaFisica.documentoIdentificacao}")).click();
        driver.findElement(By.name("{selectedPessoaFisica.documentoIdentificacao}")).sendKeys(rg);
        driver.findElement(By.name("{selectedPessoaFisica.documentoIdentificacao}")).sendKeys(Keys.TAB);
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("documentoIdentificacaoOrgaoEmissor")).click();
        driver.findElement(By.id("documentoIdentificacaoOrgaoEmissor")).sendKeys("SSP");
        driver.findElement(By.id("documentoIdentificacaoOrgaoEmissor")).sendKeys(Keys.TAB);
        driver.findElement(By.id("lnkPopupSelecionarInidicacao")).click();
        driver.findElement(By.id("informacoesIndicacao"));

        driver.findElement(By.id("padraoConfidence")).click();
        driver.findElement(By.id("btnConfirmarSelecaoIndicacao")).click();
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("telefoneCelularDDD")).click();
        driver.findElement(By.id("telefoneCelularDDD")).sendKeys("19");
        driver.findElement(By.id("telefoneCelularDDD")).sendKeys(Keys.TAB);
        driver.findElement(By.id("telefoneCelular")).click();
        driver.findElement(By.id("telefoneCelular")).sendKeys("999665544");
        driver.findElement(By.id("telefoneCelular")).sendKeys(Keys.TAB);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("tst@aut.com");
        driver.findElement(By.id("email")).sendKeys(Keys.TAB);
        driver.findElement(By.id("dataNascimento")).click();
        driver.findElement(By.id("dataNascimento")).sendKeys("23/09/1987");
        driver.findElement(By.id("dataNascimento")).sendKeys(Keys.TAB);
        driver.findElement(By.id("txtProfissao")).click();
        driver.findElement(By.id("txtProfissao")).sendKeys("225 - Médico");
        driver.findElement(By.id("txtProfissao")).sendKeys(Keys.TAB);

        driver.findElement(By.id("abaTipoTitular")).click();
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("div[id='wrapperTipoTitular'] fieldset ul li fieldset[class='tipoTitular'] ul li"));
        System.out.println("List size is: " + checkboxes.size());
        for (int i = 1; i <= checkboxes.size(); i++)
        {
            if(i == 16) {
                Assert.assertTrue(driver.findElement(By.id("tipo-" + i + "")).isSelected());
                System.out.println(i+" AssertTrue");
            }
            else{
                Assert.assertFalse(driver.findElement(By.id("tipo-" + i + "")).isSelected());
                System.out.println(i+" AssertFalse");
            }


        }
        driver.findElement(By.id("abaDadosEnderecos")).click();
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("cep")).click();
        driver.findElement(By.id("cep")).sendKeys("01010-010");
        driver.findElement(By.id("cep")).sendKeys(Keys.TAB);

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("numero")).click();
        driver.findElement(By.id("numero")).sendKeys("130");
        driver.findElement(By.id("numero")).sendKeys(Keys.TAB);

        driver.findElement(By.id("adicionarEndereco")).click();

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("salvarPessoa")).click();

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        Select dropdownJustificativa = new Select(driver.findElement(By.id("justificativaPadraoDePendencia")));
        dropdownJustificativa.selectByValue("4");

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("salvarPendencias")).click();

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }







        /*Select dropdownProfissao = new Select(driver.findElement(By.linkText("cbxPerfilUsuarioLogado")));
        dropdownPerfil.selectByVisibleText("Administrador Corretora");*/





        //geraCPF();
        //conectarBancoTeste();


    }





    public static void main(String[] args) throws ClassNotFoundException, IOException {


        conectarBancoTeste();

    }

    public static String geraCPF() {
        Random rand = new Random();
        String numero = "";

        int n=10;
        int n1=rand.nextInt(n);
        int n2=rand.nextInt(n);
        int n3=rand.nextInt(n);
        int n4=rand.nextInt(n);
        int n5=rand.nextInt(n);
        int n6=rand.nextInt(n);
        int n7=rand.nextInt(n);
        int n8=rand.nextInt(n);
        int n9=rand.nextInt(n);
        int d1=n9*2+n8*3+n7*4+n6*5+n5*6+n4*7+n3*8+n2*9+n1*10;
        d1=11-(d1 % 11);
        if(d1>=10)
            d1=0;
        int d2=d1*2+n9*3+n8*4+n7*5+n6*6+n5*7+n4*8+n3*9+n2*10+n1*11;
        d2=11-(d2 %11);
        if(d2>=10)
            d2=0;

       // if(comPontos)
            numero=""+n1+n2+n3+"."+n4+n5+n6+"."+n7+n8+n9+"-"+d1+d2;
       // else
       //     numero=''+n1+n2+n3+n4+n5+n6+n7+n8+n9+d1+d2;
        return numero;
    }

    public static String geraRG() {
        String numero = "";
        Random rand = new Random();
        int n = 100000000 + rand.nextInt(900000000);


        numero = Integer.toString(n);

        System.out.println(numero);
        return numero;
    }



}
