package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.GetTemplateInfoRequest;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.PublishTemplateRequest;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.TemplateDetailResponse;
import com.onebank.taskmaster.templatemanager.email.provider.mailchimp.MailChimpClient;
import com.onebank.taskmaster.templatemanager.email.provider.mailchimp.MailChimpClientRequestFactory;
import com.onebank.taskmaster.templatemanager.exception.ResourceNotFoundException;
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
        TemplateDetailResponse templateDetailResponse;
        try {
            templateDetailResponse = mailChimpClient.getTemplateInfo(new GetTemplateInfoRequest(actionTemplateDto.getName()));
            log.debug("Updating Mailchimp template [{}]", templateDetailResponse.getName());
            templateDetailResponse = mailChimpClient.updateTemplate(clientRequestFactory.addOrUpdateTemplate(templateDetailResponse.getName(), actionTemplateDto.getContent()));
        } catch (ResourceNotFoundException e) {
            log.debug("Mailchimp [{}] template was not found.", actionTemplateDto.getName());
            log.debug("Creating Mailchimp template [{}]", actionTemplateDto.getName());
            templateDetailResponse = mailChimpClient.addTemplate(clientRequestFactory.addOrUpdateTemplate(actionTemplateDto.getName(), actionTemplateDto.getContent()));
        }
        log.debug("Publishing Mailchimp template changes [{}]", templateDetailResponse.getName());
        templateDetailResponse = mailChimpClient.publishTemplate(new PublishTemplateRequest(templateDetailResponse.getName()));
        log.debug("Action completed successfully for [{}] template", templateDetailResponse.getName());
    }
}
