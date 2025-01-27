package utils.report;

import utils.enums.Target; // Certifique-se de que a classe Target está neste pacote
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

import static utils.config.ConfigurationManager.configuration;

public class AllureManager {

    private AllureManager() {
    }

    public static void setAllureEnvironmentInformation() {
        // Criação do mapa básico com informações de ambiente
        var basicInfo = new HashMap<String, String>(Map.of(
                "Test URL", configuration().url(),
                "Target execution", configuration().target(),
                "Global timeout", String.valueOf(configuration().timeout()),
                "Headless mode", String.valueOf(configuration().headless()),
//                "Faker locale", configuration().faker(),
                "Local browser", configuration().browser()
        ));

        // Adiciona informações específicas para Selenium Grid, caso necessário
        if (configuration().target().equalsIgnoreCase(Target.SELENIUM_GRID.name())) {
            var gridMap = Map.of(
                "Grid URL", configuration().gridUrl(),
                "Grid port", String.valueOf(configuration().gridPort()) // Converter para String se necessário
            );
            basicInfo.putAll(gridMap);
        }

        // Escreve o mapa de informações no Allure
        AllureEnvironmentWriter.allureEnvironmentWriter(ImmutableMap.copyOf(basicInfo));
    }
}