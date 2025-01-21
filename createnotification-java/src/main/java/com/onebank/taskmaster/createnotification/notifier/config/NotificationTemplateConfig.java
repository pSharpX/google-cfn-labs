package com.onebank.taskmaster.createnotification.notifier.config;


import com.onebank.taskmaster.createnotification.config.PropertiesPrefix;
import com.onebank.taskmaster.createnotification.model.NotificationTemplateDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@PropertiesPrefix("task-master.channel.email")
public class NotificationTemplateConfig {
	private List<NotificationTemplateDetails> templates = new ArrayList<>();
}
