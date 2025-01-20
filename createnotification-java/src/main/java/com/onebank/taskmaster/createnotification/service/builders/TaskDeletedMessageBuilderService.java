package com.onebank.taskmaster.createnotification.service.builders;

import com.onebank.taskmaster.createnotification.model.TaskDeletedNotificationRequest;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.createnotification.service.ContentProviderResolver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("unchecked")
public class TaskDeletedMessageBuilderService implements NotificationMessageBuilder<TaskDeletedNotificationRequest> {
    private final ContentProviderResolver contentProviderResolver;

    @Override
    public NotificationMessage build(@NonNull TaskDeletedNotificationRequest request) {
        return contentProviderResolver.resolve(TaskNotificationType.TASK_DELETED).getContent(request);
    }
}
