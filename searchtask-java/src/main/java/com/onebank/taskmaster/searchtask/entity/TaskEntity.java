package com.onebank.taskmaster.searchtask.entity;

import com.onebank.taskmaster.searchtask.model.TaskCreationStatus;
import com.onebank.taskmaster.searchtask.model.TaskStatus;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TaskEntity {
    private Long id;
    private String title;
    private String description;
    private Set<TagEntity> tags = new HashSet<>();
    private Integer weight = 0;
    private TaskStatus progressStatus = TaskStatus.TODO;
    private TaskCreationStatus status = TaskCreationStatus.PENDING;
}
