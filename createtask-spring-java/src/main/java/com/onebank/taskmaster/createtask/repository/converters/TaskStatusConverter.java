package com.onebank.taskmaster.createtask.repository.converters;

import com.onebank.taskmaster.createtask.model.TaskStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class TaskStatusConverter implements AttributeConverter<TaskStatus, String> {
    @Override
    public String convertToDatabaseColumn(TaskStatus taskStatus) {
        if (Objects.isNull(taskStatus)) {
            return null;
        }
        return taskStatus.getName();
    }

    @Override
    public TaskStatus convertToEntityAttribute(String columnValue) {
        return TaskStatus.getByName(columnValue);
    }
}
