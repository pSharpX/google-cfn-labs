package com.onebank.taskmaster.searchtask.repository;

import com.google.inject.Inject;
import com.onebank.taskmaster.searchtask.entity.TagEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class DefaultTagRepository implements TagRepository {
    @Override
    public Optional<TagEntity> findByName(String name) {
        return Optional.empty();
    }
}
