package testsFront;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import pages.LoginPage;
import utils.config.ConfigurationManager;
import utils.driver.DriverManager;
import utils.driver.TargetFactory;
import utils.listener.TestListener;

@Listeners(TestListener.class) // Adiciona o listener para capturar falhas e tirar screenshots
public class BaseLocal {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        // Utilize TargetFactory para criar o driver
        String browser = ConfigurationManager.getProperty("browser");
        driver = TargetFactory.createInstance("local", browser);  // Altere o navegador conforme necessário
        DriverManager.setDriver(driver); // Configura o driver no DriverManager

        String url = ConfigurationManager.getProperty("url");
        driver.get(url);

        loginPage = new LoginPage(driver); // Agora instanciamos o LoginPage
    }

    @AfterMethod
    public void teardown() {
        DriverManager.quitDriver();  // Garantir que o driver seja fechado corretamente
    }
}
