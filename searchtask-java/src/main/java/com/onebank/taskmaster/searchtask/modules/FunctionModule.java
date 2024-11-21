package com.onebank.taskmaster.searchtask.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.onebank.taskmaster.searchtask.config.AppConfigProperties;
import com.onebank.taskmaster.searchtask.converter.TaskDetailsConverter;
import com.onebank.taskmaster.searchtask.converter.TaskDetailsConverterService;
import com.onebank.taskmaster.searchtask.function.interceptors.Auditable;
import com.onebank.taskmaster.searchtask.function.interceptors.Validated;
import com.onebank.taskmaster.searchtask.function.interceptors.logger.LoggerInterceptor;
import com.onebank.taskmaster.searchtask.function.interceptors.validator.ValidatorInterceptor;
import com.onebank.taskmaster.searchtask.repository.*;
import com.onebank.taskmaster.searchtask.service.*;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Optional;

public class FunctionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TaskDetailsConverter.class).to(TaskDetailsConverterService.class).in(Scopes.SINGLETON);
        bind(ToggleTaskStatus.class).to(ToggleTaskStatusService.class).in(Scopes.SINGLETON);
        bind(SearchTask.class).to(SearchTaskService.class).in(Scopes.SINGLETON);

        // Bind the interceptor to all methods of classes annotated with @Auditable
        bindInterceptor(Matchers.annotatedWith(Auditable.class), Matchers.any(), new LoggerInterceptor());
        // Bind the interceptor to methods annotated with @Validated
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Validated.class), new ValidatorInterceptor());
    }

    @Provides
    @Singleton
    public TaskRepository buildTaskRepository(AppConfigProperties appConfigProperties, Optional<SqlSessionFactory> sqlSessionFactoryOptional) {
        if (!appConfigProperties.getDatabase().isEnabled()) {
            return new FakeTaskRepository();
        }
        return new PersistentTaskRepository(sqlSessionFactoryOptional.orElseThrow());
    }

    @Provides
    @Singleton
    public TagRepository buildTagRepository(AppConfigProperties appConfigProperties, Optional<SqlSessionFactory> sqlSessionFactoryOptional) {
        if (!appConfigProperties.getDatabase().isEnabled()) {
            return new FakeTagRepository();
        }
        return new PersistentTagRepository(sqlSessionFactoryOptional.orElseThrow());
    }
}
