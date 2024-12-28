package com.onebank.taskmaster.sendnotification.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.onebank.taskmaster.sendnotification.config.AppConfigProperties;
import com.onebank.taskmaster.sendnotification.config.ConfigProvider;
import com.onebank.taskmaster.sendnotification.config.EmailChannelConfigProperties;
import com.onebank.taskmaster.sendnotification.helper.PropertiesLoader;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor
public class DynamicConfigModule extends AbstractModule {
    private final String propertiesFilePath;

    @Provides
    @Singleton
    public Properties provideProperties() {
        Properties allProperties = new Properties();
        allProperties.putAll(PropertiesLoader.loadProperties(propertiesFilePath));
        allProperties.putAll(PropertiesLoader.loadEnvironmentVariables());
        return allProperties;
    }

    @Provides
    @Singleton
    public AppConfigProperties provideAppConfigProperties(ConfigProvider configProvider) {
        return configProvider.getConfig(AppConfigProperties.class);
    }

    @Provides
    @Singleton
    public EmailChannelConfigProperties provideEmailChannelConfigProperties(ConfigProvider configProvider) {
        return configProvider.getConfig(EmailChannelConfigProperties.class);
    }
}
