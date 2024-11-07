package com.onebank.taskmaster.createtask.config;

import com.onebank.taskmaster.createtask.converter.CreateTaskRequestConverter;
import com.onebank.taskmaster.createtask.repository.TagRepository;
import com.onebank.taskmaster.createtask.repository.TaskRepository;
import com.onebank.taskmaster.createtask.service.CreatePersistentTaskService;
import com.onebank.taskmaster.createtask.service.CreateTask;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnDatabaseEnabled
@Import(DataSourceAutoConfiguration.class)
public class ControlPlaneConfig {
    @Bean
    @Primary
    public CreateTask createPersistentTaskService(TaskRepository taskRepository, TagRepository tagRepository, CreateTaskRequestConverter converter) {
        return new CreatePersistentTaskService(taskRepository, tagRepository, converter);
    }
}
