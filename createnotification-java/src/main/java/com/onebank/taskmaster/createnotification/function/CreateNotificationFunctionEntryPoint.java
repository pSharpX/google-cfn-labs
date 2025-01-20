package com.onebank.taskmaster.createnotification.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onebank.taskmaster.createnotification.config.InjectorProvider;
import com.onebank.taskmaster.createnotification.function.exception.CloudEventFunctionExceptionHandler;
import com.onebank.taskmaster.createnotification.service.NotificationMessageConsumer;

public class CreateNotificationFunctionEntryPoint extends CreateNotificationCloudEventFunction {
    public CreateNotificationFunctionEntryPoint() {
        super(InjectorProvider.getInjector().getInstance(NotificationMessageConsumer.class),
                new CloudEventFunctionExceptionHandler(),
                InjectorProvider.getInjector().getInstance(ObjectMapper.class));
    }
}
