package com.onebank.taskmaster.createnotification.modules;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.inject.AbstractModule;
import com.onebank.taskmaster.createnotification.config.AppConfigProperties;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.exception.InternalServerException;
import com.onebank.taskmaster.createnotification.exception.utils.ExceptionConstantsUtils;
import com.onebank.taskmaster.createnotification.helper.ResourceLoader;
import com.onebank.taskmaster.createnotification.notifier.config.PubSubConfigProperties;
import com.onebank.taskmaster.createnotification.notifier.modules.PubSubConfigModule;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class GoogleConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Override
    protected void configure() {
        AppConfigProperties appConfigProperties = configProvider.getConfig(AppConfigProperties.class);
        PubSubConfigProperties pubSubConfigProperties = configProvider.getConfig(PubSubConfigProperties.class);
        if (!appConfigProperties.getNotification().isEnabled()
                || !appConfigProperties.getNotification().getEngine().equalsIgnoreCase("pubsub")) {
           return;
        }

        GoogleCredentials googleCredentials = resolveGoogleCredentials(pubSubConfigProperties);
        bind(GoogleCredentials.class).toInstance(googleCredentials);
        install(new PubSubConfigModule(configProvider, googleCredentials));
    }

    public GoogleCredentials fixedGoogleCredentials(PubSubConfigProperties configProperties) throws IOException {
        return GoogleCredentials.fromStream(ResourceLoader.getResource(configProperties.getServiceAccountKeyPath()));
    }

    public GoogleCredentials defaultGoogleCredentials() throws IOException {
        return GoogleCredentials.getApplicationDefault();
    }

    private GoogleCredentials resolveGoogleCredentials(PubSubConfigProperties configProperties) {
        try {
            if (enableDefaultCredentials(configProperties)) {
                return defaultGoogleCredentials();
            } else {
                return fixedGoogleCredentials(configProperties);
            }
        } catch (IOException exception) {
            throw new InternalServerException(ExceptionConstantsUtils.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    private boolean enableDefaultCredentials(PubSubConfigProperties configProperties) {
        return Optional.ofNullable(configProperties.getServiceAccountKeyPath())
                .orElse("").isBlank();
    }
}
