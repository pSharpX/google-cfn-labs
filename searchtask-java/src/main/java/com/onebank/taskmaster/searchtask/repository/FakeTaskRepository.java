package com.onebank.taskmaster.searchtask.repository;

import com.google.inject.Inject;
import com.onebank.taskmaster.searchtask.entity.TagEntity;
import com.onebank.taskmaster.searchtask.entity.TaskEntity;
import com.onebank.taskmaster.searchtask.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class FakeTaskRepository implements TaskRepository {
    @Override
    public List<TaskEntity> findByTitle(String title) {
        TaskEntity dummyTask = new TaskEntity();
        dummyTask.setTitle("Complete the create task implementation");
        dummyTask.setDescription("Need to complete the implementation of create task service including Unit/Integration Test");
        dummyTask.setTags(Stream.of("development", "code", "sprint 2", "java").map(TagEntity::new).collect(Collectors.toSet()));
        dummyTask.setWeight(5);
        dummyTask.setProgressStatus(TaskStatus.IN_PROGRESS);
        return List.of(dummyTask);
    }
}
