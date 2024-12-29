package com.onebank.taskmaster.sendnotification.email.provider.sendgrid;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.onebank.taskmaster.sendnotification.email.model.To;
import com.onebank.taskmaster.sendnotification.email.model.sendgrid.From;
import com.onebank.taskmaster.sendnotification.email.model.sendgrid.Personalization;
import com.onebank.taskmaster.sendnotification.email.model.sendgrid.SendMessageRequest;
import com.onebank.taskmaster.sendnotification.model.senders.EmailNotificationMessage;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Properties;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class SendGridClientRequestFactory {
    @Named("sendgridProperties")
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
