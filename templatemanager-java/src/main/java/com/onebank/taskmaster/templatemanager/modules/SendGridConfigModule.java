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
import com.onebank.taskmaster.templatemanager.email.provider.sendgrid.SendGridClientErrorDecoder;
import com.onebank.taskmaster.templatemanager.email.provider.sendgrid.SendGridClientRequestFactory;
import com.onebank.taskmaster.templatemanager.service.manager.CreateOrUpdateSendGridTemplateService;
import com.onebank.taskmaster.templatemanager.service.manager.CreateOrUpdateTemplate;
import com.onebank.taskmaster.templatemanager.service.manager.DeleteSendGridTemplateService;
import com.onebank.taskmaster.templatemanager.service.manager.DeleteTemplate;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;

import java.util.Properties;

@RequiredArgsConstructor
public class SendGridConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;
    private final ObjectMapper objectMapper;
    private final OkHttpClient okHttpClient;

    @Override
    protected void configure() {
        if (isProviderEnabled(configProvider.getConfig(AppConfigProperties.class))) {
            SendGridConfigProperties configProperties = configProvider.getConfig(SendGridConfigProperties.class);
            bind(Properties.class).annotatedWith(Names.named("sendgridProperties")).toInstance(configProvider.getConfig("task-master.channel.email.sendgrid"));
            bind(SendGridConfigProperties.class).toInstance(configProperties);
            bind(SendGridClient.class).toInstance(buildSendGridClient(configProperties, objectMapper, okHttpClient));
            bind(SendGridClientRequestFactory.class).in(Scopes.SINGLETON);
            bind(CreateOrUpdateTemplate.class).to(CreateOrUpdateSendGridTemplateService.class).in(Scopes.SINGLETON);
            bind(DeleteTemplate.class).to(DeleteSendGridTemplateService.class).in(Scopes.SINGLETON);
        }
    }

    public SendGridClient buildSendGridClient(SendGridConfigProperties sendGridConfigProperties, ObjectMapper objectMapper, OkHttpClient okHttpClient) {
        return Feign.builder()
                .client(new feign.okhttp.OkHttpClient(okHttpClient))
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .errorDecoder(new SendGridClientErrorDecoder(objectMapper))
                .requestInterceptor(new SendGridClientRequestInterceptor(sendGridConfigProperties.getApikey()))
                .logger(new Slf4jLogger(SendGridClient.class))
                .logLevel(Logger.Level.valueOf(sendGridConfigProperties.getLogger().getLevel()))
                .target(SendGridClient.class, sendGridConfigProperties.getBasePath());
    }

    private boolean isProviderEnabled(AppConfigProperties appConfigProperties) {
        return appConfigProperties.isEnabled()
                && "sendgrid".equalsIgnoreCase(appConfigProperties.getTemplate().getProvider());
    }
}
