package com.onebank.taskmaster.createnotification.service;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.model.NotificationChannel;
import com.onebank.taskmaster.createnotification.model.NotificationTemplateDetails;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.notifier.config.NotificationTemplateConfig;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class NotificationTemplateService {
    private final Map<NotificationChannel, NotificationTemplateConfig> templateConfigMap;

    public NotificationTemplateDetails getTemplateDetails(TaskNotificationType notificationType) {
        return this.getTemplateDetails(NotificationChannel.EMAIL, notificationType);
    }

    public NotificationTemplateDetails getTemplateDetails(NotificationChannel channel, TaskNotificationType notificationType) {
        return Optional.ofNullable(this.templateConfigMap.get(channel))
                .map(NotificationTemplateConfig::getTemplates)
                .orElse(Collections.emptyList()).stream()
                .filter(templateDetails -> templateDetails.getNotificationType().equalsIgnoreCase(notificationType.toString()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Template not found: %s, channel: %s", notificationType, channel)));
    }
}
