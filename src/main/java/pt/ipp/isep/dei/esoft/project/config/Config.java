package pt.ipp.isep.dei.esoft.project.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    public Config(String configFilePath) throws IOException {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
