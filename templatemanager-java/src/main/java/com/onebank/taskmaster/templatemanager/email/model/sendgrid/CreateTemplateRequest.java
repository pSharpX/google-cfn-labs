package com.onebank.taskmaster.templatemanager.email.model.sendgrid;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateTemplateRequest {
    private String name;
    private String generation;

    public CreateTemplateRequest(String name, String generation) {
        this.name = name;
        this.generation = generation;
    }
}
