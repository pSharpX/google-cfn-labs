package com.onebank.taskmaster.createnotification.service;

import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.service.builders.NotificationMessageBuilder;
import lombok.NonNull;

@SuppressWarnings("rawtypes")
public interface BuilderResolver {
    NotificationMessageBuilder resolve(@NonNull TaskNotificationType notificationType);
}
