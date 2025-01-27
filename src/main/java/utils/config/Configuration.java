package utils.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE) // Carrega propriedades de várias fontes
@Config.Sources({
    "system:properties", // Lê propriedades do sistema
    "classpath:frontend.properties" // Arquivo frontend.properties
})
public interface Configuration extends Config {

    @Key("url")
    String url();

    @Key("browser")
    String browser();

    @Key("headless")
    Boolean headless();

    @Key("url.base")
    String baseUrl();

    @Key("timeout")
    int timeout();

    @Key("grid.url")
    String gridUrl();

    @Key("grid.port")
    String gridPort();

    @Key("faker.locale")
    String fakerLocale();

    @Key("target")
    String target();
}
