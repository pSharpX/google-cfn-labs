package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.CreateTemplateRequest;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.SendGridTemplateDetailResponse;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.GetTemplatesResponse;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.TemplateVersion;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.TemplateVersionResponse;
import com.onebank.taskmaster.templatemanager.email.provider.sendgrid.SendGridClient;
import com.onebank.taskmaster.templatemanager.email.provider.sendgrid.SendGridClientRequestFactory;
import com.onebank.taskmaster.templatemanager.exception.ResourceNotFoundException;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateDto;
import com.onebank.taskmaster.templatemanager.service.storage.GetObjectContent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateOrUpdateSendGridTemplateService extends CreateOrUpdateTemplate {
    private final SendGridClient sendGridClient;
    private final SendGridClientRequestFactory clientRequestFactory;

    @Inject
    public CreateOrUpdateSendGridTemplateService(GetObjectContent getObjectContent, SendGridClient sendGridClient, SendGridClientRequestFactory clientRequestFactory) {
        super(getObjectContent);
        this.sendGridClient = sendGridClient;
        this.clientRequestFactory = clientRequestFactory;
    }

    @Override
    public void execute(ActionTemplateDto actionTemplateDto) {
        SendGridTemplateDetailResponse templateResponse = retrieveSingleTemplate(actionTemplateDto);
        TemplateVersionResponse templateVersionResponse = retrieveSingleTemplateVersion(templateResponse, actionTemplateDto);

        log.debug("Activating SendGrid template version changes [{}:{}]", templateResponse.getId(), templateVersionResponse.getId());
        templateVersionResponse = sendGridClient.activateTemplateVersion(templateResponse.getId(), templateVersionResponse.getId());
        log.debug("Action completed successfully for [templateId={},templateVersionId={}] template", templateVersionResponse.getTemplateId(), templateVersionResponse.getId());
    }

    private SendGridTemplateDetailResponse retrieveSingleTemplate(ActionTemplateDto actionTemplateDto) {
        try {
            log.debug("Searching for SendGrid [{}] template.", actionTemplateDto.getName());
            GetTemplatesResponse templatesResponse = sendGridClient.retrieveTemplates(clientRequestFactory.getDefaultTemplateGeneration());
            return templatesResponse.getResult().stream()
                    .filter(template -> template.getName().equalsIgnoreCase(actionTemplateDto.getName()))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND", "No template was found"));
        } catch (ResourceNotFoundException e) {
            log.debug("SendGrid [{}] template was not found.", actionTemplateDto.getName());
            log.debug("Creating SendGrid template [{}]", actionTemplateDto.getName());
            return sendGridClient.createTemplate(new CreateTemplateRequest(actionTemplateDto.getName(), "dynamic"));
        }
    }

    private TemplateVersionResponse retrieveSingleTemplateVersion(SendGridTemplateDetailResponse templateDetailResponse, ActionTemplateDto actionTemplateDto) {
        try {
            log.debug("Searching for SendGrid template Version for [{}:{}] template.", templateDetailResponse.getId(), templateDetailResponse.getName());
            TemplateVersion templateVersion = templateDetailResponse.getVersions().stream()
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND", "Not template version was found"));

            TemplateVersionResponse templateVersionResponse = sendGridClient.retrieveSingleTemplateVersion(templateVersion.getTemplateId(), templateVersion.getId());
            return sendGridClient.editTemplateVersion(templateDetailResponse.getId(), templateVersionResponse.getId(), clientRequestFactory.addOrUpdateTemplateVersion(0, actionTemplateDto.getName(), actionTemplateDto.getContent()));
        } catch (ResourceNotFoundException e) {
            log.debug("SendGrid template version for [{}:{}] template was not found.", templateDetailResponse.getId(), templateDetailResponse.getName());
            log.debug("Creating SendGrid template version for [{}:{}] template", templateDetailResponse.getId(), templateDetailResponse.getName());
            return sendGridClient.createTemplateVersion(templateDetailResponse.getId(), clientRequestFactory.addOrUpdateTemplateVersion(0, actionTemplateDto.getName(), actionTemplateDto.getContent()));
        }
    }
}
