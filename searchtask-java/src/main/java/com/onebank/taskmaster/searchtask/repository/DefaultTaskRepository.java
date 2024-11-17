package com.onebank.taskmaster.searchtask.repository;

import com.onebank.taskmaster.searchtask.entity.TaskEntity;

import java.util.List;

public class DefaultTaskRepository implements TaskRepository {
    @Override
    public List<TaskEntity> findByTitle(String title) {
        return List.of();
    }
}
