package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractPageObject {

    private WebDriverWait wait;

    @FindBy(id = "user-name")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[contains(text(), 'Epic sadface: Username is required')]")
    private WebElement emailBlankErrorMessage;

    @FindBy(xpath = "//*[contains(text(), 'Epic sadface: Password is required')]")
    private WebElement passwordBlankErrorMessage;

    @FindBy(xpath = "//*[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]")
    private WebElement invalidCredentialsErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver); // Chama o construtor da classe base
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Instancia o WebDriverWait
    }

    @Step("Realizar login com email: {email} e senha: {password}")
    public void login(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickLoginButton();
    }

    @Step("Preencher campo de email com: {email}")
    private void fillEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.clear();
        userNameField.sendKeys(email);
    }

    @Step("Preencher campo de senha com: {password}")
    private void fillPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Clicar no botão de login")
    private void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    @Step("Verificar se a mensagem de erro para email em branco está visível")
    public boolean isEmailBlankErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(emailBlankErrorMessage));
        return emailBlankErrorMessage.isDisplayed();
    }

    @Step("Verificar se a mensagem de erro para senha em branco está visível")
    public boolean isPasswordBlankErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(passwordBlankErrorMessage));
        return passwordBlankErrorMessage.isDisplayed();
    }

    @Step("Verificar se a mensagem de erro para credenciais inválidas está visível")
    public boolean isInvalidCredentialsErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(invalidCredentialsErrorMessage));
        return invalidCredentialsErrorMessage.isDisplayed();
    }

    @Step("Verificar se o botão de login está habilitado")
    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }
}
