package com.onebank.taskmaster.templatemanager.modules;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.onebank.taskmaster.templatemanager.config.AppConfigProperties;
import com.onebank.taskmaster.templatemanager.config.ConfigProvider;
import com.onebank.taskmaster.templatemanager.service.storage.DefaultGetObjectContentService;
import com.onebank.taskmaster.templatemanager.service.storage.GetObjectContent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CloudStorageConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;
    private final GoogleCredentials googleCredentials;

    @Override
    protected void configure() {
        AppConfigProperties appConfigProperties = configProvider.getConfig(AppConfigProperties.class);
        bind(Storage.class).toInstance(buildStorageService(appConfigProperties.getTemplate().getStorage(), googleCredentials));
        bind(GetObjectContent.class).to(DefaultGetObjectContentService.class).in(Scopes.SINGLETON);
    }

    public Storage buildStorageService(AppConfigProperties.Storage storage, GoogleCredentials googleCredentials) {
        return StorageOptions.newBuilder()
                .setCredentials(googleCredentials)
                .setProjectId(storage.getProjectId())
                .build().getService();
    }
}
