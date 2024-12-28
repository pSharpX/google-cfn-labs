//package com.onebank.taskmaster.sendnotification.modules;
//
//import com.google.inject.AbstractModule;
//import com.google.inject.Provides;
//import com.google.inject.Singleton;
//import com.onebank.taskmaster.sendnotification.config.AppConfigProperties;
//import com.onebank.taskmaster.sendnotification.email.service.sender.EmailSender;
//import com.onebank.taskmaster.sendnotification.email.service.sender.MockEmailSender;
//
//public class MockConfigModule extends AbstractModule {
//
//    @Provides
//    @Singleton
//    public EmailSender buildMockEmailSender(AppConfigProperties appConfigProperties) {
//        if (!appConfigProperties.getNotification().isEnabled()) {
//            return null;
//        }
//        return new MockEmailSender();
//    }
//}
