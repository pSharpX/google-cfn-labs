package com.onebank.taskmaster.createnotification.service;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.service.providers.MessageContentProvider;
import com.onebank.taskmaster.createnotification.service.providers.TaskCompletedContentProvider;
import com.onebank.taskmaster.createnotification.service.providers.TaskCompletionReminderContentProvider;
import com.onebank.taskmaster.createnotification.service.providers.TaskCreatedContentProvider;
import com.onebank.taskmaster.createnotification.service.providers.TaskDeletedContentProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
@SuppressWarnings("rawtypes")
public class ContentProviderResolverService implements ContentProviderResolver {
    private final TaskCompletedContentProvider taskCompletedContentProvider;
    private final TaskDeletedContentProvider taskDeletedContentProvider;
    private final TaskCreatedContentProvider taskCreatedContentProvider;
    private final TaskCompletionReminderContentProvider taskCompletionReminderContentProvider;

    @Override
    public MessageContentProvider resolve(@NonNull TaskNotificationType type) {
        return switch (type) {
            case TASK_COMPLETED -> taskCompletedContentProvider;
            case TASK_DELETED -> taskDeletedContentProvider;
            case TASK_CREATED -> taskCreatedContentProvider;
            case TASK_COMPLETION_REMINDER -> taskCompletionReminderContentProvider;
            case TASK_REMINDER_CONFIGURED -> throw new UnsupportedOperationException();
        };
    }
}
