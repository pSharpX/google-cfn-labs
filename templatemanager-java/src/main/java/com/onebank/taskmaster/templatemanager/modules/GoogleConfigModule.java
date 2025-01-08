package com.onebank.taskmaster.templatemanager.modules;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.inject.AbstractModule;
import com.onebank.taskmaster.templatemanager.config.AppConfigProperties;
import com.onebank.taskmaster.templatemanager.config.ConfigProvider;
import com.onebank.taskmaster.templatemanager.exception.InternalServerException;
import com.onebank.taskmaster.templatemanager.exception.utils.ExceptionConstantsUtils;
import com.onebank.taskmaster.templatemanager.helper.ResourceLoader;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class GoogleConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Override
    protected void configure() {
        AppConfigProperties appConfigProperties = configProvider.getConfig(AppConfigProperties.class);
        if (!appConfigProperties.getNotification().isEnabled()) {
           return;
        }

        GoogleCredentials googleCredentials = resolveGoogleCredentials(appConfigProperties);
        bind(GoogleCredentials.class).toInstance(googleCredentials);
        install(new CloudStorageConfigModule(configProvider, googleCredentials));
    }

    public GoogleCredentials fixedGoogleCredentials(AppConfigProperties.Storage storage) throws IOException {
        return GoogleCredentials.fromStream(ResourceLoader.getResource(storage.getServiceAccountKeyPath()));
    }

    public GoogleCredentials defaultGoogleCredentials() throws IOException {
        return GoogleCredentials.getApplicationDefault();
    }

    private GoogleCredentials resolveGoogleCredentials(AppConfigProperties appConfigProperties) {
        try {
            if (enableDefaultCredentials(appConfigProperties.getNotification().getTemplate().getStorage())) {
                return defaultGoogleCredentials();
            } else {
                return fixedGoogleCredentials(appConfigProperties.getNotification().getTemplate().getStorage());
            }
        } catch (IOException exception) {
            throw new InternalServerException(ExceptionConstantsUtils.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    private boolean enableDefaultCredentials(AppConfigProperties.Storage storage) {
        return Optional.of(storage.getServiceAccountKeyPath())
                .orElse("").isBlank();
    }
}
