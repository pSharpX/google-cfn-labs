package com.onebank.taskmaster.createnotification.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onebank.taskmaster.createnotification.config.InjectorProvider;
import com.onebank.taskmaster.createnotification.function.exception.FunctionExceptionHandler;
import com.onebank.taskmaster.createnotification.service.NotificationCreator;

public class CreateNotificationFunctionEntryPoint extends CreateNotificationFunction {
    public CreateNotificationFunctionEntryPoint() {
        super(InjectorProvider.getInjector().getInstance(NotificationCreator.class),
                new FunctionExceptionHandler(),
                InjectorProvider.getInjector().getInstance(ObjectMapper.class));
    }
}
