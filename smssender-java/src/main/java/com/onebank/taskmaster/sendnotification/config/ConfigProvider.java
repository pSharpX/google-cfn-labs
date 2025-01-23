package com.onebank.taskmaster.sendnotification.config;

import com.google.inject.Inject;
import com.onebank.taskmaster.sendnotification.helper.PropertiesMapper;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ConfigProvider {
    private final Properties properties;

    public <T> T getConfig(Class<T> pojoClass) {
        return PropertiesMapper.mapPropertiesToPojo(properties, pojoClass);
    }

    public Properties getConfig(String prefix) {
        Properties result = new Properties();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith(prefix)) {
                // Remove the prefix from the key in the result
                String subKey;
                if (key.length() > prefix.length()) {
                    subKey = key.substring(prefix.length() + 1);
                } else {
                    subKey = key.substring(prefix.length());
                }
                result.setProperty(subKey, properties.getProperty(key));
            }
        }
        return result;
    }
}
