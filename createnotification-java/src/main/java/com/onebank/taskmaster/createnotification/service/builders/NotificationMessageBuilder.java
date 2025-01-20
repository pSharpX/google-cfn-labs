package com.onebank.taskmaster.createnotification.service.builders;

import com.onebank.taskmaster.createnotification.model.TaskNotificationRequest;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import lombok.NonNull;

public interface NotificationMessageBuilder<T extends TaskNotificationRequest> {
    NotificationMessage build(@NonNull T request);
}
