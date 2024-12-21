package com.onebank.taskmaster.notifier.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum TaskNotificationType {
    TASK_CREATED("TASK_CREATED", "Notification trigger when new task is created"),
    TASK_DELETED("TASK_DELETED", "Notification trigger when task is deleted"),
    TASK_COMPLETED("TASK_COMPLETED", "Notification trigger when task is marked as done"),
    TASK_COMPLETION_REMINDER("TASK_COMPLETION_REMINDER", "Notification trigger as reminder for task completion"),
    TASK_REMINDER_CONFIGURED("TASK_REMINDER_CONFIGURED", "Notification trigger when task reminder is set up");

    private final String name;
    private final String description;

    public static TaskNotificationType getByName(@NonNull String name) {
        return Stream.of(values()).filter(taskNotificationType -> taskNotificationType.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static boolean isValid(String name) {
        return Stream.of(values()).anyMatch(taskNotificationType -> taskNotificationType.getName().equals(name));
    }
}
