package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPageObject {
    protected WebDriver driver;

    public AbstractPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Inicializa os elementos da página
    }
}
