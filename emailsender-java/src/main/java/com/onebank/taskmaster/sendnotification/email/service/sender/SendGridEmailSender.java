package com.onebank.taskmaster.sendnotification.email.service.sender;

import com.google.inject.Inject;
import com.onebank.taskmaster.sendnotification.email.model.sendgrid.SendMessageRequest;
import com.onebank.taskmaster.sendnotification.email.provider.sendgrid.SendGridClient;
import com.onebank.taskmaster.sendnotification.email.provider.sendgrid.SendGridClientRequestFactory;
import com.onebank.taskmaster.sendnotification.model.senders.EmailNotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
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
