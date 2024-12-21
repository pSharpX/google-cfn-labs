package com.onebank.taskmaster.notifier.consumer.email.service.sender;

import com.onebank.taskmaster.notifier.model.senders.EmailNotificationMessage;
import lombok.NonNull;

public interface EmailSender {
    void send(@NonNull EmailNotificationMessage notificationMessage);
}
