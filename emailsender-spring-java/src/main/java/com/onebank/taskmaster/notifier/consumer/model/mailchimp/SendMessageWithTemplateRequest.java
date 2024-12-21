package com.onebank.taskmaster.notifier.consumer.model.mailchimp;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMessageWithTemplateRequest extends SendMessageRequest {
	private String templateName;
	private List<TemplateContent> templateContent = new ArrayList<>();
}
