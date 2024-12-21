package com.onebank.taskmaster.notifier.consumer.model.mailchimp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishTemplateRequest {
    private String key;
    private String name;
}
