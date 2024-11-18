package com.onebank.taskmaster.searchtask.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.onebank.taskmaster.searchtask.helper.PropertiesLoader;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor
public class DynamicConfigModule extends AbstractModule {
    private final String propertiesFilePath;

    @Provides
    @Singleton
    public Properties provideProperties() {
        return PropertiesLoader.loadProperties(propertiesFilePath);
    }
}
