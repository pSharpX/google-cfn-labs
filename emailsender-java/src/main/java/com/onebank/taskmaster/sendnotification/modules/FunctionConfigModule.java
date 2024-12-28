package com.onebank.taskmaster.sendnotification.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.onebank.taskmaster.sendnotification.email.service.EmailNotificationMessageConsumer;
import com.onebank.taskmaster.sendnotification.service.NotificationMessageConsumer;

public class FunctionConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NotificationMessageConsumer.class).to(EmailNotificationMessageConsumer.class).in(Scopes.SINGLETON);
    }
}
