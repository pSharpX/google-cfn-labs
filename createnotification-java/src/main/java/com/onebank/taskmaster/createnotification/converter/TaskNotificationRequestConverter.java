package com.onebank.taskmaster.createnotification.converter;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.entity.NotificationEntity;
import com.onebank.taskmaster.createnotification.model.TaskNotificationRequest;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class TaskNotificationRequestConverter implements ConvertTo<TaskNotificationRequest, NotificationEntity> {
    @Override
    public NotificationEntity convert(@NonNull TaskNotificationRequest input) {
        return NotificationEntity.builder()
                .userIdentifier(input.getUser())
                .title(input.getTitle())
                .message(input.getMessage())
                .notificationType(TaskNotificationType.getByName(input.getType()))
                .channel(input.getChannel())
                .status(input.getStatus())
                .build();
    }
}
