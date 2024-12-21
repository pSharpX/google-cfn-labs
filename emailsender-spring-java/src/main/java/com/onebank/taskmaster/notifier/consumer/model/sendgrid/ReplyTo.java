package com.onebank.taskmaster.notifier.consumer.model.sendgrid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyTo {
    private String email;
    private String name;
}
