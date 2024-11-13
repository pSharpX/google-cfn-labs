package com.onebank.taskmaster.searchtask.repository;

import com.onebank.taskmaster.searchtask.entity.TaskEntity;

import java.util.List;

public interface TaskRepository {
    List<TaskEntity> findByTitle(String title);
}
