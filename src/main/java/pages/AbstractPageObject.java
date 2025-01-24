package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPageObject {
    protected WebDriver driver;

   
    public AbstractPageObject(WebDriver driver) {
        this.driver = driver;
        initializePageElements();
    }

    @Step("Inicializando elementos da página com PageFactory")
    private void initializePageElements() {
        PageFactory.initElements(driver, this); // Inicializa os elementos da página
    }
}
