package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.inject.AbstractModule;
import com.google.pubsub.v1.TopicName;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.notifier.config.PubSubConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class PubSubConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;
    private final GoogleCredentials googleCredentials;

    @Override
    protected void configure() {
        PubSubConfigProperties appConfigProperties = configProvider.getConfig(PubSubConfigProperties.class);
        bind(Publisher.class).toInstance(buildPublisher(appConfigProperties, googleCredentials));
    }

    public Publisher buildPublisher(PubSubConfigProperties configProperties, GoogleCredentials googleCredentials) {
        try {
            TopicName topicName = TopicName.of(configProperties.getProjectId(), configProperties.getTopic());
            return Publisher.newBuilder(topicName)
                    .setCredentialsProvider(FixedCredentialsProvider.create(googleCredentials))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
