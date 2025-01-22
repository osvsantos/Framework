package utils.driver;

import org.openqa.selenium.WebDriver;

public class TargetFactory {

    public static WebDriver createInstance(String target, String browser) {
        switch (target.toLowerCase()) {
            case "local":
                return BrowserFactory.createDriver(browser);
            // Add Selenium Grid and TestContainers logic here
            default:
                throw new IllegalArgumentException("Unsupported target: " + target);
        }
    }
}
