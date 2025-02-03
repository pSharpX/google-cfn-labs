package com.onebank.taskmaster.createnotification.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.onebank.taskmaster.createnotification.config.AppConfigProperties;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.helper.PropertiesLoader;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@RequiredArgsConstructor
public class DynamicConfigModule extends AbstractModule {
    private final String propertiesFilePath;
    private static final String DEFAULT_CONFIG_LOCATION = "spring.config.location";

    @Provides
    @Singleton
    public Properties provideProperties() {
        Properties allProperties = new Properties();
        allProperties.putAll(PropertiesLoader.loadEnvironmentVariablesAsPropertiesNameStyle());
        allProperties.putAll(PropertiesLoader.loadEnvironmentVariables());
        allProperties.putAll(PropertiesLoader.loadProperties(propertiesFilePath));

        String configLocation = Optional.ofNullable(allProperties.getProperty(DEFAULT_CONFIG_LOCATION))
                .map(String::trim)
                .orElse(null);
        if (Objects.nonNull(configLocation)) {
            allProperties.putAll(PropertiesLoader.loadPropertiesFromExternalLocation(configLocation));
        }
        return allProperties;
    }

    @Provides
    @Singleton
    public AppConfigProperties provideAppConfigProperties(ConfigProvider configProvider) {
        return configProvider.getConfig(AppConfigProperties.class);
    }
}
