package com.onebank.taskmaster.createnotification.modules;

import com.google.inject.AbstractModule;
import com.onebank.taskmaster.createnotification.config.AppConfigProperties;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MockConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Override
    protected void configure() {
        if (!configProvider.getConfig(AppConfigProperties.class).getNotification().isEnabled()) {

        }
    }
}
