package com.onebank.taskmaster.sendnotification.sms.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.onebank.taskmaster.sendnotification.service.NotificationMessageConsumer;
import com.onebank.taskmaster.sendnotification.sms.service.SmsNotificationMessageConsumer;

public class FunctionConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NotificationMessageConsumer.class).to(SmsNotificationMessageConsumer.class).in(Scopes.SINGLETON);
    }
}
