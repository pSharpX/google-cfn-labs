package com.onebank.taskmaster.createnotification.service.builders;

import com.onebank.taskmaster.createnotification.model.TaskCreatedNotificationRequest;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.createnotification.service.ContentProviderResolver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unchecked")
public class TaskCreatedMessageBuilderService implements NotificationMessageBuilder<TaskCreatedNotificationRequest> {
    private final ContentProviderResolver contentProviderResolver;

    @Override
    public NotificationMessage build(@NonNull TaskCreatedNotificationRequest request) {
        return contentProviderResolver.resolve(TaskNotificationType.TASK_CREATED).getContent(request);
    }
}
