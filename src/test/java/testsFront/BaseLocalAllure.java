package testsFront;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import utils.config.ConfigurationManager;
import utils.driver.DriverManager;
import utils.driver.TargetFactory;
import utils.listener.TestListener;
import utils.report.AllureManager;

@Listeners({TestListener.class}) // Adiciona o listener para capturar falhas e gerar relatórios
public class BaseLocalAllure {

    protected WebDriver driver;
    protected LoginPage loginPage;

    // Configura informações do Allure antes da suíte de testes
    @BeforeSuite(alwaysRun = true)
    public void setupAllureEnvironment() {
        AllureManager.setAllureEnvironmentInformation();
    }

    @BeforeMethod
    public void setup() {
        // Obtém o navegador configurado nas propriedades
        String browser = ConfigurationManager.getProperty("browser");
        driver = TargetFactory.createInstance("local", browser); // Cria o WebDriver para execução local
        DriverManager.setDriver(driver); // Configura o driver no DriverManager

        // Navega para a URL definida no arquivo de configuração
        String url = ConfigurationManager.getProperty("url");
        driver.get(url);

        // Instancia a página inicial de login
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        // Finaliza o WebDriver após cada teste
        DriverManager.quitDriver();
    }
}
