package com.onebank.taskmaster.notifier.consumer.email.provider.sendgrid;

import com.onebank.taskmaster.notifier.consumer.email.config.ConditionalOnSendGridEnabled;
import com.onebank.taskmaster.notifier.consumer.model.To;
import com.onebank.taskmaster.notifier.consumer.model.sendgrid.From;
import com.onebank.taskmaster.notifier.consumer.model.sendgrid.Personalization;
import com.onebank.taskmaster.notifier.consumer.model.sendgrid.SendMessageRequest;
import com.onebank.taskmaster.notifier.model.senders.EmailNotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
@ConditionalOnSendGridEnabled
public class SendGridClientRequestFactory {
    @Qualifier("sendgridProperties")
    private final Properties config;

    public SendMessageRequest build(EmailNotificationMessage notificationMessage) {
        SendMessageRequest messageRequest = new SendMessageRequest();

        messageRequest.setTemplateId(notificationMessage.getTemplateName());
        messageRequest.setFrom(new From(config.getProperty("from.email"), config.getProperty("from.name")));
        messageRequest.setPersonalizations(List.of(this.buildPersonalization(notificationMessage)));
        return messageRequest;
    }

    private Personalization buildPersonalization(EmailNotificationMessage notificationDto) {
        Personalization personalization = new Personalization();

        personalization.setTo(this.buildToList(notificationDto));
        personalization.setDynamicTemplateData(notificationDto.getVars());
        return personalization;
    }

    private List<To> buildToList(EmailNotificationMessage notificationDto) {
        return List.of(new To(notificationDto.getRecipientEmail(), notificationDto.getRecipientName()));
    }
}
