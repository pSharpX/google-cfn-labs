package com.onebank.taskmaster.templatemanager.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.onebank.taskmaster.templatemanager.config.AppConfigProperties;
import com.onebank.taskmaster.templatemanager.config.ConfigProvider;
import com.onebank.taskmaster.templatemanager.email.config.sendgrid.SendGridClientRequestInterceptor;
import com.onebank.taskmaster.templatemanager.email.config.sendgrid.SendGridConfigProperties;
import com.onebank.taskmaster.templatemanager.email.provider.sendgrid.SendGridClient;
import com.onebank.taskmaster.templatemanager.email.provider.sendgrid.SendGridClientRequestFactory;
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
    private final ObjectMapper objectMapper;

    @Override
    protected void configure() {
        if (isProviderEnabled(configProvider.getConfig(AppConfigProperties.class))) {
            SendGridConfigProperties configProperties = configProvider.getConfig(SendGridConfigProperties.class);
            bind(Properties.class).annotatedWith(Names.named("sendgridProperties")).toInstance(configProvider.getConfig("task-master.channel.email.sendgrid"));
            bind(SendGridConfigProperties.class).toInstance(configProperties);
            bind(SendGridClient.class).toInstance(buildSendGridClient(configProperties, objectMapper));
            bind(SendGridClientRequestFactory.class).in(Scopes.SINGLETON);
        }
    }

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
                && "sendgrid".equalsIgnoreCase(appConfigProperties.getNotification().getTemplate().getProvider());
    }
}
