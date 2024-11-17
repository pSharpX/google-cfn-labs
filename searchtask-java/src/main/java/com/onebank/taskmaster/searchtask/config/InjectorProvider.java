package com.onebank.taskmaster.searchtask.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;

public class InjectorProvider {
    @Getter
    private static final Injector injector = Guice.createInjector(new FunctionModule());
}
