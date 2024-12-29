package com.onebank.taskmaster.sendnotification.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.onebank.taskmaster.sendnotification.config.AppConfigProperties;
import com.onebank.taskmaster.sendnotification.config.ConfigProvider;
import com.onebank.taskmaster.sendnotification.email.service.sender.EmailSender;
import com.onebank.taskmaster.sendnotification.email.service.sender.MockEmailSender;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MockConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Override
    protected void configure() {
        if (!configProvider.getConfig(AppConfigProperties.class).getNotification().isEnabled()) {
            bind(EmailSender.class).to(MockEmailSender.class).in(Scopes.SINGLETON);
        }
    }
}
