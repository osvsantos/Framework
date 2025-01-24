package testsFront;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Listeners;

import pages.LoginPage;
import utils.config.ConfigurationManager;
import utils.driver.DriverManager;
import utils.driver.TargetFactory;
import utils.listener.TestListener;

@Listeners(TestListener.class) // Adiciona o listener para capturar falhas e tirar screenshots
public class BaseLocalSuite {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    @Parameters("browser") // Recebe o parâmetro do navegador
    public void setup(String browser) {
        // Utilize TargetFactory para criar o driver com base no navegador
        driver = TargetFactory.createInstance("local", browser);  // Passa o navegador selecionado
        DriverManager.setDriver(driver); // Configura o driver no DriverManager

        String url = ConfigurationManager.getProperty("url");
        driver.get(url);

        loginPage = new LoginPage(driver); // Instancia o LoginPage
    }

    @AfterMethod
    public void teardown() {
        DriverManager.quitDriver();  // Garantir que o driver seja fechado corretamente
    }
}
