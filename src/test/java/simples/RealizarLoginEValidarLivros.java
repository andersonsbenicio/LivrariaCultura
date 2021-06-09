package simples;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class RealizarLoginEValidarLivros {

    String url;
    WebDriver driver;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {
        File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(foto, new File(pastaPrint + nomePrint + ".png"));
    }

    @Before
    public void iniciar(){

        url = "https://www3.livrariacultura.com.br/";
        System.setProperty("webdriver.chrome.driver","drivers/chrome/91/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        System.out.println("Passo 1 - Preparou o Setup");

    }

    @After
    public void finalizar(){

        driver.quit();
        System.out.println("Passo 8 - Fechou o Navegador");

    }

    @Given("^que acesso o site da Livraria Cultura \"([^\"]*)\"$")
    public void queAcessoOSiteDaLivrariaCultura(String id) throws IOException {
        driver.get(url);
        tirarPrint("Foto 1 - Acessou a Home");
        System.out.println("Passo 2 - Acessou o Site");
    }

    @When("^clico em Entre ou Cadastre-se$")
    public void clicoEmEntreOuCadastreSe() {
        driver.findElement(By.cssSelector("div.row.align-items-center div.user-wrapper div.user-message-wrapper > p.login")).click();
        System.out.println("Passo 3 - Clicou em Entre ou Cadastre-se");
    }

    @And("^clico em Entrar/Cadastrar a pagina de Login/Cadastro e aberta$")
    public void clicoEmEntrarCadastrarAPaginaDeLoginCadastroEAberta() {
        driver.findElement(By.cssSelector("div.col-lg-3.p-0.icon-user div.icon-user-wrapper div.login-hover.js-opened div.login-inner div:nth-child(1) a.button.__bt-login-entrar > div.bg-opacity")).click();
        System.out.println("Passo 4 - Clicou em Entrar/Cadastrar e a Pagina de Login/Cadastro Abriu");
    }

    @When("^clico em Entrar com Email e Senha ou Cadastre-se$")
    public void clicoEmEntrarComEmailESenhaOuCadastreSe() {
        driver.findElement(By.cssSelector("button.btn.btn-block.btn-large.vtexIdUI-others-send-email")).click();
        System.out.println("Passo 5 - Clicou em Entrar com Email e Senha");
    }

    @And("^preencho email e Senha e clico em Entrar$")
    public void preenchoEmailESenhaEClicoEmEntrar() {
        driver.findElement(By.cssSelector("div.controls #inputEmail")).sendKeys("tester_qa2020@outlook.com");
        driver.findElement(By.cssSelector("div.controls #inputPassword")).sendKeys("#testerq@2020A");
        driver.findElement((By.cssSelector("form#vtexIdUI-form-classic-login div.modal-footer  button#classicLoginBtn"))).click();
        System.out.println("Passo 6 - Preencheu E-mail e Senha e Clicou em Entrar");
    }

    @Then("^o Login e validado e acesso a HomePage$")
    public void oLoginEValidadoEAcessoAHomePage() throws IOException {
        Assert.assertEquals("Olá tester_qa2020",driver.findElement(By.cssSelector("div.col-lg-3 div.user-message-wrapper span.welcome")).getText());
        tirarPrint("Foto 2 - Validou Login");
        System.out.println("Passo 7 - Validou Login");
    }

    @When("^procuro por \"([^\"]*)\" e pressiono Enter$")
    public void procuroPorEPressionoEnter(String Livro) throws IOException {
        driver.findElement(By.cssSelector("input.fulltext-search-box.ui-autocomplete-input")).click();
        driver.findElement(By.cssSelector("input.fulltext-search-box.ui-autocomplete-input")).sendKeys(Livro + Keys.ENTER);
        System.out.println("Passo 3a - Procurou o Livro Desejado e Pressionou Enter");
        tirarPrint("Foto 3 - Procurou Livro");
    }

    @When("^seleciono o produto desejado e Clico no Carrinho$")
    public void selecionoOProdutoDesejadoEClicoNoCarrinho() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("li.livros:nth-child(1) div.prateleiraProduto.pronto.livros div.prateleiraProduto__informacao:nth-child(3) h2.prateleiraProduto__informacao__nome > a:nth-child(1)")).click();
        driver.findElement(By.cssSelector("button.buy-in-page-button")).click();
        System.out.println("Passo 4a - Selecionou o Livro Desejado e Clicou no Carrinho");
    }

    @Then("^verifico o \"([^\"]*)\" e o \"([^\"]*)\" do Livro$")
    public void verificoOEODoLivro(String Nome, String Preco) throws InterruptedException, IOException {
        Thread.sleep(3000);
        Assert.assertEquals(Nome, driver.findElement(By.cssSelector("p.title-mini")).getText());
        assertTrue(driver.findElement(By.cssSelector("span.valor")).getText().contains(Preco));
        //Assert.assertEquals(Preco,driver.findElement(By.cssSelector("span.valor > p")).getText());
        //Assert.assertEquals(Preco,driver.findElement(By.xpath("div/div/span[contains(text(),'R$ 91,20']")));

        System.out.println("Passo 5a - Validou o Nome e o Preco do Livro");
        tirarPrint("Foto 4 - Validou Livro");
    }


}
