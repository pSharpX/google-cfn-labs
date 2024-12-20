package com.onebank.taskmaster.createtask.entity.listeners;

import com.onebank.taskmaster.createtask.entity.TaskEntity;
import jakarta.persistence.PrePersist;

public class TaskCreationListener {
    @PrePersist
    public void onPrePersist(final TaskEntity taskEntity) {
        taskEntity.setEnabled(true);
    }
}
