package com.onebank.taskmaster.notifier.consumer.model.mailchimp;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMessageRequest {
	private String key;
	private Message message;
	private boolean async;
	private String ipPool;
	private String sendAt;
}
