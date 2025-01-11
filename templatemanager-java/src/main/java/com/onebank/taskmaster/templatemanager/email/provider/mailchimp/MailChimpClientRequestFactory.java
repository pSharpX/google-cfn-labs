package com.onebank.taskmaster.templatemanager.email.provider.mailchimp;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.CreateTemplateRequest;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class MailChimpClientRequestFactory {
    @Named("mailChimpProperties")
    private final Properties config;

    public CreateTemplateRequest addOrUpdateTemplate(String name, String content) {
        return new CreateTemplateRequest(name, content, config.getProperty("from.email"), config.getProperty("from.name"));
    }
}
