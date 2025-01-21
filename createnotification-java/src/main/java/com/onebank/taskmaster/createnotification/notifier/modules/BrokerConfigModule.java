package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.onebank.taskmaster.createnotification.config.AppConfigProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrokerConfigModule extends AbstractModule {
    private final AppConfigProperties appConfigProperties;
    @Override
    protected void configure() {
        if (appConfigProperties.getNotification().isEnabled()) {
            String brokerProvider = appConfigProperties.getNotification().getEngine();
            switch (brokerProvider) {
                case "pubsub": install(new PubSubConfigModule());break;
                case "rabbit": install(new RabbitConfigModule());break;
                default: install(new KafkaConfigModule());break;
            }
        }
    }
}
