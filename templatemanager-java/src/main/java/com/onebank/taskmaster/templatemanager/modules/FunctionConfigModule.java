package com.onebank.taskmaster.templatemanager.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.onebank.taskmaster.templatemanager.service.handler.EventHandlerResolver;
import com.onebank.taskmaster.templatemanager.service.handler.EventHandlerResolverService;
import com.onebank.taskmaster.templatemanager.service.handler.PubSubEventHandler;
import com.onebank.taskmaster.templatemanager.service.handler.PubSubEventHandlerService;
import com.onebank.taskmaster.templatemanager.service.handler.StorageEventHandler;
import com.onebank.taskmaster.templatemanager.service.handler.StorageEventHandlerService;
import com.onebank.taskmaster.templatemanager.service.manager.ActionTemplateResolver;
import com.onebank.taskmaster.templatemanager.service.manager.ActionTemplateResolverService;

public class FunctionConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EventHandlerResolver.class).to(EventHandlerResolverService.class).in(Scopes.SINGLETON);
        bind(PubSubEventHandler.class).to(PubSubEventHandlerService.class).in(Scopes.SINGLETON);
        bind(StorageEventHandler.class).to(StorageEventHandlerService.class).in(Scopes.SINGLETON);
        bind(ActionTemplateResolver.class).to(ActionTemplateResolverService.class).in(Scopes.SINGLETON);
    }
}
