package com.onebank.taskmaster.templatemanager.email.model.mailchimp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailChimpErrorResponse {
    private String status;
    private Integer code;
    private String name;
    private String message;
}
