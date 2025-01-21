package com.onebank.taskmaster.createnotification.service.providers;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.model.NotificationTemplateDetails;
import com.onebank.taskmaster.createnotification.model.TaskCreatedNotificationRequest;
import com.onebank.taskmaster.createnotification.model.senders.EmailNotificationMessage;
import com.onebank.taskmaster.createnotification.model.senders.InAppNotificationMessage;
import com.onebank.taskmaster.createnotification.model.senders.PushNotificationMessage;
import com.onebank.taskmaster.createnotification.model.senders.SmsNotificationMessage;
import com.onebank.taskmaster.createnotification.service.NotificationTemplateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskCreatedContentProvider extends TaskMessageContentProvider<TaskCreatedNotificationRequest> {

    @Inject
    public TaskCreatedContentProvider(NotificationTemplateService templateService) {
        super(templateService);
    }

    @Override
    public EmailNotificationMessage getEmailMessageContent(TaskCreatedNotificationRequest request, NotificationTemplateDetails templateDetails) {
        EmailNotificationMessage notificationMessage = super.getEmailMessageContent(request, templateDetails);
        notificationMessage.getVars().put("task_title", request.getTaskTitle());
        notificationMessage.getVars().put("task_description", request.getTaskDescription());
        notificationMessage.getVars().put("task_due_date", request.getTaskDueDate());
        notificationMessage.getVars().put("task_priority", request.getTaskPriority());
        return notificationMessage;
    }

    @Override
    public PushNotificationMessage getPushMessageContent(TaskCreatedNotificationRequest request, NotificationTemplateDetails templateDetails) {
        return super.getPushMessageContent(request, templateDetails);
    }

    @Override
    public SmsNotificationMessage getSmsMessageContent(TaskCreatedNotificationRequest request, NotificationTemplateDetails templateDetails) {
        return super.getSmsMessageContent(request, templateDetails);
    }

    @Override
    public InAppNotificationMessage getInAppMessageContent(TaskCreatedNotificationRequest request, NotificationTemplateDetails templateDetails) {
        return super.getInAppMessageContent(request, templateDetails);
    }
}
