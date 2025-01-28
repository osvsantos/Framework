package utils.driver;

import org.openqa.selenium.WebDriver;

public class TargetFactory {

    public static WebDriver createInstance(String target, String browser) {
        switch (target.toLowerCase()) {
            case "local":
            case "local-suite":
            case "selenium-grid":
            case "testcontainers":
                return BrowserFactory.createDriver(browser);

            default:
                throw new IllegalArgumentException("Unsupported target: " + target);
        }
    }
}
