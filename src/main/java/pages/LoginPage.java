package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
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
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.clear();
        userNameField.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public boolean isEmailBlankErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(emailBlankErrorMessage));
        return emailBlankErrorMessage.isDisplayed();
    }

    public boolean isPasswordBlankErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(passwordBlankErrorMessage));
        return passwordBlankErrorMessage.isDisplayed();
    }

    public boolean isInvalidCredentialsErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(invalidCredentialsErrorMessage));
        return invalidCredentialsErrorMessage.isDisplayed();
    }

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }
}
