package com.onebank.taskmaster.createnotification.notifier.config;

import com.onebank.taskmaster.createnotification.model.NotificationTemplateDetails;

import java.util.List;

public interface NotificationTemplateConfig {
	List<NotificationTemplateDetails> getTemplates();
}
