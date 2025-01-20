package com.onebank.taskmaster.createnotification.service;

import com.onebank.taskmaster.createnotification.model.NotificationTemplateDetails;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.notifier.config.NotificationTemplateConfig;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationTemplateService {
    private final NotificationTemplateConfig templateConfig;

    public NotificationTemplateDetails getTemplateDetails(TaskNotificationType notificationType) {
        return this.templateConfig.getTemplates().stream()
                .filter(templateDetails -> templateDetails.getNotificationType().equalsIgnoreCase(notificationType.toString()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Template not found: %s", notificationType)));
    }
}
