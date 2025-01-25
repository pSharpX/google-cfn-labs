package com.onebank.taskmaster.sendnotification.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.onebank.taskmaster.sendnotification.modules.DynamicConfigModule;
import com.onebank.taskmaster.sendnotification.modules.LogbookConfigModule;
import com.onebank.taskmaster.sendnotification.modules.OkHttpClientConfigModule;
import com.onebank.taskmaster.sendnotification.modules.SharedConfigModule;
import com.onebank.taskmaster.sendnotification.sms.modules.FunctionConfigModule;
import com.onebank.taskmaster.sendnotification.sms.modules.MockConfigModule;
import com.onebank.taskmaster.sendnotification.sms.modules.ProviderConfigModule;
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
                new ProviderConfigModule(appConfigProperties),
                new MockConfigModule(configProvider),
                new FunctionConfigModule()
        );
    }
}
