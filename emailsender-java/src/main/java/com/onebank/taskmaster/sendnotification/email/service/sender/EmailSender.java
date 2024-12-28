package com.onebank.taskmaster.sendnotification.email.service.sender;

import com.onebank.taskmaster.sendnotification.model.senders.EmailNotificationMessage;
import lombok.NonNull;

public interface EmailSender {
    void send(@NonNull EmailNotificationMessage notificationMessage);
}
