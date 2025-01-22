package utils.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE) // Carrega propriedades de várias fontes
@Config.Sources({
    "system:properties", // Leitura de propriedades do sistema
    "classpath:general.properties", // Arquivo de configuração geral
    "classpath:selenium-grid.properties", // Configurações do Selenium Grid
    "classpath:frontend.properties" // Arquivo de configurações frontend
})
public interface Configuration extends Config {

    @Key("target")
    String target();

    @Key("browser")
    String browser();

    @Key("headless")
    Boolean headless();

    @Key("url.base")
    String url();

    @Key("timeout")
    int timeout();

    @Key("grid.url")
    String gridUrl();

    @Key("grid.port")
    String gridPort();

    @Key("faker.locale")
    String faker();
}
