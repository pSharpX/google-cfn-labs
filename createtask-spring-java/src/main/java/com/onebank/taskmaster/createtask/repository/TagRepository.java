package com.onebank.taskmaster.createtask.repository;

import com.onebank.taskmaster.createtask.config.ConditionalOnDatabaseEnabled;
import com.onebank.taskmaster.createtask.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ConditionalOnDatabaseEnabled
public interface TagRepository extends JpaRepository<TagEntity, Long> {
    Optional<TagEntity> findByName(String name);
}
