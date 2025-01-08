package com.onebank.taskmaster.templatemanager.function;

import com.onebank.taskmaster.templatemanager.config.InjectorProvider;
import com.onebank.taskmaster.templatemanager.function.exception.CloudEventFunctionExceptionHandler;
import com.onebank.taskmaster.templatemanager.service.handler.EventHandlerResolver;

public class ManageTemplateFunctionEntryPoint extends ManageTemplateCloudEventFunction {
    public ManageTemplateFunctionEntryPoint() {
        super(InjectorProvider.getInjector().getInstance(EventHandlerResolver.class),
                new CloudEventFunctionExceptionHandler());
    }
}
