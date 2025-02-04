package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.onebank.taskmaster.createnotification.config.AppConfigProperties;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.entity.NotificationEntity;
import com.onebank.taskmaster.createnotification.entity.NotificationPreferenceEntity;
import com.onebank.taskmaster.createnotification.notifier.config.DatabaseConfigProperties;
import com.onebank.taskmaster.createnotification.repository.NotificationPreferenceRepository;
import com.onebank.taskmaster.createnotification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class DatabaseModule extends AbstractModule {
    private final AppConfigProperties appConfigProperties;
    @Override
    protected void configure() {
        if (!appConfigProperties.getNotification().isEnabled() ||
                !appConfigProperties.getDatabase().isEnabled()) {
            bind(NotificationRepository.class).toInstance(buildMockNotificationRepository());
            bind(NotificationPreferenceRepository.class).toInstance(buildMockNotificationPreferenceRepository());
            return;
        }
        install(new MyBatisConfigModule());
    }

    public NotificationRepository buildMockNotificationRepository()  {
        return (NotificationEntity entity) -> {
            log.debug("Saving [{}] notification entity", entity.getTitle());
            entity.setId(100L);
            return entity;
        };
    }

    public NotificationPreferenceRepository buildMockNotificationPreferenceRepository() {
        return (String userIdentifier) -> {
            log.debug("Fetching notification preference for [{}]", userIdentifier);
            return Optional.of(NotificationPreferenceEntity.builder()
                            .id(100L)
                            .emailEnabled(false)
                            .smsEnabled(false)
                    .build());
        };
    }

    @Provides
    @Singleton
    public DatabaseConfigProperties databaseConfigProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(DatabaseConfigProperties.class);
    }
}
