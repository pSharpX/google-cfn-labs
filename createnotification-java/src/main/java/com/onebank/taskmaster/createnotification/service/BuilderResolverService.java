package com.onebank.taskmaster.createnotification.service;


import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.service.builders.NotificationMessageBuilder;
import com.onebank.taskmaster.createnotification.service.builders.TaskCompletedMessageBuilderService;
import com.onebank.taskmaster.createnotification.service.builders.TaskCompletionReminderMessageBuilderService;
import com.onebank.taskmaster.createnotification.service.builders.TaskCreatedMessageBuilderService;
import com.onebank.taskmaster.createnotification.service.builders.TaskDeletedMessageBuilderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
@SuppressWarnings("rawtypes")
public class BuilderResolverService implements BuilderResolver {
    private final TaskCreatedMessageBuilderService taskCreatedMessageBuilderService;
    private final TaskDeletedMessageBuilderService taskDeletedMessageBuilderService;
    private final TaskCompletedMessageBuilderService taskCompletedMessageBuilderService;
    private final TaskCompletionReminderMessageBuilderService taskCompletionReminderMessageBuilderService;

    @Override
    public NotificationMessageBuilder resolve(@NonNull TaskNotificationType notificationType) {
        return switch (notificationType) {
            case TASK_CREATED -> taskCreatedMessageBuilderService;
            case TASK_DELETED -> taskDeletedMessageBuilderService;
            case TASK_COMPLETED -> taskCompletedMessageBuilderService;
            case TASK_COMPLETION_REMINDER -> taskCompletionReminderMessageBuilderService;
            case TASK_REMINDER_CONFIGURED -> throw new UnsupportedOperationException();
        };
    }
}
