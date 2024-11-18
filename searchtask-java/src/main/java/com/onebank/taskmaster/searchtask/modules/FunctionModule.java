package com.onebank.taskmaster.searchtask.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;
import com.onebank.taskmaster.searchtask.converter.ConvertTo;
import com.onebank.taskmaster.searchtask.converter.TaskDetailsConverter;
import com.onebank.taskmaster.searchtask.function.interceptors.Auditable;
import com.onebank.taskmaster.searchtask.function.interceptors.Validated;
import com.onebank.taskmaster.searchtask.function.interceptors.logger.LoggerInterceptor;
import com.onebank.taskmaster.searchtask.function.interceptors.validator.ValidatorInterceptor;
import com.onebank.taskmaster.searchtask.repository.DefaultTagRepository;
import com.onebank.taskmaster.searchtask.repository.DefaultTaskRepository;
import com.onebank.taskmaster.searchtask.repository.TagRepository;
import com.onebank.taskmaster.searchtask.repository.TaskRepository;
import com.onebank.taskmaster.searchtask.service.SearchFakeTaskService;
import com.onebank.taskmaster.searchtask.service.SearchTask;
import com.onebank.taskmaster.searchtask.service.ToggleTaskStatus;
import com.onebank.taskmaster.searchtask.service.ToggleTaskStatusService;

public class FunctionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SearchTask.class).to(SearchFakeTaskService.class).in(Scopes.SINGLETON);
        bind(ToggleTaskStatus.class).to(ToggleTaskStatusService.class).in(Scopes.SINGLETON);
        bind(TaskRepository.class).to(DefaultTaskRepository.class).in(Scopes.SINGLETON);
        bind(TagRepository.class).to(DefaultTagRepository.class).in(Scopes.SINGLETON);
        bind(ConvertTo.class).to(TaskDetailsConverter.class).in(Scopes.SINGLETON);

        // Bind the interceptor to all methods of classes annotated with @Auditable
        bindInterceptor(Matchers.annotatedWith(Auditable.class), Matchers.any(), new LoggerInterceptor());
        // Bind the interceptor to methods annotated with @Validated
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Validated.class), new ValidatorInterceptor());
    }
}
