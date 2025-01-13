package com.onebank.taskmaster.templatemanager.email.model.sendgrid;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SendGridTemplateDetailResponse {
    private String id;
    private String name;
    private String generation;
    private String updatedAt;
    private List<TemplateVersion> versions;
}
