package com.onebank.taskmaster.templatemanager.email.model.mailchimp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attachment {
	private String type;
	private String name;
	private String content;
}
