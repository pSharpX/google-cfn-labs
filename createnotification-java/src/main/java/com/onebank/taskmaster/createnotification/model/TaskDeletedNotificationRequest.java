package com.onebank.taskmaster.createnotification.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaskDeletedNotificationRequest extends TaskNotificationRequest {
    private String taskTitle;
}
