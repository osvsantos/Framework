package testsFront;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import utils.config.ConfigurationManager;
import utils.driver.DriverManager;
import utils.driver.TargetFactory;
import utils.listener.*;

@Listeners(TestListener.class) // Adiciona o listener para capturar falhas e tirar screenshots
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        // Utilize TargetFactory para criar o driver
        driver = TargetFactory.createInstance("local", "chrome");  // Altere o navegador conforme necessário
        DriverManager.setDriver(driver); // Configura o driver no DriverManager

        String url = ConfigurationManager.getProperty("url");
        driver.get(url);

        loginPage = new LoginPage(driver);
    }

    @Test
    public void testEmailBlankError() {
        loginPage.login("", "secret_sauce");
        boolean isEmailBlankErrorDisplayed = loginPage.isEmailBlankErrorDisplayed();
        Assert.assertTrue(isEmailBlankErrorDisplayed, "Epic sadface: Username is required");
    }

    @Test
    public void testPasswordBlankError() {
        loginPage.login("standard_user", "");
        boolean isPasswordBlankErrorDisplayed = loginPage.isPasswordBlankErrorDisplayed();
        Assert.assertTrue(isPasswordBlankErrorDisplayed, "Epic sadface: Password is required");
    }

    @Test
    public void testInvalidCredentialsError() {
        loginPage.login("emailinvalido", "senhaerrada");
        boolean isInvalidCredentialsErrorDisplayed = loginPage.isInvalidCredentialsErrorDisplayed();
        Assert.assertTrue(isInvalidCredentialsErrorDisplayed, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        HomePage homePage = new HomePage(driver);

        String expectedMessage = "Products";
        String actualMessage = homePage.getAccessLevelMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "O texto da mensagem de nível de acesso está incorreto.");
    }

    @AfterMethod
    public void teardown() {
        DriverManager.quitDriver();  // Garantir que o driver seja fechado corretamente
    }
}
