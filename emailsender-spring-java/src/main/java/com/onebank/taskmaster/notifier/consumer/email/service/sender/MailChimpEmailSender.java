package com.onebank.taskmaster.notifier.consumer.email.service.sender;

import com.onebank.taskmaster.notifier.consumer.email.config.ConditionalOnMailChimpEnabled;
import com.onebank.taskmaster.notifier.consumer.email.provider.mailchimp.MailChimpClient;
import com.onebank.taskmaster.notifier.consumer.email.provider.mailchimp.MailChimpClientRequestFactory;
import com.onebank.taskmaster.notifier.consumer.model.mailchimp.SendMessageResponse;
import com.onebank.taskmaster.notifier.consumer.model.mailchimp.SendMessageWithTemplateRequest;
import com.onebank.taskmaster.notifier.model.senders.EmailNotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@ConditionalOnMailChimpEnabled
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
