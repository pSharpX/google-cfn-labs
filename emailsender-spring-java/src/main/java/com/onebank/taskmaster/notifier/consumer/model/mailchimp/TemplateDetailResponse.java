package com.onebank.taskmaster.notifier.consumer.model.mailchimp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TemplateDetailResponse {
    private String slug;
    private String name;
    private Set<String> labels = new HashSet<>();
    private String code;
    private String subject;
    private String fromEmail;
    private String fromName;
    private String text;
    private String publishName;
    private String publishCode;
    private String publishSubject;
    private String publishFromEmail;
    private String publishFromName;
    private String publishText;
    private String publishedAt;
    private String createdAt;
    private String updatedAt;
    @JsonProperty("is_broken_template")
    private boolean brokenTemplate;

}
