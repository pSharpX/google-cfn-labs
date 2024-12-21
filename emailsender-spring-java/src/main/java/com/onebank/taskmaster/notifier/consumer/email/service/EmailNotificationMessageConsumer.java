package com.onebank.taskmaster.notifier.consumer.email.service;

import com.onebank.taskmaster.notifier.consumer.email.service.sender.EmailSender;
import com.onebank.taskmaster.notifier.consumer.service.NotificationMessageConsumer;
import com.onebank.taskmaster.notifier.model.senders.EmailNotificationMessage;
import com.onebank.taskmaster.notifier.model.senders.NotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationMessageConsumer implements NotificationMessageConsumer {
    private final EmailSender emailSender;

    @Override
    public void send(@NonNull NotificationMessage message) {
        log.debug("Sending message via sender-provider");
        emailSender.send((EmailNotificationMessage)message);
    }
}
