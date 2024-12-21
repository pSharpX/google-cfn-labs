package com.onebank.taskmaster.notifier.consumer.email.service.sender;

import com.onebank.taskmaster.notifier.consumer.email.config.ConditionalOnSendGridEnabled;
import com.onebank.taskmaster.notifier.consumer.email.provider.sendgrid.SendGridClient;
import com.onebank.taskmaster.notifier.consumer.email.provider.sendgrid.SendGridClientRequestFactory;
import com.onebank.taskmaster.notifier.consumer.model.sendgrid.SendMessageRequest;
import com.onebank.taskmaster.notifier.model.senders.EmailNotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnSendGridEnabled
@Slf4j
public class SendGridEmailSender implements EmailSender {
    private final SendGridClientRequestFactory clientRequestFactory;
    private final SendGridClient client;

    @Override
    public void send(@NonNull EmailNotificationMessage notificationMessage) {
        log.debug("Sending [{}] notification with SendGrid Provider", notificationMessage.getChannel().getName());
        SendMessageRequest request = clientRequestFactory.build(notificationMessage);
        client.send(request);
        log.debug("Email sent");
    }
}
