package pages;

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

    public String getAccessLevelMessage() {
        wait.until(ExpectedConditions.visibilityOf(accessLevelMessage)); // Espera o elemento ser visível
        return accessLevelMessage.getText();
    }
}
