package com.onebank.taskmaster.sendnotification.sms.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.onebank.taskmaster.sendnotification.config.AppConfigProperties;
import com.onebank.taskmaster.sendnotification.config.ConfigProvider;
import com.onebank.taskmaster.sendnotification.sms.service.sender.MockSmsSender;
import com.onebank.taskmaster.sendnotification.sms.service.sender.SmsSender;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MockConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Override
    protected void configure() {
        if (!configProvider.getConfig(AppConfigProperties.class).getNotification().isEnabled()) {
            bind(SmsSender.class).to(MockSmsSender.class).in(Scopes.SINGLETON);
        }
    }
}
