package com.onebank.taskmaster.searchtask.repository;

import com.onebank.taskmaster.searchtask.entity.TagEntity;

import java.util.Optional;

public interface TagRepository {
    Optional<TagEntity> findByName(String name);
}
