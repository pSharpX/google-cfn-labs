package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.onebank.taskmaster.createnotification.config.AppConfigProperties;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.notifier.config.DatabaseConfigProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DatabaseModule extends AbstractModule {
    private final AppConfigProperties appConfigProperties;
    @Override
    protected void configure() {
        if (appConfigProperties.getNotification().isEnabled() &&
                appConfigProperties.getDatabase().isEnabled()) {
            install(new MyBatisConfigModule());
        }
    }

    @Provides
    @Singleton
    public DatabaseConfigProperties databaseConfigProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(DatabaseConfigProperties.class);
    }
}
