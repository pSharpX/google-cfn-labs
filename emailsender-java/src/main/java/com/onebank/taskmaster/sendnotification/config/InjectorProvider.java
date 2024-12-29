package com.onebank.taskmaster.sendnotification.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.onebank.taskmaster.sendnotification.modules.DynamicConfigModule;
import com.onebank.taskmaster.sendnotification.modules.FunctionConfigModule;
import com.onebank.taskmaster.sendnotification.modules.MailChimpConfigModule;
import com.onebank.taskmaster.sendnotification.modules.MockConfigModule;
import com.onebank.taskmaster.sendnotification.modules.SendGridConfigModule;
import com.onebank.taskmaster.sendnotification.modules.SharedConfigModule;
import lombok.Getter;

public class InjectorProvider {
    @Getter
    private static final Injector injector;

    static {
        Injector parentInjector = Guice.createInjector(
                new DynamicConfigModule("application.properties"),
                new SharedConfigModule());
        ConfigProvider configProvider = parentInjector.getInstance(ConfigProvider.class);
        injector = parentInjector.createChildInjector(
                new MockConfigModule(configProvider),
                new MailChimpConfigModule(configProvider),
                new SendGridConfigModule(configProvider),
                new FunctionConfigModule()
        );
    }
}
