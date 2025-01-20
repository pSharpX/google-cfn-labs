package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.OptionalBinder;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.notifier.config.DatabaseConfigProperties;
import com.onebank.taskmaster.createnotification.repository.NotificationPreferenceRepository;
import com.onebank.taskmaster.createnotification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        OptionalBinder.newOptionalBinder(binder(), NotificationRepository.class);
        OptionalBinder.newOptionalBinder(binder(), NotificationPreferenceRepository.class);
    }

    @Provides
    @Singleton
    public DatabaseConfigProperties databaseConfigProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(DatabaseConfigProperties.class);
    }
}
