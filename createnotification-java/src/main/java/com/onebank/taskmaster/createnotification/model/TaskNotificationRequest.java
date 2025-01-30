package com.onebank.taskmaster.createnotification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.onebank.taskmaster.createnotification.model.validators.NotificationChannelCode;
import com.onebank.taskmaster.createnotification.model.validators.TaskNotificationTypeCode;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TaskCreatedNotificationRequest.class, name = "TASK_CREATED"),
        @JsonSubTypes.Type(value = TaskDeletedNotificationRequest.class, names = "TASK_DELETED"),
        @JsonSubTypes.Type(value = TaskCompletedNotificationRequest.class, names = "TASK_COMPLETED"),
        @JsonSubTypes.Type(value = TaskCompletionReminderNotificationRequest.class, names = "TASK_COMPLETION_REMINDER")
})
@Getter
@Setter
public class TaskNotificationRequest {
    @JsonIgnore
    private Long id;
    @NotificationChannelCode
    @JsonIgnore
    private NotificationChannel channel;
    @NotNull
    @TaskNotificationTypeCode
    private String type;
    @NotEmpty
    private String user;
    @NotEmpty
    private String title;
    @NotEmpty
    private String message;
    @Setter(AccessLevel.NONE)
    private NotificationStatus status = NotificationStatus.CREATED;
}
