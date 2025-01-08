package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.email.provider.mailchimp.MailChimpClient;
import com.onebank.taskmaster.templatemanager.email.provider.mailchimp.MailChimpClientRequestFactory;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateDto;
import com.onebank.taskmaster.templatemanager.service.storage.GetObjectContent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateOrUpdateMailChimpTemplateService extends CreateOrUpdateTemplate {
    private final MailChimpClient mailChimpClient;
    private final MailChimpClientRequestFactory clientRequestFactory;

    @Inject
    public CreateOrUpdateMailChimpTemplateService(GetObjectContent getObjectContent, MailChimpClient mailChimpClient, MailChimpClientRequestFactory clientRequestFactory) {
        super(getObjectContent);
        this.mailChimpClient = mailChimpClient;
        this.clientRequestFactory = clientRequestFactory;
    }

    @Override
    void execute(ActionTemplateDto actionTemplateDto) {
    }
}
