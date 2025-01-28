package utils.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(getOptions());
        }

        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--disable-infobars");
            if (isHeadless()) options.addArguments("--headless");
            return options;
        }
    },
    FIREFOX {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(getOptions());
        }

        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless()) options.addArguments("--headless");
            return options;
        }
    },
    EDGE {
        @Override
        public WebDriver createDriver() {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(getOptions());
        }

        @Override
        public EdgeOptions getOptions() {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");
            if (isHeadless()) options.addArguments("--headless");
            return options;
        }
    };

    // Método abstrato para criar o driver
    public abstract WebDriver createDriver();

    // Método abstrato para obter opções específicas do navegador
    public abstract AbstractDriverOptions<?> getOptions();

    // Define se o navegador deve rodar em modo headless
    protected boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    // Método estático para criar o driver baseado em String
    public static WebDriver createDriver(String browser) {
        try {
            return BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
