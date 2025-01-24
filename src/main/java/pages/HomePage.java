package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends AbstractPageObject {

    private WebDriverWait wait;

    @FindBy(xpath = "//span[contains(text(),'Products')]")
    private WebElement accessLevelMessage;

    public HomePage(WebDriver driver) {
        super(driver); // Chama o construtor da classe base
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Instancia o WebDriverWait
    }

    @Step("Obter mensagem de nível de acesso exibida na página inicial.")
    public String getAccessLevelMessage() {
        waitForVisibilityOfAccessLevelMessage();
        String message = accessLevelMessage.getText();
        logAccessLevelMessage(message);
        return message;
    }

    @Step("Aguardar visibilidade do elemento de mensagem de nível de acesso.")
    private void waitForVisibilityOfAccessLevelMessage() {
        wait.until(ExpectedConditions.visibilityOf(accessLevelMessage));
    }

    @Step("Mensagem de nível de acesso capturada: {message}")
    private void logAccessLevelMessage(String message) {
        // Esse método será registrado automaticamente pelo Allure com o valor da mensagem
    }
}

