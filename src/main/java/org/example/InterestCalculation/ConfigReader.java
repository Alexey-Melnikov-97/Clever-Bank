package org.example.InterestCalculation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

/**
 * Класс ConfigReader предоставляет средства для чтения конфигурации из файла.
 */
public class ConfigReader {
    private static final String FILE_PATH = "config.yml";

    /**
     * Метод getConfig используется для чтения конфигурации из файла и возвращает результирующий объект Map,
     * содержащий значения из конфигурации.
     *
     * @return объект Map, содержащий значения из конфигурации.
     * @throws FileNotFoundException если файл конфигурации не найден.
     */
    public static Map<String, Object> getConfig() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            return yaml.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
