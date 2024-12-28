package com.onebank.taskmaster.sendnotification.email.model.mailchimp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalMergeVar {
	private String name;
	private String content;
}
