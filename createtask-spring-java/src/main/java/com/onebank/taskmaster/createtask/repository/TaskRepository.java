package com.onebank.taskmaster.createtask.repository;

import com.onebank.taskmaster.createtask.config.ConditionalOnDatabaseEnabled;
import com.onebank.taskmaster.createtask.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnDatabaseEnabled
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByTitle(String title);
}
