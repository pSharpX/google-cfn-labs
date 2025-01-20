package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.notifier.config.RabbitConfigProperties;
import com.onebank.taskmaster.createnotification.service.producers.NotificationMessageProducer;
import com.onebank.taskmaster.createnotification.service.producers.RabbitNotificationMessageProducer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RabbitConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NotificationMessageProducer.class).to(RabbitNotificationMessageProducer.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    public RabbitConfigProperties rabbitConfigProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(RabbitConfigProperties.class);
    }
}
