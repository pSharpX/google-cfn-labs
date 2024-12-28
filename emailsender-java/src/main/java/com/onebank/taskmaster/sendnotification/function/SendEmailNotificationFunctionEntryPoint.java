package com.onebank.taskmaster.sendnotification.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onebank.taskmaster.sendnotification.config.InjectorProvider;
import com.onebank.taskmaster.sendnotification.function.exception.FunctionExceptionHandler;
import com.onebank.taskmaster.sendnotification.service.NotificationMessageConsumer;

public class SendEmailNotificationFunctionEntryPoint extends SendEmailNotificationFunction {
    public SendEmailNotificationFunctionEntryPoint() {
        super(InjectorProvider.getInjector().getInstance(NotificationMessageConsumer.class),
                new FunctionExceptionHandler(),
                InjectorProvider.getInjector().getInstance(ObjectMapper.class));
    }
}
