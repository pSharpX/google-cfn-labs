package com.onebank.taskmaster.searchtask.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;
import com.onebank.taskmaster.searchtask.converter.ConvertTo;
import com.onebank.taskmaster.searchtask.converter.TaskDetailsConverter;
import com.onebank.taskmaster.searchtask.function.config.ValidatorProvider;
import com.onebank.taskmaster.searchtask.function.interceptors.Auditable;
import com.onebank.taskmaster.searchtask.function.interceptors.logger.LoggerInterceptor;
import com.onebank.taskmaster.searchtask.repository.DefaultTagRepository;
import com.onebank.taskmaster.searchtask.repository.DefaultTaskRepository;
import com.onebank.taskmaster.searchtask.repository.TagRepository;
import com.onebank.taskmaster.searchtask.repository.TaskRepository;
import com.onebank.taskmaster.searchtask.service.SearchFakeTaskService;
import com.onebank.taskmaster.searchtask.service.SearchTask;
import com.onebank.taskmaster.searchtask.service.ToggleTaskStatus;
import com.onebank.taskmaster.searchtask.service.ToggleTaskStatusService;
import jakarta.validation.Validator;

public class FunctionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SearchTask.class).to(SearchFakeTaskService.class).in(Scopes.SINGLETON);
        bind(ToggleTaskStatus.class).to(ToggleTaskStatusService.class).in(Scopes.SINGLETON);
        bind(TaskRepository.class).to(DefaultTaskRepository.class).in(Scopes.SINGLETON);
        bind(TagRepository.class).to(DefaultTagRepository.class).in(Scopes.SINGLETON);
        bind(ConvertTo.class).to(TaskDetailsConverter.class).in(Scopes.SINGLETON);
        bind(Validator.class).toProvider(ValidatorProvider.class).in(Scopes.SINGLETON);
        // Bind the interceptor to methods annotated with @LogExecutionTime
        // bindInterceptor(Matchers.any(), Matchers.annotatedWith(Auditable.class), new LoggerInterceptor());
        // Bind the interceptor to all methods of classes annotated with @LogExecutionTime
        bindInterceptor(Matchers.annotatedWith(Auditable.class), Matchers.any(), new LoggerInterceptor());
    }
}
