package com.onebank.taskmaster.notifier.consumer.model.mailchimp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMessageResponse {
	private String email;
	private String status;
	@JsonProperty("_id")
	private String id;
	private String rejectReason;
	private String queuedReason;
}
