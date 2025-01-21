package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.model.AbstractMessage;
import com.onebank.taskmaster.createnotification.notifier.config.KafkaConfigProperties;
import com.onebank.taskmaster.createnotification.service.producers.KafkaNotificationMessageProducer;
import com.onebank.taskmaster.createnotification.service.producers.NotificationMessageProducer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

@RequiredArgsConstructor
public class KafkaConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NotificationMessageProducer.class).to(KafkaNotificationMessageProducer.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    @Named("kafkaProducerProperties")
    public Properties kafkaProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig("task-master.notification.kafka.producer");
    }

    @Provides
    @Singleton
    public KafkaConfigProperties kafkaConfigProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(KafkaConfigProperties.class);
    }

    @Provides
    @Singleton
    public KafkaProducer<String, AbstractMessage> kafkaProducer(final @Named("kafkaProducerProperties") Properties kafkaProperties) {
        return new KafkaProducer<String, AbstractMessage>(kafkaProperties);
    }
}
