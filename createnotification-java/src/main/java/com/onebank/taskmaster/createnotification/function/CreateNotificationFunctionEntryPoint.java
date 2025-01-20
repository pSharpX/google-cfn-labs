package com.onebank.taskmaster.createnotification.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onebank.taskmaster.createnotification.config.InjectorProvider;
import com.onebank.taskmaster.createnotification.function.exception.CloudEventFunctionExceptionHandler;
import com.onebank.taskmaster.createnotification.service.NotificationCreator;

public class CreateNotificationFunctionEntryPoint extends CreateNotificationCloudEventFunction {
    public CreateNotificationFunctionEntryPoint() {
        super(InjectorProvider.getInjector().getInstance(NotificationCreator.class),
                new CloudEventFunctionExceptionHandler(),
                InjectorProvider.getInjector().getInstance(ObjectMapper.class));
    }
}
