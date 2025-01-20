package com.onebank.taskmaster.createnotification.service;

import com.onebank.taskmaster.createnotification.model.TaskNotificationRequest;
import lombok.NonNull;

public interface CreateNotification {
    void create(@NonNull TaskNotificationRequest request);
}
