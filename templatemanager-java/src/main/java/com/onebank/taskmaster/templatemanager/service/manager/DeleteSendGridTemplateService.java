package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.GetTemplatesResponse;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.SendGridTemplateDetailResponse;
import com.onebank.taskmaster.templatemanager.email.provider.sendgrid.SendGridClient;
import com.onebank.taskmaster.templatemanager.email.provider.sendgrid.SendGridClientRequestFactory;
import com.onebank.taskmaster.templatemanager.exception.ResourceNotFoundException;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class DeleteSendGridTemplateService extends DeleteTemplate {
    private final SendGridClient sendGridClient;
    private final SendGridClientRequestFactory clientRequestFactory;

    @Override
    void execute(ActionTemplateDto actionTemplateDto) {
        SendGridTemplateDetailResponse templateDetailResponse;
        try {
            log.debug("Searching for SendGrid [{}] template.", actionTemplateDto.getName());
            GetTemplatesResponse templatesResponse = sendGridClient.retrieveTemplates(clientRequestFactory.getDefaultTemplateGeneration());
            templateDetailResponse = templatesResponse.getResult().stream()
                    .filter(template -> template.getName().equalsIgnoreCase(actionTemplateDto.getName()))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND", "No template was found"));

            log.debug("Deleting SendGrid template [{}:{}]", templateDetailResponse.getId(), templateDetailResponse.getName());
            sendGridClient.deleteTemplate(templateDetailResponse.getId());
            log.debug("SendGrid template [{}:{}] deleted", templateDetailResponse.getId(), templateDetailResponse.getName());
        } catch (ResourceNotFoundException e) {
            log.debug("SendGrid template [{}] was not found", actionTemplateDto.getName());
        }
        log.debug("Action completed successfully for [{}] template", actionTemplateDto.getName());
    }
}
