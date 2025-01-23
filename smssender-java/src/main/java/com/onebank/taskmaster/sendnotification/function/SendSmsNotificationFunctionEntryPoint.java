package com.onebank.taskmaster.sendnotification.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onebank.taskmaster.sendnotification.config.InjectorProvider;
import com.onebank.taskmaster.sendnotification.function.exception.CloudEventFunctionExceptionHandler;
import com.onebank.taskmaster.sendnotification.service.NotificationMessageConsumer;

public class SendSmsNotificationFunctionEntryPoint extends SendSmsNotificationCloudEventFunction {
    public SendSmsNotificationFunctionEntryPoint() {
        super(InjectorProvider.getInjector().getInstance(NotificationMessageConsumer.class),
                new CloudEventFunctionExceptionHandler(),
                InjectorProvider.getInjector().getInstance(ObjectMapper.class));
    }
}
