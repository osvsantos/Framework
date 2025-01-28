package utils.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("O WebDriver ainda não foi inicializado. Use setDriver() antes de chamar getDriver().");
        }
        return driver.get();
    }

    public static void quitDriver() {
        try {
            if (driver.get() != null) {
                driver.get().quit();
                System.out.println("Driver encerrado com sucesso.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao encerrar o driver: " + e.getMessage());
        } finally {
            driver.remove();
        }
    }

    public static boolean isDriverInitialized() {
        return driver.get() != null;
    }
}
