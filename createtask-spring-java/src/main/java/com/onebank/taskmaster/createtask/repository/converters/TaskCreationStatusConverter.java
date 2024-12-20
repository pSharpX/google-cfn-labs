package com.onebank.taskmaster.createtask.repository.converters;

import com.onebank.taskmaster.createtask.model.TaskCreationStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class TaskCreationStatusConverter implements AttributeConverter<TaskCreationStatus, String> {
    @Override
    public String convertToDatabaseColumn(TaskCreationStatus taskCreationStatus) {
        Objects.requireNonNull(taskCreationStatus, "taskCreationStatus property must be not null");
        return taskCreationStatus.getName();
    }

    @Override
    public TaskCreationStatus convertToEntityAttribute(String columnValue) {
        return TaskCreationStatus.getByName(columnValue);
    }
}
