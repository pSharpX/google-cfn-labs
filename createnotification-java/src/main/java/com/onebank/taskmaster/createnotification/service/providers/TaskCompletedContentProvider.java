package com.onebank.taskmaster.createnotification.service.providers;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.model.NotificationTemplateDetails;
import com.onebank.taskmaster.createnotification.model.TaskCompletedNotificationRequest;
import com.onebank.taskmaster.createnotification.model.senders.EmailNotificationMessage;
import com.onebank.taskmaster.createnotification.model.senders.InAppNotificationMessage;
import com.onebank.taskmaster.createnotification.model.senders.PushNotificationMessage;
import com.onebank.taskmaster.createnotification.model.senders.SmsNotificationMessage;
import com.onebank.taskmaster.createnotification.service.NotificationTemplateService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskCompletedContentProvider extends TaskMessageContentProvider<TaskCompletedNotificationRequest> {

    @Inject
    public TaskCompletedContentProvider(NotificationTemplateService templateService) {
        super(templateService);
    }

    @Override
    public EmailNotificationMessage getEmailMessageContent(TaskCompletedNotificationRequest request, NotificationTemplateDetails templateDetails) {
        return super.getEmailMessageContent(request, templateDetails);
    }

    @Override
    public PushNotificationMessage getPushMessageContent(TaskCompletedNotificationRequest request, NotificationTemplateDetails templateDetails) {
        return super.getPushMessageContent(request, templateDetails);
    }

    @Override
    public SmsNotificationMessage getSmsMessageContent(TaskCompletedNotificationRequest request, NotificationTemplateDetails templateDetails) {
        return super.getSmsMessageContent(request, templateDetails);
    }

    @Override
    public InAppNotificationMessage getInAppMessageContent(TaskCompletedNotificationRequest request, NotificationTemplateDetails templateDetails) {
        return super.getInAppMessageContent(request, templateDetails);
    }
}
