package com.onebank.taskmaster.templatemanager.email.model.sendgrid;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetTemplatesResponse {
    @JsonAlias({"result", "templates"})
    private List<SendGridTemplateDetailResponse> result;
}
