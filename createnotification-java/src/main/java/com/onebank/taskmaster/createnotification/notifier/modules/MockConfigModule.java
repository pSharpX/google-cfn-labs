package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.onebank.taskmaster.createnotification.config.AppConfigProperties;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.entity.NotificationEntity;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.createnotification.repository.NotificationRepository;
import com.onebank.taskmaster.createnotification.service.producers.NotificationMessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class MockConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Override
    protected void configure() {
        if (!configProvider.getConfig(AppConfigProperties.class).getNotification().isEnabled()) {
            bind(NotificationMessageProducer.class).toInstance(buildMockMessageProducer());
            bind(NotificationRepository.class).toInstance(buildMockNotificationRepository());
        }
    }

    public NotificationRepository buildMockNotificationRepository()  {
        return (NotificationEntity entity) -> {
            log.debug("Saving [{}] notification entity", entity.getTitle());
            entity.setId(100L);
            return entity;
        };
    }

    public NotificationMessageProducer buildMockMessageProducer() {
        return (NotificationMessage notificationMessage) -> {
            log.debug("Publishing [Channel={}, Type={}] notification message to Mock provider...", notificationMessage.getChannel(), notificationMessage.getType());
        };
    }
}
