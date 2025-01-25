package com.onebank.taskmaster.sendnotification.sms.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.onebank.taskmaster.sendnotification.config.ConfigProvider;
import com.onebank.taskmaster.sendnotification.sms.config.plivo.PlivoClientRequestInterceptor;
import com.onebank.taskmaster.sendnotification.sms.config.plivo.PlivoConfigProperties;
import com.onebank.taskmaster.sendnotification.sms.provider.plivo.PlivoClient;
import com.onebank.taskmaster.sendnotification.sms.provider.plivo.PlivoClientErrorDecoder;
import com.onebank.taskmaster.sendnotification.sms.service.sender.PlivoSmsSender;
import com.onebank.taskmaster.sendnotification.sms.service.sender.SmsSender;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor
public class SNSConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SmsSender.class).to(PlivoSmsSender.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    @Named("snsProperties")
    public Properties snsProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig("task-master.channel.sms.sns");
    }

    @Provides
    @Singleton
    public PlivoConfigProperties snsConfigProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(PlivoConfigProperties.class);
    }

    @Provides
    @Singleton
    public PlivoClient snsClient(
            final PlivoConfigProperties plivoConfigProperties,
            final ObjectMapper objectMapper,
            final okhttp3.OkHttpClient okHttpClient) {
        return Feign.builder()
                .client(new feign.okhttp.OkHttpClient(okHttpClient))
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .errorDecoder(new PlivoClientErrorDecoder(objectMapper))
                .requestInterceptor(new PlivoClientRequestInterceptor(plivoConfigProperties.getSecret()))
                .logger(new Slf4jLogger(PlivoClient.class))
                .logLevel(Logger.Level.FULL)
                .target(PlivoClient.class, plivoConfigProperties.getBasePath());
    }
}
