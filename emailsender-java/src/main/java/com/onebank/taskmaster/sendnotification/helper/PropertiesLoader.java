package com.onebank.taskmaster.sendnotification.helper;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@UtilityClass
public class PropertiesLoader {
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (InputStream fis = PropertiesLoader.class.getClassLoader().getResourceAsStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
        return properties;
    }

    public static Properties loadEnvironmentVariables() {
        // Get environment variables
        Map<String, String> env = System.getenv();
        Properties properties = new Properties();
        for (Map.Entry<String, String> entry : env.entrySet()) {
            properties.setProperty(entry.getKey(), entry.getValue());
        }
        return properties;
    }
}
