package com.onebank.taskmaster.searchtask.entity;

import com.onebank.taskmaster.searchtask.model.TaskCreationStatus;
import lombok.Data;

import java.util.List;

@Data
public class TaskEntity {
    private Long id;
    private String title;
    private String description;
    private List<String> tags;
    private Integer weight = 0;
    private TaskCreationStatus status = TaskCreationStatus.PENDING;
}
