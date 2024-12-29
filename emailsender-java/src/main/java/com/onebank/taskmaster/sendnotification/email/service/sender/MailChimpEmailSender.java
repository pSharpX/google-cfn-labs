package com.onebank.taskmaster.sendnotification.email.service.sender;

import com.google.inject.Inject;
import com.onebank.taskmaster.sendnotification.email.model.mailchimp.SendMessageResponse;
import com.onebank.taskmaster.sendnotification.email.model.mailchimp.SendMessageWithTemplateRequest;
import com.onebank.taskmaster.sendnotification.email.provider.mailchimp.MailChimpClient;
import com.onebank.taskmaster.sendnotification.email.provider.mailchimp.MailChimpClientRequestFactory;
import com.onebank.taskmaster.sendnotification.model.senders.EmailNotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class MailChimpEmailSender implements EmailSender {
    private final MailChimpClientRequestFactory clientRequestFactory;
    private final MailChimpClient client;

    @Override
    public void send(@NonNull EmailNotificationMessage notificationMessage) {
        log.debug("Sending [{}] notification with MailChimp Provider", notificationMessage.getChannel().getName());
        SendMessageWithTemplateRequest request = clientRequestFactory.build(notificationMessage);
        List<SendMessageResponse> responses = client.sendWithTemplate(request);
        log.debug(responses.toString());
    }
}
