package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.pubsub.v1.TopicName;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.notifier.config.PubSubConfigProperties;
import com.onebank.taskmaster.createnotification.service.producers.NotificationMessageProducer;
import com.onebank.taskmaster.createnotification.service.producers.PubSubNotificationMessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class PubSubConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(NotificationMessageProducer.class).to(PubSubNotificationMessageProducer.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    public PubSubConfigProperties pubSubConfigProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(PubSubConfigProperties.class);
    }

    @Provides
    @Singleton
    public TopicName defaultTopic(final PubSubConfigProperties configProperties) {
        return TopicName.of(configProperties.getProjectId(), configProperties.getTopic());
    }

    @Provides
    @Singleton
    public Publisher publisher(final TopicName defTopicName, final GoogleCredentials googleCredentials) throws IOException {
        return Publisher.newBuilder(defTopicName)
                .setCredentialsProvider(FixedCredentialsProvider.create(googleCredentials))
                .build();
    }
}
