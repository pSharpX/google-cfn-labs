package com.onebank.taskmaster.sendnotification.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.onebank.taskmaster.sendnotification.modules.DynamicConfigModule;
import com.onebank.taskmaster.sendnotification.modules.FunctionConfigModule;
import com.onebank.taskmaster.sendnotification.modules.MailChimpConfigModule;
//import com.onebank.taskmaster.sendnotification.modules.MockConfigModule;
//import com.onebank.taskmaster.sendnotification.modules.SendGridConfigModule;
import com.onebank.taskmaster.sendnotification.modules.SharedConfigModule;
import lombok.Getter;

public class InjectorProvider {
    @Getter
    private static final Injector injector = Guice.createInjector(
            new DynamicConfigModule("application.properties"),
            new SharedConfigModule(),
            //new MockConfigModule(),
            new MailChimpConfigModule(),
            //new SendGridConfigModule(),
            new FunctionConfigModule()
    );
}
