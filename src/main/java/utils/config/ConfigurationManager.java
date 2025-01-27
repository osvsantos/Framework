package utils.config;

import org.aeonbits.owner.ConfigFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private static final Properties properties = new Properties();
    private static final Configuration configuration = ConfigFactory.create(Configuration.class);

    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/frontend.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties from frontend.properties", e);
        }
    }

    /**
     * Retorna uma propriedade do arquivo tradicional de propriedades.
     *
     * @param key chave da propriedade
     * @return valor da propriedade
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Retorna a instância de configuração do Owner.
     *
     * @return instância de Configuration
     */
    public static Configuration configuration() {
        return configuration;
    }
}
