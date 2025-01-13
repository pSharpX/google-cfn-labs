package com.onebank.taskmaster.templatemanager.email.model.sendgrid;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateTemplateVersionRequest {
    private Integer active;
    private String name;
    private String htmlContent;
    private String plainContent;
    private boolean generatePlainContent;
    private String subject;
    private String editor;
    private String testData;

    public CreateTemplateVersionRequest(Integer active, String name, String htmlContent, String subject) {
        this.active = active;
        this.name =name;
        this.htmlContent = htmlContent;
        this.subject = subject;
    }
}
