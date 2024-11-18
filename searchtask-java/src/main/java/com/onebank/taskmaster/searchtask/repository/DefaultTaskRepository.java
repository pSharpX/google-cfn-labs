package com.onebank.taskmaster.searchtask.repository;

import com.google.inject.Inject;
import com.onebank.taskmaster.searchtask.entity.TaskEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class DefaultTaskRepository implements TaskRepository {
    @Override
    public List<TaskEntity> findByTitle(String title) {
        return List.of();
    }
}
