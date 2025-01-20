package com.onebank.taskmaster.createnotification.service.providers;

import com.onebank.taskmaster.createnotification.model.NotificationChannel;
import com.onebank.taskmaster.createnotification.model.NotificationTemplateDetails;
import com.onebank.taskmaster.createnotification.model.TaskNotificationRequest;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.model.senders.EmailNotificationMessage;
import com.onebank.taskmaster.createnotification.model.senders.InAppNotificationMessage;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.createnotification.model.senders.PushNotificationMessage;
import com.onebank.taskmaster.createnotification.model.senders.SmsNotificationMessage;
import com.onebank.taskmaster.createnotification.service.NotificationTemplateService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public abstract class TaskMessageContentProvider<T extends TaskNotificationRequest> implements MessageContentProvider<T> {
    private final NotificationTemplateService templateService;

    @Override
    public NotificationMessage getContent(@NonNull T request) {
        NotificationTemplateDetails templateDetails = templateService.getTemplateDetails(TaskNotificationType.getByName(request.getType()));
        return switch (NotificationChannel.getByName(request.getChannel())) {
            case EMAIL -> getEmailMessageContent(request, templateDetails);
            case SMS -> getSmsMessageContent(request, templateDetails);
            case PUSH -> getPushMessageContent(request, templateDetails);
            case IN_APP -> getInAppMessageContent(request, templateDetails);
        };
    }

    public EmailNotificationMessage getEmailMessageContent(T request, NotificationTemplateDetails templateDetails) {
        EmailNotificationMessage notificationMessage = new EmailNotificationMessage();
        notificationMessage.setId(request.getId());
        notificationMessage.setType(TaskNotificationType.getByName(request.getType()));
        notificationMessage.setUser(request.getUser());
        notificationMessage.setRecipientEmail(request.getUser());
        notificationMessage.setRecipientName("Christian Rivera");
        notificationMessage.setTitle(request.getTitle());
        notificationMessage.setMessage(request.getMessage());
        notificationMessage.setTemplateName(templateDetails.getTemplateName());

        Map<String, String> vars = new HashMap<>();
        for (Map.Entry<String, String> entry : templateDetails.getPlaceholder().entrySet()) {
            String keyUpper = entry.getKey().toUpperCase();
            String valueUpper = entry.getValue().toLowerCase();
            switch (keyUpper) {
                case "APPLICATION_NAME" -> vars.put(valueUpper, "TaskMaster");
                case "CURRENT_YEAR" -> vars.put(valueUpper, "2024");
                case "USERNAME" -> vars.put(valueUpper, notificationMessage.getRecipientName());
            }
        }
        notificationMessage.setVars(vars);
        return notificationMessage;
    }

    public PushNotificationMessage getPushMessageContent(T request, NotificationTemplateDetails templateDetails) {
        throw new UnsupportedOperationException();
    }

    public SmsNotificationMessage getSmsMessageContent(T request, NotificationTemplateDetails templateDetails) {
        throw new UnsupportedOperationException();
    }

    public InAppNotificationMessage getInAppMessageContent(T request, NotificationTemplateDetails templateDetails) {
        throw new UnsupportedOperationException();
    }
}
