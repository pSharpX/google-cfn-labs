package com.onebank.taskmaster.sendnotification.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.onebank.taskmaster.sendnotification.config.AppConfigProperties;
import com.onebank.taskmaster.sendnotification.config.ConfigProvider;
import com.onebank.taskmaster.sendnotification.email.config.sendgrid.SendGridClientRequestInterceptor;
import com.onebank.taskmaster.sendnotification.email.config.sendgrid.SendGridConfigProperties;
import com.onebank.taskmaster.sendnotification.email.provider.sendgrid.SendGridClient;
import com.onebank.taskmaster.sendnotification.email.provider.sendgrid.SendGridClientRequestFactory;
import com.onebank.taskmaster.sendnotification.email.service.sender.EmailSender;
import com.onebank.taskmaster.sendnotification.email.service.sender.SendGridEmailSender;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor
public class SendGridConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Override
    protected void configure() {
        if (isProviderEnabled(configProvider.getConfig(AppConfigProperties.class))) {
            bind(SendGridConfigProperties.class).toInstance(configProvider.getConfig(SendGridConfigProperties.class));
            bind(SendGridClientRequestFactory.class).in(Scopes.SINGLETON);
            bind(EmailSender.class).to(SendGridEmailSender.class).in(Scopes.SINGLETON);
        }
    }

    @Provides
    @Singleton
    @Named("sendgridProperties")
    public Properties provideSendGridProperties(ConfigProvider configProvider) {
        return configProvider.getConfig("task-master.channel.email.sendgrid");
    }

    @Provides
    @Singleton
    public SendGridClient buildSendGridClient(SendGridConfigProperties sendGridConfigProperties, ObjectMapper objectMapper) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .requestInterceptor(new SendGridClientRequestInterceptor(sendGridConfigProperties.getApikey()))
                .logger(new Slf4jLogger(SendGridClient.class))
                .logLevel(Logger.Level.FULL)
                .target(SendGridClient.class, sendGridConfigProperties.getBasePath());
    }

    private boolean isProviderEnabled(AppConfigProperties appConfigProperties) {
        return appConfigProperties.getNotification().isEnabled()
                && "email".equalsIgnoreCase(appConfigProperties.getNotification().getConsumer().getChannel())
                && "sendgrid".equalsIgnoreCase(appConfigProperties.getNotification().getConsumer().getProvider());
    }
}
