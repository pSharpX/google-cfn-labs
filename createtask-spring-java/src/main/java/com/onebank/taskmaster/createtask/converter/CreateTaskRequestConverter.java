package com.onebank.taskmaster.createtask.converter;

import com.onebank.taskmaster.createtask.entity.TaskEntity;
import com.onebank.taskmaster.createtask.model.CreateTaskRequest;
import com.onebank.taskmaster.createtask.model.TaskStatus;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CreateTaskRequestConverter implements ConvertTo<CreateTaskRequest, TaskEntity> {
    @Override
    public TaskEntity convert(@NonNull CreateTaskRequest input) {
        return TaskEntity.builder()
                .title(input.getTaskTitle())
                .description(input.getTaskDescription())
                .weight(input.getWeight())
                .progressStatus(TaskStatus.TODO)
                .status(input.getStatus())
                .build();
    }
}
