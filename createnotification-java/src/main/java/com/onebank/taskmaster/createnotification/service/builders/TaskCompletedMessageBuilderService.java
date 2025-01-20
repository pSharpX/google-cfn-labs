package com.onebank.taskmaster.createnotification.service.builders;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.model.TaskCompletedNotificationRequest;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.createnotification.service.ContentProviderResolver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
@SuppressWarnings("unchecked")
public class TaskCompletedMessageBuilderService implements NotificationMessageBuilder<TaskCompletedNotificationRequest> {
    private final ContentProviderResolver contentProviderResolver;

    @Override
    public NotificationMessage build(@NonNull TaskCompletedNotificationRequest request) {
        return contentProviderResolver.resolve(TaskNotificationType.TASK_COMPLETED).getContent(request);
    }
}
