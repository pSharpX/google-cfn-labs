package com.onebank.taskmaster.sendnotification.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.onebank.taskmaster.sendnotification.config.AppConfigProperties;
import com.onebank.taskmaster.sendnotification.config.ConfigProvider;
import com.onebank.taskmaster.sendnotification.email.config.mailchimp.MailChimpClientRequestInterceptor;
import com.onebank.taskmaster.sendnotification.email.config.mailchimp.MailChimpConfigProperties;
import com.onebank.taskmaster.sendnotification.email.provider.mailchimp.MailChimpClient;
import com.onebank.taskmaster.sendnotification.email.provider.mailchimp.MailChimpClientRequestFactory;
import com.onebank.taskmaster.sendnotification.email.service.sender.EmailSender;
import com.onebank.taskmaster.sendnotification.email.service.sender.MailChimpEmailSender;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import javax.annotation.Nullable;
import java.util.Properties;

public class MailChimpConfigModule extends AbstractModule {

    @Provides
    @Singleton
    @Named("mailChimpProperties")
    public Properties provideMailChimpProperties(ConfigProvider configProvider) {
        return configProvider.getConfig("task-master.channel.email.mailchimp");
    }

    @Provides
    @Singleton
    public MailChimpConfigProperties provideMailChimpConfigProperties(ConfigProvider configProvider) {
        return configProvider.getConfig(MailChimpConfigProperties.class);
    }

    @Provides
    @Singleton
    public MailChimpClient buildMailChimpClient(AppConfigProperties appConfigProperties, MailChimpConfigProperties mailChimpConfigProperties, ObjectMapper objectMapper) {
        if (isProviderDisabled(appConfigProperties)) {
            return null;
        }
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .requestInterceptor(new MailChimpClientRequestInterceptor(mailChimpConfigProperties.getApikey()))
                .logger(new Slf4jLogger(MailChimpClient.class))
                .logLevel(Logger.Level.FULL)
                .target(MailChimpClient.class, mailChimpConfigProperties.getBasePath());
    }

    @Provides
    @Singleton
    @Nullable
    public MailChimpClientRequestFactory buildMailChimpClientRequestFactory(AppConfigProperties appConfigProperties, @Named("mailChimpProperties") Properties mailChimpProperties) {
        if (isProviderDisabled(appConfigProperties)) {
            return null;
        }
        return new MailChimpClientRequestFactory(mailChimpProperties);
    }

    @Provides
    @Singleton
    public EmailSender buildMailChimpEmailSender(AppConfigProperties appConfigProperties, MailChimpClientRequestFactory clientRequestFactory, MailChimpClient client) {
        if (isProviderDisabled(appConfigProperties)) {
            return null;
        }
        return new MailChimpEmailSender(clientRequestFactory, client);
    }

    private boolean isProviderDisabled(AppConfigProperties appConfigProperties) {
        return !appConfigProperties.getNotification().isEnabled()
                || !"email".equalsIgnoreCase(appConfigProperties.getNotification().getConsumer().getChannel())
                || !"mailchimp".equalsIgnoreCase(appConfigProperties.getNotification().getConsumer().getProvider());
    }
}
