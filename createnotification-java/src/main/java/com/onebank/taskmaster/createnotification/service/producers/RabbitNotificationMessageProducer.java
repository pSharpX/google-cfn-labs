package com.onebank.taskmaster.createnotification.service.producers;

import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RabbitNotificationMessageProducer implements NotificationMessageProducer {
    @Override
    public void sendMessage(@NonNull NotificationMessage notification) {
        throw new UnsupportedOperationException();
    }
}
