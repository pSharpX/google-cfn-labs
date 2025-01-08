package com.onebank.taskmaster.templatemanager.email.model.sendgrid;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMessageRequest {
	private List<Personalization> personalizations;
	private From from;
	private ReplyTo replyTo;
	private List<ReplyTo> replyToList;
	private String subject;
	private List<Content> content;
	private List<Attachment> attachments;
	private String templateId;
	private Map<String, String> headers;
	private Set<String> categories;
	private String customArgs;
	private Long sendAt;
	private String batchId;
	private String ipPoolName;
}
