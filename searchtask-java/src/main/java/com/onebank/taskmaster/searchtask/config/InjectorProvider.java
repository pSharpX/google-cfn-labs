package com.onebank.taskmaster.searchtask.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.onebank.taskmaster.searchtask.modules.DatabaseModule;
import com.onebank.taskmaster.searchtask.modules.DynamicConfigModule;
import com.onebank.taskmaster.searchtask.modules.FunctionModule;
import lombok.Getter;

public class InjectorProvider {
    @Getter
    private static final Injector injector = Guice.createInjector(
            new DynamicConfigModule("application.properties"),
            new DatabaseModule(),
            new FunctionModule()
    );
}
