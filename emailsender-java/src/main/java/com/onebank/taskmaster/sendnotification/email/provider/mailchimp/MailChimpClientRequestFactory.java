package com.onebank.taskmaster.sendnotification.email.provider.mailchimp;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.onebank.taskmaster.sendnotification.email.model.To;
import com.onebank.taskmaster.sendnotification.email.model.mailchimp.GlobalMergeVar;
import com.onebank.taskmaster.sendnotification.email.model.mailchimp.Message;
import com.onebank.taskmaster.sendnotification.email.model.mailchimp.SendMessageWithTemplateRequest;
import com.onebank.taskmaster.sendnotification.model.senders.EmailNotificationMessage;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Properties;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class MailChimpClientRequestFactory {
    @Named("mailChimpProperties")
    private final Properties config;

    public SendMessageWithTemplateRequest build(EmailNotificationMessage notificationMessage) {
        SendMessageWithTemplateRequest mailChimpRequest = new SendMessageWithTemplateRequest();
        mailChimpRequest.setTemplateName(notificationMessage.getTemplateName());

        Message message = this.buildMessage(notificationMessage);

        mailChimpRequest.setMessage(message);
        return mailChimpRequest;
    }

    private Message buildMessage(EmailNotificationMessage notificationDto) {
        List<To> toList = this.buildToList(notificationDto);

        Message message = new Message();
        message.setFromEmail(config.getProperty("from.email"));
        message.setFromName(config.getProperty("from.name"));
        message.setTo(toList);
        message.setMergeLanguage(config.getProperty("config.mergeLanguage"));
        message.setGlobalMergeVars(this.buildGlobalMergeVars(notificationDto));
        return message;
    }

    private List<GlobalMergeVar> buildGlobalMergeVars(EmailNotificationMessage notificationDto) {
        return notificationDto.getVars().entrySet().stream()
                .map(entry -> new GlobalMergeVar(entry.getKey(), entry.getValue()))
                .toList();
    }

    private List<To> buildToList(EmailNotificationMessage notificationDto) {
        return List.of(new To(notificationDto.getRecipientEmail(), notificationDto.getRecipientName()));
    }
}
