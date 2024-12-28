//package com.onebank.taskmaster.sendnotification.modules;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.inject.AbstractModule;
//import com.google.inject.Provides;
//import com.google.inject.Singleton;
//import com.google.inject.name.Named;
//import com.onebank.taskmaster.sendnotification.config.AppConfigProperties;
//import com.onebank.taskmaster.sendnotification.config.ConfigProvider;
//import com.onebank.taskmaster.sendnotification.email.config.sendgrid.SendGridClientRequestInterceptor;
//import com.onebank.taskmaster.sendnotification.email.config.sendgrid.SendGridConfigProperties;
//import com.onebank.taskmaster.sendnotification.email.provider.sendgrid.SendGridClient;
//import com.onebank.taskmaster.sendnotification.email.provider.sendgrid.SendGridClientRequestFactory;
//import com.onebank.taskmaster.sendnotification.email.service.sender.EmailSender;
//import com.onebank.taskmaster.sendnotification.email.service.sender.SendGridEmailSender;
//import feign.Feign;
//import feign.Logger;
//import feign.jackson.JacksonDecoder;
//import feign.jackson.JacksonEncoder;
//import feign.okhttp.OkHttpClient;
//import feign.slf4j.Slf4jLogger;
//
//import java.util.Properties;
//
//public class SendGridConfigModule extends AbstractModule {
//
//    @Provides
//    @Singleton
//    @Named("sendgridProperties")
//    public Properties provideSendGridProperties(ConfigProvider configProvider) {
//        return configProvider.getConfig("task-master.channel.email.sendgrid");
//    }
//
//    @Provides
//    @Singleton
//    public SendGridConfigProperties provideSendGridConfigProperties(ConfigProvider configProvider) {
//        return configProvider.getConfig(SendGridConfigProperties.class);
//    }
//
//    @Provides
//    @Singleton
//    public SendGridClient buildSendGridClient(AppConfigProperties appConfigProperties, SendGridConfigProperties sendGridConfigProperties, ObjectMapper objectMapper) {
//        if (isProviderDisabled(appConfigProperties)) {
//            return null;
//        }
//        return Feign.builder()
//                .client(new OkHttpClient())
//                .encoder(new JacksonEncoder(objectMapper))
//                .decoder(new JacksonDecoder(objectMapper))
//                .requestInterceptor(new SendGridClientRequestInterceptor(sendGridConfigProperties.getApikey()))
//                .logger(new Slf4jLogger(SendGridClient.class))
//                .logLevel(Logger.Level.FULL)
//                .target(SendGridClient.class, sendGridConfigProperties.getBasePath());
//    }
//
//    @Provides
//    @Singleton
//    public SendGridClientRequestFactory buildSendGridClientRequestFactory(AppConfigProperties appConfigProperties, @Named("sendgridProperties") Properties sendgridProperties) {
//        if (isProviderDisabled(appConfigProperties)) {
//            return null;
//        }
//        return new SendGridClientRequestFactory(sendgridProperties);
//    }
//
//    @Provides
//    @Singleton
//    public EmailSender buildSendGridEmailSender(AppConfigProperties appConfigProperties, SendGridClientRequestFactory clientRequestFactory, SendGridClient client) {
//        if (isProviderDisabled(appConfigProperties)) {
//            return null;
//        }
//        return new SendGridEmailSender(clientRequestFactory, client);
//    }
//
//    private boolean isProviderDisabled(AppConfigProperties appConfigProperties) {
//        return !appConfigProperties.getNotification().isEnabled()
//                || !"email".equalsIgnoreCase(appConfigProperties.getNotification().getConsumer().getChannel())
//                || !"sendgrid".equalsIgnoreCase(appConfigProperties.getNotification().getConsumer().getProvider());
//    }
//}
