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
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@ConditionalOnDatabaseEnabled
@Import(DataSourceAutoConfiguration.class)
@EnableJpaAuditing
public class ControlPlaneConfig {

    @Bean
    @Primary
    public CreateTask createPersistentTaskService(TaskRepository taskRepository, TagRepository tagRepository, CreateTaskRequestConverter converter) {
        return new CreatePersistentTaskService(taskRepository, tagRepository, converter);
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("current-user"); // Replace with actual logic
    }
}
