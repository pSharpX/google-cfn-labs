package com.onebank.taskmaster.notifier.consumer.model.mailchimp;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TemplateResponse {
	private String slug;
	private String name;
	private String code;
	private String publishCode;
	private String publishedAt;
	private String createdAt;
	private String updatedAt;
	private String draftUpdatedAt;
	private String publishName;
	private List<String> labels;
	private String text;
	private String publishText;
	private String subject;
	private String publishSubject;
	private String fromEmail;
	private String publishFromEmail;
	private String fromName;
	private String publishFromName;
}
