package com.onebank.taskmaster.templatemanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActionTemplateDto {
    private ActionTemplateType type;
    private String name;
    private String bucket;
    private String content;
    private String contentType;
    private Long size;
}
