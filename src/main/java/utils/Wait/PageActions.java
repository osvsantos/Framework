package utils.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

public class PageActions {

    private WebDriver driver;
    private WebDriverWait wait;

    public PageActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public WebElement findElementWithWait(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println("Elemento encontrado: " + locator);
            return element;
        } catch (TimeoutException e) {
            System.err.println("Timeout ao procurar o elemento: " + locator);
            throw e;
        }
    }

    public void clickElementWithWait(By locator) {
        WebElement element = findElementWithWait(locator);
        try {
            element.click();
            System.out.println("Clique bem-sucedido no elemento: " + locator);
        } catch (Exception e) {
            System.err.println("Erro ao tentar clicar no elemento: " + locator);
            throw e;
        }
    }

    public void waitForPageToLoad() {
        try {
            wait.until(ExpectedConditions.titleContains("Página Carregada"));
            System.out.println("Página carregada com sucesso.");
        } catch (TimeoutException e) {
            System.err.println("Timeout aguardando página carregar.");
            throw e;
        }
    }

    public void waitForElementToBeClickable(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            System.out.println("Elemento está clicável: " + locator);
            element.click();
        } catch (TimeoutException e) {
            System.err.println("Timeout ao esperar pelo elemento clicável: " + locator);
            throw e;
        }
    }
}
