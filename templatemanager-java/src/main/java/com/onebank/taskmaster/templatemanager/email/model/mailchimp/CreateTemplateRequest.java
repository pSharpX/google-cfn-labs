package com.onebank.taskmaster.templatemanager.email.model.mailchimp;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
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

    public CreateTemplateRequest(String name, String code, String fromEmail, String fromName) {
        this.name = name;
        this.code = code;
        this.fromEmail = fromEmail;
        this.fromName = fromName;
    }
}
