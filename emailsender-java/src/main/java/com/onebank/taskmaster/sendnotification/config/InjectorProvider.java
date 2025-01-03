package com.onebank.taskmaster.sendnotification.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.onebank.taskmaster.sendnotification.modules.DynamicConfigModule;
import com.onebank.taskmaster.sendnotification.modules.FunctionConfigModule;
import com.onebank.taskmaster.sendnotification.modules.MailChimpConfigModule;
import com.onebank.taskmaster.sendnotification.modules.MailgunConfigModule;
import com.onebank.taskmaster.sendnotification.modules.MailjetConfigModule;
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
        ObjectMapper objectMapper = parentInjector.getInstance(ObjectMapper.class);
        injector = parentInjector.createChildInjector(
                new MockConfigModule(configProvider),
                new MailChimpConfigModule(configProvider, objectMapper),
                new SendGridConfigModule(configProvider, objectMapper),
                new MailgunConfigModule(configProvider, objectMapper),
                new MailjetConfigModule(configProvider, objectMapper),
                new FunctionConfigModule()
        );
    }
}
