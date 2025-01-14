package com.onebank.taskmaster.templatemanager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.onebank.taskmaster.templatemanager.modules.DynamicConfigModule;
import com.onebank.taskmaster.templatemanager.modules.FunctionConfigModule;
import com.onebank.taskmaster.templatemanager.modules.GoogleConfigModule;
import com.onebank.taskmaster.templatemanager.modules.LogbookConfigModule;
import com.onebank.taskmaster.templatemanager.modules.MailChimpConfigModule;
import com.onebank.taskmaster.templatemanager.modules.MockConfigModule;
import com.onebank.taskmaster.templatemanager.modules.OkHttpClientConfigModule;
import com.onebank.taskmaster.templatemanager.modules.SendGridConfigModule;
import com.onebank.taskmaster.templatemanager.modules.SharedConfigModule;
import lombok.Getter;
import okhttp3.OkHttpClient;

public class InjectorProvider {
    @Getter
    private static final Injector injector;

    static {
        Injector parentInjector = Guice.createInjector(
                new DynamicConfigModule("application.properties"),
                new SharedConfigModule());
        ConfigProvider configProvider = parentInjector.getInstance(ConfigProvider.class);
        ObjectMapper objectMapper = parentInjector.getInstance(ObjectMapper.class);
        Injector intermediateInjector = parentInjector.createChildInjector(
                new LogbookConfigModule(configProvider),
                new OkHttpClientConfigModule()
        );
        OkHttpClient okHttpClient = intermediateInjector.getInstance(OkHttpClient.class);
        injector = intermediateInjector.createChildInjector(
                new MockConfigModule(configProvider),
                new MailChimpConfigModule(configProvider, objectMapper, okHttpClient),
                new SendGridConfigModule(configProvider, objectMapper, okHttpClient),
                new GoogleConfigModule(configProvider),
                new FunctionConfigModule()
        );
    }
}
