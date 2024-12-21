package com.onebank.taskmaster.notifier.consumer.model.sendgrid;

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
}
