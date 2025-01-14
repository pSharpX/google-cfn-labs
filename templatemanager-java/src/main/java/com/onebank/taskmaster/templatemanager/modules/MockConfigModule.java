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
        AppConfigProperties appConfigProperties = configProvider.getConfig(AppConfigProperties.class);
        if (!appConfigProperties.isEnabled()) {
            bind(GetObjectContent.class).toInstance(mockGetObjectContent());
            bind(CreateOrUpdateTemplate.class).to(CreateOrUpdateMockTemplateService.class).in(Scopes.SINGLETON);
            bind(DeleteTemplate.class).to(DeleteMockTemplateService.class).in(Scopes.SINGLETON);
        } else if (!appConfigProperties.getTemplate().getStorage().isEnabled()) {
            bind(GetObjectContent.class).toInstance(mockGetObjectContent());
        }
    }

    private GetObjectContent mockGetObjectContent() {
        return (String name, String bucket) -> "mock object content!";
    }

}
