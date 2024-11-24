package com.onebank.taskmaster.searchtask.repository;

import com.onebank.taskmaster.searchtask.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class FakeTagRepository implements TagRepository {
    @Override
    public Optional<TagEntity> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<TagEntity> findByTaskId(Long taskId) {
        return List.of();
    }
}
