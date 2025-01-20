package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.notifier.config.KafkaConfigProperties;
import com.onebank.taskmaster.createnotification.service.producers.KafkaNotificationMessageProducer;
import com.onebank.taskmaster.createnotification.service.producers.NotificationMessageProducer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KafkaConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NotificationMessageProducer.class).to(KafkaNotificationMessageProducer.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    public KafkaConfigProperties kafkaConfigProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(KafkaConfigProperties.class);
    }
}
