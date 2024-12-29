package com.onebank.taskmaster.sendnotification.email.service.sender;

import com.google.inject.Inject;
import com.onebank.taskmaster.sendnotification.model.senders.EmailNotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class MailjetEmailSender implements EmailSender {
    @Override
    public void send(@NonNull EmailNotificationMessage notificationMessage) {
        log.debug("Sending [{}] notification with Mailjet Provider", notificationMessage.getChannel().getName());
    }
}
