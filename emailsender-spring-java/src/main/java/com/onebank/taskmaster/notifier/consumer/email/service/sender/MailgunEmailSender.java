package com.onebank.taskmaster.notifier.consumer.email.service.sender;

import com.onebank.taskmaster.notifier.consumer.email.config.ConditionalOnMailgunEnabled;
import com.onebank.taskmaster.notifier.model.senders.EmailNotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnMailgunEnabled
@Slf4j
public class MailgunEmailSender implements EmailSender {
    @Override
    public void send(@NonNull EmailNotificationMessage notificationMessage) {
        log.debug("Sending [{}] notification with Mailgun Provider", notificationMessage.getChannel().getName());
    }
}
