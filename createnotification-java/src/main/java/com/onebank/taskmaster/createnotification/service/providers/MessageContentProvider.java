package com.onebank.taskmaster.createnotification.service.providers;

import com.onebank.taskmaster.createnotification.model.TaskNotificationRequest;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import lombok.NonNull;

public interface MessageContentProvider<T extends TaskNotificationRequest> {
    NotificationMessage getContent(@NonNull T request);
}
