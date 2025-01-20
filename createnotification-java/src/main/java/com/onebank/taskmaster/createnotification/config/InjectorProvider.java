package com.onebank.taskmaster.createnotification.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.onebank.taskmaster.createnotification.modules.DynamicConfigModule;
import com.onebank.taskmaster.createnotification.modules.FunctionConfigModule;
import com.onebank.taskmaster.createnotification.modules.LogbookConfigModule;
import com.onebank.taskmaster.createnotification.modules.OkHttpClientConfigModule;
import com.onebank.taskmaster.createnotification.notifier.modules.BrokerConfigModule;
import com.onebank.taskmaster.createnotification.notifier.modules.DatabaseModule;
import com.onebank.taskmaster.createnotification.notifier.modules.GoogleConfigModule;
import com.onebank.taskmaster.createnotification.notifier.modules.MockConfigModule;
import com.onebank.taskmaster.createnotification.modules.SharedConfigModule;
import lombok.Getter;

public class InjectorProvider {
    @Getter
    private static final Injector injector;

    static {
        Injector parentInjector = Guice.createInjector(
                new DynamicConfigModule("application.properties"),
                new SharedConfigModule(),
                new LogbookConfigModule(),
                new OkHttpClientConfigModule());
        ConfigProvider configProvider = parentInjector.getInstance(ConfigProvider.class);
        AppConfigProperties appConfigProperties = configProvider.getConfig(AppConfigProperties.class);
        injector = parentInjector.createChildInjector(
                new GoogleConfigModule(configProvider),
                new DatabaseModule(appConfigProperties),
                new BrokerConfigModule(appConfigProperties),
                new MockConfigModule(configProvider),
                new FunctionConfigModule()
        );
    }
}
