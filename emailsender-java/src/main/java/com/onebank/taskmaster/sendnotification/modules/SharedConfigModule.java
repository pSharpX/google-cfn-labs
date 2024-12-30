package com.onebank.taskmaster.sendnotification.modules;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import com.onebank.taskmaster.sendnotification.function.interceptors.Auditable;
import com.onebank.taskmaster.sendnotification.function.interceptors.Validated;
import com.onebank.taskmaster.sendnotification.function.interceptors.logger.LoggerInterceptor;
import com.onebank.taskmaster.sendnotification.function.interceptors.validator.ValidatorInterceptor;

public class SharedConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        // Bind the interceptor to all methods of classes annotated with @Auditable
        bindInterceptor(Matchers.annotatedWith(Auditable.class), Matchers.any(), new LoggerInterceptor());
        // Bind the interceptor to methods annotated with @Validated
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Validated.class), new ValidatorInterceptor());
    }

    @Provides
    @Singleton
    public ObjectMapper provideObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new ProtobufModule());
        return objectMapper;
    }
}
