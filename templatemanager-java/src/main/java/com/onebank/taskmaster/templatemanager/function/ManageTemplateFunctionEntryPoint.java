package com.onebank.taskmaster.templatemanager.function;

import com.onebank.taskmaster.templatemanager.config.InjectorProvider;
import com.onebank.taskmaster.templatemanager.function.exception.FunctionExceptionHandler;
import com.onebank.taskmaster.templatemanager.service.handler.EventHandlerResolver;

public class ManageTemplateFunctionEntryPoint extends ManageTemplateFunction {
    public ManageTemplateFunctionEntryPoint() {
        super(InjectorProvider.getInjector().getInstance(EventHandlerResolver.class),
                new FunctionExceptionHandler());
    }
}
