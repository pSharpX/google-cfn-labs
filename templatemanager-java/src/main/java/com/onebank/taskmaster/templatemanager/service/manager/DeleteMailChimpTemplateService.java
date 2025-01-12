package com.onebank.taskmaster.templatemanager.service.manager;

import com.onebank.taskmaster.templatemanager.email.model.mailchimp.GetTemplateInfoRequest;
import com.onebank.taskmaster.templatemanager.email.model.mailchimp.TemplateDetailResponse;
import com.onebank.taskmaster.templatemanager.email.provider.mailchimp.MailChimpClient;
import com.onebank.taskmaster.templatemanager.exception.ResourceNotFoundException;
import com.onebank.taskmaster.templatemanager.model.ActionTemplateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class DeleteMailChimpTemplateService extends DeleteTemplate {
    private final MailChimpClient mailChimpClient;

    @Override
    void execute(ActionTemplateDto actionTemplateDto) {
        TemplateDetailResponse templateDetailResponse;
        try {
            log.debug("Deleting Mailchimp template [{}]", actionTemplateDto.getName());
            templateDetailResponse = mailChimpClient.deleteTemplate(new GetTemplateInfoRequest(actionTemplateDto.getName()));
            log.debug("Mailchimp template [{}] deleted", templateDetailResponse.getName());
        } catch (ResourceNotFoundException e) {
            log.debug("Mailchimp template [{}] was not found", actionTemplateDto.getName());
        }
        log.debug("Action completed successfully for [{}] template", actionTemplateDto.getName());
    }
}
