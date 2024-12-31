package com.onebank.taskmaster.sendnotification.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.onebank.taskmaster.sendnotification.config.AppConfigProperties;
import com.onebank.taskmaster.sendnotification.config.ConfigProvider;
import com.onebank.taskmaster.sendnotification.email.service.sender.EmailSender;
import com.onebank.taskmaster.sendnotification.email.service.sender.MailjetEmailSender;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor
public class MailjetConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void configure() {
        if (isProviderEnabled(configProvider.getConfig(AppConfigProperties.class))) {
            bind(Properties.class).annotatedWith(Names.named("mailjetProperties")).toInstance(configProvider.getConfig("task-master.channel.email.mailjet"));
            bind(EmailSender.class).to(MailjetEmailSender.class).in(Scopes.SINGLETON);
        }
    }

    private boolean isProviderEnabled(AppConfigProperties appConfigProperties) {
        return appConfigProperties.getNotification().isEnabled()
                && "email".equalsIgnoreCase(appConfigProperties.getNotification().getConsumer().getChannel())
                && "mailjet".equalsIgnoreCase(appConfigProperties.getNotification().getConsumer().getProvider());
    }
}
