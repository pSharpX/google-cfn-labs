package com.onebank.taskmaster.searchtask.config;

import com.google.inject.AbstractModule;
import com.onebank.taskmaster.searchtask.converter.ConvertTo;
import com.onebank.taskmaster.searchtask.converter.TaskDetailsConverter;
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
        bind(SearchTask.class).to(SearchFakeTaskService.class);
        bind(ToggleTaskStatus.class).to(ToggleTaskStatusService.class);
        bind(TaskRepository.class).to(DefaultTaskRepository.class);
        bind(TagRepository.class).to(DefaultTagRepository.class);
        bind(ConvertTo.class).to(TaskDetailsConverter.class);
    }
}
