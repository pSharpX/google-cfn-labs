package com.onebank.taskmaster.notifier.consumer.model.mailchimp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MergeVars {
	private String rcpt;
	private List<Var> vars;
}
