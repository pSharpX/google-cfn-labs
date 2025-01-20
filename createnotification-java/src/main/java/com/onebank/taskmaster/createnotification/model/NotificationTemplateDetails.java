package com.onebank.taskmaster.createnotification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTemplateDetails {
	private String notificationType;
	private String templateName;
	private Map<String, String> placeholder;
}
