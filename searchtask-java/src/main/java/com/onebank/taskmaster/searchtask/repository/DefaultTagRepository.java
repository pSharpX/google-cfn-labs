package com.onebank.taskmaster.searchtask.repository;

import com.onebank.taskmaster.searchtask.entity.TagEntity;

import java.util.Optional;

public class DefaultTagRepository implements TagRepository {
    @Override
    public Optional<TagEntity> findByName(String name) {
        return Optional.empty();
    }
}
