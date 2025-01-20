package com.onebank.taskmaster.createnotification.service.producers;

import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import lombok.NonNull;

public interface NotificationMessageProducer extends MessageProducer<NotificationMessage> {
    void sendMessage(@NonNull NotificationMessage notification);
}
