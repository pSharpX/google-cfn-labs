package com.onebank.taskmaster.sendnotification.sms.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.onebank.taskmaster.sendnotification.config.ConfigProvider;
import com.onebank.taskmaster.sendnotification.sms.config.twilio.TwilioClientRequestInterceptor;
import com.onebank.taskmaster.sendnotification.sms.config.twilio.TwilioConfigProperties;
import com.onebank.taskmaster.sendnotification.sms.provider.twilio.TwilioClient;
import com.onebank.taskmaster.sendnotification.sms.provider.twilio.TwilioClientErrorDecoder;
import com.onebank.taskmaster.sendnotification.sms.provider.twilio.TwilioMessageServiceClient;
import com.onebank.taskmaster.sendnotification.sms.service.sender.SmsSender;
import com.onebank.taskmaster.sendnotification.sms.service.sender.TwilioSmsSender;
import feign.Feign;
import feign.Logger;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;

import java.util.Properties;

@RequiredArgsConstructor
public class TwilioConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SmsSender.class).to(TwilioSmsSender.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    @Named("twilioProperties")
    public Properties twilioProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig("task-master.channel.sms.twilio");
    }

    @Provides
    @Singleton
    public TwilioConfigProperties twilioConfigProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(TwilioConfigProperties.class);
    }

    @Provides
    @Singleton
    public TwilioClient twilioClient(
            final TwilioConfigProperties twilioConfigProperties,
            final ObjectMapper objectMapper,
            final OkHttpClient okHttpClient) {
        return Feign.builder()
                .client(new feign.okhttp.OkHttpClient(okHttpClient))
                .encoder(new FormEncoder(new JacksonEncoder(objectMapper)))
                .decoder(new JacksonDecoder(objectMapper))
                .errorDecoder(new TwilioClientErrorDecoder(objectMapper))
                .requestInterceptor(new TwilioClientRequestInterceptor(twilioConfigProperties.getCredentials()))
                .logger(new Slf4jLogger(TwilioClient.class))
                .logLevel(Logger.Level.valueOf(twilioConfigProperties.getLogger().getLevel()))
                .target(TwilioClient.class, twilioConfigProperties.getBasePath());
    }

    @Provides
    @Singleton
    public TwilioMessageServiceClient twilioMessageServiceClient(
            final TwilioConfigProperties twilioConfigProperties,
            final ObjectMapper objectMapper,
            final OkHttpClient okHttpClient) {
        return Feign.builder()
                .client(new feign.okhttp.OkHttpClient(okHttpClient))
                .encoder(new FormEncoder(new JacksonEncoder(objectMapper)))
                .decoder(new JacksonDecoder(objectMapper))
                .errorDecoder(new TwilioClientErrorDecoder(objectMapper))
                .requestInterceptor(new TwilioClientRequestInterceptor(twilioConfigProperties.getCredentials()))
                .logger(new Slf4jLogger(TwilioMessageServiceClient.class))
                .logLevel(Logger.Level.valueOf(twilioConfigProperties.getLogger().getLevel()))
                .target(TwilioMessageServiceClient.class, twilioConfigProperties.getMessageService().getBasePath());
    }
}
