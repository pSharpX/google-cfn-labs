package com.onebank.taskmaster.templatemanager.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.onebank.taskmaster.templatemanager.config.AppConfigProperties;
import com.onebank.taskmaster.templatemanager.config.ConfigProvider;
import com.onebank.taskmaster.templatemanager.service.manager.CreateOrUpdateMockTemplateService;
import com.onebank.taskmaster.templatemanager.service.manager.CreateOrUpdateTemplate;
import com.onebank.taskmaster.templatemanager.service.manager.DeleteMockTemplateService;
import com.onebank.taskmaster.templatemanager.service.manager.DeleteTemplate;
import com.onebank.taskmaster.templatemanager.service.storage.GetObjectContent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MockConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Override
    protected void configure() {
        if (!configProvider.getConfig(AppConfigProperties.class).getNotification().isEnabled()) {
            bind(GetObjectContent.class).toInstance(mockGetObjectContent());
            bind(CreateOrUpdateTemplate.class).to(CreateOrUpdateMockTemplateService.class).in(Scopes.SINGLETON);
            bind(DeleteTemplate.class).to(DeleteMockTemplateService.class).in(Scopes.SINGLETON);
        }
    }

    private GetObjectContent mockGetObjectContent() {
        return (String name, String bucket) -> "mock object content!";
    }

}
