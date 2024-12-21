package com.onebank.taskmaster.notifier.consumer.email.service.sender;

import com.onebank.taskmaster.notifier.consumer.email.config.ConditionalOnMailjetEnabled;
import com.onebank.taskmaster.notifier.model.senders.EmailNotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnMailjetEnabled
@Slf4j
public class MailjetEmailSender implements EmailSender {
    @Override
    public void send(@NonNull EmailNotificationMessage notificationMessage) {
        log.debug("Sending [{}] notification with Mailjet Provider", notificationMessage.getChannel().getName());
    }
}
