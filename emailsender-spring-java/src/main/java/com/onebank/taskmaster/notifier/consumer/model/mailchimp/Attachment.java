package com.onebank.taskmaster.notifier.consumer.model.mailchimp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attachment {
	private String type;
	private String name;
	private String content;
}
