package com.onebank.taskmaster.notifier.consumer.model.mailchimp;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateTemplateRequest {
    private String key;
    private String name;
    private String fromEmail;
    private String fromName;
    private String subject;
    private String code;
    private String text;
    private boolean publish;
    private Set<String> labels = new HashSet<>();
}
