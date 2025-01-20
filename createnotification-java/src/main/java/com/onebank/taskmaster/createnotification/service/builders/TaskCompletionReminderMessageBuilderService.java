package com.onebank.taskmaster.createnotification.service.builders;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.model.TaskCompletionReminderNotificationRequest;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.createnotification.service.ContentProviderResolver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
@SuppressWarnings("unchecked")
public class TaskCompletionReminderMessageBuilderService implements NotificationMessageBuilder<TaskCompletionReminderNotificationRequest> {
    private final ContentProviderResolver contentProviderResolver;

    @Override
    public NotificationMessage build(@NonNull TaskCompletionReminderNotificationRequest request) {
        return contentProviderResolver.resolve(TaskNotificationType.TASK_COMPLETION_REMINDER).getContent(request);
    }
}
