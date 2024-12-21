package com.onebank.taskmaster.notifier.consumer.model.sendgrid;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TemplateVersion {
    private String id;
    private String templateId;
    private Integer active;
    private String name;
    private String subject;
    private String updatedAt;
    private boolean generatePlainContent;
    private String htmlContent;
    private String planContent;
    private String editor;
    private String thumbnailUrl;
}
