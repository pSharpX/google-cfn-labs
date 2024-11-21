package com.onebank.taskmaster.searchtask.converter;

import com.onebank.taskmaster.searchtask.entity.TagEntity;
import com.onebank.taskmaster.searchtask.entity.TaskEntity;
import com.onebank.taskmaster.searchtask.model.TaskDetails;
import lombok.NonNull;

public class TaskDetailsConverterService implements TaskDetailsConverter {
    @Override
    public TaskDetails convert(@NonNull TaskEntity input) {
        return TaskDetails.builder()
                .taskTitle(input.getTitle())
                .taskDescription(input.getDescription())
                .weight(input.getWeight())
                .tags(input.getTags().stream().map(TagEntity::getName).toList())
                .status(input.getProgressStatus())
                .build();
    }
}
