package com.onebank.taskmaster.createnotification.service;

import com.onebank.taskmaster.createnotification.model.TaskNotificationRequest;
import lombok.NonNull;

public interface NotificationCreator {
    void create(@NonNull TaskNotificationRequest request);
}
