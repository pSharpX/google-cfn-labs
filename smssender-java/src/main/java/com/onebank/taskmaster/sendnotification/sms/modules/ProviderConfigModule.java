package com.onebank.taskmaster.sendnotification.sms.modules;

import com.google.inject.AbstractModule;
import com.onebank.taskmaster.sendnotification.config.AppConfigProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProviderConfigModule extends AbstractModule {
    private final AppConfigProperties appConfigProperties;
    @Override
    protected void configure() {
        if (appConfigProperties.getNotification().isEnabled() &&
                "sms".equalsIgnoreCase(appConfigProperties.getNotification().getConsumer().getChannel())) {
            String provider = appConfigProperties.getNotification().getConsumer().getProvider();
            switch (provider) {
                case "plivo": install(new PlivoConfigModule());break;
                case "sns": ;break;
                default: install(new TwilioConfigModule());break;
            }
        }
    }
}
