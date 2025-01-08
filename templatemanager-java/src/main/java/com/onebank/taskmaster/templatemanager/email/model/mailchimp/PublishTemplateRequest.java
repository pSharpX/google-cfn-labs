package com.onebank.taskmaster.templatemanager.email.model.mailchimp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishTemplateRequest {
    private String key;
    private String name;
}
