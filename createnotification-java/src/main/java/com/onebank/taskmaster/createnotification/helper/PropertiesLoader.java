package com.onebank.taskmaster.createnotification.helper;

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

    public static Properties loadEnvironmentVariablesAsPropertiesNameStyle() {
        // Get environment variables
        Map<String, String> env = System.getenv();
        Properties properties = new Properties();
        for (Map.Entry<String, String> entry : env.entrySet()) {
            String propName = convertToPropertyName(entry.getKey());
            properties.setProperty(propName, entry.getValue());
        }
        return properties;
    }

    // Method to convert environment variable name to property name style
    private static String convertToPropertyName(String envVarName) {
        // Convert to lowercase and replace underscores with dots
        return envVarName.toLowerCase().replace('_', '.');
    }
}
