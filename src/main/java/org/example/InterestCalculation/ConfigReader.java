package org.example.InterestCalculation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class ConfigReader {
    private static final String FILE_PATH = "config.yml";

    public static Map<String, Object> getConfig() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            return yaml.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
