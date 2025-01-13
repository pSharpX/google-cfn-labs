package com.onebank.taskmaster.templatemanager.email.provider.sendgrid;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.CreateTemplateRequest;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.CreateTemplateVersionRequest;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class SendGridClientRequestFactory {
    @Named("sendgridProperties")
    private final Properties config;

    public String getDefaultTemplateGeneration() {
        return config.getProperty("templateGeneration");
    }

    public CreateTemplateRequest addOrUpdateTemplate(String name) {
        return new CreateTemplateRequest(name, config.getProperty("templateGeneration"));
    }

    public CreateTemplateVersionRequest addOrUpdateTemplateVersion(Integer active, String name, String content) {
        return new CreateTemplateVersionRequest(active, name, content, config.getProperty("defaultSubject"));
    }
}
