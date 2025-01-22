package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    @FindBy(xpath = "//span[contains(text(),'Products')]")
    private WebElement accessLevelMessage;

    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);  // Inicializa os elementos da página
    }

    public String getAccessLevelMessage() {
        wait.until(ExpectedConditions.visibilityOf(accessLevelMessage)); // Espera o elemento ser visível
        return accessLevelMessage.getText();
    }
}
