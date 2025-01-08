package com.onebank.taskmaster.templatemanager.modules;

import com.google.inject.AbstractModule;
import com.onebank.taskmaster.templatemanager.config.AppConfigProperties;
import com.onebank.taskmaster.templatemanager.config.ConfigProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MockConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Override
    protected void configure() {
        if (!configProvider.getConfig(AppConfigProperties.class).getNotification().isEnabled()) {
            //bind(EmailSender.class).to(MockEmailSender.class).in(Scopes.SINGLETON);
        }
    }
}
