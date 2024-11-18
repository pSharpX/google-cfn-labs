package com.onebank.taskmaster.searchtask.helper;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
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
}
