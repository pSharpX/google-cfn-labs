package com.onebank.taskmaster.createtask.converter;

import com.onebank.taskmaster.createtask.entity.TaskEntity;
import com.onebank.taskmaster.createtask.model.CreateTaskRequest;
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
                .status(input.getStatus())
                //.dueDate(input.getDueDate())
                .build();
    }
}
