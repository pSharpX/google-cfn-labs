package com.onebank.taskmaster.sendnotification.email.service.sender;

import com.onebank.taskmaster.sendnotification.model.senders.EmailNotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class MailgunEmailSender implements EmailSender {
    @Override
    public void send(@NonNull EmailNotificationMessage notificationMessage) {
        log.debug("Sending [{}] notification with Mailgun Provider", notificationMessage.getChannel().getName());
    }
}
