package com.onebank.taskmaster.sendnotification.email.service;

import com.google.inject.Inject;
import com.onebank.taskmaster.sendnotification.email.service.sender.EmailSender;
import com.onebank.taskmaster.sendnotification.model.senders.EmailNotificationMessage;
import com.onebank.taskmaster.sendnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.sendnotification.service.NotificationMessageConsumer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class EmailNotificationMessageConsumer implements NotificationMessageConsumer {
    private final EmailSender emailSender;

    @Override
    public void send(@NonNull NotificationMessage message) {
        log.debug("Sending message via sender-provider");
        emailSender.send((EmailNotificationMessage) message);
    }
}
