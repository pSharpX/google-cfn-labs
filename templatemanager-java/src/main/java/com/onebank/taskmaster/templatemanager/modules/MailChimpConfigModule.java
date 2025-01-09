package com.onebank.taskmaster.templatemanager.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.onebank.taskmaster.templatemanager.config.AppConfigProperties;
import com.onebank.taskmaster.templatemanager.config.ConfigProvider;
import com.onebank.taskmaster.templatemanager.email.config.mailchimp.MailChimpClientRequestInterceptor;
import com.onebank.taskmaster.templatemanager.email.config.mailchimp.MailChimpConfigProperties;
import com.onebank.taskmaster.templatemanager.email.provider.mailchimp.MailChimpClient;
import com.onebank.taskmaster.templatemanager.email.provider.mailchimp.MailChimpClientRequestFactory;
import com.onebank.taskmaster.templatemanager.service.manager.CreateOrUpdateMailChimpTemplateService;
import com.onebank.taskmaster.templatemanager.service.manager.CreateOrUpdateTemplate;
import com.onebank.taskmaster.templatemanager.service.manager.DeleteMailChimpTemplateService;
import com.onebank.taskmaster.templatemanager.service.manager.DeleteTemplate;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor
public class MailChimpConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void configure() {
        if (isProviderEnabled(configProvider.getConfig(AppConfigProperties.class))) {
            MailChimpConfigProperties configProperties = configProvider.getConfig(MailChimpConfigProperties.class);
            bind(Properties.class).annotatedWith(Names.named("mailChimpProperties")).toInstance(configProvider.getConfig("task-master.channel.email.mailchimp"));
            bind(MailChimpConfigProperties.class).toInstance(configProperties);
            bind(MailChimpClient.class).toInstance(buildMailChimpClient(configProperties, objectMapper));
            bind(MailChimpClientRequestFactory.class).in(Scopes.SINGLETON);
            bind(CreateOrUpdateTemplate.class).to(CreateOrUpdateMailChimpTemplateService.class).in(Scopes.SINGLETON);
            bind(DeleteTemplate.class).to(DeleteMailChimpTemplateService.class).in(Scopes.SINGLETON);
        }
    }

    public MailChimpClient buildMailChimpClient(MailChimpConfigProperties mailChimpConfigProperties, ObjectMapper objectMapper) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .requestInterceptor(new MailChimpClientRequestInterceptor(mailChimpConfigProperties.getApikey()))
                .logger(new Slf4jLogger(MailChimpClient.class))
                .logLevel(Logger.Level.FULL)
                .target(MailChimpClient.class, mailChimpConfigProperties.getBasePath());
    }

    private boolean isProviderEnabled(AppConfigProperties appConfigProperties) {
        return appConfigProperties.getNotification().isEnabled()
                && "mailchimp".equalsIgnoreCase(appConfigProperties.getNotification().getTemplate().getProvider());
    }
}
