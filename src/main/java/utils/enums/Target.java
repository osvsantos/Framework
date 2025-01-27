package utils.enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum Target {

    LOCAL("local"), LOCAL_SUITE("local-suite"), SELENIUM_GRID("selenium-grid"),
    TESTCONTAINERS("testcontainers");

    private final String value;
    private static final Map<String, Target> ENUM_MAP;

    Target(String value) {
        this.value = value;
    }

    static {
        // Criação do mapa imutável ENUM_MAP
        Map<String, Target> map = stream(Target.values())
                .collect(toMap(
                        instance -> instance.value.toLowerCase(), // Converte o valor para minúsculas
                        instance -> instance,                    // Associa o valor ao enum correspondente
                        (key1, key2) -> key2,                    // Resolve conflitos (não esperado aqui)
                        ConcurrentHashMap::new                   // Usa ConcurrentHashMap
                ));
        ENUM_MAP = Collections.unmodifiableMap(map);              // Torna o mapa imutável
    }

    public static Target get(String value) {
        // Verifica se o valor está no mapa ENUM_MAP
        if (!ENUM_MAP.containsKey(value.toLowerCase())) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' not valid. Use one of the TARGET enum values", value)
            );
        }

        return ENUM_MAP.get(value.toLowerCase());
    }
}
